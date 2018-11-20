package com.chaplin.test1.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.chaplin.test1.data.entity.VehicleEntity;

@Database(
        entities = { VehicleEntity.class },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "testapp_database.db";

    public abstract VehiclesDao getVehiclesDao();

}
