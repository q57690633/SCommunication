package com.aitangba.pickdatetime.adapter.datetime;

import android.support.annotation.NonNull;

import com.aitangba.pickdatetime.adapter.GeneralWheelAdapter;
import com.aitangba.pickdatetime.bean.DateParams;
import com.aitangba.pickdatetime.bean.DatePick;

import java.util.ArrayList;

/**
 * Created by fhf11991 on 2017/8/29.
 */

public abstract class DatePickAdapter extends GeneralWheelAdapter {

    protected DateParams mDateParams;
    protected final DatePick mDatePick;

    public DatePickAdapter(@NonNull DateParams dateParams, @NonNull DatePick datePick) {
        mDateParams = dateParams;
        mDatePick = datePick;
        refreshValues();
    }

    public abstract int getCurrentIndex();

    public abstract void refreshValues();

    @Override
    public String getItem(int position) {
        int value = mData.get(position);
        return value < 10 ? ("0" + value) : String.valueOf(value);
    }

    public final ArrayList<Integer> getArray(int maxNum) {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 1; i <= maxNum; i++) {
            values.add(i);
        }
        return values;
    }
}
