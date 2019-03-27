package com.huxin.communication.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.huxin.communication.R;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.ui.TIMChatActivity;
import com.huxin.communication.ui.travel.WebViewActivity;
import com.huxin.communication.ui.travel.details.ZhouBianDetailsActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.view.SpaceItemDecoration;
import com.huxin.communication.view.recycler.RecyclerOnScrollerListener;
import com.sky.kylog.KyLog;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/16.
 */

public class CaiXianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AroundTravelEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TableNameAdapter mAdapterTableName;

    private RecyclerOnScrollerListener mOnScrollListener;

    private static final int VIEW_TYPE_CONTENT = 0;
    private static final int VIEW_TYPE_FOOTER = 1;
    private boolean isCanLoadMore = true;

    private Animation rotateAnimation;

    private static final int PER_PAGE = 10;

    public CaiXianAdapter(List<AroundTravelEntity.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }
        if (rotateAnimation == null) {
            rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.loading);
            rotateAnimation.setInterpolator(new LinearInterpolator());
        }

        if (viewType == VIEW_TYPE_CONTENT) {
        View view = mInflater.inflate(R.layout.item_zhoubian_recycler, parent, false);
        MyViewHoder hoder = new MyViewHoder(view);
        hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ZhouBianDetailsActivity.class);
                intent.putExtra("list", list.get(hoder.getAdapterPosition()));
                intent.putExtra("type",2);

                mContext.startActivity(intent);
            }
        });

        hoder.mTextViewKanxingcheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url", list.get(hoder.getAdapterPosition()).getQrCode_url());
                mContext.startActivity(intent);
            }
        });
        hoder.mTextViewSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = PreferenceUtil.getInt("uid") + "";
                String userSig = PreferenceUtil.getString("usersig");
                if (!userId.equals(String.valueOf(list.get(hoder.getAdapterPosition()).getUid()))){
                    String userName = list.get(hoder.getAdapterPosition()).getUsername();
                    onRecvUserSig(userName, userId, userSig, String.valueOf(list.get(hoder.getAdapterPosition()).getUid()));
                }else {
                    Toast.makeText(mContext, "用户id一样，不能进行聊天", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return hoder;

        } else {

            return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_footer, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_CONTENT) {
            ((MyViewHoder) holder).mTextViewDepartName.setText(list.get(position).getDepart_name());
            ((MyViewHoder) holder).mTextViewUsername.setText(list.get(position).getUsername());
            ((MyViewHoder) holder).mTextViewUserCity.setText(list.get(position).getUserCity());
            ((MyViewHoder) holder).mTextViewGoalsCity.setText(list.get(position).getGoals_city());
            ((MyViewHoder) holder).mTextViewCompany.setText(list.get(position).getCompanyName());
            ((MyViewHoder) holder).mTextViewNumberDays.setText("行程天数：" + list.get(position).getNumberDays() + "天");
            ((MyViewHoder) holder).mTextViewTotalPrice.setText("成人：" + list.get(position).getTotalPrice() + "元");
            ((MyViewHoder) holder).mTextViewReturnPrice.setText("返" + list.get(position).getReturnPrice() + "元");
            ((MyViewHoder) holder).mTextViewTotalPriceChild.setText("儿童：" + list.get(position).getTotalPriceChild() + "元");
            ((MyViewHoder) holder).mTextViewReturnPriceChild.setText("返" + list.get(position).getReturnPriceChild() + "元");
            ((MyViewHoder) holder).mTextViewSpotName.setText(list.get(position).getTravelTitle());
            ((MyViewHoder) holder).mTextViewCount.setText("以浏览" + list.get(position).getView_count() + "次");
            Glide.with(mContext).load(list.get(position)
                    .getPhoto_url())
                    .into(((MyViewHoder) holder).mImageViewPhoto);
            Glide.with(mContext).load(list.get(position)
                    .getHeadUrl())
                    .into(((MyViewHoder) holder).mImageViewHeadUrl);



            if (!TextUtils.isEmpty(list.get(position).getTagName())) {
                setTextView(list, position, ((MyViewHoder) holder).mRecyclerView);
            }

            if (TextUtils.isEmpty(list.get(position).getQrCode_url())) {
                ((MyViewHoder) holder).mTextViewKanxingcheng.setVisibility(View.GONE);
            } else {
                ((MyViewHoder) holder).mTextViewKanxingcheng.setVisibility(View.VISIBLE);

            }

            if (list.get(position).getStick_hot() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.sign_hot);
            }
            if (list.get(position).getStick_low() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.sign_tejia);
            }
            if (list.get(position).getStick_new() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.sign_shangxin);
            }
            if (list.get(position).getStick_return() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.sign_gaofanyong);
            }
            if (list.get(position).getStick_zeroC() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.sign_ziwei);
            }
            if (list.get(position).getStick_better() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.sign_jingpin);
            }
            if (list.get(position).getStick_rate() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.sign_xingjiabi);
            }
            if (list.get(position).getStick_throw() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.sign_shuaiwei);
            }

        }else {
            if (isCanLoadMore) {
                ((FooterViewHolder) holder).showLoading();
            } else {
                ((FooterViewHolder) holder).showTextOnly("无更多数据");
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_CONTENT;
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        private LinearLayout mLinearLayout;
        private ImageView mImageViewPhoto;
        private ImageView mImageViewStickName;
        private ImageView mImageViewHeadUrl;
        private TextView mTextViewUsername;
        private TextView mTextViewUserCity;
        private TextView mTextViewDepartName;
        private TextView mTextViewGoalsCity;
        private TextView mTextViewNumberDays;
        private TextView mTextViewTotalPrice;
        private TextView mTextViewReturnPrice;
        private TextView mTextViewTotalPriceChild;
        private TextView mTextViewReturnPriceChild;
        private TextView mTextViewSpotName;
        private TextView mTextViewCompany;
        private TextView mTextViewKanxingcheng;
        private TextView mTextViewSendMessage;

        private RecyclerView mRecyclerView;
        private TextView mTextViewCount;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.zou_bian_line);
            mImageViewPhoto = (ImageView) itemView.findViewById(R.id.image_photo_url);
            mImageViewStickName = (ImageView) itemView.findViewById(R.id.stick_name);
            mImageViewHeadUrl = (ImageView) itemView.findViewById(R.id.headUrl);
            mTextViewUsername = (TextView) itemView.findViewById(R.id.username);
            mTextViewGoalsCity = (TextView) itemView.findViewById(R.id.goals_city);
            mTextViewUserCity = (TextView) itemView.findViewById(R.id.userCity);
            mTextViewDepartName = (TextView) itemView.findViewById(R.id.depart_name);
            mTextViewNumberDays = (TextView) itemView.findViewById(R.id.numberDays);
            mTextViewTotalPrice = (TextView) itemView.findViewById(R.id.totalPrice);
            mTextViewReturnPrice = (TextView) itemView.findViewById(R.id.returnPrice);
            mTextViewTotalPriceChild = (TextView) itemView.findViewById(R.id.totalPriceChild);
            mTextViewReturnPriceChild = (TextView) itemView.findViewById(R.id.returnPriceChild);
            mTextViewSpotName = (TextView) itemView.findViewById(R.id.spotName);
            mTextViewKanxingcheng = (TextView) itemView.findViewById(R.id.kanxingcheng);
            mTextViewSendMessage = (TextView) itemView.findViewById(R.id.sendMessage);
            mTextViewCompany = itemView.findViewById(R.id.company_name);
            mTextViewCount = itemView.findViewById(R.id.view_count);


            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_travel);
        }
    }

    private void setTextView(List<AroundTravelEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        if (!TextUtils.isEmpty(list.get(position).getTagName())) {

            String[] strings = list.get(position).getTagName().split(",");
            KyLog.d(list.get(position).getTagName());
            for (int i = 0; i < strings.length; i++) {
                list1.add(strings[i]);
            }
        }
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 2);
            mAdapterTableName = new TableNameAdapter(list1, mContext);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
