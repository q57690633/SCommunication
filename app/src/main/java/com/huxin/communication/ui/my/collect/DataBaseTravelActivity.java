package com.huxin.communication.ui.my.collect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.CityTravelsAdapter;
import com.huxin.communication.adpter.JinWaiDuoXuanAdapter;
import com.huxin.communication.adpter.JingWaiAdapter;
import com.huxin.communication.adpter.ProvincesTravelsAdapter;
import com.huxin.communication.adpter.ShaiXuanTabNameAdapter;
import com.huxin.communication.adpter.TicketingAdapter;
import com.huxin.communication.adpter.TicketingDuoXuanAdapter;
import com.huxin.communication.adpter.ZhouBianAdapter;
import com.huxin.communication.adpter.ZhouBianDuoXuanAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.ProvincesTravelActivity;
import com.huxin.communication.ui.house.sell.SellActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class DataBaseTravelActivity extends BaseActivity implements View.OnClickListener ,EditText.OnEditorActionListener{

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
    private ImageView mImageViewMeasure;
    private ImageView mImageViewMore;
    private ImageView mImageViewFangxin;

    private ZhouBianAdapter mAdpter;
    private TicketingAdapter mTicketAdapter;

    private ZhouBianDuoXuanAdapter mZhouBianDuoXuanAdapter;
    private TicketingDuoXuanAdapter mTicketingDuoXuanAdapter;


    private JinWaiDuoXuanAdapter mJinWaiDuoXuanAdapter;
    private JingWaiAdapter mJinWaiAdpter;

    private TextView mTextViewDuanTuYou;
    private TextView mTextViewGuoNeiYou;
    private TextView mTextViewGuoWaiYou;
    private TextView mTextViewPiaoWu;
    private TextView mTextViewCollect;

    private List<String> list = new ArrayList<>();




    private RecyclerView mRecyclerViewZhuShu;
    private RecyclerView mRecyclerViewXiaoFei;
    private RecyclerView mRecyclerViewQiTa;
    private RecyclerView mRecyclerViewJiaoTong;
    private RecyclerView mRecyclerViewHuoDong;
    private RecyclerView mRecyclerViewDiDian;


    private ShaiXuanTabNameAdapter mAdapterTableName;

    private LinearLayout mLinearLayoutChuFa;
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


    private EditText mEditTextMax;
    private EditText mEditTextMin;

    private String minPrice;
    private String maxPrice;

    private int productType;

    private String zhushu;
    private String didian;
    private String jiaotong;
    private String huodong;
    private String qita;
    private String xiaofei;

    private List<String> Kouweilist;

    private EditText mEditTextSearch;




    private boolean isClickQuYu = false;

    private int travelType = 1;


    private RelativeLayout mLinearLayoutMudi;
    private RecyclerView mRecyclerViewAreaOne;
    private RecyclerView mRecyclerViewAreaTwo;
    private TextView mTextViewChufa;
    private TextView mTextViewMuDi;

    private TextView mTextViewChuFaDetermine;
    private TextView mTextViewChuFaBuXian;
    private TextView mTextViewCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_data_base_travel);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("", MODE_BACK);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyler_domestic);

        mRecyclerViewDuoXuan = (RecyclerView) findViewById(R.id.recyler_domestic_duoxuan);

        mLinearLayoutMore = (LinearLayout) findViewById(R.id.more);
        mLinearLayoutPrice = (LinearLayout) findViewById(R.id.price);
        mLinearLayoutSort = (LinearLayout) findViewById(R.id.sort);

        mLinearLayoutMores = (LinearLayout) findViewById(R.id.travel_more);
        mLinearLayoutPrices = (LinearLayout) findViewById(R.id.travel_price);
        mLinearLayoutSorts = (LinearLayout) findViewById(R.id.travel_sort);

        mRelativeLayoutSearch = (RelativeLayout) findViewById(R.id.search_rl);
        mRelativeLayoutDuoxuanBtn = (RelativeLayout) findViewById(R.id.btn_duoxuan);
        mRelativeLayoutRL = (LinearLayout) findViewById(R.id.rl_recyler_sell);

        mImageViewFangxin = (ImageView) findViewById(R.id.chufadi);
        mImageViewPrice = (ImageView) findViewById(R.id.image_price);
        mImageViewSort = (ImageView) findViewById(R.id.image_sort);
        mImageViewMeasure = (ImageView) findViewById(R.id.mudidi);
        mImageViewMore = (ImageView) findViewById(R.id.image_more);


        mTextViewSort = (TextView) findViewById(R.id.tv_sort);
        mTextViewPrice = (TextView) findViewById(R.id.tv_price);
        mTextViewMore = (TextView) findViewById(R.id.tv_more);

        mTextViewDetermineSort = (TextView) findViewById(R.id.sort_Determine);
        mTextViewDetermineMore = (TextView) findViewById(R.id.more_Determine);
        mTextViewDeterminePrice = (TextView) findViewById(R.id.price_Determine);


        mTextViewGuanLi = (TextView) findViewById(R.id.toolbar_right);
        mTextViewQuXiao = (TextView) findViewById(R.id.toolbar_quxiao);

        mTextViewDuanTuYou = (TextView) findViewById(R.id.duantuyou);
        mTextViewGuoNeiYou = (TextView) findViewById(R.id.guoneiyou);
        mTextViewGuoWaiYou = (TextView) findViewById(R.id.jingwaiyou);
        mTextViewPiaoWu = (TextView) findViewById(R.id.piaowu);

        mTextViewCollect = (TextView) findViewById(R.id.collect_btn);

        mRecyclerViewDiDian = (RecyclerView) findViewById(R.id.didian);
        mRecyclerViewHuoDong = (RecyclerView) findViewById(R.id.huodong_recycler);
        mRecyclerViewZhuShu = (RecyclerView) findViewById(R.id.zhushu_recycler);
        mRecyclerViewXiaoFei = (RecyclerView) findViewById(R.id.xiaofei_recycler);
        mRecyclerViewQiTa = (RecyclerView) findViewById(R.id.QiTa_recycler);
        mRecyclerViewJiaoTong = (RecyclerView) findViewById(R.id.jiaotong);


        mLinearLayoutChuFa = (LinearLayout) findViewById(R.id.chufadi_line);
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


        mEditTextMax = (EditText) findViewById(R.id.ed_max);
        mEditTextMin = (EditText) findViewById(R.id.ed_min);

        mTextViewZuiXin = (TextView) findViewById(R.id.zuixin);
        mTextViewChongDiDaoGao = (TextView) findViewById(R.id.congdidaogao);
        mTextViewChongDiDaoGaoFanXian = (TextView) findViewById(R.id.congdidaogao_fanxian);
        mTextViewChongShaoDaoDuoDay = (TextView) findViewById(R.id.day_congshaodaoduo);
        mTextViewZuiWan = (TextView) findViewById(R.id.zuiwan);
        mTextViewChongGaoDaoDi = (TextView) findViewById(R.id.conggaodaodi);
        mTextViewChongGaoDaoDiFanXian = (TextView) findViewById(R.id.conggaodaodi_fanxian);
        mTextViewChongDuoDaoShaoDay = (TextView) findViewById(R.id.day_congduodaoshao);

        mEditTextSearch = findViewById(R.id.toolbar_editText_search);

        mLinearLayoutMudi = findViewById(R.id.travel_mudi);
        mRecyclerViewAreaOne = findViewById(R.id.recyclerView_provinces);
        mRecyclerViewAreaTwo = findViewById(R.id.recycler_city);
        mTextViewChufa = findViewById(R.id.tv_chufa);
        mTextViewMuDi = findViewById(R.id.tv_fangxin);

        mTextViewChuFaDetermine = findViewById(R.id.chufa_Determine);
        mTextViewChuFaBuXian = findViewById(R.id.chufa_buxian);
        mTextViewCity = findViewById(R.id.tv_city);

        mTextViewChuFaDetermine.setOnClickListener(this);
        mTextViewChuFaBuXian.setOnClickListener(this);



        mLinearLayoutMore.setOnClickListener(this);
        mLinearLayoutPrice.setOnClickListener(this);
        mLinearLayoutSort.setOnClickListener(this);
        mTextViewDetermineSort.setOnClickListener(this);
        mTextViewDetermineMore.setOnClickListener(this);
        mTextViewDeterminePrice.setOnClickListener(this);
        mTextViewGuanLi.setOnClickListener(this);
        mTextViewQuXiao.setOnClickListener(this);

        mTextViewDuanTuYou.setOnClickListener(this);
        mTextViewGuoNeiYou.setOnClickListener(this);
        mTextViewGuoWaiYou.setOnClickListener(this);
        mTextViewPiaoWu.setOnClickListener(this);

        mTextViewCollect.setOnClickListener(this);

        mEditTextSearch.setOnEditorActionListener(this);


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


        mLinearLayoutMuDi.setOnClickListener(this);
        mLinearLayoutChuFa.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setEnabled(true);
        if (travelType == 1 || travelType == 2) {
            gettingAroundTravel("",  "","", productType, ""
                    , "", "", "", "", "",
                    "", "", "",
                    "1", "", "", String.valueOf(travelType), "");
        } else if (travelType == 3) {
            gettingForeignTravel("", "", "", "", "", "", "", "" ,
                    "", "", "", "", "", "", "", "",
                    "1", "");
        } else {
            getTicketInfo("1", "", "", "",
                    "", "", String.valueOf(productType), "", "1");
        }

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fangxin:
                mLinearLayoutSorts.setVisibility(View.GONE);
                mLinearLayoutPrices.setVisibility(View.GONE);
                mLinearLayoutMores.setVisibility(View.GONE);
                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
                mRelativeLayoutRL.setVisibility(View.GONE);
                mImageViewMore.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewPrice.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewSort.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewMeasure.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle_pre);

                break;
            case R.id.measure:
                mLinearLayoutSorts.setVisibility(View.GONE);
                mLinearLayoutPrices.setVisibility(View.GONE);
                mLinearLayoutMores.setVisibility(View.GONE);
                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
                mRelativeLayoutRL.setVisibility(View.GONE);
                mImageViewMore.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewPrice.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewSort.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewMeasure.setBackgroundResource(R.drawable.icon_triangle_pre);
                mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle2);

                break;
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
                mImageViewMeasure.setBackgroundResource(R.drawable.icon_triangle2);
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
                mImageViewMeasure.setBackgroundResource(R.drawable.icon_triangle2);
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
                mImageViewMeasure.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle2);
                break;

            case R.id.chufadi_line:
                getProvinces(1);
                if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NAME))) {
                    mTextViewCity.setText(PreferenceUtil.getString(Constanst.CITY_NAME));
                }
                mLinearLayoutSorts.setVisibility(View.GONE);
                mLinearLayoutPrices.setVisibility(View.GONE);
                mLinearLayoutMores.setVisibility(View.GONE);
                mLinearLayoutMudi.setVisibility(View.VISIBLE);

                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChufa.setTextColor(getResources().getColor(R.color.blue));
                mTextViewMuDi.setTextColor(getResources().getColor(R.color.register_font));


                mRelativeLayoutRL.setVisibility(View.GONE);
                mImageViewMore.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewPrice.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewSort.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewMeasure.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle_pre);


                isClickQuYu = true;
                if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME))) {
                    PreferenceUtil.removeSp(Constanst.CITY_MUDI_TRAVEL_NAME, Constanst.SP_NAME);
                }
                break;


            case R.id.mudidi_line:
                getProvinces(3);
                mLinearLayoutSorts.setVisibility(View.GONE);
                mLinearLayoutPrices.setVisibility(View.GONE);
                mLinearLayoutMores.setVisibility(View.GONE);
                mLinearLayoutMudi.setVisibility(View.VISIBLE);

                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewChufa.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMuDi.setTextColor(getResources().getColor(R.color.blue));


                mRelativeLayoutRL.setVisibility(View.GONE);
                mImageViewMore.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewPrice.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewSort.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewMeasure.setBackgroundResource(R.drawable.icon_triangle_pre);
                mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle2);

                isClickQuYu = true;
                if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_CODE))) {
                    PreferenceUtil.removeSp(Constanst.CITY_CODE, Constanst.SP_NAME);
                }

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

                if (travelType == 1 || travelType == 2) {
                    gettingAroundTravel("", "", "",productType, qita
                            , huodong, zhushu, didian, jiaotong, xiaofei,
                            "", "", "",
                            "1", "", "", String.valueOf(travelType), "");
                } else if (travelType == 3) {
                    gettingForeignTravel("", "", "", "", "", huodong, zhushu, qita ,
                            didian, jiaotong, "", xiaofei, String.valueOf(productType), "", "", "",
                            "1", "");
                } else {
                    getTicketInfo("1", "", "", "",
                            "", "", String.valueOf(productType), "", "1");
                }
                break;
            case R.id.price_Determine:
                updata();

                if (travelType == 1 || travelType == 2) {
                    //国内和周边
                    if (Integer.parseInt(minPrice) < 500) {
                        gettingAroundTravel("", "","", productType, ""
                                , "", "", "", "", "",
                                0 + "," + maxPrice, "", "",
                                "1", "", "", String.valueOf(travelType), "");
                    } else if (Integer.parseInt(minPrice) >= 500 && Integer.parseInt(minPrice) <= 7000) {
                        gettingAroundTravel("", "","", productType, ""
                                , "", "", "", "", "",
                                minPrice + "," + maxPrice, "", "",
                                "1", "", "", String.valueOf(travelType), "");

                    } else if (Integer.parseInt(minPrice) > 7000) {
                        gettingAroundTravel("", "","", productType, ""
                                , "", "", "", "", "",
                                minPrice + "," + 1000000, "", "",
                                "1", "", "", String.valueOf(travelType), "");
                    }
                } else if (travelType == 3) {
                    //境外游
                    if (Integer.parseInt(minPrice) < 500) {
                        gettingForeignTravel("", "", "", "", "", "", "", "",
                                "", "", "", "", "", 0 + "," + maxPrice, "", "",
                                "1", "");
                    } else if (Integer.parseInt(minPrice) >= 500 && Integer.parseInt(minPrice) <= 7000) {
                        gettingForeignTravel("", "", "", "", "", "", "", "",
                                "", "", "", "", "", minPrice + "," + maxPrice, "", "",
                                "1", "");

                    } else if (Integer.parseInt(minPrice) > 7000) {
                        gettingForeignTravel("", "", "", "", "", "", "", "",
                                "", "", "", "", "", 7000 + "," + 1000000, "", "",
                                "1", "");
                    }
                }else {
                    //票务
                    if (Integer.parseInt(minPrice) < 500) {
                        getTicketInfo("1", "", 0 + "," + maxPrice, "",
                                "", "", "", "", "1");
                    } else if (Integer.parseInt(minPrice) >= 500 && Integer.parseInt(minPrice) <= 7000) {
                        getTicketInfo("1", "", minPrice + "," + maxPrice, "",
                                "", "", "", "", "1");

                    } else if (Integer.parseInt(minPrice) > 7000) {
                        getTicketInfo("1", "", 7000 + "," + 1000000, "",
                                "", "", "", "", "1");
                    }
                }

                break;
            case R.id.sort_Determine:
                updata();
                if (travelType == 1 || travelType == 2) {
                    gettingAroundTravel("", "","", productType, ""
                            , "", "", "", "", "",
                            "", "", "",
                            "1", "", "", String.valueOf(travelType), "");
                } else if (travelType == 3) {
                    gettingForeignTravel("", "", "", "", "", "", "", "",
                            "", "", "", "", String.valueOf(productType), "", "", "",
                            "1", "");
                } else {
                    getTicketInfo("1", "", "", "",
                            "", "", String.valueOf(productType), "", "1");
                }

                break;
            case R.id.chufa_Determine:

                String ChufaCityCode = PreferenceUtil.getString(Constanst.CITY_CODE);
                String MuDi = PreferenceUtil.getString(Constanst.CITY_MUDI_TRAVEL_NAME);
                String MuDiProvince = PreferenceUtil.getString(Constanst.MUDI_PROVINCE_NAME);
                KyLog.d(ChufaCityCode + "travel");
                KyLog.d(MuDi + "travel");
                updata();
                if (!TextUtils.isEmpty(ChufaCityCode)) {

                    if (travelType == 1 || travelType == 2) {
                        gettingAroundTravel(ChufaCityCode, "", "", productType, ""
                                , "", "", "", "", "",
                                "", "", "",
                                "1", "", "",  String.valueOf(travelType), "");
                    } else if (travelType == 3) {
                        gettingForeignTravel(ChufaCityCode, "", "", "", "", huodong, zhushu, qita ,
                                didian, jiaotong, "", xiaofei, String.valueOf(productType), "", "", "",
                                "1", "");
                    } else {
                        getTicketInfo("1", ChufaCityCode, "", "",
                                "", "", String.valueOf(productType), "", "1");
                    }


                } else if (!TextUtils.isEmpty(MuDi) && !TextUtils.isEmpty(MuDiProvince)) {
                    if (travelType == 1 || travelType == 2) {
                        gettingAroundTravel("", MuDi, MuDiProvince, productType, ""
                                , "", "", "", "", "",
                                "","", "",
                                "1", "", "", String.valueOf(travelType), "");
                    } else if (travelType == 3) {
                        gettingForeignTravel("", "", "", MuDi.substring(1, MuDi.length() - 1), "", "", "", "" ,
                                "", "", "", xiaofei, String.valueOf(productType), "", "", "",
                                "1", "");
                    } else {
                        getTicketInfo("1", "", "", "",
                                "", "", String.valueOf(productType), "", "1");
                    }


                }
                break;

            case R.id.chufa_buxian:
                updata();
                gettingAroundTravel("", "", "", productType, ""
                        , "", "", "", "", "",
                        "", "", "",
                        "1", "", "", String.valueOf(1), "");
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
                maxPrice = "500";
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
                minPrice = "500";
                maxPrice = "1000";
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
                minPrice = "1000";
                maxPrice = "1500";
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
                minPrice = "1500";
                maxPrice = "2000";
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
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "2000";
                maxPrice = "2500";
                break;

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
                minPrice = "2500";
                maxPrice = "3000";
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
                minPrice = "3000";
                maxPrice = "4000";
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
                minPrice = "4000";
                maxPrice = "5000";
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
                minPrice = "5000";
                maxPrice = "6000";
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
                minPrice = "6000";
                maxPrice = "7000";
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
                minPrice = "7000";
                maxPrice = "8000";
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
                minPrice = "8000";
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

                productType = 3;
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
                productType = 1;
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
                productType = 5;
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
                productType = 4;
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
                productType = 7;
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
                productType = 2;
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
                productType = 6;
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
                productType = 8;
                break;
            case R.id.duantuyou:
                mTextViewDuanTuYou.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewDuanTuYou.setTextColor(getResources().getColor(R.color.white));
                mTextViewGuoNeiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoNeiYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoWaiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoWaiYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPiaoWu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewPiaoWu.setTextColor(getResources().getColor(R.color.register_font));
                gettingAroundTravel("", "","", productType, ""
                        , "", "", "", "", "",
                        "", "", "",
                        "1", "", "", String.valueOf(1), "");
                travelType = 1;
                break;
            case R.id.guoneiyou:
                mTextViewDuanTuYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewDuanTuYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoNeiYou.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewGuoNeiYou.setTextColor(getResources().getColor(R.color.white));
                mTextViewGuoWaiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoWaiYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPiaoWu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewPiaoWu.setTextColor(getResources().getColor(R.color.register_font));
                gettingAroundTravel("", "","" ,productType, ""
                        , "", "", "", "", "",
                        "", "", "",
                        "1", "", "", String.valueOf(2), "");
                travelType = 2;
                break;
            case R.id.jingwaiyou:
                mTextViewDuanTuYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewDuanTuYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoNeiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoNeiYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoWaiYou.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewGuoWaiYou.setTextColor(getResources().getColor(R.color.white));
                mTextViewPiaoWu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewPiaoWu.setTextColor(getResources().getColor(R.color.register_font));
                gettingForeignTravel("", "", "", "", "", "", "", "",
                        "", "", "", "", "", "", "", "",
                        "1", "");
                travelType = 3;
                break;
            case R.id.piaowu:
                mTextViewDuanTuYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewDuanTuYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoNeiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoNeiYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoWaiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoWaiYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPiaoWu.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewPiaoWu.setTextColor(getResources().getColor(R.color.white));
                getTicketInfo("1", "", "", "",
                        "", "", "", "", "1");
                travelType = 4;
                break;
            case R.id.toolbar_right:
                mTextViewQuXiao.setVisibility(View.VISIBLE);
                mTextViewGuanLi.setVisibility(View.GONE);
                mRecyclerViewDuoXuan.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mRelativeLayoutRL.setVisibility(View.VISIBLE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.VISIBLE);
                setEnabled(false);
                break;
            case R.id.toolbar_quxiao:
                mTextViewQuXiao.setVisibility(View.GONE);
                mTextViewGuanLi.setVisibility(View.VISIBLE);
                mRecyclerViewDuoXuan.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.GONE);
                setEnabled(true);
                break;
            case R.id.collect_btn:
                addTravelCollect(travelType);
                break;
        }
    }

    public void updata() {
        mRelativeLayoutRL.setVisibility(View.VISIBLE);
        mRecyclerViewDuoXuan.setVisibility(View.GONE);
        mLinearLayoutSorts.setVisibility(View.GONE);
        mLinearLayoutPrices.setVisibility(View.GONE);
        mLinearLayoutMores.setVisibility(View.GONE);
        mLinearLayoutMores.setVisibility(View.GONE);
        mLinearLayoutMudi.setVisibility(View.GONE);
        mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewChufa.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewMuDi.setTextColor(getResources().getColor(R.color.register_font));
        mImageViewMore.setBackgroundResource(R.drawable.icon_triangle2);
        mImageViewPrice.setBackgroundResource(R.drawable.icon_triangle2);
        mImageViewSort.setBackgroundResource(R.drawable.icon_triangle2);
        mImageViewMeasure.setBackgroundResource(R.drawable.icon_triangle2);
        mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle2);

    }

    private void setDuoXuanData(AroundTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mZhouBianDuoXuanAdapter = new ZhouBianDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mZhouBianDuoXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
//            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setData(AroundTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);

            mAdpter = new ZhouBianAdapter(entity.getList(), this,2);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRelativeLayoutSearch.setVisibility(View.VISIBLE);

//            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }
    }

    private void setJinWaiDuoXuanData(ForeignTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mJinWaiDuoXuanAdapter = new JinWaiDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mJinWaiDuoXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
//            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setJinWaiData(ForeignTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {

            LinearLayoutManager manager = new LinearLayoutManager(this);
            mJinWaiAdpter = new JingWaiAdapter(entity.getList(), this,2);
            mRecyclerView.setAdapter(mJinWaiAdpter);
            mRecyclerView.setLayoutManager(manager);
//            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }
    }

    private void setTicketDuoXuanData(TicketInfoEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mTicketingDuoXuanAdapter = new TicketingDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mTicketingDuoXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
//            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setTicketData(TicketInfoEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mTicketAdapter = new TicketingAdapter(entity.getList(), this,2);
            mRecyclerView.setAdapter(mTicketAdapter);
            mRecyclerView.setLayoutManager(manager);
//            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }

    }

    /**
     * 国内和周边
     */
    private void gettingAroundTravel(String depart_code, String goals_city, String goals_pro,
                                     int sort_type, String tOtherId,
                                     String tActivityId, String tStayId,
                                     String tAddressId, String tTrafficId,
                                     String tConsumeId, String minPri_maxPri,
                                     String numberDays, String keyWord,
                                     String curPage, String minDay, String maxDay,
                                     String travel_kind, String lineOrThrows) {
        showProgressDialog();
        ApiModule.getInstance().gettingAroundTravel(depart_code, goals_city, goals_pro,
                sort_type, tOtherId, tActivityId, tStayId, tAddressId, tTrafficId, tConsumeId, minPri_maxPri,
                numberDays, keyWord, curPage, minDay, maxDay, String.valueOf(PreferenceUtil.getInt(UID)), travel_kind, lineOrThrows,"0",String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribe(aroundTravelEntity -> {
                    cancelProgressDialog();
                    KyLog.object(aroundTravelEntity);
                    setData(aroundTravelEntity);
                    setDuoXuanData(aroundTravelEntity);

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void gettingForeignTravel(String depart_name, String min_days,
                                      String max_days, String spot_name,
                                      String goals_name, String t_activity_id,
                                      String t_stay_id, String t_other_id,
                                      String t_address_id, String t_traffic_id,
                                      String t_overseas_id,
                                      String t_consume_id, String sort_type,
                                      String minPri_maxPri, String number_days,
                                      String keyWord, String curPage,
                                      String line_or_throw) {
        showProgressDialog();
        ApiModule.getInstance().gettingForeignTravel(depart_name, min_days,
                max_days, spot_name, goals_name, t_activity_id, t_stay_id, t_other_id, t_address_id,
                t_traffic_id, t_overseas_id, t_consume_id, sort_type, minPri_maxPri, number_days, keyWord, curPage, String.valueOf(PreferenceUtil.getInt(UID)), line_or_throw,
                "0",String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribe(foreignTravelEntity -> {
                    cancelProgressDialog();
                    KyLog.object(foreignTravelEntity);
                    if (foreignTravelEntity != null) {
                        setJinWaiData(foreignTravelEntity);
                        setJinWaiDuoXuanData(foreignTravelEntity);
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    /**
     * 票务
     */
    private void getTicketInfo(String ticket_type, String ticket_city_name,
                               String minPri_maxPri, String ticket_theme_id,
                               String ticket_activity_id, String ticket_other_id,
                               String sort_type,
                               String keyWord, String curPage) {
        showProgressDialog();
        ApiModule.getInstance().getTicketInfo(ticket_type, ticket_city_name,
                minPri_maxPri, ticket_theme_id, ticket_activity_id, ticket_other_id, sort_type,
                keyWord, curPage, String.valueOf(PreferenceUtil.getInt(UID)),"0",String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribe(ticketInfoEntity -> {
                    cancelProgressDialog();
                    if (ticketInfoEntity != null) {
                        KyLog.object(ticketInfoEntity);
                        setTicketData(ticketInfoEntity);
                        setTicketDuoXuanData(ticketInfoEntity);
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void addTravelCollect(int productType) {
        KyLog.d(PreferenceUtil.getString(Constanst.PID_TRAVEL_COLLECT));
        showProgressDialog();
        ApiModule.getInstance().addTravelCollect(PreferenceUtil.getString(Constanst.PID_TRAVEL_COLLECT), productType)
                .subscribe(response -> {
                    KyLog.object(response + "");
                    cancelProgressDialog();
                    Intent intent =  new Intent(this,DataBaseTravelActivity.class);
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
        Kouweilist.add("海边");
        Kouweilist.add("登山");
        Kouweilist.add("古镇");
        Kouweilist.add("古迹");
        Kouweilist.add("主题乐园");
        Kouweilist.add("雪山");
        Kouweilist.add("沙漠");
        Kouweilist.add("湖泊");
        Kouweilist.add("江河");
        Kouweilist.add("瀑布");
        Kouweilist.add("都市");
        Kouweilist.add("草原");
        Kouweilist.add("峡谷");
        Kouweilist.add("地质公园");
        Kouweilist.add("热带雨林");
        Kouweilist.add("海岛");
        return Kouweilist;
    }

    private List<String> setJiaoTong() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("直飞");
        Kouweilist.add("双飞");
        Kouweilist.add("三飞");
        Kouweilist.add("四飞");
        Kouweilist.add("双高");
        Kouweilist.add("高铁");
        Kouweilist.add("卧铺");
        Kouweilist.add("含接送");
        Kouweilist.add("含小交通");
        Kouweilist.add("不走回头");
        Kouweilist.add("一飞一高");
        Kouweilist.add("双飞一高");
        Kouweilist.add("联运");
        Kouweilist.add("星级航空");
        Kouweilist.add("A380");
        Kouweilist.add("观景火车");
        Kouweilist.add("双游船");

        return Kouweilist;
    }

    private List<String> setXiaoFei() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("零自费");
        Kouweilist.add("零购物");
        Kouweilist.add("无抵消");
        Kouweilist.add("无小费");
        Kouweilist.add("一价全包");

        return Kouweilist;
    }

    private List<String> setHuoDong() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("蜜月");
        Kouweilist.add("购物");
        Kouweilist.add("摄影");
        Kouweilist.add("自驾");
        Kouweilist.add("拓展");
        Kouweilist.add("户外");
        Kouweilist.add("亲子");
        Kouweilist.add("美食");
        Kouweilist.add("潜水");
        Kouweilist.add("温泉");
        Kouweilist.add("冲浪");
        Kouweilist.add("休闲");
        Kouweilist.add("纯玩");
        Kouweilist.add("艺术");
        Kouweilist.add("宗教");
        Kouweilist.add("赏花");
        Kouweilist.add("游学");
        Kouweilist.add("漂流");
        Kouweilist.add("热气球");
        Kouweilist.add("蹦极");
        Kouweilist.add("骑马");
        Kouweilist.add("玻璃栈道");
        return Kouweilist;
    }


    private List<String> setZhuShu() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("特色客栈");
        Kouweilist.add("特色酒店");
        Kouweilist.add("私家别墅");
        Kouweilist.add("海景房");
        Kouweilist.add("农家乐");
        Kouweilist.add("三星酒店");
        Kouweilist.add("四星酒店");
        Kouweilist.add("五星酒店");


        return Kouweilist;
    }

    private List<String> setJinWai() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("免签");
        Kouweilist.add("落地签");
        Kouweilist.add("国际WiFi");
        Kouweilist.add("可开仓");
        Kouweilist.add("外籍可定");
        Kouweilist.add("免税店");
        Kouweilist.add("奥特莱斯");
        Kouweilist.add("当地录指纹");
        Kouweilist.add("两件托运行李");
        Kouweilist.add("拒签全退");
        return Kouweilist;
    }

    private List<String> setQiTa() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("游轮游");
        Kouweilist.add("大巴游");
        Kouweilist.add("定制游");
        Kouweilist.add("独立成团");
        Kouweilist.add("自驾");
        Kouweilist.add("自由行");
        Kouweilist.add("半自助");
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

        GridLayoutManager managerFangBen = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setJiaoTong(), this, 11);
        mRecyclerViewJiaoTong.setAdapter(mAdapterTableName);
        mRecyclerViewJiaoTong.setLayoutManager(managerFangBen);
//        mRecyclerViewJiaoTong.addItemDecoration(new SpaceItemDecoration(0, 25));

        GridLayoutManager managerLouLing = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setQiTa(), this, 10);
        mRecyclerViewQiTa.setAdapter(mAdapterTableName);
        mRecyclerViewQiTa.setLayoutManager(managerLouLing);
//        mRecyclerViewQiTa.addItemDecoration(new SpaceItemDecoration(0, 25));

        GridLayoutManager managerYongTu = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setXiaoFei(), this, 9);
        mRecyclerViewXiaoFei.setAdapter(mAdapterTableName);
        mRecyclerViewXiaoFei.setLayoutManager(managerYongTu);
//        mRecyclerViewXiaoFei.addItemDecoration(new SpaceItemDecoration(0, 25));

        GridLayoutManager managerZhuShu = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setZhuShu(), this, 8);
        mRecyclerViewZhuShu.setAdapter(mAdapterTableName);
        mRecyclerViewZhuShu.setLayoutManager(managerZhuShu);
//        mRecyclerViewZhuShu.addItemDecoration(new SpaceItemDecoration(0, 25));
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
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        switch (i) {
            case EditorInfo.IME_ACTION_GO:
                String search = mEditTextSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(search)) {
                    if (travelType == 1 || travelType == 2) {
                        gettingAroundTravel("", "","", productType, ""
                                , "", "", "", "", "",
                                "", "", search,
                                "1", "", "", String.valueOf(travelType), "");
                    } else if (travelType == 3) {
                        gettingForeignTravel("", "", "", "", "", "", "", "",
                                "", "", "", "", "", "", "", search,
                                "1", "");
                    }
                } else {
                    Toast.makeText(DataBaseTravelActivity.this, "请填写手机号", Toast.LENGTH_SHORT).show();
                }
                KyLog.d("Done_content: " + search);
                break;

        }
        return true;
    }

    private void setEnabled(boolean isFocusable) {
        mLinearLayoutChuFa.setClickable(isFocusable);
        mLinearLayoutMore.setClickable(isFocusable);
        mLinearLayoutPrice.setClickable(isFocusable);
        mLinearLayoutSort.setClickable(isFocusable);
        mLinearLayoutMuDi.setClickable(isFocusable);
        mRecyclerViewDuoXuan.setClickable(isFocusable);


        mLinearLayoutChuFa.setFocusable(isFocusable);
        mLinearLayoutMore.setFocusable(isFocusable);
        mLinearLayoutPrice.setFocusable(isFocusable);
        mLinearLayoutSort.setFocusable(isFocusable);
        mLinearLayoutMuDi.setFocusable(isFocusable);
        mRecyclerViewDuoXuan.setFocusable(isFocusable);


    }

    public void getProvinces(int type) {
        showProgressDialog();
        ApiModule.getInstance().getProvinces().subscribe(provinceEntities -> {
            cancelProgressDialog();
            if (provinceEntities != null && provinceEntities.size() > 0) {
                LinearLayoutManager manager = new LinearLayoutManager(this);
                ProvincesTravelsAdapter mAdapter = new ProvincesTravelsAdapter(provinceEntities, this, type);
                mRecyclerViewAreaOne.setAdapter(mAdapter);
                mRecyclerViewAreaOne.setLayoutManager(manager);
                mAdapter.setOnItemClickListener(new ProvincesTravelsAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        PreferenceUtil.putString(Constanst.MUDI_PROVINCE_NAME, provinceEntities.get(position).getProvince_name());
                        getInlandCity(provinceEntities.get(position).getProvince_code(), type);
                        mRecyclerViewAreaTwo.setVisibility(View.VISIBLE);

                    }
                });
                mRecyclerViewAreaTwo.setVisibility(View.GONE);
            }
        }, throwable -> {
            KyLog.d(throwable.toString());
            cancelProgressDialog();
            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void getInlandCity(String provinceCode, int type) {
        showProgressDialog();
        ApiModule.getInstance().getInlandCity(provinceCode).subscribe(inlandCityEntities -> {
            cancelProgressDialog();
            if (inlandCityEntities != null && inlandCityEntities.size() > 0) {
                LinearLayoutManager manager = new LinearLayoutManager(this);
                CityTravelsAdapter mAdapter = new CityTravelsAdapter(inlandCityEntities, this, type);
                mRecyclerViewAreaTwo.setAdapter(mAdapter);
                mRecyclerViewAreaTwo.setLayoutManager(manager);
                mAdapter.NotifyChanged();

            }
        }, throwable -> {
            KyLog.d(throwable.toString());
            cancelProgressDialog();
            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

}
