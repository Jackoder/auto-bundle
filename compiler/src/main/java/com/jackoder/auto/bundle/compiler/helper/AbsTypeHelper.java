package com.jackoder.auto.bundle.compiler.helper;

import com.squareup.javapoet.TypeName;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public abstract class AbsTypeHelper {

    protected TypeName mTypeName;

    public AbsTypeHelper(TypeName typeName) {
        mTypeName = typeName;
    }

    public TypeName getTypeName() {
        return mTypeName;
    }

    public abstract String getBundleMethodSuffix();

    public abstract boolean isNeedCast();
}
