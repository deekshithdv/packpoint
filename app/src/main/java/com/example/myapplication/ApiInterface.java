package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("place/queryautocomplete/json")
    Call<MainPojo>getplace(@Query("input") String text,
                   @Query("key") String key);
}
