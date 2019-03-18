package com.huxin.communication.ui.house.sell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.huxin.communication.adpter.HouseSearchAdapter;
import com.huxin.communication.adpter.HouseSearchDuoXuanAdapter;
import com.huxin.communication.adpter.SellAdpter;
import com.huxin.communication.adpter.SellDuoXuanAdapter;
import com.huxin.communication.adpter.ShaiXuanTabNameAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AreaOneScreenEntity;
import com.huxin.communication.entity.HouseEntity;
import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.huxin.communication.entity.SelectFrameEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.KeFuActivity;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.ui.house.phone.AddFriendActivity;
import com.huxin.communication.ui.my.tuijian.TuiJianActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;

import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SellActivity extends BaseActivity implements View.OnClickListener, EditText.OnEditorActionListener {


    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewDuoXuan;
    private RecyclerView mRecyclerViewChaoXaing;
    private RecyclerView mRecyclerViewFangBen;
    private RecyclerView mRecyclerViewZhuangXiu;
    private RecyclerView mRecyclerViewJiaJuJiaDian;
    private RecyclerView mRecyclerViewLouLing;
    private RecyclerView mRecyclerViewYongTu;


    private LinearLayout mLinearLayoutSort;
    private LinearLayout mLinearLayoutMeasure;
    private LinearLayout mLinearLayoutPrice;
    private LinearLayout mLinearLayoutMore;
    private LinearLayout mLinearLayoutFangXing;
    private LinearLayout mLinearLayoutQuYu;


    private LinearLayout mLinearLayoutSorts;
    private LinearLayout mLinearLayoutMeasures;
    private LinearLayout mLinearLayoutPrices;
    private LinearLayout mLinearLayoutMores;
    private LinearLayout mLinearLayoutFangXings;

    private RelativeLayout mRelativeLayoutSearch;
    private RelativeLayout mRelativeLayoutDuoxuanBtn;
    private LinearLayout mRelativeLayoutRL;


    private TextView mTextViewSort;
    private TextView mTextViewMeasure;
    private TextView mTextViewPrice;
    private TextView mTextViewMore;
    private TextView mTextViewFangXing;

    private TextView mTextViewDetermineSort;
    private TextView mTextViewDetermineMeasure;
    private TextView mTextViewDeterminePrice;
    private TextView mTextViewDetermineMore;
    private TextView mTextViewDetermineFangXin;
    private TextView mTextViewCollect;

    private TextView mTextViewFangXingC;
    private TextView mTextViewJiage;
    private TextView mTextViewMianji;
    private TextView mTextViewPaixu;
    private TextView mTextViewChaoXiang;
    private TextView mTextViewQuYu;


    private TextView mTextViewGuanLi;
    private TextView mTextViewQuXiao;

    private TextView mTextViewQuanBu;
    private TextView mTextViewErShouFang;
    private TextView mTextViewXinFang;

    private TextView mTextViewYiShi;
    private TextView mTextViewLiangShi;
    private TextView mTextViewSanShi;
    private TextView mTextViewSiShi;
    private TextView mTextViewWuShi;


    private ImageView mImageViewYiShi;
    private ImageView mImageViewLiangShi;
    private ImageView mImageViewSanShi;
    private ImageView mImageViewSiShi;
    private ImageView mImageViewWuShi;

    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;
    private TextView mTextView6;
    private TextView mTextView7;
    private TextView mTextView8;
    private TextView mTextView9;

    private TextView mTextViewPrice1;
    private TextView mTextViewPrice2;
    private TextView mTextViewPrice3;
    private TextView mTextViewPrice4;
    private TextView mTextViewPrice5;
    private TextView mTextViewPrice6;
    private TextView mTextViewPrice7;
    private TextView mTextViewPrice8;
    private TextView mTextViewPrice9;

    private TextView mTextViewshunxu;
    private TextView mTextViewzongjiaDG;
    private TextView mTextViewdanjiaDG;
    private TextView mTextViewmianjiDB;
    private TextView mTextViewxiaoqu;
    private TextView mTextViewgongshi;
    private TextView mTextViewfaburen;
    private TextView mTextViewdaoxu;
    private TextView mTextViewzongjiaGD;
    private TextView mTextViewdanjiaGD;
    private TextView mTextViewmianjiBD;

    private EditText mEditTextMax;
    private EditText mEditTextMin;


    private ImageView mImageViewPrice;
    private ImageView mImageViewSort;
    private ImageView mImageViewMeasure;
    private ImageView mImageViewMore;
    private ImageView mImageViewFangxin;
    private ImageView mImageViewQuYu;

    private TextView mTextViewZhuanFa;


    private EditText mEditTextSearch;

    private HouseSearchAdapter mHouseSearchAdapter;
    private HouseSearchDuoXuanAdapter mHouseSearchDuoXuanAdapter;

    private SellDuoXuanAdapter mAdpterDuoXuan;
    private ShaiXuanTabNameAdapter mAdapterTableName;
    private SellAdpter mAdpter;
    private List<String> list = new ArrayList<>();
    private String newOrOld = "";
    private boolean isClickOne = true;
    private boolean isClickTwo = true;
    private boolean isClicksan = true;
    private boolean isClickFour = true;
    private boolean isClickfive = true;
    private boolean isClickQuYu = false;


    private String yishi;
    private String liangshi;
    private String sanshi;
    private String sishi;
    private String wushi;

    private String minAcreage;
    private String maxAcreage;

    private String minPrice;
    private String maxPrice;

    private int productType = 1;


    private String chaoxiang;
    private String fangben;
    private String jiajujiadian;
    private String louling;
    private String yongtu;
    private String zhaungxiu;

    String villageName;


    private Set<String> setHouseTypeList = new HashSet<>();

    private Set<String> setTableNameList = new HashSet<>();


    private StringBuffer stringBuffer = new StringBuffer();

    private List<String> Kouweilist;

    private List<AreaOneScreenEntity> mList;

    private static TIMConversation conversation;
    private String peer;
    private String type;


    private SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(0, 15);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_sell);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("", MODE_BACK);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyler_sell);
        mRecyclerViewDuoXuan = (RecyclerView) findViewById(R.id.recyler_sell_duoxuan);

        mRecyclerViewChaoXaing = (RecyclerView) findViewById(R.id.shaixuan_chaoxuan_recycler);
        mRecyclerViewFangBen = (RecyclerView) findViewById(R.id.shaixuan_fangben_recycler);
        mRecyclerViewZhuangXiu = (RecyclerView) findViewById(R.id.shaixuan_zhuangxiu_recycler);
        mRecyclerViewJiaJuJiaDian = (RecyclerView) findViewById(R.id.shaixuan_jiajujiadian_recycler);
        mRecyclerViewLouLing = (RecyclerView) findViewById(R.id.shaixuan_louling_recycler);
        mRecyclerViewYongTu = (RecyclerView) findViewById(R.id.shaixuan_yongtu_recycler);


        mLinearLayoutFangXing = (LinearLayout) findViewById(R.id.fangxin);
        mLinearLayoutMeasure = (LinearLayout) findViewById(R.id.measure);
        mLinearLayoutMore = (LinearLayout) findViewById(R.id.more);
        mLinearLayoutPrice = (LinearLayout) findViewById(R.id.price);
        mLinearLayoutSort = (LinearLayout) findViewById(R.id.sort);
        mLinearLayoutQuYu = (LinearLayout) findViewById(R.id.quyu);


        mLinearLayoutFangXings = (LinearLayout) findViewById(R.id.house_fangxin);
        mLinearLayoutMeasures = (LinearLayout) findViewById(R.id.house_measure);
        mLinearLayoutMores = (LinearLayout) findViewById(R.id.house_more);
        mLinearLayoutPrices = (LinearLayout) findViewById(R.id.house_price);
        mLinearLayoutSorts = (LinearLayout) findViewById(R.id.house_sort);

        mRelativeLayoutSearch = (RelativeLayout) findViewById(R.id.search_rl);
        mRelativeLayoutDuoxuanBtn = (RelativeLayout) findViewById(R.id.btn_duoxuan);


        mTextViewFangXing = (TextView) findViewById(R.id.tv_fangxin);
        mTextViewSort = (TextView) findViewById(R.id.tv_sort);
        mTextViewMeasure = (TextView) findViewById(R.id.tv_measure);
        mTextViewPrice = (TextView) findViewById(R.id.tv_price);
        mTextViewMore = (TextView) findViewById(R.id.tv_more);
        mTextViewQuYu = (TextView) findViewById(R.id.quyu_tv);


        mTextViewDetermineSort = (TextView) findViewById(R.id.sort_Determine);
        mTextViewDetermineFangXin = (TextView) findViewById(R.id.fangxin_Determine);
        mTextViewDetermineMeasure = (TextView) findViewById(R.id.measure_Determine);
        mTextViewDetermineMore = (TextView) findViewById(R.id.more_Determine);
        mTextViewDeterminePrice = (TextView) findViewById(R.id.price_Determine);

        mTextViewGuanLi = (TextView) findViewById(R.id.toolbar_right);
        mTextViewQuXiao = (TextView) findViewById(R.id.toolbar_quxiao);

        mTextViewCollect = (TextView) findViewById(R.id.collect_btn);

        mTextViewQuanBu = (TextView) findViewById(R.id.quanbu);
        mTextViewErShouFang = (TextView) findViewById(R.id.ershoufang);
        mTextViewXinFang = (TextView) findViewById(R.id.xinfang);

        mRelativeLayoutRL = (LinearLayout) findViewById(R.id.rl_recyler_sell);
        mImageViewFangxin = (ImageView) findViewById(R.id.image_fangxin);
        mImageViewPrice = (ImageView) findViewById(R.id.image_price);
        mImageViewSort = (ImageView) findViewById(R.id.image_sort);
        mImageViewMeasure = (ImageView) findViewById(R.id.image_measure);
        mImageViewMore = (ImageView) findViewById(R.id.image_more);
        mImageViewQuYu = (ImageView) findViewById(R.id.image_quyu);


        mTextViewYiShi = (TextView) findViewById(R.id.yishi);
        mTextViewLiangShi = (TextView) findViewById(R.id.liangshi);
        mTextViewSanShi = (TextView) findViewById(R.id.sanshi);
        mTextViewSiShi = (TextView) findViewById(R.id.sishi);
        mTextViewWuShi = (TextView) findViewById(R.id.wushi);

        mTextViewChaoXiang = (TextView) findViewById(R.id.canel_chaoxiang);
        mTextViewFangXingC = (TextView) findViewById(R.id.canel_fangxing);
        mTextViewJiage = (TextView) findViewById(R.id.canel_price);
        mTextViewMianji = (TextView) findViewById(R.id.canel_mianji);
        mTextViewPaixu = (TextView) findViewById(R.id.canel_paixu);


        mImageViewYiShi = (ImageView) findViewById(R.id.image_yishi);
        mImageViewLiangShi = (ImageView) findViewById(R.id.image_liangshi);
        mImageViewSanShi = (ImageView) findViewById(R.id.image_sanshi);
        mImageViewSiShi = (ImageView) findViewById(R.id.image_shisi);
        mImageViewWuShi = (ImageView) findViewById(R.id.image_wushi);

        mTextView1 = (TextView) findViewById(R.id.tv1);
        mTextView2 = (TextView) findViewById(R.id.tv2);
        mTextView3 = (TextView) findViewById(R.id.tv3);
        mTextView4 = (TextView) findViewById(R.id.tv4);
        mTextView5 = (TextView) findViewById(R.id.tv5);
        mTextView6 = (TextView) findViewById(R.id.tv6);
        mTextView7 = (TextView) findViewById(R.id.tv7);
        mTextView8 = (TextView) findViewById(R.id.tv8);
        mTextView9 = (TextView) findViewById(R.id.tv9);

        mTextViewPrice1 = (TextView) findViewById(R.id.price1);
        mTextViewPrice2 = (TextView) findViewById(R.id.price2);
        mTextViewPrice3 = (TextView) findViewById(R.id.price3);
        mTextViewPrice4 = (TextView) findViewById(R.id.price4);
        mTextViewPrice5 = (TextView) findViewById(R.id.price5);
        mTextViewPrice6 = (TextView) findViewById(R.id.price6);
        mTextViewPrice7 = (TextView) findViewById(R.id.price7);
        mTextViewPrice8 = (TextView) findViewById(R.id.price8);
        mTextViewPrice9 = (TextView) findViewById(R.id.price9);

        mTextViewshunxu = (TextView) findViewById(R.id.shunxu);
        mTextViewzongjiaDG = (TextView) findViewById(R.id.congdidaogao_zongjia);
        mTextViewdanjiaDG = (TextView) findViewById(R.id.congdidaogao_danjia);
        mTextViewmianjiDB = (TextView) findViewById(R.id.mianji_congxiaodaoda);
        mTextViewxiaoqu = (TextView) findViewById(R.id.xiaoqu);
        mTextViewgongshi = (TextView) findViewById(R.id.gongshi);
        mTextViewfaburen = (TextView) findViewById(R.id.faburen);
        mTextViewdaoxu = (TextView) findViewById(R.id.daoxu_time);
        mTextViewzongjiaGD = (TextView) findViewById(R.id.zongjia_conggaodaodi);
        mTextViewdanjiaGD = (TextView) findViewById(R.id.danjia_conggaodaodi);
        mTextViewmianjiBD = (TextView) findViewById(R.id.mianji_congdadaoxiao);

        mEditTextMax = (EditText) findViewById(R.id.ed_max);
        mEditTextMin = (EditText) findViewById(R.id.ed_min);

        mTextViewZhuanFa = findViewById(R.id.delete_collect);

        mEditTextSearch = findViewById(R.id.toolbar_editText_search);

        mImageViewWuShi.setOnClickListener(this);

        mEditTextSearch.setOnEditorActionListener(this);

        mLinearLayoutFangXing.setOnClickListener(this);
        mLinearLayoutMeasure.setOnClickListener(this);
        mLinearLayoutMore.setOnClickListener(this);
        mLinearLayoutPrice.setOnClickListener(this);
        mLinearLayoutSort.setOnClickListener(this);
        mLinearLayoutQuYu.setOnClickListener(this);


        mTextViewDetermineSort.setOnClickListener(this);
        mTextViewDetermineFangXin.setOnClickListener(this);
        mTextViewDetermineMeasure.setOnClickListener(this);
        mTextViewDetermineMore.setOnClickListener(this);
        mTextViewDeterminePrice.setOnClickListener(this);
        mTextViewGuanLi.setOnClickListener(this);
        mTextViewQuXiao.setOnClickListener(this);
