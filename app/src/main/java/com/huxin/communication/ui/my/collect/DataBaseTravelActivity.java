package com.huxin.communication.ui.my.collect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.JinWaiDuoXuanAdapter;
import com.huxin.communication.adpter.JingWaiAdapter;
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
import com.huxin.communication.ui.house.sell.SellActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class DataBaseTravelActivity extends BaseActivity implements View.OnClickListener {

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

    private int productType = 1;

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

        mRelativeLayoutSearch.setVisibility(View.VISIBLE);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
       gettingAroundTravel("", "", "", ""
                , "", "", "", "", "",
                "", "", "",
                "1", "", "", String.valueOf(1), "");
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

            case R.id.more_Determine:
                updata();
                break;
            case R.id.price_Determine:
                updata();
                break;
            case R.id.sort_Determine:
                updata();
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
                productType = 1;
                gettingAroundTravel("", "", "", ""
                        , "", "", "", "", "",
                        "", "", "",
                        "1", "", "", String.valueOf(1), "");
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
                productType = 1;

                gettingAroundTravel("", "", "", ""
                        , "", "", "", "", "",
                        "", "", "",
                        "1", "", "", String.valueOf(2), "");
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
                productType = 2;

                gettingForeignTravel("", "", "", "", "", "", "", "",
                        "", "", "", "", "", "", "", "",
                        "1", "");
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
                productType = 3;

                getTicketInfo("1", "", "", "",
                        "", "", "", "", "1");
                break;
            case R.id.toolbar_right:
                mTextViewQuXiao.setVisibility(View.VISIBLE);
                mTextViewGuanLi.setVisibility(View.GONE);
                mRecyclerViewDuoXuan.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mRelativeLayoutRL.setVisibility(View.VISIBLE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.toolbar_quxiao:
                mTextViewQuXiao.setVisibility(View.GONE);
                mTextViewGuanLi.setVisibility(View.VISIBLE);
                mRecyclerViewDuoXuan.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mRelativeLayoutDuoxuanBtn.setVisibility(View.GONE);
                break;
            case R.id.collect_btn:
                addTravelCollect(productType);
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

    private void setDuoXuanData(AroundTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mZhouBianDuoXuanAdapter = new ZhouBianDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mZhouBianDuoXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setData(AroundTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);

            mAdpter = new ZhouBianAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }
    }

    private void setJinWaiDuoXuanData(ForeignTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mJinWaiDuoXuanAdapter = new JinWaiDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mJinWaiDuoXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setJinWaiData(ForeignTravelEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {

            LinearLayoutManager manager = new LinearLayoutManager(this);
            mJinWaiAdpter = new JingWaiAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }
    }

    private void setTicketDuoXuanData(TicketInfoEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mTicketingDuoXuanAdapter = new TicketingDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mTicketingDuoXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setTicketData(TicketInfoEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mTicketAdapter = new TicketingAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mTicketAdapter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }

    }

    /**
     * 国内和周边
     */
    private void gettingAroundTravel(String depart_code, String goalsId,
                                     String sort_type, String tOtherId,
                                     String tActivityId, String tStayId,
                                     String tAddressId, String tTrafficId,
                                     String tConsumeId, String minPri_maxPri,
                                     String numberDays, String keyWord,
                                     String curPage, String minDay, String maxDay,
                                     String travel_kind, String lineOrThrows) {
        showProgressDialog();
        ApiModule.getInstance().gettingAroundTravel(depart_code, goalsId,
                sort_type, tOtherId, tActivityId, tStayId, tAddressId, tTrafficId, tConsumeId, minPri_maxPri,
                numberDays, keyWord, curPage, minDay, maxDay, String.valueOf(PreferenceUtil.getInt(UID)), travel_kind, lineOrThrows)
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
                t_traffic_id, t_overseas_id, t_consume_id, sort_type, minPri_maxPri, number_days, keyWord, curPage, String.valueOf(PreferenceUtil.getInt(UID)), line_or_throw)
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
                keyWord, curPage, String.valueOf(PreferenceUtil.getInt(UID)))
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
                    Intent intent =  new Intent(this,SellActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


}
