package com.chaplin.test1.data.repository.datasource;

import androidx.annotation.NonNull;
import com.chaplin.test1.common.pref.LongPref;
import com.chaplin.test1.common.utils.SystemTimeProvider;
import com.chaplin.test1.data.db.AppDatabase;
import com.chaplin.test1.data.networking.core.DataResponse;
import com.chaplin.test1.data.networking.core.RestClient;
import io.reactivex.Flowable;

public class VehiclesDataSourceFactory {

    private static final long CACHE_EXPIRATION_TIME = 5 * 60 * 1000; // 5 min

    @NonNull
    private final RestClient<Flowable<DataResponse>> mRestClient;
    @NonNull
    private final AppDatabase mDatabase;
    @NonNull
    private final LongPref mLastCacheTime;

    public VehiclesDataSourceFactory(@NonNull RestClient<Flowable<DataResponse>> restClient,
                                     @NonNull AppDatabase database,
                                     @NonNull LongPref lastCacheTime) {
        mRestClient = restClient;
        mDatabase = database;
        mLastCacheTime = lastCacheTime;
    }

    public VehiclesDataSource create() {
        final long elapsedTime = SystemTimeProvider.currentTimeMillis() - mLastCacheTime.get();

        DatabaseVehiclesDataSource databaseVehiclesDataSource = new DatabaseVehiclesDataSource(mDatabase);

        if (elapsedTime < CACHE_EXPIRATION_TIME) {
            return databaseVehiclesDataSource;
        }

        return new NetVehiclesDataSource(mRestClient, databaseVehiclesDataSource, mLastCacheTime);
    }
}
