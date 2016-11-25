package com.jackoder.auto.bundle.compiler;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public interface IProcessor {

    Types typeUtils();
    Elements elementUtils();
    Filer filer();
    Messager messager();

}
