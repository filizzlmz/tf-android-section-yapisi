package com.filizuzulmez.turkiyefinans.service;

import com.filizuzulmez.turkiyefinans.model.ResponseSearch;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchAPI {

    //https://itunes.apple.com/search?term=jack+johnson&limit=25

    @GET("search?")
    Call<ResponseSearch> getData(@Query("term") String search);
}
