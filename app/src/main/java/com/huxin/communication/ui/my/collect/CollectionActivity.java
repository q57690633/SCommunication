package com.huxin.communication.ui.my.collect;

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
import com.huxin.communication.adpter.CollectAdapter;
import com.huxin.communication.adpter.CollectDuoXuanAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.CollectEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewDuoXuan;

    private LinearLayout mLinearLayoutSort;
    private LinearLayout mLinearLayoutMeasure;
    private LinearLayout mLinearLayoutPrice;
    private LinearLayout mLinearLayoutMore;
    private LinearLayout mLinearLayoutFangXing;

    private LinearLayout mLinearLayoutSorts;
    private LinearLayout mLinearLayoutMeasures;
    private LinearLayout mLinearLayoutPrices;
    private LinearLayout mLinearLayoutMores;
    private LinearLayout mLinearLayoutFangXings;

    private RelativeLayout mRelativeLayoutSearch;
    private RelativeLayout mRelativeLayoutDuoxuanBtn;


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

    private TextView mTextViewGuanLi;
    private TextView mTextViewQuXiao;

    private ImageView mImageViewPrice;
    private ImageView mImageViewSort;
    private ImageView mImageViewMeasure;
    private ImageView mImageViewMore;
    private ImageView mImageViewFangxin;
    private LinearLayout mRelativeLayoutRL;

    private TextView mTextViewSell;
    private TextView mTextViewQiuZhu;
    private TextView mTextViewQiuGou;
    private TextView mTextViewChuZhu;


    private CollectDuoXuanAdapter mAdpterDuoXuan;


    private CollectAdapter mAdpter;
    private List<String> list = new ArrayList<>();
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_collection);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("收藏夹", MODE_BACK);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyler_sell);
        mRecyclerViewDuoXuan = (RecyclerView) findViewById(R.id.recyler_sell_duoxuan);


        mLinearLayoutFangXing = (LinearLayout) findViewById(R.id.fangxin);
        mLinearLayoutMeasure = (LinearLayout) findViewById(R.id.measure);
        mLinearLayoutMore = (LinearLayout) findViewById(R.id.more);
        mLinearLayoutPrice = (LinearLayout) findViewById(R.id.price);
        mLinearLayoutSort = (LinearLayout) findViewById(R.id.sort);

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

        mTextViewDetermineSort = (TextView) findViewById(R.id.sort_Determine);
        mTextViewDetermineFangXin = (TextView) findViewById(R.id.fangxin_Determine);
        mTextViewDetermineMeasure = (TextView) findViewById(R.id.measure_Determine);
        mTextViewDetermineMore = (TextView) findViewById(R.id.more_Determine);
        mTextViewDeterminePrice = (TextView) findViewById(R.id.price_Determine);

        mTextViewGuanLi = (TextView) findViewById(R.id.toolbar_right);
        mTextViewQuXiao = (TextView) findViewById(R.id.toolbar_quxiao);

        mRelativeLayoutRL = (LinearLayout) findViewById(R.id.rl_recyler_sell);
        mImageViewFangxin = (ImageView) findViewById(R.id.image_fangxin);
        mImageViewPrice = (ImageView) findViewById(R.id.image_price);
        mImageViewSort = (ImageView) findViewById(R.id.image_sort);
        mImageViewMeasure = (ImageView) findViewById(R.id.image_measure);
        mImageViewMore = (ImageView) findViewById(R.id.image_more);

        mTextViewChuZhu = (TextView) findViewById(R.id.chuzu);
        mTextViewQiuGou = (TextView) findViewById(R.id.qiugou);
        mTextViewQiuZhu = (TextView) findViewById(R.id.qiuzhu);
        mTextViewSell = (TextView) findViewById(R.id.sell);

        mLinearLayoutFangXing.setOnClickListener(this);
        mLinearLayoutMeasure.setOnClickListener(this);
        mLinearLayoutMore.setOnClickListener(this);
        mLinearLayoutPrice.setOnClickListener(this);
        mLinearLayoutSort.setOnClickListener(this);
        mTextViewDetermineSort.setOnClickListener(this);
        mTextViewDetermineFangXin.setOnClickListener(this);
        mTextViewDetermineMeasure.setOnClickListener(this);
        mTextViewDetermineMore.setOnClickListener(this);
        mTextViewDeterminePrice.setOnClickListener(this);
        mTextViewGuanLi.setOnClickListener(this);
        mTextViewQuXiao.setOnClickListener(this);

        mTextViewChuZhu.setOnClickListener(this);
        mTextViewQiuGou.setOnClickListener(this);
        mTextViewQiuZhu.setOnClickListener(this);
        mTextViewSell.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getSaleOfScreening(1);
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

            case R.id.fangxin_Determine:
                updata();
                break;
            case R.id.measure_Determine:
                updata();
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
            case R.id.chuzu:
                mTextViewChuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewChuZhu.setTextColor(getResources().getColor(R.color.white));
                mTextViewQiuGou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSell.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewSell.setTextColor(getResources().getColor(R.color.register_font));
                getSaleOfScreening(2);
                break;
            case R.id.qiugou:
                mTextViewChuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewChuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuGou.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.white));
                mTextViewQiuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSell.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewSell.setTextColor(getResources().getColor(R.color.register_font));
                getSaleOfScreening(3);
                break;
            case R.id.qiuzhu:
                mTextViewChuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewChuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuGou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewQiuZhu.setTextColor(getResources().getColor(R.color.white));
                mTextViewSell.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewSell.setTextColor(getResources().getColor(R.color.register_font));
                getSaleOfScreening(4);
                break;
            case R.id.sell:
                mTextViewChuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewChuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuGou.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuGou.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewQiuZhu.setBackgroundResource(R.drawable.biaoqian_radius_top);
                mTextViewQiuZhu.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewSell.setBackgroundResource(R.drawable.biaoqian_radius_top_blue);
                mTextViewSell.setTextColor(getResources().getColor(R.color.white));
                getSaleOfScreening(1);
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
        mLinearLayoutMeasures.setVisibility(View.GONE);
        mLinearLayoutFangXings.setVisibility(View.GONE);
        mTextViewFangXing.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewPrice.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewMore.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewMeasure.setTextColor(getResources().getColor(R.color.register_font));
        mTextViewSort.setTextColor(getResources().getColor(R.color.register_font));
    }

    private void setDuoXuanData(CollectEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpterDuoXuan = new CollectDuoXuanAdapter(entity.getList(), this);
            mRecyclerViewDuoXuan.setAdapter(mAdpterDuoXuan);
            mRecyclerViewDuoXuan.setLayoutManager(manager);
            mRecyclerViewDuoXuan.addItemDecoration(new SpaceItemDecoration(0, 15));
            mTextViewGuanLi.setVisibility(View.VISIBLE);
            mRelativeLayoutSearch.setVisibility(View.VISIBLE);
        }

    }

    private void setData(CollectEntity entity) {
        if (entity.getList() != null && entity.getList().size() > 0) {

            LinearLayoutManager manager = new LinearLayoutManager(this);
            mAdpter = new CollectAdapter(entity.getList(), this);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    private void getSaleOfScreening(int newOeOld) {
//        pid = getIntent().getStringExtra("pid");
        showProgressDialog();
        ApiModule.getInstance().getCollectProduct("", "", "", "", "",
                "", "", String.valueOf(newOeOld), "",
                "", "", "", "", "", "", "1", "1")
                .subscribe(collectEntities -> {
                    KyLog.object(collectEntities + "");
                    if (collectEntities != null) {
                        setData(collectEntities);
                        setDuoXuanData(collectEntities);
                    }

                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

}
