package com.chaplin.test1.data.networking.client;

import com.chaplin.test1.data.networking.core.DataRequest;

public class Requests {

    /**
     *  available api operations
     *
     *  Please, for new operations use format: OP_(GET|POST|PUT|...)_<Operation name>
     */

    public static final String OP_GET_VEHICLES = "op_get_vehicles";

    public static DataRequest getVehicles() {
        return new DataRequest(OP_GET_VEHICLES);
    }
}
