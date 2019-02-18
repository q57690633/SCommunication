package com.huxin.communication.adpter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.InlandCityEntity;
import com.huxin.communication.entity.ProvinceEntity;
import com.huxin.communication.ui.CityActivity;
import com.huxin.communication.ui.CityTravelActivity;
import com.sky.kylog.KyLog;

import java.util.List;

public class ProvincesTravelAdapter extends RecyclerView.Adapter<ProvincesTravelAdapter.BodyViewHoder>{
    private Activity mActivity;
    private List<ProvinceEntity> mList;
    private LayoutInflater mInflater;
    private int type;

    public ProvincesTravelAdapter(List<ProvinceEntity> mList, Activity mActivity,int type) {
        this.mList = mList;
        this.mActivity = mActivity;
        this.type = type;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_address_city_recyclcer, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
        Hoder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity,CityTravelActivity.class);
                intent.putExtra(Constanst.PROVINCE_CODE,mList.get(Hoder.getAdapterPosition()).getProvince_code());
                intent.putExtra("type",type);
                mActivity.startActivity(intent);
                mActivity.finish();
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
//        holder.mTextViewAddress.setText(mList.get(position).getAddress());
        KyLog.d(mList.get(position).getProvince_name());
        holder.mTextViewName.setText(mList.get(position).getProvince_name());
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
