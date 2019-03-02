package com.huxin.communication.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.TabTravelNameEntity;
import com.huxin.communication.ui.travel.details.ZhouBianDetailsActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ZhouBianDuoXuanAdapter extends RecyclerView.Adapter<ZhouBianDuoXuanAdapter.MyViewHoder>{
    private List<AroundTravelEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;
    private Set<String> setTab = new HashSet<>();

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;
    public ZhouBianDuoXuanAdapter(List<AroundTravelEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        if (list == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
    }
    //更新adpter的数据和选择状态
    public void updateDataSet(ArrayList<AroundTravelEntity.ListBean> list) {
        this.list = list;
        mSelectedPositions = new SparseBooleanArray();
//        ab.setTitle("已选择" + 0 + "项");
    }


    //获得选中条目的结果
    public ArrayList<AroundTravelEntity.ListBean> getSelectedItem() {
        ArrayList<AroundTravelEntity.ListBean> selectList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(list.get(i));
                    setTab.add(String.valueOf(list.get(i).getId()));
                } else {
                    setTab.remove(String.valueOf(list.get(i).getId()));
                }
            }
        }
        return selectList;
    }


    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_jingwai_rcycler_duoxuan, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(hoder.getAdapterPosition())) {
                    setItemChecked(hoder.getAdapterPosition(), false);
                } else {
                    setItemChecked(hoder.getAdapterPosition(), true);
                }
                notifyItemChanged(hoder.getAdapterPosition());
                getSelectedItem();
                Iterator<String> iterator=setTab.iterator();
                String userStr = null;
                while(iterator.hasNext()){
                    userStr += iterator.next() + ",";
                }
                Toast.makeText(mContext, userStr.substring(4,userStr.length() - 1).trim() + "", Toast.LENGTH_SHORT);
                PreferenceUtil.putString(Constanst.PID_TRAVEL_COLLECT,userStr.substring(4,userStr.length() - 1).trim());
            }
        });
        return hoder;
    }
    //设置给定位置条目的选择状态
    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
    }

    //根据位置判断条目是否选中
    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    //根据位置判断条目是否可选
    private boolean isSelectable() {
        return mIsSelectable;
    }

    //设置给定位置条目的可选与否的状态
    private void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextViewDepartName.setText(list.get(position).getDepart_name());
        holder.mTextViewUsername.setText(list.get(position).getUsername());
        holder.mTextViewUserCity.setText(list.get(position).getUserCity());
        holder.mTextViewGoalsCity.setText(list.get(position).getGoals_city());
        holder.mTextViewNumberDays.setText("行程天数：" + list.get(position).getNumberDays() + "天");
        holder.mTextViewTotalPrice.setText("成人：" + list.get(position).getTotalPrice() + "元");
        holder.mTextViewReturnPrice.setText("返" + list.get(position).getReturnPrice() + "元");
        holder.mTextViewTotalPriceChild.setText("儿童：" + list.get(position).getTotalPriceChild() + "元");
        holder.mTextViewReturnPriceChild.setText("返" + list.get(position).getReturnPriceChild() + "元");
        holder.mTextViewSpotName.setText(list.get(position).getSpotName());

        ImageLoader.getInstance().displayImage(list.get(position).getPhoto_url(), holder.mImageViewPhoto);
        ImageLoader.getInstance().displayImage(list.get(position).getHeadUrl(), holder.mImageViewHeadUrl);

        if (!TextUtils.isEmpty(list.get(position).getTagName())) {
            setTextView(list, position, holder.mRecyclerView);
        }

        if (isItemChecked(position)) {
            holder.mImageViewDuoXuan.setBackgroundResource(R.drawable.icon_circle_selected);
//            strings.add(list.get(position));

        } else {
            holder.mImageViewDuoXuan.setBackgroundResource(R.drawable.icon_circle_normal);
        }

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

        private ImageView mImageViewDuoXuan;
        private RecyclerView mRecyclerView;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.jin_wai_line);
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
            mImageViewDuoXuan =(ImageView) itemView.findViewById(R.id.image_duoxuan);

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_travel);
        }
    }

    private void setTextView(List<AroundTravelEntity.ListBean> list, int position, RecyclerView linearLayout) {
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
