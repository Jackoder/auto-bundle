package com.jackoder.auto.bundle.sample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class ParcelableData implements Parcelable {

    protected ParcelableData(Parcel in) {
    }

    public static final Creator<ParcelableData> CREATOR = new Creator<ParcelableData>() {
        @Override
        public ParcelableData createFromParcel(Parcel in) {
            return new ParcelableData(in);
        }

        @Override
        public ParcelableData[] newArray(int size) {
            return new ParcelableData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
