package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxin.communication.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class SellDetailsImageAdapter extends RecyclerView.Adapter<SellDetailsImageAdapter.MyViewHoder>{

    private List<String> list;
    private Context mContext;
    private LayoutInflater mInflater;

    public SellDetailsImageAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_details_image, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);

        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
//        holder.mImageView.setText(list.get(position));
        ImageLoader.getInstance().displayImage(list.get(position),holder.mImageView);
    }

    @Override
    public int getItemCount() {
        if (list.size() > 4){
            return 4;
        }
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public MyViewHoder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_details);
        }
    }
}
