package com.huxin.communication.adpter;

import android.content.Context;
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
import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.huxin.communication.entity.SimilerEntity;
import com.huxin.communication.utils.DateUtil;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/13.
 */

public class SimilarDetailsAdapter extends RecyclerView.Adapter<SimilarDetailsAdapter.MyViewHoder> {

    private List<SimilerEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;

    public SimilarDetailsAdapter(List<SimilerEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_similar_recycler, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextViewvillageName.setText(String.valueOf(list.get(position).getVillageName()));
        holder.mTextViewhouseType.setText(String.valueOf(list.get(position).getHouseType()));
        holder.mTextViewTotalPrice.setText(String.valueOf(list.get(position).getTotalPrice()) + "万");
        holder.mTextViewUnitPrice.setText(String.valueOf(list.get(position).getUnitPrice()) + "元/㎡");
        holder.mTextViewAcreage.setText(String.valueOf(list.get(position).getAcreage()) + "㎡");
        holder.mTextViewOrientation.setText(String.valueOf(list.get(position).getOrientation()));

        holder.mTextViewPhone.setText(String.valueOf(list.get(position).getUserModel().getPhone()));
        holder.mTextViewName.setText(String.valueOf(list.get(position).getUserModel().getCompanyName()));
        holder.mTextViewTime.setText("发布时间" + DateUtil.timeslashData(String.valueOf(list.get(position).getUserModel().getTime())));
        holder.mTextViewNum.setText(String.valueOf("累计发布次数：" + list.get(position).getPublicNumber()) + "次");
        holder.mTextViewNumber.setText("相似房源（" + String.valueOf(position + 1) + "）");




        if (list.get(position).getKeying() == 1) {
            holder.mImageViewKeying.setVisibility(View.VISIBLE);
        } else {
            holder.mImageViewKeying.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(list.get(position).getTabName())) {
            setTextView(list, position, holder.mLinearLayoutTabName);
        }

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
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
        private TextView mTextViewName;
        private TextView mTextViewPhone;
        private TextView mTextViewTime;
        private TextView mTextViewNum;
        private TextView mTextViewNumber;



        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.sell_line);
            mTextViewvillageName = (TextView) itemView.findViewById(R.id.villageName_sell);
            mTextViewhouseType = (TextView) itemView.findViewById(R.id.houseType_sell);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage_sell);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice_sell);
            mImageViewKeying = (ImageView) itemView.findViewById(R.id.keying_sell);
            mTextViewUnitPrice = (TextView) itemView.findViewById(R.id.unitPrice_sell);
            mTextViewOrientation = (TextView) itemView.findViewById(R.id.orientation_sell);
            mLinearLayoutTabName = (RecyclerView) itemView.findViewById(R.id.tabName_line_sell);
            mTextViewName =  itemView.findViewById(R.id.name);
            mTextViewPhone =  itemView.findViewById(R.id.phone);
            mTextViewTime =  itemView.findViewById(R.id.time);
            mTextViewNum =  itemView.findViewById(R.id.num);
            mTextViewNumber =  itemView.findViewById(R.id.number);




        }
    }

    private void setTextView(List<SimilerEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        if (!TextUtils.isEmpty(list.get(position).getTabName())) {

            String[] strings = list.get(position).getTabName().split(",");
            KyLog.d(list.get(position).getTabName());
            for (int i = 0; i < strings.length; i++) {
                list1.add(strings[i]);
            }
        }
        KyLog.d(list1.size() + "");
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 4);
            mAdapterTableName = new TableNameAdapter(list1, mContext);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
//            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }

}
