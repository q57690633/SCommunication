package com.huxin.communication.entity;

public class ForeignSpotEntity {


    /**
     * id : 1
     * city_name : 休斯顿
     * nation_name : 美国
     * spot_name : 女神像
     */

    private int id;
    private String city_name;
    private String nation_name;
    private String spot_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getNation_name() {
        return nation_name;
    }

    public void setNation_name(String nation_name) {
        this.nation_name = nation_name;
    }

    public String getSpot_name() {
        return spot_name;
    }

    public void setSpot_name(String spot_name) {
        this.spot_name = spot_name;
    }
}