//        mRelativeLayoutSearch.setVisibility(View.VISIBLE);


        mTextViewCollect.setOnClickListener(this);

        mTextViewQuanBu.setOnClickListener(this);
        mTextViewXinFang.setOnClickListener(this);
        mTextViewErShouFang.setOnClickListener(this);

        mImageViewYiShi.setOnClickListener(this);
        mImageViewLiangShi.setOnClickListener(this);
        mImageViewSanShi.setOnClickListener(this);
        mImageViewSiShi.setOnClickListener(this);
        mImageViewWuShi.setOnClickListener(this);

        mTextViewChaoXiang.setOnClickListener(this);
        mTextViewPaixu.setOnClickListener(this);
        mTextViewMianji.setOnClickListener(this);
        mTextViewJiage.setOnClickListener(this);
        mTextViewFangXingC.setOnClickListener(this);

        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        mTextView3.setOnClickListener(this);
        mTextView4.setOnClickListener(this);
        mTextView5.setOnClickListener(this);
        mTextView6.setOnClickListener(this);
        mTextView7.setOnClickListener(this);
        mTextView8.setOnClickListener(this);
        mTextView9.setOnClickListener(this);

        mTextViewPrice1.setOnClickListener(this);
        mTextViewPrice2.setOnClickListener(this);
        mTextViewPrice3.setOnClickListener(this);
        mTextViewPrice4.setOnClickListener(this);
        mTextViewPrice5.setOnClickListener(this);
        mTextViewPrice6.setOnClickListener(this);
        mTextViewPrice7.setOnClickListener(this);
        mTextViewPrice8.setOnClickListener(this);
        mTextViewPrice9.setOnClickListener(this);

        mTextViewshunxu.setOnClickListener(this);
        mTextViewzongjiaDG.setOnClickListener(this);
        mTextViewdanjiaDG.setOnClickListener(this);
        mTextViewmianjiDB.setOnClickListener(this);
        mTextViewxiaoqu.setOnClickListener(this);
        mTextViewgongshi.setOnClickListener(this);
        mTextViewdaoxu.setOnClickListener(this);
        mTextViewzongjiaGD.setOnClickListener(this);
        mTextViewdanjiaGD.setOnClickListener(this);
        mTextViewmianjiBD.setOnClickListener(this);
        mTextViewfaburen.setOnClickListener(this);
        mTextViewZhuanFa.setOnClickListener(this);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setEnabled(true);


        getSaleOfScreening("", "", "", "", "", "", "", "", "", "",
                "", "", "", "0", 0, PreferenceUtil.getString(Constanst.CITY_NAME),
                PreferenceUtil.getString(Constanst.DISTRICT_NAME), "1", "");

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
        KyLog.d(isClickQuYu + "");
        if (!isClickQuYu) {
            return;
        }
        String selectName = null;
        String areaOne = PreferenceUtil.getString(Constanst.SCREEN_AREAONE_NAME);
        String areaTwo = PreferenceUtil.getString(Constanst.SCREEN_TWOAONE_NAME);
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.SELECT_PLOT_NAME))) {
            selectName = PreferenceUtil.getString(Constanst.SELECT_PLOT_NAME).
                    substring(1, PreferenceUtil.getString(Constanst.SELECT_PLOT_NAME).length() - 1);
        }
        if (!TextUtils.isEmpty(areaOne) && !TextUtils.isEmpty(areaTwo) && !TextUtils.isEmpty(selectName)) {
            villageName = PreferenceUtil.getString(Constanst.CITY_NAME) + "," + areaOne + ","
                    + areaTwo + "," + selectName;
        } else if (!TextUtils.isEmpty(areaTwo) && !TextUtils.isEmpty(selectName)) {
            villageName = PreferenceUtil.getString(Constanst.CITY_NAME) + "," + "-1" + ","
                    + areaTwo + "," + selectName;
        } else if (!TextUtils.isEmpty(selectName)) {
            villageName = PreferenceUtil.getString(Constanst.CITY_NAME) + "," + "-1" + ","
                    + "-1" + "," + selectName;
        } else {
            villageName = PreferenceUtil.getString(Constanst.CITY_NAME) + "," + "-1" + ","
                    + "-1" + "," + "-1";
        }
        KyLog.d(villageName);
        if (isClickQuYu) {
            getSaleOfScreening(villageName, "", "", "", "", "", "", "", "", "",
                    "", "", "", "0", 0, "",
                    "", "1", "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fangxin:
                mLinearLayoutSorts.setVisibility(View.GONE);
                mLinearLayoutPrices.setVisibility(View.GONE);
                mLinearLayoutMores.setVisibility(View.GONE);
                mLinearLayoutMeasures.setVisibility(View.GONE);
                mLinearLayoutFangXings.setVisibility(View.VISIBLE);
                mTextViewFangXing.setTextColor(getResources().getColor(R.color.blue));
                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMeasure.setTextColor(getResources().getColor(R.color.register_font));
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
                mLinearLayoutMeasures.setVisibility(View.VISIBLE);
                mLinearLayoutFangXings.setVisibility(View.GONE);
                mTextViewFangXing.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMeasure.setTextColor(getResources().getColor(R.color.blue));
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
                mLinearLayoutMeasures.setVisibility(View.GONE);
                mLinearLayoutFangXings.setVisibility(View.GONE);
                mTextViewFangXing.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.blue));
                mTextViewMeasure.setTextColor(getResources().getColor(R.color.register_font));
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
                mLinearLayoutMeasures.setVisibility(View.GONE);
                mLinearLayoutFangXings.setVisibility(View.GONE);
                mTextViewFangXing.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice.setTextColor(getResources().getColor(R.color.blue));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMeasure.setTextColor(getResources().getColor(R.color.register_font));
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
                mLinearLayoutMeasures.setVisibility(View.GONE);
                mLinearLayoutFangXings.setVisibility(View.GONE);
                mTextViewFangXing.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMeasure.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSort.setTextColor(getResources().getColor(R.color.blue));
                mRelativeLayoutRL.setVisibility(View.GONE);
                mImageViewMore.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewPrice.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewSort.setBackgroundResource(R.drawable.icon_triangle_pre);
                mImageViewMeasure.setBackgroundResource(R.drawable.icon_triangle2);
                mImageViewFangxin.setBackgroundResource(R.drawable.icon_triangle2);
                break;

            case R.id.quyu:
                isClickQuYu = true;
                Intent intent = new Intent(this, AreaOneScreenActivity.class);
                startActivity(intent);
                break;

            case R.id.fangxin_Determine:
                KyLog.d(setHouseTypeList.toString());
                stringBuffer.setLength(0);
                for (String str : setHouseTypeList) {
                    stringBuffer.append(str).append(",");
                }
                KyLog.d(stringBuffer.toString());
                updata();
                getSaleOfScreening(PreferenceUtil.getString(Constanst.CITY_NAME) + ",-1,-1,-1", stringBuffer.toString(), "", "", "", "", "", "", "", "",
                        "", "", "", "0", 0, "", "", "1", "");

                break;
            case R.id.measure_Determine:
                updata();
                getSaleOfScreening(PreferenceUtil.getString(Constanst.CITY_NAME) + ",-1,-1,-1", "", minAcreage, maxAcreage,
                        "", "", "", "", "", "",
                        "", "", "", "0", 0, "", "", "1", "");
                break;
            case R.id.more_Determine:
                updata();
                setTabData();
                KyLog.d(chaoxiang);
                KyLog.d(fangben);
                KyLog.d(louling);
                KyLog.d(yongtu);
                KyLog.d(jiajujiadian);
                KyLog.d(zhaungxiu);


                getSaleOfScreening(PreferenceUtil.getString(Constanst.CITY_NAME) + ",-1,-1,-1", "", "", "",
                        "", "", chaoxiang, fangben, zhaungxiu, "",
                        louling, yongtu, "", "0", 0, "", "", "1", jiajujiadian);

                break;

            case R.id.sort_Determine:
                updata();
                getSaleOfScreening(PreferenceUtil.getString(Constanst.CITY_NAME) + ",-1,-1,-1", "", "", "",
                        "", "", "", "", "", "",
                        "", "", "", String.valueOf(productType), 0, "", "", "1", "");
                break;
            case R.id.price_Determine:
                updata();
                getSaleOfScreening(PreferenceUtil.getString(Constanst.CITY_NAME) + ",-1,-1,-1", "", "", "",
                        minPrice, maxPrice, "", "", "", "",
                        "", "", "", "0", 0, "", "", "1", "");
                break;
            case R.id.shunxu:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.blue));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 0;
                break;
            case R.id.congdidaogao_zongjia:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.blue));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 2;
                break;
            case R.id.congdidaogao_danjia:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.blue));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 4;
                break;
            case R.id.mianji_congxiaodaoda:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.blue));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 6;
                break;
            case R.id.xiaoqu:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.blue));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 8;
                break;
            case R.id.gongshi:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.blue));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 9;
                break;
            case R.id.faburen:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.blue));
                productType = 10;
                break;
            case R.id.daoxu_time:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.blue));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 2;
                break;
            case R.id.zongjia_conggaodaodi:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.blue));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 3;

                break;
            case R.id.danjia_conggaodaodi:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.blue));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 5;
                break;
            case R.id.mianji_congdadaoxiao:
                mTextViewshunxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaDG.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiDB.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewxiaoqu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewgongshi.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdaoxu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewzongjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewdanjiaGD.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewmianjiBD.setTextColor(getResources().getColor(R.color.blue));
                mTextViewfaburen.setTextColor(getResources().getColor(R.color.register_font));
                productType = 8;
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

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "";
                maxPrice = "80";
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

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "80";
                maxPrice = "100";
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

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "100";
                maxPrice = "110";
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

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "110";
                maxPrice = "120";
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

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "120";
                maxPrice = "130";
                KyLog.d("price == 5");
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

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "130";
                maxPrice = "140";
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

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "140";
                maxPrice = "160";
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

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.white));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.register_font));
                minPrice = "160";
                maxPrice = "";
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

                mTextViewPrice1.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice2.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice3.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice4.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice5.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice6.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice7.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice8.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewPrice9.setTextColor(getResources().getColor(R.color.white));
                minPrice = "";
                maxPrice = "";
                break;

            case R.id.canel_chaoxiang:
                updata();
                getSaleOfScreening("", "", "", "", "", "", "", "", "", "",
                        "", "", "", "0", 0, PreferenceUtil.getString(Constanst.CITY_NAME),
                        PreferenceUtil.getString(Constanst.DISTRICT_NAME), "1", "");
                break;
            case R.id.canel_fangxing:
                updata();

                getSaleOfScreening("", "", "", "", "", "", "", "", "", "",
                        "", "", "", "0", 0, PreferenceUtil.getString(Constanst.CITY_NAME),
                        PreferenceUtil.getString(Constanst.DISTRICT_NAME), "1", "");
                break;
            case R.id.canel_mianji:
                updata();

                getSaleOfScreening("", "", "", "", "", "", "", "", "", "",
                        "", "", "", "0", 0, PreferenceUtil.getString(Constanst.CITY_NAME),
                        PreferenceUtil.getString(Constanst.DISTRICT_NAME), "1", "");
                break;
            case R.id.canel_paixu:
                updata();

                getSaleOfScreening("", "", "", "", "", "", "", "", "", "",
                        "", "", "", "0", 0, PreferenceUtil.getString(Constanst.CITY_NAME),
                        PreferenceUtil.getString(Constanst.DISTRICT_NAME), "1", "");
                break;
            case R.id.canel_price:
                updata();

                getSaleOfScreening("", "", "", "", "", "", "", "", "", "",
                        "", "", "", "0", 0, PreferenceUtil.getString(Constanst.CITY_NAME),
                        PreferenceUtil.getString(Constanst.DISTRICT_NAME), "1", "");
                break;
            case R.id.tv1:
                mTextView1.setBackgroundResource(R.color.blue);
                mTextView2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView9.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextView1.setTextColor(getResources().getColor(R.color.white));
                mTextView2.setTextColor(getResources().getColor(R.color.register_font));
                mTextView3.setTextColor(getResources().getColor(R.color.register_font));
                mTextView4.setTextColor(getResources().getColor(R.color.register_font));
                mTextView5.setTextColor(getResources().getColor(R.color.register_font));
                mTextView6.setTextColor(getResources().getColor(R.color.register_font));
                mTextView7.setTextColor(getResources().getColor(R.color.register_font));
                mTextView8.setTextColor(getResources().getColor(R.color.register_font));
                mTextView9.setTextColor(getResources().getColor(R.color.register_font));

                minAcreage = "";
                maxAcreage = "50";

                break;
            case R.id.tv2:
                mTextView1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView2.setBackgroundResource(R.color.blue);
                mTextView3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView9.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextView1.setTextColor(getResources().getColor(R.color.register_font));
                mTextView2.setTextColor(getResources().getColor(R.color.white));
                mTextView3.setTextColor(getResources().getColor(R.color.register_font));
                mTextView4.setTextColor(getResources().getColor(R.color.register_font));
                mTextView5.setTextColor(getResources().getColor(R.color.register_font));
                mTextView6.setTextColor(getResources().getColor(R.color.register_font));
                mTextView7.setTextColor(getResources().getColor(R.color.register_font));
                mTextView8.setTextColor(getResources().getColor(R.color.register_font));
                mTextView9.setTextColor(getResources().getColor(R.color.register_font));
                minAcreage = "50";
                maxAcreage = "70";
                break;
            case R.id.tv3:
                mTextView1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView3.setBackgroundResource(R.color.blue);
                mTextView4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView9.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextView1.setTextColor(getResources().getColor(R.color.register_font));
                mTextView2.setTextColor(getResources().getColor(R.color.register_font));
                mTextView3.setTextColor(getResources().getColor(R.color.white));
                mTextView4.setTextColor(getResources().getColor(R.color.register_font));
                mTextView5.setTextColor(getResources().getColor(R.color.register_font));
                mTextView6.setTextColor(getResources().getColor(R.color.register_font));
                mTextView7.setTextColor(getResources().getColor(R.color.register_font));
                mTextView8.setTextColor(getResources().getColor(R.color.register_font));
                mTextView9.setTextColor(getResources().getColor(R.color.register_font));
                minAcreage = "70";
                maxAcreage = "90";
                break;
            case R.id.tv4:
                mTextView1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView4.setBackgroundResource(R.color.blue);
                mTextView5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView9.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextView1.setTextColor(getResources().getColor(R.color.register_font));
                mTextView2.setTextColor(getResources().getColor(R.color.register_font));
                mTextView3.setTextColor(getResources().getColor(R.color.register_font));
                mTextView4.setTextColor(getResources().getColor(R.color.white));
                mTextView5.setTextColor(getResources().getColor(R.color.register_font));
                mTextView6.setTextColor(getResources().getColor(R.color.register_font));
                mTextView7.setTextColor(getResources().getColor(R.color.register_font));
                mTextView8.setTextColor(getResources().getColor(R.color.register_font));
                mTextView9.setTextColor(getResources().getColor(R.color.register_font));
                minAcreage = "90";
                maxAcreage = "110";
                break;
            case R.id.tv5:
                mTextView1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView5.setBackgroundResource(R.color.blue);
                mTextView6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView9.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView1.setTextColor(getResources().getColor(R.color.register_font));
                mTextView2.setTextColor(getResources().getColor(R.color.register_font));
                mTextView3.setTextColor(getResources().getColor(R.color.register_font));
                mTextView4.setTextColor(getResources().getColor(R.color.register_font));
                mTextView5.setTextColor(getResources().getColor(R.color.white));
                mTextView6.setTextColor(getResources().getColor(R.color.register_font));
                mTextView7.setTextColor(getResources().getColor(R.color.register_font));
                mTextView8.setTextColor(getResources().getColor(R.color.register_font));
                mTextView9.setTextColor(getResources().getColor(R.color.register_font));
                minAcreage = "110";
                maxAcreage = "130";
                break;
            case R.id.tv6:
                mTextView1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView6.setBackgroundResource(R.color.blue);
                mTextView7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView9.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextView1.setTextColor(getResources().getColor(R.color.register_font));
                mTextView2.setTextColor(getResources().getColor(R.color.register_font));
                mTextView3.setTextColor(getResources().getColor(R.color.register_font));
                mTextView4.setTextColor(getResources().getColor(R.color.register_font));
                mTextView5.setTextColor(getResources().getColor(R.color.register_font));
                mTextView6.setTextColor(getResources().getColor(R.color.white));
                mTextView7.setTextColor(getResources().getColor(R.color.register_font));
                mTextView8.setTextColor(getResources().getColor(R.color.register_font));
                mTextView9.setTextColor(getResources().getColor(R.color.register_font));
                minAcreage = "130";
                maxAcreage = "150";
                break;
            case R.id.tv7:
                mTextView1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView7.setBackgroundResource(R.color.blue);
                mTextView8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView9.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextView1.setTextColor(getResources().getColor(R.color.register_font));
                mTextView2.setTextColor(getResources().getColor(R.color.register_font));
                mTextView3.setTextColor(getResources().getColor(R.color.register_font));
                mTextView4.setTextColor(getResources().getColor(R.color.register_font));
                mTextView5.setTextColor(getResources().getColor(R.color.register_font));
                mTextView6.setTextColor(getResources().getColor(R.color.register_font));
                mTextView7.setTextColor(getResources().getColor(R.color.white));
                mTextView8.setTextColor(getResources().getColor(R.color.register_font));
                mTextView9.setTextColor(getResources().getColor(R.color.register_font));
                minAcreage = "150";
                maxAcreage = "200";
                break;
            case R.id.tv8:
                mTextView1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView8.setBackgroundResource(R.color.blue);
                mTextView9.setBackgroundResource(R.color.login_forget_password_code_fort);

                mTextView1.setTextColor(getResources().getColor(R.color.register_font));
                mTextView2.setTextColor(getResources().getColor(R.color.register_font));
                mTextView3.setTextColor(getResources().getColor(R.color.register_font));
                mTextView4.setTextColor(getResources().getColor(R.color.register_font));
                mTextView5.setTextColor(getResources().getColor(R.color.register_font));
                mTextView6.setTextColor(getResources().getColor(R.color.register_font));
                mTextView7.setTextColor(getResources().getColor(R.color.register_font));
                mTextView8.setTextColor(getResources().getColor(R.color.white));
                mTextView9.setTextColor(getResources().getColor(R.color.register_font));
                minAcreage = "200";
                maxAcreage = "";
                break;
            case R.id.tv9:
                mTextView1.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView2.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView3.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView4.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView5.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView6.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView7.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView8.setBackgroundResource(R.color.login_forget_password_code_fort);
                mTextView9.setBackgroundResource(R.color.blue);

                mTextView1.setTextColor(getResources().getColor(R.color.register_font));
                mTextView2.setTextColor(getResources().getColor(R.color.register_font));
                mTextView3.setTextColor(getResources().getColor(R.color.register_font));
                mTextView4.setTextColor(getResources().getColor(R.color.register_font));
                mTextView5.setTextColor(getResources().getColor(R.color.register_font));
                mTextView6.setTextColor(getResources().getColor(R.color.register_font));
                mTextView7.setTextColor(getResources().getColor(R.color.register_font));
                mTextView8.setTextColor(getResources().getColor(R.color.register_font));
                mTextView9.setTextColor(getResources().getColor(R.color.white));
                minAcreage = "";
                maxAcreage = "";
                break;

            case R.id.image_yishi:
                if (isClickOne) {
                    mImageViewYiShi.setBackgroundResource(R.drawable.icon_circle_selected);
                    mTextViewYiShi.setTextColor(getResources().getColor(R.color.blue));
                    isClickOne = false;
                    yishi = "一室";
                    setHouseTypeList.add(yishi);
                    KyLog.d(setHouseTypeList.toString());

                } else {
                    mImageViewYiShi.setBackgroundResource(R.drawable.icon_circle_normal);
                    mTextViewYiShi.setTextColor(getResources().getColor(R.color.register_font));

                    isClickOne = true;
//                    yishi = null;
                    setHouseTypeList.remove(yishi);
                    KyLog.d(setHouseTypeList.toString());

                }
                break;
            case R.id.image_liangshi:
                if (isClickTwo) {
                    mImageViewLiangShi.setBackgroundResource(R.drawable.icon_circle_selected);
                    mTextViewLiangShi.setTextColor(getResources().getColor(R.color.blue));

                    isClickTwo = false;
                    liangshi = "两室";
                    setHouseTypeList.add(liangshi);
                    KyLog.d(setHouseTypeList.toString());

                } else {
                    mImageViewLiangShi.setBackgroundResource(R.drawable.icon_circle_normal);
                    mTextViewLiangShi.setTextColor(getResources().getColor(R.color.register_font));

                    isClickTwo = true;
//                    liangshi = null;
                    setHouseTypeList.remove(liangshi);
                    KyLog.d(setHouseTypeList.toString());

                }
                break;
            case R.id.image_sanshi:
                if (isClicksan) {
                    mImageViewSanShi.setBackgroundResource(R.drawable.icon_circle_selected);
                    mTextViewSanShi.setTextColor(getResources().getColor(R.color.blue));

                    isClicksan = false;
                    sanshi = "三室";
                    setHouseTypeList.add(sanshi);
                    KyLog.d(setHouseTypeList.toString());

                } else {
                    mImageViewSanShi.setBackgroundResource(R.drawable.icon_circle_normal);
                    mTextViewSanShi.setTextColor(getResources().getColor(R.color.blue));

                    isClicksan = true;
                    setHouseTypeList.remove(sanshi);
                    KyLog.d(setHouseTypeList.toString());

                }
                break;
            case R.id.image_shisi:
                if (isClickFour) {
                    mImageViewSiShi.setBackgroundResource(R.drawable.icon_circle_selected);
                    mTextViewSiShi.setTextColor(getResources().getColor(R.color.blue));

                    isClickFour = false;
                    sishi = "四室";
                    setHouseTypeList.add(sishi);
                    KyLog.d(setHouseTypeList.toString());

                } else {
                    mImageViewSiShi.setBackgroundResource(R.drawable.icon_circle_normal);
                    mTextViewSiShi.setTextColor(getResources().getColor(R.color.blue));

                    isClickFour = true;
                    setHouseTypeList.remove(sishi);
                    KyLog.d(setHouseTypeList.toString());

                }
                break;
            case R.id.image_wushi:
                if (isClickfive) {
                    mImageViewWuShi.setBackgroundResource(R.drawable.icon_circle_selected);
                    mTextViewWuShi.setTextColor(getResources().getColor(R.color.blue));

                    isClickfive = false;
                    wushi = "五室及五室以上";
                    setHouseTypeList.add(wushi);
                    KyLog.d(setHouseTypeList.toString());
                } else {
                    mImageViewWuShi.setBackgroundResource(R.drawable.icon_circle_normal);
                    mTextViewWuShi.setTextColor(getResources().getColor(R.color.register_font));

                    isClickfive = true;
                    setHouseTypeList.remove(wushi);
                    KyLog.d(setHouseTypeList.toString());

                }
                break;

            case R.id.quanbu:
                mTextViewQuanBu.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewQuanBu.setTextColor(getResources().getColor(R.color.white));
                mTextViewErShouFang.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewErShouFang.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewXinFang.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewXinFang.setTextColor(getResources().getColor(R.color.register_font));
                newOrOld = null;

                if (setHouseTypeList.size() > 0) {
                    getSaleOfScreening(PreferenceUtil.getString(Constanst.CITY_NAME) + ",-1,-1,-1", stringBuffer.toString(), "", "",
                            "", "", "", "", "", "",
                            "", "", "", "0", 0, "", "", "1", "");
                } else {
                    getSaleOfScreening("", "", "", "", "", "", "", "", "", "",
                            "", "", "", "0", 0, PreferenceUtil.getString(Constanst.CITY_NAME), PreferenceUtil.getString(Constanst.DISTRICT_NAME), "1", "");
                }
                break;
            case R.id.ershoufang:
                mTextViewQuanBu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQuanBu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewErShouFang.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewErShouFang.setTextColor(getResources().getColor(R.color.white));
                mTextViewXinFang.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewXinFang.setTextColor(getResources().getColor(R.color.register_font));
                getSaleOfScreening("", "", "", "", "", "", "", "", "", "",
                        "", "", "", "0", 2, PreferenceUtil.getString(Constanst.CITY_NAME), PreferenceUtil.getString(Constanst.DISTRICT_NAME), "1", "");

                newOrOld = "2";
                break;
            case R.id.xinfang:
                mTextViewQuanBu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQuanBu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewErShouFang.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewErShouFang.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewXinFang.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewXinFang.setTextColor(getResources().getColor(R.color.white));
                getSaleOfScreening("", "", "", "", "",
                        "", "", "", "", "",
                        "", "", "", "0", 1, PreferenceUtil.getString(Constanst.CITY_NAME)
                        , PreferenceUtil.getString(Constanst.DISTRICT_NAME), "1", "");

                newOrOld = "1";
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
                mRelativeLayoutDuoxuanBtn.setVisibility(View.GONE);
                setEnabled(true);

                break;
            case R.id.collect_btn:
                addCollectTravel();
                break;
            case R.id.delete_collect:
