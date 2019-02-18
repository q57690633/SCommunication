package com.huxin.communication.entity;

public class ProvinceEntity {


    /**
     * id : 1
     * province_code : “110000”
     * province_name : 北京市
     */

    private int id;
    private String province_code;
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

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }
}
