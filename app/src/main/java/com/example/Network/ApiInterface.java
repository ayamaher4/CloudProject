package com.example.Network;

import com.example.model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("everything")
    Call<News> getRecent(@Query("q") String q,
                         @Query("from") String from,
                         @Query("to") String to,
                         @Query("sortBy") String sortBy,
                         @Query("apiKey") String apiKey,
                         @Query("language") String language);


}
