package com.huxin.communication.ui.travel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.CaiXianAdapter;
import com.huxin.communication.adpter.CaiXianDuoXuanAdapter;
import com.huxin.communication.adpter.CaiXianDuoXuanForeignAdapter;
import com.huxin.communication.adpter.CaiXianForeignAdapter;
import com.huxin.communication.adpter.CityTravelsAdapter;
import com.huxin.communication.adpter.JingWaiAdapter;
import com.huxin.communication.adpter.ProvincesTravelsAdapter;
import com.huxin.communication.adpter.ShaiXuanTabNameAdapter;
import com.huxin.communication.adpter.ZhouBianAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.my.collect.DataBaseTravelActivity;
import com.huxin.communication.ui.my.tuijian.TuiJianActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SwipeRefreshView;
import com.sky.kylog.KyLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CaiXianActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewDuoXuan;

    private LinearLayout mLinearLayoutSort;
    private LinearLayout mLinearLayoutPrice;
    private LinearLayout mLinearLayoutMore;

    private LinearLayout mLinearLayoutSorts;
    private LinearLayout mLinearLayoutPrices;
    private RelativeLayout mLinearLayoutMores;

    private RelativeLayout mRelativeLayoutSearch;


    private TextView mTextViewSort;
    private TextView mTextViewPrice;
    private TextView mTextViewMore;

    private TextView mTextViewDetermineSort;
    private TextView mTextViewDeterminePrice;
    private TextView mTextViewDetermineMore;


    private TextView mTextViewGuanLi;
    private TextView mTextViewQuXiao;

    private TextView mTextViewCaiXian;
    private TextView mTextViewShuaiWei;
    private TextView mTextViewDuanTuYou;
    private TextView mTextViewGuoNeiYou;
    private TextView mTextViewGuoWaiYou;

    private RelativeLayout mRelativeLayoutDuoxuanBtn;
    private LinearLayout mRelativeLayoutRL;
    private ImageView mImageViewPrice;
    private ImageView mImageViewSort;
    private ImageView mImageViewMeasure;
    private ImageView mImageViewMore;
    private ImageView mImageViewFangxin;

    private CaiXianAdapter mAdpter;
    private CaiXianDuoXuanAdapter mCaiXianDuoXuanAdapter;

    private CaiXianForeignAdapter mForeignAdapter;
    private CaiXianDuoXuanForeignAdapter mDuoXuanForeignAdapter;

    private int lineOrThrow = 1;

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

    private boolean isClickQuYu = false;


    private RelativeLayout mLinearLayoutMudi;
    private RecyclerView mRecyclerViewAreaOne;
    private RecyclerView mRecyclerViewAreaTwo;
    private TextView mTextViewChufa;
    private TextView mTextViewMuDi;

    private TextView mTextViewChuFaDetermine;
    private TextView mTextViewChuFaBuXian;
    private TextView mTextViewCity;
    private TextView mTextViewMoren;


    private int travel_kind;

    private TextView mTextViewCollect;
    private TextView mTextViewDelete;

    private SwipeRefreshView mSwipeRefreshView;

    private int mCurrentPage = 1;

    private List<TicketInfoEntity.ListBean> list = new ArrayList<>();
    private List<AroundTravelEntity.ListBean> listZhouBian = new ArrayList<>();
    private List<ForeignTravelEntity.ListBean> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_cai_xian);

    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("", MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyler_domestic);
        mRecyclerViewDuoXuan = (RecyclerView) findViewById(R.id.recyler_domestic_duoxuan);


        mLinearLayoutMore = (LinearLayout) findViewById(R.id.more);
        mLinearLayoutPrice = (LinearLayout) findViewById(R.id.price);
        mLinearLayoutSort = (LinearLayout) findViewById(R.id.sort);

        mLinearLayoutMores = (RelativeLayout) findViewById(R.id.travel_more);
        mLinearLayoutPrices = (LinearLayout) findViewById(R.id.travel_price);
        mLinearLayoutSorts = (LinearLayout) findViewById(R.id.travel_sort);


        mTextViewSort = (TextView) findViewById(R.id.tv_sort);
        mTextViewPrice = (TextView) findViewById(R.id.tv_price);
        mTextViewMore = (TextView) findViewById(R.id.tv_more);

        mTextViewDetermineSort = (TextView) findViewById(R.id.sort_Determine);
        mTextViewDetermineMore = (TextView) findViewById(R.id.more_Determine);
        mTextViewDeterminePrice = (TextView) findViewById(R.id.price_Determine);

        mRelativeLayoutSearch = (RelativeLayout) findViewById(R.id.search_rl);
        mRelativeLayoutDuoxuanBtn = (RelativeLayout) findViewById(R.id.btn_duoxuan);
        mRelativeLayoutRL = (LinearLayout) findViewById(R.id.rl_recyler_sell);

        mImageViewFangxin = (ImageView) findViewById(R.id.chufadi);
        mImageViewPrice = (ImageView) findViewById(R.id.image_price);
        mImageViewSort = (ImageView) findViewById(R.id.image_sort);
        mImageViewMeasure = (ImageView) findViewById(R.id.mudidi);
        mImageViewMore = (ImageView) findViewById(R.id.image_more);

        mTextViewGuanLi = (TextView) findViewById(R.id.toolbar_right);
        mTextViewQuXiao = (TextView) findViewById(R.id.toolbar_quxiao);

        mTextViewCaiXian = (TextView) findViewById(R.id.caixian);
        mTextViewShuaiWei = (TextView) findViewById(R.id.shuaiwei);
        mTextViewDuanTuYou = (TextView) findViewById(R.id.duantuyou);
        mTextViewGuoNeiYou = (TextView) findViewById(R.id.guoneiyou);
        mTextViewGuoWaiYou = (TextView) findViewById(R.id.jingwaiyou);


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


        mLinearLayoutMudi = findViewById(R.id.travel_mudi);
        mRecyclerViewAreaOne = findViewById(R.id.recyclerView_provinces);
        mRecyclerViewAreaTwo = findViewById(R.id.recycler_city);
        mTextViewChufa = findViewById(R.id.tv_chufa);
        mTextViewMuDi = findViewById(R.id.tv_fangxin);

        mTextViewChuFaDetermine = findViewById(R.id.chufa_Determine);
        mTextViewChuFaBuXian = findViewById(R.id.chufa_buxian);
        mTextViewCity = findViewById(R.id.tv_city);
        mTextViewMoren = findViewById(R.id.moren);

        mTextViewCollect = findViewById(R.id.collect_btn);
        mTextViewDelete = findViewById(R.id.delete_collect);

        mSwipeRefreshView = findViewById(R.id.swipeRefreshLayout);


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

        mTextViewCaiXian.setOnClickListener(this);
        mTextViewShuaiWei.setOnClickListener(this);
        mTextViewDuanTuYou.setOnClickListener(this);
        mTextViewGuoNeiYou.setOnClickListener(this);
        mTextViewGuoWaiYou.setOnClickListener(this);


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
        mTextViewCollect.setOnClickListener(this);
        mTextViewDelete.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        mSwipeRefreshView.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        mSwipeRefreshView.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_blue_bright, R.color.colorPrimaryDark,
                android.R.color.holo_orange_dark, android.R.color.holo_red_dark, android.R.color.holo_purple);


        // 手动调用,通知系统去测量
        mSwipeRefreshView.measure(0, 0);
        mSwipeRefreshView.setRefreshing(true);
        initEvent();
        setEnabled(true);
        if (lineOrThrow == 1) {
            gettingAroundTravel("", "", "", productType, ""
                    , "", "", "", "", "",
                    "", "", "",
                    "1", "", "", null, String.valueOf(travel_kind), String.valueOf(lineOrThrow));
        } else {
            gettingForeignTravel("", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "",
                    "1", null, String.valueOf(lineOrThrow));
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

    private void initEvent() {

        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        mSwipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (lineOrThrow == 1) {
                            gettingAroundTravel("", "", "", productType, ""
                                    , "", "", "", "", "",
                                    "", "", "",
                                    "1", "", "", null, String.valueOf(travel_kind), String.valueOf(lineOrThrow));
                        } else {
                            gettingForeignTravel("", "", "", "", "", "", "", "",
                                    "", "", "", "", "", "", "", "",
                                    "1", null, String.valueOf(lineOrThrow));
                        }


                    }
                }, 2000);
            }
        });
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
                mTextViewMoren.setVisibility(View.VISIBLE);
                mTextViewCity.setVisibility(View.VISIBLE);

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

                updata();

                mLinearLayoutSorts.setVisibility(View.GONE);
                mLinearLayoutPrices.setVisibility(View.GONE);
                mLinearLayoutMores.setVisibility(View.GONE);
                mLinearLayoutMudi.setVisibility(View.VISIBLE);
                mTextViewMoren.setVisibility(View.GONE);
                mTextViewCity.setVisibility(View.GONE);

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

                if (lineOrThrow == 1) {
                    gettingAroundTravel("", "", "", productType, qita
                            , huodong, zhushu, didian, jiaotong, xiaofei,
                            "", "", "",
                            "1", "", "", null, String.valueOf(1), String.valueOf(lineOrThrow));
                } else {
                    gettingForeignTravel("", "", "", "", "", huodong, zhushu, qita,
                            didian, jiaotong, "", xiaofei, "", 0 + "," + maxPrice, "", "",
                            "1", null, String.valueOf(lineOrThrow));
                }

                break;
            case R.id.price_Determine:
                updata();
                if (Integer.parseInt(minPrice) < 500) {

                    if (lineOrThrow == 1) {
                        gettingAroundTravel("", "", "", productType, ""
                                , "", "", "", "", "",
                                0 + "," + maxPrice, "", "",
                                "1", "", "", null, String.valueOf(1), String.valueOf(lineOrThrow));
                    } else {
                        gettingForeignTravel("", "", "", "", "", "", "", "",
                                "", "", "", "", "", 0 + "," + maxPrice, "", "",
                                "1", null, String.valueOf(lineOrThrow));
                    }


                } else if (Integer.parseInt(minPrice) >= 500 && Integer.parseInt(minPrice) <= 7000) {

                    if (lineOrThrow == 1) {
                        gettingAroundTravel("", "", "", productType, ""
                                , "", "", "", "", "",
                                minPrice + "," + maxPrice, "", "",
                                "1", "", "", null, String.valueOf(1), String.valueOf(lineOrThrow));
                    } else {
                        gettingForeignTravel("", "", "", "", "", "", "", "",
                                "", "", "", "", "", minPrice + "," + maxPrice, "", "",
                                "1", null, String.valueOf(lineOrThrow));
                    }
                } else if (Integer.parseInt(minPrice) > 7000) {
                    if (lineOrThrow == 1) {
                        gettingAroundTravel("", "", "", productType, ""
                                , "", "", "", "", "",
                                minPrice + "," + 1000000, "", "",
                                "1", "", "", null, String.valueOf(1), String.valueOf(lineOrThrow));
                    } else {
                        gettingForeignTravel("", "", "", "", "", "", "", "",
                                "", "", "", "", "", minPrice + "," + 1000000, "", "",
                                "1", null, String.valueOf(lineOrThrow));
                    }

                }
                break;
            case R.id.sort_Determine:
                updata();
                if (lineOrThrow == 1) {
                    gettingAroundTravel("", "", "", productType, ""
                            , "", "", "", "", "",
                            "", "", "",
                            "1", "", "", "", String.valueOf(1), String.valueOf(lineOrThrow));
                } else {
                    gettingForeignTravel("", "", "", "", "", "", "", "",
                            "", "", "", "", "", "", "", "",
                            "1", null, String.valueOf(lineOrThrow));
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
                    gettingAroundTravel(ChufaCityCode, "", "", productType, ""
                            , "", "", "", "", "",
                            "", "", "",
                            "1", "", "", null, String.valueOf(1), "");
                } else if (!TextUtils.isEmpty(MuDi) && !TextUtils.isEmpty(MuDiProvince)) {
                    gettingAroundTravel("", MuDi, MuDiProvince, productType, ""
                            , "", "", "", "", "",
                            "", "", "",
                            "1", "", "", null, String.valueOf(1), "");
                }
                break;

            case R.id.chufa_buxian:
                gettingAroundTravel("", "", "", productType, ""
                        , "", "", "", "", "",
                        "", "", "",
                        "1", "", "", null, String.valueOf(1), "");
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

            case R.id.caixian:
                //踩线
                mTextViewCaiXian.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewCaiXian.setTextColor(getResources().getColor(R.color.white));
                mTextViewShuaiWei.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewShuaiWei.setTextColor(getResources().getColor(R.color.register_font));
                lineOrThrow = 1;
                break;
            case R.id.shuaiwei:
                //甩位
                mTextViewCaiXian.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewCaiXian.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewShuaiWei.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewShuaiWei.setTextColor(getResources().getColor(R.color.white));
                lineOrThrow = 2;
                break;
            case R.id.duantuyou:
                //短途游
                mTextViewDuanTuYou.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewDuanTuYou.setTextColor(getResources().getColor(R.color.white));
                mTextViewGuoNeiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoNeiYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoWaiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoWaiYou.setTextColor(getResources().getColor(R.color.register_font));
                travel_kind = 1;
                gettingAroundTravel("", "", "", productType, ""
                        , "", "", "", "", "",
                        "", "1", "",
                        "1", "", "", null, String.valueOf(1), String.valueOf(lineOrThrow));

                break;
            case R.id.guoneiyou:
                //国内游
                mTextViewDuanTuYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewDuanTuYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoNeiYou.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewGuoNeiYou.setTextColor(getResources().getColor(R.color.white));
                mTextViewGuoWaiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoWaiYou.setTextColor(getResources().getColor(R.color.register_font));
                travel_kind = 2;

                gettingAroundTravel("", "", "", productType, ""
                        , "", "", "", "", "",
                        "", "2", "",
                        "1", "", "", "", String.valueOf(2), "");

                break;
            case R.id.jingwaiyou:
                //境外游
                mTextViewDuanTuYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewDuanTuYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoNeiYou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewGuoNeiYou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewGuoWaiYou.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewGuoWaiYou.setTextColor(getResources().getColor(R.color.white));
                travel_kind = 3;

                gettingForeignTravel("", "", "", "", "", "", "", "",
                        "", "", "", "", String.valueOf(productType), "", "", "",
                        "1", null, String.valueOf(lineOrThrow));
                break;

            case R.id.toolbar_right:
                mTextViewQuXiao.setVisibility(View.VISIBLE);
                mTextViewGuanLi.setVisibility(View.GONE);
                mRecyclerViewDuoXuan.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mRelativeLayoutRL.setVisibility(View.VISIBLE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.VISIBLE);
                setEnabled(false);

                mSwipeRefreshView.setVisibility(View.GONE);

                break;
            case R.id.toolbar_quxiao:
                mTextViewQuXiao.setVisibility(View.GONE);
                mTextViewGuanLi.setVisibility(View.VISIBLE);
                mRecyclerViewDuoXuan.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.GONE);
                setEnabled(true);

                mSwipeRefreshView.setVisibility(View.VISIBLE);

                break;

            case R.id.collect_btn:
                addTravelCollect(travel_kind);
                break;

            case R.id.delete_collect:
                //转发
                if (travel_kind == 1 || travel_kind == 2) {
                    updateIssueCount(1);
                } else if (travel_kind == 3) {
                    updateIssueCount(2);
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
            mRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mCaiXianDuoXuanAdapter = new CaiXianDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mCaiXianDuoXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setData(AroundTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);

            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpter = new CaiXianAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRelativeLayoutSearch.setVisibility(View.VISIBLE);
            mAdpter.setOnLoadMoreListener(new ZhouBianAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore(int currentPage) {
                    mCurrentPage = currentPage;
                    loadMore(mAdpter);
                }
            });

        } else {

            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();

        }
    }

    private void setForeignDuoXuanData(ForeignTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);

            LinearLayoutManager manager = new LinearLayoutManager(this);
            mDuoXuanForeignAdapter = new CaiXianDuoXuanForeignAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mDuoXuanForeignAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setForeignData(ForeignTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);

            LinearLayoutManager manager = new LinearLayoutManager(this);
            mForeignAdapter = new CaiXianForeignAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mForeignAdapter);
            mRecyclerView.setLayoutManager(manager);
            mForeignAdapter.setOnLoadMoreListener(new ZhouBianAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore(int currentPage) {
                    mCurrentPage = currentPage;
                    loadJinWaiMore(mForeignAdapter);
                }
            });
        } else {
            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();

        }
    }


    private void loadMore(CaiXianAdapter adapter) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ApiModule.getInstance().gettingAroundTravel("", "", "",
                        productType, "", "", "", "", "", "", "",
                        "", "", String.valueOf(mCurrentPage), "", "", null, String.valueOf(1), "", "0", String.valueOf(PreferenceUtil.getInt(UID)))
                        .subscribe(aroundTravelEntity -> {

                            if (aroundTravelEntity.getList() != null && aroundTravelEntity.getList().size() > 0) {
                                listZhouBian.addAll(aroundTravelEntity.getList());
//                                            if (page < Integer.parseInt(aroundTravelEntity.getCurPage())) {
                                if (aroundTravelEntity.getPageSize() == 15) {
                                    adapter.setCanLoadMore(true);
                                } else {
                                    adapter.setCanLoadMore(false);
                                }

                                adapter.setData(listZhouBian);
                            } else {
                                adapter.setCanLoadMore(false);
                                adapter.notifyDataSetChanged();
                            }

                        }, throwable -> {

                            KyLog.d(throwable.toString());
                            Toast.makeText(CaiXianActivity.this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        });
            }
        }, 2000);
    }

    private void loadJinWaiMore(CaiXianForeignAdapter adapter) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ApiModule.getInstance().gettingForeignTravel("", "",
                        "", "", "", "", "", "", "",
                        "", "", "", "", "", "", "", String.valueOf(mCurrentPage), null, "", "0", String.valueOf(PreferenceUtil.getInt(UID)))
                        .subscribe(foreignTravelEntity -> {
                            KyLog.d(foreignTravelEntity.getPageSize() + "page");

                            if (foreignTravelEntity.getPageSize() == 15) {
                                adapter.setCanLoadMore(true);
                            } else {
                                adapter.setCanLoadMore(false);
                            }

                            if (foreignTravelEntity.getList() != null && foreignTravelEntity.getList().size() > 0) {
                                lists.addAll(foreignTravelEntity.getList());
                                adapter.setData(lists);
                            } else {
                                adapter.setCanLoadMore(false);
                                adapter.notifyDataSetChanged();
                            }


                        }, throwable -> {
                            KyLog.d(throwable.toString());
                            Toast.makeText(CaiXianActivity.this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        });
            }
        }, 2000);
    }

    //国内
    private void gettingAroundTravel(String depart_code, String goals_city, String goals_pro,
                                     int sort_type, String tOtherId,
                                     String tActivityId, String tStayId,
                                     String tAddressId, String tTrafficId,
                                     String tConsumeId, String minPri_maxPri,
                                     String numberDays, String keyWord,
                                     String curPage, String minDay, String maxDay, String uid,
                                     String travel_kind, String lineOrThrows) {
        ApiModule.getInstance().gettingAroundTravel(depart_code, goals_city, goals_pro,
                sort_type, tOtherId, tActivityId, tStayId, tAddressId, tTrafficId, tConsumeId, minPri_maxPri,
                numberDays, keyWord, curPage, minDay, maxDay, uid, travel_kind, lineOrThrows, "", String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribe(aroundTravelEntity -> {
                    KyLog.object(aroundTravelEntity);
                    setData(aroundTravelEntity);
                    setDuoXuanData(aroundTravelEntity);
                    if (mSwipeRefreshView.isRefreshing()) {
                        mSwipeRefreshView.setRefreshing(false);
                    }
                    if (aroundTravelEntity.getList() != null && aroundTravelEntity.getList().size() > 0) {
                        listZhouBian.addAll(aroundTravelEntity.getList());
                    }


                }, throwable -> {
                    if (mSwipeRefreshView.isRefreshing()) {
                        mSwipeRefreshView.setRefreshing(false);
                    }
                    KyLog.d(throwable.toString());
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    //国外
    private void gettingForeignTravel(String depart_name, String min_days,
                                      String max_days, String spot_name,
                                      String goals_name, String t_activity_id,
                                      String t_stay_id, String t_other_id,
                                      String t_address_id, String t_traffic_id,
                                      String t_overseas_id,
                                      String t_consume_id, String sort_type,
                                      String minPri_maxPri, String number_days,
                                      String keyWord, String curPage, String uid,
                                      String line_or_throw) {
        ApiModule.getInstance().gettingForeignTravel(depart_name, min_days,
                max_days, spot_name, goals_name, t_activity_id, t_stay_id, t_other_id, t_address_id,
                t_traffic_id, t_overseas_id, t_consume_id, sort_type, minPri_maxPri, number_days, keyWord, curPage, uid, "0", line_or_throw, String.valueOf(PreferenceUtil.getInt(UID)))
                .subscribe(foreignTravelEntity -> {
                    KyLog.object(foreignTravelEntity);
                    if (foreignTravelEntity != null) {
                        setForeignData(foreignTravelEntity);
                        setForeignDuoXuanData(foreignTravelEntity);
                    }
                    if (mSwipeRefreshView.isRefreshing()) {
                        mSwipeRefreshView.setRefreshing(false);
                    }
                    if (foreignTravelEntity.getList() != null && foreignTravelEntity.getList().size() > 0) {
                        lists.addAll(foreignTravelEntity.getList());
                    }

                }, throwable -> {
                    if (mSwipeRefreshView.isRefreshing()) {
                        mSwipeRefreshView.setRefreshing(false);
                    }
                    KyLog.d(throwable.toString());
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

    private String getDuanTuData(ArrayList<AroundTravelEntity.ListBean> Salelist, int type) {
        String str = "";
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject data = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            if (Salelist != null && Salelist.size() > 0) {
                for (AroundTravelEntity.ListBean SaleEntity : Salelist) {
                    JSONObject dataObj = new JSONObject();
                    dataObj.put("depart_name", SaleEntity.getDepart_name());
                    dataObj.put("goals_city", String.valueOf(SaleEntity.getGoals_city()));
                    dataObj.put("headUrl", String.valueOf(SaleEntity.getHeadUrl()));
                    dataObj.put("numberDays", String.valueOf(SaleEntity.getNumberDays()));
                    dataObj.put("photo_url", SaleEntity.getPhoto_url());
                    dataObj.put("finalPrice", String.valueOf(SaleEntity.getReturnPrice()));
                    dataObj.put("finalPriceChild", SaleEntity.getReturnPriceChild());
                    dataObj.put("totalPrice", String.valueOf(SaleEntity.getTotalPrice()));
                    dataObj.put("totalPriceChild", SaleEntity.getTotalPriceChild());
                    dataObj.put("spotName", String.valueOf(SaleEntity.getSpotName()));
                    dataObj.put("tagName", SaleEntity.getTagName());
                    dataObj.put("userCity", SaleEntity.getUserCity());
                    dataObj.put("username", SaleEntity.getUsername());
                    dataObj.put("TActivityId", SaleEntity.getTActivityId());
                    dataObj.put("TAddressId", SaleEntity.getTAddressId());
                    dataObj.put("TConsumeId", SaleEntity.getTConsumeId());
                    dataObj.put("TOtherId", SaleEntity.getTOtherId());
                    dataObj.put("TOverseasId", SaleEntity.getTOverseasId());
                    dataObj.put("TStayId", SaleEntity.getTStayId());
                    dataObj.put("TTrafficId", SaleEntity.getTTrafficId());
                    dataObj.put("companyName", SaleEntity.getCompanyName());
                    jsonArray.put(dataObj);
                }
                data.put("list", jsonArray);
                jsonObject.put("type", 2);
                jsonObject.put("travelType", 1);
                jsonObject.put("data", data);
            }
            str = jsonObject.toString();
            KyLog.i("getData str = " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private String getJingWaiData(ArrayList<ForeignTravelEntity.ListBean> Salelist, int type) {
        String str = "";
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject data = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            if (Salelist != null && Salelist.size() > 0) {
                for (ForeignTravelEntity.ListBean SaleEntity : Salelist) {
                    JSONObject dataObj = new JSONObject();
                    dataObj.put("depart_name", SaleEntity.getDepart_name());
                    dataObj.put("goals_name", SaleEntity.getGoals_nat_name());
                    dataObj.put("headUrl", String.valueOf(SaleEntity.getHeadUrl()));
                    dataObj.put("number_days", String.valueOf(SaleEntity.getNumber_days()));
                    dataObj.put("photo_url", SaleEntity.getPhoto_url());
                    dataObj.put("return_price", String.valueOf(SaleEntity.getReturn_price()));
                    dataObj.put("return_price_child", SaleEntity.getReturn_price_child());
                    dataObj.put("total_price", String.valueOf(SaleEntity.getTotal_price()));
                    dataObj.put("total_price_child", SaleEntity.getTotal_price_child());
                    dataObj.put("spotName", String.valueOf(SaleEntity.getSpot_name()));
                    dataObj.put("tagName", SaleEntity.getTagName());
                    dataObj.put("userCity", SaleEntity.getUserCity());
                    dataObj.put("username", SaleEntity.getUsername());
                    dataObj.put("travel_title", SaleEntity.getTravel_title());
                    jsonArray.put(dataObj);
                }
                data.put("list", jsonArray);
                jsonObject.put("type", 2);
                jsonObject.put("travelType", 3);
                jsonObject.put("data", data);
            }
            str = jsonObject.toString();
            KyLog.i("getData str = " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private void zhuanFaDuanTu() {
        String data = getDuanTuData(mCaiXianDuoXuanAdapter.getSelectedItem(), 1);
        KyLog.i("zhuanfa data = " + data);
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        bundle.putString("from", "zhuanfa");
        Intent intent = new Intent(CaiXianActivity.this, TuiJianActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void zhuanFaGuoNei() {
        String data = getDuanTuData(mCaiXianDuoXuanAdapter.getSelectedItem(), 2);
        KyLog.i("zhuanfa data = " + data);
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        bundle.putString("from", "zhuanfa");
        Intent intent = new Intent(CaiXianActivity.this, TuiJianActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void zhuanFaJingWai() {
        String data = getJingWaiData(mDuoXuanForeignAdapter.getSelectedItem(), 2);
        KyLog.i("zhuanfa data = " + data);
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        bundle.putString("from", "zhuanfa");
        Intent intent = new Intent(CaiXianActivity.this, TuiJianActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
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

    private void updateIssueCount(int travelTypes) {
        showProgressDialog();
        ApiModule.getInstance().updateIssueCount(PreferenceUtil.getString(Constanst.PID_TRAVEL_COLLECT), String.valueOf(travelTypes))
                .subscribe(response -> {
                    KyLog.object(response + "");
                    cancelProgressDialog();
                    if (travel_kind == 1) {
                        zhuanFaDuanTu();
                    }
                    if (travel_kind == 2) {
                        zhuanFaGuoNei();
                    }
                    if (travel_kind == 3) {
                        zhuanFaJingWai();
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
                    Intent intent = new Intent(this, CollectTravelActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


}