//            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


    }

    private void onRecvUserSig(String username, String userId, String userSig, String targetId) {
        TUIKit.login(userId, userSig, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                KyLog.i("imlogin onSuccess", data);
                Intent intent = new Intent(mContext, TIMChatActivity.class);
                intent.putExtra("TARGET_ID", targetId);
                intent.putExtra("username", username);
                mContext.startActivity(intent);
            }
            @Override
            public void onError(String module, int errCode, String errMsg) {
                Toast.makeText(mContext, "用户Id == " + userId + " \n"+"imlogin fail" + errMsg
                        + " \n"+"imlogin fail" + userSig, Toast.LENGTH_SHORT).show();
                KyLog.e("imlogin fail", errMsg);
            }
        });
    }

    //底部的FooterView
    class FooterViewHolder extends RecyclerView.ViewHolder {

        ImageView ivLoading = itemView.findViewById(R.id.iv_loading);
        TextView tvLoading = itemView.findViewById(R.id.tv_loading);

        public FooterViewHolder(View itemView) {
            super(itemView);
        }

        void showTextOnly(String s) {
            Log.d("mytest", "showTextOnly: " + s);
            ivLoading.setVisibility(View.INVISIBLE);
            tvLoading.setText(s);
        }

        void showLoading() {
            Log.i("mytest", "show loading");
            ivLoading.setImageResource(R.drawable.loading);
            tvLoading.setText("正在加载...");
            if (ivLoading != null) {
                ivLoading.startAnimation(rotateAnimation);
            }
        }

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mOnScrollListener = new RecyclerOnScrollerListener(recyclerView) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.i("loadingtest", "currentPage: " + currentPage);
                mOnLoadMoreListener.onLoadMore(currentPage);
            }
        };
        recyclerView.addOnScrollListener(mOnScrollListener);
//        mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//            }
//        };
        //初始化的时候如果未填满一页，那么肯定就没有更多数据了
        if (list.size() < PER_PAGE) {
            setCanLoadMore(false);
        } else {
            setCanLoadMore(true);
        }
    }


    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mOnScrollListener != null) {
            recyclerView.removeOnScrollListener(mOnScrollListener);
        }
//        if (mAdapterDataObserver != null) {
//            unregisterAdapterDataObserver(mAdapterDataObserver);
//        }
        mOnScrollListener = null;
    }

    public void setData(List<AroundTravelEntity.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /*
     * 数据加载完毕时执行setCanLoadMore()，此时isLoading都置为false
     * */
    public void setCanLoadMore(boolean isCanLoadMore) {
        this.isCanLoadMore = isCanLoadMore;
        mOnScrollListener.setCanLoadMore(isCanLoadMore);
        mOnScrollListener.setLoading(false);
    }


    public interface OnLoadMoreListener {
        void onLoadMore(int currentPage);
    }

    private ZhouBianAdapter.OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(ZhouBianAdapter.OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }
}
