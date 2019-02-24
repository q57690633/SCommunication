package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.AroundStickEntity;
import com.huxin.communication.entity.TicketStickEntity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class TopSelectionTicketAdapter extends RecyclerView.Adapter<TopSelectionTicketAdapter.MyViewHoder>{

    private List<TicketStickEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;

    public TopSelectionTicketAdapter(List<TicketStickEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_zhoubian_recycler, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
//        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ZhouBianDetailsActivity.class);
//                intent.putExtra("position",hoder.getAdapterPosition());
//                mContext.startActivity(intent);
//            }
//        });
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextViewUsername.setText(list.get(position).getUsername());
        holder.mTextViewUserCity.setText(list.get(position).getUserCity());
        holder.mTextViewGoalsCity.setText(list.get(position).getGoals_city());

        setTextView(list, position, holder.mRecyclerView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        private LinearLayout mLinearLayout;
        private ImageView mImageViewPhoto;
        private ImageView mImageViewStickName;
        private ImageView mImageViewHeadUrl;
        private TextView mTextViewUsername;
        private TextView mTextViewUserCity;
        private TextView mTextViewDepartName;
        private TextView mTextViewGoalsCity;
        private TextView mTextViewNumberDays;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewReturnPrice;
        private TextView mTextViewTotalPriceChild;
        private TextView mTextViewReturnPriceChild;
        private TextView mTextViewSpotName;
        private TextView mTextViewKanxingcheng;
        private TextView mTextViewSendMessage;

        private RecyclerView mRecyclerView;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.zou_bian_line);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_photo_url);
            mImageViewStickName = (ImageView) itemView.findViewById(R.id.stick_name);
            mImageViewHeadUrl = (ImageView) itemView.findViewById(R.id.headUrl);
            mTextViewUsername = (TextView) itemView.findViewById(R.id.username);
            mTextViewGoalsCity = (TextView) itemView.findViewById(R.id.goals_city);
            mTextViewUserCity = (TextView) itemView.findViewById(R.id.userCity);
            mTextViewDepartName = (TextView) itemView.findViewById(R.id.depart_name);
            mTextViewNumberDays = (TextView) itemView.findViewById(R.id.numberDays);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewReturnPrice = (TextView) itemView.findViewById(R.id.returnPrice);
            mTextViewTotalPriceChild = (TextView) itemView.findViewById(R.id.totalPriceChild);
            mTextViewReturnPriceChild = (TextView) itemView.findViewById(R.id.returnPriceChild);
            mTextViewSpotName = (TextView) itemView.findViewById(R.id.spotName);
            mTextViewKanxingcheng = (TextView) itemView.findViewById(R.id.kanxingcheng);
            mTextViewSendMessage = (TextView) itemView.findViewById(R.id.sendMessage);

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_travel);
        }
    }

    private void setTextView(List<TicketStickEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        String[] strings = list.get(position).getTagName().split(",");
        KyLog.d(list.get(position).getTagName());
        for (int i = 0; i < strings.length; i++){
            list1.add(strings[i]);
        }
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 3);
            mAdapterTableName = new TableNameAdapter(list1, mContext);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }
}
