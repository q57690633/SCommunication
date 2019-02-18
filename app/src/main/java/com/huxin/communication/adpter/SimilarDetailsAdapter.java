package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huxin.communication.R;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/13.
 */

public class SimilarDetailsAdapter extends RecyclerView.Adapter<SimilarDetailsAdapter.MyViewHoder> {

    private List<String> list;
    private Context mContext;
    private LayoutInflater mInflater;

    public SimilarDetailsAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_similar_recycler, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        public MyViewHoder(View itemView) {
            super(itemView);
        }
    }
}
