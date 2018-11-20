package com.chaplin.test1.data.networking.client;

import com.chaplin.test1.data.entity.VehicleEntity;
import com.chaplin.test1.data.networking.core.DataRequest;
import com.chaplin.test1.data.networking.core.DataResponse;
import com.chaplin.test1.data.networking.core.RestClient;
import io.reactivex.Flowable;
import retrofit2.Retrofit;

import java.util.List;

public class RetrofitRestClient extends RestClient<Flowable<DataResponse>> {

    private API mApi;

    public RetrofitRestClient(Retrofit retrofit) {
        mApi = retrofit.create(API.class);
    }

    public Flowable<DataResponse> execute(DataRequest request) {
        final String operation = request.getOperation();

        Flowable<DataResponse> resultObservable = null;
        switch (operation) {
            case Requests.OP_GET_VEHICLES:
                resultObservable = mApi.getVehicles().map(list -> {
                    DataResponse<List<VehicleEntity>> dataResponse = new DataResponse<>();

                    dataResponse.request = request;
                    dataResponse.responseObject = list;
                    dataResponse.code = 200;

                    return dataResponse;
                });
                break;

            case DataRequest.NO_OPERATION:
                // fall-through
            default:
                break;
        }
        if (resultObservable != null) {
            return resultObservable.map(dataResponse -> {
                dataResponse.request = request;
                return dataResponse;
            });
        }

        return Flowable.error(new Exception("Invalid Api operation!"));
    }

}
