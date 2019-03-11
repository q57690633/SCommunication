package com.huxin.communication.ui.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.JinWaiDuoXuanAdapter;
import com.huxin.communication.adpter.ShaiXuanTabNameAdapter;
import com.huxin.communication.adpter.TicketingAdapter;
import com.huxin.communication.adpter.TicketingDuoXuanAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.entity.TravelEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.ProvincesTravelActivity;
import com.huxin.communication.ui.house.sell.SellActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;


public class TicketingActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewDuoXuan;

    private LinearLayout mLinearLayoutSort;
    private LinearLayout mLinearLayoutPrice;
    private LinearLayout mLinearLayoutMore;

    private LinearLayout mLinearLayoutSorts;
    private LinearLayout mLinearLayoutPrices;
    private LinearLayout mLinearLayoutMores;

    private RelativeLayout mRelativeLayoutSearch;


    private TextView mTextViewSort;
    private TextView mTextViewPrice;
    private TextView mTextViewMore;

    private TextView mTextViewDetermineSort;
    private TextView mTextViewDeterminePrice;
    private TextView mTextViewDetermineMore;

    private TextView mTextViewGuanLi;
    private TextView mTextViewQuXiao;

    private RelativeLayout mRelativeLayoutDuoxuanBtn;
    private LinearLayout mRelativeLayoutRL;
    private ImageView mImageViewPrice;
    private ImageView mImageViewSort;
    private ImageView mImageViewMore;
    private ImageView mImageViewFangxin;

    private TicketingAdapter mAdpter;
    private TicketingDuoXuanAdapter mXuanAdapter;
    private List<String> list = new ArrayList<>();


    private RecyclerView mRecyclerViewQiTa;
    private RecyclerView mRecyclerViewHuoDong;
    private RecyclerView mRecyclerViewDiDian;


    private ShaiXuanTabNameAdapter mAdapterTableName;

    private LinearLayout mLinearLayoutMuDi;

    private TextView mTextViewPrice1;
    private TextView mTextViewPrice2;
    private TextView mTextViewPrice3;
    private TextView mTextViewPrice4;
    private TextView mTextViewPrice5;
    private TextView mTextViewPrice6;
    private TextView mTextViewPrice7;
    private TextView mTextViewPrice8;
    private TextView mTextViewPrice9;
    private TextView mTextViewPrice10;
    private TextView mTextViewPrice11;
    private TextView mTextViewPrice12;

    private TextView mTextViewZuiXin;
    private TextView mTextViewChongDiDaoGao;
    private TextView mTextViewChongDiDaoGaoFanXian;
    private TextView mTextViewChongShaoDaoDuoDay;
    private TextView mTextViewZuiWan;
    private TextView mTextViewChongGaoDaoDi;
    private TextView mTextViewChongGaoDaoDiFanXian;
    private TextView mTextViewChongDuoDaoShaoDay;

    private TextView mTextViewSpotTicket;
    private TextView mTextViewQiTaTicket;

    private EditText mEditTextMax;
    private EditText mEditTextMin;

    private String minPrice;
    private String maxPrice;

    private String productType;

    private String zhushu;
    private String didian;
    private String jiaotong;
    private String huodong;
    private String qita;
    private String xiaofei;

    private List<String> Kouweilist;

    private boolean isClickQuYu = false;

    private int TicketType = 1;

    private TextView mTextViewCollect;

    private static TIMConversation conversation;
    private String peer;
    private String type;
    private TextView mTextViewZhuanFa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_ticketing);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("", MODE_BACK);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_ticket);
        mRecyclerViewDuoXuan = (RecyclerView) findViewById(R.id.recycler_ticket_duoxuan);

        mRelativeLayoutSearch = (RelativeLayout) findViewById(R.id.search_rl);
        mRelativeLayoutDuoxuanBtn = (RelativeLayout) findViewById(R.id.btn_duoxuan);
        mRelativeLayoutRL = (LinearLayout) findViewById(R.id.rl_recyler_sell);

        mImageViewFangxin = (ImageView) findViewById(R.id.image_spot);
        mImageViewPrice = (ImageView) findViewById(R.id.image_price);
        mImageViewSort = (ImageView) findViewById(R.id.image_sort);
        mImageViewMore = (ImageView) findViewById(R.id.image_more);

        mLinearLayoutMore = (LinearLayout) findViewById(R.id.more);
        mLinearLayoutPrice = (LinearLayout) findViewById(R.id.price);
        mLinearLayoutSort = (LinearLayout) findViewById(R.id.sort);

        mTextViewSpotTicket = (TextView) findViewById(R.id.spot_tv);
        mTextViewQiTaTicket = (TextView) findViewById(R.id.other_tv);


        mLinearLayoutMores = (LinearLayout) findViewById(R.id.ticketing_more);
        mLinearLayoutPrices = (LinearLayout) findViewById(R.id.ticketing_price);
        mLinearLayoutSorts = (LinearLayout) findViewById(R.id.ticketing_sort);


        mTextViewSort = (TextView) findViewById(R.id.tv_sort);
        mTextViewPrice = (TextView) findViewById(R.id.tv_price);
        mTextViewMore = (TextView) findViewById(R.id.tv_more);

        mTextViewDetermineSort = (TextView) findViewById(R.id.sort_Determine);
        mTextViewDetermineMore = (TextView) findViewById(R.id.more_Determine);
        mTextViewDeterminePrice = (TextView) findViewById(R.id.price_Determine);

        mTextViewGuanLi = (TextView) findViewById(R.id.toolbar_right);
        mTextViewQuXiao = (TextView) findViewById(R.id.toolbar_quxiao);


        mRecyclerViewDiDian = (RecyclerView) findViewById(R.id.zhuti);
        mRecyclerViewHuoDong = (RecyclerView) findViewById(R.id.huodong_recycler);
        mRecyclerViewQiTa = (RecyclerView) findViewById(R.id.QiTa_recycler);


        mLinearLayoutMuDi = (LinearLayout) findViewById(R.id.mudidi_line);

        mTextViewPrice1 = (TextView) findViewById(R.id.price1);
        mTextViewPrice2 = (TextView) findViewById(R.id.price2);
        mTextViewPrice3 = (TextView) findViewById(R.id.price3);
        mTextViewPrice4 = (TextView) findViewById(R.id.price4);
        mTextViewPrice5 = (TextView) findViewById(R.id.price5);
        mTextViewPrice6 = (TextView) findViewById(R.id.price6);
        mTextViewPrice7 = (TextView) findViewById(R.id.price7);
        mTextViewPrice8 = (TextView) findViewById(R.id.price8);
        mTextViewPrice9 = (TextView) findViewById(R.id.price9);
        mTextViewPrice10 = (TextView) findViewById(R.id.price10);
        mTextViewPrice11 = (TextView) findViewById(R.id.price11);
        mTextViewPrice12 = (TextView) findViewById(R.id.price12);


        mEditTextMax = (EditText) findViewById(R.id.ed_maxMeasure);
        mEditTextMin = (EditText) findViewById(R.id.ed_minMeasure);

        mTextViewZuiXin = (TextView) findViewById(R.id.zuixin);
        mTextViewChongDiDaoGao = (TextView) findViewById(R.id.congdidaogao);
        mTextViewChongDiDaoGaoFanXian = (TextView) findViewById(R.id.congdidaogao_fanxian);
        mTextViewChongShaoDaoDuoDay = (TextView) findViewById(R.id.day_congshaodaoduo);
        mTextViewZuiWan = (TextView) findViewById(R.id.zuiwan);
        mTextViewChongGaoDaoDi = (TextView) findViewById(R.id.conggaodaodi);
        mTextViewChongGaoDaoDiFanXian = (TextView) findViewById(R.id.conggaodaodi_fanxian);
        mTextViewChongDuoDaoShaoDay = (TextView) findViewById(R.id.day_congduodaoshao);

        mTextViewCollect = (TextView) findViewById(R.id.collect_btn);

        mTextViewZhuanFa = findViewById(R.id.delete_collect);

        mLinearLayoutMore.setOnClickListener(this);
        mLinearLayoutPrice.setOnClickListener(this);
        mLinearLayoutSort.setOnClickListener(this);
        mTextViewDetermineSort.setOnClickListener(this);
        mTextViewDetermineMore.setOnClickListener(this);
        mTextViewDeterminePrice.setOnClickListener(this);
        mTextViewGuanLi.setOnClickListener(this);
        mTextViewQuXiao.setOnClickListener(this);
        mTextViewQiTaTicket.setOnClickListener(this);
        mTextViewSpotTicket.setOnClickListener(this);
        mRelativeLayoutSearch.setVisibility(View.VISIBLE);


        mTextViewPrice1.setOnClickListener(this);
        mTextViewPrice2.setOnClickListener(this);
        mTextViewPrice3.setOnClickListener(this);
        mTextViewPrice4.setOnClickListener(this);
        mTextViewPrice5.setOnClickListener(this);
        mTextViewPrice6.setOnClickListener(this);
        mTextViewPrice7.setOnClickListener(this);
        mTextViewPrice8.setOnClickListener(this);
        mTextViewPrice9.setOnClickListener(this);
        mTextViewPrice10.setOnClickListener(this);
        mTextViewPrice11.setOnClickListener(this);
        mTextViewPrice12.setOnClickListener(this);

        mTextViewZuiXin.setOnClickListener(this);
        mTextViewChongDiDaoGao.setOnClickListener(this);
        mTextViewChongDiDaoGaoFanXian.setOnClickListener(this);
        mTextViewZuiWan.setOnClickListener(this);
        mTextViewChongShaoDaoDuoDay.setOnClickListener(this);
        mTextViewChongGaoDaoDi.setOnClickListener(this);
        mTextViewChongGaoDaoDiFanXian.setOnClickListener(this);
        mTextViewChongDuoDaoShaoDay.setOnClickListener(this);
        mTextViewCollect.setOnClickListener(this);
        mTextViewZhuanFa.setOnClickListener(this);


        mLinearLayoutMuDi.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setEnabled(true);
        getTicketInfo(String.valueOf(TicketType), "", "", "",
                "", "", "", "", "1");
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!isClickQuYu) {
            return;
        }
        String ChufaCityCode = PreferenceUtil.getString(Constanst.CITY_CODE);
