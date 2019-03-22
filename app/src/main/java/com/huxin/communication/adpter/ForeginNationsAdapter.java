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

import com.huxin.communication.ForeginNationAdapter;
import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.ForeignNationEntity;
import com.huxin.communication.ui.ForeignCityActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.List;

public class ForeginNationsAdapter extends RecyclerView.Adapter<ForeginNationsAdapter.BodyViewHoder>{

    private Activity mActivity;
    private List<ForeignNationEntity> mList;
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;
    private String oldName;


    public interface OnItemClickListener {
        void onClick(int position);
    }

    public ForeginNationsAdapter(List<ForeignNationEntity> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }
    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_province_recycler, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
//        Hoder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mActivity,ForeignCityActivity.class);
//                intent.putExtra(Constanst.NATION_NAME,mList.get(Hoder.getAdapterPosition()).getCountry());
//                PreferenceUtil.putString(Constanst.NATION_NAME,mList.get(Hoder.getAdapterPosition()).getCountry());
//                mActivity.startActivity(intent);
//                mActivity.finish();
//            }
//        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
//        holder.mTextViewAddress.setText(mList.get(position).getAddress());
        KyLog.d(mList.get(position).getCountry());
        holder.mTextViewName.setText(mList.get(position).getCountry());
//        holder.mTextViewPhone.setText(mList.get(position).getPhone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onClick(holder.getAdapterPosition());
                    oldName = mList.get(holder.getAdapterPosition()).getCountry();
                    notifyDataSetChanged();

                }
            }

        });

        if (mList.get(position).getCountry().equals(oldName)) {
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
        private TextView mTextViewLine;


        public BodyViewHoder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.City_Name);
            mRelativeLayout = (LinearLayout) itemView.findViewById(R.id.name_line);

        }
    }
}
