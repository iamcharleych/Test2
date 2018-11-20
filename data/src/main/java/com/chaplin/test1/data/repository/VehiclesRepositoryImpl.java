package com.chaplin.test1.data.repository;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.chaplin.test1.data.entity.VehicleEntity;
import com.chaplin.test1.data.entity.mapper.VehicleEntityMapper;
import com.chaplin.test1.data.repository.datasource.VehiclesDataSourceFactory;
import com.chaplin.test1.domain.model.Vehicle;
import com.chaplin.test1.domain.repository.VehiclesRepository;
import io.reactivex.Flowable;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class VehiclesRepositoryImpl implements VehiclesRepository {

    @NonNull
    private final VehiclesDataSourceFactory mFactory;
    @NonNull
    private final VehicleEntityMapper mMapper;

    public VehiclesRepositoryImpl(@NonNull VehiclesDataSourceFactory factory, @NonNull VehicleEntityMapper mapper) {
        mFactory = factory;
        mMapper = mapper;
    }

    @WorkerThread
    @Override
    public List<Vehicle> getVehicles() {
        return mFactory.create().getVehicles().map(mMapper::map).blockingSingle();
    }
}
