package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.MatchingProductEntity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/11.
 */

public class MatchAdpter extends RecyclerView.Adapter<MatchAdpter.MyViewHoder> {

    private List<MatchingProductEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    private MatchItemAdpter mMatchAdpter;
    private int i = 1;

    public MatchAdpter(List<MatchingProductEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_match, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextViewKeHu.setText("客户" + i++);
        holder.mTextViewAcreage.setText(String.valueOf(list.get(position).getAcreage()));
        holder.mTextViewHouseType.setText(String.valueOf(list.get(position).getHouseType()));
        holder.mTextViewMaxAcreage.setText(String.valueOf(list.get(position).getMaxAcreage()));
        holder.mTextViewTotalPrice.setText(String.valueOf(list.get(position).getTotalPrice()));

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mMatchAdpter = new MatchItemAdpter(list.get(position).getList1(), mContext);
        mRecyclerView.setAdapter(mMatchAdpter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 10));

    }

    @Override
    public int getItemCount() {
        KyLog.d(list.size() + "");
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private TextView mTextViewKeHu;
        private TextView mTextViewHouseType;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewAcreage;
        private TextView mTextViewMaxAcreage;
        public MyViewHoder(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_match_item);
            mTextViewKeHu = (TextView) itemView.findViewById(R.id.kehu_btn);
            mTextViewHouseType = (TextView) itemView.findViewById(R.id.houseType_match);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice_match);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage_match);
            mTextViewMaxAcreage = (TextView) itemView.findViewById(R.id.maxAcreage_match);

        }
    }
}
