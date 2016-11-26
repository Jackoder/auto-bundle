package com.jackoder.auto.bundle.sample;

import com.jackoder.auto.bundle.ann.AutoBundle;
import com.jackoder.auto.bundle.ann.Extra;

/**
 * Created by Jackoder on 2016/11/25.
 */
@AutoBundle
public class Student extends People {

    @Extra(key = "score")
    int             mScore;
    @Extra(key = "finish")
    boolean         mFinish;
//    @Extra(key = "friendIds")
//    ArrayList<Long> mFriendIds;

}
