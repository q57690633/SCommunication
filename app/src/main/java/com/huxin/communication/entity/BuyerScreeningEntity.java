package com.huxin.communication.entity;

import java.util.List;

public class BuyerScreeningEntity {


    /**
     * curPage : 1
     * pageSize : 2
     * list : [{"acreage":80,"areaId":102,"areaOne":"","city":"","curPage":"","element":0,"exclusive":0,"findNumber":0,"fitment":"","floorAge":"","floorNumber":0,"floorSize":"","houseHoldAppliances":"","houseNumber":"","houseType":"一居","id":2,"keying":0,"loans":0,"maxAcreage":80,"maxPrice":0,"minAcreage":75,"minPrice":47,"newOrOld":0,"orientation":"","ownership":"","permit":"","productType":0,"publicTime":{"date":10,"day":1,"hours":11,"minutes":5,"month":11,"nanos":0,"seconds":54,"time":1544411154000,"timezoneOffset":-480,"year":118},"purpose":"","simpleNumber":1,"stick":1,"tabClassify":"","tabId":"1,2","tabName":"学区房,近地铁","title":"","totalFloorNumber":0,"totalPrice":50,"uid":1,"unitPrice":0,"villageName":"紫绶园"}]
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
         * acreage : 80
         * areaId : 102
         * areaOne :
         * city :
         * curPage :
         * element : 0
         * exclusive : 0
         * findNumber : 0
         * fitment :
         * floorAge :
         * floorNumber : 0
         * floorSize :
         * houseHoldAppliances :
         * houseNumber :
         * houseType : 一居
         * id : 2
         * keying : 0
         * loans : 0
         * maxAcreage : 80
         * maxPrice : 0
         * minAcreage : 75
         * minPrice : 47
         * newOrOld : 0
         * orientation :
         * ownership :
         * permit :
         * productType : 0
         * publicTime : {"date":10,"day":1,"hours":11,"minutes":5,"month":11,"nanos":0,"seconds":54,"time":1544411154000,"timezoneOffset":-480,"year":118}
         * purpose :
         * simpleNumber : 1
         * stick : 1
         * tabClassify :
         * tabId : 1,2
         * tabName : 学区房,近地铁
         * title :
         * totalFloorNumber : 0
         * totalPrice : 50
         * uid : 1
         * unitPrice : 0
         * villageName : 紫绶园
         */

        private int acreage;
        private int areaId;
        private String areaOne;
        private String city;
        private String curPage;
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
        private int maxAcreage;
        private int maxPrice;
        private int minAcreage;
        private int minPrice;
        private int newOrOld;
        private String orientation;
        private String ownership;
        private String permit;
        private int productType;
        private RentalScreeningEntity.ListBean.PublicTimeBean publicTime;
        private String purpose;
        private int simpleNumber;
        private int stick;
        private String tabClassify;
        private String tabId;
        private String tabName;
        private String title;
        private int totalFloorNumber;
        private int totalPrice;
        private int uid;
        private int unitPrice;
        private String villageName;

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

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public RentalScreeningEntity.ListBean.PublicTimeBean getPublicTime() {
            return publicTime;
        }

        public void setPublicTime(RentalScreeningEntity.ListBean.PublicTimeBean publicTime) {
            this.publicTime = publicTime;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
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

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public static class PublicTimeBean {
            /**
             * date : 10
             * day : 1
             * hours : 11
             * minutes : 5
             * month : 11
             * nanos : 0
             * seconds : 54
             * time : 1544411154000
             * timezoneOffset : -480
             * year : 118
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
    }
}
