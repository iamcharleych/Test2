package com.chaplin.test1.domain.model;

public class Vehicle {

    private String mAddress;
    private String mEngineType;
    private String mExterior;
    private String mInterior;
    private String mName;
    private String mVin;
    private int mFuel;
    private Coordinate mCoordinates;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getEngineType() {
        return mEngineType;
    }

    public void setEngineType(String engineType) {
        mEngineType = engineType;
    }

    public String getExterior() {
        return mExterior;
    }

    public void setExterior(String exterior) {
        mExterior = exterior;
    }

    public String getInterior() {
        return mInterior;
    }

    public void setInterior(String interior) {
        mInterior = interior;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getVin() {
        return mVin;
    }

    public void setVin(String vin) {
        mVin = vin;
    }

    public int getFuel() {
        return mFuel;
    }

    public void setFuel(int fuel) {
        mFuel = fuel;
    }

    public Coordinate getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        mCoordinates = coordinates;
    }
}