//        String MuDi = PreferenceUtil.getString(Constanst.SHAIXUAN_SPOT_NAME);
        KyLog.d(ChufaCityCode);
        if (!TextUtils.isEmpty(ChufaCityCode)) {
            getTicketInfo(String.valueOf(TicketType), ChufaCityCode, "", "",
                    "", "", "", "", "1");
        } else {

            getTicketInfo(String.valueOf(TicketType), "", "", "",
                    "", "", "", "", "1");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more:
                mLinearLayoutSorts.setVisibility(View.GONE);
                mLinearLayoutPrices.setVisibility(View.GONE);
                mLinearLayoutMores.setVisibility(View.VISIBLE);
                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.blue));
                mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
                mRelativeLayoutRL.setVisibility(View.GONE);
                mImageViewMore.setBackgroundResource(R.drawable.icon_triangle_pre);
                mImageViewPrice.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewSort.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle2);
                setMoreData();
                setDetleTabData();
                break;
            case R.id.price:
                mLinearLayoutSorts.setVisibility(View.GONE);
                mLinearLayoutPrices.setVisibility(View.VISIBLE);
                mLinearLayoutMores.setVisibility(View.GONE);
                mTextViewPrice.setTextColor(getResources().getColor(R.color.blue));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
                mRelativeLayoutRL.setVisibility(View.GONE);
                mImageViewMore.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewPrice.setBackgroundResource(R.drawable.icon_triangle_pre);
                mImageViewSort.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle2);
                break;
            case R.id.sort:
                mLinearLayoutSorts.setVisibility(View.VISIBLE);
                mLinearLayoutPrices.setVisibility(View.GONE);
                mLinearLayoutMores.setVisibility(View.GONE);
                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSort.setTextColor(getResources().getColor(R.color.blue));
                mRelativeLayoutRL.setVisibility(View.GONE);
                mImageViewMore.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewPrice.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewSort.setBackgroundResource(R.drawable.icon_triangle_pre);
                mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle2);
                break;

            case R.id.mudidi_line:
                Intent intentMudDi = new Intent(this, ProvincesTravelActivity.class);
                intentMudDi.putExtra("type", 3);
                startActivity(intentMudDi);
                break;

            case R.id.spot_tv:
                mTextViewSpotTicket.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewSpotTicket.setTextColor(getResources().getColor(R.color.white));
                mTextViewQiTaTicket.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiTaTicket.setTextColor(getResources().getColor(R.color.register_font));
                TicketType = 1;
                break;


            case R.id.other_tv:
                mTextViewQiTaTicket.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewQiTaTicket.setTextColor(getResources().getColor(R.color.white));
                mTextViewSpotTicket.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewSpotTicket.setTextColor(getResources().getColor(R.color.register_font));
                TicketType = 2;

                break;

            case R.id.more_Determine:
                updata();
                setTabData();
                KyLog.d(zhushu);
                KyLog.d(didian);
                KyLog.d(jiaotong);
                KyLog.d(huodong);
                KyLog.d(qita);
                KyLog.d(xiaofei);
                getTicketInfo(String.valueOf(TicketType), "", "", jiaotong,
                        huodong, qita, "", "", "1");

                break;
            case R.id.price_Determine:
                updata();
                if (Integer.parseInt(minPrice) < 500) {
                    getTicketInfo(String.valueOf(TicketType), "", 0 + "," + maxPrice, "",
                            "", "", "", "", "1");
                } else if (Integer.parseInt(minPrice) >= 500 && Integer.parseInt(minPrice) <= 7000) {
                    getTicketInfo(String.valueOf(TicketType), "", minPrice + "," + maxPrice, "",
                            "", "", "", "", "1");
                } else if (Integer.parseInt(minPrice) > 7000) {
                    getTicketInfo(String.valueOf(TicketType), "", 7000 + "," + 1000000, "",
                            "", "", "", "", "1");
                }
                break;
            case R.id.sort_Determine:
                updata();
                getTicketInfo(String.valueOf(TicketType), "", "", "",
                        "", "", productType, "", "1");
                break;
            case R.id.price1:
                mTextViewPrice1.setBackgroundResource(R.color.blue);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "";
                maxPrice = "10";
                break;
            case R.id.price2:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.blue);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "10";
                maxPrice = "30";
                break;
            case R.id.price3:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.blue);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "30";
                maxPrice = "50";
                break;
            case R.id.price4:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.blue);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "50";
                maxPrice = "70";
                break;
            case R.id.price5:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.blue);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                KyLog.d("5");
                minPrice = "70";
                maxPrice = "90";
            case R.id.price6:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.blue);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "90";
                maxPrice = "110";
                break;
            case R.id.price7:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.blue);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "110";
                maxPrice = "130";
                break;
            case R.id.price8:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.blue);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "130";
                maxPrice = "150";
                break;
            case R.id.price9:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.blue);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "150";
                maxPrice = "200";
                break;
            case R.id.price10:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.blue);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "200";
                maxPrice = "250";
                break;
            case R.id.price11:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.blue);
                mTextViewPrice12.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "250";
                maxPrice = "300";
                break;
            case R.id.price12:
                mTextViewPrice1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice10.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice11.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextViewPrice12.setBackgroundResource(R.color.blue);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.white));
                minPrice = "300";
                maxPrice = "";
                break;

            case R.id.zuixin:
                mTextViewZuiXin.setTextColor(getResources().getColor(R.color.blue));
                mTextViewChongDiDaoGao.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGaoFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewZuiWan.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongShaoDaoDuoDay.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDiFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDuoDaoShaoDay.setTextColor(getResources().getColor(R.color.register_font));

                productType = "3";
                break;
            case R.id.congdidaogao:
                mTextViewZuiXin.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGao.setTextColor(getResources().getColor(R.color.blue));
                mTextViewChongDiDaoGaoFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewZuiWan.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongShaoDaoDuoDay.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDiFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDuoDaoShaoDay.setTextColor(getResources().getColor(R.color.register_font));
                productType = "1";
                break;
            case R.id.congdidaogao_fanxian:
                mTextViewZuiXin.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGao.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGaoFanXian.setTextColor(getResources().getColor(R.color.blue));
                mTextViewZuiWan.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongShaoDaoDuoDay.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDiFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDuoDaoShaoDay.setTextColor(getResources().getColor(R.color.register_font));
                productType = "5";
                break;
            case R.id.zuiwan:
                mTextViewZuiXin.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGao.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGaoFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewZuiWan.setTextColor(getResources().getColor(R.color.blue));
                mTextViewChongShaoDaoDuoDay.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDiFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDuoDaoShaoDay.setTextColor(getResources().getColor(R.color.register_font));
                productType = "4";
                break;
            case R.id.day_congshaodaoduo:
                mTextViewZuiXin.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGao.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGaoFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewZuiWan.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongShaoDaoDuoDay.setTextColor(getResources().getColor(R.color.blue));
                mTextViewChongGaoDaoDi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDiFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDuoDaoShaoDay.setTextColor(getResources().getColor(R.color.register_font));
                productType = "7";
                break;
            case R.id.conggaodaodi:
                mTextViewZuiXin.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGao.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGaoFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewZuiWan.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongShaoDaoDuoDay.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDi.setTextColor(getResources().getColor(R.color.blue));
                mTextViewChongGaoDaoDiFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDuoDaoShaoDay.setTextColor(getResources().getColor(R.color.register_font));
                productType = "2";
                break;
            case R.id.conggaodaodi_fanxian:
                mTextViewZuiXin.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGao.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGaoFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewZuiWan.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongShaoDaoDuoDay.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDiFanXian.setTextColor(getResources().getColor(R.color.blue));
                mTextViewChongDuoDaoShaoDay.setTextColor(getResources().getColor(R.color.register_font));
                productType = "6";
                break;
            case R.id.day_congduodaoshao:
                mTextViewZuiXin.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGao.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDiDaoGaoFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewZuiWan.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongShaoDaoDuoDay.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongGaoDaoDiFanXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChongDuoDaoShaoDay.setTextColor(getResources().getColor(R.color.blue));
                productType = "8";
                break;
            case R.id.toolbar_right:
                setEnabled(false);
                mTextViewQuXiao.setVisibility(View.VISIBLE);
                mTextViewGuanLi.setVisibility(View.GONE);
                mRecyclerViewDuoXuan.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mRelativeLayoutRL.setVisibility(View.VISIBLE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.toolbar_quxiao:
                setEnabled(true);
                mTextViewQuXiao.setVisibility(View.GONE);
                mTextViewGuanLi.setVisibility(View.VISIBLE);
                mRecyclerViewDuoXuan.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.GONE);
                break;
            case R.id.collect_btn:
                addTravelCollect(3);
                break;
            case R.id.delete_collect:
