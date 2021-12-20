package com.filizuzulmez.turkiyefinans.view;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filizuzulmez.turkiyefinans.MainActivity;
import com.filizuzulmez.turkiyefinans.R;
import com.filizuzulmez.turkiyefinans.adapter.SectionListAdapter;
import com.filizuzulmez.turkiyefinans.model.Result;
import com.filizuzulmez.turkiyefinans.model.Section;
import com.filizuzulmez.turkiyefinans.viewModel.SearchViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;



public class SearchPageFragment extends Fragment {

    private SearchViewModel viewModel;
    private List<Section> sectionList = new ArrayList<>();
    private SectionListAdapter sectionListAdapter;
    private RecyclerView rvSectionList;
    private EditText etSearchProduct;

    private String wordToSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_page,container,false);

        rvSectionList = v.findViewById(R.id.rvSectionList);
        etSearchProduct = v.findViewById(R.id.etSearchProduct);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);


        setEdittext();


        return v;
    }

    private void setEdittext() {
        etSearchProduct.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_SEARCH){

                    wordToSearch = etSearchProduct.getText().toString();

                    if (wordToSearch != ""){

                        getSearchItem(wordToSearch);

                    }
                    handled = true;
                }
                return handled;
            }
        });
    }


    private void getSearchItem(String searchItem){

        viewModel.getSearchItem(searchItem).observe(getViewLifecycleOwner(), response ->{
            List<Result> resultList = response.getResults();
            sectionList = viewModel.setSectionList(resultList);
            setAdapter();
        });
    }

    private void setAdapter(){

        sectionListAdapter = new SectionListAdapter(getActivity(),sectionList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvSectionList.setLayoutManager(llm);
        sectionListAdapter.setCallBack(new SectionListAdapter.CallBackSection() {
            @Override
            public void onClickListItem(Result product, int position) {
                Log.d("clicked item: ",product.getArtistName());

                DetailPageFragment fragment = new DetailPageFragment();
                fragment.getData(product);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        rvSectionList.setAdapter(sectionListAdapter);
    }


}
