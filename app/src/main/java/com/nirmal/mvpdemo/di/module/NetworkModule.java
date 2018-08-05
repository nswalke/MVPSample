package com.nirmal.mvpdemo.di.module;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nirmal.mvpdemo.BuildConfig;
import com.nirmal.mvpdemo.di.qualifier.RestApiDateTimeFormat;
import com.nirmal.mvpdemo.di.scope.ApplicationScope;
import com.nirmal.mvpdemo.network.RestApiService;

import java.io.IOException;
import java.util.HashMap;

import dagger.Module;
import dagger.Provides;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {ContextModule.class, DateTimeFormatModule.class})
public class NetworkModule {

    @Provides
    @ApplicationScope
    public RestApiService restApiService(Retrofit retrofit) {
        return retrofit.create(RestApiService.class);
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(Interceptor interceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @ApplicationScope
    public Interceptor interceptor(final HashMap<String, String> headers) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder().headers(Headers.of(headers));
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        };
    }

    @Provides
    @ApplicationScope
    public HashMap<String, String> headers() {
        String base64EncodedCredentials = "Basic " + Base64.encodeToString(("user" + ":" + "pwd").getBytes(), Base64.NO_WRAP);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", base64EncodedCredentials);
        return headers;
    }

    @Provides
    @ApplicationScope
    public Gson gson(@RestApiDateTimeFormat String restApiDateTimeFormat) {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(restApiDateTimeFormat)
                .create();
    }
}
