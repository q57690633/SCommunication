package com.huxin.communication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.adpter.ViewPagerAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.entity.AroundTravelEntity;
import com.huxin.communication.ui.LoginActivity;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends BaseActivity {

    private ViewPagerAdapter mViewPagerAdapter;

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

    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_start);


    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.start_viewPager);

    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        KyLog.d(PreferenceUtil.getString("start"));
        if (TextUtils.isEmpty(PreferenceUtil.getString("start"))){
            setOnBinner();
        }else {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        PreferenceUtil.putString("start","start");
    }


    /**
     * 加载binner图
     */
    private void setOnBinner() {
        KyLog.d(setData().size() + "");
        if (setData().size() > 0) {
            mViewPagerAdapter = new ViewPagerAdapter(this, setData());
            mViewPager.setAdapter(mViewPagerAdapter);

//            if (setData().size() > 1) {
//                mViewPager.setCurrentItem(((Short.MAX_VALUE / 2) / setData().size()) * setData().size(), false);
//            }
//            setCurPage(0 % imageList.size(), imageList.size());

//            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                }
//
//                @Override
//                public void onPageSelected(int position) {
////                    big_index = position;
////                    setCurPage(position % imageList.size(), imageList.size());
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
        }
    }

    private List<Integer> setData(){
        List<Integer> imageList = new ArrayList<>();
        imageList.add(new Integer(R.drawable.starts));
        imageList.add(new Integer(R.drawable.starts2));
        imageList.add(new Integer(R.drawable.starts1));


        return imageList;
    }


    public class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;
        private List<Integer> mlist; // 图片资源ID数组
        private List<ImageView> mImageViews; // ImageView集合

        public ViewPagerAdapter(Context context, List<Integer> list){
            mContext = context;
            mlist = list;
            mImageViews = new ArrayList<>();
            initImageViews(mlist);
        }

        /**
         * 初始化ImageViews集合
         */
        private void initImageViews(List<Integer> list) {

            // 根据图片资源数组填充ImageViews集合
            for(int i = 0 ; i < list.size() ; i++){
                ImageView mImageView = new ImageView(mContext);
//            mImageView.setImageResource(imageIds[i]);
//                ImageLoader.getInstance().displayImage( list.get(i),mImageView);
                mImageView.setBackgroundResource(list.get(i));
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
            return mImageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView mImageView = mImageViews.get(position);
            container.addView(mImageView);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    KyLog.d(position + "");
                    if (position == 2){
                        Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                        finish();
                        startActivity(intent);
                        finish();
                    }
                }
            });
            return mImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

}
