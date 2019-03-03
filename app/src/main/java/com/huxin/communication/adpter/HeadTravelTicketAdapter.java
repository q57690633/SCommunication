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

public class HeadTravelTicketAdapter extends RecyclerView.Adapter<HeadTravelTicketAdapter.MyViewHoder>{
    private List<HomeTravelEntity.TicketHeadBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;
    private int type;


    public HeadTravelTicketAdapter(List<HomeTravelEntity.TicketHeadBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_travel_xianlu_recycler, parent, false);
        MyViewHoder hoderSell = new MyViewHoder(view);
        return hoderSell;
//        else if (viewType == TYPE_RENT) {
//            View view = mInflater.inflate(R.layout.item_ticketing_recycler, parent, false);
//            MyRentViewHoder hoderRent = new MyRentViewHoder(view);
//            return hoderRent;
//        }

    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextViewTitles.setText(list.get(position).getTicket_name());
        holder.mTextViewTotalPrice.setText(String.valueOf(list.get(position).getOriginal_price()));
        holder.mTextViewAddress.setText(String.valueOf(list.get(position).getTicket_city_name()));
        holder.mTextViewReturnPrice.setText(String.valueOf(list.get(position).getFinal_price()));


        ImageLoader.getInstance().displayImage(list.get(position).getHeadUrl(), holder.mImageViewPhoto);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : Integer.MAX_VALUE;
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPhoto;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewAddress;
        private TextView mTextViewTitles;

        private TextView mTextViewReturnPrice;



        public MyViewHoder(View itemView) {
            super(itemView);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.travelTitle);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewAddress = (TextView) itemView.findViewById(R.id.address);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewReturnPrice = itemView.findViewById(R.id.returnPrice);


        }
    }
}
