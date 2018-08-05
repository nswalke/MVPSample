package com.nirmal.mvpdemo.network;

import com.nirmal.mvpdemo.network.response.SearchItemsApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiService {

    @GET("/search")
    Observable<SearchItemsApiResponse> searchItems(@Query("text") String searchQueryText);
}
