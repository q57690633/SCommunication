package com.huxin.communication.ui.house.release;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.ReleaseTabAdapter;
import com.huxin.communication.base.BaseActivity;
import com.huxin.communication.custom.ReleaseDialog;
import com.huxin.communication.entity.MyPopVlaues;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.view.SpaceItemDecoration;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布出租
 */
public class ReleseLaseActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEditTextVillageName;
    private EditText mEditTextAcreage;
    private TextView mEditTextHouseType;
    private EditText mEditTextTotalPrice;
    private EditText mEditTextFloorNumber;
    private EditText mEditTexTotalFloorNumber;
    private ImageView mEditTextNew;
    private ImageView mEditTextOld;
    private ImageView mEditTextLoans;
    private ImageView mEditTextKeying;
    private ImageView mEditTextNewClick;
    private ImageView mEditTextOldClick;
    private ImageView mEditTextLoansClick;
    private ImageView mEditTextKeyingClick;

    private EditText mEditTextTitle;
    private EditText mEditTextHouseNumber;
    private EditText mEditTextPdu;
    private EditText mEditTextFloorSize;


    private TextView mTextViewHouseHoldAppliances;
    private TextView mTextViewFitment;
    private TextView mTextViewPermit;
    private TextView mTextViewOrientation;
    private TextView mTextViewPurpose;
    private TextView mTextViewConfirm;
    private TextView mTextViewExclusive;

    private List<MyPopVlaues> Kouweilist;
    private List<MyPopVlaues> keshulist;
    private String houseHoldAppliances;
    private String fitment;
    private String permit;
    private String orientation;
    private String purpose;
    private String houseType;
    private boolean isclicked = true;
    private boolean isclickeds = true;

    private RecyclerView mRecyclerView;
    private ReleaseTabAdapter mTabAdapter;
    private ReleaseDialog mReleaseDialog;

    private int NeworOld = 2;
    private int loans = 1;
    private int keying = 1;
    private int exclusive = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_relese_lase);
    }

    @Override
    protected void initViews() {
        setToolbarCenterMode("发布出租", MODE_BACK);
        mEditTextAcreage = (EditText) findViewById(R.id.acreage_ed_release);
        mEditTextVillageName = (EditText) findViewById(R.id.villageName_ed_release);
        mEditTextHouseType = (TextView) findViewById(R.id.houseType_ed_release);
        mEditTextTotalPrice = (EditText) findViewById(R.id.totalPrice_ed_release);
        mEditTextFloorNumber = (EditText) findViewById(R.id.floorNumber_ed_release);
        mEditTexTotalFloorNumber = (EditText) findViewById(R.id.totalFloorNumber_ed_release);

        mEditTextNew = (ImageView) findViewById(R.id.new_tv_release);
        mEditTextOld = (ImageView) findViewById(R.id.Old_tv_release);
        mEditTextLoans = (ImageView) findViewById(R.id.loans_tv_release);
        mEditTextKeying = (ImageView) findViewById(R.id.keying_tv_release);

        mEditTextNewClick = (ImageView) findViewById(R.id.new_tv_release_click);
        mEditTextOldClick = (ImageView) findViewById(R.id.Old_tv_release_click);
        mEditTextLoansClick = (ImageView) findViewById(R.id.loans_tv_release_click);
        mEditTextKeyingClick = (ImageView) findViewById(R.id.keying_tv_release_click);

        mEditTextTitle = (EditText) findViewById(R.id.title_ed_release);
        mEditTextHouseNumber = (EditText) findViewById(R.id.houseNumber_ed_release);
        mEditTextPdu = (EditText) findViewById(R.id.pdu_ed_release);
        mEditTextFloorSize = (EditText) findViewById(R.id.floorSize_ed_release);

        mTextViewHouseHoldAppliances = (TextView) findViewById(R.id.Occupation_type);
        mTextViewFitment = (TextView) findViewById(R.id.release_type);
        mTextViewPermit = (TextView) findViewById(R.id.release1_type);
        mTextViewOrientation = (TextView) findViewById(R.id.release2_type);
        mTextViewPurpose = (TextView) findViewById(R.id.release3_type);
        mTextViewConfirm = (TextView) findViewById(R.id.confirm);

        mTextViewExclusive = (TextView) findViewById(R.id.exclusive_ed_release);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_release_sell);

        mTextViewHouseHoldAppliances.setOnClickListener(this);
        mTextViewFitment.setOnClickListener(this);
        mTextViewPermit.setOnClickListener(this);
        mTextViewOrientation.setOnClickListener(this);
        mTextViewPurpose.setOnClickListener(this);
        mEditTextHouseType.setOnClickListener(this);
        mTextViewConfirm.setOnClickListener(this);

        mEditTextNew.setOnClickListener(this);
        mEditTextOld.setOnClickListener(this);
        mEditTextLoans.setOnClickListener(this);
        mEditTextKeying.setOnClickListener(this);
        mEditTextNewClick.setOnClickListener(this);
        mEditTextOldClick.setOnClickListener(this);
        mEditTextLoansClick.setOnClickListener(this);
        mEditTextKeyingClick.setOnClickListener(this);
        mTextViewExclusive.setOnClickListener(this);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setTabData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Occupation_type:
