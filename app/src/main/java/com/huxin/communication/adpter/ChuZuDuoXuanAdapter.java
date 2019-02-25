package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.BuyerScreeningEntity;
import com.huxin.communication.entity.RentalScreeningEntity;
import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChuZuDuoXuanAdapter extends RecyclerView.Adapter<ChuZuDuoXuanAdapter.MyViewHoder> {

    private static final String PID = "pid";

    private List<RentalScreeningEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private boolean isClicked = false;
    private int position = -1;
    private TableNameAdapter mAdapterTableName;

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;

    private Set<String> strings = new HashSet<>();

    //更新adpter的数据和选择状态
    public void updateDataSet(ArrayList<RentalScreeningEntity.ListBean> list) {
        this.list = list;
        mSelectedPositions = new SparseBooleanArray();
//        ab.setTitle("已选择" + 0 + "项");
    }


    //获得选中条目的结果
    public ArrayList<RentalScreeningEntity.ListBean> getSelectedItem() {
        ArrayList<RentalScreeningEntity.ListBean> selectList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(list.get(i));
                    strings.add(String.valueOf(list.get(i).getId()));
                } else {
                    strings.remove(String.valueOf(list.get(i).getId()));
                }
            }
        }
        return selectList;
    }

    public ChuZuDuoXuanAdapter(List<RentalScreeningEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        if (list == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_sell_duoxuan, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                KyLog.d(isClicked + "" + position + "" + hoder.getAdapterPosition());
                if (isItemChecked(hoder.getAdapterPosition())) {
                    setItemChecked(hoder.getAdapterPosition(), false);
                } else {
                    setItemChecked(hoder.getAdapterPosition(), true);
                }
                notifyItemChanged(hoder.getAdapterPosition());
                getSelectedItem();

                PreferenceUtil.putString(Constanst.PID_COLLECT, strings.toString());
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

        holder.mTextViewvillageName.setText(String.valueOf(list.get(position).getVillageName()));
        holder.mTextViewhouseType.setText(String.valueOf(list.get(position).getHouseType()));
        holder.mTextViewTotalPrice.setText(String.valueOf(list.get(position).getTotalPrice()));
        holder.mTextViewUnitPrice.setText(String.valueOf(list.get(position).getUnitPrice()));
        holder.mTextViewOrientation.setText(String.valueOf(list.get(position).getOrientation()));
        holder.mTextViewAcreage.setText(String.valueOf(list.get(position).getAcreage()));

        if (isItemChecked(position)) {
            holder.mImageViewClicked.setVisibility(View.VISIBLE);
            holder.mImageView.setVisibility(View.GONE);
//            strings.add(list.get(position));

        } else {
            holder.mImageViewClicked.setVisibility(View.GONE);
            holder.mImageView.setVisibility(View.VISIBLE);
        }

        if (list.get(position).getKeying() == 1) {
            holder.mImageViewKeying.setVisibility(View.VISIBLE);
        } else {
            holder.mImageViewKeying.setVisibility(View.GONE);
        }

        if (list.get(position).getStick() == 1) {
            holder.mTextViewStick.setVisibility(View.VISIBLE);
        } else {
            holder.mTextViewStick.setVisibility(View.GONE);
        }

        if (list.get(position).getExclusive() == 1) {
            holder.mTextView.setText("独家");
        } else {
            holder.mTextView.setText("相似房源");
        }
        setTextView(list, position, holder.mLinearLayoutTabName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mImageView;
        private ImageView mImageViewClicked;
        private TextView mTextView;
        private TextView mTextViewvillageName;
        private TextView mTextViewhouseType;
        private TextView mTextViewAcreage;
        private TextView mTextViewTotalPrice;
        private ImageView mImageViewKeying;
        private TextView mTextViewStick;
        private TextView mTextViewUnitPrice;
        private TextView mTextViewOrientation;
        private RecyclerView mLinearLayoutTabName;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.sell_line);
            mImageView = (ImageView) itemView.findViewById(R.id.image_duoxuan);
            mImageViewClicked = (ImageView) itemView.findViewById(R.id.image_duoxuan_clicked);
            mTextView = (TextView) itemView.findViewById(R.id.textView_duoxuan_similar);
            mTextViewvillageName = (TextView) itemView.findViewById(R.id.villageName_duoxuan_sell);
            mTextViewhouseType = (TextView) itemView.findViewById(R.id.houseType_duoxuan_sell);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage_duoxuan_sell);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice_duoxuan_sell);
            mImageViewKeying = (ImageView) itemView.findViewById(R.id.keying_duoxuan_sell);
            mTextViewStick = (TextView) itemView.findViewById(R.id.stick_duoxuan_sell);
            mTextViewUnitPrice = (TextView) itemView.findViewById(R.id.unitPrice_duoxuan_sell);
            mTextViewOrientation = (TextView) itemView.findViewById(R.id.orientation_duoxuan_sell);
            mLinearLayoutTabName = (RecyclerView) itemView.findViewById(R.id.tabName_line_duoxuan_sell);
        }
    }

    private void setTextView(List<RentalScreeningEntity.ListBean> list, int position, RecyclerView linearLayout) {

        List<String> list1 = new ArrayList<>();
        String[] strings = list.get(position).getTabName().split(",");
        KyLog.d(list.get(position).getTabName());
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
