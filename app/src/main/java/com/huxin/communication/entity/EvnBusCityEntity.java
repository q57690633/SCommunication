package com.huxin.communication.entity;

import java.util.ArrayList;
import java.util.List;

public class EvnBusCityEntity {

    private List<AddressEntity.ListBeanX> mData;

    public EvnBusCityEntity(List<AddressEntity.ListBeanX> mData) {
        this.mData = mData;
    }

    public List<AddressEntity.ListBeanX> getmData() {
        return mData;
    }

    public void setmData(List<AddressEntity.ListBeanX> mData) {
        this.mData = mData;
    }
}
