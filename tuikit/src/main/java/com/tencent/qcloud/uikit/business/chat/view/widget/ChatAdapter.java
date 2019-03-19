package com.tencent.qcloud.uikit.business.chat.view.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMFaceElem;
import com.tencent.imsdk.TIMFileElem;
import com.tencent.imsdk.TIMImage;
import com.tencent.imsdk.TIMImageElem;
import com.tencent.imsdk.TIMImageType;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMSnapshot;
import com.tencent.imsdk.TIMSoundElem;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMVideo;
import com.tencent.imsdk.TIMVideoElem;
import com.tencent.imsdk.log.QLog;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.api.chat.IChatAdapter;
import com.tencent.qcloud.uikit.api.chat.IChatProvider;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;
import com.tencent.qcloud.uikit.business.chat.view.ChatIconView;
import com.tencent.qcloud.uikit.business.chat.view.ChatListView;
import com.tencent.qcloud.uikit.business.chat.view.itemdecoration.GridSpacingItemDecoration;
import com.tencent.qcloud.uikit.common.BackgroundTasks;
import com.tencent.qcloud.uikit.common.UIKitConstants;
import com.tencent.qcloud.uikit.common.component.audio.UIKitAudioArmMachine;
import com.tencent.qcloud.uikit.common.component.face.FaceManager;
import com.tencent.qcloud.uikit.common.component.picture.imageEngine.impl.GlideEngine;
import com.tencent.qcloud.uikit.common.component.video.VideoViewActivity;
import com.tencent.qcloud.uikit.common.utils.DateTimeUtil;
import com.tencent.qcloud.uikit.common.utils.FileUtil;
import com.tencent.qcloud.uikit.common.utils.UIUtils;
import com.tencent.qcloud.uikit.common.widget.photoview.PhotoViewActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by valxehuang on 2018/7/18.
 */

public class ChatAdapter extends IChatAdapter {
    private static final String TAG = "ChatAdapter";

    private boolean mLoading = true;
    private boolean mIsCustomMessage = false;
    private ChatListView mRecycleView;
    private List<MessageInfo> mDataSource = new ArrayList<>();

    private ChatListEvent mListEvent;

    private MessageInterceptor mInterceptor;

    public void setChatListEvent(ChatListEvent mListEvent) {
        this.mListEvent = mListEvent;
    }

    public void setEditor(MessageInterceptor interceptor) {
        this.mInterceptor = interceptor;
    }

    private static final int width = UIUtils.getPxByDp(100);
    private static final int height = UIUtils.getPxByDp(160);
    private static final int normal = UIUtils.getPxByDp(120);

    private static final int audio_min_width = UIUtils.getPxByDp(60);
    private static final int audio_max_width = UIUtils.getPxByDp(250);
    private static final int headerViewType = -99;
    private static List<String> downloadEles = new ArrayList();

    private String customMsgData = "";
    private Context mContext;
    private int count = -2;

    public ChatAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        count++;
        if (viewType == headerViewType) {
            LayoutInflater inflater = LayoutInflater.from(TUIKit.getAppContext());
            return new HeaderViewHolder(inflater.inflate(R.layout.chat_adapter_load_more, parent, false));
        }

