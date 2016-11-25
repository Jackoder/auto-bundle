/*
package com.jackoder.auto.bundle.compiler;

import android.os.Bundle;

import com.jackoder.auto.bundle.IAutoBundle;

import static com.jackoder.auto.bundle.compiler.helper.ClassProvider.bundle;
import static javafx.scene.input.KeyCode.T;

*/
/**
 * @author Jackoder
 * @version 2016/11/25
 *//*

public class TemplateBundle implements IAutoBundle<Object> {

    T xxx;
    T xxx;

    @Override
    public void read(Bundle bundle, Object target) {
        target.xxx = bundle.getInt("key");
        target.xxx = bundle.getParcelable("key2");
    }

    @Override
    public void write(Object target, Bundle bundle) {
        bundle.putInt("key2", target.xxx);
        bundle.putParcelable("key2", target.xxx);
    }

    TemplateBundle xxx(T xxx) {
        xxx = xxx;
        return this;
    }

    Bundle build() {
        return build(null);
    }

    Bundle build(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("key", xxx);
        bundle.putParcelable("key2", xxx);
        return bundle;
    }
}
*/
