package com.huxin.communication.adpter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huxin.communication.R;
import com.tencent.qcloud.uikit.adapter.GroupInfoMemberAdapter;
import com.tencent.qcloud.uikit.entity.MemberHeadUrlEntity;

import java.util.ArrayList;

public class GroupCompanyAdapter extends RecyclerView.Adapter<GroupCompanyAdapter.HeadImageViewHoder>{
    private Context mContext;
    private ArrayList<MemberHeadUrlEntity> list;

    public GroupCompanyAdapter(Context context, ArrayList<MemberHeadUrlEntity> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public HeadImageViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new HeadImageViewHoder(inflater.inflate(R.layout.group_info_member_head, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(HeadImageViewHoder holder, int position) {
//        if("".equalsIgnoreCase(list.get(position))) {
//            holder.headImageView.setImageDrawable(mContext.getDrawable(R.drawable.default_head));
//        }else {
//            Glide.with(mContext)
//                    .load(list.get(position))
//                    .placeholder(R.drawable.default_head)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .override(135, 135)
//                    .fitCenter()
//                    .into(holder.headImageView);
//        }
        holder.company.setText(list.get(position).getCompanyName());
    }

    @Override
    public int getItemCount() {
        return null != list ? list.size() : 0;
    }

    public class HeadImageViewHoder extends RecyclerView.ViewHolder {

        private TextView company;
        private RecyclerView recycler;

        public HeadImageViewHoder(View itemView) {
            super(itemView);
            company = (TextView) itemView.findViewById(R.id.headUrl_iv);
            recycler = itemView.findViewById(R.id.recycler);
        }
    }

}
