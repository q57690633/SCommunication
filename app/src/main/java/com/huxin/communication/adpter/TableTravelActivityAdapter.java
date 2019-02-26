package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.TabTravelNameEntity;
import com.huxin.communication.utils.PreferenceUtil;

import java.util.List;

public class TableTravelActivityAdapter extends RecyclerView.Adapter<TableTravelActivityAdapter.MyViewHoder>{
    private List<TabTravelNameEntity.ActivityListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;

    public TableTravelActivityAdapter(List<TabTravelNameEntity.ActivityListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_details_tablename, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoder.mTextView.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                hoder.mTextView.setTextColor(mContext.getResources().getColor(R.color.blue));
                PreferenceUtil.putString(Constanst.TAB_NMAE_ACTIVITY,list.get(hoder.getAdapterPosition()).getTagName());
            }
        });

        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextView.setText(list.get(position).getTagName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private LinearLayout mLinearLayout;

        public MyViewHoder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_table_name);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.line_table);

        }
    }
}
