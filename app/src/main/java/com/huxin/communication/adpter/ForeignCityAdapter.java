package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.ForeignCityEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.List;

import static com.huxin.communication.controls.Constanst.CITY_NATION_NAME;

public class ForeignCityAdapter extends RecyclerView.Adapter<ForeignCityAdapter.BodyViewHoder> {

    private Activity mActivity;
    private List<ForeignCityEntity> mList;
    private LayoutInflater mInflater;

    public ForeignCityAdapter(List<ForeignCityEntity> mList, Activity mActivity) {
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
                PreferenceUtil.putString(CITY_NATION_NAME, String.valueOf(mList.get(Hoder.getAdapterPosition()).getCity_name()));
//                PreferenceUtil.putString(CITY_NATION_NAME, String.valueOf(mList.get(Hoder.getAdapterPosition()).getNation_name()));

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
        if (position == mList.size()) {
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
