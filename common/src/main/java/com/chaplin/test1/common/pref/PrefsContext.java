package com.chaplin.test1.common.pref;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("StaticFieldLeak")
public class PrefsContext {

    private static Context mPrefsContext;

    /* package */ static Context get() {
        return mPrefsContext;
    }

    public static void init(Context context) {
        mPrefsContext = context;
    }
}
