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
import com.huxin.communication.listener.TuiJianStarPhoneListener;
import com.huxin.communication.utils.JsonUitil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class TuiJIanStickAdapter extends RecyclerView.Adapter<TuiJIanStickAdapter.BodyViewHoder> {

    private List<AddressBookEntity.StarListBean> mList;
    private Activity mActivity;
    private LayoutInflater mInflater;
    private boolean isClicked = true;

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;

    private TuiJianStarPhoneListener mTuiJianPhoneListener;


    public void setTuiJianStarListener(TuiJianStarPhoneListener tuiJianStarListener) {
        mTuiJianPhoneListener = tuiJianStarListener;
    }


    //更新adpter的数据和选择状态
    public void updateDataSet(ArrayList<AddressBookEntity.StarListBean> list) {
        this.mList = list;
        mSelectedPositions = new SparseBooleanArray();
//        ab.setTitle("已选择" + 0 + "项");
    }

    ArrayList<UserInfoEntity> listUserInfo;
    //获得选中条目的结果
    public ArrayList<AddressBookEntity.StarListBean> getSelectedItem(int position) {
        ArrayList<AddressBookEntity.StarListBean> selectList = new ArrayList<>();
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(mList.get(i));
                    if (mTuiJianPhoneListener != null) {
                        listUserInfo = new ArrayList<>();
                        UserInfoEntity userInfoEntity = new UserInfoEntity();
                        userInfoEntity.setImageHead(mList.get(i).getHeadUrl());
                        userInfoEntity.setName(mList.get(i).getCompanyName());
                        userInfoEntity.setPhone(mList.get(i).getPhone());
                        userInfoEntity.setUid(mList.get(position).getId() + "");
                        listUserInfo.add(userInfoEntity);
                        mTuiJianPhoneListener.starPhone(mList.get(i).getHeadUrl(), isItemChecked(i));
                        if (listUserInfo.size() > 0) {
                            mTuiJianPhoneListener.starUserInfo(JsonUitil.getData(listUserInfo), isItemChecked(i));
                        }
                    }
                }else {
                    mTuiJianPhoneListener.starPhone(mList.get(i).getHeadUrl(), isItemChecked(position));
                    mTuiJianPhoneListener.starUserInfo(JsonUitil.getData(listUserInfo), isItemChecked(i));
                }
            }
        }
        return selectList;
    }


    public TuiJIanStickAdapter(List<AddressBookEntity.StarListBean> mList, Activity mActivity) {
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
                Log.d("qiugou", "isClicked == " + isClicked);
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
        KyLog.d(mList.get(position).getStoreName());
        holder.tvTitle.setText(mList.get(position).getStoreName());
        if (!TextUtils.isEmpty(mList.get(position).getHeadUrl())) {
            ImageLoader.getInstance().displayImage(mList.get(position).getHeadUrl(), holder.mImageView);
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
