package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.HomeTravelEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class HeadTravelJinWaiAdapter extends RecyclerView.Adapter<HeadTravelJinWaiAdapter.MyViewHoder>{


    private List<HomeTravelEntity.ForeignHeadBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;
    private int type;


    public HeadTravelJinWaiAdapter(List<HomeTravelEntity.ForeignHeadBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_travel_xianlu_recycler, parent, false);
        MyViewHoder hoderSell = new MyViewHoder(view);
        return hoderSell;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextViewTitles.setText(list.get(position).getTravel_title());
        holder.mTextViewchufadi.setText(list.get(position). getDepart_name());
        holder.mTextViewmudidi.setText(list.get(position).getGoals_city());
        holder.mTextViewTotalPrice.setText(String.valueOf(list.get(position).getTotal_price()));
        holder.mTextViewnNmdays.setText(String.valueOf(list.get(position).getNumber_days()));
        holder.mTextViewReturnPrice.setText(String.valueOf(list.get(position).getReturn_price()));


        ImageLoader.getInstance().displayImage(list.get(position).getHeadUrl(), holder.mImageViewPhoto);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : Integer.MAX_VALUE;
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPhoto;
        private TextView mTextViewchufadi;
        private TextView mTextViewmudidi;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewnNmdays;
        private TextView mTextViewTitles;

        private TextView mTextViewReturnPrice;


        public MyViewHoder(View itemView) {
            super(itemView);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.travelTitle);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewchufadi = (TextView) itemView.findViewById(R.id.chufadi);
            mTextViewmudidi = (TextView) itemView.findViewById(R.id.mudidi);
            mTextViewnNmdays = (TextView) itemView.findViewById(R.id.numdays);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewReturnPrice = itemView.findViewById(R.id.returnPrice);


        }
    }
}
