package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.AddressEntity;
import com.huxin.communication.entity.CountyEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.List;

import static com.huxin.communication.controls.Constanst.DISTRICT_NAME;
import static com.huxin.communication.controls.Constanst.DISTRICT_TRAVEL_NAME;

public class CountyAdapter extends RecyclerView.Adapter<CountyAdapter.BodyViewHoder>{

    private Activity mActivity;
    private List<AddressEntity.ListBeanX.ListBean> mList;
    private LayoutInflater mInflater;

    public CountyAdapter(List<AddressEntity.ListBeanX.ListBean> mList, Activity mActivity) {
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
                if (PreferenceUtil.getInt("type") == 1){


                PreferenceUtil.putString(DISTRICT_NAME,mList.get(Hoder.getAdapterPosition()).getName());
                }else {
                    PreferenceUtil.putString(DISTRICT_TRAVEL_NAME,mList.get(Hoder.getAdapterPosition()).getName());

                }
                mActivity.finish();
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
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
