package com.jackoder.auto.bundle.compiler.helper.type;

import com.jackoder.auto.bundle.compiler.helper.AbsTypeHelper;
import com.jackoder.auto.bundle.compiler.helper.ClassProvider;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class KnownTypeHelper extends AbsTypeHelper {

    private static List<TypeName> sKnownTypes = Arrays.asList(
            ClassProvider.BUNDLE,
            ClassProvider.SIZE,
            ClassProvider.SIZEF,
            TypeName.get(String.class),
            TypeName.get(CharSequence.class),
            ArrayTypeName.of(String.class),
            ArrayTypeName.of(CharSequence.class),
            ParameterizedTypeName.get(ArrayList.class, String.class),
            ParameterizedTypeName.get(ArrayList.class, CharSequence.class),
            ParameterizedTypeName.get(ArrayList.class, Integer.class)
    );

    public KnownTypeHelper(TypeName typeName) {
        super(typeName);
        if(!isKnownType(typeName)) {
            throw new IllegalStateException("KnownTypeHelper used for an unrecognized type");
        }
    }

    @Override
    public String getBundleMethodSuffix() {
        if(mTypeName instanceof ClassName) {
            return ((ClassName) mTypeName).simpleName();
        }
        if(mTypeName instanceof ArrayTypeName) {
            ClassName component = (ClassName) ((ArrayTypeName) mTypeName).componentType;
            return component.simpleName() + "Array";
        }
        if(mTypeName instanceof ParameterizedTypeName) {
            ClassName rootType = ((ParameterizedTypeName) mTypeName).rawType;
            ClassName parameter = (ClassName) ((ParameterizedTypeName) mTypeName).typeArguments.get(0);
            return parameter.simpleName() + rootType.simpleName();
        }
        throw new IllegalStateException("Unhandled case in KnownTypeHelper");
    }

    @Override
    public boolean isNeedCast() {
        return false;
    }

    public static boolean isKnownType(TypeName typeName) {
        return sKnownTypes.contains(typeName);
    }
}
