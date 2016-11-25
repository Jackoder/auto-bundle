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
    public static final ClassName context = ClassName.get("android.content", "Context");
    public static final ClassName bundle = ClassName.get("android.os", "Bundle");
    public static final ClassName intent = ClassName.get("android.content", "Intent");

}
