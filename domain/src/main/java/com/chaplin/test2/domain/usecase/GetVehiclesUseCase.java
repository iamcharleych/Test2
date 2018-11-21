package com.chaplin.test2.domain.usecase;

import com.chaplin.test2.domain.execution.ExecutionThread;
import com.chaplin.test2.domain.model.Vehicle;
import com.chaplin.test2.domain.repository.VehiclesRepository;

import java.util.List;

import io.reactivex.Flowable;

public class GetVehiclesUseCase extends UseCase<List<Vehicle>, Void> {

    private final VehiclesRepository mRepository;

    public GetVehiclesUseCase(VehiclesRepository repository,
                              ExecutionThread mWorkerThread,
                              ExecutionThread mObserverThread) {
        super(mWorkerThread, mObserverThread);
        mRepository = repository;
    }

    @Override
    public Flowable<List<Vehicle>> createObservable(Void aVoid) {
        return mRepository.getVehicles();
    }
}
