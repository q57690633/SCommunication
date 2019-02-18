package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.InlandCityEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.List;

import static com.huxin.communication.controls.Constanst.CITY_CODE;
import static com.huxin.communication.controls.Constanst.CITY_MUDI_CODE;
import static com.huxin.communication.controls.Constanst.CITY_MUDI_TRAVEL_NAME;
import static com.huxin.communication.controls.Constanst.CITY_TRAVEL_NAME;
import static com.huxin.communication.controls.Constanst.PROVINCE_CODE;
import static com.huxin.communication.controls.Constanst.PROVINCE_MUDI_CODE;
import static com.huxin.communication.controls.Constanst.PROVINCE_MUDI_TRAVEL_NAME;
import static com.huxin.communication.controls.Constanst.PROVINCE_TRAVEL_NAME;

public class CityTravelAdapter extends RecyclerView.Adapter<CityTravelAdapter.BodyViewHoder>{
    private Activity mActivity;
    private List<InlandCityEntity> mList;
    private LayoutInflater mInflater;
    private int type;

    public CityTravelAdapter(List<InlandCityEntity> mList, Activity mActivity,int type) {
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
                if (type == 1) {
                    if (mList.get(Hoder.getAdapterPosition()).getCity_name().contains("市")) {
                        PreferenceUtil.putString(CITY_TRAVEL_NAME, mList.get(Hoder.getAdapterPosition()).getCity_name());
                    } else {
                        PreferenceUtil.putString(CITY_TRAVEL_NAME, mList.get(Hoder.getAdapterPosition()).getCity_name() + "市");
                    }
                    PreferenceUtil.putString(CITY_CODE, String.valueOf(mList.get(Hoder.getAdapterPosition()).getCity_code()));
                    PreferenceUtil.putString(PROVINCE_CODE, String.valueOf(mList.get(Hoder.getAdapterPosition()).getProvince_code()));
                    PreferenceUtil.putString(PROVINCE_TRAVEL_NAME, String.valueOf(mList.get(Hoder.getAdapterPosition()).getProvince_name()));
                }else {
                    if (mList.get(Hoder.getAdapterPosition()).getCity_name().contains("市")) {
                        PreferenceUtil.putString(CITY_MUDI_TRAVEL_NAME, mList.get(Hoder.getAdapterPosition()).getCity_name());
                    } else {
                        PreferenceUtil.putString(CITY_MUDI_TRAVEL_NAME, mList.get(Hoder.getAdapterPosition()).getCity_name() + "市");
                    }
                    KyLog.d(String.valueOf(mList.get(Hoder.getAdapterPosition()).getCity_code()));
                    PreferenceUtil.putString(CITY_MUDI_CODE, String.valueOf(mList.get(Hoder.getAdapterPosition()).getCity_code()));
                    PreferenceUtil.putString(PROVINCE_MUDI_CODE, String.valueOf(mList.get(Hoder.getAdapterPosition()).getProvince_code()));
                    PreferenceUtil.putString(PROVINCE_MUDI_TRAVEL_NAME, String.valueOf(mList.get(Hoder.getAdapterPosition()).getProvince_name()));
                }
                mActivity.finish();
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
//        holder.mTextViewAddress.setText(mList.get(position).getAddress());
        KyLog.d(mList.get(position).getCity_name());
        holder.mTextViewName.setText(mList.get(position).getCity_name());
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
