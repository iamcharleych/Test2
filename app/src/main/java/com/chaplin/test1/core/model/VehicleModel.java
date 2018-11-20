package com.chaplin.test1.core.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class VehicleModel implements ClusterItem {

    private String mVin;
    private String mName;
    private String mAddress;
    private LatLng mLocation;

    public String getVin() {
        return mVin;
    }

    public void setVin(String vin) {
        mVin = vin;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public LatLng getLocation() {
        return mLocation;
    }

    public void setLocation(LatLng location) {
        mLocation = location;
    }

    @Override
    public LatLng getPosition() {
        return mLocation;
    }

    @Override
    public String getTitle() {
        return mName;
    }

    @Override
    public String getSnippet() {
        return mAddress;
    }
}
