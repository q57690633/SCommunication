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

import com.huxin.communication.R;
import com.huxin.communication.entity.AddressBookEntity;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

import java.util.List;

public class GounpAdapter extends RecyclerView.Adapter<GounpAdapter.BodyViewHoder>{

    private List<AddressBookEntity.GroupBean> mList;
    private Activity mActivity;
    private LayoutInflater mInflater;

    public GounpAdapter(List<AddressBookEntity.GroupBean> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public BodyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_group_or_stick_recycler, parent, false);
        BodyViewHoder Hoder = new BodyViewHoder(view);
        Hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String userId = PreferenceUtil.getInt("uid") + "";
                String userSig = PreferenceUtil.getString("usersig");
//                showProgressDialog();
                TUIKit.login(userId, userSig, new IUIKitCallBack() {
                    @Override
                    public void onSuccess(Object data) {
//                        cancelProgressDialog();
                        KyLog.i("home onSuccess", data);
                        Intent chatIntent = new Intent(mActivity, TIMChatActivity.class);
                        chatIntent.putExtra("TARGET_TYPE", "Group");
                        chatIntent.putExtra("TARGET_ID", mList.get(Hoder.getAdapterPosition()).getFlockId() + "");
                        mActivity.startActivity(chatIntent);
                    }
                    @Override
                    public void onError(String module, int errCode, String errMsg) {
//                        cancelProgressDialog();
                        KyLog.e("home fail", errMsg);
                    }
                });
            }
        });
        return Hoder;
    }

    @Override
    public void onBindViewHolder(BodyViewHoder holder, int position) {
//        holder.mTextViewAddress.setText(mList.get(position).getAddress());
        KyLog.d(mList.get(position).getFlockName());
        holder.mTextViewName.setText(mList.get(position).getFlockName());
        ImageLoader.getInstance().displayImage(mList.get(position).getUrl(),holder.image);
//        holder.mTextViewPhone.setText(mList.get(position).getPhone());


    }

    @Override
    public int getItemCount() {
        KyLog.d(mList.size() + "");
        return mList.size();
    }


    public class BodyViewHoder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private LinearLayout mLinearLayout;
        private ImageView image;


        public BodyViewHoder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.name_famous);
            mLinearLayout= (LinearLayout) itemView.findViewById(R.id.rl_group);
            image = (ImageView) itemView.findViewById(R.id.image);


        }
    }
}
