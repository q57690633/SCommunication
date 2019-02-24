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
import com.huxin.communication.adpter.TicketingAdapter;
import com.huxin.communication.adpter.TicketingDuoXuanAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

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

        mLinearLayoutMore.setOnClickListener(this);
        mLinearLayoutPrice.setOnClickListener(this);
        mLinearLayoutSort.setOnClickListener(this);
        mTextViewDetermineSort.setOnClickListener(this);
        mTextViewDetermineMore.setOnClickListener(this);
        mTextViewDeterminePrice.setOnClickListener(this);
        mTextViewGuanLi.setOnClickListener(this);
        mTextViewQuXiao.setOnClickListener(this);
        mRelativeLayoutSearch.setVisibility(View.VISIBLE);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getTicketInfo();
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

            case R.id.more_Determine:
                updata();
                break;
            case R.id.price_Determine:
                updata();
                break;
            case R.id.sort_Determine:
                updata();
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


    private void setDuoXuanData(TicketInfoEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mXuanAdapter = new TicketingDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mXuanAdapter);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
        }
    }

    private void setData(TicketInfoEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpter = new TicketingAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 30));
        }

    }


    private void getTicketInfo() {
        showProgressDialog();
        ApiModule.getInstance().getTicketInfo("1", PreferenceUtil.getString(Constanst.CITY_NATION_NAME), "", ""
                , "", "", "", "", "1",null)
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
}
