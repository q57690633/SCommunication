package com.huxin.communication.custom;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.huxin.communication.R;
import com.huxin.communication.adpter.ReleaseDialogAdapter;
import com.huxin.communication.entity.MyPopVlaues;
import com.sky.kylog.KyLog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiongxiong on 2016/7/25.
 */
public class ReleaseDialog extends Dialog {
    private Activity activity;

    private String company_id;

    private ListView listView;

    private ReleaseDialogAdapter adapter;

    private List<MyPopVlaues> lists = new ArrayList<>();

    public ReleaseDialog(Activity activity, List<MyPopVlaues> list) {
        super(activity, R.style.custom_dialog);
        this.activity = activity;
        this.lists = list;
//        this.airFeetCallBack = airFeetCallBack;
        KyLog.d(list.size() + "");
        setView();
        initSize();
    }

    private void initSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width * 50 / 100;
        getWindow().setAttributes(lp);
    }


    private void setView() {
        setContentView(R.layout.dialog_airfeet);
        listView = (ListView) findViewById(R.id.dialog_lv);
        if (lists != null && lists.size() > 0) {
            adapter = new ReleaseDialogAdapter(lists, getContext());
            listView.setAdapter(adapter);
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                    cancel();
//                    PreferenceUtil.putString("name", lists.get(position));
//
//                }
//            });
        }

    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        listView.setOnItemClickListener(listener);
    }
//    public ArrayList<AirFeetIdEntity> airfeetid(){
////        ArrayList<AirFeetIdEntity> list = new ArrayList<>();
////        list.add(new AirFeetIdEntity("1"));
////        list.add(new AirFeetIdEntity("2"));
////        list.add(new AirFeetIdEntity("3"));
////        list.add(new AirFeetIdEntity("4"));
//
//        return list;
//    }
}
