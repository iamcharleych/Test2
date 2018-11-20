package com.chaplin.test1.core.di.module;

import com.chaplin.test1.App;
import com.chaplin.test1.BuildConfig;
import com.chaplin.test1.data.entity.VehicleEntity;
import com.chaplin.test1.data.entity.serializer.VehicleEntityListSerializer;
import com.chaplin.test1.data.entity.serializer.VehicleEntitySerializer;
import com.chaplin.test1.data.networking.client.RetrofitRestClient;
import com.chaplin.test1.data.networking.client.RxErrorHandlingCallAdapterFactory;
import com.chaplin.test1.data.networking.core.DataResponse;
import com.chaplin.test1.data.networking.core.RestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;
import java.util.List;

@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    public static Cache provideOkHttpCache(App application) {
        int cacheSize = 5 * 1024 * 1024; // 5 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    public static Gson provideGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .registerTypeAdapter(VehicleEntity.class, new VehicleEntitySerializer())
                .registerTypeAdapter(new TypeToken<List<VehicleEntity>>(){}.getType(), new VehicleEntityListSerializer())
                .create();
    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.cache(cache);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public static RestClient<Flowable<DataResponse>> provideRestClient(Retrofit retrofit) {
        return new RetrofitRestClient(retrofit);
    }
}
