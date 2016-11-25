package com.jackoder.auto.bundle.ann;

import android.os.Bundle;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public abstract class Converter<Data> {

    public abstract void put(Bundle bundle, String key, Data value);

    public abstract Data get(Bundle bundle, String key);
}
