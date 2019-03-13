package com.huxin.communication.entity;

import com.google.gson.annotations.SerializedName;

public class SelectMessageEntity {


    /**
     * message : 6,5,1,0,0
     * group : 6,5,1,0,0
     * company : 6,5,1,0,0
     */
    @SerializedName("个人信息")
    public String message;
    @SerializedName("百行同业科技有限公司")
    public String group;
    @SerializedName("本公司总群")
    public String company;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