        LayoutInflater inflater = LayoutInflater.from(TUIKit.getAppContext());
        RecyclerView.ViewHolder holder = new ChatTextHolder(inflater.inflate(R.layout.chat_adapter_text, parent, false));
        Log.i(TAG, "viewType = " + viewType);
        switch (viewType) {
            case MessageInfo.MSG_TYPE_TEXT:
                holder = new ChatTextHolder(inflater.inflate(R.layout.chat_adapter_text, parent, false));
                if(mIsCustomMessage) {
                    holder = new ChatCustomHolder(inflater.inflate(R.layout.chat_adapter_custom, parent, false));
                }
                break;
            case MessageInfo.MSG_TYPE_TEXT + 1:
                if (mRecycleView.isDivided()) {
                    holder = new ChatTextHolder(inflater.inflate(R.layout.chat_adapter_text_self, parent, false));
                } else {
                    holder = new ChatTextHolder(inflater.inflate(R.layout.chat_adapter_text, parent, false));
                }
                break;
            case MessageInfo.MSG_TYPE_HOUSE_CUSTOM:
            case MessageInfo.MSG_TYPE_BUSINESS_CARD_CUSTOM:
            case MessageInfo.MSG_TYPE_TRAVEL_CUSTOM:
                holder = new ChatCustomHolder(inflater.inflate(R.layout.chat_adapter_custom, parent, false));
                break;
            case MessageInfo.MSG_TYPE_HOUSE_CUSTOM + 1:
            case MessageInfo.MSG_TYPE_BUSINESS_CARD_CUSTOM + 1:
            case MessageInfo.MSG_TYPE_TRAVEL_CUSTOM + 1:
                if (mRecycleView.isDivided()) {
                    holder = new ChatCustomHolder(inflater.inflate(R.layout.chat_adapter_custom_self, parent, false));
                }else {
                    holder = new ChatCustomHolder(inflater.inflate(R.layout.chat_adapter_custom, parent, false));
                }
                break;
            case MessageInfo.MSG_TYPE_IMAGE:
            case MessageInfo.MSG_TYPE_VIDEO:
            case MessageInfo.MSG_TYPE_CUSTOM_FACE:
                holder = new ChatImageHolder(inflater.inflate(R.layout.chat_adapter_image, parent, false));
                break;
            case MessageInfo.MSG_TYPE_IMAGE + 1:
            case MessageInfo.MSG_TYPE_VIDEO + 1:
            case MessageInfo.MSG_TYPE_CUSTOM_FACE + 1:
                if (mRecycleView.isDivided())
                    holder = new ChatImageHolder(inflater.inflate(R.layout.chat_adapter_image_self, parent, false));
                else
                    holder = new ChatImageHolder(inflater.inflate(R.layout.chat_adapter_image, parent, false));
                break;
            case MessageInfo.MSG_TYPE_AUDIO:
                holder = new ChatAudioHolder(inflater.inflate(R.layout.chat_adapter_audio, parent, false));
                break;
            case MessageInfo.MSG_TYPE_AUDIO + 1:
                if (mRecycleView.isDivided())
                    holder = new ChatAudioHolder(inflater.inflate(R.layout.chat_adapter_audio_self, parent, false));
                else
                    holder = new ChatAudioHolder(inflater.inflate(R.layout.chat_adapter_audio, parent, false));

                break;
            case MessageInfo.MSG_TYPE_FILE:
                holder = new ChatFileHolder(inflater.inflate(R.layout.chat_adapter_file, parent, false));
                break;
            case MessageInfo.MSG_TYPE_FILE + 1:
                if (mRecycleView.isDivided())
                    holder = new ChatFileHolder(inflater.inflate(R.layout.chat_adapter_file_self, parent, false));
                else
                    holder = new ChatFileHolder(inflater.inflate(R.layout.chat_adapter_file, parent, false));
                break;

        }
        if (viewType >= MessageInfo.MSG_TYPE_TIPS) {
            holder = new ChatTipsHolder(inflater.inflate(R.layout.chat_adapter_tips, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (position == 0) {
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            if (mLoading) {
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                holder.itemView.setVisibility(View.VISIBLE);
            } else {
                holder.itemView.setVisibility(View.GONE);
                param.height = 0;
                param.width = 0;
            }
            holder.itemView.setLayoutParams(param);
            return;
        }


        final MessageInfo msg = getItem(position);
        final TIMMessage timMsg = msg.getTIMMessage();
        final BaseChatHolder chatHolder = (BaseChatHolder) holder;
        if (mRecycleView.getChatTimeBubble() != null) {
            chatHolder.chatTime.setBackground(mRecycleView.getChatTimeBubble());
        }
        if (mRecycleView.getChatTimeColor() != 0) {
            chatHolder.chatTime.setTextColor(mRecycleView.getChatTimeColor());
        }
        if (mRecycleView.getChatTimeSize() != 0) {
            chatHolder.chatTime.setTextSize(mRecycleView.getChatTimeSize());
        }


        if (position > 1) {
            TIMMessage last = getItem(position - 1).getTIMMessage();
            if (last != null) {
                if (timMsg.timestamp() - last.timestamp() >= 5 * 60) {
                    chatHolder.chatTime.setVisibility(View.VISIBLE);
                    chatHolder.chatTime.setText(DateTimeUtil.getTimeFormatText(new Date(timMsg.timestamp() * 1000)));
                } else {
                    chatHolder.chatTime.setVisibility(View.GONE);
                }
            }
        } else {
            chatHolder.chatTime.setVisibility(View.VISIBLE);
            chatHolder.chatTime.setText(DateTimeUtil.getTimeFormatText(new Date(timMsg.timestamp() * 1000)));

        }

        if (msg.getMsgType() >= MessageInfo.MSG_TYPE_TIPS) {
            ChatTipsHolder tipsHolder = (ChatTipsHolder) holder;
            if (mRecycleView.getTipsMessageBubble() != null) {
                tipsHolder.tips.setBackground(mRecycleView.getTipsMessageBubble());
            }
            if (mRecycleView.getTipsMessageColor() != 0) {
                tipsHolder.tips.setTextColor(mRecycleView.getTipsMessageColor());
            }
            if (mRecycleView.getTipsMessageSize() != 0) {
                tipsHolder.tips.setTextSize(mRecycleView.getTipsMessageSize());
            }

            if (msg.getStatus() == MessageInfo.MSG_STATUS_REVOKE) {
                if (msg.isSelf())
                    tipsHolder.tips.setText("您撤回了一条消息");
                else if (msg.isGroup()) {
                    tipsHolder.tips.setText(msg.getFromUser() + "撤回了一条消息");
                } else {
                    tipsHolder.tips.setText("对方撤回了一条消息");
                }

            } else if (msg.getMsgType() >= MessageInfo.MSG_TYPE_GROUP_CREATE && msg.getMsgType() <= MessageInfo.MSG_TYPE_GROUP_MODIFY_NOTICE) {
                if (msg.getExtra() != null)
                    tipsHolder.tips.setText(msg.getExtra().toString());
            }
            return;
        }
        if (msg.isSelf()) {
            if (chatHolder.dataView != null) {
                if (mRecycleView.getSelfBubble() != null) {
                    chatHolder.dataView.setBackground(mRecycleView.getSelfBubble());
                } else {
                    chatHolder.dataView.setBackgroundResource(R.drawable.chat_self_bg);
                }
            }

        } else {
            if (chatHolder.dataView != null) {
                if (mRecycleView.getOppositeBubble() != null) {
                    chatHolder.dataView.setBackground(mRecycleView.getOppositeBubble());
                } else {
                    chatHolder.dataView.setBackgroundResource(R.drawable.chat_opposite_bg);
                }
            }
        }

        if (mListEvent != null) {
            chatHolder.contentGroup.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListEvent.onMessageLongClick(v, position, msg);
                    return true;
                }
            });

            chatHolder.userIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListEvent.onUserIconClick(v, position, msg);
                }
            });

        }


        if (mRecycleView.getUserChatIcon() != null) {
            chatHolder.userIcon.setDynamicChatIconView(mRecycleView.getUserChatIcon());
        }
        chatHolder.userIcon.invokeInformation(msg);
        chatHolder.userIcon.setDefaultImageResId(R.drawable.default_head);

        switch (getItemViewType(position)) {
            case MessageInfo.MSG_TYPE_TEXT:
            case MessageInfo.MSG_TYPE_TEXT + 1:
                if(mIsCustomMessage) {
                    break;
                }
                if(chatHolder.getClass() != ChatTextHolder.class) {
                    return;
                }
                ChatTextHolder msgHolder = (ChatTextHolder) chatHolder;
                msgHolder.msg.setVisibility(View.VISIBLE);
                if (timMsg.getElement(0) instanceof TIMTextElem) {
                    TIMTextElem textElem = (TIMTextElem) timMsg.getElement(0);
                    FaceManager.handlerEmojiText(msgHolder.msg, textElem.getText());
                }
                if (mRecycleView.getContextSize() != 0) {
                    msgHolder.msg.setTextSize(mRecycleView.getContextSize());
                }
                if (msg.isSelf()) {
                    if (mRecycleView.getSelfContentColor() != 0) {
                        msgHolder.msg.setTextColor(mRecycleView.getSelfContentColor());
                    }
                } else {
                    if (mRecycleView.getOppositeContentColor() != 0) {
                        msgHolder.msg.setTextColor(mRecycleView.getOppositeContentColor());
                    }
                }
                break;
            case MessageInfo.MSG_TYPE_HOUSE_CUSTOM:
            case MessageInfo.MSG_TYPE_HOUSE_CUSTOM + 1:
                try {
                    TIMCustomElem customElem = (TIMCustomElem) timMsg.getElement(0);
                    byte[] data = customElem.getData();
                    String str = new String(data);
                    Log.i(TAG, "MSG_TYPE_HOUSE_CUSTOM str = " + str);
                    String totalPrice = "";
                    String unitPrice = "";
                    JSONObject jsonObject = new JSONObject(str);
                    JSONArray arr = jsonObject.getJSONArray("data");
                    JSONObject jsonData = arr.getJSONObject(0);
                    if(jsonObject.getInt("houseType") == 1) {
                        //出售
                        totalPrice = jsonData.getString("totalPrice");
                        unitPrice = jsonData.getString("unitPrice");
                        String villageName = jsonData.getString("villageName");
                        String houseType = jsonData.getString("houseType");
                        String acreage = "";
                        if(jsonData.has("acreage")) {
                            acreage = jsonData.getString("acreage");
                        }
                        int stick = jsonData.getInt("stick");
                        String orientation = jsonData.getString("orientation");
                        String tabId = jsonData.getString("tabId");
                        String tabName = jsonData.getString("tabName");
                        ChatCustomHolder customHolder = (ChatCustomHolder) chatHolder;
                        customHolder.villageName.setText(villageName);
                        customHolder.houseType.setText(houseType);
                        customHolder.acreage.setText(acreage + mContext.getResources().getString(R.string.custom_house_m2));
                        customHolder.totalPrice.setText(totalPrice);
                        customHolder.unitPrice.setText(unitPrice + mContext.getResources().getString(R.string.custom_unitPrice_sell_m2));
                        customHolder.orientation.setText(orientation);
                        customHolder.stick.setVisibility(View.GONE);
                        customHolder.productType.setBackgroundResource(R.drawable.sign_chhshou);
                        String[] tab = tabName.split(",");
                        ChatCustomMsgAdapter adapter = new ChatCustomMsgAdapter(tab);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
                        gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
                        customHolder.tabName_line.setLayoutManager(gridLayoutManager);
                        customHolder.tabName_line.addItemDecoration(new GridSpacingItemDecoration(12, 0));
                        customHolder.tabName_line.setAdapter(adapter);
                    }else if(jsonObject.getInt("houseType") == 2){
                        //出租
                        String villageName = jsonData.getString("villageName");
                        String houseType = jsonData.getString("houseType");
                        String acreage = jsonData.getString("acreage");
                        int stick = jsonData.getInt("stick");
                        String orientation = jsonData.getString("orientation");
                        String tabId = jsonData.getString("tabId");
                        String tabName = jsonData.getString("tabName");
                        ChatCustomHolder customHolder = (ChatCustomHolder) chatHolder;
                        customHolder.villageName.setText(villageName);
                        customHolder.houseType.setText(houseType);
                        customHolder.acreage.setText(acreage);
                        customHolder.totalPrice.setText(totalPrice);
                        customHolder.unitPrice.setText(unitPrice);
                        customHolder.orientation.setText(orientation);
                        customHolder.stick.setVisibility(View.GONE);
                        customHolder.productType.setBackgroundResource(R.drawable.sign_chuzu);
                        String[] tab = tabName.split(",");
                        ChatCustomMsgAdapter adapter = new ChatCustomMsgAdapter(tab);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
                        gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
                        customHolder.tabName_line.setLayoutManager(gridLayoutManager);
                        customHolder.tabName_line.addItemDecoration(new GridSpacingItemDecoration(12, 0));
                        customHolder.tabName_line.setAdapter(adapter);
                    }else if(jsonObject.getInt("houseType") == 3){
                        //求购
                        String villageName = jsonData.getString("villageName");
                        String houseType = jsonData.getString("houseType");
                        String acreage = jsonData.getString("acreage");
                        int stick = jsonData.getInt("stick");
                        String orientation = jsonData.getString("orientation");
                        String tabId = jsonData.getString("tabId");
                        String tabName = jsonData.getString("tabName");
                        ChatCustomHolder customHolder = (ChatCustomHolder) chatHolder;
                        customHolder.villageName.setText(villageName);
                        customHolder.houseType.setText(houseType);
                        customHolder.acreage.setText(acreage);
                        customHolder.totalPrice.setText(totalPrice);
                        customHolder.unitPrice.setText(unitPrice);
                        customHolder.orientation.setText(orientation);
                        customHolder.stick.setVisibility(View.GONE);
                        customHolder.productType.setBackgroundResource(R.drawable.sign_qiugou);
                        String[] tab = tabName.split(",");
                        ChatCustomMsgAdapter adapter = new ChatCustomMsgAdapter(tab);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
                        gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
                        customHolder.tabName_line.setLayoutManager(gridLayoutManager);
                        customHolder.tabName_line.addItemDecoration(new GridSpacingItemDecoration(12, 0));
                        customHolder.tabName_line.setAdapter(adapter);
                    }else if(jsonObject.getInt("houseType") == 4){
                        //求租
                        String villageName = jsonData.getString("villageName");
                        String houseType = jsonData.getString("houseType");
                        String acreage = jsonData.getString("acreage");
                        int stick = jsonData.getInt("stick");
                        String orientation = jsonData.getString("orientation");
                        String tabId = jsonData.getString("tabId");
                        String tabName = jsonData.getString("tabName");
                        ChatCustomHolder customHolder = (ChatCustomHolder) chatHolder;
                        customHolder.villageName.setText(villageName);
                        customHolder.houseType.setText(houseType);
                        customHolder.acreage.setText(acreage);
                        customHolder.totalPrice.setText(totalPrice);
                        customHolder.unitPrice.setText(unitPrice);
                        customHolder.orientation.setText(orientation);
                        customHolder.stick.setVisibility(View.GONE);
                        customHolder.productType.setBackgroundResource(R.drawable.sign_qiuzu);
                        String[] tab = tabName.split(",");
                        ChatCustomMsgAdapter adapter = new ChatCustomMsgAdapter(tab);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
                        gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
                        customHolder.tabName_line.setLayoutManager(gridLayoutManager);
                        customHolder.tabName_line.addItemDecoration(new GridSpacingItemDecoration(12, 0));
                        customHolder.tabName_line.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case MessageInfo.MSG_TYPE_BUSINESS_CARD_CUSTOM:
            case MessageInfo.MSG_TYPE_BUSINESS_CARD_CUSTOM + 1:
                try {
                    TIMCustomElem customElem = (TIMCustomElem) timMsg.getElement(0);
                    byte[] data = customElem.getData();
                    String str = new String(data);
                    Log.i(TAG, "MSG_TYPE_BUSINESS_CARD_CUSTOM str = " + str);
                    JSONObject jsonObject = new JSONObject(str);
                    ChatCustomHolder customHolder = (ChatCustomHolder) chatHolder;
                    customHolder.houseLayout.setVisibility(View.GONE);
                    customHolder.travelLayout.setVisibility(View.GONE);
                    customHolder.ticketingLayout.setVisibility(View.GONE);
                    customHolder.businessCardLayout.setVisibility(View.VISIBLE);
                    JSONObject dataObj = jsonObject.getJSONArray("info").getJSONObject(0);
                    ImageLoader.getInstance().displayImage(dataObj.getString("imageUrl"), customHolder.businessCardHeadUrl);
                    customHolder.businessCardName.setText(dataObj.getString("name"));
                    customHolder.businessCardPhone.setText(dataObj.getString("phone"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case MessageInfo.MSG_TYPE_TRAVEL_CUSTOM:
            case MessageInfo.MSG_TYPE_TRAVEL_CUSTOM + 1:
                try {
                    TIMCustomElem customElem = (TIMCustomElem) timMsg.getElement(0);
                    byte[] data = customElem.getData();
                    String str = new String(data);
                    Log.i(TAG, "MSG_TYPE_TRAVEL_CUSTOM str = " + str);
                    int travelType = new JSONObject(str).getInt("travelType");
                    if(1 == travelType || 2 == travelType) {
                        //国内游和周边游
                        JSONObject object = new JSONObject(str).getJSONObject("data");
                        JSONArray list = object.getJSONArray("list");
                        for(int i = 0; i < list.length(); i++) {
                            JSONObject dataObj = list.getJSONObject(i);
                            String userName = dataObj.getString("username");
                            String departName = dataObj.getString("depart_name");
                            String goalsCity = dataObj.getString("goals_city");
                            String totalPrice = dataObj.getString("totalPrice");
                            String returnPrice = dataObj.getString("finalPrice");
                            String totalPriceChild = dataObj.getString("totalPriceChild");
                            String returnPriceChild = dataObj.getString("finalPriceChild");
                            String spotName = dataObj.getString("spotName");
                            String tagName = dataObj.getString("tagName");
                            String numberDays = dataObj.getString("numberDays");
                            ChatCustomHolder customHolder = (ChatCustomHolder) chatHolder;
                            customHolder.houseLayout.setVisibility(View.GONE);
                            customHolder.travelLayout.setVisibility(View.VISIBLE);
                            customHolder.ticketingLayout.setVisibility(View.GONE);
                            customHolder.businessCardLayout.setVisibility(View.GONE);
                            customHolder.travelUserName.setText(userName);
                            customHolder.travelDepartName.setText(departName);
                            customHolder.travelGoalsCity.setText(goalsCity);
                            customHolder.travelNumberDays.setText(numberDays);
                            totalPrice = mContext.getResources().getString(R.string.custom_travel_adult) + totalPrice;
                            returnPrice = mContext.getResources().getString(R.string.custom_travel_return) + returnPrice;
                            totalPriceChild = mContext.getResources().getString(R.string.custom_travel_child) + totalPriceChild;
                            returnPriceChild = mContext.getResources().getString(R.string.custom_travel_return) + returnPriceChild;
                            customHolder.travelTotalPrice.setText(totalPrice);
                            customHolder.travelReturnPrice.setText(returnPrice);
                            customHolder.travelTotalPriceChild.setText(totalPriceChild);
                            customHolder.travelReturnPriceChild.setText(returnPriceChild);
                            customHolder.travelSpotName.setText(spotName);
                            ImageLoader.getInstance().displayImage(dataObj.getString("headUrl"), customHolder.travelHeadUrl);
                            String[] tab = tagName.split(",");
                            ChatCustomMsgAdapter adapter = new ChatCustomMsgAdapter(tab);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
                            gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
                            customHolder.travelTabRv.setLayoutManager(gridLayoutManager);
                            customHolder.travelTabRv.addItemDecoration(new GridSpacingItemDecoration(12, 5));
                            customHolder.travelTabRv.setAdapter(adapter);
                        }
                    }
                    if(3 == travelType) {
                        //境外游
                        JSONObject object = new JSONObject(str).getJSONObject("data");
                        JSONArray list = object.getJSONArray("list");
                        for(int i = 0; i < list.length(); i++) {
                            JSONObject dataObj = list.getJSONObject(i);
                            String userName = dataObj.getString("username");
                            String departName = dataObj.getString("depart_name");
                            String goalsCity = dataObj.getString("goals_name");
                            String totalPrice = dataObj.getString("total_price");
                            String returnPrice = dataObj.getString("return_price");
                            String totalPriceChild = dataObj.getString("total_price_child");
                            String returnPriceChild = dataObj.getString("return_price_child");
                            String spotName = dataObj.getString("spot_name");
                            String tagName = dataObj.getString("tagName");
                            String numberDays = dataObj.getString("number_days");
                            ChatCustomHolder customHolder = (ChatCustomHolder) chatHolder;
                            customHolder.houseLayout.setVisibility(View.GONE);
                            customHolder.travelLayout.setVisibility(View.VISIBLE);
                            customHolder.ticketingLayout.setVisibility(View.GONE);
                            customHolder.businessCardLayout.setVisibility(View.GONE);
                            customHolder.travelUserName.setText(userName);
                            customHolder.travelDepartName.setText(departName);
                            customHolder.travelGoalsCity.setText(goalsCity);
                            customHolder.travelNumberDays.setText(numberDays);
                            totalPrice = mContext.getResources().getString(R.string.custom_travel_adult) + totalPrice;
                            returnPrice = mContext.getResources().getString(R.string.custom_travel_return) + returnPrice;
                            totalPriceChild = mContext.getResources().getString(R.string.custom_travel_child) + totalPriceChild;
                            returnPriceChild = mContext.getResources().getString(R.string.custom_travel_return) + returnPriceChild;
                            customHolder.travelTotalPrice.setText(totalPrice);
                            customHolder.travelReturnPrice.setText(returnPrice);
                            customHolder.travelTotalPriceChild.setText(totalPriceChild);
                            customHolder.travelReturnPriceChild.setText(returnPriceChild);
                            customHolder.travelSpotName.setText(spotName);
                            ImageLoader.getInstance().displayImage(dataObj.getString("headUrl"), customHolder.travelHeadUrl);
                            String[] tab = tagName.split(",");
                            ChatCustomMsgAdapter adapter = new ChatCustomMsgAdapter(tab);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
                            gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
                            customHolder.travelTabRv.setLayoutManager(gridLayoutManager);
                            customHolder.travelTabRv.addItemDecoration(new GridSpacingItemDecoration(12, 5));
                            customHolder.travelTabRv.setAdapter(adapter);
                        }
                    }
                    if(4 == travelType) {
                        //票务
                        JSONObject object = new JSONObject(str).getJSONObject("data");
                        JSONArray list = object.getJSONArray("list");
                        for(int i = 0; i < list.length(); i++) {
                            JSONObject dataObj = list.getJSONObject(i);
                            String ticketName = dataObj.getString("ticket_name");
                            String ticketAddr = dataObj.getString("ticket_addr");
                            String ticketAdultPrice = dataObj.getString("original_price");
                            String tabName = dataObj.getString("tagName");
                            String[] tab = tabName.split(",");
                            ChatCustomHolder customHolder = (ChatCustomHolder) chatHolder;
                            customHolder.houseLayout.setVisibility(View.GONE);
                            customHolder.travelLayout.setVisibility(View.GONE);
                            customHolder.ticketingLayout.setVisibility(View.VISIBLE);
                            customHolder.businessCardLayout.setVisibility(View.GONE);
                            customHolder.ticketName.setText(ticketName);
                            customHolder.ticketAddr.setText(ticketAddr);
                            customHolder.ticketAdultPrice.setText(ticketAdultPrice);
                            ImageLoader.getInstance().displayImage(dataObj.getString("photo_url"), customHolder.ticketImageAddr);
                            ChatCustomMsgAdapter adapter = new ChatCustomMsgAdapter(tab);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
                            gridLayoutManager.setOrientation(GridLayout.HORIZONTAL);
                            customHolder.ticketTabRv.setLayoutManager(gridLayoutManager);
                            customHolder.ticketTabRv.addItemDecoration(new GridSpacingItemDecoration(12, 5));
                            customHolder.ticketTabRv.setAdapter(adapter);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case MessageInfo.MSG_TYPE_VIDEO:
            case MessageInfo.MSG_TYPE_VIDEO + 1:
            case MessageInfo.MSG_TYPE_IMAGE:
            case MessageInfo.MSG_TYPE_IMAGE + 1:
            case MessageInfo.MSG_TYPE_CUSTOM_FACE:
            case MessageInfo.MSG_TYPE_CUSTOM_FACE + 1:
                final ChatImageHolder imgHolder = (ChatImageHolder) chatHolder;
                imgHolder.imgData.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams params;
                //自定义表情的比较特殊，优先处理
                if (msg.getMsgType() == MessageInfo.MSG_TYPE_CUSTOM_FACE) {
                    imgHolder.cover.setVisibility(View.GONE);
                    imgHolder.playBtn.setVisibility(View.GONE);
                    imgHolder.mDuration.setVisibility(View.GONE);
                    params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.CENTER_IN_PARENT);
                    imgHolder.imgData.setLayoutParams(params);
                    if (timMsg.getElementCount() > 0) {
                        TIMFaceElem faceEle = (TIMFaceElem) timMsg.getElement(0);
                        imgHolder.imgData.setImageBitmap(FaceManager.getCustomBitmap(faceEle.getIndex(), new String(faceEle.getData())));
                    }
                    break;
                }

                if (msg.getImgWithd() < 200 && msg.getImgHeight() < 200) {
                    params = new RelativeLayout.LayoutParams(normal, normal);
                } else if (msg.getImgWithd() > msg.getImgHeight()) {
                    params = new RelativeLayout.LayoutParams(height, width);
                } else {
                    params = new RelativeLayout.LayoutParams(width, height);
                }
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                imgHolder.imgData.setLayoutParams(params);

                if (msg.getMsgType() == MessageInfo.MSG_TYPE_IMAGE) {
                    imgHolder.cover.setVisibility(View.GONE);
                    imgHolder.playBtn.setVisibility(View.GONE);
                    imgHolder.mDuration.setVisibility(View.GONE);
                    final TIMImageElem imageEle = (TIMImageElem) timMsg.getElement(0);
                    final List<TIMImage> imgs = imageEle.getImageList();
                    if (!TextUtils.isEmpty(msg.getDataPath())) {
                        GlideEngine.loadImage(imgHolder.imgData, msg.getDataPath(), null);
                    } else {
                        for (int i = 0; i < imgs.size(); i++) {
                            final TIMImage img = imgs.get(i);
                            if (img.getType() == TIMImageType.Thumb) {
                                synchronized (downloadEles) {
                                    if (downloadEles.contains(img.getUuid()))
                                        break;
                                    downloadEles.add(img.getUuid());
                                }
                                final String path = UIKitConstants.IMAGE_DOWNLOAD_DIR + img.getUuid();
                                img.getImage(path, new TIMCallBack() {
                                    @Override
                                    public void onError(int code, String desc) {
                                        downloadEles.remove(img.getUuid());
                                        QLog.e("ChatAdapter img getImage", code + ":" + desc);
                                    }

                                    @Override
                                    public void onSuccess() {
                                        downloadEles.remove(img.getUuid());
                                        msg.setDataPath(path);
                                        GlideEngine.loadImage(imgHolder.imgData, msg.getDataPath(), null);
                                    }
                                });
                                break;
                            }
                        }
                    }

                    imgHolder.imgData.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0; i < imgs.size(); i++) {
                                TIMImage img = imgs.get(i);
                                if (img.getType() == TIMImageType.Original) {
                                    PhotoViewActivity.mCurrentOriginalImage = img;
                                    break;
                                }
                            }
                            Intent intent = new Intent(TUIKit.getAppContext(), PhotoViewActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra(UIKitConstants.IMAGE_DATA, msg.getDataPath());
                            intent.putExtra(UIKitConstants.SELF_MESSAGE, msg.isSelf());
                            TUIKit.getAppContext().startActivity(intent);
                        }
                    });

                } else if (msg.getMsgType() == MessageInfo.MSG_TYPE_VIDEO) {
                    imgHolder.playBtn.setVisibility(View.VISIBLE);
                    imgHolder.cover.setLayoutParams(params);
                    imgHolder.mDuration.setVisibility(View.VISIBLE);
                    final TIMVideoElem videoEle = (TIMVideoElem) timMsg.getElement(0);
                    final TIMVideo video = videoEle.getVideoInfo();


                    if (!TextUtils.isEmpty(msg.getDataPath())) {
                        GlideEngine.loadImage(imgHolder.imgData, msg.getDataPath(), null);
                    } else {
                        final TIMSnapshot shotInfo = videoEle.getSnapshotInfo();
                        synchronized (downloadEles) {

                            if (downloadEles.contains(shotInfo.getUuid()))
                                break;
                            downloadEles.add(shotInfo.getUuid());
                        }

                        final String path = UIKitConstants.IMAGE_DOWNLOAD_DIR + videoEle.getSnapshotInfo().getUuid();
                        videoEle.getSnapshotInfo().getImage(path, new TIMCallBack() {
                            @Override
                            public void onError(int code, String desc) {
                                downloadEles.remove(shotInfo.getUuid());
                                QLog.e("ChatAdapter video getImage", code + ":" + desc);
                            }

                            @Override
                            public void onSuccess() {
                                downloadEles.remove(shotInfo.getUuid());
                                msg.setDataPath(path);
                                GlideEngine.loadImage(imgHolder.imgData, msg.getDataPath(), null);
                            }
                        });
                    }

                    String durations = "00:" + video.getDuaration();
                    if (video.getDuaration() < 10)
                        durations = "00:0" + video.getDuaration();
                    imgHolder.mDuration.setText(durations);
                    if (msg.isSelf()) {
                        imgHolder.cover.setVisibility(View.GONE);
                        registerVideoPlayClickListener(imgHolder.contentGroup, msg);
                    } else {

                        final String videoPath = UIKitConstants.VIDEO_DOWNLOAD_DIR + video.getUuid();
                        File videoFile = new File(videoPath);
                        //如果视频还未下载
                        if (!videoFile.exists()) {
                            imgHolder.cover.setVisibility(View.VISIBLE);
                            imgHolder.contentGroup.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ((BaseChatHolder) holder).progress.setVisibility(View.VISIBLE);
                                    video.getVideo(videoPath, new TIMCallBack() {
                                        @Override
                                        public void onError(int code, String desc) {
                                            UIUtils.toastLongMessage("下载视频失败:" + code + "=" + desc);
                                            ((BaseChatHolder) holder).progress.setVisibility(View.GONE);
                                            msg.setStatus(MessageInfo.MSG_STATUS_DOWNLOADED);
                                        }

                                        @Override
                                        public void onSuccess() {
                                            ((BaseChatHolder) holder).progress.setVisibility(View.GONE);
                                            registerVideoPlayClickListener(imgHolder.contentGroup, msg);
                                            imgHolder.cover.setVisibility(View.GONE);
                                            //下载完后自动播放
                                            Intent intent = new Intent(TUIKit.getAppContext(), VideoViewActivity.class);
                                            intent.putExtra(UIKitConstants.CAMERA_IMAGE_PATH, msg.getDataPath());
                                            intent.putExtra(UIKitConstants.CAMERA_VIDEO_PATH, msg.getDataUri());
                                            TUIKit.getAppContext().startActivity(intent);
                                        }
                                    });
                                }
                            });
                        } else {
                            registerVideoPlayClickListener(imgHolder.contentGroup, msg);
                        }
                    }
                }
                break;

            case MessageInfo.MSG_TYPE_AUDIO:
            case MessageInfo.MSG_TYPE_AUDIO + 1:
                final ChatAudioHolder audioHolder = (ChatAudioHolder) chatHolder;
                audioHolder.imgPlay.setImageResource(R.drawable.voice_msg_playing_3);
                final TIMSoundElem soundElem = (TIMSoundElem) msg.getTIMMessage().getElement(0);
                int duration = (int) soundElem.getDuration();
                if (duration == 0)
                    duration = 1;
                LinearLayout.LayoutParams audioParams = (LinearLayout.LayoutParams) chatHolder.dataView.getLayoutParams();
                audioParams.width = audio_min_width + UIUtils.getPxByDp(duration * 10);
                if (audioParams.width > audio_max_width)
                    audioParams.width = audio_max_width;
                // chatHolder.dataView.setLayoutParams(audioParams);
                audioHolder.time.setText(duration + "''");
                audioHolder.contentGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (UIKitAudioArmMachine.getInstance().isPlayingRecord()) {
                            UIKitAudioArmMachine.getInstance().stopPlayRecord();
                            return;
                        }

                        if (TextUtils.isEmpty(msg.getDataPath())) {
                            UIUtils.toastLongMessage("语音文件还未下载完成");
                            return;
                        }
                        audioHolder.imgPlay.setImageResource(R.drawable.play_voice_message);
                        final AnimationDrawable animationDrawable = (AnimationDrawable) audioHolder.imgPlay.getDrawable();
                        animationDrawable.start();
                        UIKitAudioArmMachine.getInstance().playRecord(msg.getDataPath(), new UIKitAudioArmMachine.AudioPlayCallback() {
                            @Override
                            public void playComplete() {
                                audioHolder.imgPlay.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        animationDrawable.stop();
                                        audioHolder.imgPlay.setImageResource(R.drawable.voice_msg_playing_3);
                                    }
                                });
                            }
                        });


                    }
                });
                break;

            case MessageInfo.MSG_TYPE_FILE:
            case MessageInfo.MSG_TYPE_FILE + 1:
                final ChatFileHolder fileHolder = (ChatFileHolder) chatHolder;
                final TIMFileElem fileElem = (TIMFileElem) msg.getTIMMessage().getElement(0);
                final String path = msg.getDataPath();
                fileHolder.fileName.setText(fileElem.getFileName());
                String size = FileUtil.FormetFileSize(fileElem.getFileSize());
                fileHolder.fileSize.setText(size);
                fileHolder.contentGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIUtils.toastLongMessage("文件路径:" + path);
                    }
                });
                if (msg.isSelf()) {

                    if (msg.getStatus() == MessageInfo.MSG_STATUS_SENDING) {
                        fileHolder.fileStatus.setText(R.string.sending);
                    } else if (msg.getStatus() == MessageInfo.MSG_STATUS_SEND_SUCCESS || msg.getStatus() == MessageInfo.MSG_STATUS_NORMAL) {
                        fileHolder.fileStatus.setText(R.string.sended);
                    }


                } else {
                    if (msg.getStatus() == MessageInfo.MSG_STATUS_DOWNLOADING) {
                        fileHolder.fileStatus.setText(R.string.downloading);
                    } else if (msg.getStatus() == MessageInfo.MSG_STATUS_DOWNLOADED) {
                        fileHolder.fileStatus.setText(R.string.downloaded);
                    } else if (msg.getStatus() == MessageInfo.MSG_STATUS_UN_DOWNLOAD) {
                        fileHolder.fileStatus.setText(R.string.un_download);
                        fileHolder.contentGroup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msg.setStatus(MessageInfo.MSG_STATUS_DOWNLOADING);
                                fileHolder.fileStatus.setText(R.string.downloading);
                                fileElem.getToFile(path, new TIMCallBack() {
                                    @Override
                                    public void onError(int code, String desc) {
                                        UIUtils.toastLongMessage("getToFile fail:" + code + "=" + desc);
                                        chatHolder.progress.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onSuccess() {
                                        //FileUtil.reNameFile(new File(path), fileElem.getFileName());
                                        msg.setDataPath(path);
                                        fileHolder.fileStatus.setText(R.string.downloaded);
                                        msg.setStatus(MessageInfo.MSG_STATUS_DOWNLOADED);
                                        chatHolder.progress.setVisibility(View.GONE);
                                        fileHolder.contentGroup.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                UIUtils.toastLongMessage("文件路径:" + path);

                                            }
                                        });
                                    }
                                });
                            }
                        });

                    }
                }
                break;
        }


        if (mRecycleView.getNameColor() != 0)

        {
            chatHolder.userName.setTextColor(mRecycleView.getNameColor());
        }
        if (mRecycleView.getNameSize() != 0)

        {
            chatHolder.userName.setTextSize(mRecycleView.getNameSize());
        }

        if (msg.isGroup())

        {
            chatHolder.userName.setVisibility(View.VISIBLE);
            chatHolder.userName.setText(msg.getFromUser());
        } else

        {
            chatHolder.userName.setVisibility(View.GONE);
        }
        if (msg.getStatus() == MessageInfo.MSG_STATUS_SEND_FAIL)

        {
            chatHolder.status.setVisibility(View.VISIBLE);
        } else

        {
            chatHolder.status.setVisibility(View.GONE);
        }

        if (msg.getStatus() == MessageInfo.MSG_STATUS_SENDING || msg.getStatus() == MessageInfo.MSG_STATUS_DOWNLOADING)

        {
            if (chatHolder.progress != null)
                chatHolder.progress.setVisibility(View.VISIBLE);
        } else

        {
            if (chatHolder.progress != null)
                chatHolder.progress.setVisibility(View.GONE);
        }
        //if(mIsCustomMessage) {

        //}
        customMsgData = "";
        mIsCustomMessage = false;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecycleView = (ChatListView) recyclerView;
    }

    public void showLoading() {
        if (mLoading)
            return;
        mLoading = true;
        notifyItemChanged(0, "adcd");
    }

    public void notifyDataSetChanged(final int type, final int value) {
       /* QLog.i("notifyDataSetChanged", "type=" + type + ":value=" + value);
        QLog.i("notifyDataSetChanged", Log.getStackTraceString(new Throwable()));*/
        BackgroundTasks.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoading = false;
                if (type == ChatListView.DATA_CHANGE_TYPE_REFRESH) {
                    notifyDataSetChanged();
                    mRecycleView.scrollToEnd();
                } else if (type == ChatListView.DATA_CHANGE_TYPE_ADD_BACK) {
                    notifyItemRangeInserted(mDataSource.size() + 1, value);
                    mRecycleView.scrollToEnd();
                } else if (type == ChatListView.DATA_CHANGE_TYPE_UPDATE) {
                    System.out.println("================" + System.currentTimeMillis());
                    notifyItemChanged((Integer) value + 1, "abcd");
                } else if (type == ChatListView.DATA_CHANGE_TYPE_LOAD || type == ChatListView.DATA_CHANGE_TYPE_ADD_FRONT) {
                    //加载条目为数0，只更新动画
                    if (value == 0) {
                        notifyItemChanged(0, "abcd");
                    } else {
                        //加载过程中有可能之前第一条与新加载的最后一条的时间间隔不超过5分钟，时间条目需去掉，所以这里的刷新要多一个条目
                        if (getItemCount() > value) {
                            notifyItemRangeInserted(0, value);
                            //notifyItemChanged(value + 1, "abcd");
                        } else {
                            notifyItemRangeInserted(0, value);
                        }

                    }

                } else if (type == ChatListView.DATA_CHANGE_TYPE_DELETE) {
                    notifyItemRemoved(value + 1);
                }
            }
        }, 100);


    }


    private void registerVideoPlayClickListener(View view, final MessageInfo msg) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TUIKit.getAppContext(), VideoViewActivity.class);
                intent.putExtra(UIKitConstants.CAMERA_IMAGE_PATH, msg.getDataPath());
                intent.putExtra(UIKitConstants.CAMERA_VIDEO_PATH, msg.getDataUri());
                TUIKit.getAppContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSource.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return headerViewType;
        MessageInfo msg = getItem(position);
        return msg.isSelf() ? msg.getMsgType() + 1 : msg.getMsgType();
    }

    @Override
    public void setDataSource(IChatProvider provider) {
        this.mDataSource = provider.getDataSource();
        provider.attachAdapter(this);
        notifyDataSetChanged(ChatListView.DATA_CHANGE_TYPE_REFRESH, getItemCount());
    }

    @Override
    public MessageInfo getItem(int position) {
        if (position == 0 || mDataSource == null || mDataSource.size() == 0)
            return null;
        MessageInfo info = mDataSource.get(position - 1);
        if (mInterceptor != null)
            mInterceptor.intercept(info);
        return info;
    }


    class BaseChatHolder extends RecyclerView.ViewHolder {
        protected ChatIconView userIcon;
        protected ImageView status;
        protected TextView userName, chatTime;
        protected ViewGroup contentGroup, dataView;
        protected ProgressBar progress;
        protected View rootView;

        public BaseChatHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            userName = itemView.findViewById(R.id.tv_user_name);
            chatTime = itemView.findViewById(R.id.chat_time);
            userIcon = itemView.findViewById(R.id.iv_user_icon);
            contentGroup = itemView.findViewById(R.id.ll_content_group);
            dataView = itemView.findViewById(R.id.ll_msg_data_group);
            status = itemView.findViewById(R.id.message_status);
            progress = itemView.findViewById(R.id.message_sending);
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


    class ChatTextHolder extends BaseChatHolder {
        private TextView msg;

        public ChatTextHolder(View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.tv_user_msg);

        }
    }

    class ChatImageHolder extends BaseChatHolder {
        private ImageView imgData;
        private ImageView playBtn;
        private View cover;
        private RelativeLayout mDataGroup;
        private TextView mDuration;

        public ChatImageHolder(View itemView) {
            super(itemView);
            imgData = itemView.findViewById(R.id.iv_user_image);
            playBtn = itemView.findViewById(R.id.video_play_btn);
            cover = itemView.findViewById(R.id.video_un_download_cover);
            mDataGroup = itemView.findViewById(R.id.image_data_group);
            mDuration = itemView.findViewById(R.id.video_duration);
        }
    }

    class ChatAudioHolder extends BaseChatHolder {
        private ImageView imgPlay;
        private ImageView unread;
        private TextView time;


        public ChatAudioHolder(View itemView) {
            super(itemView);
            imgPlay = itemView.findViewById(R.id.audio_play);
            unread = itemView.findViewById(R.id.unread_flag);
            time = itemView.findViewById(R.id.audio_time);
        }
    }


    class ChatFileHolder extends BaseChatHolder {
        private ImageView fileIcon;
        private TextView fileName, fileSize, fileStatus;

        public ChatFileHolder(View itemView) {
            super(itemView);
            fileIcon = itemView.findViewById(R.id.file_image);
            fileName = itemView.findViewById(R.id.file_name);
            fileSize = itemView.findViewById(R.id.file_size);
            fileStatus = itemView.findViewById(R.id.file_status);
        }
    }

    class ChatTipsHolder extends BaseChatHolder {

        private TextView tips;

        public ChatTipsHolder(View itemView) {
            super(itemView);
            chatTime = itemView.findViewById(R.id.chat_time);
            tips = itemView.findViewById(R.id.chat_tips);
        }
    }

    class ChatCustomHolder extends BaseChatHolder {

        private LinearLayout houseLayout;
        private LinearLayout travelLayout;
        private LinearLayout ticketingLayout;
        private LinearLayout businessCardLayout;
        //房产
        private TextView villageName;//name
        private TextView houseType;//几房几厅
        private TextView acreage;//面积
        private TextView totalPrice;//总价
        private TextView stick;//置顶
        private ImageView productType;//类型
        private TextView unitPrice;//单价
        private TextView orientation;//朝向
        private RecyclerView tabName_line;//标签
        //名片
        private ImageView businessCardHeadUrl;
        private TextView businessCardName;
        private TextView businessCardPhone;
        //旅游
        private TextView travelUserName;
        private TextView travelDepartName;
        private TextView travelGoalsCity;
        private TextView travelNumberDays;
        private TextView travelTotalPrice;
        private TextView travelReturnPrice;
        private TextView travelTotalPriceChild;
        private TextView travelReturnPriceChild;
        private TextView travelSpotName;
        private RecyclerView travelTabRv;
        private ImageView travelHeadUrl;
        //票务
        private TextView ticketName;
        private TextView ticketAddr;
        private TextView ticketAdultPrice;
        private RecyclerView ticketTabRv;
        private ImageView ticketImageAddr;

        public ChatCustomHolder(View itemView) {
            super(itemView);
            houseLayout = itemView.findViewById(R.id.house_id);
            travelLayout = itemView.findViewById(R.id.travel_id);
            ticketingLayout = itemView.findViewById(R.id.ticketing_id);
            businessCardLayout = itemView.findViewById(R.id.business_card_id);
            tabName_line = itemView.findViewById(R.id.tabName_line_sell);
            if(houseLayout != null) {
                villageName = houseLayout.findViewById(R.id.villageName_sell);
                houseType = houseLayout.findViewById(R.id.houseType_sell);
                acreage = houseLayout.findViewById(R.id.acreage_sell);
                totalPrice = houseLayout.findViewById(R.id.totalPrice_sell);
                stick = houseLayout.findViewById(R.id.stick_sell);
                unitPrice = houseLayout.findViewById(R.id.unitPrice_sell);
                orientation = houseLayout.findViewById(R.id.orientation_sell);
                productType = houseLayout.findViewById(R.id.type);
            }
            if(businessCardLayout != null) {
                businessCardHeadUrl = businessCardLayout.findViewById(R.id.headUrl_iv);
                businessCardName = businessCardLayout.findViewById(R.id.name_tv);
                businessCardPhone = businessCardLayout.findViewById(R.id.phone_tv);
            }
            if(travelLayout != null) {
                travelUserName = travelLayout.findViewById(R.id.username);
                travelDepartName = travelLayout.findViewById(R.id.depart_name);
                travelGoalsCity = travelLayout.findViewById(R.id.goals_city);
                travelNumberDays = travelLayout.findViewById(R.id.numberDays);
                travelTotalPrice = travelLayout.findViewById(R.id.totalPrice);
                travelReturnPrice = travelLayout.findViewById(R.id.returnPrice);
                travelTotalPriceChild = travelLayout.findViewById(R.id.totalPriceChild);
                travelReturnPriceChild = travelLayout.findViewById(R.id.returnPriceChild);
                travelSpotName = travelLayout.findViewById(R.id.spotName);
                travelTabRv = travelLayout.findViewById(R.id.recycler_travel);
                travelHeadUrl = travelLayout.findViewById(R.id.headUrl);
            }
            if(ticketingLayout != null) {
                ticketName = ticketingLayout.findViewById(R.id.ticket_name);
                ticketAddr = ticketingLayout.findViewById(R.id.ticket_addr);
                ticketAdultPrice = ticketingLayout.findViewById(R.id.original_price);
                ticketTabRv = ticketingLayout.findViewById(R.id.recycler_ticket);
                ticketImageAddr = ticketingLayout.findViewById(R.id.image_ticket_addr);
            }
        }
    }
}