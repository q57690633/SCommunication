package com.huxin.communication.entity;

public class UpdateUserPhoneEntity {


    /**
     * uid : 13
     * username :
     * password :
     * phone : 15132412471
     * authCode : 404248
     * registerType :
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwaG9uZSI6IjE3ODAwMzc2OTgyIiwidGltZSI6MTU0NTgxNjY4MzcyNiwiZXhwIjoxNTQ1OTAzMDgzLCJpYXQiOjE1NDU4MTY2ODN9.2CIhOHGWHb-N1DoRbkHltoLcG-jGD2jpQ3ZXtLk4D4M
     * time : 0
     */

    private int uid;
    private String username;
    private String password;
    private String phone;
    private int authCode;
    private String registerType;
    private String token;
    private int time;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
