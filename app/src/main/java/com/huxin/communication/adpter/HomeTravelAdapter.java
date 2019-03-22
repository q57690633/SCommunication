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
import com.huxin.communication.entity.HeadTravelEntivty;
import com.huxin.communication.entity.HomeEntity;
import com.huxin.communication.entity.HomeTravelEntity;
import com.huxin.communication.ui.travel.details.ZhouBianDetailsActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class HomeTravelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //设置常量
    private static final int TYPE_SELL = 1;
    private static final int TYPE_RENT = 2;
    private static final int TYPE_TICKET = 3;


    private List<HeadTravelEntivty> list;
    private Context mContext;
    private LayoutInflater mInflater;


    public HomeTravelAdapter(List<HeadTravelEntivty> list, Context mContext) {
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
        KyLog.d(list.get(position % list.size()).getProductType() + "a");
//        KyLog.d(list.get(position).getProductType() + "a");

        if (position < list.size()) {
            if (list.get(position % list.size()).getProductType() == 1) {
                return TYPE_SELL;
            } else if (list.get(position % list.size()).getProductType() == 2) {
                return TYPE_RENT;
            }else if (list.get(position % list.size()).getProductType() == 3) {
                return TYPE_TICKET;
            }
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_SELL) {
            View view = mInflater.inflate(R.layout.item_travel_xianlu_recycler, parent, false);
            MyViewHoder hoderSell = new MyViewHoder(view);
            hoderSell.mTextViewGuonei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,ZhouBianDetailsActivity.class);
                    intent.putExtra("headlist", list.get(hoderSell.getAdapterPosition()));
                    intent.putExtra("type",1);
                    mContext.startActivity(intent);
                }
            });
            return hoderSell;
        }else if (viewType == TYPE_RENT) {
            View view = mInflater.inflate(R.layout.item_travel_xianlu_recycler, parent, false);
            MyJinWaiViewHoder hoderRent = new MyJinWaiViewHoder(view);
            hoderRent.mTextViewGuonei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return hoderRent;
        } else if (viewType == TYPE_TICKET) {
            View view = mInflater.inflate(R.layout.item_travel_ticket_recycelr, parent, false);
            MyRentViewHoder hoderTicket = new MyRentViewHoder(view);
            return hoderTicket;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHoder) {
            ((MyViewHoder) holder).mTextViewTitles.setText(list.get(position % list.size()).getTravelTitle());
            ((MyViewHoder) holder).mTextViewchufadi.setText(list.get(position % list.size()).getDepart_name());
            ((MyViewHoder) holder).mTextViewmudidi.setText("至" + list.get(position % list.size()).getGoals_city());
            ((MyViewHoder) holder).mTextViewTotalPrice.setText("成人：" + String.valueOf(list.get(position % list.size()).getTotalPrice()) + "元/人");
            ((MyViewHoder) holder).mTextViewnNmdays.setText(String.valueOf(list.get(position % list.size()).getNumberDays()) + "天");
            ((MyViewHoder) holder).mTextViewReturnPrice.setText("返佣：" + String.valueOf(list.get(position % list.size()).getNumberDays())+ "元/人");
            ImageLoader.getInstance().displayImage(list.get(position % list.size()).getHeadUrl(), ((MyViewHoder) holder).mImageViewPhoto);
        }if (holder instanceof MyJinWaiViewHoder) {
            ((MyJinWaiViewHoder) holder).mTextViewTitles.setText(list.get(position % list.size()).getTravelTitle());
            ((MyJinWaiViewHoder) holder).mTextViewchufadi.setText(list.get(position % list.size()).getDepart_name());
            ((MyJinWaiViewHoder) holder).mTextViewmudidi.setText("至" + list.get(position % list.size()).getGoals_nat_name());
            ((MyJinWaiViewHoder) holder).mTextViewTotalPrice.setText("成人：" + String.valueOf(list.get(position % list.size()).getTotalPrice()) + "元/人");
            ((MyJinWaiViewHoder) holder).mTextViewReturnPrice.setText("返佣：" + String.valueOf(list.get(position % list.size()).getNumberDays())+ "元/人");

            ((MyJinWaiViewHoder) holder).mTextViewnNmdays.setText(String.valueOf(list.get(position % list.size()).getNumberDays()) + "天");
            ImageLoader.getInstance().displayImage(list.get(position % list.size()).getHeadUrl(), ((MyJinWaiViewHoder) holder).mImageViewPhoto);
        } else if (holder instanceof MyRentViewHoder) {
            ((MyRentViewHoder) holder).mTextViewTitles.setText(list.get(position % list.size()).getTicket_name());
            ((MyRentViewHoder) holder).mTextViewTotalPrice.setText("成人：" + String.valueOf(list.get(position % list.size()).getOriginal_price()) + "元/人");
            ((MyRentViewHoder) holder).mTextViewAddress.setText(list.get(position % list.size()).getTicket_city_name());
            ((MyRentViewHoder) holder).mTextViewReturnPrice.setText(String.valueOf("返佣：" + list.get(position % list.size()).getFinal_price()) + "元/人");
            ImageLoader.getInstance().displayImage(list.get(position % list.size()).getHeadUrl(), ((MyRentViewHoder) holder).mImageViewPhoto);
//            setTextView(list, position % list.size(), ((MyRentViewHoder) holder).mRecyclerView);
        }

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPhoto;
        private TextView mTextViewchufadi;
        private TextView mTextViewmudidi;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewnNmdays;
        private TextView mTextViewTitles;
        private TextView mTextViewReturnPrice;
        private LinearLayout mTextViewGuonei;


        public MyViewHoder(View itemView) {
            super(itemView);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.travelTitle);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewchufadi = (TextView) itemView.findViewById(R.id.chufadi);
            mTextViewmudidi = (TextView) itemView.findViewById(R.id.mudidi);
            mTextViewnNmdays = (TextView) itemView.findViewById(R.id.numdays);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewReturnPrice = itemView.findViewById(R.id.returnPrice);
            mTextViewGuonei = itemView.findViewById(R.id.guonei);

        }
    }

    class MyJinWaiViewHoder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPhoto;
        private TextView mTextViewchufadi;
        private TextView mTextViewmudidi;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewnNmdays;
        private TextView mTextViewTitles;
        private LinearLayout mTextViewGuonei;

        private TextView mTextViewReturnPrice;


        public MyJinWaiViewHoder(View itemView) {
            super(itemView);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.travelTitle);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewchufadi = (TextView) itemView.findViewById(R.id.chufadi);
            mTextViewmudidi = (TextView) itemView.findViewById(R.id.mudidi);
            mTextViewnNmdays = (TextView) itemView.findViewById(R.id.numdays);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewReturnPrice = itemView.findViewById(R.id.returnPrice);
            mTextViewGuonei = itemView.findViewById(R.id.guonei);


        }
    }

    class MyRentViewHoder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPhoto;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewAddress;
        private TextView mTextViewTitles;

        private TextView mTextViewReturnPrice;
        private LinearLayout mTextViewTicket;


        public MyRentViewHoder(View itemView) {
            super(itemView);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.travelTitle);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_head);
            mTextViewAddress = (TextView) itemView.findViewById(R.id.address);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewReturnPrice = itemView.findViewById(R.id.returnPrice);
            mTextViewTicket = itemView.findViewById(R.id.ticket);

        }
    }

//    private void setTextView(List<HeadTravelEntivty> list, int position, RecyclerView linearLayout) {
//        List<String> list1 = new ArrayList<>();
//        if (!TextUtils.isEmpty(list.get(position).getTabName())) {
//            String[] strings = list.get(position).getTabName().split(",");
//            if (strings.length > 0) {
//                for (int i = 0; i < strings.length; i++) {
//                    list1.add(strings[i]);
//                }
//            }
//        }
//        if (list1.size() > 0) {
//            GridLayoutManager manager = new GridLayoutManager(mContext, 3);
//            mAdapterTableName = new TableNameAdapter(list1, mContext);
//            linearLayout.setAdapter(mAdapterTableName);
//            linearLayout.setLayoutManager(manager);
////            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
//        }
//
//
//    }
}
