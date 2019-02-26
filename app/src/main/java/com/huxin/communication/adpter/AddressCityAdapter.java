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
import com.sky.kylog.KyLog;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/1/23.
 */

public class AddressCityAdapter extends RecyclerView.Adapter<AddressCityAdapter.BodyViewHoder> {

    private List<AddressEntity.ListBeanX> mList;
    private Activity mActivity;
    private LayoutInflater mInflater;

    public AddressCityAdapter(List<AddressEntity.ListBeanX> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public AddressCityAdapter.BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_address_city_recyclcer, parent, false);
        AddressCityAdapter.BodyViewHoder Hoder = new AddressCityAdapter.BodyViewHoder(view);
        Hoder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mList.get(Hoder.getAdapterPosition()).getSub() != null && mList.size() > 0) {
//                    Intent intent = new Intent(mActivity, AddressDistrictActivity.class);
//                    intent.putParcelableArrayListExtra(CITY, mList.get(Hoder.getAdapterPosition()).getSub());
//                    PreferenceUtil.putString(CITY_NAME, mList.get(Hoder.getAdapterPosition()).getName());
//                    PreferenceUtil.putInt(CITY_ID,Integer.parseInt(mList.get(Hoder.getAdapterPosition()).getID()));
//
//                    mActivity.startActivity(intent);
//                    mActivity.finish();
//                } else {
//                    PreferenceUtil.putString(CITY_NAME, mList.get(Hoder.getAdapterPosition()).getName());
//                    PreferenceUtil.putInt(CITY_ID,Integer.parseInt(mList.get(Hoder.getAdapterPosition()).getID()));
//
//                    mActivity.finish();
//                    Toast.makeText(mActivity, "沒有区县了", Toast.LENGTH_SHORT).show();
//                }
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(AddressCityAdapter.BodyViewHoder holder, int position) {
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