//                final MyPopWindow myPopWindow = new MyPopWindow(ReleaseGuoNeiActivity.this, setOccupation());
//                //显示popwindow的位置
//                myPopWindow.showAtLocation(mTextViewHouseHoldAppliances, Gravity.CENTER | Gravity.LEFT, 33, 60);
//                myPopWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        mTextViewHouseHoldAppliances.setText(setOccupation().get(position).getName());
//                        houseHoldAppliances = setOccupation().get(position).getName();
//                        myPopWindow.dismiss();
//                    }
//                });
                mReleaseDialog = new ReleaseDialog(this, setOccupation());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewHouseHoldAppliances.setText(setOccupation().get(position).getName());
                        houseHoldAppliances = setOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();

                break;
            case R.id.release_type:
//                final MyPopWindow1 myPopWindow1 = new MyPopWindow1(ReleaseGuoNeiActivity.this, setFitment());
//                //显示popwindow的位置
//                myPopWindow1.showAtLocation(mTextViewFitment, Gravity.CENTER, 233, 60);
//                myPopWindow1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        mTextViewFitment.setText(setFitment().get(position).getName());
//                        fitment = setFitment().get(position).getName();
//                        myPopWindow1.dismiss();
//                    }
//                });

                mReleaseDialog = new ReleaseDialog(this, setFitment());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewFitment.setText(setOccupation().get(position).getName());
                        fitment = setOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.release1_type:
//                final MyPopWindow1 myPopWindow2 = new MyPopWindow1(ReleaseGuoNeiActivity.this, setPermit());
//                //显示popwindow的位置
//                myPopWindow2.showAtLocation(mTextViewPermit, Gravity.CENTER | Gravity.LEFT, 33, 230);
//                myPopWindow2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        mTextViewPermit.setText(setPermit().get(position).getName());
//                        permit = setPermit().get(position).getName();
//                        myPopWindow2.dismiss();
//                    }
//                });

                mReleaseDialog = new ReleaseDialog(this, setPermit());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewPermit.setText(setOccupation().get(position).getName());
                        permit = setOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.release2_type:
//                final MyPopWindow2 myPopWindow3 = new MyPopWindow2(ReleaseGuoNeiActivity.this, setOrientation());
//                //显示popwindow的位置
//                myPopWindow3.showAtLocation(mTextViewOrientation, Gravity.CENTER, 3, 230);
//                myPopWindow3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        mTextViewOrientation.setText(setOccupation().get(position).getName());
//                        orientation = setOccupation().get(position).getName();
//                        myPopWindow3.dismiss();
//                    }
//                });
                mReleaseDialog = new ReleaseDialog(this, setOrientation());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewOrientation.setText(setOccupation().get(position).getName());
                        orientation = setOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.release3_type:
//                final MyPopWindow1 myPopWindow4 = new MyPopWindow1(ReleaseGuoNeiActivity.this, setPurpose());
//                //显示popwindow的位置
//                myPopWindow4.showAtLocation(mTextViewPurpose, Gravity.CENTER | Gravity.RIGHT, 33, 230);
//                myPopWindow4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        mTextViewPurpose.setText(setPurpose().get(position).getName());
//                        purpose = setPurpose().get(position).getName();
//                        myPopWindow4.dismiss();
//                    }
//                });

                mReleaseDialog = new ReleaseDialog(this, setPurpose());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mTextViewPurpose.setText(setOccupation().get(position).getName());
                        purpose = setOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;
            case R.id.houseType_ed_release:
//                final MyPopWindow1 myPopWindow5 = new MyPopWindow1(ReleaseGuoNeiActivity.this, setHouseType());
//                //显示popwindow的位置
//                myPopWindow5.showAtLocation(mEditTextHouseType, Gravity.TOP | Gravity.LEFT, 33, 530);
//                myPopWindow5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        mEditTextHouseType.setText(setHouseType().get(position).getName());
//                        purpose = setHouseType().get(position).getName();
//                        myPopWindow5.dismiss();
//                    }
//                });

                mReleaseDialog = new ReleaseDialog(this, setHouseType());
                mReleaseDialog.setCancelable(true);

                mReleaseDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mEditTextHouseType.setText(setOccupation().get(position).getName());
                        houseType = setOccupation().get(position).getName();
                        mReleaseDialog.cancel();
                    }
                });
                mReleaseDialog.show();
                break;

            case R.id.confirm:
                addSaleProduct();
                break;
            case R.id.new_tv_release:
                mEditTextNewClick.setVisibility(View.VISIBLE);
                mEditTextOld.setVisibility(View.VISIBLE);
                mEditTextNew.setVisibility(View.GONE);
                mEditTextOldClick.setVisibility(View.GONE);
                NeworOld = 1;
                KyLog.d("new_tv_release");
                break;
            case R.id.Old_tv_release:
                mEditTextNew.setVisibility(View.VISIBLE);
                mEditTextOldClick.setVisibility(View.VISIBLE);
                mEditTextNewClick.setVisibility(View.GONE);
                mEditTextOld.setVisibility(View.GONE);
                NeworOld = 2;
                KyLog.d("Old_tv_release");

                break;
            case R.id.loans_tv_release:
                mEditTextLoans.setVisibility(View.GONE);
                mEditTextLoansClick.setVisibility(View.VISIBLE);
                loans = 1;
                break;
            case R.id.keying_tv_release:
                mEditTextKeying.setVisibility(View.GONE);
                mEditTextKeyingClick.setVisibility(View.VISIBLE);
                keying = 1;
                break;
            case R.id.new_tv_release_click:
                mEditTextNewClick.setVisibility(View.GONE);
                mEditTextOld.setVisibility(View.GONE);
                mEditTextNew.setVisibility(View.VISIBLE);
                mEditTextOldClick.setVisibility(View.VISIBLE);
//                mEditTextNew.setBackgroundResource(R.drawable.icon_circle_selected);
//                mEditTextOld.setBackgroundResource(R.drawable.icon_circle_normal);
                NeworOld = 1;
                KyLog.d("new_tv_release");
                break;
            case R.id.Old_tv_release_click:
                mEditTextNewClick.setVisibility(View.VISIBLE);
                mEditTextOld.setVisibility(View.VISIBLE);
                mEditTextNew.setVisibility(View.GONE);
                mEditTextOldClick.setVisibility(View.GONE);
