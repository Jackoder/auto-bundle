package com.jackoder.auto.bundle.compiler.helper.type;

import com.jackoder.auto.bundle.compiler.helper.AbsTypeHelper;
import com.jackoder.auto.bundle.compiler.helper.TypeHelperFactory;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.lang.model.util.Elements;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class ParcelableVariableHelper extends AbsTypeHelper {

    private static final ClassName       SPARSE_ARRAY_CLASS = ClassName.get("android.util", "SparseArray");
    public static        List<ClassName> supportedRawTypes  = Arrays.asList(
            ClassName.get(ArrayList.class),
            SPARSE_ARRAY_CLASS
    );

    public ParcelableVariableHelper(TypeName typeName, Elements elementUtils) {
        super(typeName);
        if (!isKnownParcelableTypeVariable(mTypeName, elementUtils)) {
            throw new IllegalStateException("ParcelableTypeVariableHelper used for an unsupported type");
        }
    }

    @Override
    public String getBundleMethodSuffix() {
        ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) mTypeName;
        if (parameterizedTypeName.rawType.equals(ClassName.get(ArrayList.class))) {
            return "ParcelableArrayList";
        }
        if (parameterizedTypeName.rawType.equals(SPARSE_ARRAY_CLASS)) {
            return "SparseParcelableArray";
        }
        throw new IllegalStateException("Unhandled raw type in ParcelableTypeVariableHelper");
    }

    @Override
    public boolean isNeedCast() {
        return false;
    }

    public static boolean isKnownParcelableTypeVariable(TypeName typeName, Elements elementUtils) {
        if (typeName instanceof ParameterizedTypeName) {
            ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
            if (parameterizedTypeName.typeArguments.size() == 1
                    && supportedRawTypes.contains(parameterizedTypeName.rawType)) {
                return TypeHelperFactory.isParcelable(parameterizedTypeName.typeArguments.get(0), elementUtils);
            }
        }
        return false;
    }
}