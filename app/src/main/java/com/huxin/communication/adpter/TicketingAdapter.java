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
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.ui.travel.details.JinWaiDetailsActivity;
import com.huxin.communication.ui.travel.details.TicketingDetailsActivity;
import com.huxin.communication.ui.travel.release.ReleaseTicketingActivity;
import com.huxin.communication.ui.travel.release.ReleaseZhouBoundaryActivity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/16.
 */

public class TicketingAdapter extends RecyclerView.Adapter<TicketingAdapter.MyViewHoder> {

    private List<TicketInfoEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TicketTableNameAdapter mAdapterTableName;
    private int type;

    public TicketingAdapter(List<TicketInfoEntity.ListBean> list, Context mContext,int type) {
        this.list = list;
        this.mContext = mContext;
        this.type = type;

        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_ticketing_recycler, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    Intent intent = new Intent(mContext, TicketingDetailsActivity.class);
                    intent.putExtra("list", list.get(hoder.getAdapterPosition()));
                    mContext.startActivity(intent);
                } else if (type == 2) {
                    Intent intents = new Intent(mContext, ReleaseTicketingActivity.class);
                    intents.putExtra("list", list.get(hoder.getAdapterPosition()));
                    intents.putExtra("id", list.get(hoder.getAdapterPosition()).getId());
                    mContext.startActivity(intents);
                }
            }
        });
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextViewAddr.setText(list.get(position).getTicket_addr());
        holder.mTextViewName.setText(list.get(position).getTicket_name());
        holder.mTextViewOriginalPrice.setText(String.valueOf(list.get(position).getOriginal_price()) + "元");
        ImageLoader.getInstance().displayImage(list.get(position).getPhoto_url(),holder.mImageViewAddr);
        holder.mTextViewCount.setText("以浏览" + list.get(position).getView_count() + "次");

        if (!TextUtils.isEmpty(list.get(position).getTagName())) {
            setTextView(list, position, holder.mRecyclerView);
        }
        if (list.get(position).getStick_hot() == 1){
            holder.mImageViewStickName.setBackgroundResource(R.drawable.hot);
        }
        if (list.get(position).getStick_low() == 1){
            holder.mImageViewStickName.setBackgroundResource(R.drawable.tejia);
        }
        if (list.get(position).getStick_new() == 1){
            holder.mImageViewStickName.setBackgroundResource(R.drawable.shangxin);
        }
        if (list.get(position).getStick_return() == 1){
            holder.mImageViewStickName.setBackgroundResource(R.drawable.gaofanyong);
        }
        if (list.get(position).getStick_zeroC() == 1){
            holder.mImageViewStickName.setBackgroundResource(R.drawable.zifei);
        }
        if (list.get(position).getStick_better() == 1){
            holder.mImageViewStickName.setBackgroundResource(R.drawable.jingpin);
        }
        if (list.get(position).getStick_rate() == 1){
            holder.mImageViewStickName.setBackgroundResource(R.drawable.xingjiabi);
        }
        if (list.get(position).getStick_throw() == 1){
            holder.mImageViewStickName.setBackgroundResource(R.drawable.shuaiwei);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mImageViewAddr;
        private ImageView mImageViewStickName;
        private TextView mTextViewName;
        private TextView mTextViewOriginalPrice;
        private TextView mTextViewAddr;
        private TextView mTextViewCount;


        private RecyclerView mRecyclerView;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.ticketing_line);
            mImageViewAddr = (ImageView) itemView.findViewById(R.id.image_ticket_addr);
            mTextViewName = (TextView) itemView.findViewById(R.id.ticket_name);
            mTextViewAddr = (TextView) itemView.findViewById(R.id.ticket_addr);
            mTextViewOriginalPrice = (TextView) itemView.findViewById(R.id.original_price);

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_ticket);

            mImageViewStickName = itemView.findViewById(R.id.image_stick);
            mTextViewCount = itemView.findViewById(R.id.view_count);

        }
    }

    private void setTextView(List<TicketInfoEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        if (!TextUtils.isEmpty(list.get(position).getTagName())) {

            String[] strings = list.get(position).getTagName().split(",");
            KyLog.d(list.get(position).getTagName());
            for (int i = 0; i < strings.length; i++) {
                list1.add(strings[i]);
            }
        }
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 3);
            mAdapterTableName = new TicketTableNameAdapter(list1, mContext);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
//            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }
}
