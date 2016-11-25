package com.jackoder.auto.bundle.ann;

import android.os.Bundle;

import org.parceler.Parcels;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public class ParcelConverter extends Converter<Object> {

    @Override
    public void put(Bundle bundle, String key, Object value) {
        bundle.putParcelable(key, Parcels.wrap(value));
    }

    @Override
    public Object get(Bundle bundle, String key) {
        return Parcels.unwrap(bundle.getParcelable(key));
    }
}
