package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ShaiXuanTabNameAdapter extends RecyclerView.Adapter<ShaiXuanTabNameAdapter.MyViewHoder> {

    private List<String> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private int positions = -1;
    private int positionsAfater = -1;

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;

    private Set<String> strings = new HashSet<>();
    private Set<Integer> integers = new HashSet<>();
    private boolean isClicked = true;
    private int type;


    //更新adpter的数据和选择状态
    public void updateDataSet(ArrayList<String> list) {
        this.list = list;
        mSelectedPositions = new SparseBooleanArray();
//        ab.setTitle("已选择" + 0 + "项");
    }


    //获得选中条目的结果
    public ArrayList<String> getSelectedItem() {
        ArrayList<String> selectList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(list.get(i));
                    strings.add(list.get(i));
                }else {
                    strings.remove(list.get(i));
                }
            }
        }
        return selectList;
    }


    public ShaiXuanTabNameAdapter(List<String> list, Context mContext, int type) {
        this.list = list;
        this.mContext = mContext;
        this.type = type;
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

//                Toast.makeText(mContext, "已选择" + getSelectedItem().size() + "项", Toast.LENGTH_SHORT).show();

                if (type == 1) {
                    PreferenceUtil.putString(Constanst.CHAO_XIANG,userStr.substring(4,userStr.length() - 1).trim());
                } else if (type == 2) {
                    PreferenceUtil.putString(Constanst.FANG_BEN, userStr.substring(4,userStr.length() - 1).trim());
                } else if (type == 3) {
                    PreferenceUtil.putString(Constanst.JIA_JU_JIA_DIAN, userStr.substring(4,userStr.length() - 1).trim());

                } else if (type == 4) {
                    PreferenceUtil.putString(Constanst.LOU_LING, userStr.substring(4,userStr.length() - 1).trim());

                } else if (type == 5) {
                    PreferenceUtil.putString(Constanst.ZHUANG_XIU, userStr.substring(4,userStr.length() - 1).trim());

                } else if (type == 6) {
                    PreferenceUtil.putString(Constanst.YONG_TU, userStr.substring(4,userStr.length() - 1).trim());

                } else if (type == 7) {
                    PreferenceUtil.putString(Constanst.HUO_DONG,userStr.substring(4,userStr.length() - 1).trim());

                } else if (type == 8) {
                    PreferenceUtil.putString(Constanst.ZHU_SHU, userStr.substring(4,userStr.length() - 1).trim());

                } else if (type == 9) {
                    PreferenceUtil.putString(Constanst.XIAO_FEI, userStr.substring(4,userStr.length() - 1).trim());

                } else if (type == 10) {
                    PreferenceUtil.putString(Constanst.QI_TA, userStr.substring(4,userStr.length() - 1).trim());

                } else if (type == 11) {
                    PreferenceUtil.putString(Constanst.JIAO_TONG, userStr.substring(4,userStr.length() - 1).trim());

                } else if (type == 12) {
                    PreferenceUtil.putString(Constanst.DI_DIAN, userStr.substring(4,userStr.length() - 1).trim());

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
        holder.mTextView.setText(list.get(position));
        KyLog.d(position + " == onBindViewHolder" + "isItemChecked(position) == " + isItemChecked(position));

        if (isItemChecked(position)) {
            holder.mTextView.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.blue));
//            strings.add(list.get(position));

        } else {
            holder.mTextView.setBackgroundResource(R.drawable.biaoqian_radius);
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.sell_font));
//            strings.remove(list.get(position));
        }

        KyLog.d(strings.toString());


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
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
