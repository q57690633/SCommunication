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
import com.huxin.communication.entity.AreaOneScreenEntity;
import com.huxin.communication.entity.AreaTwoScreenEntity;
import com.huxin.communication.ui.CityTravelActivity;
import com.huxin.communication.ui.house.sell.SelectPlotActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.List;

public class AreaTwoScreenAdapter extends RecyclerView.Adapter<AreaTwoScreenAdapter.BodyViewHoder> {

    private Activity mActivity;
    private List<AreaTwoScreenEntity> mList;
    private LayoutInflater mInflater;
    private int areaId;
    private int type;

    public AreaTwoScreenAdapter(List<AreaTwoScreenEntity> mList, Activity mActivity, int areaId,int type) {
        this.mList = mList;
        this.mActivity = mActivity;
        this.areaId = areaId;
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
                if (type != 1) {
                    Intent intent = new Intent(mActivity, SelectPlotActivity.class);
                    intent.putExtra(Constanst.SCREEN_TWOAONE_NAME, mList.get(Hoder.getAdapterPosition()).getAreaId());
                    intent.putExtra(Constanst.SCREEN_TWOAONE_ID, areaId);
                    mActivity.startActivity(intent);
                }
                PreferenceUtil.putString(Constanst.SCREEN_TWOAONE_NAME, mList.get(Hoder.getAdapterPosition()).getSecondName());
                mActivity.finish();
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
//        holder.mTextViewAddress.setText(mList.get(position).getAddress());
        KyLog.d(mList.get(position).getSecondName());
        holder.mTextViewName.setText(mList.get(position).getSecondName());
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
