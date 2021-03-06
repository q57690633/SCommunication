package com.huxin.communication.entity;

import java.util.List;

public class TopSelectionEntity {


    /**
     * curPage : 1
     * pageSize : 1
     * list : [{"acreage":235,"areaId":102,"areaOne":"","city":"","curPage":"","dateNumber":0,"element":0,"exclusive":2,"files":[],"findNumber":0,"fitment":"简装","floorAge":"","floorNumber":23,"floorSize":"","houseHoldAppliances":"无家具无家电","houseNumber":"","houseType":"五室及以上","id":49,"keying":1,"loans":0,"maxAcreage":0,"maxPrice":0,"minAcreage":0,"minPrice":0,"newOrOld":0,"orientation":"朝东","ownership":"","paymentType":"押一付一","pdu":"","photoUrl":"","price":1235,"productType":2,"publicNumber":1,"publicTime":{"date":4,"day":1,"hours":16,"minutes":12,"month":2,"nanos":0,"seconds":11,"time":1551687131000,"timezoneOffset":-480,"year":119},"purpose":"","saleAndStick":0,"secondId":208,"simpleNumber":0,"stick":1,"stickTime":1551687131153,"tabClassify":"","tabId":"22,27,29,30,20,21","tabName":"近公交,别墅,双卫生间,首次出租,学区房,有电梯","title":"标题","totalFloorNumber":40,"uid":154,"userModel":{"advice":"","area":"","authCode":0,"cOrP":"","city":"","companyName":"","county":"","headUrl":"","identifier":"","industryType":"","invitationCode":"","licenseCode":"","matchingMonth":0,"password":"","phone":"","positions":"","province":"","registerType":0,"secondAuthCode":0,"secondPhone":"","stickNumber":0,"storeName":"","time":0,"token":"","uid":0,"username":"","usersig":""},"villageName":"奥斯卡纳别墅小区"}]
     */

    private String curPage;
    private int pageSize;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * acreage : 235.0
         * areaId : 102
         * areaOne :
         * city :
         * curPage :
         * dateNumber : 0
         * element : 0
         * exclusive : 2
         * files : []
         * findNumber : 0
         * fitment : 简装
         * floorAge :
         * floorNumber : 23
         * floorSize :
         * houseHoldAppliances : 无家具无家电
         * houseNumber :
         * houseType : 五室及以上
         * id : 49
         * keying : 1
         * loans : 0
         * maxAcreage : 0.0
         * maxPrice : 0.0
         * minAcreage : 0.0
         * minPrice : 0.0
         * newOrOld : 0
         * orientation : 朝东
         * ownership :
         * paymentType : 押一付一
         * pdu :
         * photoUrl :
         * price : 1235.0
         * productType : 2
         * publicNumber : 1
         * publicTime : {"date":4,"day":1,"hours":16,"minutes":12,"month":2,"nanos":0,"seconds":11,"time":1551687131000,"timezoneOffset":-480,"year":119}
         * purpose :
         * saleAndStick : 0
         * secondId : 208
         * simpleNumber : 0
         * stick : 1
         * stickTime : 1551687131153
         * tabClassify :
         * tabId : 22,27,29,30,20,21
         * tabName : 近公交,别墅,双卫生间,首次出租,学区房,有电梯
         * title : 标题
         * totalFloorNumber : 40
         * uid : 154
         * userModel : {"advice":"","area":"","authCode":0,"cOrP":"","city":"","companyName":"","county":"","headUrl":"","identifier":"","industryType":"","invitationCode":"","licenseCode":"","matchingMonth":0,"password":"","phone":"","positions":"","province":"","registerType":0,"secondAuthCode":0,"secondPhone":"","stickNumber":0,"storeName":"","time":0,"token":"","uid":0,"username":"","usersig":""}
         * villageName : 奥斯卡纳别墅小区
         */

        private double acreage;
        private int areaId;
        private String areaOne;
        private String city;
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
        private List<?> files;

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

        public List<?> getFiles() {
            return files;
        }

        public void setFiles(List<?> files) {
            this.files = files;
        }

        public static class PublicTimeBean {
            /**
             * date : 4
             * day : 1
             * hours : 16
             * minutes : 12
             * month : 2
             * nanos : 0
             * seconds : 11
             * time : 1551687131000
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
        }

        public static class UserModelBean {
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
        }
    }
}
