package com.jackoder.auto.bundle.compiler.helper.type;

import com.jackoder.auto.bundle.compiler.helper.AbsTypeHelper;
import com.jackoder.auto.bundle.compiler.helper.TypeHelperFactory;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.util.Elements;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class ParcelableArrayHelper extends AbsTypeHelper {

    public ParcelableArrayHelper(TypeName typeName, Elements elementUtils) {
        super(typeName);
        if (!isParcelableArray(typeName, elementUtils)) {
            throw new IllegalStateException("ParcelableArrayHelper used for a non ParcelableArray type");
        }
    }

    @Override
    public String getBundleMethodSuffix() {
        return "ParcelableArray";
    }

    @Override
    public boolean isNeedCast() {
        return true;
    }

    public static boolean isParcelableArray(TypeName typeName, Elements elementUtils) {
        return (typeName instanceof ArrayTypeName) && TypeHelperFactory.isParcelable(
                ((ArrayTypeName)typeName).componentType, elementUtils);
    }
}
