package com.huxin.communication.entity;

import java.util.List;

public class ForeignTraveAroundEntity {

    /**
     * curPage : 1
     * pageSize : 4
     * list : [{"TActivityId":"射击,气球","TAddressId":"香山,北京","TConsumeId":"消费,无消费","TOtherId":"呵呵","TOverseasId":"","TStayId":"五星级","TTrafficId":"飞机,火车","companyName":"","curPage":"","depart_code":"110100","depart_name":"北京","depart_pro_code":"110000","files":[],"finalPrice":1500,"finalPriceChild":0,"generalize":"无","goalsId":1,"goals_city":"北京市","headUrl":"/upload/1547465839612/1547465839612_12.jpg","id":1,"issue_count":5,"issue_time":"2019-01-17 10:55:15","keyWord":"","lineOrThrow":1,"maxDay":0,"maxPrice":0,"minDay":0,"minPri_maxPri":"","minPrice":0,"numberDays":10,"photo_url":"1","pickupPrice":0,"returnPrice":300,"returnPriceChild":0,"sort_type":0,"specilDays":0,"spotName":"香山","stick":1,"stick_better":0,"stick_hot":0,"stick_low":1,"stick_name":"特价","stick_new":0,"stick_rate":0,"stick_return":0,"stick_throw":0,"stick_zeroC":0,"tagName":"香山,北京,射击,气球,消费,无消费,射击,气球,五星级,呵呵","totalPrice":1800,"totalPriceChild":0,"travelTitle":"北京香山一日游","uid":13,"userCity":"石家庄","username":"fds","view_count":100}]
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
         * TActivityId : 射击,气球
         * TAddressId : 香山,北京
         * TConsumeId : 消费,无消费
         * TOtherId : 呵呵
         * TOverseasId :
         * TStayId : 五星级
         * TTrafficId : 飞机,火车
         * companyName :
         * curPage :
         * depart_code : 110100
         * depart_name : 北京
         * depart_pro_code : 110000
         * files : []
         * finalPrice : 1500
         * finalPriceChild : 0
         * generalize : 无
         * goalsId : 1
         * goals_city : 北京市
         * headUrl : /upload/1547465839612/1547465839612_12.jpg
         * id : 1
         * issue_count : 5
         * issue_time : 2019-01-17 10:55:15
         * keyWord :
         * lineOrThrow : 1
         * maxDay : 0
         * maxPrice : 0
         * minDay : 0
         * minPri_maxPri :
         * minPrice : 0
         * numberDays : 10
         * photo_url : 1
         * pickupPrice : 0
         * returnPrice : 300
         * returnPriceChild : 0
         * sort_type : 0
         * specilDays : 0
         * spotName : 香山
         * stick : 1
         * stick_better : 0
         * stick_hot : 0
         * stick_low : 1
         * stick_name : 特价
         * stick_new : 0
         * stick_rate : 0
         * stick_return : 0
         * stick_throw : 0
         * stick_zeroC : 0
         * tagName : 香山,北京,射击,气球,消费,无消费,射击,气球,五星级,呵呵
         * totalPrice : 1800
         * totalPriceChild : 0
         * travelTitle : 北京香山一日游
         * uid : 13
         * userCity : 石家庄
         * username : fds
         * view_count : 100
         */

        private String TActivityId;
        private String TAddressId;
        private String TConsumeId;
        private String TOtherId;
        private String TOverseasId;
        private String TStayId;
        private String TTrafficId;
        private String companyName;
        private String curPage;
        private String depart_code;
        private String depart_name;
        private String depart_pro_code;
        private int finalPrice;
        private int finalPriceChild;
        private String generalize;
        private int goalsId;
        private String goals_city;
        private String headUrl;
        private int id;
        private int issue_count;
        private String issue_time;
        private String keyWord;
        private int lineOrThrow;
        private int maxDay;
        private int maxPrice;
        private int minDay;
        private String minPri_maxPri;
        private int minPrice;
        private int numberDays;
        private String photo_url;
        private int pickupPrice;
        private int returnPrice;
        private int returnPriceChild;
        private int sort_type;
        private int specilDays;
        private String spotName;
        private int stick;
        private int stick_better;
        private int stick_hot;
        private int stick_low;
        private String stick_name;
        private int stick_new;
        private int stick_rate;
        private int stick_return;
        private int stick_throw;
        private int stick_zeroC;
        private String tagName;
        private int totalPrice;
        private int totalPriceChild;
        private String travelTitle;
        private int uid;
        private String userCity;
        private String username;
        private int view_count;
        private List<?> files;

        public String getTActivityId() {
            return TActivityId;
        }

        public void setTActivityId(String TActivityId) {
            this.TActivityId = TActivityId;
        }

        public String getTAddressId() {
            return TAddressId;
        }

        public void setTAddressId(String TAddressId) {
            this.TAddressId = TAddressId;
        }

        public String getTConsumeId() {
            return TConsumeId;
        }

        public void setTConsumeId(String TConsumeId) {
            this.TConsumeId = TConsumeId;
        }

        public String getTOtherId() {
            return TOtherId;
        }

        public void setTOtherId(String TOtherId) {
            this.TOtherId = TOtherId;
        }

        public String getTOverseasId() {
            return TOverseasId;
        }

        public void setTOverseasId(String TOverseasId) {
            this.TOverseasId = TOverseasId;
        }

        public String getTStayId() {
            return TStayId;
        }

        public void setTStayId(String TStayId) {
            this.TStayId = TStayId;
        }

        public String getTTrafficId() {
            return TTrafficId;
        }

