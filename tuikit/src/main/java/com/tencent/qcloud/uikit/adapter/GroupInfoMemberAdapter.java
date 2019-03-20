package com.tencent.qcloud.uikit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.qcloud.uikit.R;

import java.util.ArrayList;


public class GroupInfoMemberAdapter extends RecyclerView.Adapter<GroupInfoMemberAdapter.HeadImageViewHoder> {

    private Context mContext;
    private ArrayList<String> list;

    public GroupInfoMemberAdapter(Context context, ArrayList<String> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public HeadImageViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new HeadImageViewHoder(inflater.inflate(R.layout.group_info_member_head, parent, false));
    }

    @Override
    public void onBindViewHolder(HeadImageViewHoder holder, int position) {
        ImageLoader.getInstance().displayImage(list.get(position), holder.headImageView);
    }

    @Override
    public int getItemCount() {
        return null != list ? list.size() : 0;
    }

    public class HeadImageViewHoder extends RecyclerView.ViewHolder {

        private ImageView headImageView;

        public HeadImageViewHoder(View itemView) {
            super(itemView);
            headImageView = (ImageView) itemView.findViewById(R.id.headUrl_iv);
        }
    }

}
