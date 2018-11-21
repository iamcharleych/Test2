package com.chaplin.test2.domain.repository;

import com.chaplin.test2.domain.model.Vehicle;

import java.util.List;

import io.reactivex.Flowable;

public interface VehiclesRepository {

    Flowable<List<Vehicle>> getVehicles();
}
