package com.chaplin.test2.data.networking.core;

public abstract class RestClient<RESPONSE> {

    public abstract RESPONSE execute(DataRequest request);
}
