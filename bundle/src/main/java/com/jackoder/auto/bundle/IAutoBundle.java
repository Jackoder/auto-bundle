package com.jackoder.auto.bundle;

import android.os.Bundle;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public interface IAutoBundle<T> {

    String METHOD_READ  = "read";
    String METHOD_WRITE = "write";
    String METHOD_BUILD = "build";

    void read(T target, Bundle bundle);

    void write(T target, Bundle bundle);

    Bundle build();

    Bundle build(Bundle bundle);
}
