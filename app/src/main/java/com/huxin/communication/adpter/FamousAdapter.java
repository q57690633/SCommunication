package com.huxin.communication.adpter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huxin.communication.R;
import com.huxin.communication.entity.FamousEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class FamousAdapter extends BaseAdapter implements SectionIndexer {

    private static final int GOUNP = 2;
    private static final int FRIEND = 1;

    private List<FamousEntity> list = null;
    private Context mContext;
    private LayoutInflater mInflater;
    private int[] ints;

//    @Override
//    public int getItemViewType(int position) {
//
//        if (list.get(position).getType() == FRIEND){
//            return FRIEND;
//        }else if (list.get(position).getType() == GOUNP){
//            return GOUNP;
//        }
//
//        return FRIEND;
//    }

    public FamousAdapter(Context mContext, List<FamousEntity> list) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
        setTopNum();
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
        final FamousEntity mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.activity_group_member_item, arg2, false);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.name_famous);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.tvLl = (LinearLayout) view.findViewById(R.id.tel_ll);
            viewHolder.tvPhone = (TextView) view.findViewById(R.id.tel);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.image = (ImageView) view.findViewById(R.id.image);
//        setinte(viewHolder.tvLetter, position, mContent);
        viewHolder.tvTitle.setText(list.get(position).getName());
        viewHolder.tvPhone.setText(list.get(position).getPhone());


        if (TextUtils.isEmpty(list.get(position).getImage())){
            Glide.with(mContext).load(list.get(position).getImage()).into(viewHolder.image);

        }else {
            viewHolder.image.setBackgroundResource(R.drawable.head2);
        }

        viewHolder.tvLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = list.get(position).getPhone();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                mContext.startActivity(intent);
            }
        });
        return view;

    }

    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        ImageView image;
        LinearLayout tvLl;
        TextView tvPhone;
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
                if(list.get(j).getFirstLetter() != null) {
                    if (list.get(j).getFirstLetter().equals(s.get(i))) {
                        ints[i] = j;
                        break;
                    }
                }
            }
        }
    }
    private void setinte(TextView dot, int position, FamousEntity entity) {
        for (int num : ints) {
            if (position == num) {
                dot.setVisibility(View.VISIBLE);
                if(entity.getFirstLetter() != null) {
                    dot.setText(entity.getFirstLetter().toUpperCase());
                }

                break;
            } else {
                dot.setVisibility(View.GONE);
            }
        }
    }
}