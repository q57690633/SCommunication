package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.AddressEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.List;

import static com.huxin.communication.controls.Constanst.PROVINCE_ID;
import static com.huxin.communication.controls.Constanst.PROVINCE_NAME;


/**
 * Created by yangzanxiong on 2018/1/23.
 */

public class AddressProvinceAdapter extends RecyclerView.Adapter<AddressProvinceAdapter.BodyViewHoder>{
    private Activity mActivity;
    private List<AddressEntity> mList;
    private LayoutInflater mInflater;

    public AddressProvinceAdapter(List<AddressEntity> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public AddressProvinceAdapter.BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_address_city_recyclcer, parent, false);
        AddressProvinceAdapter.BodyViewHoder Hoder = new AddressProvinceAdapter.BodyViewHoder(view);
        Hoder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (PreferenceUtil.getInt("type") == 1) {
                    PreferenceUtil.putString(PROVINCE_NAME, mList.get(Hoder.getAdapterPosition()).getName());
                    PreferenceUtil.putString(PROVINCE_ID, mList.get(Hoder.getAdapterPosition()).getId());
//                }else {
//                    PreferenceUtil.putString(PROVINCE_TRAVELS_NAME, mList.get(Hoder.getAdapterPosition()).getName());
//                    PreferenceUtil.putString(PROVINCE_TRAVEL_ID, mList.get(Hoder.getAdapterPosition()).getId());
//                }
                mActivity.finish();
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(AddressProvinceAdapter.BodyViewHoder holder, int position) {
//        holder.mTextViewAddress.setText(mList.get(position).getAddress());
        KyLog.d(mList.get(position).getName());
        holder.mTextViewName.setText(mList.get(position).getName());
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
