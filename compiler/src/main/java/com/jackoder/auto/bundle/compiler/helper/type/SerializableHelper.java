package com.jackoder.auto.bundle.compiler.helper.type;

import com.jackoder.auto.bundle.compiler.helper.AbsTypeHelper;
import com.jackoder.auto.bundle.compiler.helper.TypeHelperFactory;
import com.squareup.javapoet.TypeName;

import javax.lang.model.util.Elements;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class SerializableHelper extends AbsTypeHelper {

    public SerializableHelper(TypeName typeName, Elements elementUtils) {
        super(typeName);
        if (!TypeHelperFactory.isSerializable(typeName, elementUtils)) {
            throw new IllegalStateException("SerializableHelper used for a non serializable object");
        }
    }

    @Override
    public String getBundleMethodSuffix() {
        return "Serializable";
    }

    @Override
    public boolean isNeedCast() {
        return true;
    }
}
