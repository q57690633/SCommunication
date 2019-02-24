package com.huxin.communication.entity;

import java.util.List;

public class HomeEntity {


    private List<CarouselBean> carousel;
    private List<HeadLineBean> headLine;

    public List<CarouselBean> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<CarouselBean> carousel) {
        this.carousel = carousel;
    }

    public List<HeadLineBean> getHeadLine() {
        return headLine;
    }

    public void setHeadLine(List<HeadLineBean> headLine) {
        this.headLine = headLine;
    }

    public static class CarouselBean {

        /**
         * carousel_detail : 烧烤好吃的哟
         * category : 1
         * city : 北京市
         * company_name : 天天烧烤
         * create_time : 2019-01-23 20:18:08
         * id : 26
         * img_url : http://39.105.203.33/upload/1550133990180/1550133990180_134.jpg
         * status : 1
         * url : http://wemedia.ifeng.com/72167460/wemedia.shtml
         */

        private String carousel_detail;
        private int category;
        private String city;
        private String company_name;
        private String create_time;
        private int id;
        private String img_url;
        private int status;
        private String url;

        public String getCarousel_detail() {
            return carousel_detail;
        }

        public void setCarousel_detail(String carousel_detail) {
            this.carousel_detail = carousel_detail;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class HeadLineBean {
        /**
         * acreage : 100
         * areaId : 102
         * areaOne :
         * city :
         * curPage :
         * element : 0
         * files : []
         * findNumber : 0
         * fitment :
         * floorAge :
         * floorNumber : 0
         * floorSize :
         * houseHoldAppliances :
         * houseNumber :
         * houseType : 三室一厅
         * id : 10
         * keying : 0
         * list1 : []
         * list2 : []
         * loans : 0
         * maxAcreage : 0
         * maxPrice : 0
         * minAcreage : 0
         * minPrice : 0
         * newOrOld : 0
         * orientation :
         * ownership :
         * permit :
         * photoUrl : upload/1547532634623/1547532634623_159.jpg,upload/1547532634627/1547532634627_544.jpg
         * productType : 0
         * publicNumber : 1
         * publicTime : {"date":15,"day":2,"hours":14,"minutes":11,"month":0,"nanos":0,"seconds":1,"time":1547532661000,"timezoneOffset":-480,"year":119}
         * purpose :
         * remark :
         * stick : 1
         * tabClassify :
         * tabId :
         * tabName :
         * totalFloorNumber : 0
         * totalPrice : 500
         * uid : 13
         * unitPrice : 0
         * userModel : {"advice":"","area":"","authCode":0,"cOrP":"","city":"","companyName":"","county":"","headUrl":"","industryType":"","invitationCode":"","licenseCode":"","matchingMonth":0,"password":"","phone":"","positions":"","province":"","registerType":0,"secondAuthCode":0,"secondPhone":"","stickNumber":0,"storeName":"","time":0,"token":"","uid":0,"username":""}
         * villageName : 怡家花园
         */

        private int acreage;
        private int areaId;
        private String areaOne;
        private String city;
        private String curPage;
        private int element;
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
        private int maxAcreage;
        private int maxPrice;
        private int minAcreage;
        private int minPrice;
        private int newOrOld;
        private String orientation;
        private String ownership;
        private String permit;
        private String photoUrl;
        private int productType;
        private int publicNumber;
        private PublicTimeBean publicTime;
        private String purpose;
        private String remark;
        private int stick;
        private String tabClassify;
        private String tabId;
        private String tabName;
        private int totalFloorNumber;
        private int totalPrice;
        private int uid;
        private int unitPrice;
        private UserModelBean userModel;
        private String villageName;
        private List<?> files;
        private List<?> list1;
        private List<?> list2;

        public int getAcreage() {
            return acreage;
        }

        public void setAcreage(int acreage) {
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

        public int getElement() {
            return element;
        }

        public void setElement(int element) {
            this.element = element;
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

        public int getMaxAcreage() {
            return maxAcreage;
        }

        public void setMaxAcreage(int maxAcreage) {
            this.maxAcreage = maxAcreage;
        }

        public int getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(int maxPrice) {
            this.maxPrice = maxPrice;
        }

        public int getMinAcreage() {
            return minAcreage;
        }

        public void setMinAcreage(int minAcreage) {
            this.minAcreage = minAcreage;
        }

        public int getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(int minPrice) {
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

        public String getPermit() {
            return permit;
        }

        public void setPermit(String permit) {
            this.permit = permit;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStick() {
            return stick;
        }

        public void setStick(int stick) {
            this.stick = stick;
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

        public int getTotalFloorNumber() {
            return totalFloorNumber;
        }

        public void setTotalFloorNumber(int totalFloorNumber) {
            this.totalFloorNumber = totalFloorNumber;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
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

        public List<?> getList1() {
            return list1;
        }

        public void setList1(List<?> list1) {
            this.list1 = list1;
        }

        public List<?> getList2() {
            return list2;
        }

        public void setList2(List<?> list2) {
            this.list2 = list2;
        }

        public static class PublicTimeBean {
            /**
             * date : 15
             * day : 2
             * hours : 14
             * minutes : 11
             * month : 0
             * nanos : 0
             * seconds : 1
             * time : 1547532661000
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
             */

            private String advice;
            private String area;
            private int authCode;
            private String cOrP;
            private String city;
            private String companyName;
            private String county;
            private String headUrl;
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
        }
    }
}
