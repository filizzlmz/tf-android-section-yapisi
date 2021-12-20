package com.filizuzulmez.turkiyefinans.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.filizuzulmez.turkiyefinans.R;
import com.filizuzulmez.turkiyefinans.model.Result;

import org.jetbrains.annotations.NotNull;


public class DetailPageFragment extends Fragment {

    private Result mProduct;

    private ImageView ivDetail;
    private TextView tvArtistNameDetail, tvProductNameDetail;
    private Button btnUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_page,container,false);

        ivDetail = v.findViewById(R.id.ivDetail);
        tvArtistNameDetail = v.findViewById(R.id.tvArtistNameDetail);
        tvProductNameDetail = v.findViewById(R.id.tvProductNameDetail);
        btnUrl = v.findViewById(R.id.btnUrl);

        setPageDetail();

        onClickBtn();

        return v;
    }

    private void onClickBtn() {
        btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mProduct.getArtistViewUrl()!=null && mProduct.getArtistViewUrl()!=""){
                    if (mProduct.getArtistViewUrl().startsWith("https://") || mProduct.getArtistViewUrl().startsWith("http://")) {
                        Uri uri = Uri.parse(mProduct.getArtistViewUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(), "Invalid Url", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void getData(Result product){
        mProduct = product;
        Log.d("detail page: ",mProduct.getArtistName());
    }

    private void setPageDetail(){

        tvArtistNameDetail.setText(mProduct.getArtistName());
        tvProductNameDetail.setText(mProduct.getCollectionName());

        if(mProduct.getArtworkUrl30()!=""){
            setProductImage(mProduct.getArtworkUrl30(),ivDetail);
        }
        else if(mProduct.getArtworkUrl60()!=""){
            setProductImage(mProduct.getArtworkUrl60(),ivDetail);
        }
        else if(mProduct.getArtworkUrl100()!=""){
            setProductImage(mProduct.getArtworkUrl100(),ivDetail);
        }
        else{
            ivDetail.setImageResource(getActivity().getResources().getIdentifier(
                    "ic_not_found",
                    "drawable",
                    getActivity().getPackageName()
            ));
        }
    }

    public void setProductImage(String url, ImageView imageView){
        Glide.with(getActivity())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_not_found)
                .into(imageView);
    }
}
