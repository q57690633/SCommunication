package com.huxin.communication.ui.travel;

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
import com.huxin.communication.adpter.CollectTicketAdapter;
import com.huxin.communication.adpter.CollectTravelAdapter;
import com.huxin.communication.adpter.CollectTravelDuoXuanAdapter;
import com.huxin.communication.adpter.CollectTravelJingWaiAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.CollectAroundEntity;
import com.huxin.communication.entity.CollectForeignEntity;
import com.huxin.communication.entity.CollectTicketEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class CollectTravelActivity extends BaseActivity implements View.OnClickListener {
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

    private CollectTravelAdapter mAdpter;
    private CollectTravelJingWaiAdapter mWaiAdapter;
    private CollectTicketAdapter mTicketAdapter;

    private CollectTravelDuoXuanAdapter collectDuoXuanAdapter;
    private CollectTravelJingWaiAdapter mCollectTravelJingWaiAdapter;
    private CollectTicketAdapter mCollectTicketAdapter;

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
        setContentView(R.layout.activity_collect_travel);
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
        getCollectAround();
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
                getCollectAround();
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
                getCollectAround();
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
                getCollectForeign();
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
                getCollectTicket();
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

    private void setDuoXuanData(CollectAroundEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            collectDuoXuanAdapter = new CollectTravelDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(collectDuoXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setJingWaiDuoXuanData(CollectForeignEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mCollectTravelJingWaiAdapter = new CollectTravelJingWaiAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mCollectTravelJingWaiAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setTicketDuoXuanData(CollectTicketEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mCollectTicketAdapter = new CollectTicketAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mCollectTicketAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }


    private void setData(CollectAroundEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpter = new CollectTravelAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }
    }

    private void setJingWaiData(CollectForeignEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mWaiAdapter = new CollectTravelJingWaiAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mWaiAdapter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }
    }

    private void setTicketData(CollectTicketEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mTicketAdapter = new CollectTicketAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mTicketAdapter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }

    }

    /**
     * 国内和周边
     */
    private void getCollectAround() {
        showProgressDialog();
        ApiModule.getInstance().getCollectAround("", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "")
                .subscribe(collectAroundEntity -> {
                    cancelProgressDialog();
                    KyLog.object(collectAroundEntity);
                    setData(collectAroundEntity);
                    setDuoXuanData(collectAroundEntity);

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * 境外
     */
    private void getCollectForeign() {
        showProgressDialog();
        ApiModule.getInstance().getCollectForeign("", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "")
                .subscribe(collectForeignEntity -> {
                    cancelProgressDialog();
                    KyLog.object(collectForeignEntity);
                    setJingWaiData(collectForeignEntity);
                    setJingWaiDuoXuanData(collectForeignEntity);

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * 票务
     */
    private void getCollectTicket() {
        showProgressDialog();
        ApiModule.getInstance().getCollectTicket("", "", "", "",
                "", "", "", "", "")
                .subscribe(collectTicketEntity -> {
                    cancelProgressDialog();
                    KyLog.object(collectTicketEntity);
                    setTicketData(collectTicketEntity);
                    setTicketDuoXuanData(collectTicketEntity);

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


}
