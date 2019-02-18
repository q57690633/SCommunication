package com.huxin.communication.entity;

public class InlandCityEntity {


    /**
     * id : 5
     * province_code : 130000
     * city_name : 石家庄市
     * city_code : 130100
     * province_name : 河北省
     */

    private int id;
    private String province_code;
    private String city_name;
    private String city_code;
    private String province_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }
}
