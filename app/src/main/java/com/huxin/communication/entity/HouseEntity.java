package com.huxin.communication.entity;

import java.util.List;

public class HouseEntity {

    /**
     * type : 1 房产 2旅游
     * houseType : 1 出售 2出租 3求购 4求租
     * list : [{"acreage":80,"areaId":102,"areaOne":"","city":"","curPage":"","element":0,"exclusive":0,"findNumber":0,"fitment":"","floorAge":"","floorNumber":0,"floorSize":"","houseHoldAppliances":"","houseNumber":"","houseType":"一居","id":2,"keying":0,"loans":0,"maxAcreage":80,"maxPrice":0,"minAcreage":75,"minPrice":47,"newOrOld":0,"orientation":"","ownership":"","permit":"","productType":0,"publicTime":{"date":10,"day":1,"hours":11,"minutes":5,"month":11,"nanos":0,"seconds":54,"time":1544411154000,"timezoneOffset":-480,"year":118},"purpose":"","simpleNumber":1,"stick":1,"tabClassify":"","tabId":"1,2","tabName":"学区房,近地铁","title":"","totalFloorNumber":0,"totalPrice":50,"uid":1,"unitPrice":0,"villageName":"紫绶园"}]
     */

    private int type;
    private int houseType;
    private List<ListBean> list;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHouseType() {
        return houseType;
    }

    public void setHouseType(int houseType) {
        this.houseType = houseType;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * houseType :居室
         * id : 2  商品id
         * keying : 0 钥匙
         * orientation 朝向:
         * stick : 1  置顶
         * tabName : 学区房,近地铁  标签
         * title : 标题
         * totalPrice : 50 总价
         * unitPrice : 0  单价
         * villageName : 紫绶园  小区名称
         */

        private  int id;
        private String villageName;
        private String houseType;
        private int totalPrice;
        private int unitPrice;
        private String Orientation;
        private int keying;
        private int stick;
        private int exclusive;
        private String tabName;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getOrientation() {
            return Orientation;
        }

        public void setOrientation(String orientation) {
            Orientation = orientation;
        }

        public int getKeying() {
            return keying;
        }

        public void setKeying(int keying) {
            this.keying = keying;
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

        public String getTabName() {
            return tabName;
        }

        public void setTabName(String tabName) {
            this.tabName = tabName;
        }
    }
}
