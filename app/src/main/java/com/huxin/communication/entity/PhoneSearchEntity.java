package com.huxin.communication.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class PhoneSearchEntity implements Parcelable {


    /**
     * uid : 157
     * username :
     * password :
     * phone : 19922286211
     * authCode :
     * registerType :
     * secondPhone :
     * token :
     * time : 0
     * province :
     * city :
     * county :
     * area :
     * companyName : 移民灵敏
     * licenseCode :
     * positions :
     * industryType :
     * invitationCode :
     * storeName :
     * advice :
     * cOrP :
     * secondAuthCode :
     * headUrl :
     * stickNumber : 1
     * matchingMonth :
     * usersig :
     * identifier :
     */

    private int uid;
    private String username;
    private String password;
    private String phone;
    private String authCode;
    private String registerType;
    private String secondPhone;
    private String token;
    private int time;
    private String province;
    private String city;
    private String county;
    private String area;
    private String companyName;
    private String licenseCode;
    private String positions;
    private String industryType;
    private String invitationCode;
    private String storeName;
    private String advice;
    private String cOrP;
    private String secondAuthCode;
    private String headUrl;
    private int stickNumber;
    private String matchingMonth;
    private String usersig;
    private String identifier;

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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getCOrP() {
        return cOrP;
    }

    public void setCOrP(String cOrP) {
        this.cOrP = cOrP;
    }

    public String getSecondAuthCode() {
        return secondAuthCode;
    }

    public void setSecondAuthCode(String secondAuthCode) {
        this.secondAuthCode = secondAuthCode;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getStickNumber() {
        return stickNumber;
    }

    public void setStickNumber(int stickNumber) {
        this.stickNumber = stickNumber;
    }

    public String getMatchingMonth() {
        return matchingMonth;
    }

    public void setMatchingMonth(String matchingMonth) {
        this.matchingMonth = matchingMonth;
    }

    public String getUsersig() {
        return usersig;
    }

    public void setUsersig(String usersig) {
        this.usersig = usersig;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.phone);
        dest.writeString(this.authCode);
        dest.writeString(this.registerType);
        dest.writeString(this.secondPhone);
        dest.writeString(this.token);
        dest.writeInt(this.time);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.county);
        dest.writeString(this.area);
        dest.writeString(this.companyName);
        dest.writeString(this.licenseCode);
        dest.writeString(this.positions);
        dest.writeString(this.industryType);
        dest.writeString(this.invitationCode);
        dest.writeString(this.storeName);
        dest.writeString(this.advice);
        dest.writeString(this.cOrP);
        dest.writeString(this.secondAuthCode);
        dest.writeString(this.headUrl);
        dest.writeInt(this.stickNumber);
        dest.writeString(this.matchingMonth);
        dest.writeString(this.usersig);
        dest.writeString(this.identifier);
    }

    public PhoneSearchEntity() {
    }

    protected PhoneSearchEntity(Parcel in) {
        this.uid = in.readInt();
        this.username = in.readString();
        this.password = in.readString();
        this.phone = in.readString();
        this.authCode = in.readString();
        this.registerType = in.readString();
        this.secondPhone = in.readString();
        this.token = in.readString();
        this.time = in.readInt();
        this.province = in.readString();
        this.city = in.readString();
        this.county = in.readString();
        this.area = in.readString();
        this.companyName = in.readString();
        this.licenseCode = in.readString();
        this.positions = in.readString();
        this.industryType = in.readString();
        this.invitationCode = in.readString();
        this.storeName = in.readString();
        this.advice = in.readString();
        this.cOrP = in.readString();
        this.secondAuthCode = in.readString();
        this.headUrl = in.readString();
        this.stickNumber = in.readInt();
        this.matchingMonth = in.readString();
        this.usersig = in.readString();
        this.identifier = in.readString();
    }

    public static final Parcelable.Creator<PhoneSearchEntity> CREATOR = new Parcelable.Creator<PhoneSearchEntity>() {
        @Override
        public PhoneSearchEntity createFromParcel(Parcel source) {
            return new PhoneSearchEntity(source);
        }

        @Override
        public PhoneSearchEntity[] newArray(int size) {
            return new PhoneSearchEntity[size];
        }
    };
}
