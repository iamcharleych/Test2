package com.chaplin.test1.data.networking.core;

public abstract class RestClient<RESPONSE> {

    public abstract RESPONSE execute(DataRequest request);
}
