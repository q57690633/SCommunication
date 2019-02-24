package com.huxin.communication.ui.my.collect;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.CollectTicketAdapter;
import com.huxin.communication.adpter.CollectTravelAdapter;
import com.huxin.communication.adpter.CollectTravelDuoXuanAdapter;
import com.huxin.communication.adpter.CollectTravelJingWaiAdapter;
import com.huxin.communication.adpter.JingWaiAdapter;
import com.huxin.communication.adpter.TicketingAdapter;
import com.huxin.communication.adpter.TicketingDuoXuanAdapter;
import com.huxin.communication.adpter.TopSelectionTravelAdapter;
import com.huxin.communication.adpter.ZhouBianAdapter;
import com.huxin.communication.adpter.ZhouBianDuoXuanAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.CollectAroundEntity;
import com.huxin.communication.entity.CollectForeignEntity;
import com.huxin.communication.entity.CollectTicketEntity;
import com.huxin.communication.entity.ForeignTravelEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.http.ApiModule;
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
    private JingWaiAdapter mJingWaiAdapter;
    private TicketingDuoXuanAdapter mTicketingDuoXuanAdapter;

    private TextView mTextViewDuanTuYou;
    private TextView mTextViewGuoNeiYou;
    private TextView mTextViewGuoWaiYou;
    private TextView mTextViewPiaoWu;
    private List<String> list = new ArrayList<>();

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

        mRelativeLayoutSearch.setVisibility(View.VISIBLE);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        gettingAroundTravel();
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
                gettingAroundTravel();
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
                gettingAroundTravel();
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
                gettingForeignTravel();
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
                getTicketInfo();
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

    private void setJinWaiData(ForeignTravelEntity entity){
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mJingWaiAdapter = new JingWaiAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mJingWaiAdapter);
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
    private void gettingAroundTravel() {
        showProgressDialog();
        ApiModule.getInstance().gettingAroundTravel(null, null, null, null
                , null, null, null, null, null,
                null, null, null, String.valueOf(PreferenceUtil.getInt(UID)), "1", null, "","","")
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

    /**
     * 境外
     */
    private void gettingForeignTravel() {
        showProgressDialog();
        ApiModule.getInstance().gettingForeignTravel("", "", "", ""
                , "", "", "", "", "",
                "", "", String.valueOf(PreferenceUtil.getInt(UID)), "", "", "", "", "", "1","")
                .subscribe(foreignTravelEntity -> {
                    cancelProgressDialog();
                    KyLog.object(foreignTravelEntity);
                        setJinWaiData(foreignTravelEntity);

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    /**
     * 票务
     */
    private void getTicketInfo() {
        showProgressDialog();
        ApiModule.getInstance().getTicketInfo("1", PreferenceUtil.getString(Constanst.CITY_NATION_NAME), "", ""
                , "", "", "", String.valueOf(PreferenceUtil.getInt(UID)), "1", "")
                .subscribe(ticketInfoEntity -> {
                    cancelProgressDialog();
                        KyLog.object(ticketInfoEntity);
                        setTicketData(ticketInfoEntity);
                        setTicketDuoXuanData(ticketInfoEntity);

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


}
