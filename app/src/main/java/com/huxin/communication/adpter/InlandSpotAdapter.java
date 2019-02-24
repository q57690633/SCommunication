package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.InlandSpotEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.List;

public class InlandSpotAdapter extends RecyclerView.Adapter<InlandSpotAdapter.BodyViewHoder>{
    private Activity mActivity;
    private List<InlandSpotEntity> mList;
    private LayoutInflater mInflater;

    public InlandSpotAdapter(List<InlandSpotEntity> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_address_city_recyclcer, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
        Hoder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtil.putString(Constanst.SPOT_NAME,mList.get(Hoder.getAdapterPosition()).getSpot_name());
                PreferenceUtil.putString(Constanst.SPOT_ID,String.valueOf(mList.get(Hoder.getAdapterPosition()).getId()));
                mActivity.finish();
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
//        holder.mTextViewAddress.setText(mList.get(position).getAddress());
        KyLog.d(mList.get(position).getSpot_name());
        holder.mTextViewName.setText(mList.get(position).getSpot_name());
//        holder.mTextViewPhone.setText(mList.get(position).getPhone());
        if (position == mList.size()){
            holder.mTextViewLine.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        KyLog.d(mList.size() + "");
        return mList.size();
    }


    public class BodyViewHoder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private RelativeLayout mRelativeLayout;
        private TextView mTextViewLine;


        public BodyViewHoder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.City_Name);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.address_city_rl);
            mTextViewLine = (TextView) itemView.findViewById(R.id.line_tv);

        }
    }
}