//                addCollectTravel(productType);
                if (mAdpterDuoXuan.getSelectedItem().size() > 0) {
                    zhuanfa(mAdpterDuoXuan);
                } else {
                    Toast.makeText(this, "请选择需要转发的数据", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void setTabData() {
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CHAO_XIANG))) {
            chaoxiang = PreferenceUtil.getString(Constanst.CHAO_XIANG)
                    .substring(1, PreferenceUtil.getString(Constanst.CHAO_XIANG).length() - 1);
        } else {
            chaoxiang = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.FANG_BEN))) {
            fangben = PreferenceUtil.getString(Constanst.FANG_BEN)
                    .substring(1, PreferenceUtil.getString(Constanst.FANG_BEN).length() - 1);
        } else {
            fangben = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.JIA_JU_JIA_DIAN))) {
            jiajujiadian = PreferenceUtil.getString(Constanst.JIA_JU_JIA_DIAN)
                    .substring(1, PreferenceUtil.getString(Constanst.JIA_JU_JIA_DIAN).length() - 1);
        } else {
            jiajujiadian = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.LOU_LING))) {
            louling = PreferenceUtil.getString(Constanst.LOU_LING)
                    .substring(1, PreferenceUtil.getString(Constanst.LOU_LING).length() - 1);
        } else {
            louling = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.YONG_TU))) {
            yongtu = PreferenceUtil.getString(Constanst.YONG_TU)
                    .substring(1, PreferenceUtil.getString(Constanst.YONG_TU).length() - 1);
        } else {
            yongtu = "";
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.ZHUANG_XIU))) {
            zhaungxiu = PreferenceUtil.getString(Constanst.ZHUANG_XIU)
                    .substring(1, PreferenceUtil.getString(Constanst.ZHUANG_XIU).length() - 1);
        } else {
            zhaungxiu = "";
        }
    }

    private void setDetleTabData() {
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CHAO_XIANG))) {
            PreferenceUtil.removeSp(Constanst.CHAO_XIANG, Constanst.SP_NAME);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.FANG_BEN))) {
            PreferenceUtil.removeSp(Constanst.FANG_BEN, Constanst.SP_NAME);

        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.JIA_JU_JIA_DIAN))) {
            PreferenceUtil.removeSp(Constanst.JIA_JU_JIA_DIAN, Constanst.SP_NAME);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.LOU_LING))) {
            PreferenceUtil.removeSp(Constanst.LOU_LING, Constanst.SP_NAME);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.YONG_TU))) {
            PreferenceUtil.removeSp(Constanst.YONG_TU, Constanst.SP_NAME);
        }

        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.ZHUANG_XIU))) {
            PreferenceUtil.removeSp(Constanst.ZHUANG_XIU, Constanst.SP_NAME);
        }
    }

    private void setMoreData() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setOccupation(), this, 3);
        mRecyclerViewJiaJuJiaDian.setAdapter(mAdapterTableName);
        mRecyclerViewJiaJuJiaDian.setLayoutManager(manager);
