package com.huxin.communication.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.huxin.communication.R;
import com.huxin.communication.adpter.MyPopWindowAdapter;
import com.huxin.communication.entity.MyPopVlaues;

import java.util.List;

/**
 * Created by pc on 2015/12/7.
 */
public class MyPopWindow extends PopupWindow {
    private View view;
    private List<MyPopVlaues> list;
    private MyPopWindowAdapter adapter;
    private ListView listView;

    public MyPopWindow(final Activity context, List<MyPopVlaues> list){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_mypopwindow, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(450);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(178);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
        this.list = list;
        listView = (ListView) view.findViewById(R.id.pop_listview);
        adapter = new MyPopWindowAdapter(context,list);
        listView.setAdapter(adapter);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        listView.setOnItemClickListener(listener);
    }
}
