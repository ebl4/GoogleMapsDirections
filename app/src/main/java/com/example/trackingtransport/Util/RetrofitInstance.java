package com.example.trackingtransport.Util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitInstance class implements a retrofit instance
 * that manage network connections to the web service
 */
public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String stringJSON = "https://maps.googleapis.com/maps/api/";

    /**
     * Generate a new Retrofit instance for RestFull Web Service conection
     * @return
     */
    public static Retrofit getRetrofitInstance(){
        if(retrofit==null) {
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(loggingInterceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl(stringJSON)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build();


        }
        return retrofit;
    }
}
