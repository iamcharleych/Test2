package com.chaplin.test1.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.chaplin.test1.data.entity.serializer.VehicleEntitySerializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "vehicles")
public class VehicleEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "vin")
    @SerializedName("vin")
    private String mVin;

    @ColumnInfo(name = "address")
    @SerializedName("address")
    private String mAddress;

    @ColumnInfo(name = "engineType")
    @SerializedName("engineType")
    private String mEngineType;

    @ColumnInfo(name = "exterior")
    @SerializedName("exterior")
    private String mExterior;

    @ColumnInfo(name = "interior")
    @SerializedName("interior")
    private String mInterior;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String mName;

    @ColumnInfo(name = "fuel")
    @SerializedName("fuel")
    private int mFuel;

    @ColumnInfo(name = "latitude")
    private transient double mLatitude;

    @ColumnInfo(name = "longitude")
    private transient double mLongitude;

    public String getVin() {
        return mVin;
    }

    public void setVin(String vin) {
        mVin = vin;
    }

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

    public int getFuel() {
        return mFuel;
    }

    public void setFuel(int fuel) {
        mFuel = fuel;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }
}
