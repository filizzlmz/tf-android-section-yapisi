package com.filizuzulmez.turkiyefinans.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.filizuzulmez.turkiyefinans.model.ResponseSearch;
import com.filizuzulmez.turkiyefinans.model.Result;
import com.filizuzulmez.turkiyefinans.model.Section;
import com.filizuzulmez.turkiyefinans.repository.SearchItemRepository;

import java.util.ArrayList;
import java.util.List;



public class SearchViewModel extends ViewModel {

    private SearchItemRepository searchItemRepository;

    public SearchViewModel(){

        searchItemRepository = new SearchItemRepository();

    }


    public LiveData<ResponseSearch> getSearchItem(String search){

        return searchItemRepository.getDataFromAPI2(search);
    }

    //wrapper types: track, artist, collection
    public List<Section> setSectionList(List<Result> searchResultList){

        List<Section> sectionList = new ArrayList<>();

        Section trackSection = new Section();
        Section artistSection = new Section();
        Section collectionSection = new Section();

        List<Result> trackResultList = new ArrayList<>();
        List<Result> artistResultList = new ArrayList<>();
        List<Result> collectionResultList = new ArrayList<>();

        if(searchResultList!=null){
            for(int i=0; i<searchResultList.size();i++){

                if(searchResultList.get(i).getWrapperType().equals("track")){
                    trackSection.setSectionName("track");
                    trackResultList.add(searchResultList.get(i));
                }
                else if(searchResultList.get(i).getWrapperType().equals("artist")){
                    artistSection.setSectionName("artist");
                    artistResultList.add(searchResultList.get(i));
                }
                else if(searchResultList.get(i).getWrapperType().equals("collection")){
                    collectionSection.setSectionName("collection");
                    collectionResultList.add(searchResultList.get(i));
                }
            }

            if(trackResultList.size()!=0){
                trackSection.setSectionItems(trackResultList);
                sectionList.add(trackSection);
            }
            if(artistResultList.size()!=0){
                artistSection.setSectionItems(artistResultList);
                sectionList.add(artistSection);
            }
            if(collectionResultList.size()!=0){
                collectionSection.setSectionItems(collectionResultList);
                sectionList.add(collectionSection);
            }

        }

        return sectionList;

    }
}
