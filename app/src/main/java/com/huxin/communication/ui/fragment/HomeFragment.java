package com.huxin.communication.ui.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.GetMsgManager;
import com.huxin.communication.HomeViewPagerTravelAdapter;
import com.huxin.communication.R;
import com.huxin.communication.adpter.HeadHouseAdapter;
import com.huxin.communication.adpter.HeadTravelTicketAdapter;
import com.huxin.communication.adpter.HomeTravelAdapter;
import com.huxin.communication.adpter.HomeViewPagerAdapter;
import com.huxin.communication.adpter.RecyclerHomeAdpter;
import com.huxin.communication.base.BaseFragment;
import com.huxin.communication.base.HomeFragmentMsgDBHelper;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.GetMessageEntity;
import com.huxin.communication.entity.HeadTravelEntivty;
import com.huxin.communication.entity.HomeEntity;
import com.huxin.communication.entity.HomeTravelEntity;
import com.huxin.communication.entity.InlandCityEntity;
import com.huxin.communication.entity.ProvinceEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.listener.GetMessageListener;
import com.huxin.communication.ui.InvitationActivity;
import com.huxin.communication.ui.house.MessageRemindActivity;
import com.huxin.communication.ui.house.TopSelectionActivity;
import com.huxin.communication.ui.house.match.MatchActivity;
import com.huxin.communication.ui.house.sell.QiuGouActivity;
import com.huxin.communication.ui.house.sell.QiuZuActivity;
import com.huxin.communication.ui.house.sell.RentActivity;
import com.huxin.communication.ui.house.sell.SellActivity;
import com.huxin.communication.ui.my.collect.CollectionActivity;
import com.huxin.communication.ui.my.feedback.FeedbackActivity;
import com.huxin.communication.ui.travel.CaiXianActivity;
import com.huxin.communication.ui.travel.CollectTravelActivity;
import com.huxin.communication.ui.travel.DomesticActivity;
import com.huxin.communication.ui.travel.JinWaiActivity;
import com.huxin.communication.ui.travel.TicketingActivity;
import com.huxin.communication.ui.travel.TopSelectionTravelActivity;
import com.huxin.communication.ui.travel.ZhouBianActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.utils.SQLiteUtil;
import com.huxin.communication.view.SmoothLinearLayoutManager;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupDetailInfo;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import com.tencent.imsdk.ext.message.TIMManagerExt;
import com.tencent.imsdk.ext.message.TIMMessageExt;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, GetMessageListener, TIMMessageListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private RecyclerHomeAdpter mAdpter;
    private Disposable mDisposable;

    private HeadHouseAdapter mHeadLineAdapter;
    private HomeTravelAdapter mHeadTravelAdapter;
    private HeadTravelTicketAdapter mTravelTicketAdapter;


    private LinearLayout mLinearLayoutSell;
    private LinearLayout mLinearLayoutrental;
    private LinearLayout mLinearLayoutmatching;
    private LinearLayout mLinearLayoutqiuzu;
    private LinearLayout mLinearLayoutqiugou;

    private LinearLayout mLinearLayoutGuoNei;
    private LinearLayout mLinearLayouJingWai;
    private LinearLayout mLinearLayoutZhouBain;
    private LinearLayout mLinearLayoutPiaoWu;

    private LinearLayout mLinearLayoutAddXuanXiang;
    private LinearLayout mLinearLayoutAddkefu;
    private LinearLayout mLinearLayoutAddcode;

    private ImageView mImageViewInvitation;
    private TextView mTextViewTopSelection;
    private TextView mTextViewMessage;
    private TextView mTextViewCollect;
    private TextView mTextViewTravelTopSelection;
    private TextView mTextViewCaiXian;
    private TextView mTextViewTravelCollect;

    private GetMsgManager mGetMsgManager;

    private RecyclerView mRecyclerViewHead;

    private ImageView mImageViewTongyetoutiao;

    /**
     * 滚动焦点图片
     **/
    private ViewPager mViewPager;
    /**
     * 滚动焦点图片页码点
     **/
    private LinearLayout dot;

    private boolean mIsStop = false;// 焦点图触摸暂停切换

    private int big_index = 0;// 大焦点图自动切换初始位置

    private List<String> imageList = new ArrayList<>();

    private HomeViewPagerAdapter mViewPagerAdapter;

    private HomeViewPagerTravelAdapter mTravelViewPagerAdapter;

    /**
     * 获取所有会话
     *
     * @return 会话列表
     */
    public List<TIMConversation> ConversationList;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        KyLog.d(PreferenceUtil.getInt("type") + "");
        if (PreferenceUtil.getInt("type") == 1) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_home_travel, container, false);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        KyLog.d("onStart");
        if (PreferenceUtil.getInt("type") == 1) {
            initData();
        } else {
            initDataTravel();
            getProvinces();
        }
        TIMManager.getInstance().addMessageListener(this);
        String userId = PreferenceUtil.getInt("uid") + "";
        String userSig = PreferenceUtil.getString("usersig");
        TUIKit.login(userId, userSig, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                getConversationList();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                KyLog.d("errCode = " + errCode + " errMsg = " + errMsg);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        autoLoop();//轮询


    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_home);
        mRecyclerViewHead = (RecyclerView) view.findViewById(R.id.recycler_head);
        mImageViewInvitation = (ImageView) view.findViewById(R.id.invitation_btn);
        mTextViewCollect = (TextView) view.findViewById(R.id.collect_btn);
        mTextViewTopSelection = (TextView) view.findViewById(R.id.top_selection_btn);
        mLinearLayoutAddXuanXiang = (LinearLayout) view.findViewById(R.id.add_xuanxiang);
        mLinearLayoutAddkefu = (LinearLayout) view.findViewById(R.id.kefu);
        mLinearLayoutAddcode = (LinearLayout) view.findViewById(R.id.code);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        if (PreferenceUtil.getInt("type") == 1) {
            initViewHouse(view);
        } else {
            initViewTravel(view);
        }

        mImageViewInvitation.setOnClickListener(this);
        mTextViewCollect.setOnClickListener(this);
        mTextViewTopSelection.setOnClickListener(this);
        mLinearLayoutAddkefu.setOnClickListener(this);
        mLinearLayoutAddcode.setOnClickListener(this);


    }

    @Override
    protected void loadData() {
        mGetMsgManager = GetMsgManager.instants();
        mGetMsgManager.setmMessageListener(this);
    }


    public void initViewHouse(View view) {
        mLinearLayoutmatching = (LinearLayout) view.findViewById(R.id.home_matching_line);
        mLinearLayoutqiugou = (LinearLayout) view.findViewById(R.id.home_qiugou_line);
        mLinearLayoutqiuzu = (LinearLayout) view.findViewById(R.id.home_qiuzu_line);
        mLinearLayoutrental = (LinearLayout) view.findViewById(R.id.home_rental_line);
        mLinearLayoutSell = (LinearLayout) view.findViewById(R.id.home_sell_line);


        mTextViewMessage = (TextView) view.findViewById(R.id.message_btn);


        mLinearLayoutSell.setOnClickListener(this);
        mLinearLayoutmatching.setOnClickListener(this);
        mLinearLayoutqiugou.setOnClickListener(this);
        mLinearLayoutqiuzu.setOnClickListener(this);
        mLinearLayoutrental.setOnClickListener(this);
        mTextViewMessage.setOnClickListener(this);


    }

    public void initViewTravel(View view) {

        mLinearLayoutGuoNei = (LinearLayout) view.findViewById(R.id.travel_guonei_line);
        mLinearLayouJingWai = (LinearLayout) view.findViewById(R.id.travel_jingwai_line);
        mLinearLayoutPiaoWu = (LinearLayout) view.findViewById(R.id.travel_piaowu_line);
        mLinearLayoutZhouBain = (LinearLayout) view.findViewById(R.id.travel_zhoubian_line);
        mTextViewCaiXian = (TextView) view.findViewById(R.id.caixian_btn);
        mImageViewTongyetoutiao = view.findViewById(R.id.tongyetoutiao);

        mLinearLayoutGuoNei.setOnClickListener(this);
        mLinearLayouJingWai.setOnClickListener(this);
        mLinearLayoutPiaoWu.setOnClickListener(this);
        mLinearLayoutZhouBain.setOnClickListener(this);
        mTextViewCaiXian.setOnClickListener(this);
    }

    @Override
    protected void bindData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_matching_line:
                Intent intentMatch = new Intent(getContext(), MatchActivity.class);
                getContext().startActivity(intentMatch);
                break;
            case R.id.home_qiugou_line:
                Intent intentQiuGou = new Intent(getContext(), QiuGouActivity.class);
                getContext().startActivity(intentQiuGou);
                break;
            case R.id.home_rental_line:
                Intent intentQRetal = new Intent(getContext(), RentActivity.class);
                getContext().startActivity(intentQRetal);
                break;
            case R.id.home_sell_line:
                Intent intentSell = new Intent(getContext(), SellActivity.class);
                getContext().startActivity(intentSell);
                break;
            case R.id.home_qiuzu_line:
                Intent intentQiuZu = new Intent(getContext(), QiuZuActivity.class);
                getContext().startActivity(intentQiuZu);
                break;
            case R.id.invitation_btn:
                mLinearLayoutAddXuanXiang.setVisibility(View.VISIBLE);
                break;
            case R.id.message_btn:
                mTextViewTopSelection.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewCollect.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewMessage.setTextColor(getResources().getColor(R.color.blue));
                Intent intentMessage = new Intent(getContext(), MessageRemindActivity.class);
                getContext().startActivity(intentMessage);
                break;
            case R.id.collect_btn:
                if (PreferenceUtil.getInt("type") == 1) {
                    mTextViewTopSelection.setTextColor(getResources().getColor(R.color.register_font));
                    mTextViewCollect.setTextColor(getResources().getColor(R.color.blue));
                    mTextViewMessage.setTextColor(getResources().getColor(R.color.register_font));
                    Intent intentCollect = new Intent(getContext(), CollectionActivity.class);
                    getContext().startActivity(intentCollect);
                } else {
                    mTextViewCaiXian.setTextColor(getResources().getColor(R.color.register_font));
                    mTextViewCollect.setTextColor(getResources().getColor(R.color.blue));
                    mTextViewTopSelection.setTextColor(getResources().getColor(R.color.register_font));
                    Intent intentCollectTravel = new Intent(getContext(), CollectTravelActivity.class);
                    getContext().startActivity(intentCollectTravel);
                }

                break;
            case R.id.top_selection_btn:
                if (PreferenceUtil.getInt("type") == 1) {
                    mTextViewTopSelection.setTextColor(getResources().getColor(R.color.blue));
                    mTextViewCollect.setTextColor(getResources().getColor(R.color.register_font));
                    mTextViewMessage.setTextColor(getResources().getColor(R.color.register_font));
                    Intent intentTop = new Intent(getContext(), TopSelectionActivity.class);
                    getContext().startActivity(intentTop);
                } else {
                    mTextViewCaiXian.setTextColor(getResources().getColor(R.color.register_font));
                    mTextViewCollect.setTextColor(getResources().getColor(R.color.register_font));
                    mTextViewTopSelection.setTextColor(getResources().getColor(R.color.blue));
                    Intent intentTopTravel = new Intent(getContext(), TopSelectionTravelActivity.class);
                    getContext().startActivity(intentTopTravel);
                }
                break;
            case R.id.travel_guonei_line:
                Intent intentGuoNei = new Intent(getContext(), DomesticActivity.class);
                getContext().startActivity(intentGuoNei);
                break;
            case R.id.travel_zhoubian_line:
                Intent intentZhouBian = new Intent(getContext(), ZhouBianActivity.class);
                getContext().startActivity(intentZhouBian);
                break;
            case R.id.travel_jingwai_line:
                Intent intentJingWai = new Intent(getContext(), JinWaiActivity.class);
                getContext().startActivity(intentJingWai);
                break;
            case R.id.travel_piaowu_line:
                Intent intentPiaoWu = new Intent(getContext(), TicketingActivity.class);
                getContext().startActivity(intentPiaoWu);
                break;
            case R.id.caixian_btn:
                mTextViewCaiXian.setTextColor(getResources().getColor(R.color.blue));
                mTextViewCollect.setTextColor(getResources().getColor(R.color.register_font));
                mTextViewTopSelection.setTextColor(getResources().getColor(R.color.register_font));
                Intent intentCaiXian = new Intent(getContext(), CaiXianActivity.class);
                getContext().startActivity(intentCaiXian);
                break;
            case R.id.code:
                Intent intentCode = new Intent(getContext(), InvitationActivity.class);
                getContext().startActivity(intentCode);
                mLinearLayoutAddXuanXiang.setVisibility(View.GONE);
                break;
            case R.id.kefu:
                Intent intentKeFu = new Intent(getContext(), FeedbackActivity.class);
                startActivity(intentKeFu);

                mLinearLayoutAddXuanXiang.setVisibility(View.GONE);
                break;
        }
    }

    private void initData() {
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
        KyLog.d(PreferenceUtil.getString(Constanst.DISTRICT_NAME));

        showProgressDialog();
        ApiModule.getInstance().getHomes(PreferenceUtil.getString(Constanst.CITY_NAME), PreferenceUtil.getString(Constanst.DISTRICT_NAME))
                .subscribe(homeEntity -> {
                    cancelProgressDialog();
                    if (homeEntity != null) {
                        KyLog.d(homeEntity.getCarousel().size() + "");
                        setOnBinner(homeEntity.getCarousel());
                        if (homeEntity.getHeadLine() != null && homeEntity.getHeadLine().size() > 0) {
                            final SmoothLinearLayoutManager layoutManager = new SmoothLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                            mHeadLineAdapter = new HeadHouseAdapter(homeEntity.getHeadLine(), getContext());
                            mRecyclerViewHead.setAdapter(mHeadLineAdapter);
                            mRecyclerViewHead.setLayoutManager(layoutManager);

                            PagerSnapHelper snapHelper = new PagerSnapHelper();
                            snapHelper.attachToRecyclerView(mRecyclerViewHead);

                            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
                            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                                @Override
                                public void run() {
                                    mRecyclerViewHead.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
                                }
                            }, 4000, 4000, TimeUnit.MILLISECONDS);
                        }
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
//                    Toast.makeText(getContext(), throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void initDataTravel() {
//        showProgressDialog();
        ApiModule.getInstance().getTravelHome(PreferenceUtil.getString(Constanst.CITY_NAME), String.valueOf(PreferenceUtil.getInt("uid")))
                .subscribe(homeTravelEntity -> {
//                    cancelProgressDialog();
                    if (homeTravelEntity != null) {
                        KyLog.d(homeTravelEntity.getCarousel().size() + "");
                        setTravelOnBinner(homeTravelEntity.getCarousel());

                        List<HeadTravelEntivty> list = new ArrayList<>();
                        if (homeTravelEntity.getAroundHead() != null && homeTravelEntity.getAroundHead().size() > 0) {
                            for (HomeTravelEntity.AroundHeadBean aroundHeadBean : homeTravelEntity.getAroundHead()) {
                                HeadTravelEntivty headTravelEntivty = new HeadTravelEntivty();
                                headTravelEntivty.setTravelTitle(aroundHeadBean.getTravelTitle());
                                headTravelEntivty.setDepart_name(aroundHeadBean.getDepart_name());
                                headTravelEntivty.setGoals_city(aroundHeadBean.getGoals_city());
                                headTravelEntivty.setTotalPrice(aroundHeadBean.getTotalPrice());
                                headTravelEntivty.setHeadUrl(aroundHeadBean.getHeadUrl());
                                headTravelEntivty.setNumberDays(aroundHeadBean.getNumberDays());
                                headTravelEntivty.setProductType(1);
                                headTravelEntivty.setIsCollect(aroundHeadBean.getIsCollect());
                                headTravelEntivty.setId(aroundHeadBean.getId());


                                list.add(headTravelEntivty);
                            }
                        }

                        if (homeTravelEntity.getForeignHead() != null && homeTravelEntity.getForeignHead().size() > 0) {
                            for (HomeTravelEntity.ForeignHeadBean foreignHeadBean : homeTravelEntity.getForeignHead()) {
                                HeadTravelEntivty headTravelEntivty = new HeadTravelEntivty();
                                headTravelEntivty.setTravel_title(foreignHeadBean.getTravel_title());
                                headTravelEntivty.setDepart_name(foreignHeadBean.getDepart_name());
                                headTravelEntivty.setGoals_name(foreignHeadBean.getGoals_name());
                                headTravelEntivty.setTotal_price(foreignHeadBean.getTotal_price());
                                headTravelEntivty.setNumber_days(foreignHeadBean.getNumber_days());
                                headTravelEntivty.setHeadUrl(foreignHeadBean.getHeadUrl());
                                headTravelEntivty.setReturn_price(foreignHeadBean.getReturn_price());
                                headTravelEntivty.setIsCollect(foreignHeadBean.getIsCollect());
                                headTravelEntivty.setId(foreignHeadBean.getId());

                                headTravelEntivty.setProductType(2);
                                list.add(headTravelEntivty);
                            }
                        }


                        if (homeTravelEntity.getTicketHead() != null && homeTravelEntity.getTicketHead().size() > 0) {
                            for (HomeTravelEntity.TicketHeadBean ticketHeadBean : homeTravelEntity.getTicketHead()) {
                                HeadTravelEntivty headTravelEntivty = new HeadTravelEntivty();
                                headTravelEntivty.setTicket_name(ticketHeadBean.getTicket_name());
                                headTravelEntivty.setOriginal_price(ticketHeadBean.getOriginal_price());
                                headTravelEntivty.setTicket_city_name(ticketHeadBean.getTicket_city_name());
                                headTravelEntivty.setFinal_price(ticketHeadBean.getFinal_price());
                                headTravelEntivty.setHeadUrl(ticketHeadBean.getHeadUrl());
                                headTravelEntivty.setProductType(3);
                                headTravelEntivty.setIsCollect(ticketHeadBean.getIsCollect());
                                headTravelEntivty.setId(ticketHeadBean.getId());

                                list.add(headTravelEntivty);
                            }
                        }

                        KyLog.d(list.size() + "");
                        KyLog.object(list);

                        if (list.size() > 0) {
                            mImageViewTongyetoutiao.setVisibility(View.GONE);
                        }else {
                            mImageViewTongyetoutiao.setVisibility(View.VISIBLE);
                        }
                            final SmoothLinearLayoutManager layoutManager = new SmoothLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                            mHeadTravelAdapter = new HomeTravelAdapter(list, getContext());
                            mRecyclerViewHead.setAdapter(mHeadTravelAdapter);
                            mRecyclerViewHead.setHasFixedSize(true);
                            mRecyclerViewHead.setLayoutManager(layoutManager);

                            PagerSnapHelper snapHelper = new PagerSnapHelper();
                            snapHelper.attachToRecyclerView(mRecyclerViewHead);
                            if (list.size() > 1) {
                                ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
                                scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                                    @Override
                                    public void run() {
                                        mRecyclerViewHead.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
                                    }
                                }, 4000, 4000, TimeUnit.MILLISECONDS);
                            }
                        }
//                    }


                }, throwable -> {
                    KyLog.d(throwable.toString());
//                    Toast.makeText(getContext(), throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    // 设置焦点图片数量小圆点的方法
    // private int page=0;
    public void setCurPage(int page, int count) {
        try {
            dot.removeAllViews();
            for (int i = 0; i < count; i++) {
                ImageView imgCur = new ImageView(getContext());
                imgCur.setBackgroundResource(R.drawable.gray);
                imgCur.setId(i);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        -2, -2);
                // lp.leftMargin = 5;
                lp.rightMargin = 5;
                if (imgCur.getId() == page) {
                    imgCur.setBackgroundResource(R.drawable.orange);
                }
                dot.addView(imgCur, lp);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private final int SCROLL_WHAT = 1;// 大焦点图

    private MyFocusHandler scrollHandler = new MyFocusHandler();

    /**
     * 焦点图自动滚动方法
     *
     * @param delayTimeInMills
     */
    private void sendScrollMessage(long delayTimeInMills) {
        /** remove messages before, keeps one message is running at most **/
        scrollHandler.removeMessages(SCROLL_WHAT);
        scrollHandler.sendEmptyMessageDelayed(SCROLL_WHAT, delayTimeInMills);
    }

    public void onScroll(int scrollY) {

    }

    @Override
    public void getMessage() {
        //KyLog.object("login ==------ list----- " + list);
        String TABLE_NAME = HomeFragmentMsgDBHelper.TABLE_NAME;
        String UID = HomeFragmentMsgDBHelper.UID;
        String MESSAGE = HomeFragmentMsgDBHelper.MESSAGE;
        String TIME = HomeFragmentMsgDBHelper.TIME;
        String HEAD_URL = HomeFragmentMsgDBHelper.HEAD_URL;
        String TYPE = HomeFragmentMsgDBHelper.TYPE;
        String UNREAD_NUM = HomeFragmentMsgDBHelper.UNREAD_NUM;
        String IS_READ = HomeFragmentMsgDBHelper.IS_READ;
        String NICK_NAME = HomeFragmentMsgDBHelper.NICKNAME;
        SQLiteUtil util = new SQLiteUtil(getContext());
        String currentId = PreferenceUtil.getInt("uid") + "";
        Cursor cursor = util.query(TABLE_NAME, null);
        List<GetMessageEntity> dbList = new ArrayList<>();
        while (cursor.moveToNext()) {
            GetMessageEntity entity = new GetMessageEntity();
            String curId = cursor.getString(cursor.getColumnIndex(HomeFragmentMsgDBHelper.CURRENTUID));
            if(currentId.equalsIgnoreCase(curId)) {
                String uid, message, time, head_url, type, unread_num, isread, nickname;
                uid = cursor.getString(cursor.getColumnIndex(UID));
                message = cursor.getString(cursor.getColumnIndex(MESSAGE));
                time = cursor.getString(cursor.getColumnIndex(TIME));
                head_url = cursor.getString(cursor.getColumnIndex(HEAD_URL));
                type = cursor.getString(cursor.getColumnIndex(TYPE));
                unread_num = cursor.getString(cursor.getColumnIndex(UNREAD_NUM));
                isread = cursor.getString(cursor.getColumnIndex(IS_READ));
                nickname = cursor.getString(cursor.getColumnIndex(NICK_NAME));
                entity.setId(uid);
                entity.setMsg(message);
                entity.setTimeStamp(Long.parseLong(time));
                entity.setHead_url(head_url);
                entity.setType(type);
                entity.setNum(Long.parseLong(unread_num));
                entity.setRead(Boolean.getBoolean(isread));
                entity.setNickName(nickname);
                dbList.add(entity);
            }
        }
        if (dbList.size() > 0) {
            List<GetMessageEntity> list = getList(dbList);
            List<String> Mutelist = getMuteList(dbList);

            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mAdpter = new RecyclerHomeAdpter(list, getContext(),Mutelist);
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
        }

    }

    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        KyLog.object("login ==------ list----- " + list);
        KyLog.i("HomeFragment onNewMessage method Run");
        List<GetMessageEntity> lists = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String text = "";
            TIMMessage message = list.get(i);
            //if (i == 0) {
            TIMElem elem = message.getElement(0);
            if (null == elem) {
                return true;
            }
            if (elem.getType() != null) {
                if (elem.getType() == TIMElemType.Text) {
                    TIMTextElem e = (TIMTextElem) elem;
                    text = e.getText();
                }
            }
            //}
            if (elem.getType() == TIMElemType.Image) {
                text = getResources().getString(R.string.msg_image);
            }
            if (elem.getType() == TIMElemType.Sound) {
                text = getResources().getString(R.string.msg_audio);
            }
            if (elem.getType() == TIMElemType.Video) {
                text = getResources().getString(R.string.msg_video);
            }
            if (elem.getType() == TIMElemType.Custom) {
                text = getResources().getString(R.string.msg_custom);
            }
            if (elem.getType() == TIMElemType.Face) {
                text = getResources().getString(R.string.msg_face);
            }
            String currentId = PreferenceUtil.getInt("uid") + "";
            String sender = message.getConversation().getPeer();
            String faceUrl = message.getSenderProfile().getFaceUrl();
            TIMConversationType conversationType = message.getConversation().getType();
            String type = conversationType.name();
            long timeStamp = message.timestamp();
            TIMConversation con = TIMManager.getInstance().getConversation(TIMConversationType.C2C, message.getConversation().getPeer());
            TIMConversationExt conExt = new TIMConversationExt(con);
            long count = conExt.getUnreadMessageNum();
            TIMMessageExt msgExt = new TIMMessageExt(message);
            boolean isRead = msgExt.isRead();
            KyLog.d("text == " + text);
            KyLog.d("sender == " + sender);
            KyLog.d("faceUrl == " + faceUrl);
            KyLog.d("type == " + type);
            KyLog.d("timeStamp == " + timeStamp);
            ContentValues values = new ContentValues();
            values.put(HomeFragmentMsgDBHelper.UID, sender);
            values.put(HomeFragmentMsgDBHelper.MESSAGE, text);
            values.put(HomeFragmentMsgDBHelper.TIME, timeStamp);
            values.put(HomeFragmentMsgDBHelper.HEAD_URL, faceUrl);
            values.put(HomeFragmentMsgDBHelper.TYPE, type);
            values.put(HomeFragmentMsgDBHelper.UNREAD_NUM, count);
            values.put(HomeFragmentMsgDBHelper.IS_READ, isRead + "");
            values.put(HomeFragmentMsgDBHelper.CURRENTUID, currentId);
            SQLiteUtil util = new SQLiteUtil(getContext());
            boolean isUpdate = false;
            Cursor cursor = util.query(HomeFragmentMsgDBHelper.TABLE_NAME, null);
            while (cursor.moveToNext()) {
                String uid = cursor.getString(cursor.getColumnIndex(HomeFragmentMsgDBHelper.UID));
                if (sender.equalsIgnoreCase(uid)) {
                    isUpdate = true;
                }
            }
            if (isUpdate) {
                util.update(HomeFragmentMsgDBHelper.TABLE_NAME, values, "uid = ?", new String[]{sender});
            } else {
                util.insert(HomeFragmentMsgDBHelper.TABLE_NAME, values);
            }

        }

        GetMsgManager msgManager = GetMsgManager.instants();
        msgManager.setList(lists);
        return true;
    }


    private class MyFocusHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SCROLL_WHAT:
                    if (big_index == imageList.size()) {
                        big_index = 0;
                    }
                    // 点击图片时，自动切换暂停
                    if (!mIsStop) {
                        int currentIndex = mViewPager.getCurrentItem();
                        if (++currentIndex == mViewPagerAdapter.getCount()) {
                            mViewPager.setCurrentItem(0);
                        } else {
//                            mViewPager.setCurrentItem(currentIndex, true);
                        }
                    }
                    sendScrollMessage(3000);
                    break;

            }
        }

    }

    /**
     * 加载binner图
     */
    private void setOnBinner(List<HomeEntity.CarouselBean> list) {
//        imageList = new ArrayList<>();
////        if (!TextUtils.isEmpty(list.get(0).getPhotoUrl())) {
////            String[] str = list.get(0).getPhotoUrl().split(",");
////            for (String strs : str) {
////                imageList.add(strs);
////            }
////        }
//        KyLog.d(imageList.size() + "");
        if (list != null && list.size() > 0) {
            mViewPagerAdapter = new HomeViewPagerAdapter(getContext(), list);
            mViewPager.setAdapter(mViewPagerAdapter);

            if (imageList.size() > 1) {
                mViewPager.setCurrentItem(((Short.MAX_VALUE / 2) / imageList.size()) * imageList.size(), false);
                setCurPage(0 % imageList.size(), imageList.size());
            }
//            sendScrollMessage(3000);

            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    big_index = position;
                    if (imageList.size() > 1) {
                        setCurPage(position % imageList.size(), imageList.size());
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }


    private void setTravelOnBinner(List<HomeTravelEntity.CarouselBean> list) {
//        imageList = new ArrayList<>();
////        if (!TextUtils.isEmpty(list.get(0).getPhotoUrl())) {
////            String[] str = list.get(0).getPhotoUrl().split(",");
////            for (String strs : str) {
////                imageList.add(strs);
////            }
////        }
//        KyLog.d(imageList.size() + "");
        if (list != null && list.size() > 0) {
            mTravelViewPagerAdapter = new HomeViewPagerTravelAdapter(getContext(), list);
            mViewPager.setAdapter(mTravelViewPagerAdapter);

            if (imageList.size() > 1) {
                mViewPager.setCurrentItem(((Short.MAX_VALUE / 2) / imageList.size()) * imageList.size(), false);
                setCurPage(0 % imageList.size(), imageList.size());
            }

            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    big_index = position;
                    if (imageList.size() > 1) {
                        setCurPage(position % imageList.size(), imageList.size());
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    public void getProvinces() {
        KyLog.d(PreferenceUtil.getString(Constanst.PROVINCE_NAME));

//        showProgressDialog();
        ApiModule.getInstance().getProvinces().subscribe(provinceEntities -> {
//            cancelProgressDialog();
            if (provinceEntities != null && provinceEntities.size() > 0) {
                for (ProvinceEntity entity : provinceEntities) {
                    if (entity.getProvince_name().equals(PreferenceUtil.getString(Constanst.PROVINCE_NAME))) {
                        PreferenceUtil.putString(Constanst.PROVINCE_HOME_CODE, entity.getProvince_code());
                        getInlandCity(entity.getProvince_code());
                        break;
                    }
                }

            }
        }, throwable -> {
            KyLog.d(throwable.toString());
//            cancelProgressDialog();
            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void getInlandCity(String provinceCode) {
        KyLog.d(PreferenceUtil.getString(Constanst.CITY_NAME));
//        showProgressDialog();
        ApiModule.getInstance().getInlandCity(provinceCode).subscribe(inlandCityEntities -> {
//            cancelProgressDialog();
            if (inlandCityEntities != null && inlandCityEntities.size() > 0) {
                for (InlandCityEntity entity : inlandCityEntities) {
                    if (entity.getCity_name().equals(PreferenceUtil.getString(Constanst.CITY_NAME))) {
                        PreferenceUtil.putString(Constanst.CITY_HOME_CODE, entity.getCity_code());
                        break;
                    }
                }

            }
        }, throwable -> {
            KyLog.d(throwable.toString());
//            cancelProgressDialog();
            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private static final int TIMNEWSMESSAHE = 0x01;

    private Handler mHandler = new Handler();


    private void getConversationList() {
        ConversationList = TIMManagerExt.getInstance().getConversationList();
        KyLog.d(ConversationList.size() + " === ConversationList.size()");
        List<GetMessageEntity> list = new ArrayList<>();

        if (ConversationList != null && ConversationList.size() > 0) {
            for (TIMConversation conversation : ConversationList) {
                KyLog.d(conversation.getPeer() + " === getPeer");
                getLocalMessage(conversation.getPeer(), list, conversation.getType());
            }
        }
    }

    private void getLocalMessage(String peer, List<GetMessageEntity> list, TIMConversationType type) {
        if (type == TIMConversationType.C2C) {
            getLocalMessage(TIMConversationType.C2C, peer, list);
        } else if (type == TIMConversationType.Group) {
            getLocalMessage(TIMConversationType.Group, peer, list);
        }
    }

    private void getLocalMessage(TIMConversationType type, String peer, List<GetMessageEntity> list) {
        TIMConversation con = TIMManager.getInstance().getConversation(type, peer);
        TIMConversationExt conExt = new TIMConversationExt(con);

        conExt.getLocalMessage(1, null,//不指定从哪条消息开始获取 - 等同于从最新的消息开始往前
                new TIMValueCallBack<List<TIMMessage>>() {//回调接口
                    @Override
                    public void onError(int code, String desc) {//获取消息失败
                        //接口返回了错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 含义请参见错误码表
                        KyLog.d("home", "get message failed. code: " + code + " errmsg: " + desc);
                    }

                    @Override
                    public void onSuccess(List<TIMMessage> msgs) {//获取消息成功
                        KyLog.i("getLocalMessage success");
                        //遍历取得的消息
                        TIMMessage message = null;
                        String text = "";
                        if (msgs.size() > 0) {
                            message = msgs.get(0);
                            TIMElem elem = message.getElement(0);
                            if (elem == null) {
                                return;
                            }
                            if (elem.getType() == TIMElemType.Text) {
                                TIMTextElem e = (TIMTextElem) elem;
                                text = e.getText();
                            }
                            if (elem.getType() == TIMElemType.Image) {
                                text = getResources().getString(R.string.msg_image);
                            }
                            if (elem.getType() == TIMElemType.Sound) {
                                text = getResources().getString(R.string.msg_audio);
                            }
                            if (elem.getType() == TIMElemType.Video) {
                                text = getResources().getString(R.string.msg_video);
                            }
                            if (elem.getType() == TIMElemType.Custom) {
                                text = getResources().getString(R.string.msg_custom);
                            }
                            if (elem.getType() == TIMElemType.Face) {
                                text = getResources().getString(R.string.msg_face);
                            }

                            List<String> identifiersList = new ArrayList<>();
                            String identifiers = message.getSenderProfile().getIdentifier();
                            Log.i("MyTAG", "identifiers = " + identifiers);
                            int uid = PreferenceUtil.getInt("uid");
                            if(!TextUtils.isEmpty(identifiers) && uid == Integer.parseInt(identifiers)) {
                                return;
                            }
                            identifiersList.add(identifiers);
                            TIMMessage finalMessage = message;
                            String finalText = text;
                            TIMConversationType conversationType = message.getConversation().getType();
                            String type = conversationType.name();
                            if("c2c".equalsIgnoreCase(type)) {
                                TIMFriendshipManager.getInstance().getUsersProfile(identifiersList, true, new TIMValueCallBack<List<TIMUserProfile>>() {
                                    @Override
                                    public void onError(int i, String s) {
                                        Log.i("MyTAG", "getUsersProfile error code = " + i + " desc = " + s);
                                    }

                                    @Override
                                    public void onSuccess(List<TIMUserProfile> timUserProfiles) {
                                        TIMUserProfile profile = timUserProfiles.get(0);
                                        Log.i("MyTAG", "profile.getNickName = " + profile.getNickName());
                                        String nickname = profile.getNickName();
                                        Log.i("MyTAG", "profile.getFaceUrl = " + profile.getFaceUrl());
                                        String faceurl = profile.getFaceUrl();
                                        updateDBWhenGetLocalMessage(finalMessage, finalText, nickname, faceurl, list, type);
                                        GetMsgManager msgManager = GetMsgManager.instants();
                                        msgManager.setList(null);
                                    }
                                });
                            }else {
                                String sender = message.getConversation().getPeer();
                                List<String> groupInfoList = new ArrayList<>();
                                groupInfoList.add(sender);
                                TIMGroupManagerExt.getInstance().getGroupPublicInfo(groupInfoList, new TIMValueCallBack<List<TIMGroupDetailInfo>>() {
                                    @Override
                                    public void onError(int i, String s) {
                                        Log.i("MyTAG", "getGroupPublicInfo error code = " + i + " desc = " + s);
                                    }

                                    @Override
                                    public void onSuccess(List<TIMGroupDetailInfo> timGroupDetailInfos) {
                                        TIMGroupDetailInfo info = timGroupDetailInfos.get(0);
                                        Log.i("MyTAG", "info.getGroupName = " + info.getGroupName());
                                        String nickname = info.getGroupName();
                                        String faceurl = info.getFaceUrl();
                                        updateDBWhenGetLocalMessage(finalMessage, finalText, nickname, faceurl, list, type);
                                        GetMsgManager msgManager = GetMsgManager.instants();
                                        msgManager.setList(null);
                                    }
                                });
                            }

                        }
                    }
                });
    }

    private boolean isCity() {
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.CITY_NAME))) {
            return true;
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLoop();
    }

    /**
     * 停止轮询
     */
    private void stopLoop() {
        cancel();
    }

    /**
     * RxJava轮询器
     */
    private void autoLoop() {
        Observable.interval(5000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {

                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (mViewPagerAdapter != null) {
                            int currentIndex = mViewPager.getCurrentItem();
                            if (++currentIndex == mViewPagerAdapter.getCount()) {
                                mViewPager.setCurrentItem(0);
                            } else {
                                mViewPager.setCurrentItem(currentIndex, true);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        cancel();
                    }
                });

    }


    /**
     * 取消订阅
     */
    public void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    private void updateDBWhenGetLocalMessage(TIMMessage message, String text, String nickName, String faceurl, List<GetMessageEntity> list, String type) {
        String currentId = PreferenceUtil.getInt("uid") + "";
        String sender = message.getConversation().getPeer();
        long timeStamp = message.timestamp();
        TIMConversation con;
        if("c2c".equalsIgnoreCase(type)) {
            con = TIMManager.getInstance().getConversation(TIMConversationType.C2C, message.getConversation().getPeer());
        }else {
            con = TIMManager.getInstance().getConversation(TIMConversationType.Group, message.getConversation().getPeer());
        }
        TIMConversationExt conExt = new TIMConversationExt(con);
        long count = conExt.getUnreadMessageNum();
        TIMMessageExt msgExt = new TIMMessageExt(message);
        boolean isRead = msgExt.isRead();
        GetMessageEntity entity = new GetMessageEntity();
        entity.setHead_url(faceurl);
        entity.setId(sender);
        entity.setMsg(text);
        entity.setNum(count);
        entity.setTimeStamp(timeStamp);
        entity.setType(type);
        entity.setRead(isRead);
        list.add(entity);
        KyLog.d(list.size() + "");
        ContentValues values = new ContentValues();
        values.put(HomeFragmentMsgDBHelper.UID, sender);
        values.put(HomeFragmentMsgDBHelper.MESSAGE, text);
        values.put(HomeFragmentMsgDBHelper.TIME, timeStamp);
        values.put(HomeFragmentMsgDBHelper.HEAD_URL, faceurl);
        values.put(HomeFragmentMsgDBHelper.TYPE, type);
        values.put(HomeFragmentMsgDBHelper.UNREAD_NUM, count);
        values.put(HomeFragmentMsgDBHelper.IS_READ, isRead + "");
        values.put(HomeFragmentMsgDBHelper.CURRENTUID, currentId);
        values.put(HomeFragmentMsgDBHelper.NICKNAME, nickName);
        SQLiteUtil util = new SQLiteUtil(getContext());
        boolean isUpdate = false;
        boolean canUpdate = false;
        Cursor cursor = util.query(HomeFragmentMsgDBHelper.TABLE_NAME, null);
        while (cursor.moveToNext()) {
            String uid = cursor.getString(cursor.getColumnIndex(HomeFragmentMsgDBHelper.UID));
            if (sender.equalsIgnoreCase(uid)) {
                isUpdate = true;
                long timeStampDB = Long.parseLong(cursor.getString(cursor.getColumnIndex(HomeFragmentMsgDBHelper.TIME)));
                if(timeStamp >= timeStampDB) {
                    canUpdate = true;
                }
            }
        }
        if (isUpdate) {
            if(canUpdate) {
                util.update(HomeFragmentMsgDBHelper.TABLE_NAME, values, "uid = ?", new String[]{sender});
            }
        } else {
            util.insert(HomeFragmentMsgDBHelper.TABLE_NAME, values);
        }
    }

    private List<GetMessageEntity> getList(List<GetMessageEntity> list) {
        List<GetMessageEntity> showList = new ArrayList<>();
        try {
            String spId = PreferenceUtil.getString("groupTop");
            if(null == spId) {
                return list;
            }
            JSONArray array = new JSONArray(spId);
            for(int i = 0; i < array.length(); i++) {
                String id = array.getString(i);
                for(int j = 0; j < list.size(); j++) {
                    if(id.equalsIgnoreCase(list.get(j).getId())) {
                        showList.add(list.get(j));
                        list.remove(j);
                    }
                }
            }
            for(int i = 0; i < list.size(); i++) {
                showList.add(list.get(i));
            }
            if(showList.size() == 0) {
                showList = list;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return showList;
    }

    private List<String> getMuteList(List<GetMessageEntity> list) {
        List<String> showList = new ArrayList<>();
        try {
            String spId = PreferenceUtil.getString("mute");
            if(null == spId) {
                return null;
            }
            JSONArray array = new JSONArray(spId);
            for(int i = 0; i < array.length(); i++) {
                String id = array.getString(i);
                showList.add(id);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return showList;
    }
}
