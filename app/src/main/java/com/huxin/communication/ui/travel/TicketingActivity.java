package com.huxin.communication.ui.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.huxin.communication.adpter.TableTravelActivityAdapter;
import com.huxin.communication.adpter.TableTravelOtherAdapter;
import com.huxin.communication.adpter.TableTravelOverseasAdapter;
import com.huxin.communication.adpter.TicketingAdapter;
import com.huxin.communication.adpter.TicketingDuoXuanAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.TabTicketNameEntity;
import com.huxin.communication.entity.TabTravelNameEntity;
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

    private LinearLayout mLinearLayoutSortTime;


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

        mLinearLayoutSortTime = findViewById(R.id.recycler_sort_time);

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
        mTextViewSpotTicket.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
        mTextViewSpotTicket.setTextColor(getResources().getColor(R.color.white));
        mTextViewQiTaTicket.setBackgroundResource(R.drawable.biaoqian_radius_top);
        mTextViewQiTaTicket.setTextColor(getResources().getColor(R.color.register_font));
        setEnabled(true);
        getTicketInfo(String.valueOf(TicketType), "", "", "",
                "", "", "", "", "1");
        mEditTextMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                maxPrice = mEditTextMax.getText().toString().trim();

            }
        });

        mEditTextMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                maxPrice = mEditTextMin.getText().toString().trim();

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!isClickQuYu) {
            return;
        }
        String ChufaCityCode = PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME);
//        String MuDi = PreferenceUtil.getString(Constanst.SHAIXUAN_SPOT_NAME);
        KyLog.d(ChufaCityCode + "tiket");
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
                setDetleTabData();
                selectTravelTab();

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

                mTextViewZuiXin.setText("总价从低到高");
                mTextViewChongDiDaoGao.setText("总价从高到低");
                mTextViewChongDiDaoGaoFanXian.setText("发布时间顺序");
                mTextViewChongShaoDaoDuoDay.setText("发布时间倒序");
