package com.chaplin.test1.core.di.module;

import com.chaplin.test1.core.pref.Prefs;
import com.chaplin.test1.data.db.AppDatabase;
import com.chaplin.test1.data.entity.mapper.VehicleEntityMapper;
import com.chaplin.test1.data.networking.core.DataResponse;
import com.chaplin.test1.data.networking.core.RestClient;
import com.chaplin.test1.data.repository.VehiclesRepositoryImpl;
import com.chaplin.test1.data.repository.datasource.VehiclesDataSourceFactory;
import com.chaplin.test1.domain.repository.VehiclesRepository;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;

import javax.inject.Singleton;

@Module
public abstract class RepositoryModule {

    @Provides
    static VehicleEntityMapper provideVehicleEntityMapper() {
        return new VehicleEntityMapper();
    }

    @Provides
    static VehiclesDataSourceFactory provideVehiclesDataSourceFactory(RestClient<Flowable<DataResponse>> restClient,
                                                                      AppDatabase database) {
        return new VehiclesDataSourceFactory(restClient, database, Prefs.Data.LAST_CACHE_TIME);
    }

    @Singleton
    @Provides
    static VehiclesRepository provideVehiclesRepository(VehiclesDataSourceFactory factory, VehicleEntityMapper mapper) {
        return new VehiclesRepositoryImpl(factory, mapper);
    }
}
