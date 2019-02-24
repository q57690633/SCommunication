package com.huxin.communication.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.utils.PreferenceUtil;


/**
 * Activity基础类
 * Created by kyosky on 16/4/11.
 */
public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    public static final String TOKEN = "token";
    public static final String UID = "uid";
    public static final String PID = "PID";
    public static final String PHONE = "phone";
    public static final String IS_BINDPHONE = "is_bind_phone";

    protected static final int MODE_NOR = 0;
    protected static final int MODE_BACK = 1;
    protected static final int MODE_DRAWER = 2;
    protected static final int MODE_LFET = 3;
    protected static final int MODE_GOLD_RIGHT = 4;
    protected static final int MODE_REWARD_RIGHT = 5;
    protected static final int MODE_COLOR_GOLD = 5;
    protected static final int MODE_COLOR_REWARD = 6;
    protected static final int MODE_COLOR_SILVER = 7;
    protected static final int MODE_COLOR_DRILING = 8;
    protected Toolbar toolbar;
    protected TextView toolbarTitle;
    protected TextView toolbarTitleRight;
    protected ProgressDialog mProgressDialog;
    protected boolean titleCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        initContentView();
        if (getStatusBarColor() != 0) {
//            StatusBarCompat.setStatusBarColor(this, getStatusBarColor());
        }
        initViews();
        loadData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mProgressDialog != null) {
            mProgressDialog.cancel();
            mProgressDialog = null;
        }
    }

    protected void setToolbar(String resStr, int resId, int menuId, int mode, int position, int color, boolean showSettings) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitleRight = (TextView) findViewById(R.id.toolbar_right);

        if (!TextUtils.isEmpty(resStr)) {
            setTitleResString(resStr);
        } else {
            setTitleResId(resId);
        }
        switch (mode) {
            case MODE_NOR:
                break;
            case MODE_BACK:
                toolbar.setNavigationIcon(R.drawable.nav_icon_back2);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onNavigationBtnClicked();
                    }
                });
                break;
            case MODE_DRAWER:

                break;
        }

        switch (position) {
            case MODE_LFET:
                break;
            case MODE_GOLD_RIGHT:
//                toolbarTitleRight.setText("提现记录");
//                toolbarTitleRight.setVisibility(View.VISIBLE);
//                toolbarTitleRight.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(BaseActivity.this, GoldRecordActivity.class);
//                        startActivity(intent);
//                    }
//                });
                break;
            case MODE_REWARD_RIGHT:
//                toolbarTitleRight.setText("提现记录");
//                toolbarTitleRight.setVisibility(View.VISIBLE);
//                toolbarTitleRight.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(BaseActivity.this, RewardRecordActivity.class);
//                        startActivity(intent);
//                    }
//                });
                break;
        }
        switch (color) {
//            case MODE_COLOR_DRILING:
//                toolbar.setBackgroundResource(R.color.driling_bg);
//                break;
//            case MODE_COLOR_GOLD:
//                toolbar.setBackgroundResource(R.color.gold_bg);
//                break;
//            case MODE_COLOR_REWARD:
//                toolbar.setBackgroundResource(R.color.reward_bg);
//                break;
//            case MODE_COLOR_SILVER:
//                toolbar.setBackgroundResource(R.color.silver_bg);

//                break;
        }

        if (showSettings) {
            setUpMenu(menuId);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    protected void setToolbar(int resId) {
        setToolbar(null, resId, -1, MODE_NOR, -1, -1, false);
    }

    protected void setToolbar(int resId, int menuId) {
        setToolbar(null, resId, menuId, MODE_NOR, -1, -1, false);
    }

    protected void setToolbarMode(int resId, int mode) {
        setToolbar(null, resId, -1, mode, -1, -1, false);
    }

    protected void setToolbarCenter(int resId) {
        this.titleCenter = true;
        setToolbar(null, resId, -1, MODE_NOR, -1, -1, false);
    }

    protected void setToolbarCenterMode(int resId, int mode) {
        this.titleCenter = true;
        setToolbar(null, resId, -1, mode, -1, -1, false);
    }

    protected void setToolbarCenterMode(String resString, int mode) {
        this.titleCenter = true;
        setToolbar(resString, -1, -1, mode, -1, -1, false);
    }

    protected void setToolbarCenter(int resId, int menuId) {
        this.titleCenter = true;
        setToolbar(null, resId, menuId, MODE_NOR, -1, -1, false);
    }

    protected void setToolbarCenter(int resId, int mode, int position) {
        this.titleCenter = true;
        setToolbar(null, resId, -1, mode, position, -1, false);
    }

    protected void setToolbarColorCenter(String resString, int mode, int color) {
        this.titleCenter = true;
        setToolbar(resString, -1, -1, mode, -1, color, false);
    }

//    protected void setToolbarCenter(int resId, int menuId, int mode) {
//        this.titleCenter = true;
//        setToolbar(null, resId, menuId, mode, -1, false);
//    }

    protected void setToolbarCenter(int resId, int menuId, int mode, boolean showSettings) {
        this.titleCenter = true;
        setToolbar(null, resId, menuId, mode, -1, -1, showSettings);
    }

    /**
     * 加载弹框
     */
    protected void showProgressDialog() {
        showProgressDialog(true);
    }

    protected void showProgressDialog(boolean isCancel) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(isCancel);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    /**
     * 取消加载弹框
     */
    protected void cancelProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

//    /**
//     * 统一toast管理
//     * @param msg
//     */
//    protected void showToastLong(String msg) {
//        Toast.makeText(gContext, msg, Toast.LENGTH_LONG).show();
//    }
//
//    protected void showToastLong(int resId) {
//        Toast.makeText(gContext, resId, Toast.LENGTH_LONG).show();
//    }
//
//    protected void showToastShort(String msg) {
//        Toast.makeText(gContext, msg, Toast.LENGTH_SHORT).show();
//    }
//
//    protected void showToastShort(int resId) {
//        Toast.makeText(gContext, resId, Toast.LENGTH_SHORT).show();
//    }

    protected void setUpMenu(int menuId) {
        if (toolbar != null) {
            if (menuId > 0) {
                toolbar.getMenu().clear();
                toolbar.inflateMenu(menuId);
                toolbar.setOnMenuItemClickListener(this);
            }
        }
    }


    private void setTitleResId(int titleResId) {

        if (titleResId >= 0) {
            if (titleCenter && toolbarTitle != null) {
                toolbarTitle.setText(titleResId);
                toolbarTitle.setVisibility(View.VISIBLE);
                toolbar.setTitle("");
            } else {
                toolbar.setTitle(titleResId);
            }
        }
    }

    private void setTitleResString(String titleResId) {

        if (!TextUtils.isEmpty(titleResId)) {
            if (titleCenter && toolbarTitle != null) {
                toolbarTitle.setText(titleResId);
                toolbarTitle.setVisibility(View.VISIBLE);
                toolbar.setTitle("");
            } else {
                toolbar.setTitle(titleResId);
            }
        }
    }

    protected abstract void initContentView();

    protected abstract void initViews();

    protected abstract void loadData(Bundle savedInstanceState);


    protected void onNavigationBtnClicked() {
        finish();
    }

    /**
     * 获取状态栏颜色
     *
     * @return default -1
     */
    protected int getStatusBarColor() {

        return 0;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

//    private double getScreenSizeOfDevice() {
//        Point point = new Point();
//        getWindowManager().getDefaultDisplay().getRealSize(point);
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        double x = Math.pow(point.x / dm.xdpi, 2);
//        double y = Math.pow(point.y / dm.ydpi, 2);
//        return Math.sqrt(x + y);
//    }

    //    public void loadImage(String path,ImageView view) {
//        Observable.create(new Observable.OnSubscribe<Bitmap>() {
//            @Override
//            public void call(Subscriber<? super Bitmap> subscriber) {
//                Bitmap bitmap = null;
//                //创建一个url对象
//                try {
//                    URL url = new URL(path);
//                    //打开URL对应的资源输入流
//                    InputStream is = url.openStream();
//                    //从InputStream流中解析出图片
//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    options.inJustDecodeBounds = true;
//                    bitmap = BitmapFactory.decodeStream(is,null,options);
//                    options = calculateSize(options);
//                    is.close();
//                    is = url.openStream();
//                    bitmap = BitmapFactory.decodeStream(is,null,options);
//                    //发送消息，通知UI组件显示图片
//                    //关闭输入流
//                    is.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                subscriber.onNext(bitmap);
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).
//                subscribeOn(Schedulers.io()).
//                subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//                        if (bitmap != null) {
//                            view.setImageBitmap(bitmap);
////                            calculateSize(path,bitmap,view);
//                        }
//                    }
//                });
//    }
//    public double getPPI() {
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenWidth = dm.widthPixels;
//        int screenHeight = dm.heightPixels;
//        return Math.sqrt(screenWidth * screenWidth + screenHeight * screenHeight) / getScreenSizeOfDevice();
//    }

//    public void calculateSize(String path, Bitmap bitmap, ImageView view) {
//        double w = bitmap.getWidth() / 100.0;
//        double h = bitmap.getHeight() / 100.0;
//        double ratio = w / h;
//        double ppi = getPPI();
//        double inchDiagonally = w * w + h * h;
//        double pxAmount = ppi * ppi * inchDiagonally;
//        double widthSquare = pxAmount / (1 + ratio * ratio);
//        double width = Math.sqrt(widthSquare);
//        double height = width / ratio;
//        if(width < 350 || height < 350){
//            if(width < 350) {
//                width = 350;
//            }
//            if(height < 350){
//                height = 350;
//            }
//        }else {
//            ImageLoader.getInstance().displayImage( path, view, ImageLoadUtils.DB_OPTIONS);
//            returnone ;
//        }
//        // 计算缩放比例
//        float scaleWidth = ((float) width) / bitmap.getWidth();
//        float scaleHeight = ((float) height) / bitmap.getHeight();
//        // 取得想要缩放的matrix参数
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//        // 得到新的图片
//        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//        if(!bitmap.isRecycled()){
//            // 回收并且置为null
//            bitmap.recycle();
//            bitmap = null;
//        }
//        System.gc();
//        view.setImageBitmap(newBitmap);
//    }
//    public BitmapFactory.Options calculateSize(BitmapFactory.Options options) {
//        double w = options.outWidth / 100.0;
//        double h = options.outHeight / 100.0;
//        double ratio = w / h;
//        double ppi = getPPI();
//        double inchDiagonally = w * w + h * h;
//        double pxAmount = ppi * ppi * inchDiagonally;
//        double widthSquare = pxAmount / (1 + ratio * ratio);
//        double width = Math.sqrt(widthSquare);
//        double height = width / ratio;
//        options.inSampleSize = (int) (options.outWidth/width);
//        options.inDither=false;    /*不进行图片抖动处理*/
//        options.inPreferredConfig=null;  /*设置让解码器以最佳方式解码*/
//        /* 下面两个字段需要组合使用 */
//        options.inPurgeable = true;
//        options.inInputShareable = true;
//        // 得到新的图片
//        options.outWidth = (int) width;
//        options.outHeight = (int) height;
//        options.inJustDecodeBounds = false;
//        returnone options;
//    }

//    public boolean isLogin() {
//        String token = PreferenceUtil.getString(TOKEN);
//        if (TextUtils.isEmpty(token)) {
//            return false;
//        }
//        return true;
//    }
//
//    public boolean isBindPhone() {
//        if (PreferenceUtil.getBoolean(IS_BINFPHONE)) {
//            return false;
//        }
//        return true;
//    }
//
//    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
//
//        private int space;
//
//        public SpaceItemDecoration(int space) {
//            this.space = space;
//        }
//
//        @Override
//        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//
//            if(parent.getChildPosition(view) != 0){
//                outRect.top = space;
//
//            }
//        }
//    }

    public boolean isLogin(){
        if (!TextUtils.isEmpty(PreferenceUtil.getString(TOKEN))){
            return true;
        }
        return false;
    }
}
