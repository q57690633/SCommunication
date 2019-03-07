package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.AddressBookEntity;
import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class TuijianCompanyAdapter extends RecyclerView.Adapter<TuijianCompanyAdapter.BodyViewHoder> {

    private List<AddressBookEntity.CompanyBean> mList;
    private Activity mActivity;
    private LayoutInflater mInflater;
    private boolean isClicked = true;

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;


    //更新adpter的数据和选择状态
    public void updateDataSet(ArrayList<AddressBookEntity.CompanyBean> list) {
        this.mList = list;
        mSelectedPositions = new SparseBooleanArray();
//        ab.setTitle("已选择" + 0 + "项");
    }


    //获得选中条目的结果
    public ArrayList<AddressBookEntity.CompanyBean> getSelectedItem() {
        ArrayList<AddressBookEntity.CompanyBean> selectList = new ArrayList<>();
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(mList.get(i));
                }
            }
        }
        return selectList;
    }

    public TuijianCompanyAdapter(List<AddressBookEntity.CompanyBean> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
        if (mList == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_phone, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
        Hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(Hoder.getAdapterPosition())) {
                    setItemChecked(Hoder.getAdapterPosition(), false);
                } else {
                    setItemChecked(Hoder.getAdapterPosition(), true);
                }
                notifyItemChanged(Hoder.getAdapterPosition());
                getSelectedItem();

                Log.d("qiugou", "isClicked == " + isClicked);

            }
        });
        return Hoder;
    }

    //设置给定位置条目的选择状态
    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
    }

    //根据位置判断条目是否选中
    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    //根据位置判断条目是否可选
    private boolean isSelectable() {
        return mIsSelectable;
    }

    //设置给定位置条目的可选与否的状态
    private void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
//        holder.mTextViewAddress.setText(mList.get(position).getAddress());
        KyLog.d(mList.get(position).getStoreName());
        holder.tvTitle.setText(mList.get(position).getStoreName());
        ImageLoader.getInstance().displayImage(mList.get(position).getHeadUrl(), holder.image);
//        holder.mTextViewPhone.setText(mList.get(position).getPhone());
        if (isItemChecked(position)) {
            holder.mImageView.setBackgroundResource(R.drawable.icon_circle_selected);
        } else {
            holder.mImageView.setBackgroundResource(R.drawable.icon_circle_normal);
        }

    }

    @Override
    public int getItemCount() {
        KyLog.d(mList.size() + "");
        return mList.size();
    }


    public class BodyViewHoder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView image;
        private LinearLayout mLinearLayout;
        private ImageView mImageView;


        public BodyViewHoder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.name_famous);
            image = (ImageView) itemView.findViewById(R.id.image_duoxuan);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.rl_stick);
            mImageView = itemView.findViewById(R.id.image);


        }
    }
}
