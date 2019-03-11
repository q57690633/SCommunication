package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.InformationDetailEntity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class SellDetailsAdapter extends RecyclerView.Adapter<SellDetailsAdapter.MyViewHoder> {

    private List<InformationDetailEntity> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private DetailsTableNameAdapter mAdapterTableName;
    private SellDetailsImageAdapter mImageAdapter;


    public SellDetailsAdapter(List<InformationDetailEntity> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_sell_details, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        KyLog.d(position + "");
        holder.mTextViewvillageName.setText(String.valueOf(list.get(position + 1).getVillageName()));
        holder.mTextViewhouseType.setText(String.valueOf(list.get(position + 1).getHouseType()));
        holder.mTextViewTotalPrice.setText(String.valueOf(list.get(position + 1).getTotalPrice()));
        holder.mTextViewUnitPrice.setText(String.valueOf(list.get(position + 1).getUnitPrice()));
        holder.mTextViewOrientation.setText(String.valueOf(list.get(position + 1).getOrientation()));
        holder.mTextViewAcreage.setText(String.valueOf(list.get(position + 1).getAcreage()));

        if (list.get(position + 1).getKeying() == 1) {
            holder.mImageViewKeying.setVisibility(View.VISIBLE);
        } else {
            holder.mImageViewKeying.setVisibility(View.GONE);
        }
        setTableNameAdapter(list, holder.mRecyclerViewTableName, position + 1);
        setImageAdapter(list, holder.mRecyclerViewImage, position + 1);
    }

    @Override
    public int getItemCount() {
//        if (list.size() >= 2) {
        return list.size() - 1;
//        }
//        return 0;
    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private TextView mTextViewvillageName;
        private TextView mTextViewhouseType;
        private TextView mTextViewAcreage;
        private TextView mTextViewTotalPrice;
        private ImageView mImageViewKeying;
        private TextView mTextViewUnitPrice;
        private TextView mTextViewOrientation;
        private RecyclerView mRecyclerViewTableName;
        private RecyclerView mRecyclerViewImage;

        public MyViewHoder(View itemView) {
            super(itemView);
            mTextViewvillageName = (TextView) itemView.findViewById(R.id.villageName_details);
            mTextViewhouseType = (TextView) itemView.findViewById(R.id.houseType_details);
            mTextViewAcreage = (TextView) itemView.findViewById(R.id.acreage_details);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice_details);
            mImageViewKeying = (ImageView) itemView.findViewById(R.id.keying_details);
            mTextViewUnitPrice = (TextView) itemView.findViewById(R.id.unitPrice_details);
            mTextViewOrientation = (TextView) itemView.findViewById(R.id.orientation_details);
            mRecyclerViewTableName = (RecyclerView) itemView.findViewById(R.id.recycler_details);
            mRecyclerViewImage = (RecyclerView) itemView.findViewById(R.id.recycler_details_image);

        }
    }


    private List<String> getTableNameList(String name) {
        List<String> tabNameList = new ArrayList<>();
        KyLog.d(name);
        if (!TextUtils.isEmpty(name)) {
            String[] strings = name.split(",");
            KyLog.d(name);
            for (String str : strings) {
                tabNameList.add(str);
            }
        }
        return tabNameList;
    }

    private void setTableNameAdapter(List<InformationDetailEntity> list, RecyclerView recyclerView, int position) {
        if (getTableNameList(list.get(position).getTabName()).size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 5);
            mAdapterTableName = new DetailsTableNameAdapter(getTableNameList(list.get(position).getTabName()), mContext);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
//            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void setImageAdapter(List<InformationDetailEntity> list, RecyclerView recyclerView, int position) {

        if (getTableNameList(list.get(position).getPhotoUrl()).size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 4);
            mImageAdapter = new SellDetailsImageAdapter(getTableNameList(list.get(position).getPhotoUrl()), mContext);
            recyclerView.setAdapter(mImageAdapter);
            recyclerView.setLayoutManager(manager);
//            recyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }
}
