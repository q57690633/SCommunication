package com.tencent.qcloud.uikit.business.chat.view.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.TUIKit;

import java.util.ArrayList;

public class ChatCustomTitleAdapter extends RecyclerView.Adapter<ChatCustomTitleAdapter.TabViewHoder> {

    private ArrayList<String> tabData;
    private int type = 0;
    private Context mContext;

    public ChatCustomTitleAdapter(Context context, ArrayList<String> tabData, int type) {
        this.mContext = context;
        this.tabData = tabData;
        this.type = type;
    }

    @Override
    public TabViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(TUIKit.getAppContext());
        return new TabViewHoder(inflater.inflate(R.layout.custom_msg_tab, parent, false));
    }

    @Override
    public void onBindViewHolder(TabViewHoder holder, int position) {
        holder.tab.setText(tabData.get(position));
    }

    @Override
    public int getItemCount() {
        return null != tabData ? tabData.size() : 0;
    }

    public class TabViewHoder extends RecyclerView.ViewHolder {

        private TextView tab;
        private LinearLayout tabLl;

        public TabViewHoder(View itemView) {
            super(itemView);
            tab = (TextView) itemView.findViewById(R.id.tab);
            tabLl = (LinearLayout) itemView.findViewById(R.id.tab_ll);
        }
    }

}
