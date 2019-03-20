package com.huxin.communication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class PersonProductEntity implements Parcelable {


    /**
     * curPage : 1
     * pageSize : 1
     * list : [{"acreage":23,"areaId":102,"areaOne":"","city":"","collectState":0,"curPage":"","dateNumber":0,"element":0,"exclusive":2,"files":[],"findNumber":0,"fitment":"未交房","fitmentList":[],"floorAge":"","floorAgeList":[],"floorNumber":12,"floorSize":"","houseHoldAppliances":"无家具无家电","houseList":[],"houseNumber":"","houseType":"四室","houseTypeList":[],"id":68,"keying":1,"loans":0,"maxAcreage":0,"maxPrice":0,"minAcreage":0,"minPrice":0,"newOrOld":0,"orientation":"","orientationList":[],"ownership":"","paymentType":"","pdu":"","photoUrl":"","price":0,"productType":2,"publicNumber":1,"publicTime":{"date":20,"day":3,"hours":18,"minutes":44,"month":2,"nanos":0,"seconds":11,"time":1553078651000,"timezoneOffset":-480,"year":119},"purpose":"","purposeList":[],"saleAndStick":0,"secondId":208,"showTime":0,"simpleNumber":0,"stick":1,"stickTime":1553078651295,"tabClassify":"","tabId":"","tabName":"","title":"","totalFloorNumber":12,"uid":181,"userModel":{"advice":"","area":"","authCode":0,"cOrP":"","city":"","companyName":"","county":"","headUrl":"","identifier":"","industryType":"","invitationCode":"","licenseCode":"","matchingMonth":0,"password":"","phone":"","phoneState":0,"positions":"","province":"","registerType":0,"secondAuthCode":0,"secondPhone":"","stickNumber":0,"storeName":"","time":0,"token":"","uid":0,"username":"","usersig":""},"villageName":"如花小区"}]
     */

    private String curPage;
    private int pageSize;
    private ArrayList<ListBean> list;

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ArrayList<ListBean> getList() {
        return list;
    }

    public void setList(ArrayList<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {


        /**
         * acreage : 23.0
         * areaId : 102
         * areaOne :
         * city :
         * collectState : 0
         * curPage :
         * dateNumber : 0
         * element : 0
         * exclusive : 2
         * files : []
         * findNumber : 0
         * fitment : 未交房
         * fitmentList : []
         * floorAge :
         * floorAgeList : []
         * floorNumber : 12
         * floorSize :
         * houseHoldAppliances : 无家具无家电
         * houseList : []
         * houseNumber :
         * houseType : 四室
         * houseTypeList : []
         * id : 68
         * keying : 1
         * loans : 0
         * maxAcreage : 0.0
         * maxPrice : 0.0
         * minAcreage : 0.0
         * minPrice : 0.0
         * newOrOld : 0
         * orientation :
         * orientationList : []
         * ownership :
         * paymentType :
         * pdu :
         * photoUrl :
         * price : 0.0
         * productType : 2
         * publicNumber : 1
         * publicTime : {"date":20,"day":3,"hours":18,"minutes":44,"month":2,"nanos":0,"seconds":11,"time":1553078651000,"timezoneOffset":-480,"year":119}
         * purpose :
         * purposeList : []
         * saleAndStick : 0
         * secondId : 208
         * showTime : 0
         * simpleNumber : 0
         * stick : 1
         * stickTime : 1553078651295
         * tabClassify :
         * tabId :
         * tabName :
         * title :
         * totalFloorNumber : 12
         * uid : 181
         * userModel : {"advice":"","area":"","authCode":0,"cOrP":"","city":"","companyName":"","county":"","headUrl":"","identifier":"","industryType":"","invitationCode":"","licenseCode":"","matchingMonth":0,"password":"","phone":"","phoneState":0,"positions":"","province":"","registerType":0,"secondAuthCode":0,"secondPhone":"","stickNumber":0,"storeName":"","time":0,"token":"","uid":0,"username":"","usersig":""}
         * villageName : 如花小区
         */
        private String totalPrice;
        private String unitPrice;
        private double acreage;
        private int areaId;
        private String areaOne;
        private String city;
        private int collectState;
        private String curPage;
        private int dateNumber;
        private int element;
        private int exclusive;
        private int findNumber;
        private String fitment;
        private String floorAge;
        private int floorNumber;
        private String floorSize;
        private String houseHoldAppliances;
        private String houseNumber;
        private String houseType;
        private int id;
        private int keying;
        private int loans;
        private double maxAcreage;
        private double maxPrice;
        private double minAcreage;
        private double minPrice;
        private int newOrOld;
        private String orientation;
        private String ownership;
        private String paymentType;
        private String pdu;
        private String photoUrl;
        private double price;
        private int productType;
        private int publicNumber;
        private PublicTimeBean publicTime;
        private String purpose;
        private int saleAndStick;
        private int secondId;
        private int showTime;
        private int simpleNumber;
        private int stick;
        private long stickTime;
        private String tabClassify;
        private String tabId;
        private String tabName;
        private String title;
        private int totalFloorNumber;
        private int uid;
        private UserModelBean userModel;
        private String villageName;
        private List<String> files;
        private List<String> fitmentList;
        private List<String> floorAgeList;
        private List<String> houseList;
        private List<String> houseTypeList;
        private List<String> orientationList;
        private List<String> purposeList;
        private String remark;
        private String permit;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPermit() {
            return permit;
        }

        public void setPermit(String permit) {
            this.permit = permit;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getAcreage() {
            return acreage;
        }

        public void setAcreage(double acreage) {
            this.acreage = acreage;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaOne() {
            return areaOne;
        }

        public void setAreaOne(String areaOne) {
            this.areaOne = areaOne;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCollectState() {
            return collectState;
        }

        public void setCollectState(int collectState) {
            this.collectState = collectState;
        }

        public String getCurPage() {
            return curPage;
        }

        public void setCurPage(String curPage) {
            this.curPage = curPage;
        }

        public int getDateNumber() {
            return dateNumber;
        }

        public void setDateNumber(int dateNumber) {
            this.dateNumber = dateNumber;
        }

        public int getElement() {
            return element;
        }

        public void setElement(int element) {
            this.element = element;
        }

        public int getExclusive() {
            return exclusive;
        }

        public void setExclusive(int exclusive) {
            this.exclusive = exclusive;
        }

        public int getFindNumber() {
            return findNumber;
        }

        public void setFindNumber(int findNumber) {
            this.findNumber = findNumber;
        }

        public String getFitment() {
            return fitment;
        }

        public void setFitment(String fitment) {
            this.fitment = fitment;
        }

        public String getFloorAge() {
            return floorAge;
        }

        public void setFloorAge(String floorAge) {
            this.floorAge = floorAge;
        }

        public int getFloorNumber() {
            return floorNumber;
        }

        public void setFloorNumber(int floorNumber) {
            this.floorNumber = floorNumber;
        }

        public String getFloorSize() {
            return floorSize;
        }

        public void setFloorSize(String floorSize) {
            this.floorSize = floorSize;
        }

        public String getHouseHoldAppliances() {
            return houseHoldAppliances;
        }

        public void setHouseHoldAppliances(String houseHoldAppliances) {
            this.houseHoldAppliances = houseHoldAppliances;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getKeying() {
            return keying;
        }

        public void setKeying(int keying) {
            this.keying = keying;
        }

        public int getLoans() {
            return loans;
        }

        public void setLoans(int loans) {
            this.loans = loans;
        }

        public double getMaxAcreage() {
            return maxAcreage;
        }

        public void setMaxAcreage(double maxAcreage) {
            this.maxAcreage = maxAcreage;
        }

        public double getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(double maxPrice) {
            this.maxPrice = maxPrice;
        }

        public double getMinAcreage() {
            return minAcreage;
        }

        public void setMinAcreage(double minAcreage) {
            this.minAcreage = minAcreage;
        }

        public double getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(double minPrice) {
            this.minPrice = minPrice;
        }

        public int getNewOrOld() {
            return newOrOld;
        }

        public void setNewOrOld(int newOrOld) {
            this.newOrOld = newOrOld;
        }

        public String getOrientation() {
            return orientation;
        }

        public void setOrientation(String orientation) {
            this.orientation = orientation;
        }

        public String getOwnership() {
            return ownership;
        }

        public void setOwnership(String ownership) {
            this.ownership = ownership;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getPdu() {
            return pdu;
        }

        public void setPdu(String pdu) {
            this.pdu = pdu;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public int getPublicNumber() {
            return publicNumber;
        }

        public void setPublicNumber(int publicNumber) {
            this.publicNumber = publicNumber;
        }

        public PublicTimeBean getPublicTime() {
            return publicTime;
        }

        public void setPublicTime(PublicTimeBean publicTime) {
            this.publicTime = publicTime;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public int getSaleAndStick() {
            return saleAndStick;
        }

        public void setSaleAndStick(int saleAndStick) {
            this.saleAndStick = saleAndStick;
        }

        public int getSecondId() {
            return secondId;
        }

        public void setSecondId(int secondId) {
            this.secondId = secondId;
        }

        public int getShowTime() {
            return showTime;
        }

        public void setShowTime(int showTime) {
            this.showTime = showTime;
        }

        public int getSimpleNumber() {
            return simpleNumber;
        }

        public void setSimpleNumber(int simpleNumber) {
            this.simpleNumber = simpleNumber;
        }

        public int getStick() {
            return stick;
        }

        public void setStick(int stick) {
            this.stick = stick;
        }

        public long getStickTime() {
            return stickTime;
        }

        public void setStickTime(long stickTime) {
            this.stickTime = stickTime;
        }

        public String getTabClassify() {
            return tabClassify;
        }

        public void setTabClassify(String tabClassify) {
            this.tabClassify = tabClassify;
        }

        public String getTabId() {
            return tabId;
        }

        public void setTabId(String tabId) {
            this.tabId = tabId;
        }

        public String getTabName() {
            return tabName;
        }

        public void setTabName(String tabName) {
            this.tabName = tabName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTotalFloorNumber() {
            return totalFloorNumber;
        }

        public void setTotalFloorNumber(int totalFloorNumber) {
            this.totalFloorNumber = totalFloorNumber;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public UserModelBean getUserModel() {
            return userModel;
        }

        public void setUserModel(UserModelBean userModel) {
            this.userModel = userModel;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public List<String> getFiles() {
            return files;
        }

        public void setFiles(List<String> files) {
            this.files = files;
        }

        public List<String> getFitmentList() {
            return fitmentList;
        }

        public void setFitmentList(List<String> fitmentList) {
            this.fitmentList = fitmentList;
        }

        public List<String> getFloorAgeList() {
            return floorAgeList;
        }

        public void setFloorAgeList(List<String> floorAgeList) {
            this.floorAgeList = floorAgeList;
        }

        public List<?> getHouseList() {
            return houseList;
        }

        public void setHouseList(List<String> houseList) {
            this.houseList = houseList;
        }

        public List<String> getHouseTypeList() {
            return houseTypeList;
        }

        public void setHouseTypeList(List<String> houseTypeList) {
            this.houseTypeList = houseTypeList;
        }

        public List<String> getOrientationList() {
            return orientationList;
        }

        public void setOrientationList(List<String> orientationList) {
            this.orientationList = orientationList;
        }

        public List<String> getPurposeList() {
            return purposeList;
        }

        public void setPurposeList(List<String> purposeList) {
            this.purposeList = purposeList;
        }

        public static class PublicTimeBean implements Parcelable {


            /**
             * date : 20
             * day : 3
             * hours : 18
             * minutes : 44
             * month : 2
             * nanos : 0
             * seconds : 11
             * time : 1553078651000
             * timezoneOffset : -480
             * year : 119
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.date);
                dest.writeInt(this.day);
                dest.writeInt(this.hours);
                dest.writeInt(this.minutes);
                dest.writeInt(this.month);
                dest.writeInt(this.nanos);
                dest.writeInt(this.seconds);
                dest.writeLong(this.time);
                dest.writeInt(this.timezoneOffset);
                dest.writeInt(this.year);
            }

            public PublicTimeBean() {
            }

            protected PublicTimeBean(Parcel in) {
                this.date = in.readInt();
                this.day = in.readInt();
                this.hours = in.readInt();
                this.minutes = in.readInt();
                this.month = in.readInt();
                this.nanos = in.readInt();
                this.seconds = in.readInt();
                this.time = in.readLong();
                this.timezoneOffset = in.readInt();
                this.year = in.readInt();
            }

            public static final Creator<PublicTimeBean> CREATOR = new Creator<PublicTimeBean>() {
                @Override
                public PublicTimeBean createFromParcel(Parcel source) {
                    return new PublicTimeBean(source);
                }

                @Override
                public PublicTimeBean[] newArray(int size) {
                    return new PublicTimeBean[size];
                }
            };
        }

        public static class UserModelBean implements Parcelable {

            /**
             * advice :
             * area :
             * authCode : 0
             * cOrP :
             * city :
             * companyName :
             * county :
             * headUrl :
             * identifier :
             * industryType :
             * invitationCode :
             * licenseCode :
             * matchingMonth : 0
             * password :
             * phone :
             * phoneState : 0
             * positions :
             * province :
             * registerType : 0
             * secondAuthCode : 0
             * secondPhone :
             * stickNumber : 0
             * storeName :
             * time : 0
             * token :
             * uid : 0
             * username :
             * usersig :
             */

            private String advice;
            private String area;
            private int authCode;
            private String cOrP;
            private String city;
            private String companyName;
            private String county;
            private String headUrl;
            private String identifier;
            private String industryType;
            private String invitationCode;
            private String licenseCode;
            private int matchingMonth;
            private String password;
            private String phone;
            private int phoneState;
            private String positions;
            private String province;
            private int registerType;
            private int secondAuthCode;
            private String secondPhone;
            private int stickNumber;
            private String storeName;
            private int time;
            private String token;
            private int uid;
            private String username;
            private String usersig;

            public String getAdvice() {
                return advice;
            }

            public void setAdvice(String advice) {
                this.advice = advice;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getAuthCode() {
                return authCode;
            }

            public void setAuthCode(int authCode) {
                this.authCode = authCode;
            }

            public String getCOrP() {
                return cOrP;
            }

            public void setCOrP(String cOrP) {
                this.cOrP = cOrP;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getCounty() {
                return county;
            }

            public void setCounty(String county) {
                this.county = county;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getIdentifier() {
                return identifier;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
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

            public String getLicenseCode() {
                return licenseCode;
            }

            public void setLicenseCode(String licenseCode) {
                this.licenseCode = licenseCode;
            }

            public int getMatchingMonth() {
                return matchingMonth;
            }

            public void setMatchingMonth(int matchingMonth) {
                this.matchingMonth = matchingMonth;
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

            public int getPhoneState() {
                return phoneState;
            }

            public void setPhoneState(int phoneState) {
                this.phoneState = phoneState;
            }

            public String getPositions() {
                return positions;
            }

            public void setPositions(String positions) {
                this.positions = positions;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getRegisterType() {
                return registerType;
            }

            public void setRegisterType(int registerType) {
                this.registerType = registerType;
            }

            public int getSecondAuthCode() {
                return secondAuthCode;
            }

            public void setSecondAuthCode(int secondAuthCode) {
                this.secondAuthCode = secondAuthCode;
            }

            public String getSecondPhone() {
                return secondPhone;
            }

            public void setSecondPhone(String secondPhone) {
                this.secondPhone = secondPhone;
            }

            public int getStickNumber() {
                return stickNumber;
            }

            public void setStickNumber(int stickNumber) {
                this.stickNumber = stickNumber;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

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

            public String getUsersig() {
                return usersig;
            }

            public void setUsersig(String usersig) {
                this.usersig = usersig;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.advice);
                dest.writeString(this.area);
                dest.writeInt(this.authCode);
                dest.writeString(this.cOrP);
                dest.writeString(this.city);
                dest.writeString(this.companyName);
                dest.writeString(this.county);
                dest.writeString(this.headUrl);
                dest.writeString(this.identifier);
                dest.writeString(this.industryType);
                dest.writeString(this.invitationCode);
                dest.writeString(this.licenseCode);
                dest.writeInt(this.matchingMonth);
                dest.writeString(this.password);
                dest.writeString(this.phone);
                dest.writeInt(this.phoneState);
                dest.writeString(this.positions);
                dest.writeString(this.province);
                dest.writeInt(this.registerType);
                dest.writeInt(this.secondAuthCode);
                dest.writeString(this.secondPhone);
                dest.writeInt(this.stickNumber);
                dest.writeString(this.storeName);
                dest.writeInt(this.time);
                dest.writeString(this.token);
                dest.writeInt(this.uid);
                dest.writeString(this.username);
                dest.writeString(this.usersig);
            }

            public UserModelBean() {
            }

            protected UserModelBean(Parcel in) {
                this.advice = in.readString();
                this.area = in.readString();
                this.authCode = in.readInt();
                this.cOrP = in.readString();
                this.city = in.readString();
                this.companyName = in.readString();
                this.county = in.readString();
                this.headUrl = in.readString();
                this.identifier = in.readString();
                this.industryType = in.readString();
                this.invitationCode = in.readString();
                this.licenseCode = in.readString();
                this.matchingMonth = in.readInt();
                this.password = in.readString();
                this.phone = in.readString();
                this.phoneState = in.readInt();
                this.positions = in.readString();
                this.province = in.readString();
                this.registerType = in.readInt();
                this.secondAuthCode = in.readInt();
                this.secondPhone = in.readString();
                this.stickNumber = in.readInt();
                this.storeName = in.readString();
                this.time = in.readInt();
                this.token = in.readString();
                this.uid = in.readInt();
                this.username = in.readString();
                this.usersig = in.readString();
            }

            public static final Creator<UserModelBean> CREATOR = new Creator<UserModelBean>() {
                @Override
                public UserModelBean createFromParcel(Parcel source) {
                    return new UserModelBean(source);
                }

                @Override
                public UserModelBean[] newArray(int size) {
                    return new UserModelBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.totalPrice);
            dest.writeString(this.unitPrice);
            dest.writeDouble(this.acreage);
            dest.writeInt(this.areaId);
            dest.writeString(this.areaOne);
            dest.writeString(this.city);
            dest.writeInt(this.collectState);
            dest.writeString(this.curPage);
            dest.writeInt(this.dateNumber);
            dest.writeInt(this.element);
            dest.writeInt(this.exclusive);
            dest.writeInt(this.findNumber);
            dest.writeString(this.fitment);
            dest.writeString(this.floorAge);
            dest.writeInt(this.floorNumber);
            dest.writeString(this.floorSize);
            dest.writeString(this.houseHoldAppliances);
            dest.writeString(this.houseNumber);
            dest.writeString(this.houseType);
            dest.writeInt(this.id);
            dest.writeInt(this.keying);
            dest.writeInt(this.loans);
            dest.writeDouble(this.maxAcreage);
            dest.writeDouble(this.maxPrice);
            dest.writeDouble(this.minAcreage);
            dest.writeDouble(this.minPrice);
            dest.writeInt(this.newOrOld);
            dest.writeString(this.orientation);
            dest.writeString(this.ownership);
            dest.writeString(this.paymentType);
            dest.writeString(this.pdu);
            dest.writeString(this.photoUrl);
            dest.writeDouble(this.price);
            dest.writeInt(this.productType);
            dest.writeInt(this.publicNumber);
            dest.writeParcelable(this.publicTime, flags);
            dest.writeString(this.purpose);
            dest.writeInt(this.saleAndStick);
            dest.writeInt(this.secondId);
            dest.writeInt(this.showTime);
            dest.writeInt(this.simpleNumber);
            dest.writeInt(this.stick);
            dest.writeLong(this.stickTime);
            dest.writeString(this.tabClassify);
            dest.writeString(this.tabId);
            dest.writeString(this.tabName);
            dest.writeString(this.title);
            dest.writeInt(this.totalFloorNumber);
            dest.writeInt(this.uid);
            dest.writeParcelable(this.userModel, flags);
            dest.writeString(this.villageName);
            dest.writeStringList(this.files);
            dest.writeStringList(this.fitmentList);
            dest.writeStringList(this.floorAgeList);
            dest.writeStringList(this.houseList);
            dest.writeStringList(this.houseTypeList);
            dest.writeStringList(this.orientationList);
            dest.writeStringList(this.purposeList);
            dest.writeString(this.remark);
            dest.writeString(this.permit);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.totalPrice = in.readString();
            this.unitPrice = in.readString();
            this.acreage = in.readDouble();
            this.areaId = in.readInt();
            this.areaOne = in.readString();
            this.city = in.readString();
            this.collectState = in.readInt();
            this.curPage = in.readString();
            this.dateNumber = in.readInt();
            this.element = in.readInt();
            this.exclusive = in.readInt();
            this.findNumber = in.readInt();
            this.fitment = in.readString();
            this.floorAge = in.readString();
            this.floorNumber = in.readInt();
            this.floorSize = in.readString();
            this.houseHoldAppliances = in.readString();
            this.houseNumber = in.readString();
            this.houseType = in.readString();
            this.id = in.readInt();
            this.keying = in.readInt();
            this.loans = in.readInt();
            this.maxAcreage = in.readDouble();
            this.maxPrice = in.readDouble();
            this.minAcreage = in.readDouble();
            this.minPrice = in.readDouble();
            this.newOrOld = in.readInt();
            this.orientation = in.readString();
            this.ownership = in.readString();
            this.paymentType = in.readString();
            this.pdu = in.readString();
            this.photoUrl = in.readString();
            this.price = in.readDouble();
            this.productType = in.readInt();
            this.publicNumber = in.readInt();
            this.publicTime = in.readParcelable(PublicTimeBean.class.getClassLoader());
            this.purpose = in.readString();
            this.saleAndStick = in.readInt();
            this.secondId = in.readInt();
            this.showTime = in.readInt();
            this.simpleNumber = in.readInt();
            this.stick = in.readInt();
            this.stickTime = in.readLong();
            this.tabClassify = in.readString();
            this.tabId = in.readString();
            this.tabName = in.readString();
            this.title = in.readString();
            this.totalFloorNumber = in.readInt();
            this.uid = in.readInt();
            this.userModel = in.readParcelable(UserModelBean.class.getClassLoader());
            this.villageName = in.readString();
            this.files = in.createStringArrayList();
            this.fitmentList = in.createStringArrayList();
            this.floorAgeList = in.createStringArrayList();
            this.houseList = in.createStringArrayList();
            this.houseTypeList = in.createStringArrayList();
            this.orientationList = in.createStringArrayList();
            this.purposeList = in.createStringArrayList();
            this.remark = in.readString();
            this.permit = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.curPage);
        dest.writeInt(this.pageSize);
        dest.writeList(this.list);
    }

    public PersonProductEntity() {
    }

    protected PersonProductEntity(Parcel in) {
        this.curPage = in.readString();
        this.pageSize = in.readInt();
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PersonProductEntity> CREATOR = new Parcelable.Creator<PersonProductEntity>() {
        @Override
        public PersonProductEntity createFromParcel(Parcel source) {
            return new PersonProductEntity(source);
        }

        @Override
        public PersonProductEntity[] newArray(int size) {
            return new PersonProductEntity[size];
        }
    };
}
