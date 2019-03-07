package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huxin.communication.R;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/20.
 */

public class PhoneRecyclerAdapter  extends RecyclerView.Adapter<PhoneRecyclerAdapter.MyViewHoder>{
    private List<String> list;
    private Context mContext;
    private LayoutInflater mInflater;

    public void setList(List<String> lists){
        if (lists != null){
            list = lists;
        }
        notifyDataSetChanged();
    }


    public PhoneRecyclerAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_phone_top, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);

        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        public MyViewHoder(View itemView) {
            super(itemView);
        }
    }
}
