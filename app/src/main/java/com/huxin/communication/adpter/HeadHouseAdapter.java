package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.HomeEntity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class HeadHouseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //设置常量
    private static final int TYPE_SELL = 1;
    private static final int TYPE_RENT = 2;
    private static final int TYPE_QIUGOU = 3;
    private static final int TYPE_QIUZU = 4;

    private List<HomeEntity.HeadLineBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;
    private int type;


    public HeadHouseAdapter(List<HomeEntity.HeadLineBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 根据不同的position，设置不同的ViewType
     * position表示当前是第几个Item，通过position拿到当前的Item对象，然后判断这个item对象需要那种视图
     */
    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getProductType() == 1) {
            return TYPE_SELL;
        } else if (list.get(position).getProductType() == 2) {
            return TYPE_RENT;
        } else if (list.get(position).getProductType() == 3) {
            return TYPE_QIUGOU;
        } else if (list.get(position).getProductType() == 4) {
            return TYPE_QIUZU;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_SELL) {
            View view = mInflater.inflate(R.layout.item_head_line_recycler, parent, false);
            MyViewHoder hoderSell = new MyViewHoder(view);
            return hoderSell;
        } else if (viewType == TYPE_RENT) {
            View view = mInflater.inflate(R.layout.item_head_rent_recycler, parent, false);
            MyRentViewHoder hoderRent = new MyRentViewHoder(view);
            return hoderRent;
        } else if (viewType == TYPE_QIUGOU) {
            View view = mInflater.inflate(R.layout.item_head_qiugou_recycler, parent, false);
            MyQiuGouViewHoder hoderQiuGou = new MyQiuGouViewHoder(view);
            return hoderQiuGou;
        } else if (viewType == TYPE_QIUZU) {
            View view = mInflater.inflate(R.layout.item_head_qiuzu_recycler, parent, false);
            MyQiuZhuViewHoder hoderQiuZhu = new MyQiuZhuViewHoder(view);
            return hoderQiuZhu;

        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHoder) {
            ((MyViewHoder) holder).mTextViewTitles.setText(list.get(position).getTitle());
            ((MyViewHoder) holder).mTextViewVillageName.setText(list.get(position).getVillageName());
            ((MyViewHoder) holder).mTextViewHouseType.setText(list.get(position).getHouseType());
            ((MyViewHoder) holder).mTextViewTotalPrice.setText(String.valueOf(list.get(position).getMaxPrice()));
            ((MyViewHoder) holder).mTextViewAcreage.setText(String.valueOf(list.get(position).getAcreage()));
            ImageLoader.getInstance().displayImage(list.get(position).getUserModel().getHeadUrl(), ((MyViewHoder) holder).mImageViewPhoto);
            setTextView(list, position, ((MyViewHoder) holder).mRecyclerView);
        } else if (holder instanceof MyRentViewHoder) {
            ((MyRentViewHoder) holder).mTextViewTitles.setText(list.get(position).getRemark());
            ((MyRentViewHoder) holder).mTextViewVillageName.setText(list.get(position).getVillageName());
            ((MyRentViewHoder) holder).mTextViewHouseType.setText(list.get(position).getHouseType());
            ((MyRentViewHoder) holder).mTextViewTotalPrice.setText(String.valueOf(list.get(position).getMaxPrice()));
//            ((MyRentViewHoder) holder).mTextViewAcreage.setText(String.valueOf(list.get(position).getAcreage()));
            ((MyRentViewHoder) holder).mTextViewHouseHoldAppliances.setText(list.get(position).getHouseHoldAppliances());
            ((MyRentViewHoder) holder).mTextViewFitment.setText(list.get(position).getFitment());
            ImageLoader.getInstance().displayImage(list.get(position).getUserModel().getHeadUrl(), ((MyRentViewHoder) holder).mImageViewPhoto);
            setTextView(list, position, ((MyRentViewHoder) holder).mRecyclerView);
        } else if (holder instanceof MyQiuGouViewHoder) {
            ((MyQiuGouViewHoder) holder).mTextViewFloorAge.setText(list.get(position).getFloorAge());
            ((MyQiuGouViewHoder) holder).mTextViewTitles.setText(list.get(position).getRemark());
            ((MyQiuGouViewHoder) holder).mTextViewVillageName.setText(list.get(position).getVillageName());
            ((MyQiuGouViewHoder) holder).mTextViewHouseType.setText(list.get(position).getHouseType());
            ((MyQiuGouViewHoder) holder).mTextViewTotalPrice.setText(String.valueOf(list.get(position).getMaxPrice()));
            ((MyQiuGouViewHoder) holder).mTextViewAcreage.setText(String.valueOf(list.get(position).getAcreage()));
            ImageLoader.getInstance().displayImage(list.get(position).getUserModel().getHeadUrl(), ((MyQiuGouViewHoder) holder).mImageViewPhoto);

//            ((MyQiuGouViewHoder) holder).mTextViewHouseHoldAppliances.setText(list.get(position).getHouseHoldAppliances());
        } else if (holder instanceof MyQiuZhuViewHoder) {
            ((MyQiuZhuViewHoder) holder).mTextViewTitles.setText(list.get(position).getRemark());
            ((MyQiuZhuViewHoder) holder).mTextViewVillageName.setText(list.get(position).getVillageName());
            ((MyQiuZhuViewHoder) holder).mTextViewHouseType.setText(list.get(position).getHouseType());
            ((MyQiuZhuViewHoder) holder).mTextViewTotalPrice.setText(String.valueOf(list.get(position).getMaxPrice()));
            ((MyQiuZhuViewHoder) holder).mTextViewAcreage.setText(String.valueOf(list.get(position).getAcreage()));
            ImageLoader.getInstance().displayImage(list.get(position).getUserModel().getHeadUrl(), ((MyQiuZhuViewHoder) holder).mImageViewPhoto);

//            ((MyQiuZhuViewHoder) holder).mTextViewHouseHoldAppliances.setText(list.get(position).getHouseHoldAppliances());
        }

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
            mTextViewTitles = (TextView) itemView.findViewById(R.id.title);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewVillageName = (TextView) itemView.findViewById(R.id.villageName);
            mTextViewHouseType = (TextView) itemView.findViewById(R.id.houseType);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.table_name);

        }
    }

    class MyRentViewHoder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPhoto;
        private TextView mTextViewVillageName;
        private TextView mTextViewHouseType;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewAcreage;
        private TextView mTextViewTitles;
        private TextView mTextViewHouseHoldAppliances;
        private TextView mTextViewFitment;
        private TextView mTextViewFloorAge;

        private RecyclerView mRecyclerView;


        public MyRentViewHoder(View itemView) {
            super(itemView);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.title);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewVillageName = (TextView) itemView.findViewById(R.id.villageName);
            mTextViewHouseType = (TextView) itemView.findViewById(R.id.houseType);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewHouseHoldAppliances = itemView.findViewById(R.id.houseHoldAppliances);
            mTextViewFitment = itemView.findViewById(R.id.fitment);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.table_name);


        }
    }

    class MyQiuGouViewHoder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPhoto;
        private TextView mTextViewVillageName;
        private TextView mTextViewHouseType;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewAcreage;
        private TextView mTextViewTitles;
        private TextView mTextViewHouseHoldAppliances;
        private TextView mTextViewFitment;
        private TextView mTextViewFloorAge;

        private RecyclerView mRecyclerView;


        public MyQiuGouViewHoder(View itemView) {
            super(itemView);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.title);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewVillageName = (TextView) itemView.findViewById(R.id.villageName);
            mTextViewHouseType = (TextView) itemView.findViewById(R.id.houseType);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage);
            mTextViewFloorAge = (TextView) itemView.findViewById(R.id.floorAge);

        }
    }


    class MyQiuZhuViewHoder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPhoto;
        private TextView mTextViewVillageName;
        private TextView mTextViewHouseType;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewAcreage;
        private TextView mTextViewTitles;
        private TextView mTextViewHouseHoldAppliances;
        private TextView mTextViewFitment;
        private TextView mTextViewFloorAge;

        private RecyclerView mRecyclerView;


        public MyQiuZhuViewHoder(View itemView) {
            super(itemView);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.title);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewVillageName = (TextView) itemView.findViewById(R.id.villageName);
            mTextViewHouseType = (TextView) itemView.findViewById(R.id.houseType);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage);


        }
    }

    private void setTextView(List<HomeEntity.HeadLineBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        String[] strings = list.get(position).getTabName().split(",");
        KyLog.d(list.get(position).getTabName());
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
