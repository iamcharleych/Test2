package com.chaplin.test1;

import android.app.Activity;
import android.app.Application;
import com.chaplin.test1.common.pref.PrefsContext;
import com.chaplin.test1.core.di.component.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import javax.inject.Inject;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        PrefsContext.init(this);

        DaggerAppComponent.builder().app(this).build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
