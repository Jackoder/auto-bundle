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
    public static final ClassName BUNDLE       = ClassName.get("android.os", "Bundle");
    public static final ClassName SIZE         = ClassName.get("android.util", "Size");
    public static final ClassName SIZEF        = ClassName.get("android.util", "SizeF");

    public static final ClassName CONTEXT = ClassName.get("android.content", "Context");
    public static final ClassName INTENT  = ClassName.get("android.content", "Intent");
    public static final ClassName IBINDER = ClassName.get("android.os", "IBinder");

}
