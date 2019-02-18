package com.huxin.communication.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.huxin.communication.R;
import com.huxin.communication.ui.travel.details.ZhouBianDetailsActivity;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/16.
 */

public class DataBaseTravelAdapter extends RecyclerView.Adapter<DataBaseTravelAdapter.MyViewHoder> {

    private List<String> list;
    private Context mContext;
    private LayoutInflater mInflater;

    public DataBaseTravelAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_data_base_travel_recycler, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ZhouBianDetailsActivity.class);
                mContext.startActivity(intent);
            }
        });
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

        private LinearLayout mLinearLayout;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.top_selection_line);
        }
    }
}
