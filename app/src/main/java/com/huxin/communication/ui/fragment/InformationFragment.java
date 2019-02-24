package com.huxin.communication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.huxin.communication.R;
import com.huxin.communication.base.BaseFragment;
import com.huxin.communication.ui.MainActivity;
import com.huxin.communication.ui.house.release.ReleaseActivity;
import com.huxin.communication.ui.house.release.ReleaseBuyActivity;
import com.huxin.communication.ui.house.release.ReleaseRentActivity;
import com.huxin.communication.ui.house.release.ReleseLaseActivity;
import com.huxin.communication.ui.my.collect.DataBaseActivity;
import com.huxin.communication.ui.travel.release.OverseasReleaseActivity;
import com.huxin.communication.ui.travel.release.ReleaseGuoNeiActivity;
import com.huxin.communication.ui.travel.release.ReleaseTicketingActivity;
import com.huxin.communication.ui.travel.release.ReleaseZhouBoundaryActivity;
import com.huxin.communication.utils.PreferenceUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends BaseFragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private LinearLayout mLinearLayoutSell;
    private LinearLayout mLinearLayoutrental;
    private LinearLayout mLinearLayoutqiuzu;
    private LinearLayout mLinearLayoutqiugou;
    private LinearLayout mLinearLayoutData;


    private LinearLayout mLinearLayoutguonei;
    private LinearLayout mLinearLayoutjingwai;
    private LinearLayout mLinearLayoutzhoubian;
    private LinearLayout mLinearLayoutpiaowu;


    private RelativeLayout mRelativeLayoutHouse;
    private RelativeLayout mRelativeLayoutTravel;

    private ImageView mImageViewHouseBack;
    private ImageView mImageViewTravelBack;


    public InformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationFragment.
     */
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
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
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    protected void initView(View view) {
        mLinearLayoutqiugou = (LinearLayout) view.findViewById(R.id.release_qiugou_line);
        mLinearLayoutqiuzu = (LinearLayout) view.findViewById(R.id.release_qiuzu_line);
        mLinearLayoutrental = (LinearLayout) view.findViewById(R.id.release_rental_line);
        mLinearLayoutSell = (LinearLayout) view.findViewById(R.id.release_sell_line);
        mLinearLayoutData = (LinearLayout) view.findViewById(R.id.release_data_base_line);

        mLinearLayoutguonei = (LinearLayout) view.findViewById(R.id.travel_release_guonei_line);
        mLinearLayoutzhoubian = (LinearLayout) view.findViewById(R.id.travel_release_zhoubian_line);
        mLinearLayoutjingwai = (LinearLayout) view.findViewById(R.id.travel_release_jingwai_line);
        mLinearLayoutpiaowu = (LinearLayout) view.findViewById(R.id.travel_release_piaowu_line);

        mRelativeLayoutHouse = (RelativeLayout) view.findViewById(R.id.release_house);
        mRelativeLayoutTravel = (RelativeLayout) view.findViewById(R.id.release_travel);

        mImageViewHouseBack = (ImageView) view.findViewById(R.id.image_interface_back_house);
        mImageViewTravelBack = (ImageView) view.findViewById(R.id.image_interface_back_travel);

        mLinearLayoutSell.setOnClickListener(this);
        mLinearLayoutqiugou.setOnClickListener(this);
        mLinearLayoutqiuzu.setOnClickListener(this);
        mLinearLayoutrental.setOnClickListener(this);
        mLinearLayoutData.setOnClickListener(this);

        mLinearLayoutguonei.setOnClickListener(this);
        mLinearLayoutjingwai.setOnClickListener(this);
        mLinearLayoutzhoubian.setOnClickListener(this);
        mLinearLayoutpiaowu.setOnClickListener(this);

        mImageViewTravelBack.setOnClickListener(this);
        mImageViewHouseBack.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        if (PreferenceUtil.getInt("type") == 1) {
            mRelativeLayoutHouse.setVisibility(View.VISIBLE);
            mRelativeLayoutTravel.setVisibility(View.GONE);
        } else {
            mRelativeLayoutHouse.setVisibility(View.GONE);
            mRelativeLayoutTravel.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void bindData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.release_qiugou_line:
                Intent intentQiuGou = new Intent(getContext(), ReleaseBuyActivity.class);
                getContext().startActivity(intentQiuGou);
                break;
            case R.id.release_rental_line:
                Intent intentRetal = new Intent(getContext(), ReleseLaseActivity.class);
                getContext().startActivity(intentRetal);
                break;
            case R.id.release_sell_line:
                Intent intentRelease = new Intent(getContext(), ReleaseActivity.class);
                getContext().startActivity(intentRelease);
                break;
            case R.id.release_qiuzu_line:
                Intent intentQiuZu = new Intent(getContext(), ReleaseRentActivity.class);
                getContext().startActivity(intentQiuZu);
                break;
            case R.id.release_data_base_line:
                Intent intentData = new Intent(getContext(), DataBaseActivity.class);
                getContext().startActivity(intentData);
                break;

            case R.id.travel_release_guonei_line:
                Intent intentguonei = new Intent(getContext(), ReleaseGuoNeiActivity.class);
                getContext().startActivity(intentguonei);
                break;
            case R.id.travel_release_piaowu_line:
                Intent intentpiaowu = new Intent(getContext(), ReleaseTicketingActivity.class);
                getContext().startActivity(intentpiaowu);
                break;
            case R.id.travel_release_jingwai_line:
                Intent intentjingwai = new Intent(getContext(), OverseasReleaseActivity.class);
                getContext().startActivity(intentjingwai);
                break;
            case R.id.travel_release_zhoubian_line:
                Intent intentzhoubian = new Intent(getContext(), ReleaseZhouBoundaryActivity.class);
                getContext().startActivity(intentzhoubian);
                break;

            case R.id.image_interface_back_house:
                Intent intentHouseBack = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intentHouseBack);
                break;
            case R.id.image_interface_back_travel:
                Intent intentTravelBack = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intentTravelBack);
                break;


        }
    }
}
