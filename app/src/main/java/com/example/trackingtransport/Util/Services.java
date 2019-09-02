package com.example.trackingtransport.Util;


import android.text.Layout;

import com.google.maps.model.DirectionsResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Services {

    @GET("directions/json")
    Call<DirectionsResult> doRegister(@Query("origin") String origin,
                                      @Query("destination") String destination,
                                      @Query("key") String key);
}
