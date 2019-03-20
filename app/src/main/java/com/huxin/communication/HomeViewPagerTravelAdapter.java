package com.huxin.communication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huxin.communication.entity.HomeEntity;
import com.huxin.communication.entity.HomeTravelEntity;
import com.huxin.communication.ui.house.details.ImageActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerTravelAdapter extends PagerAdapter {

    private Context mContext;
    private List<HomeTravelEntity.CarouselBean> mlist; // 图片资源ID数组
    private List<ImageView> mImageViews; // ImageView集合

    public HomeViewPagerTravelAdapter(Context context, List<HomeTravelEntity.CarouselBean> list){
        mContext = context;
        mlist = list;
        mImageViews = new ArrayList<>();
        initImageViews(mlist);
    }

    /**
     * 初始化ImageViews集合
     */
    private void initImageViews(List<HomeTravelEntity.CarouselBean> list) {

        // 根据图片资源数组填充ImageViews集合
        for(int i = 0 ; i < list.size() ; i++){
            ImageView mImageView = new ImageView(mContext);
//            mImageView.setImageResource(imageIds[i]);
            KyLog.d(list.get(i).getImg_url());
            ImageLoader.getInstance().displayImage( list.get(i).getImg_url(),mImageView);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageViews.add(mImageView);
        }

        // ImageViews集合中的图片个数在[2,3]时会存在问题，递归再次填充一遍
        if(mImageViews.size() > 1 && mImageViews.size() < 4){
            KyLog.d("=========");
            initImageViews(list);
        }
    }

    @Override
    public int getCount() {
        return mImageViews.size() <=1 ? mImageViews.size() : Short.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView mImageView = mImageViews.get(position % mImageViews.size());
        container.addView(mImageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ImageActivity.class);
                intent.putExtra("url",mlist.get(position % mImageViews.size()).getUrl());
                mContext.startActivity(intent);
            }
        });
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

}
