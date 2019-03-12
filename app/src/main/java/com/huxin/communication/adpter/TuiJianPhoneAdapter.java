package com.huxin.communication.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.huxin.communication.R;
import com.huxin.communication.entity.FamousEntity;
import com.huxin.communication.entity.SaleOfScreeningEntity;
import com.huxin.communication.entity.UserInfoEntity;
import com.huxin.communication.listener.TuiJianPhoneListener;
import com.huxin.communication.utils.JsonUitil;
import com.huxin.communication.utils.JsonUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzanxiong on 2018/12/20.
 */

public class TuiJianPhoneAdapter extends BaseAdapter implements SectionIndexer {

    private List<FamousEntity> list = null;

    private Context mContext;
    private LayoutInflater mInflater;
    private int[] ints;
    private boolean isClicked = true;
    private TuiJianPhoneListener mTuiJianPhoneListener;

    List<String> imageList = new ArrayList<>();


    public TuiJianPhoneAdapter(Context mContext, List<FamousEntity> list) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
//        setTopNum();
    }

    public void setTuiJianPhoneListener(TuiJianPhoneListener tuiJianPhoneListener) {
        mTuiJianPhoneListener = tuiJianPhoneListener;
    }

    public void updateListView(List<FamousEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
//        return 10;
        return this.list.size();

    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_recycler_phone, arg2, false);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.name_famous);
            viewHolder.mLinearLayout = (LinearLayout) view.findViewById(R.id.line_phone);
//            viewHolder.mImageView = (ImageView) view.findViewById(R.id.image_duoxuan);
            viewHolder.radioButton = (CheckBox) view.findViewById(R.id.image_duoxuan);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);


            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        setinte(viewHolder.tvLetter, position, mContent);
        viewHolder.tvTitle.setText(list.get(position).getName());

        if (!TextUtils.isEmpty(list.get(position).getImage())) {
            ImageLoader.getInstance().displayImage(list.get(position).getImage(), viewHolder.image);
        } else {
            viewHolder.image.setBackgroundResource(R.drawable.head2);
        }

        viewHolder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mTuiJianPhoneListener != null) {
                    ArrayList<UserInfoEntity> listUserInfo = new ArrayList<>();
                    UserInfoEntity userInfoEntity = new UserInfoEntity();
                    userInfoEntity.setImageHead(list.get(position).getImage());
                    userInfoEntity.setName(list.get(position).getName());
                    userInfoEntity.setPhone(list.get(position).getPhone());
                    userInfoEntity.setUid(list.get(position).getId());
                    if(listUserInfo.size() == 0) {
                        listUserInfo.add(userInfoEntity);
                    }else {
                        Toast.makeText(mContext, "暂时只能分享给一位好友，请重新选择", Toast.LENGTH_SHORT).show();
                    }
                    mTuiJianPhoneListener.updateImage(list.get(position).getImage(), b);
                    if (listUserInfo.size() > 0) {
                        mTuiJianPhoneListener.updateUserInfo(JsonUitil.getData(listUserInfo), b);
                    }
                }
            }
        });

        return view;

    }


    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        ImageView image;
        LinearLayout mLinearLayout;
        //        ImageView mImageView;
        CheckBox radioButton;
    }

    public int getSectionForPosition(int position) {
        return Integer.valueOf(list.get(position).getFirstLetter().toUpperCase());
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 添加列表项
     *
     * @param item
     */
    public void addItem(FamousEntity item) {
        list.add(item);
        setTopNum();
    }


    private void setTopNum() {
        List<String> s = new ArrayList<>();
        for (FamousEntity a : list) {
            if (!s.contains(a.getFirstLetter())) {
                s.add(a.getFirstLetter());
            }
        }
        System.out.println(s.size());
        ints = new int[s.size()];
        for (int i = 0; i < s.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getFirstLetter().equals(s.get(i))) {
                    ints[i] = j;
                    break;
                }
            }
        }
    }

    private void setinte(TextView dot, int position, FamousEntity entity) {
        for (int num : ints) {
            if (position == num) {
                dot.setVisibility(View.VISIBLE);
                dot.setText(entity.getFirstLetter().toUpperCase());

                break;
            } else {
                dot.setVisibility(View.GONE);
            }
        }
    }
}
