package com.jackoder.auto.bundle.compiler.holder;

import com.jackoder.auto.bundle.ann.Extra;
import com.jackoder.auto.bundle.compiler.IProcessor;
import com.jackoder.auto.bundle.compiler.helper.AbsTypeHelper;
import com.jackoder.auto.bundle.compiler.helper.TypeHelperFactory;
import com.jackoder.auto.bundle.compiler.util.StringUtils;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public class ExtraHolder {

    Element    mElement;
    IProcessor mProcessor;

    AbsTypeHelper mTypeHelper;
    String        mFieldName;
    String        mKey;

    public ExtraHolder(Element element, IProcessor processor) {
        mElement = element;
        mProcessor = processor;
        mTypeHelper = TypeHelperFactory.getHelper(TypeName.get(element.asType()), processor.elementUtils());
        mFieldName = element.getSimpleName().toString();
        if (element.getModifiers().contains(Modifier.PRIVATE)) {
            throw new IllegalStateException("@Extra field " + mFieldName + " must not be private or static.");
        }
        mKey = element.getAnnotation(Extra.class).key();
    }

    public String getBundleFieldName() {
        return mFieldName;
    }

    public TypeName getTypeName() {
        return mTypeHelper.getTypeName();
    }

    public String getFieldName() {
        return StringUtils.getVariableName(mFieldName);
    }

    public String getParamName() {
        return StringUtils.getVariableNameWithoutPrefix(mFieldName);
    }

    public String getGetMethod() {
        return "get" + mTypeHelper.getBundleMethodSuffix();
    }

    public String getPutMethod() {
        return "put" + mTypeHelper.getBundleMethodSuffix();
    }

    public boolean isPrimitive() {
        return mTypeHelper.getTypeName().isPrimitive();
    }

    public boolean isNeedCast() {
        return mTypeHelper.isNeedCast();
    }

    public String getKey() {
        return mKey;
    }

    public FieldSpec asField(Modifier... modifiers) {
        TypeName typeName = mTypeHelper.getTypeName().isPrimitive() ?
                mTypeHelper.getTypeName().box() : mTypeHelper.getTypeName();
        return FieldSpec.builder(typeName, getFieldName(), modifiers).build();
    }

    public ParameterSpec asParameter(Modifier... modifiers) {
        return ParameterSpec.builder(mTypeHelper.getTypeName(), getParamName(), modifiers).build();
    }
}
