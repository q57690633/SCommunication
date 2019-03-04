package com.huxin.communication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.huxin.communication.adpter.HeadTravelAdapter;
import com.huxin.communication.adpter.HeadTravelJinWaiAdapter;
import com.huxin.communication.adpter.HeadTravelTicketAdapter;
import com.huxin.communication.adpter.HomeViewPagerAdapter;
import com.huxin.communication.adpter.RecyclerHomeAdpter;
import com.huxin.communication.base.BaseFragment;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.entity.GetMessageEntity;
import com.huxin.communication.entity.HomeEntity;
import com.huxin.communication.entity.HomeTravelEntity;
import com.huxin.communication.entity.InlandCityEntity;
import com.huxin.communication.entity.ProvinceEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.listener.GetMessageListener;
import com.huxin.communication.ui.InvitationActivity;
import com.huxin.communication.ui.KeFuActivity;
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
import com.huxin.communication.view.AutoScrollLayoutManager;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.conversation.Conversation;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import com.tencent.imsdk.ext.message.TIMManagerExt;

import java.util.ArrayList;
import java.util.List;

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
    private HeadHouseAdapter mHeadLineAdapter;
    private HeadTravelAdapter mHeadTravelAdapter;
    private HeadTravelJinWaiAdapter mHeadTravelJinWaiAdapter;
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
    public void onResume() {
        super.onResume();

//        getTIMmsg();
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
        TIMManager.getInstance().addMessageListener(this);
        if (PreferenceUtil.getInt("type") == 1) {
            initData();
            getConversationList();
        } else {
            initDataTravel();
            getProvinces();
        }

//        mRecyclerViewHead.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    // 如果自动滑动到最后一个位置，则此处状态为SCROLL_STATE_IDLE
//                    AutoScrollLayoutManager lm = (AutoScrollLayoutManager) recyclerView
//                            .getLayoutManager();
//
//                    int position = lm.findLastCompletelyVisibleItemPosition();
//                    int count = lm.getItemCount();
//                    if (position == count - 1) {
//                        lm.scrollToPosition(0);
//                        mRecyclerViewHead.smoothScrollToPosition(mHeadLineAdapter.getItemCount());
//                    }
//                }
//
//
//            }
//        });


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
                            AutoScrollLayoutManager manager = new AutoScrollLayoutManager(getContext());
                            mHeadLineAdapter = new HeadHouseAdapter(homeEntity.getHeadLine(), getContext());
                            mRecyclerViewHead.setAdapter(mHeadLineAdapter);
                            mRecyclerViewHead.setLayoutManager(manager);
                            mRecyclerViewHead.addItemDecoration(new SpaceItemDecoration(0, 15));
                        }
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(getContext(), throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void initDataTravel() {
//        showProgressDialog();
        ApiModule.getInstance().getTravelHome(PreferenceUtil.getString(Constanst.CITY_NAME))
                .subscribe(homeTravelEntity -> {
//                    cancelProgressDialog();
                    if (homeTravelEntity != null) {
                        KyLog.d(homeTravelEntity.getCarousel().size() + "");
                        setTravelOnBinner(homeTravelEntity.getCarousel());
                        if (homeTravelEntity.getAroundHead() != null && homeTravelEntity.getAroundHead().size() > 0) {
                            AutoScrollLayoutManager manager = new AutoScrollLayoutManager(getContext());
                            mHeadTravelAdapter = new HeadTravelAdapter(homeTravelEntity.getAroundHead(), getContext());
                            mRecyclerViewHead.setAdapter(mHeadLineAdapter);
                            mRecyclerViewHead.setLayoutManager(manager);
                            mRecyclerViewHead.addItemDecoration(new SpaceItemDecoration(0, 15));
                        }

                        if (homeTravelEntity.getForeignHead() != null && homeTravelEntity.getForeignHead().size() > 0) {
                            LinearLayoutManager manager = new LinearLayoutManager(getContext());
                            mHeadTravelJinWaiAdapter = new HeadTravelJinWaiAdapter(homeTravelEntity.getForeignHead(), getContext());
                            mRecyclerViewHead.setAdapter(mHeadLineAdapter);
                            mRecyclerViewHead.setLayoutManager(manager);
                            mRecyclerViewHead.addItemDecoration(new SpaceItemDecoration(0, 15));
                        }

                        if (homeTravelEntity.getTicketHead() != null && homeTravelEntity.getTicketHead().size() > 0) {
                            LinearLayoutManager manager = new LinearLayoutManager(getContext());
                            mTravelTicketAdapter = new HeadTravelTicketAdapter(homeTravelEntity.getTicketHead(), getContext());
                            mRecyclerViewHead.setAdapter(mHeadLineAdapter);
                            mRecyclerViewHead.setLayoutManager(manager);
                            mRecyclerViewHead.addItemDecoration(new SpaceItemDecoration(0, 15));
                        }

                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
//                    cancelProgressDialog();
                    Toast.makeText(getContext(), throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
    public void getMessage(List<GetMessageEntity> list) {
        KyLog.object("login ==------ list----- " + list);
        if (list.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mAdpter = new RecyclerHomeAdpter(list, getContext());
            mRecyclerView.setAdapter(mAdpter);
            mRecyclerView.setLayoutManager(manager);
//            mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
        }
    }

    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        KyLog.object("login ==------ list----- " + list);

        List<GetMessageEntity> lists = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String text = "";
            TIMMessage message = list.get(i);
            if (i == 0) {
                TIMElem elem = message.getElement(0);
                if (elem.getType() == TIMElemType.Text) {
                    TIMTextElem e = (TIMTextElem) elem;
                    text = e.getText();
                }
            }
            String sender = message.getSender();
            String faceUrl = message.getSenderProfile().getFaceUrl();
            TIMConversationType conversationType = message.getConversation().getType();
            int type = conversationType.value();
            long timeStamp = message.timestamp();
            TIMConversation con = TIMManager.getInstance().getConversation(TIMConversationType.C2C, message.getConversation().getPeer());
            TIMConversationExt conExt = new TIMConversationExt(con);
            long count = conExt.getUnreadMessageNum();
            KyLog.d("text == " + text);
            KyLog.d("sender == " + sender);
            KyLog.d("faceUrl == " + faceUrl);
            KyLog.d("type == " + type);
            KyLog.d("timeStamp == " + timeStamp);

            GetMessageEntity entity = new GetMessageEntity();
            entity.setHead_url(faceUrl);
            entity.setId(Integer.parseInt(sender));
            entity.setMsg(text);
            entity.setNum((int) count);
            entity.setTimeStamp(timeStamp);
            entity.setType(type);
            lists.add(entity);
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
        KyLog.d(ConversationList.size() + " === home");
        List<GetMessageEntity> list = new ArrayList<>();

        if (ConversationList != null && ConversationList.size() > 0) {
            for (TIMConversation conversation : ConversationList) {
                KyLog.d(conversation.getPeer() + " === home");
                getLocalMessage(conversation.getPeer(), list, conversation.getType());
            }
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    GetMsgManager msgManager = GetMsgManager.instants();
//                    msgManager.setList(list);
//                }
//            }, 3000);
        }
    }

    private void getLocalMessage(String groupId, List<GetMessageEntity> list, TIMConversationType type) {
        if (type == TIMConversationType.C2C) {
            getC2CLocalMessage(groupId, list);
        } else if (type == TIMConversationType.Group) {
            getC2CLocalMessage(groupId, list);

        }
    }

    private void getC2CLocalMessage(String groupId, List<GetMessageEntity> list) {
        TIMConversation con = TIMManager.getInstance().getConversation(TIMConversationType.C2C, groupId);
        TIMConversationExt conExt = new TIMConversationExt(con);

//获取此会话的消息
        conExt.getLocalMessage(20, //获取此会话最近的 10 条消息
                conExt.getLastMsg(), //不指定从哪条消息开始获取 - 等同于从最新的消息开始往前
                new TIMValueCallBack<List<TIMMessage>>() {//回调接口
                    @Override
                    public void onError(int code, String desc) {//获取消息失败
                        //接口返回了错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 含义请参见错误码表
                        KyLog.d("home", "get message failed. code: " + code + " errmsg: " + desc);
                    }

                    @Override
                    public void onSuccess(List<TIMMessage> msgs) {//获取消息成功
                        KyLog.d("success == " + msgs);
                        //遍历取得的消息
                        TIMMessage message = null;
                        String text = "";
                        if (msgs.size() > 0) {
                            for (int i = 0; i < msgs.size(); i++) {

                                message = msgs.get(i);
                                if (i == 0) {
                                    TIMElem elem = message.getElement(0);
                                    if (elem.getType() == TIMElemType.Text) {
                                        TIMTextElem e = (TIMTextElem) elem;
                                        text = e.getText();
                                    }
                                }
                            }
                            String sender = message.getSender();
                            String faceUrl = message.getSenderProfile().getFaceUrl();
                            TIMConversationType conversationType = message.getConversation().getType();
                            int type = conversationType.value();
                            long timeStamp = message.timestamp();
                            TIMConversation con = TIMManager.getInstance().getConversation(TIMConversationType.C2C, message.getConversation().getPeer());
                            TIMConversationExt conExt = new TIMConversationExt(con);
                            long count = conExt.getUnreadMessageNum();
                            GetMessageEntity entity = new GetMessageEntity();
                            entity.setHead_url(faceUrl);
                            entity.setId(Integer.parseInt(sender));
                            entity.setMsg(text);
                            entity.setNum((int) count);
                            entity.setTimeStamp(timeStamp);
                            entity.setType(type);
                            list.add(entity);
                            KyLog.d(list.size() + "");

                            GetMsgManager msgManager = GetMsgManager.instants();
                            msgManager.setList(list);
                        }
                    }
                });
    }
}
