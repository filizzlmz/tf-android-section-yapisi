package com.filizuzulmez.turkiyefinans.adapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.filizuzulmez.turkiyefinans.R;
import com.filizuzulmez.turkiyefinans.model.Result;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int RES_ITEM_ROW_PRODUCTS = R.layout.item_row_product_list;
    private List<Result> mProductList = new ArrayList<>();
    private Activity mActivity;
    private CallBack mCallBack;

    private LayoutInflater mInflater;
    private GridLayoutManager mLayoutManager;

    public ProductListAdapter(Activity context, List<Result> productList){
        this.mProductList = productList;
        this.mActivity = context;
        this.mInflater = LayoutInflater.from(mActivity);
        this.mLayoutManager = new GridLayoutManager(mActivity, 2);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(RES_ITEM_ROW_PRODUCTS, parent, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;


        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) itemView.getLayoutParams();
        layoutParams.height = (int) ((screenWidth / 2) * 1.3);
        itemView.setLayoutParams(layoutParams);

        ProductViewHolder holder = new ProductViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Result Product = mProductList.get(position);

        ((ProductViewHolder) holder).onBind(Product, position);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivProductImage)
        ImageView ivProductImage;

        @BindView(R.id.tvPrimaryGenreName)
        TextView tvPrimaryGenreName;

        @BindView(R.id.tvArtistName)
        TextView tvArtistName;

        @BindView(R.id.tvProductName)
        TextView tvProductName;

        @BindView(R.id.tvPrice)
        TextView tvPrice;

        View mItemView;

        public ProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemView = itemView;
        }

        public void onBind(Result product, int position) {

            if(product.getArtworkUrl30()!=""){
                setProductImage(product.getArtworkUrl30(),mActivity,ivProductImage);
            }
            else if(product.getArtworkUrl60()!=""){
                setProductImage(product.getArtworkUrl60(),mActivity,ivProductImage);
            }
            else if(product.getArtworkUrl100()!=""){
                setProductImage(product.getArtworkUrl100(),mActivity,ivProductImage);
            }
            else{
                ivProductImage.setImageResource(mActivity.getResources().getIdentifier(
                        "ic_not_found",
                        "drawable",
                        mActivity.getPackageName()
                ));
            }

            tvPrimaryGenreName.setText(product.getPrimaryGenreName());
            tvArtistName.setText(product.getArtistName());
            tvProductName.setText(product.getCollectionName());

            if(product.getCollectionPrice()!=null){
                String price = mActivity.getResources().getString(R.string.tv_price, product.getCollectionPrice().toString());

                tvPrice.setText(price);
            }



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mCallBack!=null){
                        mCallBack.onClickProduct(product,position);
                    }
                }
            });
        }
    }

    public void setCallBack(CallBack callBack){
        mCallBack=callBack;

    }

    public interface CallBack {
        void onClickProduct(Result product, int position);
    }

    public void setProductImage(String url, Activity context, ImageView imageView){
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_not_found)
                .into(imageView);
    }
}
