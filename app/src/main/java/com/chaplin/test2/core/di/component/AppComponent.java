package com.chaplin.test2.core.di.component;

import com.chaplin.test1.App;
import com.chaplin.test1.core.di.module.AppModule;
import com.chaplin.test2.App;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class
})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder app(App app);

        AppComponent build();
    }
}
