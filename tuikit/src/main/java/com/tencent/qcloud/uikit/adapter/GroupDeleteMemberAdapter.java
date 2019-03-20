package com.tencent.qcloud.uikit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tencent.qcloud.uikit.R;

import java.util.ArrayList;

public class GroupDeleteMemberAdapter extends RecyclerView.Adapter<GroupDeleteMemberAdapter.MemberViewHoder>{

    private Context mContext;

    public GroupDeleteMemberAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MemberViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MemberViewHoder(inflater.inflate(R.layout.group_delete_member, parent, false));
    }

    @Override
    public void onBindViewHolder(MemberViewHoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MemberViewHoder extends RecyclerView.ViewHolder {



        public MemberViewHoder(View itemView) {
            super(itemView);
        }
    }
}
