package com.jackoder.auto.bundle.compiler.helper.type;

import com.jackoder.auto.bundle.compiler.helper.AbsTypeHelper;
import com.jackoder.auto.bundle.compiler.helper.TypeHelperFactory;
import com.squareup.javapoet.TypeName;

import javax.lang.model.util.Elements;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class ParcelableHelper extends AbsTypeHelper {

    public ParcelableHelper(TypeName typeName, Elements elementUtils) {
        super(typeName);
        if (!TypeHelperFactory.isParcelable(typeName, elementUtils)) {
            throw new IllegalStateException("ParcelableHelper used for a non parcelable");
        }
    }

    @Override
    public String getBundleMethodSuffix() {
        return "Parcelable";
    }

    @Override
    public boolean isNeedCast() {
        return false;
    }
}
