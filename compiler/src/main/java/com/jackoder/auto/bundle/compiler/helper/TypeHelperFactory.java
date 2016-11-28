package com.jackoder.auto.bundle.compiler.helper;

import com.jackoder.auto.bundle.compiler.helper.type.BinderHelper;
import com.jackoder.auto.bundle.compiler.helper.type.KnownTypeHelper;
import com.jackoder.auto.bundle.compiler.helper.type.ParcelableArrayHelper;
import com.jackoder.auto.bundle.compiler.helper.type.ParcelableHelper;
import com.jackoder.auto.bundle.compiler.helper.type.ParcelableVariableHelper;
import com.jackoder.auto.bundle.compiler.helper.type.PrimitiveArrayHelper;
import com.jackoder.auto.bundle.compiler.helper.type.PrimitiveHelper;
import com.jackoder.auto.bundle.compiler.helper.type.SerializableHelper;
import com.jackoder.auto.bundle.compiler.util.Utils;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;

import java.io.Serializable;

import javax.lang.model.util.Elements;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class TypeHelperFactory {

    public static AbsTypeHelper getHelper(TypeName typeName, Elements elementUtils) {
        if (typeName.isPrimitive()) {
            return new PrimitiveHelper(typeName);
        }
        if (KnownTypeHelper.isKnownType(typeName)) {
            return new KnownTypeHelper(typeName);
        }
        if (isParcelable(typeName, elementUtils)) {
            return new ParcelableHelper(typeName, elementUtils);
        }
        if (PrimitiveArrayHelper.isPrimitiveArray(typeName)) {
            return new PrimitiveArrayHelper(typeName);
        }
        if (ParcelableArrayHelper.isParcelableArray(typeName, elementUtils)) {
            return new ParcelableArrayHelper(typeName, elementUtils);
        }
        if (ParcelableVariableHelper.isKnownParcelableTypeVariable(typeName, elementUtils)) {
            return new ParcelableVariableHelper(typeName, elementUtils);
        }
        if (isSerializable(typeName, elementUtils)) {
            return new SerializableHelper(typeName, elementUtils);
        }
        if (isBinder(typeName, elementUtils)) {
            return new BinderHelper(typeName, elementUtils);
        }
        return null;
    }

    public static boolean isParcelable(ClassName className, Elements elementUtils) {
        return Utils.implementsInterface(
                Utils.getTypeMirror(className, elementUtils),
                "android.os.Parcelable"
        );
    }

    public static boolean isParcelable(TypeName typeName, Elements elements) {
        if (typeName instanceof WildcardTypeName
                || typeName instanceof TypeVariableName
                || typeName instanceof ArrayTypeName
                || typeName.isPrimitive()) {
            // TODO handle these better. Check is the assumption is valid
            return false;
        }
        if (typeName instanceof ClassName) {
            return isParcelable((ClassName) typeName, elements);
        }
        if (typeName instanceof ParameterizedTypeName) {
            ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
            ClassName primaryType = parameterizedTypeName.rawType;
            return isParcelable(primaryType, elements);
        }
        return false;
    }

    public static boolean isSerializable(ClassName className, Elements elementUtils) {
        return Utils.implementsInterface(
                Utils.getTypeMirror(className, elementUtils),
                Serializable.class.getCanonicalName()
        );
    }

    public static boolean isSerializable(TypeName typeName, Elements elements) {
        if (typeName.isPrimitive()) {
            return false;
        }
        if (typeName instanceof WildcardTypeName || typeName instanceof TypeVariableName) {
            // TODO this is definitely not true. But WildcardType fields is probably not possible. Check.
            return false;
        }
        if (typeName instanceof ArrayTypeName) {
            return isSerializable(((ArrayTypeName) typeName).componentType, elements);
        }
        if (typeName instanceof ClassName) {
            return isSerializable((ClassName) typeName, elements);
        }
        if (typeName instanceof ParameterizedTypeName) {
            ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
            ClassName primaryType = parameterizedTypeName.rawType;
            return isSerializable(primaryType, elements);
        }
        return false;
    }

    public static boolean isBinder(ClassName className, Elements elementUtils) {
        return Utils.implementsInterface(
                Utils.getTypeMirror(className, elementUtils),
                "android.os.IBinder"
        );
    }

    public static boolean isBinder(TypeName typeName, Elements elements) {
        if (typeName instanceof WildcardTypeName
                || typeName instanceof TypeVariableName
                || typeName instanceof ArrayTypeName
                || typeName.isPrimitive()) {
            // TODO handle these better. Check is the assumption is valid
            return false;
        }
        if (typeName instanceof ClassName) {
            return isBinder((ClassName) typeName, elements);
        }
        if (typeName instanceof ParameterizedTypeName) {
            ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
            ClassName primaryType = parameterizedTypeName.rawType;
            return isBinder(primaryType, elements);
        }
        return false;
    }
}
