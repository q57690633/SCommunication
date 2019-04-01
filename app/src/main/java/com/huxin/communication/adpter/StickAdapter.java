package com.huxin.communication.adpter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huxin.communication.R;
import com.huxin.communication.entity.AddressBookEntity;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.ui.house.phone.FriendDetailedActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

import java.util.List;

import static com.huxin.communication.ui.fragment.AssortmentFragment.ADDRESS_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.IMAGE_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.INDUSTRY_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.NAME_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.PHONE_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.STAR_FRIEND_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.UID_TAG;

public class StickAdapter extends RecyclerView.Adapter<StickAdapter.BodyViewHoder> {

    private List<AddressBookEntity.StarListBean> mList;
    private Activity mActivity;
    private LayoutInflater mInflater;

    public StickAdapter(List<AddressBookEntity.StarListBean> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_stick_recycler, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
        Hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mList.get(Hoder.getAdapterPosition()).getCompanyName();
                String industry = mList.get(Hoder.getAdapterPosition()).getIndustryType();
                String phone = mList.get(Hoder.getAdapterPosition()).getPhone();
                String starFriend = mList.get(Hoder.getAdapterPosition()).getStarFriend();
                String headUrl = mList.get(Hoder.getAdapterPosition()).getHeadUrl();
                int uid = mList.get(Hoder.getAdapterPosition()).getUid();
                Intent intent = new Intent(mActivity, FriendDetailedActivity.class);
                intent.putExtra(NAME_TAG, name);
                intent.putExtra(ADDRESS_TAG, "");
                intent.putExtra(INDUSTRY_TAG, industry);
                intent.putExtra(PHONE_TAG, phone);
                intent.putExtra(STAR_FRIEND_TAG, starFriend);
                intent.putExtra(UID_TAG, uid);
                intent.putExtra(IMAGE_TAG, headUrl);
                intent.putExtra("star","star");
                mActivity.startActivity(intent);
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
//        holder.mTextViewAddress.setText(mList.get(position).getAddress());
        KyLog.d(mList.get(position).getStoreName());
        holder.tvTitle.setText(mList.get(position).getStoreName());
        holder.tvPhone.setText(mList.get(position).getPhone());
        Glide.with(mActivity).load(mList.get(position).getHeadUrl()).into(holder.image);

//        holder.mTextViewPhone.setText(mList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        KyLog.d(mList.size() + "");
        return mList.size();
    }


    public class BodyViewHoder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private TextView tvTitle;
        private ImageView image;
        private LinearLayout tvLl;
        private TextView tvPhone;


        public BodyViewHoder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.name_famous);
            tvLl = (LinearLayout) itemView.findViewById(R.id.tel_ll);
            tvPhone = (TextView) itemView.findViewById(R.id.tel);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.rl_stick);
            image = itemView.findViewById(R.id.image);

        }
    }
}
