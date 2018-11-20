package com.chaplin.test1.core.di.module;

import androidx.room.Room;
import com.chaplin.test1.App;
import com.chaplin.test1.data.db.AppDatabase;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public abstract class DBModule {

    @Provides
    @Singleton
    public static AppDatabase provideDatabase(App context) {
        return Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    }
}
