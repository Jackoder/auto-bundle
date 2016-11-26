package com.jackoder.auto.bundle.sample;

import android.os.Bundle;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import com.jackoder.auto.bundle.ann.AutoBundle;
import com.jackoder.auto.bundle.ann.Extra;

import java.util.ArrayList;

/**
 * Created by Jackoder on 2016/11/25.
 */
@AutoBundle
public class Student extends People {

    @Extra(key = "boolean")
    boolean                     mBoolean;
    @Extra(key = "byte")
    byte                        mByte;
    @Extra(key = "char")
    char                        mChar;
    @Extra(key = "short")
    short                       mShort;
    @Extra(key = "int")
    int                         mInt;
    @Extra(key = "long")
    long                        mLong;
    @Extra(key = "float")
    float                       mFloat;
    @Extra(key = "double")
    double                      mDouble;
    @Extra(key = "String")
    String                      mString;
    @Extra(key = "CharSequence")
    CharSequence                mCharSequence;
    @Extra(key = "Parcelable")
    ParcelableData              mParcelable;
    @Extra(key = "Size")
    Size                        mSize;
    @Extra(key = "SizeF")
    SizeF                       mSizeF;
    @Extra(key = "Parcelable[]")
    ParcelableData[]            mParcelables;
    @Extra(key = "ArrayList<Parcelable>")
    ArrayList<ParcelableData>   mParcelableArrayList;
    @Extra(key = "SparseArray<Parcelable>")
    SparseArray<ParcelableData> mSparseArray;
    @Extra(key = "ArrayList<Integer>")
    ArrayList<Integer>          mIntegerArrayList;
    @Extra(key = "ArrayList<String>")
    ArrayList<String>           mStringArrayList;
    @Extra(key = "ArrayList<CharSequence>")
    ArrayList<CharSequence>     mCharSequenceArrayList;
    @Extra(key = "Serializable")
    SerializableData            mSerializable;
    @Extra(key = "boolean[]")
    boolean[]                   mBooleans;
    @Extra(key = "byte[]")
    byte[]                      mBytes;
    @Extra(key = "short[]")
    short[]                     mShorts;
    @Extra(key = "char[]")
    char[]                      mChars;
    @Extra(key = "int[]")
    int[]                       mInts;
    @Extra(key = "long[]")
    long[]                      mLongs;
    @Extra(key = "float[]")
    float[]                     mFloats;
    @Extra(key = "double[]")
    double[]                    mDoubles;
    @Extra(key = "String[]")
    String[]                    mStrings;
    @Extra(key = "CharSequence[]")
    CharSequence[]              mCharSequences;
    @Extra(key = "Bundle")
    Bundle                      mBundle;
    @Extra(key = "IBinder")
    BinderData                  mBinder;

}
