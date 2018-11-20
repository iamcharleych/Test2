package com.chaplin.test2.core.di.module;

import com.chaplin.test1.core.model.mapper.VehicleModelMapper;
import com.chaplin.test1.domain.usecase.GetVehiclesUseCase;
import com.chaplin.test1.ui.vehicles.viewmodel.VehiclesViewModel;
import com.chaplin.test2.core.model.mapper.VehicleModelMapper;
import com.chaplin.test2.ui.vehicles.viewmodel.VehiclesViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelModule {

    @Provides
    static VehiclesViewModel.Factory provideVehiclesViewModelFactory(GetVehiclesUseCase useCase, VehicleModelMapper mapper) {
        return new VehiclesViewModel.Factory(useCase, mapper);
    }
}
