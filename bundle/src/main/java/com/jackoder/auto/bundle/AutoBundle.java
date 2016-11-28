package com.jackoder.auto.bundle;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public class AutoBundle {

    public final static String PREFIX = "ATBundle";

    private static AutoBundle sAutoBundle;

    private Map<Class, IAutoBundle> mAutoBundleMap = new HashMap<>();

    public static void read(Object target, Bundle bundle, Bundle... bundles) {
        IAutoBundle autoBundle;
        if (target != null && (autoBundle = get().findAutoBundle(target.getClass())) != null) {
            Bundle data = new Bundle(bundle);
            if (bundles != null) {
                for (Bundle temp : bundles) {
                    data.putAll(temp);
                }
            }
            autoBundle.read(target, bundle);
        }
        //TODO
    }

    public static void write(Object target, Bundle bundle) {
        IAutoBundle autoBundle;
        if (target != null && (autoBundle = get().findAutoBundle(target.getClass())) != null) {
            autoBundle.write(target, bundle);
        }
        //TODO
    }

    private static AutoBundle get() {
        if (sAutoBundle == null) {
            synchronized (AutoBundle.class) {
                if (sAutoBundle == null) {
                    sAutoBundle = new AutoBundle();
                }
            }
        }
        return sAutoBundle;
    }

    private IAutoBundle findAutoBundle(Class targetClazz) {
        if (mAutoBundleMap.containsKey(targetClazz)) {
            return mAutoBundleMap.get(targetClazz);
        } else {
            try {
                String targetClsName = targetClazz.getName();
                Class mapperClazz = Class.forName(PREFIX +
                        targetClsName.substring(targetClsName.lastIndexOf(".") + 1, targetClsName.length()));
                IAutoBundle mapper = (IAutoBundle) mapperClazz.newInstance();
                mAutoBundleMap.put(targetClazz, mapper);
                return mapper;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
