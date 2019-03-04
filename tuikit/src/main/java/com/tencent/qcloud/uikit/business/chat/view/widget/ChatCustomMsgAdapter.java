package com.tencent.qcloud.uikit.business.chat.view.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.TUIKit;

public class ChatCustomMsgAdapter extends RecyclerView.Adapter<ChatCustomMsgAdapter.TabViewHoder> {

    private String[] tabData;

    public ChatCustomMsgAdapter(String[] tabData) {
        this.tabData = tabData;
    }

    @Override
    public TabViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(TUIKit.getAppContext());
        return new TabViewHoder(inflater.inflate(R.layout.custom_msg_tab, parent, false));
    }

    @Override
    public void onBindViewHolder(TabViewHoder holder, int position) {
        holder.tab.setText(tabData[position]);
    }

    @Override
    public int getItemCount() {
        return null != tabData ? tabData.length : 0;
    }

    public class TabViewHoder extends RecyclerView.ViewHolder {

        private TextView tab;

        public TabViewHoder(View itemView) {
            super(itemView);
            tab = (TextView) itemView.findViewById(R.id.tab);
        }
    }

}
