package com.jackoder.auto.bundle.compiler.holder;

import java.lang.reflect.Type;

import javax.lang.model.element.Element;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public class ExtraHolder {

    Element mElement;

    Type   mType;
    String mFiledName;
    String mKey;

    private ExtraHolder(Element element) {

    }

    public static ExtraHolder from(Element element) {
        ExtraHolder extraHolder = new ExtraHolder(element);
        //TODO
        return extraHolder;
    }

    public Type getType() {
        return mType;
    }

    public String getFiledName() {
        return mFiledName;
    }

    public String getGetMethod() {
        //TODO
        return null;
    }

    public String getPutMethod() {
        //TODO
        return null;
    }

    public String getKey() {
        return mKey;
    }
}
