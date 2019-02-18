package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.ProvinceEntity;
import com.huxin.communication.utils.PreferenceUtil;

import java.util.List;

public class RegionAdapter extends  RecyclerView.Adapter<RegionAdapter.MyViewHoder>{

    private List<ProvinceEntity> list;
    private Activity mContext;
    private LayoutInflater mInflater;

    public RegionAdapter(List<ProvinceEntity> list, Activity mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_region_recycler, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
                PreferenceUtil.putString("province",list.get(hoder.getAdapterPosition()).getProvince_name());
            }
        });
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {

        holder.mTextView.setText(list.get(position).getProvince_name());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private TextView mTextView;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.region_line);
            mTextView = (TextView) itemView.findViewById(R.id.region_name_tv);
        }
    }
}
