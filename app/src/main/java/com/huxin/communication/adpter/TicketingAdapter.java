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

import com.bumptech.glide.Glide;
import com.huxin.communication.R;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.entity.TicketInfoEntity;
import com.huxin.communication.ui.travel.details.JinWaiDetailsActivity;
import com.huxin.communication.ui.travel.details.TicketingDetailsActivity;
import com.huxin.communication.ui.travel.release.ReleaseTicketingActivity;
import com.huxin.communication.ui.travel.release.ReleaseZhouBoundaryActivity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.huxin.communication.view.recycler.RecyclerOnScrollerListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/16.
 */

public class TicketingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TicketInfoEntity.ListBean> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private TicketTableNameAdapter mAdapterTableName;
    private int type;

    private RecyclerOnScrollerListener mOnScrollListener;

    private static final int VIEW_TYPE_CONTENT = 0;
    private static final int VIEW_TYPE_FOOTER = 1;
    private boolean isCanLoadMore = true;

    private Animation rotateAnimation;

    private static final int PER_PAGE = 10;

    public TicketingAdapter(List<TicketInfoEntity.ListBean> list, Context mContext, int type) {
        this.list = list;
        this.mContext = mContext;
        this.type = type;

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
            View view = mInflater.inflate(R.layout.item_ticketing_recycler, parent, false);
            MyViewHoder hoder = new MyViewHoder(view);
            hoder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type == 1) {
                        Intent intent = new Intent(mContext, TicketingDetailsActivity.class);
                        intent.putExtra("list", list.get(hoder.getAdapterPosition()));
                        mContext.startActivity(intent);
                    } else if (type == 2) {
                        Intent intents = new Intent(mContext, ReleaseTicketingActivity.class);
                        intents.putExtra("list", list.get(hoder.getAdapterPosition()));
                        intents.putExtra("id", list.get(hoder.getAdapterPosition()).getId());
                        mContext.startActivity(intents);
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

            ((MyViewHoder) holder).mTextViewAddr.setText(list.get(position).getTicket_addr());
            ((MyViewHoder) holder).mTextViewName.setText(list.get(position).getTicket_name());
            ((MyViewHoder) holder).mTextViewOriginalPrice.setText(String.valueOf(list.get(position).getOriginal_price()) + "元");

            Glide.with(mContext).load(list.get(position)
                    .getPhoto_url())
                    .into(((MyViewHoder) holder).mImageViewAddr);

            ((MyViewHoder) holder).mTextViewCount.setText("以浏览" + list.get(position).getView_count() + "次");

            if (!TextUtils.isEmpty(list.get(position).getTagName())) {
                setTextView(list, position, ((MyViewHoder) holder).mRecyclerView);
            }
            if (list.get(position).getStick_hot() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.hot);
            }
            if (list.get(position).getStick_low() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.tejia);
            }
            if (list.get(position).getStick_new() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.shangxin);
            }
            if (list.get(position).getStick_return() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.gaofanyong);
            }
            if (list.get(position).getStick_zeroC() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.zifei);
            }
            if (list.get(position).getStick_better() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.jingpin);
            }
            if (list.get(position).getStick_rate() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.xingjiabi);
            }
            if (list.get(position).getStick_throw() == 1) {
                ((MyViewHoder) holder).mImageViewStickName.setBackgroundResource(R.drawable.shuaiwei);
            }

        } else {
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
        private ImageView mImageViewAddr;
        private ImageView mImageViewStickName;
        private TextView mTextViewName;
        private TextView mTextViewOriginalPrice;
        private TextView mTextViewAddr;
        private TextView mTextViewCount;


        private RecyclerView mRecyclerView;

        public MyViewHoder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.ticketing_line);
            mImageViewAddr = (ImageView) itemView.findViewById(R.id.image_ticket_addr);
            mTextViewName = (TextView) itemView.findViewById(R.id.ticket_name);
            mTextViewAddr = (TextView) itemView.findViewById(R.id.ticket_addr);
            mTextViewOriginalPrice = (TextView) itemView.findViewById(R.id.original_price);

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_ticket);

            mImageViewStickName = itemView.findViewById(R.id.image_stick);
            mTextViewCount = itemView.findViewById(R.id.view_count);

        }
    }

    private void setTextView(List<TicketInfoEntity.ListBean> list, int position, RecyclerView linearLayout) {
        List<String> list1 = new ArrayList<>();
        if (!TextUtils.isEmpty(list.get(position).getTagName())) {

            String[] strings = list.get(position).getTagName().split(",");
            KyLog.d(list.get(position).getTagName());
            for (int i = 0; i < strings.length; i++) {
                list1.add(strings[i]);
            }
        }
        if (list1.size() > 0) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 3);
            mAdapterTableName = new TicketTableNameAdapter(list1, mContext);
            linearLayout.setAdapter(mAdapterTableName);
            linearLayout.setLayoutManager(manager);
//            linearLayout.addItemDecoration(new SpaceItemDecoration(0, 15));
        }


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

    public void setData(List<TicketInfoEntity.ListBean> list) {
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
