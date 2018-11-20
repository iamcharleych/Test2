package com.chaplin.test1.common.pref;

import android.content.SharedPreferences;

public class FloatPref extends BasePref<Float> {
    public FloatPref(String key, Float defaultValue) {
        super(key, defaultValue);
    }

    @Override
    protected Float getValueFromPrefs(SharedPreferences prefs) {
        return prefs.getFloat(mKey, mDefaultValue);
    }

    @Override
    protected void setValueToPrefs(Float value, SharedPreferences prefs) {
        prefs.edit().putFloat(mKey, value).apply();
    }
}
