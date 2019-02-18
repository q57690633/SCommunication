package com.huxin.communication.adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.huxin.communication.R;
import com.huxin.communication.entity.FamousEntity;

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
    private boolean isClicked = false;

    public TuiJianPhoneAdapter(Context mContext, List<FamousEntity> list) {
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
            view = mInflater.inflate(R.layout.item_recycler_phone, arg2, false);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.name_famous);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.mLinearLayout = (LinearLayout) view.findViewById(R.id.line_phone);
            viewHolder.mImageView = (ImageView) view.findViewById(R.id.image_duoxuan);
            viewHolder.mImageViewClicked = (ImageView) view.findViewById(R.id.image_duoxuan_clicked);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.image = (ImageView) view.findViewById(R.id.image);
        setinte(viewHolder.tvLetter, position, mContent);
        viewHolder.tvTitle.setText(list.get(position).getName());

        ViewHolder finalViewHolder = viewHolder;
        viewHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("qiugou", "isClicked == " + isClicked);
                if (isClicked) {
                    isClicked = false;
                    finalViewHolder.mImageViewClicked.setVisibility(View.GONE);
                    finalViewHolder.mImageView.setVisibility(View.VISIBLE);
                } else {
                    isClicked = true;
                    finalViewHolder.mImageViewClicked.setVisibility(View.VISIBLE);
                    finalViewHolder.mImageView.setVisibility(View.GONE);
                }
                notifyDataSetChanged();
            }
        });
        return view;

    }

    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        ImageView image;
        LinearLayout mLinearLayout;
        ImageView mImageView;
        ImageView mImageViewClicked;
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
