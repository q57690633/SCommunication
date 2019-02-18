package com.huxin.communication.entity;

import java.util.ArrayList;
import java.util.List;

public class EvnBusCountyEntity {
    private List<AddressEntity.ListBeanX.ListBean> mData;

    public EvnBusCountyEntity(List<AddressEntity.ListBeanX.ListBean> mData) {
        this.mData = mData;
    }

    public List<AddressEntity.ListBeanX.ListBean> getmData() {
        return mData;
    }

    public void setmData(List<AddressEntity.ListBeanX.ListBean> mData) {
        this.mData = mData;
    }
}
