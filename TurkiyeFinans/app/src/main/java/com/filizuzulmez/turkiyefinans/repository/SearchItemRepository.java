package com.filizuzulmez.turkiyefinans.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.filizuzulmez.turkiyefinans.model.ResponseSearch;
import com.filizuzulmez.turkiyefinans.service.SearchAPI;
import com.filizuzulmez.turkiyefinans.service.SearchAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchItemRepository {
    private SearchAPI searchAPI;

    public SearchItemRepository(){
        searchAPI = SearchAPIService.getRetrofit().create(SearchAPI.class);
    }


    public LiveData<ResponseSearch> getDataFromAPI2(String searchData) {
        //searchAPI = SearchAPIService.getData().create(SearchAPI.class);

        MutableLiveData<ResponseSearch> searchItem= new MutableLiveData<>();

        //searchAPI = SearchAPIService.getRetrofit().create(SearchAPI.class);
        Call<ResponseSearch> call = searchAPI.getData(searchData);
        call.enqueue(new Callback<ResponseSearch>() {
            @Override
            public void onResponse(Call<ResponseSearch> call, Response<ResponseSearch> response) {

                searchItem.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseSearch> call, Throwable t) {

                searchItem.postValue(null);
            }
        });

        return searchItem;
    }
}
