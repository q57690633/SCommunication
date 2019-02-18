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
 * Created by xiongxiong on 2016/7/25.
 */
public class ReleaseDialogAdapter extends BaseAdapter {
    private List<MyPopVlaues> list;
    private Context context;
    private LayoutInflater inflater;

    public ReleaseDialogAdapter(List<MyPopVlaues> list, Context context) {
        this.list = list;
        this.context = context;
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
        ViewHodle viewHodle;
        if (view == null) {
            viewHodle = new ViewHodle();
            view = inflater.inflate(R.layout.airfeetid_item, null);
            viewHodle.name = (TextView) view.findViewById(R.id.airfeet_tv);
            view.setTag(viewHodle);
        } else {
            viewHodle = (ViewHodle) view.getTag();
        }

        viewHodle.name.setText(list.get(position).getName());
        return view;
    }

    class ViewHodle {
        private TextView name;
    }
}
