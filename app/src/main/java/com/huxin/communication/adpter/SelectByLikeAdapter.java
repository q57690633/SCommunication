package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.SelectByLikeEntity;
import com.huxin.communication.entity.SelectPlotEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectByLikeAdapter extends RecyclerView.Adapter<SelectByLikeAdapter.BodyViewHoder>{

    private Activity mActivity;
    private List<SelectByLikeEntity> mList;
    private LayoutInflater mInflater;
    private boolean isClicked = true;

    private OnMyItemClickListener listener;

    public void setOnMyItemClickListener(OnMyItemClickListener listener){
        this.listener = listener;

    }

    public interface OnMyItemClickListener{
        void myClick(View v, int pos);
    }


    public void setList(List<SelectByLikeEntity> list) {
        if (list != null && list.size() > 0) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    public SelectByLikeAdapter(List<SelectByLikeEntity> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_viillagename_select, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
        Hoder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PreferenceUtil.putString(Constanst.SELECT_BY_LIKE_NAME,String.valueOf(mList.get(Hoder.getAdapterPosition()).getId()));
                listener.myClick(v,Hoder.getAdapterPosition());
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
        KyLog.d(mList.get(position).getVillageName());
        holder.mTextViewName.setText(mList.get(position).getVillageName());
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
        private LinearLayout mRelativeLayout;
        private TextView mTextViewName;


        public BodyViewHoder(View itemView) {
            super(itemView);
            mRelativeLayout = (LinearLayout) itemView.findViewById(R.id.villagenmae_city_rl);
            mTextViewName = (TextView) itemView.findViewById(R.id.City_Name);

        }
    }
}
