package com.chaplin.test1.data.repository.datasource;

import androidx.annotation.NonNull;
import com.chaplin.test1.data.db.AppDatabase;
import com.chaplin.test1.data.entity.VehicleEntity;
import io.reactivex.Flowable;

import java.util.List;

public class DatabaseVehiclesDataSource implements VehiclesDataSource {

    @NonNull
    private final AppDatabase mDatabase;

    public DatabaseVehiclesDataSource(@NonNull AppDatabase database) {
        mDatabase = database;
    }

    @Override
    public Flowable<List<VehicleEntity>> getVehicles() {
        return Flowable.just(mDatabase.getVehiclesDao().loadVehicles());
    }

    /* package */ void saveVehicles(List<VehicleEntity> vehicles) {
        try {
            mDatabase.beginTransaction();
            mDatabase.getVehiclesDao().deleteAllVehicles();
            mDatabase.getVehiclesDao().saveVehicles(vehicles);
            mDatabase.setTransactionSuccessful();
        } finally {
            mDatabase.endTransaction();
        }

    }
}
