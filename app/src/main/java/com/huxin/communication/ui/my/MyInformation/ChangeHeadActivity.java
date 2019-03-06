package com.huxin.communication.ui.my.MyInformation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.huxin.communication.R;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.controls.Constanst;
import com.huxin.communication.ui.ClipImageActivity;
import com.huxin.communication.ui.cammer.HttpUtil;
import com.huxin.communication.utils.FileUtil;
import com.huxin.communication.utils.PictureCutUtil;
import com.huxin.communication.utils.PreferenceUtil;
import com.huxin.communication.utils.UploadImage;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sky.kylog.KyLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

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

    private File photoFile;  //存放图片文件，最后是上传这个file

    private PictureCutUtil pictureCutUtil;   //图片压缩工具

    private Uri uri;

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
        pictureCutUtil = new PictureCutUtil(this);
        if (!TextUtils.isEmpty(PreferenceUtil.getString(Constanst.IMAGE_URL))) {
            ImageLoader.getInstance().displayImage(PreferenceUtil.getString(Constanst.IMAGE_URL), mImageViewHead);
        } else {
            mImageViewHead.setBackgroundResource(R.drawable.head2);
        }
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
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(uri));
                        mImageViewHead.setImageBitmap(bitmap);
                        photoFile = pictureCutUtil.cutPictureQuality(bitmap, "ff");  //压缩并保存

                        showProgressDialog();
                        postIcon(String.valueOf(PreferenceUtil.getInt(UID)), PreferenceUtil.getString(TOKEN), photoFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(uri));
                        mImageViewHead.setImageBitmap(bitmap);
                        photoFile = pictureCutUtil.cutPictureQuality(bitmap, "ff");  //压缩并保存

                        showProgressDialog();
                        postIcon(String.valueOf(PreferenceUtil.getInt(UID)), PreferenceUtil.getString(TOKEN), photoFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
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
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String f = System.currentTimeMillis() + ".jpg";
        uri = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory("/image/").getPath() + f));
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri); //指定图片存放位置，指定后，在onActivityResult里得到的Data将为null
        startActivityForResult(openCameraIntent, REQUEST_CAPTURE);
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

    /**
     * 上传头像
     * @param uid
     * @param token
     * @param photoFile
     */
    private void postIcon(String uid, String token, File photoFile) {
        Thread t = new Thread() {
            @Override
            public void run() {
                String url = " http://39.105.203.33/jlkf/mutual-trust/user/uploadHeadPhoto?uid=" + uid + "&token=" + token;
                String response = UploadImage.uploadFile(photoFile, url);  //方法后面给出
                KyLog.d(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String image = jsonObject.getString("data");
                    ImageLoader.getInstance().displayImage(image, mImageViewHead);
                    PreferenceUtil.putString(Constanst.IMAGE_URL, image);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Message msg = new Message();
                msg.obj = response;
                msg.what = 1;
                handler.sendMessage(msg);
            }
        };
        t.start();
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 处理消息时需要知道是成功的消息还是失败的消息
            if (!isFinishing()) cancelProgressDialog(); //上传返回后，等待框消失
            switch (msg.what) {
                case 1:
                    Toast.makeText(ChangeHeadActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                    break;
                case 0:
                    Toast.makeText(ChangeHeadActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }

        }

    };
}
