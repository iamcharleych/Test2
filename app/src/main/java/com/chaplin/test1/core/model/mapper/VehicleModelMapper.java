package com.chaplin.test1.core.model.mapper;

import com.chaplin.test1.core.model.VehicleModel;
import com.chaplin.test1.domain.model.Coordinate;
import com.chaplin.test1.domain.model.Vehicle;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class VehicleModelMapper {

    public VehicleModel map(Vehicle vehicle) {
        VehicleModel result = new VehicleModel();

        result.setVin(vehicle.getVin());
        result.setName(vehicle.getName());
        result.setAddress(vehicle.getAddress());
        Coordinate coordinate = vehicle.getCoordinates();
        result.setLocation(new LatLng(coordinate.getLatitude(), coordinate.getLongitude()));

        return result;
    }

    public List<VehicleModel> map(List<Vehicle> vehicles) {
        ArrayList<VehicleModel> result = new ArrayList<>();

        if (vehicles != null && vehicles.size() > 0) {
            for (Vehicle vehicle : vehicles) {
                result.add(map(vehicle));
            }
        }

        return result;
    }
}
