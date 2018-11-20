package com.chaplin.test1.data.repository.datasource;

import androidx.annotation.NonNull;
import com.chaplin.test1.common.pref.LongPref;
import com.chaplin.test1.common.utils.SystemTimeProvider;
import com.chaplin.test1.data.entity.VehicleEntity;
import com.chaplin.test1.data.networking.client.Requests;
import com.chaplin.test1.data.networking.core.DataResponse;
import com.chaplin.test1.data.networking.core.RestClient;
import io.reactivex.Flowable;

import java.util.List;

public class NetVehiclesDataSource implements VehiclesDataSource {

    @NonNull
    private final RestClient<Flowable<DataResponse>> mRestClient;
    @NonNull
    private final DatabaseVehiclesDataSource mDatabaseDataSource;
    @NonNull
    private final LongPref mLastCacheTime;

    public NetVehiclesDataSource(@NonNull RestClient<Flowable<DataResponse>> restClient,
                                 @NonNull DatabaseVehiclesDataSource databaseDataSource,
                                 @NonNull LongPref lastCacheTime) {
        mRestClient = restClient;
        mDatabaseDataSource = databaseDataSource;
        mLastCacheTime = lastCacheTime;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Flowable<List<VehicleEntity>> getVehicles() {
        return mRestClient.execute(Requests.getVehicles())
                .map(dataResponse -> {
                    mLastCacheTime.set(SystemTimeProvider.currentTimeMillis());
                    return (List<VehicleEntity>) dataResponse.responseObject;
                })
                .doOnNext(mDatabaseDataSource::saveVehicles);
    }
}
