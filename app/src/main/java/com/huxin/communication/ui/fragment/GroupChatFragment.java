package com.huxin.communication.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huxin.communication.R;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.listener.MessageUnitClickListener;
import com.huxin.communication.ui.house.release.ReleaseActivity;
import com.huxin.communication.ui.house.release.ReleaseBuyActivity;
import com.huxin.communication.ui.house.release.ReleaseRentActivity;
import com.huxin.communication.ui.house.release.ReleseLaseActivity;
import com.huxin.communication.ui.house.sell.QiuGouActivity;
import com.huxin.communication.ui.house.sell.QiuZuActivity;
import com.huxin.communication.ui.house.sell.RentActivity;
import com.huxin.communication.ui.house.sell.SellActivity;
import com.huxin.communication.ui.travel.details.DomesticDetailsActivity;
import com.huxin.communication.ui.travel.details.JinWaiDetailsActivity;
import com.huxin.communication.ui.travel.details.TicketingDetailsActivity;
import com.huxin.communication.ui.travel.details.ZhouBianDetailsActivity;
import com.huxin.communication.ui.travel.release.OverseasReleaseActivity;
import com.huxin.communication.ui.travel.release.ReleaseGuoNeiActivity;
import com.huxin.communication.ui.travel.release.ReleaseTicketingActivity;
import com.huxin.communication.ui.travel.release.ReleaseZhouBoundaryActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.utils.SendImageMessageUtil;
import com.huxin.communication.view.chatmenuunit.DataBaseUnit;
import com.huxin.communication.view.chatmenuunit.FavoriteUnit;
import com.huxin.communication.view.chatmenuunit.GuoNeiYouUnit;
import com.huxin.communication.view.chatmenuunit.JingWaiYouUnit;
import com.huxin.communication.view.chatmenuunit.PhotoUnit;
import com.huxin.communication.view.chatmenuunit.PiaoWuUnit;
import com.huxin.communication.view.chatmenuunit.QiuGouUnit;
import com.huxin.communication.view.chatmenuunit.QiuZuUnit;
import com.huxin.communication.view.chatmenuunit.RentUnit;
import com.huxin.communication.view.chatmenuunit.SellUnit;
import com.huxin.communication.view.chatmenuunit.ZhouBianYouUnit;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.business.chat.c2c.view.C2CChatPanel;
import com.tencent.qcloud.uikit.business.chat.group.view.GroupChatPanel;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfoUtil;
import com.tencent.qcloud.uikit.business.chat.view.ChatBottomInputGroup;
import com.tencent.qcloud.uikit.business.chat.view.widget.MessageOperaUnit;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupChatFragment extends BaseFragment implements MessageUnitClickListener {
    private View mBaseView;
    private GroupChatPanel chatPanel;
    private PageTitleBar chatTitleBar;
    private String type = "";
    private String chatId = "";
    private String data = "";
    private String from = "";
    private ChatBottomInputGroup.MessageHandler msgHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.chat_fragment_group, container, false);
        init();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if("tuijian".equalsIgnoreCase(from)) {
                    sendBusinessCard();
                }
                if("zhuanfa".equalsIgnoreCase(from)) {
                    sendZhuanFa();
                }
            }
        }, 1000);
        return mBaseView;
    }

    private void init() {
        msgHandler = new ChatBottomInputGroup.MessageHandler() {
            @Override
            public void sendMessage(MessageInfo msg) {
                chatPanel.sendMessage(msg);
            }
        };
        Bundle datas = getArguments();
        //由会话列表传入的会话ID
        if(datas != null) {
            chatId = datas.getString("TARGET_ID");
            type = datas.getString("TARGET_TYPE");
            data = datas.getString("data");
            from = datas.getString("from");
        }
        initView();
    }

    private void initView() {
        List<MessageOperaUnit> units = initUnitList();

        //从布局文件中获取聊天面板组件
        chatPanel = mBaseView.findViewById(R.id.chat_panel);
        //单聊组件的默认UI和交互初始化
        chatPanel.initDefault();
        chatPanel.initDefaultEvent();
        /*
         * 需要指定会话ID（即聊天对象的identify，具体可参考IMSDK接入文档）来加载聊天消息。在上一章节SessionClickListener中回调函数的参数SessionInfo对象中持有每一会话的会话ID，所以在会话列表点击时都可传入会话ID。
         * 特殊的如果用户应用不具备类似会话列表相关的组件，则需自行实现逻辑获取会话ID传入。
         */
        if("".equalsIgnoreCase(chatId)) {
            chatId = "";
        }
        KyLog.i("TARGETID = " + chatId);
        //chatId = "@TGS#2444HEXFF";// @TGS#2444HEXFF
        chatPanel.setBaseChatId(chatId);
        chatPanel.setMoreOperaUnits(units, false);

        //获取单聊面板的标题栏
        chatTitleBar = chatPanel.getTitleBar();
        //单聊面板标记栏返回按钮点击事件，这里需要开发者自行控制
        chatTitleBar.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        verifyAudioPermissions(getActivity());
    }

    private List<MessageOperaUnit> initUnitList() {
        List<MessageOperaUnit> units = new ArrayList<>();
        //1 house    2 travel
        int type = PreferenceUtil.getInt("type");
        if(type == 1) {
            SellUnit sellUnit = new SellUnit();
            RentUnit rentUnit = new RentUnit();
            QiuGouUnit qiuGouUnit = new QiuGouUnit();
            QiuZuUnit qiuZuUnit = new QiuZuUnit();
            PhotoUnit photoUnit = new PhotoUnit();
            DataBaseUnit dataBaseUnit = new DataBaseUnit();
            FavoriteUnit favoriteUnit = new FavoriteUnit();
            sellUnit.setMessageUnitClickListener(this);
            rentUnit.setMessageUnitClickListener(this);
            qiuGouUnit.setMessageUnitClickListener(this);
            qiuZuUnit.setMessageUnitClickListener(this);
            photoUnit.setMessageUnitClickListener(this);
            dataBaseUnit.setMessageUnitClickListener(this);
            favoriteUnit.setMessageUnitClickListener(this);
            units.add(sellUnit);
            units.add(rentUnit);
            units.add(qiuGouUnit);
            units.add(qiuZuUnit);
            units.add(photoUnit);
            units.add(dataBaseUnit);
            units.add(favoriteUnit);
        }else {
            GuoNeiYouUnit guoNeiYouUnit = new GuoNeiYouUnit();
            ZhouBianYouUnit zhouBianYouUnit = new ZhouBianYouUnit();
            JingWaiYouUnit jingWaiYouUnit = new JingWaiYouUnit();
            PiaoWuUnit piaoWuUnit = new PiaoWuUnit();
            PhotoUnit photoUnit = new PhotoUnit();
            DataBaseUnit dataBaseUnit = new DataBaseUnit();
            FavoriteUnit favoriteUnit = new FavoriteUnit();
            guoNeiYouUnit.setMessageUnitClickListener(this);
            zhouBianYouUnit.setMessageUnitClickListener(this);
            jingWaiYouUnit.setMessageUnitClickListener(this);
            piaoWuUnit.setMessageUnitClickListener(this);
            photoUnit.setMessageUnitClickListener(this);
            dataBaseUnit.setMessageUnitClickListener(this);
            favoriteUnit.setMessageUnitClickListener(this);
            units.add(guoNeiYouUnit);
            units.add(zhouBianYouUnit);
            units.add(jingWaiYouUnit);
            units.add(piaoWuUnit);
            units.add(photoUnit);
            units.add(dataBaseUnit);
            units.add(favoriteUnit);
        }
        return units;
    }

    private void sendBusinessCard() {
        if(data == null) {
            return;
        }
        try {
            JSONObject dataJson = new JSONObject(data);
            msgHandler.sendMessage(MessageInfoUtil.buildBussinessCardCustomMessage(dataJson.toString().getBytes(), "Business Card"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendZhuanFa() {
        if(data == null) {
            return;
        }
        try {
            JSONObject dataJson = new JSONObject(data);
            if("1".equalsIgnoreCase(dataJson.getString("type"))) {
                for(int i = 0; i < dataJson.getJSONArray("data").length(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("type", Integer.parseInt(dataJson.getString("type")));
                    jsonObj.put("houseType", Integer.parseInt(dataJson.getString("houseType")));
                    JSONArray arr = new JSONArray();
                    jsonObj.put("data", arr.put(dataJson.getJSONArray("data").get(i)));
                    msgHandler.sendMessage(MessageInfoUtil.buildHouseCustomMessage(jsonObj.toString().getBytes(), "Zhuan Fa"));
                }
            }
            if("2".equalsIgnoreCase(dataJson.getString("type"))) {
                for(int i = 0; i < dataJson.getJSONObject("data").getJSONArray("list").length(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("type", dataJson.getInt("type"));
                    jsonObj.put("travelType", dataJson.getInt("travelType"));
                    JSONObject data = new JSONObject();
                    JSONArray arr = new JSONArray();
                    data.put("list", arr.put(dataJson.getJSONObject("data").getJSONArray("list").get(i)));
                    jsonObj.put("data", data);
                    msgHandler.sendMessage(MessageInfoUtil.buildTravelCustomMessage(jsonObj.toString().getBytes(), "Zhuan Fa"));
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(int iconResId) {
        switch (iconResId) {
            case R.drawable.tab_icon_sell:
                Intent intent = new Intent(getActivity(), ReleaseActivity.class);
                intent.putExtra("type", "GROUP");
                intent.putExtra("peer", chatId);
                startActivityForResult(intent, 2);
                break;
            case R.drawable.tab_icon_rent:
                Intent intentRent = new Intent(getActivity(), ReleseLaseActivity.class);
                intentRent.putExtra("type", "GROUP");
                intentRent.putExtra("peer", chatId);
                startActivityForResult(intentRent, 2);
                break;
            case R.drawable.tab_icon_qiugou:
                Intent intentQiuGou = new Intent(getActivity(), ReleaseBuyActivity.class);
                intentQiuGou.putExtra("type", "GROUP");
                intentQiuGou.putExtra("peer", chatId);
                startActivityForResult(intentQiuGou, 2);
                break;
            case R.drawable.tab_icon_qiuzu:
                Intent intentQiuZu= new Intent(getActivity(), ReleaseRentActivity.class);
                intentQiuZu.putExtra("type", "GROUP");
                intentQiuZu.putExtra("peer", chatId);
                startActivityForResult(intentQiuZu, 2);
                break;
            case R.drawable.tab_icon_guoneiyou:
                Intent intentGuoNei = new Intent(getActivity(), ReleaseGuoNeiActivity.class);
                intentGuoNei.putExtra("type", "GROUP");
                intentGuoNei.putExtra("peer", chatId);
                startActivityForResult(intentGuoNei, 2);
                break;
            case R.drawable.tab_icon_zhoubianyou:
                Intent intentZhouBian = new Intent(getActivity(), ReleaseZhouBoundaryActivity.class);
                intentZhouBian.putExtra("type", "GROUP");
                intentZhouBian.putExtra("peer", chatId);
                startActivityForResult(intentZhouBian, 2);
                break;
            case R.drawable.tab_icon_jingwaiyou:
                Intent intentJingWai = new Intent(getActivity(), OverseasReleaseActivity.class);
                intentJingWai.putExtra("type", "GROUP");
                intentJingWai.putExtra("peer", chatId);
                startActivityForResult(intentJingWai, 2);
                break;
            case R.drawable.tab_icon_piowu:
                Intent intentPiaoWu = new Intent(getActivity(), ReleaseTicketingActivity.class);
                intentPiaoWu.putExtra("type", "GROUP");
                intentPiaoWu.putExtra("peer", chatId);
                startActivityForResult(intentPiaoWu, 2);
                break;
            case R.drawable.tab_icon_photo:
                new SendImageMessageUtil(this, chatPanel).openAlbum();
                break;
            case R.drawable.tab_icon_database:
                break;
            case R.drawable.tab_icon_favorite:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == 2) {
            String str = data.getStringExtra("msg");
            KyLog.i("onActivityResult str = " + str);
            try {
                JSONObject dataJson = new JSONObject(str);
                if(dataJson.getInt("type") == 1) {
                    msgHandler.sendMessage(MessageInfoUtil.buildHouseCustomMessage(str.getBytes(), "sell message"));
                }
                if(dataJson.getInt("type") == 2) {
                    JSONArray arr = dataJson.getJSONArray("arrData");
                    for(int i = 0; i < arr.length(); i++) {
                        msgHandler.sendMessage(MessageInfoUtil.buildTravelCustomMessage(arr.getJSONObject(i).toString().getBytes(), "Travel message"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if(resultCode == Activity.RESULT_OK && requestCode == Constanst.REQUEST_SYSTEM_PIC) {
            new SendImageMessageUtil(this, chatPanel).sendImageMessage(data);
        }
    }

    public static void verifyAudioPermissions(Activity activity) {
        int GET_RECODE_AUDIO = 1;
        String[] PERMISSION_AUDIO = {
                Manifest.permission.RECORD_AUDIO
        };
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);
        }
    }

}
