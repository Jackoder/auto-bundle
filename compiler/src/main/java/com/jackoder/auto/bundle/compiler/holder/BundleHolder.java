package com.jackoder.auto.bundle.compiler.holder;

import com.jackoder.auto.bundle.ann.AutoBundle;
import com.jackoder.auto.bundle.ann.Extra;
import com.jackoder.auto.bundle.compiler.IProcessor;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public class BundleHolder {

    TypeElement mBundleElement;
    IProcessor  mProcessor;

    String            mPackageName;
    ClassName         mClassName;
    List<ExtraHolder> mExtras;

    public BundleHolder(TypeElement bundleElement, IProcessor processor) {
        this.mBundleElement = bundleElement;
        this.mProcessor = processor;
        mPackageName = processor.elementUtils().getPackageOf(bundleElement).getQualifiedName().toString();
        mClassName = ClassName.get(mPackageName, bundleElement.getSimpleName().toString());
        Map<String, ExtraHolder> extraHolderMap = createExtras(bundleElement, processor);
        if (extraHolderMap.isEmpty()) {
            throw new IllegalStateException(mClassName + " should have at lease one filed annotated @Extra.");
        }
        mExtras = new ArrayList<>();
        mExtras.addAll(extraHolderMap.values());
    }

    private Map<String, ExtraHolder> createExtras(TypeElement typeElement, IProcessor processor) {
        Map<String, ExtraHolder> extraHolderMap = new HashMap<>();
        TypeMirror parentTypeElement = typeElement.getSuperclass();
        if (parentTypeElement != null && !ClassName.get(parentTypeElement).equals(TypeName.OBJECT)) {
            extraHolderMap.putAll(createExtras(
                    (TypeElement) processor.typeUtils().asElement(parentTypeElement), processor));
        }
        if (typeElement.getAnnotation(AutoBundle.class) != null) {
            List<? extends Element> enclosedElements = typeElement.getEnclosedElements();
            for (Element element : enclosedElements) {
                Annotation annotation = element.getAnnotation(Extra.class);
                if (annotation != null) {
                    ExtraHolder extraHolder = new ExtraHolder(element, processor);
                    extraHolderMap.put(extraHolder.getKey(), extraHolder);
                }
            }
        }
        return extraHolderMap;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public ClassName getClassName() {
        return mClassName;
    }

    public List<ExtraHolder> getExtras() {
        return mExtras;
    }
}