//                mEditTextNew.setBackgroundResource(R.drawable.icon_circle_normal);
//                mEditTextOld.setBackgroundResource(R.drawable.icon_circle_selected);
                NeworOld = 2;
                KyLog.d("Old_tv_release");

                break;
            case R.id.loans_tv_release_click:
                mEditTextLoans.setVisibility(View.VISIBLE);
                mEditTextLoansClick.setVisibility(View.GONE);

                isclicked = false;
                loans = 2;

                break;
            case R.id.keying_tv_release_click:
                mEditTextKeying.setVisibility(View.VISIBLE);
                mEditTextKeyingClick.setVisibility(View.GONE);

                keying = 2;
                break;

            case R.id.exclusive_ed_release:
                if (isclicked) {
                    mTextViewExclusive.setBackgroundColor(getResources().getColor(R.color.red_setting));
                    isclicked = false;

                } else {
                    mTextViewExclusive.setBackgroundColor(getResources().getColor(R.color.login_forget_password_code_fort));
                    isclicked = false;
                }
                break;

        }
    }


    private List<MyPopVlaues> setOccupation() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("家具家电齐全"));
        Kouweilist.add(new MyPopVlaues("无家具无家电"));
        Kouweilist.add(new MyPopVlaues("有家具无家电"));
        Kouweilist.add(new MyPopVlaues("无家具有家电"));

        return Kouweilist;
    }

    private List<MyPopVlaues> setFitment() {
        keshulist = new ArrayList<MyPopVlaues>();
        keshulist.add(new MyPopVlaues("毛坯"));
        keshulist.add(new MyPopVlaues("简装"));
        keshulist.add(new MyPopVlaues("精装"));
        keshulist.add(new MyPopVlaues("豪装"));
        keshulist.add(new MyPopVlaues("未交房"));
        return keshulist;
    }

    private List<MyPopVlaues> setPermit() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("未知"));
        Kouweilist.add(new MyPopVlaues("不满两年"));
        Kouweilist.add(new MyPopVlaues("满两年"));
        Kouweilist.add(new MyPopVlaues("满五年"));
        return Kouweilist;
    }


    private List<MyPopVlaues> setOrientation() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("南北"));
        Kouweilist.add(new MyPopVlaues("朝南"));
        Kouweilist.add(new MyPopVlaues("西南"));
        Kouweilist.add(new MyPopVlaues("东南"));
        Kouweilist.add(new MyPopVlaues("朝东"));
        Kouweilist.add(new MyPopVlaues("朝西"));
        Kouweilist.add(new MyPopVlaues("朝北"));
        Kouweilist.add(new MyPopVlaues("东北"));
        Kouweilist.add(new MyPopVlaues("西北"));


        return Kouweilist;
    }

    private List<MyPopVlaues> setPurpose() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("普通住宅"));
        Kouweilist.add(new MyPopVlaues("商业"));
        Kouweilist.add(new MyPopVlaues("商住两用"));
        Kouweilist.add(new MyPopVlaues("有独院"));
        Kouweilist.add(new MyPopVlaues("其他"));

        return Kouweilist;
    }


    private List<MyPopVlaues> setHouseType() {
        Kouweilist = new ArrayList<MyPopVlaues>();
        Kouweilist.add(new MyPopVlaues("一居室"));
        Kouweilist.add(new MyPopVlaues("二居室"));
        Kouweilist.add(new MyPopVlaues("三居室"));
        Kouweilist.add(new MyPopVlaues("四居室"));

        return Kouweilist;
    }

    private void addSaleProduct() {
        String VillageName = mEditTextVillageName.getText().toString().trim();
        String Acreage = mEditTextAcreage.getText().toString().trim();
        String totalPrice = mEditTextTotalPrice.getText().toString().trim();
        String floorNumber = mEditTextFloorNumber.getText().toString().trim();
        String totalFloorNumber = mEditTexTotalFloorNumber.getText().toString().trim();
        String title = mEditTextTitle.getText().toString().trim();
        String houseNumber = mEditTextHouseNumber.getText().toString().trim();
        String pdu = mEditTextPdu.getText().toString().trim();
        String floorSize = mEditTextFloorSize.getText().toString().trim();

        KyLog.d(loans + "");
        KyLog.d(keying + "");

        showProgressDialog();
        ApiModule.getInstance().addSaleProduct(VillageName, Acreage, houseType, totalPrice
                , floorNumber, totalFloorNumber, String.valueOf(NeworOld), String.valueOf(loans), String.valueOf(keying),
                houseHoldAppliances, fitment, permit, orientation, purpose, title, "2",
                "2", "2", houseNumber, pdu, floorSize)
                .subscribe(response -> {

                    cancelProgressDialog();
                    KyLog.d(response.getResultMsg());
                    Toast.makeText(this, response.getResultMsg(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }

    private void setTabData() {
        ApiModule.getInstance().selectTab("2")
                .subscribe(selectTabEntity -> {
                    cancelProgressDialog();
                    if (selectTabEntity != null && selectTabEntity.getTag().size() > 0) {
                        GridLayoutManager manager = new GridLayoutManager(ReleseLaseActivity.this, 4);
                        mTabAdapter = new ReleaseTabAdapter(selectTabEntity.getTag(), this);
                        mRecyclerView.setAdapter(mTabAdapter);
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.addItemDecoration(new SpaceItemDecoration(0, 15));
                    }

                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(this, throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });
    }
}
