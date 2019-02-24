package com.huxin.communication.widgets.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.widgets.DensityUtil;

import java.util.ArrayList;

/**
 * Created by kyosky on 16/5/18.
 */
public class TabLayout extends LinearLayout implements View.OnClickListener {

    private ArrayList<Tab> mTabs;
    private OnTabClickListener mListener;
    private OnPreClickListener mPrelistener;

    private View mSelectView;
    private int mTabCount;

    private int mTabLabelColorDef;
    private int mTabLabelColorSelect;

    private int mTabLabelBgColorDef;
    private int mTabLabelBgColorSelect;

    public TabLayout(Context context) {
        super(context);
        initView();
    }


    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void setTabLabelColor(int resIdDef, int resIdSelect) {
        if (resIdDef > 0 && resIdSelect > 0) {
            mTabLabelColorDef = resIdDef;
            mTabLabelColorSelect = resIdSelect;
        }
    }

    public void setTabLabelBgColor(int resIdDef, int resIdSelect) {
        if (resIdDef > 0 && resIdSelect > 0) {
            mTabLabelBgColorDef = resIdDef;
            mTabLabelBgColorSelect = resIdSelect;
        }
    }

    private void initView() {
        setOrientation(HORIZONTAL);
//        setBackgroundResource(R.color.white);
    }

    public void setUpData(ArrayList<Tab> tabs, OnTabClickListener listener, OnPreClickListener preListener) {
        mTabs = tabs;
        mListener = listener;
        if (preListener != null) {
            mPrelistener = preListener;
        }
        if (tabs != null && tabs.size() > 0) {
            mTabCount = tabs.size();
            TabView tabView = null;
            LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER | Gravity.BOTTOM;
            for (Tab tag : tabs) {
//                if (!tag.getFragment().getSimpleName().equals("InformationFragment")) {
//                    System.out.println("true");
//                    params.topMargin = DensityUtil.dip2px(getContext(), 8.0f);
//                }
                System.out.println("tab === " + tag.getFragment().getSimpleName());
                tabView = new TabView(getContext());
                tabView.setUpData(tag);
                tabView.setTag(tag);
                tabView.setOnClickListener(this);
                addView(tabView, params);
                tabView = null;
            }
        } else {
            throw new IllegalArgumentException("tag can't be empty");
        }
    }


    public void setCurrentTab(int index) {

        if (0 <= index && index < mTabCount) {
            View view = getChildAt(index);
            onClick(view);
        }
    }

    public void onDataChanged(int index, int badgeCount) {
        if (index >= 0 && index < mTabCount) {
            TabView tabView = (TabView) getChildAt(index);
            tabView.onDataChanged(badgeCount);
        }
    }

    @Override
    public void onClick(View v) {

        if (mSelectView != v) {
            Tab tab = (Tab) v.getTag();
            if (mPrelistener != null) {
                if (!mPrelistener.onPreClick(tab)) {
                    return;
                }
            }
            mListener.onTabClick(tab);
            ((TabView) v).setLabelBgColor(mTabLabelBgColorSelect);
            ((TabView) v).setLabelColor(mTabLabelColorSelect);
            v.setSelected(true);
            if (mSelectView != null) {
                ((TabView) mSelectView).setLabelBgColor(mTabLabelBgColorDef);
                ((TabView) mSelectView).setLabelColor(mTabLabelColorDef);
                mSelectView.setSelected(false);
            }
            mSelectView = v;
        }
    }

    public interface OnTabClickListener {
        void onTabClick(Tab tag);
    }

    public interface OnPreClickListener {

        /**
         * 返回值 是否继续进行事件
         *
         * @param tab
         * @return
         */
        boolean onPreClick(Tab tab);
    }

    public static final class Tab {
        private int imgResId;
        private int labelResId;
        private int badgeCount;
        private Class fragment;

        public Tab(int imgResId, int labelResId) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
        }

        public Tab(int imgResId, int labelResId, Class fragment) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.fragment = fragment;
        }

        public Tab(int imgResId, int labelResId, int badgeCount) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.badgeCount = badgeCount;
        }

        public Class getFragment() {
            return fragment;
        }

        public void setFragment(Class fragment) {
            this.fragment = fragment;
        }

        public int getImgResId() {
            return imgResId;
        }

        public void setImgResId(int imgResId) {
            this.imgResId = imgResId;
        }

        public int getBadgeCount() {
            return badgeCount;
        }

        public void setBadgeCount(int badgeCount) {
            this.badgeCount = badgeCount;
        }

        public int getLabelResId() {
            return labelResId;
        }

        public void setLabelResId(int labelResId) {
            this.labelResId = labelResId;
        }

    }

    class TabView extends LinearLayout {

        private ImageView mTabImage;
        private TextView mTabLabel;


        public TabView(Context context) {
            super(context);
            initView(context);
        }


        public TabView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView(context);
        }

        public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView(context);
        }

        private void initView(Context context) {
            LayoutInflater.from(context).inflate(R.layout.widget_tab_view, this, true);
            setOrientation(VERTICAL);
            setGravity(Gravity.CENTER | Gravity.BOTTOM);

            int padding = DensityUtil.dip2px(getContext(), 8.0f);
            setPadding(padding, padding, padding, padding / 2);
            mTabImage = (ImageView) findViewById(R.id.tabImage);
            mTabLabel = (TextView) findViewById(R.id.tabLabel);

        }


        public void setLabelColor(int resId) {
            if (resId > 0) {
                mTabLabel.setTextColor(getContext().getResources().getColor(resId));
            }
        }

        public void setLabelBgColor(int resId) {
            if (resId > 0) {
                setBackgroundResource(resId);
            }
        }

        public void setUpData(Tab tab) {
            mTabImage.setBackgroundResource(tab.getImgResId());
            mTabLabel.setText(tab.getLabelResId());
        }

        public void onDataChanged(int badgeCount) {
            //TODO UPDATE badgeCount
        }

    }
}
