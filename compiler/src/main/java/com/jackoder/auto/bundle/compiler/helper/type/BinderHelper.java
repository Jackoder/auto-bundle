package com.jackoder.auto.bundle.compiler.helper.type;

import com.jackoder.auto.bundle.compiler.helper.AbsTypeHelper;
import com.jackoder.auto.bundle.compiler.helper.TypeHelperFactory;
import com.squareup.javapoet.TypeName;

import javax.lang.model.util.Elements;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class BinderHelper extends AbsTypeHelper {

    public BinderHelper(TypeName typeName, Elements elementUtils) {
        super(typeName);
        if (!TypeHelperFactory.isBinder(typeName, elementUtils)) {
            throw new IllegalStateException("BinderHelper used for a non binder");
        }
    }

    @Override
    public String getBundleMethodSuffix() {
        return "Binder";
    }

    @Override
    public boolean isNeedCast() {
        return true;
    }
}