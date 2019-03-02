package com.huxin.communication.entity;

import org.litepal.crud.DataSupport;

/**
 * 名家实体类
 */
public class FamousEntity extends DataSupport{
    private String image;
    private String name;
    private String sortLetters;
    private String firstLetter;
    private String authorAvatar;
    private int id;
    private String phone;
    private String industryType;
    private String starFriend;

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getStarFriend() {
        return starFriend;
    }

    public void setStarFriend(String starFriend) {
        this.starFriend = starFriend;
    }
}
