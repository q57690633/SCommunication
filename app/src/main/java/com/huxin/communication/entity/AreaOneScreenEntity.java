package com.huxin.communication.entity;

public class AreaOneScreenEntity {


    /**
     * iid : 1
     * city : 北京
     * county :
     * areaOne : 朝阳
     * newOrOld :
     */

    private int id;
    private String city;
    private String county;
    private String areaOne;
    private String newOrOld;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAreaOne() {
        return areaOne;
    }

    public void setAreaOne(String areaOne) {
        this.areaOne = areaOne;
    }

    public String getNewOrOld() {
        return newOrOld;
    }

    public void setNewOrOld(String newOrOld) {
        this.newOrOld = newOrOld;
    }
}
