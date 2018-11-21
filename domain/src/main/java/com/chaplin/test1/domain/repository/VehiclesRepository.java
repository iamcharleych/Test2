package com.chaplin.test1.domain.repository;

import com.chaplin.test1.domain.model.Vehicle;

import java.util.List;

import io.reactivex.Flowable;

public interface VehiclesRepository {

    Flowable<List<Vehicle>> getVehicles();
}
