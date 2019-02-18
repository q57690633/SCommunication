package com.huxin.communication.entity;

public class ForeignCityEntity {


    /**
     * id : 1
     * nation_name : 美国
     * city_name : 休斯顿
     */

    private int id;
    private String nation_name;
    private String city_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNation_name() {
        return nation_name;
    }

    public void setNation_name(String nation_name) {
        this.nation_name = nation_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
