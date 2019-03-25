package com.huxin.communication.adpter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.base.HomeFragmentMsgDBHelper;
import com.huxin.communication.entity.GetMessageEntity;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.utils.DateUtil;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.utils.SQLiteUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
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
        View view = mInflater.inflate(R.layout.recycler_home_item, parent, false);
        hoder = new MyViewHoder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.time.setText(DateUtil.timeslashData(String.valueOf(list.get(position).getTimeStamp())));
        holder.num.setText(String.valueOf(list.get(position).getNum()));
        holder.msg.setText(String.valueOf(list.get(position).getMsg()));
        holder.nickname.setText(list.get(position).getNickName());
        if (!TextUtils.isEmpty(list.get(position).getHead_url())){
            ImageLoader.getInstance().displayImage(list.get(position).getHead_url(),holder.image);
        }else {
            holder.image.setBackgroundResource(R.drawable.head2);
        }

        if (list.get(position).getNum() == 0 || list.get(position).isRead()){
            holder.numRl.setVisibility(View.GONE);
        }else {
            holder.numRl.setVisibility(View.VISIBLE);
        }
        hoder.Tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,TIMChatActivity.class);
                intent.putExtra("TARGET_TYPE", list.get(position).getType());
                intent.putExtra("TARGET_ID", list.get(position).getId() + "");
                intent.putExtra("from", "homefragment");
                KyLog.i("position.getId = " + list.get(position).getId() + "");
                updateDataBase(list.get(position).getId() + "");
                list.get(position).setRead(true);
                notifyDataSetChanged();
                mContext.startActivity(intent);
            }
        });

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
        private TextView nickname;

        public MyViewHoder(View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.num);
            msg = itemView.findViewById(R.id.msg);
            image = itemView.findViewById(R.id.image_head);
            time = itemView.findViewById(R.id.time);
            numRl = itemView.findViewById(R.id.rl_num);
            Tm = itemView.findViewById(R.id.line_tm);
            nickname = itemView.findViewById(R.id.nickname);

        }
    }

    private void updateDataBase(String peer) {
        SQLiteUtil util = new SQLiteUtil(mContext);
        Cursor cursor = util.query(HomeFragmentMsgDBHelper.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            String uid = cursor.getString(cursor.getColumnIndex(HomeFragmentMsgDBHelper.UID));
            if (peer.equalsIgnoreCase(uid)) {
                ContentValues values = new ContentValues();
                values.put("isread", "true");
                util.update(HomeFragmentMsgDBHelper.TABLE_NAME, values, "uid = ?", new String[]{uid});
            }
        }
    }

}
