package com.huxin.communication.ui.my.MyInformation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
<<<<<<< Updated upstream
import android.os.Build;
=======
>>>>>>> Stashed changes
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
<<<<<<< Updated upstream
import android.support.v4.content.FileProvider;
=======
>>>>>>> Stashed changes
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.ui.ClipImageActivity;
import com.huxin.communication.ui.cammer.HttpUtil;
import com.huxin.communication.utils.FileUtil;
import com.sky.kylog.KyLog;

import java.io.File;

import static com.huxin.communication.utils.FileUtil.getRealFilePathFromUri;


public class ChangeHeadActivity extends BaseActivity implements View.OnClickListener {
    private static final String url = "http://39.105.203.33/jlkf/mutual-trust/user/uploadHeadPhoto";

    private TextView mTextViewWanCheng;
    private TextView mTextViewQuXiao;
    private TextView mTextViewPaiZhao;
    private TextView mTextViewCongXiangCeXuanZhe;
    private ImageView mImageViewHead;


    private HttpUtil httpUtil;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    private static final int CAMERA_REQUEST_CODE = 105;

    //调用照相机返回图片文件
    private File tempFile;

   private  Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_change_head);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("头像", MODE_BACK);
        mTextViewCongXiangCeXuanZhe = findViewById(R.id.xiangcexuanze);
        mTextViewWanCheng = findViewById(R.id.toolbar_quxiao);
        mTextViewQuXiao = findViewById(R.id.quxiao);
        mTextViewPaiZhao = findViewById(R.id.paizhao);
        mImageViewHead = findViewById(R.id.image_head);

        mTextViewCongXiangCeXuanZhe.setOnClickListener(this);
        mTextViewWanCheng.setOnClickListener(this);
        mTextViewQuXiao.setOnClickListener(this);
        mTextViewPaiZhao.setOnClickListener(this);



    }

    @Override
    protected void loadData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xiangcexuanze:
                if (ContextCompat.checkSelfPermission(ChangeHeadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ChangeHeadActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {

                    //跳转到相册
                    gotoPhoto();
                }
                mTextViewWanCheng.setVisibility(View.VISIBLE);
                break;
            case R.id.toolbar_quxiao:
//                setPhoto(url, ivPhotoStr);
                Intent intent = new Intent();
                intent.setData(uri);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.quxiao:
                finish();
                break;
            case R.id.paizhao:
                if (ContextCompat.checkSelfPermission(ChangeHeadActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ChangeHeadActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    ActivityCompat.requestPermissions(ChangeHeadActivity.this, new String[]{Manifest.permission.CAMERA},
                            CAMERA_REQUEST_CODE);
                    gotoCamera();
                }
                mTextViewWanCheng.setVisibility(View.VISIBLE);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KyLog.d(requestCode + "");
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                     uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//                    if (type == 1) {
//                        headImage1.setImageBitmap(bitMap);
//                    } else {
                        mImageViewHead.setImageBitmap(bitMap);
//                    }
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }

    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            }
        }
    }

//    private String url = "http://39.105.203.33/jlkf/mutual-trust/user/uploadHeadPhoto";
//
//    private void uploadImage(ArrayList<ImageItem> pathList) {
//        httpUtil.postFileRequest(url, null, pathList, new MyStringCallBack() {
//
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                super.onError(call, e, id);
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                super.onResponse(response, id);
//                KyLog.d(response);
//                //返回图片的地址
//            }
//        });
//    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
<<<<<<< Updated upstream
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),  "images.jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(ChangeHeadActivity.this,  "com.huxin.communication.fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
=======
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            Uri contentUri = FileProvider.getUriForFile(ChangeHeadActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
//        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
//        }
>>>>>>> Stashed changes
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", 2);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }



}
