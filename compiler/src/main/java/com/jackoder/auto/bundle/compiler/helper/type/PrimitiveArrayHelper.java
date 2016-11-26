package com.jackoder.auto.bundle.compiler.helper.type;

import com.jackoder.auto.bundle.compiler.helper.AbsTypeHelper;
import com.jackoder.auto.bundle.compiler.util.StringUtils;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.TypeName;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class PrimitiveArrayHelper extends AbsTypeHelper {

    public PrimitiveArrayHelper(TypeName typeName) {
        super(typeName);
        if (!isPrimitiveArray(typeName)) {
            throw new IllegalStateException("PrimitiveArrayHelper used for a non PrimitiveArray");
        }
    }

    @Override
    public String getBundleMethodSuffix() {
        return StringUtils.getClassName(((ArrayTypeName)mTypeName).componentType.toString()) + "Array";
    }

    @Override
    public boolean isNeedCast() {
        return false;
    }

    public static boolean isPrimitiveArray(TypeName typeName) {
        if(typeName instanceof ArrayTypeName) {
            return ((ArrayTypeName) typeName).componentType.isPrimitive();
        }
        return false;
    }
}
