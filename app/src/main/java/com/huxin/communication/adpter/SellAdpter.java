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
import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.huxin.communication.ui.house.details.SellDetailsActivity;
import com.huxin.communication.ui.house.sell.SimilarDetailsActivity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/2.
 */

public class SellAdpter extends RecyclerView.Adapter<SellAdpter.MyViewHoder> {

    private List<SaleOfScreeningEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;


    public SellAdpter(List<SaleOfScreeningEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recyler_sell, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SellDetailsActivity.class);
                KyLog.d(list.get(hoder.getAdapterPosition()).getId() + "");
                intent.putExtra("pid", list.get(hoder.getAdapterPosition()).getId());
                mContext.startActivity(intent);
            }
        });
        hoder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(mContext, SimilarDetailsActivity.class);
                mContext.startActivity(intent1);
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

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.sell_line);
            mTextView = (TextView) itemView.findViewById(R.id.textView_similar);
            mTextViewvillageName = (TextView) itemView.findViewById(R.id.villageName_sell);
            mTextViewhouseType = (TextView) itemView.findViewById(R.id.houseType_sell);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage_sell);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice_sell);
            mImageViewKeying = (ImageView) itemView.findViewById(R.id.keying_sell);
            mTextViewStick = (TextView) itemView.findViewById(R.id.stick_sell);
            mTextViewUnitPrice = (TextView) itemView.findViewById(R.id.unitPrice_sell);
            mTextViewOrientation = (TextView) itemView.findViewById(R.id.orientation_sell);
            mLinearLayoutTabName = (RecyclerView) itemView.findViewById(R.id.tabName_line_sell);

        }
    }

    private void setTextView(List<SaleOfScreeningEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        String[] strings = list.get(position).getTabName().split(",");
        KyLog.d(list.get(position).getTabName());
        for (int i = 0; i < strings.length; i++) {
            list1.add(strings[i]);
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

    public void clearList() {
        list.clear();
        notifyDataSetChanged();
    }
}
