package com.chaplin.test2.data.networking.core;

public class DataResponse<T> {

    public transient DataRequest request;

    public int code;

    public T responseObject;

}
