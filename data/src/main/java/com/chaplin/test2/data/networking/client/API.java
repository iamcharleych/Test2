package com.chaplin.test2.data.networking.client;

import com.chaplin.test2.data.entity.VehicleEntity;
import io.reactivex.Flowable;
import retrofit2.http.GET;

import java.util.List;

public interface API {

    @GET("car2go/vehicles.json")
    Flowable<List<VehicleEntity>> getVehicles();
}
