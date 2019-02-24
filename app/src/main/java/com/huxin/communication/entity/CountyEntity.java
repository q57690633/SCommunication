package com.huxin.communication.entity;

public class CountyEntity {


    /**
     * id : 1
     * tcId : 1
     * county : 朝阳区
     */

    private int id;
    private int tcId;
    private String county;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTcId() {
        return tcId;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
