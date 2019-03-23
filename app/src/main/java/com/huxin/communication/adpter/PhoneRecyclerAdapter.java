package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huxin.communication.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.L;

import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/20.
 */

public class PhoneRecyclerAdapter  extends RecyclerView.Adapter<PhoneRecyclerAdapter.MyViewHoder>{
    private List<String> list;
    private Context mContext;
    private LayoutInflater mInflater;

    public void setList(List<String> lists){
        if (lists != null){
            list = lists;
        }
        notifyDataSetChanged();
    }


    public PhoneRecyclerAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_phone_top, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);

        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        if (!TextUtils.isEmpty(list.get(position))) {
            ImageLoader.getInstance().displayImage(list.get(position), holder.mImageView);
        } else {
            holder.mImageView.setBackgroundResource(R.drawable.head2);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public MyViewHoder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
