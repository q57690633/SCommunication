package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.InlandSpotEntity;
import com.huxin.communication.entity.SelectPlotEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShuaiShuanInlandSpotAdapter extends RecyclerView.Adapter<ShuaiShuanInlandSpotAdapter.BodyViewHoder>{

    private Activity mActivity;
    private List<InlandSpotEntity> mList;
    private LayoutInflater mInflater;
    private boolean isClicked = true;
    private Set<String> setList = new HashSet<>();

    public void setList(List<InlandSpotEntity> list) {
        if (list != null && list.size() > 0) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    public ShuaiShuanInlandSpotAdapter(List<InlandSpotEntity> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_select_plot_recycler, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
        Hoder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    Hoder.mImageViewSelect.setVisibility(View.GONE);
                    Hoder.mImageViewSelectC.setVisibility(View.VISIBLE);
                    setList.add(mList.get(Hoder.getAdapterPosition()).getSpot_name());
                    isClicked = false;
                } else {
                    Hoder.mImageViewSelect.setVisibility(View.VISIBLE);
                    Hoder.mImageViewSelectC.setVisibility(View.GONE);
                    setList.remove(mList.get(Hoder.getAdapterPosition()).getSpot_name());
                    isClicked = true;
                }
                KyLog.d(setList.toString());
                PreferenceUtil.putString(Constanst.SHAIXUAN_SPOT_NAME, setList.toString());
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
        KyLog.d(mList.get(position).getSpot_name());
        holder.mTextViewName.setText(mList.get(position).getSpot_name());
//        holder.mTextViewPhone.setText(mList.get(position).getPhone());
//        if (position == mList.size()){
//            holder.mTextViewLine.setVisibility(View.GONE);
//        }




    }

    @Override
    public int getItemCount() {
        KyLog.d(mList.size() + "");
        return mList.size();
    }


    public class BodyViewHoder extends RecyclerView.ViewHolder {
        private ImageView mImageViewSelect;
        private RelativeLayout mRelativeLayout;
        private ImageView mImageViewSelectC;
        private TextView mTextViewName;


        public BodyViewHoder(View itemView) {
            super(itemView);
            mImageViewSelect = (ImageView) itemView.findViewById(R.id.select);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.address_city_rl);
            mImageViewSelectC = (ImageView) itemView.findViewById(R.id.select_click);
            mTextViewName = (TextView) itemView.findViewById(R.id.City_Name);

        }
    }
}
