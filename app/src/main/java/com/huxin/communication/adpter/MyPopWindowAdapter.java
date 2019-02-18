package com.huxin.communication.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.huxin.communication.R;
import com.huxin.communication.entity.MyPopVlaues;

import java.util.List;

/**
 * Created by pc on 2015/12/7.
 */
public class MyPopWindowAdapter extends BaseAdapter {
    private List<MyPopVlaues> list;
    private Context context;
    private LayoutInflater inflater;

    public MyPopWindowAdapter(Context context, List<MyPopVlaues> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHoder viewHoder;
        if (view == null) {
            viewHoder = new ViewHoder();
            view = inflater.inflate(R.layout.item_pop_activity, null);
            viewHoder.TVpop = (TextView) view.findViewById(R.id.tv_pop);
            view.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) view.getTag();
        }
        viewHoder.TVpop.setText(list.get(position).getName());
        return view;
    }

    class ViewHoder {
        private TextView TVpop;
    }
}
