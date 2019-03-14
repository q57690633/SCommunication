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
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by yangzanxiong on 2018/12/19.
 */

public class TicketingDuoXuanAdapter extends RecyclerView.Adapter<TicketingDuoXuanAdapter.MyViewHoder> {

    private List<TicketInfoEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;

    private Set<String> setTab = new HashSet<>();

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;

    public TicketingDuoXuanAdapter(List<TicketInfoEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        if (list == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
    }

    //更新adpter的数据和选择状态
    public void updateDataSet(ArrayList<TicketInfoEntity.ListBean> list) {
        this.list = list;
        mSelectedPositions = new SparseBooleanArray();
//        ab.setTitle("已选择" + 0 + "项");
    }


    //获得选中条目的结果
    public ArrayList<TicketInfoEntity.ListBean> getSelectedItem() {
        ArrayList<TicketInfoEntity.ListBean> selectList = new ArrayList<>();
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
        View view = mInflater.inflate(R.layout.item_ticketing_duoxuan_recycler, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isItemChecked(hoder.getAdapterPosition())) {
                    setItemChecked(hoder.getAdapterPosition(), false);
                } else {
                    setItemChecked(hoder.getAdapterPosition(), true);
                }
                notifyItemChanged(hoder.getAdapterPosition());
                getSelectedItem();
                Iterator<String> iterator = setTab.iterator();
                String userStr = null;
                while (iterator.hasNext()) {
                    userStr += iterator.next() + ",";
                }
                if (!TextUtils.isEmpty(userStr)) {
                    PreferenceUtil.putString(Constanst.PID_TRAVEL_COLLECT, userStr.substring(4, userStr.length() - 1).trim());
                }
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
        holder.mTextViewAddr.setText(list.get(position).getTicket_addr());
        holder.mTextViewName.setText(list.get(position).getTicket_name());
        holder.mTextViewOriginalPrice.setText(String.valueOf(list.get(position).getOriginal_price()));
        ImageLoader.getInstance().displayImage(list.get(position).getPhoto_url(), holder.mImageViewAddr);
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
        private ImageView mImageViewAddr;
        private TextView mTextViewName;
        private TextView mTextViewOriginalPrice;
        private TextView mTextViewAddr;


        private RecyclerView mRecyclerView;
        private ImageView mImageViewDuoXuan;


        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.ticketing_line);
            mImageViewAddr = (ImageView) itemView.findViewById(R.id.image_ticket_addr);
            mTextViewName = (TextView) itemView.findViewById(R.id.ticket_name);
            mTextViewAddr = (TextView) itemView.findViewById(R.id.ticket_addr);
            mTextViewOriginalPrice = (TextView) itemView.findViewById(R.id.original_price);
            mImageViewDuoXuan = (ImageView) itemView.findViewById(R.id.image_duoxuan);


            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_ticket);
        }
    }

    private void setTextView(List<TicketInfoEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        if (!TextUtils.isEmpty(list.get(position).getTagName())) {

            String[] strings = list.get(position).getTagName().split(",");
            KyLog.d(list.get(position).getTagName());
            for (int i = 0; i < strings.length; i++) {
                list1.add(strings[i]);
            }
        }
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 2);
            mAdapterTableName = new TableNameAdapter(list1, mContext);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
//            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }
}
