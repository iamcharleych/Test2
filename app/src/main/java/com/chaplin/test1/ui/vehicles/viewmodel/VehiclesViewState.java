package com.chaplin.test1.ui.vehicles.viewmodel;

import com.chaplin.test1.core.model.VehicleModel;

import java.util.List;

public class VehiclesViewState {

    static VehiclesViewState idle() {
        return new VehiclesViewState(true, false, null, null);
    }

    static VehiclesViewState loading() {
        return new VehiclesViewState(false,true, null, null);
    }

    static VehiclesViewState success(List<VehicleModel> vehicles) {
        return new VehiclesViewState(false,false, vehicles, null);
    }

    static VehiclesViewState error(Throwable error) {
        return new VehiclesViewState(false,false, null, error);
    }

    private boolean mIsLoading;
    private boolean mIsIdle;
    private List<VehicleModel> mVehicles;
    private Throwable mError;

    private VehiclesViewState(boolean isIdle, boolean isLoading, List<VehicleModel> vehicles, Throwable error) {
        mIsIdle = isIdle;
        mIsLoading = isLoading;
        mVehicles = vehicles;
        mError = error;
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    public boolean isIdle() {
        return mIsIdle;
    }

    public List<VehicleModel> getVehicles() {
        return mVehicles;
    }

    public Throwable getError() {
        return mError;
    }
}
