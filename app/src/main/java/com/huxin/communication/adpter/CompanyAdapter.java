package com.huxin.communication.adpter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.huxin.communication.ui.house.phone.FriendDetailedActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.List;

import static com.huxin.communication.ui.fragment.AssortmentFragment.ADDRESS_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.IMAGE_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.INDUSTRY_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.NAME_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.PHONE_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.STAR_FRIEND_TAG;
import static com.huxin.communication.ui.fragment.AssortmentFragment.UID_TAG;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.BodyViewHoder> {

    private List<AddressBookEntity.CompanyBean> mList;
    private List<String> Mutelist;

    private Activity mActivity;
    private LayoutInflater mInflater;

    public CompanyAdapter(List<AddressBookEntity.CompanyBean> mList, Activity mActivity) {
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
                String image = mList.get(Hoder.getAdapterPosition()).getHeadUrl();
                int uid = mList.get(Hoder.getAdapterPosition()).getUid();

                Intent intent = new Intent(mActivity, FriendDetailedActivity.class);
                intent.putExtra(NAME_TAG, name);
                intent.putExtra(ADDRESS_TAG, "");
                intent.putExtra(INDUSTRY_TAG, industry);
                intent.putExtra(PHONE_TAG, phone);
                intent.putExtra(STAR_FRIEND_TAG, starFriend);
                intent.putExtra(UID_TAG, uid);
                intent.putExtra(IMAGE_TAG, image);
                intent.putExtra("companyFriend", "company");


                mActivity.startActivity(intent);
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
        if (!TextUtils.isEmpty(mList.get(position).getUsername())) {
            holder.tvTitle.setText(mList.get(position).getUsername());
        } else {
            holder.tvTitle.setText("同业用户");

        }
        holder.tvPhone.setText(mList.get(position).getPhone());

        if (mList.get(position).getCOrP() == 1) {
            holder.zhiwei.setText("·" + mList.get(position).getPositions());
            holder.company.setText(mList.get(position).getCompanyName());
            holder.company.setVisibility(View.VISIBLE);

        } else {
            holder.zhiwei.setText("·" + mList.get(position).getIndustryType());
            holder.company.setVisibility(View.GONE);


        }


        if (!TextUtils.isEmpty(mList.get(position).getHeadUrl())) {
            Glide.with(mActivity).load(mList.get(position).getHeadUrl()).into(holder.image);

        } else {
            holder.image.setBackgroundResource(R.drawable.head2);
        }

        if (mList.get(position).getPhoneState()== 1){
            holder.tvPhone.setVisibility(View.VISIBLE);
        }else {
            holder.tvPhone.setVisibility(View.GONE);

        }

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
        private TextView tvPhone;
        private LinearLayout mRelativeLayout;
        private TextView company;
        private TextView zhiwei;


        public BodyViewHoder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.name_famous);
            tvPhone = (TextView) itemView.findViewById(R.id.tel);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.rl_stick);
            image = itemView.findViewById(R.id.image);
            mRelativeLayout = itemView.findViewById(R.id.tel_ll);
            company = itemView.findViewById(R.id.name_famous_ai);
            zhiwei = itemView.findViewById(R.id.name_zhiwei_famous);


        }
    }
}