        public void setTTrafficId(String TTrafficId) {
            this.TTrafficId = TTrafficId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCurPage() {
            return curPage;
        }

        public void setCurPage(String curPage) {
            this.curPage = curPage;
        }

        public String getDepart_code() {
            return depart_code;
        }

        public void setDepart_code(String depart_code) {
            this.depart_code = depart_code;
        }

        public String getDepart_name() {
            return depart_name;
        }

        public void setDepart_name(String depart_name) {
            this.depart_name = depart_name;
        }

        public String getDepart_pro_code() {
            return depart_pro_code;
        }

        public void setDepart_pro_code(String depart_pro_code) {
            this.depart_pro_code = depart_pro_code;
        }

        public int getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(int finalPrice) {
            this.finalPrice = finalPrice;
        }

        public int getFinalPriceChild() {
            return finalPriceChild;
        }

        public void setFinalPriceChild(int finalPriceChild) {
            this.finalPriceChild = finalPriceChild;
        }

        public String getGeneralize() {
            return generalize;
        }

        public void setGeneralize(String generalize) {
            this.generalize = generalize;
        }

        public int getGoalsId() {
            return goalsId;
        }

        public void setGoalsId(int goalsId) {
            this.goalsId = goalsId;
        }

        public String getGoals_city() {
            return goals_city;
        }

        public void setGoals_city(String goals_city) {
            this.goals_city = goals_city;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIssue_count() {
            return issue_count;
        }

        public void setIssue_count(int issue_count) {
            this.issue_count = issue_count;
        }

        public String getIssue_time() {
            return issue_time;
        }

        public void setIssue_time(String issue_time) {
            this.issue_time = issue_time;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }

        public int getLineOrThrow() {
            return lineOrThrow;
        }

        public void setLineOrThrow(int lineOrThrow) {
            this.lineOrThrow = lineOrThrow;
        }

        public int getMaxDay() {
            return maxDay;
        }

        public void setMaxDay(int maxDay) {
            this.maxDay = maxDay;
        }

        public int getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(int maxPrice) {
            this.maxPrice = maxPrice;
        }

        public int getMinDay() {
            return minDay;
        }

        public void setMinDay(int minDay) {
            this.minDay = minDay;
        }

        public String getMinPri_maxPri() {
            return minPri_maxPri;
        }

        public void setMinPri_maxPri(String minPri_maxPri) {
            this.minPri_maxPri = minPri_maxPri;
        }

        public int getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(int minPrice) {
            this.minPrice = minPrice;
        }

        public int getNumberDays() {
            return numberDays;
        }

        public void setNumberDays(int numberDays) {
            this.numberDays = numberDays;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public int getPickupPrice() {
            return pickupPrice;
        }

        public void setPickupPrice(int pickupPrice) {
            this.pickupPrice = pickupPrice;
        }

        public int getReturnPrice() {
            return returnPrice;
        }

        public void setReturnPrice(int returnPrice) {
            this.returnPrice = returnPrice;
        }

        public int getReturnPriceChild() {
            return returnPriceChild;
        }

        public void setReturnPriceChild(int returnPriceChild) {
            this.returnPriceChild = returnPriceChild;
        }

        public int getSort_type() {
            return sort_type;
        }

        public void setSort_type(int sort_type) {
            this.sort_type = sort_type;
        }

        public int getSpecilDays() {
            return specilDays;
        }

        public void setSpecilDays(int specilDays) {
            this.specilDays = specilDays;
        }

        public String getSpotName() {
            return spotName;
        }

        public void setSpotName(String spotName) {
            this.spotName = spotName;
        }

        public int getStick() {
            return stick;
        }

        public void setStick(int stick) {
            this.stick = stick;
        }

        public int getStick_better() {
            return stick_better;
        }

        public void setStick_better(int stick_better) {
            this.stick_better = stick_better;
        }

        public int getStick_hot() {
            return stick_hot;
        }

        public void setStick_hot(int stick_hot) {
            this.stick_hot = stick_hot;
        }

        public int getStick_low() {
            return stick_low;
        }

        public void setStick_low(int stick_low) {
            this.stick_low = stick_low;
        }

        public String getStick_name() {
            return stick_name;
        }

        public void setStick_name(String stick_name) {
            this.stick_name = stick_name;
        }

        public int getStick_new() {
            return stick_new;
        }

        public void setStick_new(int stick_new) {
            this.stick_new = stick_new;
        }

        public int getStick_rate() {
            return stick_rate;
        }

        public void setStick_rate(int stick_rate) {
            this.stick_rate = stick_rate;
        }

        public int getStick_return() {
            return stick_return;
        }

        public void setStick_return(int stick_return) {
            this.stick_return = stick_return;
        }

        public int getStick_throw() {
            return stick_throw;
        }

        public void setStick_throw(int stick_throw) {
            this.stick_throw = stick_throw;
        }

        public int getStick_zeroC() {
            return stick_zeroC;
        }

        public void setStick_zeroC(int stick_zeroC) {
            this.stick_zeroC = stick_zeroC;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getTotalPriceChild() {
            return totalPriceChild;
        }

        public void setTotalPriceChild(int totalPriceChild) {
            this.totalPriceChild = totalPriceChild;
        }

        public String getTravelTitle() {
            return travelTitle;
        }

        public void setTravelTitle(String travelTitle) {
            this.travelTitle = travelTitle;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUserCity() {
            return userCity;
        }

        public void setUserCity(String userCity) {
            this.userCity = userCity;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }

        public List<?> getFiles() {
            return files;
        }

        public void setFiles(List<?> files) {
            this.files = files;
        }
    }
}