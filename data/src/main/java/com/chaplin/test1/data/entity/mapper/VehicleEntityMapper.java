package com.chaplin.test1.data.entity.mapper;

import com.chaplin.test1.data.entity.VehicleEntity;
import com.chaplin.test1.domain.model.Coordinate;
import com.chaplin.test1.domain.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleEntityMapper {

    public Vehicle map(VehicleEntity entity) {
        final Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(entity.getLatitude());
        coordinate.setLongitude(entity.getLongitude());

        final Vehicle vehicle = new Vehicle();
        vehicle.setCoordinates(coordinate);
        vehicle.setAddress(entity.getAddress());
        vehicle.setEngineType(entity.getEngineType());
        vehicle.setExterior(entity.getExterior());
        vehicle.setInterior(entity.getInterior());
        vehicle.setFuel(entity.getFuel());
        vehicle.setName(entity.getName());
        vehicle.setVin(entity.getVin());

        return vehicle;
    }

    public List<Vehicle> map(List<VehicleEntity> entities) {
        List<Vehicle> result = new ArrayList<>();

        if (entities != null && entities.size() > 0) {
            for (VehicleEntity entity : entities) {
                result.add(map(entity));
            }
        }

        return result;
    }
}
