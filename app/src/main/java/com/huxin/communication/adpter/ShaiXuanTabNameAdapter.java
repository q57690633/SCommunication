package com.huxin.communication.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShaiXuanTabNameAdapter extends RecyclerView.Adapter<ShaiXuanTabNameAdapter.MyViewHoder> {

    private List<String> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private int positions = -1;
    private int type;


    public ShaiXuanTabNameAdapter(List<String> list, Context mContext, int type) {
        this.list = list;
        this.mContext = mContext;
        this.type = type;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recycler_details_tablename, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);

        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positions == hoder.getAdapterPosition()) {
                    positions = -1;

                    KyLog.d("");
                    if (type == 1) {
                        PreferenceUtil.removeSp(Constanst.CHAO_XIANG, Constanst.SP_NAME);
                    } else if (type == 2) {
                        PreferenceUtil.removeSp(Constanst.FANG_BEN, Constanst.SP_NAME);
                    } else if (type == 3) {
                        PreferenceUtil.removeSp(Constanst.JIA_JU_JIA_DIAN, Constanst.SP_NAME);

                    } else if (type == 4) {
                        PreferenceUtil.removeSp(Constanst.LOU_LING, Constanst.SP_NAME);

                    } else if (type == 5) {
                        PreferenceUtil.removeSp(Constanst.ZHUANG_XIU, Constanst.SP_NAME);

                    } else if (type == 6) {
                        PreferenceUtil.removeSp(Constanst.YONG_TU, Constanst.SP_NAME);

                    }

                    notifyDataSetChanged();
                    return;
                }
                KyLog.d(positions + "");
                hoder.mTextView.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                hoder.mTextView.setTextColor(mContext.getResources().getColor(R.color.blue));
//                name = list.get(hoder.getAdapterPosition());

                if (type == 1) {
                    PreferenceUtil.putString(Constanst.CHAO_XIANG, list.get(hoder.getAdapterPosition()));
                } else if (type == 2) {
                    PreferenceUtil.putString(Constanst.FANG_BEN, list.get(hoder.getAdapterPosition()));
                } else if (type == 3) {
                    PreferenceUtil.putString(Constanst.JIA_JU_JIA_DIAN, list.get(hoder.getAdapterPosition()));

                } else if (type == 4) {
                    PreferenceUtil.putString(Constanst.LOU_LING, list.get(hoder.getAdapterPosition()));

                } else if (type == 5) {
                    PreferenceUtil.putString(Constanst.ZHUANG_XIU, list.get(hoder.getAdapterPosition()));

                } else if (type == 6) {
                    PreferenceUtil.putString(Constanst.YONG_TU, list.get(hoder.getAdapterPosition()));

                }
                positions = hoder.getAdapterPosition();
                notifyDataSetChanged();



            }
        });
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, int position) {
        holder.mTextView.setText(list.get(position));
        KyLog.d(positions + " == positions+onBindViewHolder");
        KyLog.d(position + " == onBindViewHolder");
        if (positions != position) {
            holder.mTextView.setBackgroundResource(R.drawable.biaoqian_radius);
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.sell_font));
        } else {
            holder.mTextView.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.blue));
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
