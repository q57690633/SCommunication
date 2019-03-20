package com.huxin.communication.entity;

/**
 * Created by yangzanxiong on 2017/11/21.
 */

public class BaseEntity {

    private int image;
    private String name;
    private String price;
    private String cfd_price;
    private String en_url;

    public String getEn_url() {
        return en_url;
    }

    public void setEn_url(String en_url) {
        this.en_url = en_url;
    }

    public String getCfd_price() {
        return cfd_price;
    }

    public void setCfd_price(String cfd_price) {
        this.cfd_price = cfd_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
