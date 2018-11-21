package com.chaplin.test2.core.di.module;

import com.chaplin.test2.domain.execution.ExecutionThread;
import com.chaplin.test2.domain.repository.VehiclesRepository;
import com.chaplin.test2.domain.usecase.GetVehiclesUseCase;
import com.chaplin.test2.core.model.mapper.VehicleModelMapper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.chaplin.test2.core.di.module.ExecutionModule.ANDROID;
import static com.chaplin.test2.core.di.module.ExecutionModule.IO;

@Module
public abstract class UseCaseModule {

    @Provides
    static public VehicleModelMapper provideVehicleModelMapper() {
        return new VehicleModelMapper();
    }

    @Singleton
    @Provides
    static public GetVehiclesUseCase provideGetVehicleUseCase(VehiclesRepository repository,
          @Named(IO) ExecutionThread workerThread, @Named(ANDROID) ExecutionThread observerThread) {
        return new GetVehiclesUseCase(repository, workerThread, observerThread);
    }
}
