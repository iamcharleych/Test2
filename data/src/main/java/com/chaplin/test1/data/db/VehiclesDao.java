package com.chaplin.test1.data.db;

import com.chaplin.test1.data.entity.VehicleEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface VehiclesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] saveVehicles(List<VehicleEntity> vehicles);

    @Query("DELETE FROM vehicles")
    int deleteAllVehicles();

    @Query("SELECT * FROM vehicles") // WHERE interior='GOOD' AND exterior='GOOD'
    Flowable<List<VehicleEntity>> loadVehicles();
}
