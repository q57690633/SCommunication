package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.TabTravelNameEntity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TableTravelOverseasAdapter extends RecyclerView.Adapter<TableTravelOverseasAdapter.MyViewHoder>{
    private List<TabTravelNameEntity.ThemeList> list;
    private Context mContext;
    private LayoutInflater mInflater;


    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;

    private Set<String> strings = new HashSet<>();
    private Set<Integer> integers = new HashSet<>();
    private boolean isClicked = true;

    private List<String> Tablist;


    public void setTabList(List<String> list) {
        this.Tablist = list;
        notifyDataSetChanged();
    }

    //更新adpter的数据和选择状态
    public void updateDataSet(ArrayList<TabTravelNameEntity.ThemeList> list) {
        this.list = list;
        mSelectedPositions = new SparseBooleanArray();
//        ab.setTitle("已选择" + 0 + "项");
    }


    //获得选中条目的结果
    public ArrayList<TabTravelNameEntity.ThemeList> getSelectedItem() {
        ArrayList<TabTravelNameEntity.ThemeList> selectList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(list.get(i));
                    strings.add(list.get(i).getTagName());
                }else {
                    strings.remove(list.get(i).getTagName());
                }
            }
        }
        return selectList;
    }

    public TableTravelOverseasAdapter(List<TabTravelNameEntity.ThemeList> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        if (list == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_details_tablename, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KyLog.d(hoder.getAdapterPosition() + "");

                if (isItemChecked(hoder.getAdapterPosition())) {
                    setItemChecked(hoder.getAdapterPosition(), false);
                } else {
                    setItemChecked(hoder.getAdapterPosition(), true);
                }
                notifyItemChanged(hoder.getAdapterPosition());
                getSelectedItem();

               Iterator<String> iterator=strings.iterator();
                String userStr = null;
                while(iterator.hasNext()){
                    userStr += iterator.next() + ",";
                }
                if (!TextUtils.isEmpty(userStr)){
                    PreferenceUtil.putString(Constanst.TICKET_OVERSEAS_NAME,userStr.substring(4,userStr.length() - 1).trim());
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
        holder.mTextView.setText(list.get(position).getTagName());
        if (isItemChecked(position)) {
            holder.mTextView.setBackgroundResource(R.drawable.biaoqian_radius_blue);
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.white));
//            strings.add(list.get(position));

        } else {
            holder.mTextView.setBackgroundResource(R.drawable.biaoqian_radius);
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.sell_font));
//            strings.remove(list.get(position));
        }


        if (Tablist != null && Tablist.size() > 0) {
            for (String tabNmae : Tablist) {
                if (list.get(position).getTagName().equalsIgnoreCase(tabNmae)) {
                    holder.mTextView.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                    holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.blue));
                    setItemChecked(position, true);
                    strings.add(tabNmae);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    class MyViewHoder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private LinearLayout mLinearLayout;


        public MyViewHoder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_table_name);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.line_table);

        }
    }
}
