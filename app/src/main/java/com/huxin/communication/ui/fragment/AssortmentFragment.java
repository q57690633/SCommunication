package com.huxin.communication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.huxin.communication.R;
import com.huxin.communication.adpter.FamousAdapter;
import com.huxin.communication.base.BaseFragment;
import com.huxin.communication.entity.AddressBookEntity;
import com.huxin.communication.entity.FamousEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.house.phone.AddFriendActivity;
import com.huxin.communication.ui.house.phone.FriendDetailedActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 通讯录
 */
public class AssortmentFragment extends BaseFragment implements View.OnClickListener {
    public static final String NAME_TAG = "name";
    public static final String ADDRESS_TAG = "address";
    public static final String INDUSTRY_TAG = "industry";
    public static final String PHONE_TAG = "phone";
    public static final String STAR_FRIEND_TAG = "starFriend";
    public static final String UID_TAG = "uid";
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ListView mListView;
    private FamousAdapter mAdapter;
    private ImageView mImageView;

    private List<FamousEntity> list = new ArrayList<>();

    public AssortmentFragment() {
        // Required empty public constructor
    }

    public static AssortmentFragment newInstance(String param1, String param2) {
        AssortmentFragment fragment = new AssortmentFragment();
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
        return inflater.inflate(R.layout.fragment_assortment, container, false);
    }

    @Override
    protected void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.country_lvcountry);
        mImageView = (ImageView) view.findViewById(R.id.image_phone_person);
        mImageView.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        initData();
        //mAdapter = new FamousAdapter(getContext(), setData());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = list.get(position).getName();
                String industry = list.get(position).getIndustryType();
                String phone = list.get(position).getPhone();
                String starFriend = list.get(position).getStarFriend();
                int uid = list.get(position).getId();
                Intent intent = new Intent(getContext(), FriendDetailedActivity.class);
                intent.putExtra(NAME_TAG, name);
                intent.putExtra(ADDRESS_TAG, "");
                intent.putExtra(INDUSTRY_TAG, industry);
                intent.putExtra(PHONE_TAG, phone);
                intent.putExtra(STAR_FRIEND_TAG, starFriend);
                intent.putExtra(UID_TAG, uid);
                getContext().startActivity(intent);
            }
        });

    }

    @Override
    protected void bindData() {

    }

    private void initData() {
        int uid = PreferenceUtil.getInt("uid");
        String token = PreferenceUtil.getString("token");
        ApiModule.getInstance().addressBook(uid + "", token)
                .subscribe(AddressBookEntity -> {
                    KyLog.i("----------加载通讯录---------");
                    KyLog.object(AddressBookEntity);

                    List<com.huxin.communication.entity.AddressBookEntity.CompanyBean> beanList = AddressBookEntity.getCompany();

                    if(beanList != null) {
                        for(com.huxin.communication.entity.AddressBookEntity.CompanyBean friendListBean : AddressBookEntity.getCompany()) {
                            FamousEntity famousEntity = new FamousEntity();
                            famousEntity.setName(friendListBean.getUsername());
                            famousEntity.setImage(friendListBean.getHeadUrl());
                            famousEntity.setPhone(friendListBean.getPhone());
                            famousEntity.setIndustryType(friendListBean.getIndustryType());
                            famousEntity.setStarFriend(friendListBean.getStarFriend());
                            famousEntity.setId(friendListBean.getId());
                            list.add(famousEntity);
                        }
                        mAdapter = new FamousAdapter(getContext(), list);
                    }else {
                        mAdapter = new FamousAdapter(getContext(), setData());
                    }
                    mListView.setAdapter(mAdapter);
                });
    }

    private List<FamousEntity> setData() {
        List<FamousEntity> list = new ArrayList<>();
        FamousEntity famousEntity = new FamousEntity();
        famousEntity.setFirstLetter("星标朋友");
        famousEntity.setName("店面经理·李宁");
        FamousEntity famousEntity1 = new FamousEntity();
        famousEntity1.setFirstLetter("星标朋友");
        famousEntity1.setName("店面经理·李宁");
        FamousEntity famousEntity2 = new FamousEntity();
        famousEntity2.setFirstLetter("a");
        famousEntity2.setName("店面经理·李宁");
        FamousEntity famousEntity3 = new FamousEntity();
        famousEntity3.setFirstLetter("a");
        famousEntity3.setName("店面经理·李宁");
        FamousEntity famousEntity4 = new FamousEntity();
        famousEntity4.setFirstLetter("d");
        famousEntity4.setName("店面经理·李宁");
        FamousEntity famousEntity5 = new FamousEntity();
        famousEntity5.setFirstLetter("c");
        famousEntity5.setName("店面经理·李宁");
        FamousEntity famousEntity6 = new FamousEntity();
        famousEntity6.setFirstLetter("d");
        famousEntity6.setName("店面经理·李宁");
        FamousEntity famousEntity7 = new FamousEntity();
        famousEntity7.setFirstLetter("a");
        famousEntity7.setName("店面经理·李宁");
        FamousEntity famousEntity8 = new FamousEntity();
        famousEntity8.setFirstLetter("f");
        famousEntity8.setName("店面经理·李宁");

        FamousEntity famousEntity9 = new FamousEntity();
        famousEntity9.setFirstLetter("h");
        famousEntity9.setName("店面经理·李宁");
        FamousEntity famousEntity10 = new FamousEntity();
        famousEntity10.setFirstLetter("s");
        famousEntity10.setName("店面经理·李宁");
        FamousEntity famousEntity11 = new FamousEntity();
        famousEntity11.setFirstLetter("q");
        famousEntity11.setName("店面经理·李宁");
        FamousEntity famousEntity12 = new FamousEntity();
        famousEntity12.setFirstLetter("q");
        famousEntity12.setName("店面经理·李宁");
        FamousEntity famousEntity13 = new FamousEntity();
        famousEntity13.setFirstLetter("f");
        famousEntity13.setName("店面经理·李宁");
        FamousEntity famousEntity14 = new FamousEntity();
        famousEntity14.setFirstLetter("星标朋友");
        famousEntity14.setName("店面经理·李宁");
        list.add(famousEntity);
        list.add(famousEntity1);
        list.add(famousEntity2);
        list.add(famousEntity3);
        list.add(famousEntity4);
        list.add(famousEntity5);
        list.add(famousEntity6);
        list.add(famousEntity7);
        list.add(famousEntity8);
        list.add(famousEntity9);
        list.add(famousEntity10);
        list.add(famousEntity11);
        list.add(famousEntity12);
        list.add(famousEntity13);
        list.add(famousEntity14);

        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_phone_person:
                Intent intent = new Intent(getContext(), AddFriendActivity.class);
                getContext().startActivity(intent);
                break;
        }
    }
}
