package com.huxin.communication.view.chatmenuunit;

import android.os.Handler;
import android.view.View;

import com.huxin.communication.R;
import com.huxin.communication.listener.MessageUnitClickListener;
import com.tencent.qcloud.uikit.business.chat.view.widget.MessageOperaUnit;

public class JingWaiYouUnit extends MessageOperaUnit implements View.OnClickListener{
    private int iconResId;

    private int titleId;

    private String action;

    private View.OnClickListener onClickListener;

    private MessageUnitClickListener messageUnitClickListener;

    public JingWaiYouUnit() {
        iconResId = R.drawable.tab_icon_jingwaiyou;
        titleId = R.string.title_tab_icon_jingwaiyou;
        action = "This is action";
        onClickListener = this;
    }

    @Override
    public int getIconResId() {
        return iconResId;
    }

    @Override
    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    @Override
    public int getTitleId() {
        return titleId;
    }

    @Override
    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    @Override
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        if(messageUnitClickListener != null) {
            messageUnitClickListener.onClick(iconResId);
        }
    }

    public MessageUnitClickListener getMessageUnitClickListener() {
        return messageUnitClickListener;
    }

    public void setMessageUnitClickListener(MessageUnitClickListener messageUnitClickListener) {
        this.messageUnitClickListener = messageUnitClickListener;
    }
}
