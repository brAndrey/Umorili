package com.example.umorili.api;

import com.example.umorili.model.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UmoriliApi {

    @GET("/api/get")
    Call<List<PostModel>> getData(@Query("name") String resourseName, @Query("num") int count);


    @GET("/api/get")
    Observable<List<PostModel>> getPostModel(@Query("name") String resourseName, @Query("num") int count);


}
