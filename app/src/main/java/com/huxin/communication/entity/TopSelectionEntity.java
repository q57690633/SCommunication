package com.huxin.communication.entity;

import java.util.List;

public class TopSelectionEntity {

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
         * id : 1
         * uid : 1
         * villageName : 1
         * acreage : 100
         * minAcreage :
         * maxAcreage :
         * houseType : 两居
         * floorSize :
         * totalPrice : 500
         * unitPrice :
         * minPrice : 450
         * maxPrice :
         * floorNumber :
         * totalFloorNumber :
         * element : 1
         * houseNumber : 2
         * newOrOld : 1
         * orientation :
         * loans :
         * keying :
         * houseHoldAppliances :
         * fitment :
         * permit :
         * purpose :
         * ownership :
         * title :
         * stick : 1
         * exclusive : 1
         * floorAge :
         * areaId :
         * tabId : 1,2
         * publicTime : 2018-12-10T03:05:46.000+0000
         * tabName : 学区房,近地铁
         * tabClassify :
         * productType :
         * findNumber :
         */

        private int id;
        private int uid;
        private String villageName;
        private int acreage;
        private String minAcreage;
        private String maxAcreage;
        private String houseType;
        private String floorSize;
        private int totalPrice;
        private String unitPrice;
        private int minPrice;
        private String maxPrice;
        private String floorNumber;
        private String totalFloorNumber;
        private int element;
        private String houseNumber;
        private int newOrOld;
        private String orientation;
        private String loans;
        private String keying;
        private String houseHoldAppliances;
        private String fitment;
        private String permit;
        private String purpose;
        private String ownership;
        private String title;
        private int stick;
        private int exclusive;
        private String floorAge;
        private String areaId;
        private String tabId;
        private String publicTime;
        private String tabName;
        private String tabClassify;
        private String productType;
        private String findNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public int getAcreage() {
            return acreage;
        }

        public void setAcreage(int acreage) {
            this.acreage = acreage;
        }

        public String getMinAcreage() {
            return minAcreage;
        }

        public void setMinAcreage(String minAcreage) {
            this.minAcreage = minAcreage;
        }

        public String getMaxAcreage() {
            return maxAcreage;
        }

        public void setMaxAcreage(String maxAcreage) {
            this.maxAcreage = maxAcreage;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public String getFloorSize() {
            return floorSize;
        }

        public void setFloorSize(String floorSize) {
            this.floorSize = floorSize;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(int minPrice) {
            this.minPrice = minPrice;
        }

        public String getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(String maxPrice) {
            this.maxPrice = maxPrice;
        }

        public String getFloorNumber() {
            return floorNumber;
        }

        public void setFloorNumber(String floorNumber) {
            this.floorNumber = floorNumber;
        }

        public String getTotalFloorNumber() {
            return totalFloorNumber;
        }

        public void setTotalFloorNumber(String totalFloorNumber) {
            this.totalFloorNumber = totalFloorNumber;
        }

        public int getElement() {
            return element;
        }

        public void setElement(int element) {
            this.element = element;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
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

        public String getLoans() {
            return loans;
        }

        public void setLoans(String loans) {
            this.loans = loans;
        }

        public String getKeying() {
            return keying;
        }

        public void setKeying(String keying) {
            this.keying = keying;
        }

        public String getHouseHoldAppliances() {
            return houseHoldAppliances;
        }

        public void setHouseHoldAppliances(String houseHoldAppliances) {
            this.houseHoldAppliances = houseHoldAppliances;
        }

        public String getFitment() {
            return fitment;
        }

        public void setFitment(String fitment) {
            this.fitment = fitment;
        }

        public String getPermit() {
            return permit;
        }

        public void setPermit(String permit) {
            this.permit = permit;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getOwnership() {
            return ownership;
        }

        public void setOwnership(String ownership) {
            this.ownership = ownership;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getStick() {
            return stick;
        }

        public void setStick(int stick) {
            this.stick = stick;
        }

        public int getExclusive() {
            return exclusive;
        }

        public void setExclusive(int exclusive) {
            this.exclusive = exclusive;
        }

        public String getFloorAge() {
            return floorAge;
        }

        public void setFloorAge(String floorAge) {
            this.floorAge = floorAge;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getTabId() {
            return tabId;
        }

        public void setTabId(String tabId) {
            this.tabId = tabId;
        }

        public String getPublicTime() {
            return publicTime;
        }

        public void setPublicTime(String publicTime) {
            this.publicTime = publicTime;
        }

        public String getTabName() {
            return tabName;
        }

        public void setTabName(String tabName) {
            this.tabName = tabName;
        }

        public String getTabClassify() {
            return tabClassify;
        }

        public void setTabClassify(String tabClassify) {
            this.tabClassify = tabClassify;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getFindNumber() {
            return findNumber;
        }

        public void setFindNumber(String findNumber) {
            this.findNumber = findNumber;
        }
    }
}
