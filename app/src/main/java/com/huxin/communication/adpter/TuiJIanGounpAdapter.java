package com.huxin.communication.adpter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.huxin.communication.entity.UserInfoEntity;
import com.huxin.communication.listener.TuiJianGounpListener;
import com.huxin.communication.listener.TuijianCompanyListener;
import com.huxin.communication.utils.JsonUitil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class TuiJIanGounpAdapter extends RecyclerView.Adapter<TuiJIanGounpAdapter.BodyViewHoder> {
    private List<AddressBookEntity.GroupBean> mList;
    private Activity mActivity;
    private LayoutInflater mInflater;
    private boolean isClicked = true;
    private TuiJianGounpListener mTuiJianPhoneListener;


    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;


    public void setTuiJianGounpListener(TuiJianGounpListener tuiJianPhoneListener) {
        mTuiJianPhoneListener = tuiJianPhoneListener;
    }


    //更新adpter的数据和选择状态
    public void updateDataSet(ArrayList<AddressBookEntity.GroupBean> list) {
        this.mList = list;
        mSelectedPositions = new SparseBooleanArray();
//        ab.setTitle("已选择" + 0 + "项");
    }


    //获得选中条目的结果
    public ArrayList<AddressBookEntity.GroupBean> getSelectedItem(int position) {
        ArrayList<AddressBookEntity.GroupBean> selectList = new ArrayList<>();
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(mList.get(i));
                    if (mTuiJianPhoneListener != null) {
                        ArrayList<UserInfoEntity> listUserInfo = new ArrayList<>();
                        UserInfoEntity userInfoEntity = new UserInfoEntity();
                        userInfoEntity.setImageHead(mList.get(position).getUrl());
                        userInfoEntity.setName(mList.get(position).getFlockName());
                        userInfoEntity.setUid(mList.get(position).getFlockId());
                        listUserInfo.add(userInfoEntity);
                        mTuiJianPhoneListener.updateGounp(mList.get(position).getUrl(), isItemChecked(position));
                        if (listUserInfo.size() > 0) {
                            mTuiJianPhoneListener.updateUserInfoGounp(JsonUitil.getData(listUserInfo), isItemChecked(position));
                        }
                    }
                } else {
                    mTuiJianPhoneListener.updateGounp(null, isItemChecked(position));
                    mTuiJianPhoneListener.updateUserInfoGounp(null, isItemChecked(position));
                }


            }
        }
        return selectList;
    }

    public TuiJIanGounpAdapter(List<AddressBookEntity.GroupBean> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
        if (mList == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_stick_duoxuan_recycler, parent, false);
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
                getSelectedItem(Hoder.getAdapterPosition());

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
        KyLog.d(mList.get(position).getFlockName());
        holder.tvTitle.setText(mList.get(position).getFlockName());
        if (!TextUtils.isEmpty(mList.get(position).getUrl())) {
            ImageLoader.getInstance().displayImage(mList.get(position).getUrl(), holder.mImageView);
        } else {
            holder.mImageView.setBackgroundResource(R.drawable.head2);
        }
//        holder.mTextViewPhone.setText(mList.get(position).getPhone());
        if (isItemChecked(position)) {
            holder.image.setBackgroundResource(R.drawable.icon_circle_selected);
        } else {
            holder.image.setBackgroundResource(R.drawable.icon_circle_normal);
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
