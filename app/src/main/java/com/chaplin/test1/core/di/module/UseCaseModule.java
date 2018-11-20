package com.chaplin.test1.core.di.module;

import com.chaplin.test1.core.model.mapper.VehicleModelMapper;
import com.chaplin.test1.domain.repository.VehiclesRepository;
import com.chaplin.test1.domain.usecase.GetVehiclesUseCase;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public abstract class UseCaseModule {

    @Provides
    static public VehicleModelMapper provideVehicleModelMapper() {
        return new VehicleModelMapper();
    }

    @Singleton
    @Provides
    static public GetVehiclesUseCase provideGetVehicleUseCase(VehiclesRepository repository) {
        return new GetVehiclesUseCase(repository);
    }
}
