package com.chaplin.test1.domain.usecase;

import com.chaplin.test1.domain.model.Vehicle;
import com.chaplin.test1.domain.repository.VehiclesRepository;

import java.util.List;

public class GetVehiclesUseCase extends UseCase<List<Vehicle>, Void> {

    private final VehiclesRepository mRepository;

    public GetVehiclesUseCase(VehiclesRepository repository) {
        mRepository = repository;
    }

    @Override
    public List<Vehicle> execute(Void aVoid) {
        return mRepository.getVehicles();
    }
}