//                mTextViewChongDuoDaoShaoDay.setVisibility(View.GONE);
//                mTextViewZuiWan.setVisibility(View.GONE);
//                mTextViewChongGaoDaoDi.setVisibility(View.GONE);
//                mTextViewChongGaoDaoDiFanXian.setVisibility(View.GONE);
                mLinearLayoutSortTime.setVisibility(View.GONE);

                break;

            case R.id.mudidi_line:
                Intent intentMudDi = new Intent(this, ProvincesTravelActivity.class);
                intentMudDi.putExtra("type", 3);
                startActivity(intentMudDi);
                isClickQuYu = true;
                break;

            case R.id.spot_tv:
                mTextViewSpotTicket.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewSpotTicket.setTextColor(getResources().getColor(R.color.white));
                mTextViewQiTaTicket.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiTaTicket.setTextColor(getResources().getColor(R.color.register_font));
                TicketType = 1;
                getTicketInfo(String.valueOf(TicketType), "", "", "",
                        "", "", "", "", "1");
                break;


            case R.id.other_tv:
                mTextViewQiTaTicket.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewQiTaTicket.setTextColor(getResources().getColor(R.color.white));
                mTextViewSpotTicket.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewSpotTicket.setTextColor(getResources().getColor(R.color.register_font));
                TicketType = 2;
                getTicketInfo(String.valueOf(TicketType), "", "", "",
                        "", "", "", "", "1");
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
                getTicketInfo(String.valueOf(TicketType), "", "", xiaofei,
                        huodong, qita, "", "", "1");

                break;
            case R.id.price_Determine:
                updata();
                if (Integer.parseInt(minPrice) < 10) {
                    getTicketInfo(String.valueOf(TicketType), "", 0 + "," + maxPrice, "",
                            "", "", "", "", "1");
                } else if (Integer.parseInt(minPrice) >= 10 && Integer.parseInt(minPrice) <= 300) {
                    getTicketInfo(String.valueOf(TicketType), "", minPrice + "," + maxPrice, "",
                            "", "", "", "", "1");
                } else if (Integer.parseInt(minPrice) > 300) {
                    getTicketInfo(String.valueOf(TicketType), "", minPrice + "," + 1000000, "",
                            "", "", "", "", "1");
                }
                break;
            case R.id.sort_Determine:
                updata();
                getTicketInfo(String.valueOf(TicketType), "", "", "",
                        "", "", productType, "", "1");
                break;
            case R.id.price1:
                mTextViewPrice1.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.blue));
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
                minPrice = "0";
                maxPrice = "10";
                break;
            case R.id.price2:
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.blue));
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
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.blue));
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
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.blue));
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
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);

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
                break;
            case R.id.price6:
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.blue));
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
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.blue));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "110";
                maxPrice = "130";
                break;
            case R.id.price8:
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.blue));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "130";
                maxPrice = "150";
                break;
            case R.id.price9:
                mTextViewPrice1.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.blue));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "150";
                maxPrice = "200";
                break;
            case R.id.price10:
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice10.setTextColor(getResources().getColor(R.color.blue));
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "200";
                maxPrice = "250";
                break;
            case R.id.price11:
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.shuaixuan_radius_blue);
                mTextViewPrice12.setBackgroundResource(R.drawable.biaoqian_radius);

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
                mTextViewPrice11.setTextColor(getResources().getColor(R.color.blue));
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "250";
                maxPrice = "300";
                break;
            case R.id.price12:
                mTextViewPrice1.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice2.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice3.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice4.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice5.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice6.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice7.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice8.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice9.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice10.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice11.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewPrice12.setBackgroundResource(R.drawable.shuaixuan_radius_blue);

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
                mTextViewPrice12.setTextColor(getResources().getColor(R.color.blue));
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

                productType = "1";
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
                productType = "2";
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
                productType = "3";
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
                productType = "4";
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
                } else {
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
//            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        } else {
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
//            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
            mRelativeLayoutSearch.setVisibility(View.VISIBLE);

        } else {
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
                keyWord, curPage, null, "0", String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribe(ticketInfoEntity -> {
                    cancelProgressDialog();
                    if (ticketInfoEntity != null) {
                        KyLog.object(ticketInfoEntity);
                        setData(ticketInfoEntity);
                        setDuoXuanData(ticketInfoEntity);
                    }
                    isClickQuYu = false;


                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    private void selectTravelTab() {
        showProgressDialog();
        ApiModule.getInstance().selectTravelTab("2")
                .subscribe(tabTicketNameEntity -> {
                    KyLog.object(tabTicketNameEntity.getThemeLists());
                    cancelProgressDialog();
                    setActivityData(tabTicketNameEntity.getActivityList(), mRecyclerViewHuoDong);
                    setThemeListData(tabTicketNameEntity.getThemeLists(), mRecyclerViewDiDian);
                    setOtherData(tabTicketNameEntity.getOtherList(), mRecyclerViewQiTa);

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
                    Intent intent = new Intent(this, TicketingActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    private void setThemeListData(List<TabTravelNameEntity.ThemeList> list, RecyclerView recyclerView) {
        KyLog.object(list);
        if (list != null && list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            TableTravelOverseasAdapter mAdapterTableName = new TableTravelOverseasAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
        }
    }

    private void setActivityData(List<TabTravelNameEntity.ActivityListBean> list, RecyclerView recyclerView) {
        if (list != null && list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            TableTravelActivityAdapter mAdapterTableName = new TableTravelActivityAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
        }
    }

    private void setOtherData(List<TabTravelNameEntity.OtherListBean> list, RecyclerView recyclerView) {
        if (list != null && list.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(this, 4);
            TableTravelOtherAdapter mAdapterTableName = new TableTravelOtherAdapter(list, this);
            recyclerView.setAdapter(mAdapterTableName);
            recyclerView.setLayoutManager(manager);
        }
    }


    private void setDetleTabData() {

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.HUO_DONG))) {
            PreferenceUtil.removeSp(Constanst.HUO_DONG, Constanst.SP_NAME);
        }


        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.QI_TA))) {
            PreferenceUtil.removeSp(Constanst.QI_TA, Constanst.SP_NAME);
        }


        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TICKET_OVERSEAS_NAME))) {
            PreferenceUtil.removeSp(Constanst.TICKET_OVERSEAS_NAME, Constanst.SP_NAME);
        }


    }

    private void setTabData() {


        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.HUO_DONG))) {
            huodong = PreferenceUtil.getString(Constanst.HUO_DONG);

        } else {
            huodong = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.QI_TA))) {
            qita = PreferenceUtil.getString(Constanst.QI_TA);

        } else {
            qita = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.TICKET_OVERSEAS_NAME))) {
            xiaofei = PreferenceUtil.getString(Constanst.TICKET_OVERSEAS_NAME);

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
        } else {
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
