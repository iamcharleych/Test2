package com.chaplin.test1.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.chaplin.test1.data.entity.VehicleEntity;
import io.reactivex.Flowable;

import java.util.List;

@Dao
public interface VehiclesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] saveVehicles(List<VehicleEntity> vehicles);

    @Query("DELETE FROM vehicles")
    int deleteAllVehicles();

    @Query("SELECT * FROM vehicles") // WHERE interior='GOOD' AND exterior='GOOD'
    List<VehicleEntity> loadVehicles();
}
