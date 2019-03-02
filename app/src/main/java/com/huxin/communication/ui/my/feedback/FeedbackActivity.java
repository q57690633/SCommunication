package com.huxin.communication.ui.my.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.cammer.HttpUtil;
import com.huxin.communication.ui.cammer.ImagePickerAdapter;
import com.huxin.communication.ui.cammer.MyStringCallBack;
import com.huxin.communication.ui.cammer.SelectDialog;
import com.huxin.communication.ui.house.release.ReleaseActivity;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {
    private TextView mTextViewGongNen;
    private TextView mTextViewTiJIan;
    private TextView mTextViewNeiRong;
    private TextView mTextViewBug;
    private TextView mTextViewTiJiao;
    private EditText mEditTextFanKui;


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数

    private RecyclerView mRecyclerViewAddPicture;
    private ImagePickerAdapter adapter;

    private HttpUtil httpUtil;

    private int adviceType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("意见反馈", MODE_BACK);
        mTextViewBug = findViewById(R.id.bug);
        mTextViewGongNen = findViewById(R.id.gongneng);
        mTextViewTiJIan = findViewById(R.id.tijian);
        mTextViewNeiRong = findViewById(R.id.neirong);
        mRecyclerViewAddPicture = findViewById(R.id.recyclerView);
        mEditTextFanKui = findViewById(R.id.fankui);


        mTextViewTiJIan.setOnClickListener(this);
        mTextViewNeiRong.setOnClickListener(this);
        mTextViewBug.setOnClickListener(this);
        mTextViewGongNen.setOnClickListener(this);


    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        httpUtil = new HttpUtil();
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        mRecyclerViewAddPicture.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerViewAddPicture.setHasFixedSize(true);
        mRecyclerViewAddPicture.setAdapter(adapter);
    }

    public void returnAdvice(String advice, int adviceType) {
        if (!TextUtils.isEmpty(advice)) {
            showProgressDialog();
            ApiModule.getInstance().returnAdvice(advice, adviceType)
                    .subscribe(updateUserPhoneEntity -> {
                        cancelProgressDialog();
                        Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();

                    }, throwable -> {
                        KyLog.d(throwable.toString());
                        cancelProgressDialog();
                    });
        } else {
            Toast.makeText(this, "请先输入电话号码", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onItemClick(View view, int position) {
        List<String> names = new ArrayList<>();
        names.add("拍照");
        names.add("相册");
        showDialog(new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // 直接调起相机
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent = new Intent(FeedbackActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        break;
                    case 1:
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(FeedbackActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        break;
                    default:
                        break;
                }
            }
        }, names);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KyLog.d(requestCode + "");
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    private String url = "http://39.105.203.33/jlkf/mutual-trust/user/returnAdvice";

    private void uploadImage(ArrayList<ImageItem> pathList) {
        httpUtil.postFileRequest(url, null, pathList, new MyStringCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                KyLog.d(response);
                //返回图片的地址
            }
        });
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gongneng:
                mTextViewBug.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewGongNen.setBackgroundResource(R.drawable.biaoqian_radius_blue);
                mTextViewNeiRong.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewTiJIan.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewTiJIan.setTextColor(getResources().getColor(R.color.login_walling_fort));
                mTextViewBug.setTextColor(getResources().getColor(R.color.login_walling_fort));
                mTextViewNeiRong.setTextColor(getResources().getColor(R.color.login_walling_fort));
                mTextViewGongNen.setTextColor(getResources().getColor(R.color.white));
                adviceType = 1;
                break;
            case R.id.tijian:
                mTextViewBug.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewGongNen.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewNeiRong.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewTiJIan.setBackgroundResource(R.drawable.biaoqian_radius_blue);

                mTextViewTiJIan.setTextColor(getResources().getColor(R.color.white));
                mTextViewBug.setTextColor(getResources().getColor(R.color.login_walling_fort));
                mTextViewNeiRong.setTextColor(getResources().getColor(R.color.login_walling_fort));
                mTextViewGongNen.setTextColor(getResources().getColor(R.color.login_walling_fort));
                adviceType = 2;
                break;
            case R.id.neirong:
                mTextViewBug.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewGongNen.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewNeiRong.setBackgroundResource(R.drawable.biaoqian_radius_blue);
                mTextViewTiJIan.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewTiJIan.setTextColor(getResources().getColor(R.color.login_walling_fort));
                mTextViewBug.setTextColor(getResources().getColor(R.color.login_walling_fort));
                mTextViewNeiRong.setTextColor(getResources().getColor(R.color.white));
                mTextViewGongNen.setTextColor(getResources().getColor(R.color.login_walling_fort));
                adviceType = 3;
                break;
            case R.id.bug:
                mTextViewBug.setBackgroundResource(R.drawable.biaoqian_radius_blue);
                mTextViewGongNen.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewNeiRong.setBackgroundResource(R.drawable.biaoqian_radius);
                mTextViewTiJIan.setBackgroundResource(R.drawable.biaoqian_radius);

                mTextViewTiJIan.setTextColor(getResources().getColor(R.color.login_walling_fort));
                mTextViewBug.setTextColor(getResources().getColor(R.color.white));
                mTextViewNeiRong.setTextColor(getResources().getColor(R.color.login_walling_fort));
                mTextViewGongNen.setTextColor(getResources().getColor(R.color.login_walling_fort));
                adviceType = 4;
                break;
            case R.id.tijiao:
                String str = mEditTextFanKui.getText().toString().trim();
                if (!TextUtils.isEmpty(str)) {
                    returnAdvice(str, adviceType);
                } else {
                    Toast.makeText(this, "请输入反馈的信息", Toast.LENGTH_SHORT).show();
                }
                uploadImage(selImageList);
                break;
        }
    }





}
