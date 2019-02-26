package com.huxin.communication.ui.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.huxin.communication.R;
import com.huxin.communication.base.BaseFragment;
import com.huxin.communication.entity.AliPayEntity;
import com.huxin.communication.entity.ToVipEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.ui.pay.AuthResult;
import com.huxin.communication.ui.pay.PayResult;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import java.util.Map;

/**
 * 会员
 */
public class ShopCarFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2018121762587164";

    private TextView mTextViewjian;
    private TextView mTextViewjia;
    private TextView mTextViewYuefen;
    private TextView mTextViewMatchingVip;
    private TextView mTextViewStickNumber;
    private TextView mTextViewJianZhiDing;
    private TextView mTextViewNumDay;
    private TextView mTextViewJiaZhiDing;
    private TextView mTextViewOnePhone;
    private TextView mTextViewComboPreference;
    private TextView mTextViewComboOriginal;
    private TextView mTextViewComboPreference2;
    private TextView mTextViewComboOriginal2;
    private TextView mTextViewZongJia;
    private TextView mTextViewPay;


    private RelativeLayout mRelativeLayoutWeiXin;
    private RelativeLayout mRelativeLayoutZhiFuBao;

    private ImageView mImageViewImageWeixin;
    private ImageView mImageViewImageWeixinClick;
    private ImageView mImageViewImageZhiFuBao;
    private ImageView mImageViewImageZhiFuBaoClick;

    private LinearLayout mLinearLayoutTaoChan;
    private LinearLayout mLinearLayoutTaoChan2;


    private int num = 0;

    private int numDay = 0;

    private int type = 0;

    private int taocan = 0;

    private int ComboPreference = 0;
    private int ComboOriginal = 0;

    private int MatchingVip = 0;
    private int StickNumber = 0;

    private int AllPrice = 0;

    public ShopCarFragment() {
        // Required empty public constructor
    }

    public static ShopCarFragment newInstance(String param1, String param2) {
        ShopCarFragment fragment = new ShopCarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_car, container, false);
    }

    @Override
    protected void initView(View view) {
        mTextViewjian = (TextView) view.findViewById(R.id.jian);
        mTextViewjia = (TextView) view.findViewById(R.id.jia);
        mTextViewYuefen = (TextView) view.findViewById(R.id.tv_yuefen);
        mTextViewMatchingVip = (TextView) view.findViewById(R.id.matchingVip);
        mTextViewStickNumber = (TextView) view.findViewById(R.id.stickNumber);
        mTextViewJianZhiDing = (TextView) view.findViewById(R.id.jian_zhiding);
        mTextViewNumDay = (TextView) view.findViewById(R.id.num_day);
        mTextViewJiaZhiDing = (TextView) view.findViewById(R.id.jia_zhiding);
        mTextViewOnePhone = (TextView) view.findViewById(R.id.one_phone);
        mTextViewComboPreference = (TextView) view.findViewById(R.id.comboPreference);
        mTextViewComboOriginal = (TextView) view.findViewById(R.id.comboOriginal);
        mTextViewComboPreference2 = (TextView) view.findViewById(R.id.comboPreference2);
        mTextViewComboOriginal2 = (TextView) view.findViewById(R.id.comboOriginal2);

        mTextViewZongJia = (TextView) view.findViewById(R.id.zongjia);
        mTextViewPay = (TextView) view.findViewById(R.id.qurenzhifu);

        mRelativeLayoutWeiXin = (RelativeLayout) view.findViewById(R.id.rl_weixin);
        mRelativeLayoutZhiFuBao = (RelativeLayout) view.findViewById(R.id.rl_zhifubao);
        mImageViewImageWeixin = (ImageView) view.findViewById(R.id.weixin);
        mImageViewImageWeixinClick = (ImageView) view.findViewById(R.id.weixin_click);
        mImageViewImageZhiFuBao = (ImageView) view.findViewById(R.id.zhifubao);
        mImageViewImageZhiFuBaoClick = (ImageView) view.findViewById(R.id.zhifubao_click);

        mLinearLayoutTaoChan = (LinearLayout) view.findViewById(R.id.line_taocan);
        mLinearLayoutTaoChan2 = (LinearLayout) view.findViewById(R.id.line_taocan2);


        mTextViewjian.setOnClickListener(this);
        mTextViewjia.setOnClickListener(this);
        mTextViewJianZhiDing.setOnClickListener(this);
        mTextViewJiaZhiDing.setOnClickListener(this);
        mTextViewOnePhone.setOnClickListener(this);
        mImageViewImageWeixin.setOnClickListener(this);
        mImageViewImageZhiFuBao.setOnClickListener(this);
        mTextViewZongJia.setOnClickListener(this);
        mTextViewPay.setOnClickListener(this);
        mLinearLayoutTaoChan.setOnClickListener(this);
        mLinearLayoutTaoChan2.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        toVip();

    }

    @Override
    protected void bindData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jian:
                num--;
                mTextViewYuefen.setText(String.valueOf(num));
                setPrice();
                break;
            case R.id.jia:
                num++;
                mTextViewYuefen.setText(String.valueOf(num));
                setPrice();
                break;
            case R.id.jian_zhiding:
                numDay--;
                mTextViewNumDay.setText(String.valueOf(numDay));
                setPrice();
                break;
            case R.id.jia_zhiding:
                numDay++;
                mTextViewNumDay.setText(String.valueOf(numDay));
                setPrice();
                break;
            case R.id.one_phone:
                break;
            case R.id.qurenzhifu:
                apppayZhiFuBao();
                break;
            case R.id.weixin:
                mImageViewImageWeixin.setVisibility(View.GONE);
                mImageViewImageWeixinClick.setVisibility(View.VISIBLE);
                mImageViewImageZhiFuBao.setVisibility(View.VISIBLE);
                mImageViewImageZhiFuBaoClick.setVisibility(View.GONE);
                type = 1;
                break;

            case R.id.zhifubao:
                mImageViewImageWeixin.setVisibility(View.VISIBLE);
                mImageViewImageWeixinClick.setVisibility(View.GONE);
                mImageViewImageZhiFuBao.setVisibility(View.GONE);
                mImageViewImageZhiFuBaoClick.setVisibility(View.VISIBLE);
                type = 2;
                break;

            case R.id.line_taocan:
                mLinearLayoutTaoChan.setBackgroundColor(getResources().getColor(R.color.huiyuan));
                mLinearLayoutTaoChan2.setBackgroundColor(getResources().getColor(R.color.login_forget_password_code_fort));
                taocan = 1;
                setPrice();
                break;

            case R.id.line_taocan2:
                mLinearLayoutTaoChan.setBackgroundColor(getResources().getColor(R.color.login_forget_password_code_fort));
                mLinearLayoutTaoChan2.setBackgroundColor(getResources().getColor(R.color.huiyuan));
                taocan = 2;
                setPrice();
                break;

        }
    }

    private void toVip() {

        showProgressDialog();
        ApiModule.getInstance().toVip(String.valueOf(PreferenceUtil.getInt("type")))
                .subscribe(toVipEntity -> {
                    if (toVipEntity != null) {
                        KyLog.object(toVipEntity + "");
                        setData(toVipEntity);
                    }

                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(getContext(), throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    private void setData(ToVipEntity entity) {

        MatchingVip = entity.getVipModel().getMatchingVip();
        StickNumber = entity.getVipModel().getStickNumber();

        mTextViewMatchingVip.setText(String.valueOf(entity.getVipModel().getMatchingVip()) + "元/月");
        mTextViewStickNumber.setText(String.valueOf(entity.getVipModel().getStickNumber()) + "元/条/天");
        for (ToVipEntity.ComboBean entity1 : entity.getCombo()) {
            if (entity1.getComboType() == 1) {
                ComboPreference = entity1.getComboPreference();
                ComboOriginal = entity1.getComboOriginal();
                mTextViewComboPreference.setText("特惠价：" + entity1.getComboPreference() + "元");
                mTextViewComboOriginal.setText("原价：" + String.valueOf(entity1.getComboOriginal()) + "元");
            } else {
                mTextViewComboPreference2.setText("特惠价：" + entity1.getComboPreference() + "元");
                mTextViewComboOriginal2.setText("原价：" + String.valueOf(entity1.getComboOriginal()) + "元");
            }
        }

        setPrice();

    }

    private void setPrice() {
        if (taocan == 0) {
            mTextViewZongJia.setText(String.valueOf((MatchingVip * num) + (StickNumber * numDay)));
            AllPrice = (MatchingVip * num) + (StickNumber * numDay);
        } else if (taocan == 1) {
            mTextViewZongJia.setText(String.valueOf(ComboPreference));
            AllPrice = ComboPreference;
        } else {
            mTextViewZongJia.setText(String.valueOf(ComboOriginal));
            AllPrice = ComboOriginal;

        }
    }


    private void apppayWeiXin() {
        ApiModule.getInstance().apppay(String.valueOf(AllPrice), "2", "", "", "",
                "", String.valueOf(MatchingVip), String.valueOf(num), String.valueOf(numDay), String.valueOf(StickNumber))
                .subscribe(appPayEntity -> {
                    if (appPayEntity != null) {
                        KyLog.object(appPayEntity + "");
//                        payAliKey(appPayEntity);

                    }

//                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
//                    cancelProgressDialog();
                    Toast.makeText(getContext(), throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void apppayZhiFuBao() {
        ApiModule.getInstance().apppayZhiFuBao(String.valueOf(AllPrice), "2", "", "", "",
                "", String.valueOf(MatchingVip), String.valueOf(num), String.valueOf(numDay), String.valueOf(StickNumber))
                .subscribe(aliPayEntity -> {
                    if (aliPayEntity != null) {
                        KyLog.object(aliPayEntity + "");
                        payAliKey(aliPayEntity);

                    }

//                    cancelProgressDialog();
                }, throwable -> {
                    KyLog.d(throwable.toString());
//                    cancelProgressDialog();
                    Toast.makeText(getContext(), throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }


    private void payAliKey(AliPayEntity entity) {

//        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
//        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap();
//        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
////
////        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
//        String sign = entity.getSign();
        final String orderInfo = entity.getZfbOrder();

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        showAlert(getContext(), getString(R.string.pay_success) + payResult);
                        Toast.makeText(getContext(), R.string.pay_success, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        showAlert(getContext(), getString(R.string.pay_failed) + payResult);
                        Toast.makeText(getContext(), "支付失败:" + payResult, Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
//                        showAlert(getContext(), getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
//                        showAlert(getContext(), getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }
}
