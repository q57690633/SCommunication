package com.huxin.communication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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
        showProgressDialog();
        ApiModule.getInstance().addressBook(uid + "", token)
                .subscribe(AddressBookEntity -> {
                    KyLog.i("----------加载通讯录---------");
                    KyLog.object(AddressBookEntity);
                    cancelProgressDialog();

                    if (AddressBookEntity.getGroup() != null) {
                        for (AddressBookEntity.GroupBean friendListBean : AddressBookEntity.getGroup()) {
                            FamousEntity famousEntity = new FamousEntity();
                            famousEntity.setName(friendListBean.getFlockName());
                            famousEntity.setImage(friendListBean.getUrl());
//                            famousEntity.setPhone(friendListBean.getFlockId());
//                            famousEntity.setIndustryType(friendListBean.getIndustryType());
//                            famousEntity.setStarFriend(friendListBean.getStarFriend());
//                            famousEntity.setId(friendListBean.getUid());
                            famousEntity.setType(2);
                            list.add(famousEntity);
                        }
                    }

                    List<com.huxin.communication.entity.AddressBookEntity.CompanyBean> beanList = AddressBookEntity.getCompany();
                    if (list != null) {
                        list.clear();
                    }
                    if (beanList != null) {
                        for (com.huxin.communication.entity.AddressBookEntity.CompanyBean friendListBean : AddressBookEntity.getCompany()) {
                            FamousEntity famousEntity = new FamousEntity();
                            famousEntity.setName(friendListBean.getUsername());
                            famousEntity.setImage(friendListBean.getHeadUrl());
                            famousEntity.setPhone(friendListBean.getPhone());
                            famousEntity.setIndustryType(friendListBean.getIndustryType());
                            famousEntity.setStarFriend(friendListBean.getStarFriend());
                            famousEntity.setId(friendListBean.getUid());
                            famousEntity.setType(1);
                            list.add(famousEntity);
                        }

                    }

                    if (AddressBookEntity.getFriendList() != null) {
                        for (AddressBookEntity.FriendListBean friendListBean : AddressBookEntity.getFriendList()) {
                            FamousEntity famousEntity = new FamousEntity();
                            famousEntity.setName(friendListBean.getUsername());
                            famousEntity.setImage(friendListBean.getHeadUrl());
                            famousEntity.setPhone(friendListBean.getPhone());
                            famousEntity.setIndustryType(friendListBean.getIndustryType());
                            famousEntity.setStarFriend(friendListBean.getStarFriend());
                            famousEntity.setId(friendListBean.getUid());
                            famousEntity.setType(1);
                            list.add(famousEntity);
                        }
                    }


                    if (AddressBookEntity.getStarList() != null) {
                        for (AddressBookEntity.StarListBean friendListBean : AddressBookEntity.getStarList()) {
                            FamousEntity famousEntity = new FamousEntity();
                            famousEntity.setName(friendListBean.getUsername());
                            famousEntity.setImage(friendListBean.getHeadUrl());
                            famousEntity.setPhone(friendListBean.getPhone());
                            famousEntity.setIndustryType(friendListBean.getIndustryType());
                            famousEntity.setStarFriend(friendListBean.getStarFriend());
                            famousEntity.setId(friendListBean.getUid());
                            famousEntity.setType(1);
                            list.add(famousEntity);
                        }
                    }


                    if (list.size() > 0) {
                        mAdapter = new FamousAdapter(getContext(), list);
                        mListView.setAdapter(mAdapter);
                    } else {
//                        mAdapter = new FamousAdapter(getContext(), setData());
                        Toast.makeText(getContext(), "数据为空", Toast.LENGTH_SHORT).show();
                    }
                }, throwable -> {
                    KyLog.d(throwable.toString());
                    cancelProgressDialog();
                    Toast.makeText(getContext(), throwable.getMessage().toString(), Toast.LENGTH_SHORT).show();
                });

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
