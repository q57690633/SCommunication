package com.huxin.communication.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.HomeEntity;
import com.huxin.communication.ui.travel.details.ZhouBianDetailsActivity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class headLineAdapter extends RecyclerView.Adapter<headLineAdapter.MyViewHoder> {

    private List<HomeEntity.HeadLineBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;
    private int type;

    public headLineAdapter(List<HomeEntity.HeadLineBean> list, Context mContext, int type) {
        this.list = list;
        this.mContext = mContext;
        this.type = type;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type == 1) {
            View view = mInflater.inflate(R.layout.item_head_line_recycler, parent, false);
            MyViewHoder hoderSell = new MyViewHoder(view);
            return hoderSell;

        }else if (type == 2){
            View view = mInflater.inflate(R.layout.item_head_rent_recycler, parent, false);
            MyViewHoder hoderRent = new MyViewHoder(view);
            return hoderRent;

        }else if (type == 3){
            View view = mInflater.inflate(R.layout.item_head_line_recycler, parent, false);
            MyViewHoder hoderqiugou = new MyViewHoder(view);
            return hoderqiugou;

        }else {
            View view = mInflater.inflate(R.layout.item_head_line_recycler, parent, false);
            MyViewHoder hoderqiuzu = new MyViewHoder(view);
            return hoderqiuzu;
        }

    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : Integer.MAX_VALUE;
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPhoto;
        private TextView mTextViewVillageName;
        private TextView mTextViewHouseType;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewAcreage;
        private TextView mTextViewTitles;

        private RecyclerView mRecyclerView;


        public MyViewHoder(View itemView) {
            super(itemView);
            if (type == 1){


//            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.zou_bian_line);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewVillageName = (TextView) itemView.findViewById(R.id.villageName);
            mTextViewHouseType = (TextView) itemView.findViewById(R.id.houseType);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.title);

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_travel);
            }
        }
    }

    private void setTextView(List<AroundTravelEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        String[] strings = list.get(position).getTagName().split(",");
        KyLog.d(list.get(position).getTagName());
        for (int i = 0; i < strings.length; i++) {
            list1.add(strings[i]);
        }
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 3);
            mAdapterTableName = new TableNameAdapter(list1, mContext);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }
}
