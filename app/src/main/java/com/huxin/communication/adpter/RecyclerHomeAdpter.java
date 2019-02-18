package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huxin.communication.R;
import com.huxin.communication.utils.PreferenceUtil;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/10.
 */

public class RecyclerHomeAdpter extends RecyclerView.Adapter<RecyclerHomeAdpter.MyViewHoder> {

    private List<String> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private MyViewHoder hoder;

    public RecyclerHomeAdpter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (PreferenceUtil.getInt("type") == 1) {
            View view = mInflater.inflate(R.layout.recycler_home_item, parent, false);
            hoder = new MyViewHoder(view);

        } else {
            View view = mInflater.inflate(R.layout.recycler_home_item_travel, parent, false);
            hoder = new MyViewHoder(view);
        }
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        public MyViewHoder(View itemView) {
            super(itemView);
        }
    }
}
