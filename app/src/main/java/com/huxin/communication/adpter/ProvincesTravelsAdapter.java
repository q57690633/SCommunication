package com.huxin.communication.adpter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.ProvinceEntity;
import com.huxin.communication.ui.CityTravelActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.List;

public class ProvincesTravelsAdapter extends RecyclerView.Adapter<ProvincesTravelsAdapter.BodyViewHoder> {
    private Activity mActivity;
    private List<ProvinceEntity> mList;
    private LayoutInflater mInflater;
    private int type;

    private OnItemClickListener mClickListener;
    private String oldName;


    public interface OnItemClickListener {
        void onClick(int position);
    }


    public ProvincesTravelsAdapter(List<ProvinceEntity> mList, Activity mActivity, int type) {
        this.mList = mList;
        this.mActivity = mActivity;
        this.type = type;
        mInflater = LayoutInflater.from(mActivity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_province_recycler, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
        KyLog.d(mList.get(position).getProvince_name());
        holder.mTextViewName.setText(mList.get(position).getProvince_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onClick(holder.getAdapterPosition());
                    oldName = mList.get(holder.getAdapterPosition()).getProvince_name();
                    notifyDataSetChanged();

                }
            }
        });

        if (mList.get(position).getProvince_name().equals(oldName)) {
            holder.mTextViewName.setTextColor(mActivity.getResources().getColor(R.color.blue));
        } else {
            holder.mTextViewName.setTextColor(mActivity.getResources().getColor(R.color.Font_color_title));

        }
    }

    @Override
    public int getItemCount() {
        KyLog.d(mList.size() + "");
        return mList.size();
    }


    public class BodyViewHoder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private LinearLayout mRelativeLayout;


        public BodyViewHoder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.City_Name);
            mRelativeLayout = (LinearLayout) itemView.findViewById(R.id.name_line);
        }


    }
}
