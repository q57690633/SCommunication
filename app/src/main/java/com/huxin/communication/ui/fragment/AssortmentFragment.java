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
import android.widget.LinearLayout;
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
import com.huxin.communication.entity.GetMessageEntity;
import com.huxin.communication.http.ApiModule;
import com.huxin.communication.ui.house.phone.AddFriendActivity;
import com.huxin.communication.ui.house.phone.FriendDetailedActivity;
import com.huxin.communication.utils.PreferenceUtil;
import com.sky.kylog.KyLog;

import org.json.JSONArray;
import org.json.JSONException;

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
    private LinearLayout mLinearLayoutText;

    private RecyclerView mRecyclerViewGroup;
    private RecyclerView mRecyclerViewStick;
    private RecyclerView mRecyclerViewCompany;

    private StickAdapter mStickAdapter;
    private CompanyAdapter mCompanyAdapter;
    private GounpAdapter mGounpAdapter;
    private List<FamousEntity> lists;


    private List<FamousEntity> list = new ArrayList<>();

    private String mSearch;

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
        mLinearLayoutText = view.findViewById(R.id.rl_text);


        mImageView.setOnClickListener(this);
    }


    @Override
    protected void loadData() {
//        initData();
        //mAdapter = new FamousAdapter(getContext(), setData());
//        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = lists.get(position).getName();
                String industry = lists.get(position).getIndustryType();
                String phone = lists.get(position).getPhone();
                String starFriend = lists.get(position).getStarFriend();
                int uid = lists.get(position).getId();
                Intent intent = new Intent(getContext(), FriendDetailedActivity.class);
                intent.putExtra(NAME_TAG, name);
                intent.putExtra(ADDRESS_TAG, "");
                intent.putExtra(INDUSTRY_TAG, industry);
                intent.putExtra(PHONE_TAG, phone);
                intent.putExtra(STAR_FRIEND_TAG, starFriend);
                intent.putExtra(UID_TAG, uid);
                intent.putExtra("Friend", "friend");

                getContext().startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        mEditTextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSearch = mEditTextSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(mSearch)) {
                    mLinearLayoutText.setVisibility(View.GONE);
                } else {
                    mLinearLayoutText.setVisibility(View.VISIBLE);
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
                        List<AddressBookEntity.GroupBean> list = getGroupList(AddressBookEntity.getGroup());
                        KyLog.object(list);
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mGounpAdapter = new GounpAdapter(list, getActivity());
                        mRecyclerViewGroup.setAdapter(mGounpAdapter);
                        mRecyclerViewGroup.setLayoutManager(manager);
                    }

                    List<com.huxin.communication.entity.AddressBookEntity.CompanyBean> beanList = AddressBookEntity.getCompany();

                    if (beanList != null && beanList.size() > 0) {
                        List<AddressBookEntity.CompanyBean> list = getCompanyList(beanList);
//                        List<String> Mutelist = getCompanyMuteList(beanList);

                        KyLog.object(AddressBookEntity.getCompany());
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mCompanyAdapter = new CompanyAdapter(list, getActivity());
                        mRecyclerViewCompany.setAdapter(mCompanyAdapter);
                        mRecyclerViewCompany.setLayoutManager(manager);
                    }

                    if (AddressBookEntity.getStarList() != null && AddressBookEntity.getStarList().size() > 0) {
                        KyLog.object(AddressBookEntity.getStarList());
                        List<AddressBookEntity.StarListBean> list = getStarList(AddressBookEntity.getStarList());
//                        List<String> Mutelist = getStarMuteList(AddressBookEntity.getStarList());
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mStickAdapter = new StickAdapter(list, getActivity());
                        mRecyclerViewStick.setAdapter(mStickAdapter);
                        mRecyclerViewStick.setLayoutManager(manager);
//                        mRelativeLayoutStick.setVisibility(View.VISIBLE);
                    } else {
//                        mRelativeLayoutStick.setVisibility(View.GONE);

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
                        lists = getFriendList(list);
//                        List<String> Mutelist = getFriendMuteList(list);
                        mAdapter = new FamousAdapter(getContext(), lists);
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

    private List<AddressBookEntity.CompanyBean> getCompanyList(List<AddressBookEntity.CompanyBean> list) {
        List<AddressBookEntity.CompanyBean> showList = new ArrayList<>();
        try {
            String spId = PreferenceUtil.getString("PersonCompanyTop");
            if (null == spId) {
                return list;
            }
            JSONArray array = new JSONArray(spId);
            for (int i = 0; i < array.length(); i++) {
                String id = array.getString(i);
                for (int j = 0; j < list.size(); j++) {
                    if (id.equalsIgnoreCase(String.valueOf(list.get(j).getId()))) {
                        showList.add(list.get(j));
                        list.remove(j);
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                showList.add(list.get(i));
            }
            if (showList.size() == 0) {
                showList = list;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return showList;
    }

    private List<FamousEntity> getFriendList(List<FamousEntity> list) {
        List<FamousEntity> showList = new ArrayList<>();
        try {
            String spId = PreferenceUtil.getString("PersonTop");
            if (null == spId) {
                return list;
            }
            JSONArray array = new JSONArray(spId);
            for (int i = 0; i < array.length(); i++) {
                String id = array.getString(i);
                for (int j = 0; j < list.size(); j++) {
                    if (id.equalsIgnoreCase(String.valueOf(list.get(j).getId()))) {
                        showList.add(list.get(j));
                        list.remove(j);
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                showList.add(list.get(i));
            }
            if (showList.size() == 0) {
                showList = list;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return showList;
    }

    private List<AddressBookEntity.StarListBean> getStarList(List<AddressBookEntity.StarListBean> list) {
        List<AddressBookEntity.StarListBean> showList = new ArrayList<>();
        try {
            String spId = PreferenceUtil.getString("PersonStarTop");
            KyLog.d(spId);
            if (null == spId) {
                return list;
            }
            JSONArray array = new JSONArray(spId);
            for (int i = 0; i < array.length(); i++) {
                String id = array.getString(i);
                for (int j = 0; j < list.size(); j++) {
                    if (id.equalsIgnoreCase(String.valueOf(list.get(j).getId()))) {
                        showList.add(list.get(j));
                        list.remove(j);
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                showList.add(list.get(i));
            }
            if (showList.size() == 0) {
                showList = list;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return showList;
    }

    private List<AddressBookEntity.GroupBean> getGroupList(List<AddressBookEntity.GroupBean> list) {
        List<AddressBookEntity.GroupBean> showList = new ArrayList<>();
        try {
            String spId = PreferenceUtil.getString("groupTop");
            KyLog.d(spId);
            if (null == spId) {
                return list;
            }
            JSONArray array = new JSONArray(spId);
            for (int i = 0; i < array.length(); i++) {
                String id = array.getString(i);
                KyLog.d(id);
                for (int j = 0; j < list.size(); j++) {
                    if (id.equalsIgnoreCase(String.valueOf(list.get(j).getFlockId()))) {
                        showList.add(list.get(j));
                        list.remove(j);
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                showList.add(list.get(i));
            }
            if (showList.size() == 0) {
                showList = list;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return showList;
    }


    private List<String> getStarMuteList(List<AddressBookEntity.StarListBean> list) {
        List<String> showList = new ArrayList<>();
        try {
            String spId = PreferenceUtil.getString("PersonStarMute");
            if (null == spId) {
                return null;
            }
            JSONArray array = new JSONArray(spId);
            for (int i = 0; i < array.length(); i++) {
                String id = array.getString(i);
                showList.add(id);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return showList;
    }

    private List<String> getCompanyMuteList(List<AddressBookEntity.CompanyBean> list) {
        List<String> showList = new ArrayList<>();
        try {
            String spId = PreferenceUtil.getString("PersonCompanyMute");
            if (null == spId) {
                return null;
            }
            JSONArray array = new JSONArray(spId);
            for (int i = 0; i < array.length(); i++) {
                String id = array.getString(i);
                showList.add(id);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return showList;
    }

    private List<String> getFriendMuteList(List<FamousEntity> list) {
        List<String> showList = new ArrayList<>();
        try {
            String spId = PreferenceUtil.getString("PersonFriendMute");
            if (null == spId) {
                return null;
            }
            JSONArray array = new JSONArray(spId);
            for (int i = 0; i < array.length(); i++) {
                String id = array.getString(i);
                showList.add(id);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return showList;
    }
}
