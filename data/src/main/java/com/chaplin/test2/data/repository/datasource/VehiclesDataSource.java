package com.chaplin.test2.data.repository.datasource;

import com.chaplin.test2.data.entity.VehicleEntity;
import io.reactivex.Flowable;

import java.util.List;

public interface VehiclesDataSource {

    Flowable<List<VehicleEntity>> getVehicles();
}
