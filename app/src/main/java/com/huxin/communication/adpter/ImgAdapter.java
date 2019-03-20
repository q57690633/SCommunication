package com.huxin.communication.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huxin.communication.entity.BaseEntity;
import com.huxin.communication.http.NetConfig;
import com.huxin.communication.ui.travel.WebViewActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/9/13.
 */
public class ImgAdapter extends LoopVPAdapter<BaseEntity> {
    private ImageView imageView;

    public ImgAdapter(Context context, ArrayList<BaseEntity> datas, ViewPager viewPager) {
        super(context, datas, viewPager);
    }

    private ViewGroup.LayoutParams layoutParams;

    @Override
    protected View getItemView(BaseEntity data) {
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        imageView = new ImageView(mContext);
        imageView.setLayoutParams(layoutParams);
        imageView.setMaxWidth(375);
        imageView.setMaxHeight(150);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        KyLog.d(NetConfig.BASE_IMG_URL + data.getName());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        ImageUtils.loadImage(mContext, data, imageView);
        return imageView;
    }
}
