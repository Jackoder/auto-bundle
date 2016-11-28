package com.jackoder.auto.bundle.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface Extra {
    String key();
}
