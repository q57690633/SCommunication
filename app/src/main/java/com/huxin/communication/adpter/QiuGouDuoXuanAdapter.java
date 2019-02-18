package com.huxin.communication.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.BuyerScreeningEntity;
import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.huxin.communication.entity.WantedScreeningEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/17.
 */

public class QiuGouDuoXuanAdapter extends RecyclerView.Adapter<QiuGouDuoXuanAdapter.MyViewHoder> {

    private static final String PID = "pid";

    private List<BuyerScreeningEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private boolean isClicked = false;
    private StringBuffer buffer;
    private int position = -1;
    private TableNameAdapter mAdapterTableName;

    public QiuGouDuoXuanAdapter(List<BuyerScreeningEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        buffer = new StringBuffer();
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_sell_duoxuan, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                KyLog.d(isClicked + "" + position + "" + hoder.getAdapterPosition());
                if (position < hoder.getAdapterPosition()) {
                    position = hoder.getAdapterPosition();
                    isClicked = false;
                } else {
                    if (hoder.getAdapterPosition() == 0) {
                        position = -1;
                    }
                    isClicked = true;
                }
                if (isClicked) {
                    isClicked = false;
                    hoder.mImageViewClicked.setVisibility(View.GONE);
                    hoder.mImageView.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(buffer.toString())) {
                        if (list.get(hoder.getAdapterPosition()).getId() < 10){
                            buffer.delete(buffer.length() - 2, buffer.length());
                        }else if (list.get(hoder.getAdapterPosition()).getId() > 10
                                && list.get(hoder.getAdapterPosition()).getId() < 100){
                            buffer.delete(buffer.length() - 3, buffer.length());
                        }else if (list.get(hoder.getAdapterPosition()).getId() > 100
                                && list.get(hoder.getAdapterPosition()).getId() < 1000){
                            buffer.delete(buffer.length() - 4, buffer.length());
                        }else if (list.get(hoder.getAdapterPosition()).getId() > 1000
                                && list.get(hoder.getAdapterPosition()).getId() < 10000){
                            buffer.delete(buffer.length() - 5, buffer.length());
                        }
                    }
                    KyLog.d(buffer.toString());
                } else {
                    addPosition(hoder.getAdapterPosition());
                    isClicked = true;
                    hoder.mImageViewClicked.setVisibility(View.VISIBLE);
                    hoder.mImageView.setVisibility(View.GONE);
                }
                notifyDataSetChanged();
            }
        });
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {

        holder.mTextViewvillageName.setText(String.valueOf(list.get(position).getVillageName()));
        holder.mTextViewhouseType.setText(String.valueOf(list.get(position).getHouseType()));
        holder.mTextViewTotalPrice.setText(String.valueOf(list.get(position).getTotalPrice()));
        holder.mTextViewUnitPrice.setText(String.valueOf(list.get(position).getUnitPrice()));
        holder.mTextViewOrientation.setText(String.valueOf(list.get(position).getOrientation()));
        holder.mTextViewAcreage.setText(String.valueOf(list.get(position).getAcreage()));

        if (list.get(position).getKeying() == 1) {
            holder.mImageViewKeying.setVisibility(View.VISIBLE);
        } else {
            holder.mImageViewKeying.setVisibility(View.GONE);
        }

        if (list.get(position).getStick() == 1) {
            holder.mTextViewStick.setVisibility(View.VISIBLE);
        } else {
            holder.mTextViewStick.setVisibility(View.GONE);
        }

        if (list.get(position).getExclusive() == 1) {
            holder.mTextView.setText("独家");
        } else {
            holder.mTextView.setText("相似房源");
        }
        setTextView(list, position, holder.mLinearLayoutTabName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mImageView;
        private ImageView mImageViewClicked;
        private TextView mTextView;
        private TextView mTextViewvillageName;
        private TextView mTextViewhouseType;
        private TextView mTextViewAcreage;
        private TextView mTextViewTotalPrice;
        private ImageView mImageViewKeying;
        private TextView mTextViewStick;
        private TextView mTextViewUnitPrice;
        private TextView mTextViewOrientation;
        private RecyclerView mLinearLayoutTabName;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.sell_line);
            mImageView = (ImageView) itemView.findViewById(R.id.image_duoxuan);
            mImageViewClicked = (ImageView) itemView.findViewById(R.id.image_duoxuan_clicked);
            mTextView = (TextView) itemView.findViewById(R.id.textView_duoxuan_similar);
            mTextViewvillageName = (TextView) itemView.findViewById(R.id.villageName_duoxuan_sell);
            mTextViewhouseType = (TextView) itemView.findViewById(R.id.houseType_duoxuan_sell);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage_duoxuan_sell);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice_duoxuan_sell);
            mImageViewKeying = (ImageView) itemView.findViewById(R.id.keying_duoxuan_sell);
            mTextViewStick = (TextView) itemView.findViewById(R.id.stick_duoxuan_sell);
            mTextViewUnitPrice = (TextView) itemView.findViewById(R.id.unitPrice_duoxuan_sell);
            mTextViewOrientation = (TextView) itemView.findViewById(R.id.orientation_duoxuan_sell);
            mLinearLayoutTabName = (RecyclerView) itemView.findViewById(R.id.tabName_line_duoxuan_sell);
        }
    }

    private void setTextView(List<BuyerScreeningEntity.ListBean> list, int position, RecyclerView linearLayout) {

        List<String> list1 = new ArrayList<>();
        String[] strings = list.get(position).getTabName().split(",");
        KyLog.d(list.get(position).getTabName());
        for (int i = 0; i < strings.length; i++){
            list1.add(strings[i]);
        }
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 5);
            mAdapterTableName = new TableNameAdapter(list1, mContext);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }

    private void addPosition(int position) {
        String pid;

        buffer.append(list.get(position).getId()).append(",");
        pid = buffer.toString();

        KyLog.d(pid);
        PreferenceUtil.putString(PID, pid);

    }
}
