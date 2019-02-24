package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.MatchingProductEntity;
import com.sky.kylog.KyLog;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/11.
 */

public class MatchItemAdpter extends RecyclerView.Adapter<MatchItemAdpter.MyViewHoder>{

    private List<MatchingProductEntity.List1Bean> list;
    private Context mContext;
    private LayoutInflater mInflater;

    public MatchItemAdpter(List<MatchingProductEntity.List1Bean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_ietm_recycler_match, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
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

        if (Integer.parseInt(list.get(position).getKeying()) == 1){
            holder.mImageViewKeying.setVisibility(View.VISIBLE);
        }else {
            holder.mImageViewKeying.setVisibility(View.GONE);
        }

        if (list.get(position).getStick() == 1){
            holder.mTextViewStick.setVisibility(View.VISIBLE);
        }else {
            holder.mTextViewStick.setVisibility(View.GONE);
        }

        if (Integer.parseInt(list.get(position).getExclusive()) == 1){
            holder.mTextView.setText("独家");
        }else {
            holder.mTextView.setText("相似房源");
        }
        setTextView(list,position,holder.mLinearLayoutTabName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private TextView mTextViewvillageName;
        private TextView mTextViewhouseType;
        private TextView mTextViewAcreage;
        private TextView mTextViewTotalPrice;
        private ImageView mImageViewKeying;
        private TextView mTextViewStick;
        private TextView mTextViewUnitPrice;
        private TextView mTextViewOrientation;
        private LinearLayout mLinearLayoutTabName;

        public MyViewHoder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.exclusive_recycler_match);
            mTextViewvillageName = (TextView) itemView.findViewById(R.id.villageName_recycler_match);
            mTextViewhouseType = (TextView) itemView.findViewById(R.id.houseType_recycler_match);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage_recycler_match);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice_recycler_match);
            mImageViewKeying = (ImageView) itemView.findViewById(R.id.keying_recycler_match);
            mTextViewStick = (TextView) itemView.findViewById(R.id.stick_recycler_match);
            mTextViewUnitPrice = (TextView) itemView.findViewById(R.id.unitPrice_recycler_match);
            mTextViewOrientation = (TextView) itemView.findViewById(R.id.orientation_recycler_match);
            mLinearLayoutTabName = (LinearLayout) itemView.findViewById(R.id.tabName_line_match);
        }
    }

    private void setTextView(List<MatchingProductEntity.List1Bean> list, int position, LinearLayout linearLayout){

        String[] strings = list.get(position).getTabName().split(",");
        KyLog.d(list.get(position).getTabName());
//            LinearLayout mLinearLayout = new LinearLayout(mContext);
        ViewGroup.LayoutParams vlp = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        ((LinearLayout.LayoutParams) vlp).rightMargin = 20;
//            mLinearLayout.setGravity(LinearLayout.HORIZONTAL);
//            mLinearLayout.setLayoutParams(vlp);
        for (int i = 0; i < strings.length; i++) {
            KyLog.d(strings[i]);
            TextView tv1 = new TextView(mContext);
            tv1.setLayoutParams(vlp);//设置TextView的布局
            tv1.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
            tv1.setTextColor(mContext.getResources().getColor(R.color.white));
            tv1.setTextSize(12);
            tv1.setText(strings[i]);
            linearLayout.addView(tv1);
        }


    }
}
