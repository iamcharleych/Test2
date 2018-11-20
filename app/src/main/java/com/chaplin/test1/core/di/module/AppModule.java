package com.chaplin.test1.core.di.module;

import com.chaplin.test1.core.di.scope.ActivityScope;
import com.chaplin.test1.ui.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {
        DBModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        UseCaseModule.class,
        ViewModelModule.class
})
public abstract class AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = { MainActivityModule.class })
    abstract MainActivity contributesMainActivity();
}
