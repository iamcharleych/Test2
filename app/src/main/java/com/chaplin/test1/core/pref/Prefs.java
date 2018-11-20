package com.chaplin.test1.core.pref;

import com.chaplin.test1.common.pref.LongPref;

public class Prefs {

    public static class Data {
        public static final LongPref LAST_CACHE_TIME = new LongPref("com.chaplin.test1.LastCacheTime", 0L);
    }
}
