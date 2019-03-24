package com.tencent.qcloud.uikit.operation.group;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.adapter.GroupInfoMemberAdapter;
import com.tencent.qcloud.uikit.entity.MemberHeadUrlEntity;

import java.util.ArrayList;
import java.util.List;

public class GroupCompanyFenLeiAdapter extends RecyclerView.Adapter<GroupCompanyFenLeiAdapter.HeadImageViewHoder>{
    private Context mContext;
    private ArrayList<MemberHeadUrlEntity> list;

    public GroupCompanyFenLeiAdapter(Context context, ArrayList<MemberHeadUrlEntity> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public HeadImageViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new HeadImageViewHoder(inflater.inflate(R.layout.item_group_company_recycler, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(HeadImageViewHoder holder, int position) {
        holder.company.setText(list.get(position).getCompanyName());
//        String image = listToString(list.get(position).getHeadUrl());
        KyLog.object(list.get(position).getHeadUrl().size());
//        KyLog.d("data == "+ image);
        if (list.get(position).getHeadUrl() != null && list.get(position).getHeadUrl().size() > 0){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5);
            holder.recycler.setLayoutManager(gridLayoutManager);
            holder.recycler.setAdapter(new GroupInfoMemberAdapter(mContext,  list.get(position).getHeadUrl()));
        }
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

    private ArrayList<String> setImageData(String image){
        ArrayList<String> list = new ArrayList<>();
        String[] dataArr = image.split(",");
        for(int i = 0; i < dataArr.length; i++) {
            list.add(dataArr[i]);
        }
        return list;
    }

    private String listToString(ArrayList<String> list) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if(i != list.size()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
