package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/19.
 */

public class TicketingDuoXuanAdapter extends RecyclerView.Adapter<TicketingDuoXuanAdapter.MyViewHoder>{

    private List<TicketInfoEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;

    public TicketingDuoXuanAdapter(List<TicketInfoEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_ticketing_duoxuan_recycler, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
//        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, TicketingDetailsActivity.class);
//                mContext.startActivity(intent);
//            }
//        });
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextViewAddr.setText(list.get(position).getTicket_addr());
        holder.mTextViewName.setText(list.get(position).getTicket_name());
        holder.mTextViewOriginalPrice.setText(String.valueOf(list.get(position).getOriginal_price()));
        ImageLoader.getInstance().displayImage(list.get(position).getPhoto_url(),holder.mImageViewAddr);
        if (!TextUtils.isEmpty(list.get(position).getTagName())) {
            setTextView(list, position, holder.mRecyclerView);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mImageViewAddr;
        private TextView mTextViewName;
        private TextView mTextViewOriginalPrice;
        private TextView mTextViewAddr;


        private RecyclerView mRecyclerView;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.ticketing_line);
            mImageViewAddr = (ImageView) itemView.findViewById(R.id.image_ticket_addr);
            mTextViewName = (TextView) itemView.findViewById(R.id.ticket_name);
            mTextViewAddr = (TextView) itemView.findViewById(R.id.ticket_addr);
            mTextViewOriginalPrice = (TextView) itemView.findViewById(R.id.original_price);

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_ticket);
        }
    }

    private void setTextView(List<TicketInfoEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        String[] strings = list.get(position).getTagName().split(",");
        KyLog.d(list.get(position).getTagName());
        for (int i = 0; i < strings.length; i++) {
            list1.add(strings[i]);
        }
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 5);
            mAdapterTableName = new TableNameAdapter(list1, mContext);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }
}
