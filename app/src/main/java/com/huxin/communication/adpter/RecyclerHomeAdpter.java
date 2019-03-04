package com.huxin.communication.adpter;

import android.content.Context;
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

import com.alipay.sdk.sys.a;
import com.huxin.communication.R;
import com.huxin.communication.entity.GetMessageEntity;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/10.
 */

public class RecyclerHomeAdpter extends RecyclerView.Adapter<RecyclerHomeAdpter.MyViewHoder> {

    private List<GetMessageEntity> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private MyViewHoder hoder;

    public RecyclerHomeAdpter(List<GetMessageEntity> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (PreferenceUtil.getInt("type") == 1) {
            View view = mInflater.inflate(R.layout.recycler_home_item, parent, false);
            hoder = new MyViewHoder(view);
            hoder.Tm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,TIMChatActivity.class);
                    intent.putExtra("TARGET_TYPE", "C2C");
                    intent.putExtra("TARGET_ID", list.get(hoder.getAdapterPosition()).getId() + "");

                    mContext.startActivity(intent);
                }
            });

        } else {
            View view = mInflater.inflate(R.layout.recycler_home_item_travel, parent, false);
            hoder = new MyViewHoder(view);
        }
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.time.setText(String.valueOf(list.get(position).getTimeStamp()));
        holder.num.setText(String.valueOf(list.get(position).getNum()));
        holder.msg.setText(String.valueOf(list.get(position).getMsg()));
        if (!TextUtils.isEmpty(list.get(position).getHead_url())){
            ImageLoader.getInstance().displayImage(list.get(position).getHead_url(),holder.image);
        }else {
            holder.image.setBackgroundResource(R.drawable.head2);
        }

        if (list.get(position).getNum() == 0){
            holder.numRl.setVisibility(View.GONE);
        }else {
            holder.numRl.setVisibility(View.VISIBLE);

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        private TextView num;
        private TextView time;
        private TextView msg;
        private ImageView image;
        private RelativeLayout numRl;
        private LinearLayout Tm;

        public MyViewHoder(View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.num);
            msg = itemView.findViewById(R.id.msg);
            image = itemView.findViewById(R.id.image_head);
            time = itemView.findViewById(R.id.time);
            numRl = itemView.findViewById(R.id.rl_num);
            Tm = itemView.findViewById(R.id.line_tm);


        }
    }
}
