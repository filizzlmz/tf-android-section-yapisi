package com.filizuzulmez.turkiyefinans.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filizuzulmez.turkiyefinans.R;
import com.filizuzulmez.turkiyefinans.model.Result;
import com.filizuzulmez.turkiyefinans.model.Section;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionListAdapter extends RecyclerView.Adapter<SectionListAdapter.ListSectionHolder> {

    private final int RES_ITEM_ROW_SECTION_LIST = R.layout.item_row_section_list;

    private List<Section> mSectionList;
    private Activity mActivity;
    private LayoutInflater mInflater;
    private LinearLayoutManager mLayoutManager;

    private ProductListAdapter mProductListAdapter;

    private CallBackSection mCallBack;

    public SectionListAdapter(Activity activity, List<Section> sectionList){


        this.mSectionList = sectionList;
        this.mActivity = activity;
        this.mInflater = LayoutInflater.from(mActivity);
        //this.mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);


    }

    @NonNull
    @NotNull
    @Override
    public ListSectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = mInflater.inflate(RES_ITEM_ROW_SECTION_LIST, parent, false);

        ListSectionHolder holder = new ListSectionHolder(item);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListSectionHolder holder, int position) {
        Section section = mSectionList.get(position);


        ((ListSectionHolder) holder).onBind(section, position);
    }

    @Override
    public int getItemCount() {
        return mSectionList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ListSectionHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvSectionName)
        TextView tvSectionName;

        RecyclerView rvProductList;

        View mItemView;

        public ListSectionHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemView = itemView;
            rvProductList = mItemView.findViewById(R.id.rvProductList);

        }

        public void onBind(Section section, int position) {
            tvSectionName.setText(section.getSectionName());

            mProductListAdapter = new ProductListAdapter(mActivity,section.getSectionItems());
            mProductListAdapter.setCallBack(new ProductListAdapter.CallBack() {
                @Override
                public void onClickProduct(Result product, int positionProduct) {

                    if(mCallBack!=null){
                        mCallBack.onClickListItem(product,positionProduct);
                    }
                }
            });
            rvProductList.setAdapter(mProductListAdapter);
        }


    }
    public void setCallBack(CallBackSection callBack){
        mCallBack=callBack;

    }

    public interface CallBackSection {
        void onClickListItem(Result product, int position);
    }



}
