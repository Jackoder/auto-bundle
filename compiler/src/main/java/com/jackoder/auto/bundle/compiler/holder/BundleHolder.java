package com.jackoder.auto.bundle.compiler.holder;

import com.jackoder.auto.bundle.ann.Extra;
import com.jackoder.auto.bundle.compiler.IProcessor;
import com.squareup.javapoet.ClassName;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

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

    private BundleHolder(TypeElement bundleElement, IProcessor processor) {
        this.mBundleElement = bundleElement;
        this.mProcessor = processor;
    }

    public static BundleHolder from(TypeElement bundleElement, IProcessor processor) {
        BundleHolder bundleHolder = new BundleHolder(bundleElement, processor);
        bundleHolder.mPackageName = processor.elementUtils().getPackageOf(bundleElement).getQualifiedName().toString();
        bundleHolder.mClassName = ClassName.get(bundleHolder.mPackageName, bundleElement.getSimpleName().toString());
        List<? extends Element> enclosedElements = bundleElement.getEnclosedElements();
        bundleHolder.mExtras = new ArrayList<>();
        for (Element element : enclosedElements) {
            Annotation annotation = element.getAnnotation(Extra.class);
            if (annotation != null) {
                bundleHolder.mExtras.add(ExtraHolder.from(element));
            }
        }
        return bundleHolder;
    }

    public TypeElement getBundleElement() {
        return mBundleElement;
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