//        mRecyclerViewJiaJuJiaDian.addItemDecoration(new SpaceItemDecoration(0, 25));


        GridLayoutManager managerChaoXiang = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setChaoXiang(), this, 1);
        mRecyclerViewChaoXaing.setAdapter(mAdapterTableName);
        mRecyclerViewChaoXaing.setLayoutManager(managerChaoXiang);
//        mRecyclerViewChaoXaing.addItemDecoration(new SpaceItemDecoration(0, 25));

        GridLayoutManager managerFangBen = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setFangBen(), this, 2);
        mRecyclerViewFangBen.setAdapter(mAdapterTableName);
        mRecyclerViewFangBen.setLayoutManager(managerFangBen);
//        mRecyclerViewFangBen.addItemDecoration(new SpaceItemDecoration(0, 25));

        GridLayoutManager managerZhuangXiu = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setZhuangXiu(), this, 5);
        mRecyclerViewZhuangXiu.setAdapter(mAdapterTableName);
        mRecyclerViewZhuangXiu.setLayoutManager(managerZhuangXiu);
//        mRecyclerViewZhuangXiu.addItemDecoration(new SpaceItemDecoration(0, 25));

        GridLayoutManager managerLouLing = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setLouLing(), this, 4);
        mRecyclerViewLouLing.setAdapter(mAdapterTableName);
        mRecyclerViewLouLing.setLayoutManager(managerLouLing);
