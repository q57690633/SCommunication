package com.huxin.communication.entity;

import com.google.gson.annotations.SerializedName;

public class AppPayEntity {


    /**
     * package : Sign=WXPay
     * appid : wx890797a15ec36142
     * extdata : 13
     * sign : 79F1230CE4FA22091309ED07BCBCA188
     * partnerid : 1523179451
     * prepayid : wx20154507284292608a92b24b1883033004
     * noncestr : K3WJY5FoGjRwjh8M
     * timestamp : 1547970323
     */

    @SerializedName("package")
    private String packageX;
    private String appid;
    private String extdata;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getExtdata() {
        return extdata;
    }

    public void setExtdata(String extdata) {
        this.extdata = extdata;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
