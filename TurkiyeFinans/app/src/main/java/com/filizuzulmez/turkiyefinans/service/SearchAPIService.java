package com.filizuzulmez.turkiyefinans.service;

import com.filizuzulmez.turkiyefinans.model.ResponseSearch;

import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchAPIService {

    private static final String BASE_URL = "https://itunes.apple.com/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static SearchAPI getInterface() {
        return retrofit.create(SearchAPI.class);
    }


    public static Call<ResponseSearch> getData(String search){
        return getInterface().getData(search);
    }

    public static Retrofit getRetrofit(){
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit1;
    }
}
