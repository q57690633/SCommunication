package com.huxin.communication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.adpter.CompanyAdapter;
import com.huxin.communication.adpter.FamousAdapter;
import com.huxin.communication.adpter.GounpAdapter;
import com.huxin.communication.adpter.StickAdapter;
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
    public static final String IMAGE_TAG = "image";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ListView mListView;
    private FamousAdapter mAdapter;
    private ImageView mImageView;
    private RelativeLayout mRelativeLayoutStick;
    private EditText mEditTextSearch;
    private RelativeLayout mRelativeLayoutText;


    private RecyclerView mRecyclerViewGroup;
    private RecyclerView mRecyclerViewStick;
    private RecyclerView mRecyclerViewCompany;

    private StickAdapter mStickAdapter;
    private CompanyAdapter mCompanyAdapter;
    private GounpAdapter mGounpAdapter;


    private List<FamousEntity> list = new ArrayList<>();

    private  String mSearch;

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
        mRecyclerViewGroup = view.findViewById(R.id.group_recycler);
        mRecyclerViewStick = view.findViewById(R.id.stick_recycler);
        mRecyclerViewCompany = view.findViewById(R.id.company_recycler);
        mRelativeLayoutStick = view.findViewById(R.id.stick_rl);
        mEditTextSearch = view.findViewById(R.id.editText_assortment);
        mRelativeLayoutText = view.findViewById(R.id.rl_text);


        mImageView.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        initData();
        //mAdapter = new FamousAdapter(getContext(), setData());
//        mListView.setAdapter(mAdapter);
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

        mEditTextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSearch = mEditTextSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(mSearch)){
                    mRelativeLayoutText.setVisibility(View.GONE);
                }else {
                    mRelativeLayoutText.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

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
                    if (list != null) {
                        list.clear();
                    }

                    if (AddressBookEntity.getGroup() != null && AddressBookEntity.getGroup().size() > 0) {
                        KyLog.object(AddressBookEntity.getGroup());
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mGounpAdapter = new GounpAdapter(AddressBookEntity.getGroup(), getActivity());
                        mRecyclerViewGroup.setAdapter(mGounpAdapter);
                        mRecyclerViewGroup.setLayoutManager(manager);
                    }

                    List<com.huxin.communication.entity.AddressBookEntity.CompanyBean> beanList = AddressBookEntity.getCompany();

                    if (beanList != null && beanList.size() > 0) {
                        KyLog.object(AddressBookEntity.getCompany());
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mCompanyAdapter = new CompanyAdapter(AddressBookEntity.getCompany(), getActivity());
                        mRecyclerViewCompany.setAdapter(mCompanyAdapter);
                        mRecyclerViewCompany.setLayoutManager(manager);
                    }

                    if (AddressBookEntity.getStarList() != null && AddressBookEntity.getStarList().size() > 0) {
                        KyLog.object(AddressBookEntity.getStarList());
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mStickAdapter = new StickAdapter(AddressBookEntity.getStarList(), getActivity());
                        mRecyclerViewStick.setAdapter(mStickAdapter);
                        mRecyclerViewStick.setLayoutManager(manager);
                        mRelativeLayoutStick.setVisibility(View.VISIBLE);
                    } else {
                        mRelativeLayoutStick.setVisibility(View.GONE);

                    }

                    if (AddressBookEntity.getFriendList() != null) {
                        KyLog.object(AddressBookEntity.getFriendList());
                        for (AddressBookEntity.FriendListBean friendListBean : AddressBookEntity.getFriendList()) {
                            FamousEntity famousEntity2 = new FamousEntity();
                            famousEntity2.setName(friendListBean.getUsername());
                            famousEntity2.setImage(friendListBean.getHeadUrl());
                            famousEntity2.setPhone(friendListBean.getPhone());
                            famousEntity2.setIndustryType(friendListBean.getIndustryType());
                            famousEntity2.setStarFriend(friendListBean.getStarFriend());
                            famousEntity2.setId(friendListBean.getUid());
                            famousEntity2.setType(1);
                            list.add(famousEntity2);
                        }
                    }

                    KyLog.object(list);
                    if (list.size() > 0) {
                        mAdapter = new FamousAdapter(getContext(), list);
                        mListView.setAdapter(mAdapter);
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