//        mRecyclerViewLouLing.addItemDecoration(new SpaceItemDecoration(0, 25));

        GridLayoutManager managerYongTu = new GridLayoutManager(this, 4);
        mAdapterTableName = new ShaiXuanTabNameAdapter(setYongTu(), this, 6);
        mRecyclerViewYongTu.setAdapter(mAdapterTableName);
        mRecyclerViewYongTu.setLayoutManager(managerYongTu);
//        mRecyclerViewYongTu.addItemDecoration(new SpaceItemDecoration(0, 25));

    }

    public void updata() {
        mRelativeLayoutRL.setVisibility(View.VISIBLE);
        mRecyclerViewDuoXuan.setVisibility(View.GONE);
        mLinearLayoutSorts.setVisibility(View.GONE);
        mLinearLayoutPrices.setVisibility(View.GONE);
        mLinearLayoutMores.setVisibility(View.GONE);
        mLinearLayoutMeasures.setVisibility(View.GONE);
        mLinearLayoutFangXings.setVisibility(View.GONE);
        mTextViewFangXing.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewMeasure.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
    }

    private void setDuoXuanData(SaleOfScreeningEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpterDuoXuan = new SellDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mAdpterDuoXuan);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
//            mRecyclerViewDuoXuan.addItemDecoration(spaceItemDecoration);
            mTextViewGuanLi.setVisibility(View.VISIBLE);
            mRelativeLayoutSearch.setVisibility(View.VISIBLE);
        }

    }

    private void setData(SaleOfScreeningEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpter = new SellAdpter(entity.getList(), this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
//            mRecyclerView.addItemDecoration(spaceItemDecoration);
        } else {
            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
        }

    }

    private void setSearchDuoXuanData(List<SelectFrameEntity> list) {
        if (list != null && list.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mHouseSearchDuoXuanAdapter = new HouseSearchDuoXuanAdapter(list, this);
            mRecyclerViewDuoXuan.setAdapter(mHouseSearchDuoXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
//            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
            mRelativeLayoutSearch.setVisibility(View.VISIBLE);
        }

    }

    private void setSearchData(List<SelectFrameEntity> list) {
        if (list != null && list.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mHouseSearchAdapter = new HouseSearchAdapter(list, this);
            mRecyclerView.setAdapter(mHouseSearchAdapter);
            mRecyclerView.setLayoutManager(manager);
//            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        } else {
            mRecyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
        }
    }


    private void getSaleOfScreening(String villageName,
                                    String houseType, String minAcreage, String maxAcreage, String minPrice,
                                    String maxPrice, String orientation, String permit, String fitment, String element, String floorAge, String purpose, String ownership, String productType,
                                    int newOrOld, String city, String areaOne, String curPage, String houseHoldAppliancesint) {
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.DISTRICT_NAME));

        showProgressDialog();
        ApiModule.getInstance().SaleOfScreening(villageName, houseType, minAcreage, maxAcreage, minPrice, maxPrice, orientation,
                permit, fitment, element, floorAge, purpose, ownership, productType,
                String.valueOf(newOrOld), city, areaOne, curPage, houseHoldAppliancesint)
                .subscribe(saleOfScreeningEntities -> {
                    if (saleOfScreeningEntities != null) {
                        KyLog.object(saleOfScreeningEntities + "");
                        setData(saleOfScreeningEntities);
                        setDuoXuanData(saleOfScreeningEntities);
                    }

                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void addCollectTravel() {
        KyLog.d(PreferenceUtil.getString(Constanst.PID_COLLECT));
        showProgressDialog();
        ApiModule.getInstance().addCollectTravel(PreferenceUtil.getString(Constanst.PID_COLLECT), 1)
                .subscribe(response -> {
                    KyLog.object(response + "");
                    cancelProgressDialog();
                    Intent intent = new Intent(this, SellActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * 搜索
     *
     * @param productType
     * @param newOrOld
     * @param condition
     * @param stick
     * @param collectType
     */
    private void selectFrame(String productType, String newOrOld,
                             String condition, String stick,
                             String collectType, String uid) {

        showProgressDialog();
        ApiModule.getInstance().selectFrame(productType, newOrOld, condition, stick, collectType, uid,"1")
                .subscribe(saleOfScreeningEntities -> {
                    if (saleOfScreeningEntities != null) {
                        KyLog.object(saleOfScreeningEntities + "");
                        setData(saleOfScreeningEntities);
                        setDuoXuanData(saleOfScreeningEntities);
                    }

                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    private List<String> setOccupation() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("家具家电齐全");
        Kouweilist.add("无家具无家电");
        Kouweilist.add("有家具无家电");
        Kouweilist.add("无家具有家电");

        return Kouweilist;
    }

    private List<String> setLouLing() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("5年内");
        Kouweilist.add("10年内");
        Kouweilist.add("15年内");
        Kouweilist.add("15年以上");

        return Kouweilist;
    }

    private List<String> setZhuangXiu() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("毛坯");
        Kouweilist.add("简装");
        Kouweilist.add("精装");
        Kouweilist.add("豪装");
        return Kouweilist;
    }

    private List<String> setFangBen() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("唯一");
        Kouweilist.add("不满两年");
        Kouweilist.add("满两年");
        Kouweilist.add("满五年");
        return Kouweilist;
    }


    private List<String> setChaoXiang() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("南北");
        Kouweilist.add("朝南");
        Kouweilist.add("西南");
        Kouweilist.add("东南");
        Kouweilist.add("朝东");
        Kouweilist.add("朝西");
        Kouweilist.add("朝北");
        Kouweilist.add("东西");


        return Kouweilist;
    }

    private List<String> setYongTu() {
        Kouweilist = new ArrayList<String>();
        Kouweilist.add("普通住宅");
        Kouweilist.add("商铺");
        Kouweilist.add("别墅");
        Kouweilist.add("独院");

        return Kouweilist;
    }

    private void zhuanfa(SellDuoXuanAdapter adapter) {
        String data = getData(adapter.getSelectedItem(), 1);
        KyLog.i("zhuanfa data = " + data);
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        bundle.putString("from", "zhuanfa");
        Intent intent = new Intent(SellActivity.this, TuiJianActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public static String getData(ArrayList<SaleOfScreeningEntity.ListBean> Salelist, int houseType) {
        String str = "";
        try {
            JSONObject jsonObject1 = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            if (Salelist != null && Salelist.size() > 0) {
                for (SaleOfScreeningEntity.ListBean SaleEntity : Salelist) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("houseType", SaleEntity.getHouseType());
                    jsonObject.put("exclusive", String.valueOf(SaleEntity.getExclusive()));
                    jsonObject.put("id", String.valueOf(SaleEntity.getId()));
                    jsonObject.put("keying", String.valueOf(SaleEntity.getKeying()));
                    jsonObject.put("orientation", SaleEntity.getOrientation());
                    jsonObject.put("stick", String.valueOf(SaleEntity.getStick()));
                    jsonObject.put("tabName", SaleEntity.getTabName());
                    jsonObject.put("totalPrice", String.valueOf(SaleEntity.getTotalPrice()));
                    jsonObject.put("title", SaleEntity.getTitle());
                    jsonObject.put("unitPrice", String.valueOf(SaleEntity.getUnitPrice()));
                    jsonObject.put("villageName", SaleEntity.getVillageName());
                    jsonObject.put("acreage", "");
                    jsonObject.put("tabId", "");
                    jsonArray.put(jsonObject);
                }
                jsonObject1.put("type", "1");
                jsonObject1.put("houseType", String.valueOf(houseType));
                jsonObject1.put("data", jsonArray);
            }
            str = jsonObject1.toString();
            KyLog.i("getData str = " + str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * List转换String
     *
     * @param list :需要转换的List
     * @return String转换后的字符串
     */
    public static String ListToString(List<HouseEntity> list) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        StringBuffer sblist = new StringBuffer();

        if (list != null && list.size() > 0) {
            for (HouseEntity entity : list) {
                for (HouseEntity.ListBean listBean : entity.getList()) {
                    sblist.append("{").append(listBean).append("}");
                }
                sb.append(entity).append("[").append(sblist).append("]");
            }
            stringBuffer.append("{").append(sb).append("}");
        }
        return "L" + sb.toString();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        switch (i) {
            case EditorInfo.IME_ACTION_GO:
                String search = mEditTextSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(search)) {
                    selectFrame("1", newOrOld, search, null, null, null);
                } else {
                    Toast.makeText(SellActivity.this, "请填写手机号", Toast.LENGTH_SHORT).show();
                }
                KyLog.d("Done_content: " + search);
                break;

        }
        return true;
    }

    private void setEnabled(boolean isFocusable) {
        mLinearLayoutFangXing.setClickable(isFocusable);
        mLinearLayoutMore.setClickable(isFocusable);
        mLinearLayoutPrice.setClickable(isFocusable);
        mLinearLayoutSort.setClickable(isFocusable);
        mLinearLayoutMeasure.setClickable(isFocusable);
        mRecyclerViewDuoXuan.setClickable(isFocusable);
        mLinearLayoutQuYu.setClickable(isFocusable);


        mLinearLayoutFangXing.setFocusable(isFocusable);
        mLinearLayoutMore.setFocusable(isFocusable);
        mLinearLayoutPrice.setFocusable(isFocusable);
        mLinearLayoutSort.setFocusable(isFocusable);
        mLinearLayoutMeasure.setFocusable(isFocusable);
        mRecyclerViewDuoXuan.setFocusable(isFocusable);
        mLinearLayoutQuYu.setFocusable(isFocusable);


    }
}
