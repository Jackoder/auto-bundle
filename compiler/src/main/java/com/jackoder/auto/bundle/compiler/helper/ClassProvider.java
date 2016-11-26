package com.jackoder.auto.bundle.compiler.helper;

import com.squareup.javapoet.ClassName;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public class ClassProvider {

    private ClassProvider() {
    }

    // android classes
    public static final ClassName CONTEXT = ClassName.get("android.content", "Context");
    public static final ClassName BUNDLE  = ClassName.get("android.os", "Bundle");
    public static final ClassName INTENT  = ClassName.get("android.content", "Intent");

}
