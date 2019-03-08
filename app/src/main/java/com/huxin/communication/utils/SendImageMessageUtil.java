package com.huxin.communication.utils;

import android.app.Fragment;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.huxin.communication.controls.Constanst;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMImageElem;
import com.tencent.imsdk.TIMMessage;
import com.tencent.qcloud.uikit.business.chat.c2c.view.C2CChatCreatePanel;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfoUtil;
import com.tencent.qcloud.uikit.business.chat.view.ChatBottomInputGroup;
import com.tencent.qcloud.uikit.business.chat.view.ChatPanel;

public class SendImageMessageUtil {

    private Fragment mFragment;
    private ChatPanel mPanel;

    private ChatBottomInputGroup.MessageHandler msgHandler;

    public SendImageMessageUtil(Fragment fragment, ChatPanel panel) {
        this.mFragment = fragment;
        this.mPanel = panel;
        msgHandler = new ChatBottomInputGroup.MessageHandler() {
            @Override
            public void sendMessage(MessageInfo msg) {
                mPanel.sendMessage(msg);
            }
        };
    }

    public void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        mFragment.startActivityForResult(intent, Constanst.REQUEST_SYSTEM_PIC);//打开系统相册
    }

    private void handleImageOnKitkat(Intent data) {
        Uri uri = data.getData();
        sendMsg(uri);
    }

    public void sendImageMessage(Intent data) {
        handleImageOnKitkat(data);
    }

    private void sendMsg(Uri imageUri) {
        if (msgHandler != null && imageUri != null)
            msgHandler.sendMessage(MessageInfoUtil.buildImageMessage(imageUri, false, false));
    }

}
