package com.huxin.communication.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huxin.communication.R;
import com.huxin.communication.listener.MessageUnitClickListener;
import com.huxin.communication.ui.house.sell.QiuGouActivity;
import com.huxin.communication.ui.house.sell.QiuZuActivity;
import com.huxin.communication.ui.house.sell.RentActivity;
import com.huxin.communication.ui.house.sell.SellActivity;
import com.huxin.communication.ui.travel.details.DomesticDetailsActivity;
import com.huxin.communication.ui.travel.details.JinWaiDetailsActivity;
import com.huxin.communication.ui.travel.details.TicketingDetailsActivity;
import com.huxin.communication.ui.travel.details.ZhouBianDetailsActivity;
import com.huxin.communication.utils.PreferenceUtil;
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
import com.tencent.qcloud.uikit.business.chat.view.widget.MessageOperaUnit;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupChatFragment extends BaseFragment implements MessageUnitClickListener {
    private View mBaseView;
    private GroupChatPanel chatPanel;
    private PageTitleBar chatTitleBar;
    private String type = "";
    private String chatId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.chat_fragment_group, container, false);
        Bundle datas = getArguments();
        //由会话列表传入的会话ID
        if(datas != null) {
            chatId = datas.getString("TARGET_ID");
            type = datas.getString("TARGET_TYPE");
        }
        initView();
        return mBaseView;
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

    @Override
    public void onClick(int iconResId) {
        switch (iconResId) {
            case R.drawable.tab_icon_sell:
                Intent intent = new Intent(getActivity(), SellActivity.class);
                intent.putExtra("type", "GROUP");
                intent.putExtra("peer", chatId);
                startActivityForResult(intent, 2);
                break;
            case R.drawable.tab_icon_rent:
                Intent intentRent = new Intent(getActivity(), RentActivity.class);
                intentRent.putExtra("type", "GROUP");
                intentRent.putExtra("peer", chatId);
                getActivity().startActivity(intentRent);
                break;
            case R.drawable.tab_icon_qiugou:
                Intent intentQiuGou = new Intent(getActivity(), QiuGouActivity.class);
                intentQiuGou.putExtra("type", "GROUP");
                intentQiuGou.putExtra("peer", chatId);
                getActivity().startActivity(intentQiuGou);
                break;
            case R.drawable.tab_icon_qiuzu:
                Intent intentQiuZu= new Intent(getActivity(), QiuZuActivity.class);
                intentQiuZu.putExtra("type", "GROUP");
                intentQiuZu.putExtra("peer", chatId);
                getActivity().startActivity(intentQiuZu);
                break;
            case R.drawable.tab_icon_guoneiyou:
                Intent intentGuoNei = new Intent(getActivity(), DomesticDetailsActivity.class);
                intentGuoNei.putExtra("type", "GROUP");
                intentGuoNei.putExtra("peer", chatId);
                startActivityForResult(intentGuoNei, 2);
                break;
            case R.drawable.tab_icon_zhoubianyou:
                Intent intentZhouBian = new Intent(getActivity(), ZhouBianDetailsActivity.class);
                intentZhouBian.putExtra("type", "GROUP");
                intentZhouBian.putExtra("peer", chatId);
                getActivity().startActivity(intentZhouBian);
                break;
            case R.drawable.tab_icon_jingwaiyou:
                Intent intentJingWai = new Intent(getActivity(), JinWaiDetailsActivity.class);
                intentJingWai.putExtra("type", "GROUP");
                intentJingWai.putExtra("peer", chatId);
                getActivity().startActivity(intentJingWai);
                break;
            case R.drawable.tab_icon_piowu:
                Intent intentPiaoWu = new Intent(getActivity(), TicketingDetailsActivity.class);
                intentPiaoWu.putExtra("type", "GROUP");
                intentPiaoWu.putExtra("peer", chatId);
                getActivity().startActivity(intentPiaoWu);
                break;
            case R.drawable.tab_icon_photo:
                break;
            case R.drawable.tab_icon_database:
                break;
            case R.drawable.tab_icon_favorite:
                break;
        }
    }

}