//                addCollectTravel(productType);
                if (mXuanAdapter.getSelectedItem().size() > 0) {
                    zhuanfa(mXuanAdapter);
                }else {
                    Toast.makeText(this, "请选择需要转发的数据", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void updata() {
        mRelativeLayoutRL.setVisibility(View.VISIBLE);
        mRecyclerViewDuoXuan.setVisibility(View.GONE);
        mLinearLayoutSorts.setVisibility(View.GONE);
        mLinearLayoutPrices.setVisibility(View.GONE);
        mLinearLayoutMores.setVisibility(View.GONE);
        mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
    }


    private void setDuoXuanData(TicketInfoEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mXuanAdapter = new TicketingDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }else {
            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();

        }
    }

    private void setData(TicketInfoEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpter = new TicketingAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }else {
            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();

        }

    }


    private void getTicketInfo(String ticket_type, String ticket_city_name,
                               String minPri_maxPri, String ticket_theme_id,
                               String ticket_activity_id, String ticket_other_id,
                               String sort_type,
                               String keyWord, String curPage) {
        showProgressDialog();
        ApiModule.getInstance().getTicketInfo(ticket_type, ticket_city_name,
                minPri_maxPri, ticket_theme_id, ticket_activity_id, ticket_other_id, sort_type,
                keyWord, curPage, null)
                .subscribe(ticketInfoEntity -> {
                    cancelProgressDialog();
                    if (ticketInfoEntity != null) {
                        KyLog.object(ticketInfoEntity);
                        setData(ticketInfoEntity);
                        setDuoXuanData(ticketInfoEntity);
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void addTravelCollect(int travelType) {
        KyLog.d(PreferenceUtil.getString(Constanst.PID_TRAVEL_COLLECT));
        showProgressDialog();
        ApiModule.getInstance().addTravelCollect(PreferenceUtil.getString(Constanst.PID_TRAVEL_COLLECT), travelType)
                .subscribe(response -> {
                    KyLog.object(response + "");
                    cancelProgressDialog();
                    Intent intent =  new Intent(this,TicketingActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private List<String> setDiDian() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("主题乐园");
        Kouweilist.add("动物园");
        Kouweilist.add("地质公园");
        Kouweilist.add("热带雨林");
        Kouweilist.add("海边");
        Kouweilist.add("海岛");
        Kouweilist.add("草原");
        Kouweilist.add("沙漠");
        Kouweilist.add("峡谷");
        Kouweilist.add("登山");
        Kouweilist.add("雪山");
        Kouweilist.add("湖泊");
        Kouweilist.add("江河");
        Kouweilist.add("瀑布");
        Kouweilist.add("都市");
        Kouweilist.add("古镇");

        Kouweilist.add("古迹");
        Kouweilist.add("寺庙");
        Kouweilist.add("长城");
        Kouweilist.add("海洋馆");
        Kouweilist.add("玻璃栈道");
        return Kouweilist;
    }

    private List<String> setHuoDong() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("自驾");
        Kouweilist.add("托展");
        Kouweilist.add("户外");
        Kouweilist.add("摄影");
        Kouweilist.add("休闲");
        Kouweilist.add("美食");
        Kouweilist.add("购物");
        Kouweilist.add("纯玩");
        Kouweilist.add("赏花");
        Kouweilist.add("艺术");
        Kouweilist.add("蜜月");
        Kouweilist.add("亲子");
        Kouweilist.add("游学");
        Kouweilist.add("画展");
        Kouweilist.add("宗教");
        Kouweilist.add("漂流");
        Kouweilist.add("温泉");
        Kouweilist.add("蹦极");
        Kouweilist.add("潜水");
        Kouweilist.add("冲浪");
        Kouweilist.add("滑雪");
        Kouweilist.add("骑马");

        Kouweilist.add("热气球");
        Kouweilist.add("马戏团");
        Kouweilist.add("嘉年华");
        Kouweilist.add("演出");
        Kouweilist.add("音乐节");
        Kouweilist.add("演唱会");
        Kouweilist.add("啤酒节");
        Kouweilist.add("博览会");
        return Kouweilist;
    }


    private List<String> setQiTa() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("一卡通");
        Kouweilist.add("代金券");
        Kouweilist.add("折扣群");
        Kouweilist.add("游乐场");
        Kouweilist.add("洗车");
        Kouweilist.add("汗蒸");
        Kouweilist.add("保健");

        Kouweilist.add("足疗");
        Kouweilist.add("租车");
        return Kouweilist;
    }


    private void setMoreData() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setDiDian(), this, 12);
        mRecyclerViewDiDian.setAdapter(mAdapterTableName);
        mRecyclerViewDiDian.setLayoutManager(manager);
//        mRecyclerViewDiDian.addItemDecoration(new SpaceItemDecoration(0, 25));


        GridLayoutManager managerChaoXiang = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setHuoDong(), this, 7);
        mRecyclerViewHuoDong.setAdapter(mAdapterTableName);
        mRecyclerViewHuoDong.setLayoutManager(managerChaoXiang);
//        mRecyclerViewHuoDong.addItemDecoration(new SpaceItemDecoration(0, 25));


        GridLayoutManager managerLouLing = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setQiTa(), this, 10);
        mRecyclerViewQiTa.setAdapter(mAdapterTableName);
        mRecyclerViewQiTa.setLayoutManager(managerLouLing);
//        mRecyclerViewQiTa.addItemDecoration(new SpaceItemDecoration(0, 25));
    }


    private void setDetleTabData() {

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.HUO_DONG))) {
            PreferenceUtil.removeSp(Constanst.HUO_DONG, Constanst.SP_NAME);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.ZHU_SHU))) {
            PreferenceUtil.removeSp(Constanst.ZHU_SHU, Constanst.SP_NAME);

        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.XIAO_FEI))) {
            PreferenceUtil.removeSp(Constanst.XIAO_FEI, Constanst.SP_NAME);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.QI_TA))) {
            PreferenceUtil.removeSp(Constanst.QI_TA, Constanst.SP_NAME);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.JIAO_TONG))) {
            PreferenceUtil.removeSp(Constanst.JIAO_TONG, Constanst.SP_NAME);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.DI_DIAN))) {
            PreferenceUtil.removeSp(Constanst.DI_DIAN, Constanst.SP_NAME);
        }


    }

    private void setTabData() {

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.ZHU_SHU))) {
            zhushu = PreferenceUtil.getString(Constanst.ZHU_SHU)
                    .substring(1, PreferenceUtil.getString(Constanst.ZHU_SHU).length() - 1);
        } else {
            zhushu = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.DI_DIAN))) {
            didian = PreferenceUtil.getString(Constanst.DI_DIAN)
                    .substring(1, PreferenceUtil.getString(Constanst.DI_DIAN).length() - 1);
        } else {
            didian = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.JIAO_TONG))) {
            jiaotong = PreferenceUtil.getString(Constanst.JIAO_TONG)
                    .substring(1, PreferenceUtil.getString(Constanst.JIAO_TONG).length() - 1);
        } else {
            jiaotong = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.HUO_DONG))) {
            huodong = PreferenceUtil.getString(Constanst.HUO_DONG)
                    .substring(1, PreferenceUtil.getString(Constanst.HUO_DONG).length() - 1);
        } else {
            huodong = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.QI_TA))) {
            qita = PreferenceUtil.getString(Constanst.QI_TA)
                    .substring(1, PreferenceUtil.getString(Constanst.QI_TA).length() - 1);
        } else {
            qita = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.XIAO_FEI))) {
            xiaofei = PreferenceUtil.getString(Constanst.XIAO_FEI)
                    .substring(1, PreferenceUtil.getString(Constanst.XIAO_FEI).length() - 1);
        } else {
            xiaofei = "";
        }
    }

    private void zhuanfa(TicketingDuoXuanAdapter adapter) {
        type = getIntent().getStringExtra("type");
        peer = getIntent().getStringExtra("peer");

        if (TextUtils.isEmpty(type) && TextUtils.isEmpty(peer)) {
            return;
        }
        //获取单聊会话
        if (type.equalsIgnoreCase("C2C")) {
            conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, peer);
        }else {
            conversation = TIMManager.getInstance().getConversation(TIMConversationType.Group, peer);
        }
        TIMMessage msg = new TIMMessage();

        TIMCustomElem elem = new TIMCustomElem();
        elem.setData(getData(adapter.getSelectedItem(), 4).getBytes());      //自定义 byte[]
        elem.setDesc("sell message"); //自定义描述信息

        //将 elem 添加到消息
        if (msg.addElement(elem) != 0) {
            Log.d("failed", "addElement failed");
            return;
        }
        //发送消息
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {//发送消息失败
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 含义请参见错误码表
                Log.d("failed", "send message failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e("failed", "SendMsg ok");
                Toast.makeText(TicketingActivity.this, "success", Toast.LENGTH_SHORT).show();
                KyLog.d(msg.toString());
                finish();
            }
        });

    }

    public static String getData(ArrayList<TicketInfoEntity.ListBean> Salelist, int TravelType) {
        List<TravelEntity> list = new ArrayList<>();
        List<TravelEntity.ListBean> listBeans = new ArrayList<>();
        TravelEntity entityTravel = new TravelEntity();

        if (Salelist != null && Salelist.size() > 0) {
            for (TicketInfoEntity.ListBean SaleEntity : Salelist) {
                TravelEntity.ListBean entity = new TravelEntity.ListBean();
                entity.setGoals_city(SaleEntity.getGoals_city());
                entity.setHeadUrl(SaleEntity.getHeadUrl());
                entity.setPhoto_url(SaleEntity.getPhoto_url());
                entity.setReturnPrice(SaleEntity.getReturn_price());
                entity.setReturnPriceChild(SaleEntity.getReturn_price_child());
                entity.setTagName(SaleEntity.getTagName());
                entity.setUserCity(SaleEntity.getUserCity());
                entity.setUsername(SaleEntity.getUsername());

                listBeans.add(entity);
            }
            entityTravel.setList(listBeans);
            entityTravel.setTravelType(TravelType);//出售
            entityTravel.setType(2);
            list.add(entityTravel);
        }
        KyLog.object(list);
        return ListToString(list);
    }

    /**
     * List转换String
     *
     * @param list :需要转换的List
     * @return String转换后的字符串
     */
    public static String ListToString(List<TravelEntity> list) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        StringBuffer sblist = new StringBuffer();

        if (list != null && list.size() > 0) {
            for (TravelEntity entity : list) {
                for (TravelEntity.ListBean listBean : entity.getList()) {
                    sblist.append("{").append(listBean).append("}");
                }
                sb.append(entity).append("[").append(sblist).append("]");
            }
            stringBuffer.append("{").append(sb).append("}");
        }
        return "L" + sb.toString();
    }

    private void setEnabled(boolean isFocusable) {
        mLinearLayoutMore.setClickable(isFocusable);
        mLinearLayoutPrice.setClickable(isFocusable);
        mLinearLayoutSort.setClickable(isFocusable);
        mLinearLayoutMuDi.setClickable(isFocusable);
        mRecyclerViewDuoXuan.setClickable(isFocusable);


        mLinearLayoutMore.setFocusable(isFocusable);
        mLinearLayoutPrice.setFocusable(isFocusable);
        mLinearLayoutSort.setFocusable(isFocusable);
        mLinearLayoutMuDi.setFocusable(isFocusable);
        mRecyclerViewDuoXuan.setFocusable(isFocusable);


    }
}
