package com.jackoder.auto.bundle.sample;

import com.jackoder.auto.bundle.ann.Extra;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
//@AutoBundle
public class People {

    @Extra(key = "userId")
    long   mId;
    @Extra(key = "name")
    String mName;
}
