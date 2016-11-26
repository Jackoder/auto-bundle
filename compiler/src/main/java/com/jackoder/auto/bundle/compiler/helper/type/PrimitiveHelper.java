package com.jackoder.auto.bundle.compiler.helper.type;

import com.jackoder.auto.bundle.compiler.helper.AbsTypeHelper;
import com.jackoder.auto.bundle.compiler.util.StringUtils;
import com.squareup.javapoet.TypeName;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public class PrimitiveHelper extends AbsTypeHelper {

    public PrimitiveHelper(TypeName typeName) {
        super(typeName);
        if (!typeName.isPrimitive()) {
            throw new IllegalStateException("PrimitiveHelper invoked for a non primitive type");
        }
    }

    @Override
    public String getBundleMethodSuffix() {
        return StringUtils.getClassName(mTypeName.toString());
    }

    @Override
    public boolean isNeedCast() {
        return false;
    }
}
