package com.huxin.communication.entity;

import java.util.ArrayList;

/**
 * Created by yangzanxiong on 2018/1/12.
 */

public class EvnBusEntity {
    private ArrayList<AddressEntity> mData;

    public EvnBusEntity(ArrayList<AddressEntity> mData) {
        this.mData = mData;
    }

    public ArrayList<AddressEntity> getmData() {
        return mData;
    }

    public void setmData(ArrayList<AddressEntity> mData) {
        this.mData = mData;
    }
}
