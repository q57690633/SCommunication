package com.huxin.communication.entity;

import java.util.List;

public class TravelEntity {
    /**
     * type : 1房产 2旅游
     * TravelType : 1周边游，2国内游，3境外游，4.票务
     * list : [{"TActivityId":"射击,气球","TAddressId":"香山,北京","TConsumeId":"消费,无消费","TOtherId":"呵呵","TOverseasId":"","TStayId":"五星级","TTrafficId":"飞机,火车","companyName":"","curPage":"","depart_code":"110100","depart_name":"北京","depart_pro_code":"110000","files":[],"finalPrice":1500,"finalPriceChild":0,"generalize":"无","goalsId":1,"goals_city":"北京市","headUrl":"/upload/1547465839612/1547465839612_12.jpg","id":1,"issue_count":5,"issue_time":"2019-01-17 10:55:15","keyWord":"","lineOrThrow":1,"maxDay":0,"maxPrice":0,"minDay":0,"minPri_maxPri":"","minPrice":0,"numberDays":10,"photo_url":"1","pickupPrice":0,"returnPrice":300,"returnPriceChild":0,"sort_type":0,"specilDays":0,"spotName":"香山","stick":1,"stick_better":0,"stick_hot":0,"stick_low":1,"stick_name":"特价","stick_new":0,"stick_rate":0,"stick_return":0,"stick_throw":0,"stick_zeroC":0,"tagName":"香山,北京,射击,气球,消费,无消费,射击,气球,五星级,呵呵","totalPrice":1800,"totalPriceChild":0,"travelTitle":"北京香山一日游","uid":13,"userCity":"石家庄","username":"fds","view_count":100}]
     */

    private int type;
    private int TravelType;
    private List<ListBean> list;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTravelType() {
        return TravelType;
    }

    public void setTravelType(int travelType) {
        TravelType = travelType;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         *
         *
         *         depart_name : 北京    出发城市名称
         *         username : fds        用户名
         *         userCity : 石家庄      用户城市
         *         goals_city : 北京市     目的地城市
         *         numberDays : 10          天数
         *         totalPrice : 1800         成人总价
         *         totalPriceChild : 0       儿童总价
         *         returnPrice : 300           成人返现
         *         returnPriceChild : 0         儿童返现
         *         spotName : 香山                景点名称
         *         photo_url : 1                 海报地址
         *          headUrl : /upload/1547465839612/1547465839612_12.jpg          头像地址
         *         tagName : 香山,北京,射击,气球,消费,无消费,射击,气球,五星级,呵呵          标签
         */

        private String depart_name;
        private String username;
        private String userCity;
        private String goals_city;
        private int numberDays;
        private int totalPrice;
        private int returnPrice;
        private int totalPriceChild;
        private int returnPriceChild;
        private String spotName;
        private String photo_url;
        private String headUrl;
        private String tagName;

        public String getDepart_name() {
            return depart_name;
        }

        public void setDepart_name(String depart_name) {
            this.depart_name = depart_name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserCity() {
            return userCity;
        }

        public void setUserCity(String userCity) {
            this.userCity = userCity;
        }

        public String getGoals_city() {
            return goals_city;
        }

        public void setGoals_city(String goals_city) {
            this.goals_city = goals_city;
        }

        public int getNumberDays() {
            return numberDays;
        }

        public void setNumberDays(int numberDays) {
            this.numberDays = numberDays;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getReturnPrice() {
            return returnPrice;
        }

        public void setReturnPrice(int returnPrice) {
            this.returnPrice = returnPrice;
        }

        public int getTotalPriceChild() {
            return totalPriceChild;
        }

        public void setTotalPriceChild(int totalPriceChild) {
            this.totalPriceChild = totalPriceChild;
        }

        public int getReturnPriceChild() {
            return returnPriceChild;
        }

        public void setReturnPriceChild(int returnPriceChild) {
            this.returnPriceChild = returnPriceChild;
        }

        public String getSpotName() {
            return spotName;
        }

        public void setSpotName(String spotName) {
            this.spotName = spotName;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }
}
