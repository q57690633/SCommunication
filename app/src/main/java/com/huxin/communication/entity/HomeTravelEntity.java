package com.huxin.communication.entity;

import java.util.List;

public class HomeTravelEntity {


    private List<CarouselBean> carousel;
    private List<AroundHeadBean> aroundHead;
    private List<ForeignHeadBean> foreignHead;
    private List<TicketHeadBean> ticketHead;

    public List<CarouselBean> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<CarouselBean> carousel) {
        this.carousel = carousel;
    }

    public List<AroundHeadBean> getAroundHead() {
        return aroundHead;
    }

    public void setAroundHead(List<AroundHeadBean> aroundHead) {
        this.aroundHead = aroundHead;
    }

    public List<ForeignHeadBean> getForeignHead() {
        return foreignHead;
    }

    public void setForeignHead(List<ForeignHeadBean> foreignHead) {
        this.foreignHead = foreignHead;
    }

    public List<TicketHeadBean> getTicketHead() {
        return ticketHead;
    }

    public void setTicketHead(List<TicketHeadBean> ticketHead) {
        this.ticketHead = ticketHead;
    }

    public static class CarouselBean {
        /**
         * carousel_detail : 烧烤好吃的哟
         * category : 2
         * city : 北京市
         * company_name : 天天烧烤
         * create_time : 2019-01-23 20:18:08
         * id : 27
         * img_url : http://39.105.203.33/upload/1550133990180/1550133990180_134.jpg
         * status : 0
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

    public static class AroundHeadBean {
        /**
         * TActivityId : 打架
         * TAddressId : 古城
         * TConsumeId : 全包,半包
         * TOtherId : 哈哈
         * TOverseasId :
         * TStayId : 四星级
         * TTrafficId : 汽车,自行车
         * companyName :
         * curPage :
         * depart_code : 110100
         * depart_name : 北京市
         * depart_pro_code : 110000
         * files : []
         * finalPrice : 1800
         * finalPriceChild : 0
         * generalize : 无
         * goalsId : 1
         * goals_city : 北京市
         * goals_city_code : 110100
         * goals_pro : 北京
         * headUrl :
         * id : 2
         * issue_count : 1
         * issue_time : 2018-12-24 13:22:28
         * keyWord :
         * lineOrThrow : 1
         * maxDay : 0
         * maxPrice : 0
         * minDay : 0
         * minPri_maxPri :
         * minPrice : 0
         * numberDays : 4
         * photo_url :
         * pickupPrice : 0
         * returnPrice : 200
         * returnPriceChild : 0
         * sort_type : 0
         * specilDays : 0
         * spotName : 十堰
         * stick : 1
         * stick_better : 0
         * stick_hot : 0
         * stick_low : 0
         * stick_name :
         * stick_new : 1
         * stick_rate : 0
         * stick_return : 0
         * stick_throw : 0
         * stick_time : 1550213940260
         * stick_zeroC : 0
         * tagName :
         * totalPrice : 2000
         * totalPriceChild : 0
         * travelTitle : 敢不敢给个
         * uid : 1
         * userCity :
         * userPhone :
         * username :
         * view_count : 1
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
        private String goalsId;
        private String goals_city;
        private String goals_city_code;
        private String goals_pro;
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
        private long stick_time;
        private int stick_zeroC;
        private String tagName;
        private int totalPrice;
        private int totalPriceChild;
        private String travelTitle;
        private int uid;
        private String userCity;
        private String userPhone;
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

        public String getGoalsId() {
            return goalsId;
        }

        public void setGoalsId(String goalsId) {
            this.goalsId = goalsId;
        }

        public String getGoals_city() {
            return goals_city;
        }

        public void setGoals_city(String goals_city) {
            this.goals_city = goals_city;
        }

        public String getGoals_city_code() {
            return goals_city_code;
        }

        public void setGoals_city_code(String goals_city_code) {
            this.goals_city_code = goals_city_code;
        }

        public String getGoals_pro() {
            return goals_pro;
        }

        public void setGoals_pro(String goals_pro) {
            this.goals_pro = goals_pro;
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

        public long getStick_time() {
            return stick_time;
        }

        public void setStick_time(long stick_time) {
            this.stick_time = stick_time;
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

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
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

    public static class ForeignHeadBean {
        /**
         * companyName :
         * curPage :
         * depart_name : 北京市
         * depart_pro_name : 北京
         * files : []
         * final_price : 4800
         * final_price_child : 0
         * generalize :
         * goals_city :
         * goals_name : 伦敦
         * goals_nat_name : 英国
         * headUrl :
         * id : 1
         * issue_count : 2
         * issue_time : 2019-01-17 13:17:28
         * keyWord :
         * line_or_throw : 1
         * max_days : 30
         * max_price : 10000
         * minPri_maxPri :
         * min_days : 0
         * min_price : 1000
         * number_days : 20
         * photo_url :
         * pickup_price : 0
         * return_price : 200
         * return_price_child : 0
         * sort_type : 0
         * spot_name : 巴黎圣母院
         * stick : 1
         * stick_better : 0
         * stick_hot : 0
         * stick_low : 0
         * stick_name : 上新
         * stick_new : 1
         * stick_rate : 0
         * stick_return : 0
         * stick_throw : 0
         * stick_zeroC : 0
         * t_activity_id : 萨达啥
         * t_address_id : 女神,哈哈
         * t_consume_id : 萨达
         * t_other_id : 大达到
         * t_overseas_id : 黑寡妇
         * t_stay_id : 大大神
         * t_traffic_id : 大sad
         * tagName : 女神,哈哈,萨达啥,萨达,黑寡妇,大大神,大达到
         * total_price : 5000
         * total_price_child : 0
         * travel_title : 粉底霜房间看电视
         * uid : 1
         * userCity : 长春市
         * username : zail
         * view_count : 1
         */

        private String companyName;
        private String curPage;
        private String depart_name;
        private String depart_pro_name;
        private int final_price;
        private int final_price_child;
        private String generalize;
        private String goals_city;
        private String goals_name;
        private String goals_nat_name;
        private String headUrl;
        private int id;
        private int issue_count;
        private String issue_time;
        private String keyWord;
        private int line_or_throw;
        private int max_days;
        private int max_price;
        private String minPri_maxPri;
        private int min_days;
        private int min_price;
        private int number_days;
        private String photo_url;
        private int pickup_price;
        private int return_price;
        private int return_price_child;
        private int sort_type;
        private String spot_name;
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
        private String t_activity_id;
        private String t_address_id;
        private String t_consume_id;
        private String t_other_id;
        private String t_overseas_id;
        private String t_stay_id;
        private String t_traffic_id;
        private String tagName;
        private int total_price;
        private int total_price_child;
        private String travel_title;
        private int uid;
        private String userCity;
        private String username;
        private int view_count;
        private List<?> files;

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

        public String getDepart_name() {
            return depart_name;
        }

        public void setDepart_name(String depart_name) {
            this.depart_name = depart_name;
        }

        public String getDepart_pro_name() {
            return depart_pro_name;
        }

        public void setDepart_pro_name(String depart_pro_name) {
            this.depart_pro_name = depart_pro_name;
        }

        public int getFinal_price() {
            return final_price;
        }

        public void setFinal_price(int final_price) {
            this.final_price = final_price;
        }

        public int getFinal_price_child() {
            return final_price_child;
        }

        public void setFinal_price_child(int final_price_child) {
            this.final_price_child = final_price_child;
        }

        public String getGeneralize() {
            return generalize;
        }

        public void setGeneralize(String generalize) {
            this.generalize = generalize;
        }

        public String getGoals_city() {
            return goals_city;
        }

        public void setGoals_city(String goals_city) {
            this.goals_city = goals_city;
        }

        public String getGoals_name() {
            return goals_name;
        }

        public void setGoals_name(String goals_name) {
            this.goals_name = goals_name;
        }

        public String getGoals_nat_name() {
            return goals_nat_name;
        }

        public void setGoals_nat_name(String goals_nat_name) {
            this.goals_nat_name = goals_nat_name;
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

        public int getLine_or_throw() {
            return line_or_throw;
        }

        public void setLine_or_throw(int line_or_throw) {
            this.line_or_throw = line_or_throw;
        }

        public int getMax_days() {
            return max_days;
        }

        public void setMax_days(int max_days) {
            this.max_days = max_days;
        }

        public int getMax_price() {
            return max_price;
        }

        public void setMax_price(int max_price) {
            this.max_price = max_price;
        }

        public String getMinPri_maxPri() {
            return minPri_maxPri;
        }

        public void setMinPri_maxPri(String minPri_maxPri) {
            this.minPri_maxPri = minPri_maxPri;
        }

        public int getMin_days() {
            return min_days;
        }

        public void setMin_days(int min_days) {
            this.min_days = min_days;
        }

        public int getMin_price() {
            return min_price;
        }

        public void setMin_price(int min_price) {
            this.min_price = min_price;
        }

        public int getNumber_days() {
            return number_days;
        }

        public void setNumber_days(int number_days) {
            this.number_days = number_days;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public int getPickup_price() {
            return pickup_price;
        }

        public void setPickup_price(int pickup_price) {
            this.pickup_price = pickup_price;
        }

        public int getReturn_price() {
            return return_price;
        }

        public void setReturn_price(int return_price) {
            this.return_price = return_price;
        }

        public int getReturn_price_child() {
            return return_price_child;
        }

        public void setReturn_price_child(int return_price_child) {
            this.return_price_child = return_price_child;
        }

        public int getSort_type() {
            return sort_type;
        }

        public void setSort_type(int sort_type) {
            this.sort_type = sort_type;
        }

        public String getSpot_name() {
            return spot_name;
        }

        public void setSpot_name(String spot_name) {
            this.spot_name = spot_name;
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

        public String getT_activity_id() {
            return t_activity_id;
        }

        public void setT_activity_id(String t_activity_id) {
            this.t_activity_id = t_activity_id;
        }

        public String getT_address_id() {
            return t_address_id;
        }

        public void setT_address_id(String t_address_id) {
            this.t_address_id = t_address_id;
        }

        public String getT_consume_id() {
            return t_consume_id;
        }

        public void setT_consume_id(String t_consume_id) {
            this.t_consume_id = t_consume_id;
        }

        public String getT_other_id() {
            return t_other_id;
        }

        public void setT_other_id(String t_other_id) {
            this.t_other_id = t_other_id;
        }

        public String getT_overseas_id() {
            return t_overseas_id;
        }

        public void setT_overseas_id(String t_overseas_id) {
            this.t_overseas_id = t_overseas_id;
        }

        public String getT_stay_id() {
            return t_stay_id;
        }

        public void setT_stay_id(String t_stay_id) {
            this.t_stay_id = t_stay_id;
        }

        public String getT_traffic_id() {
            return t_traffic_id;
        }

        public void setT_traffic_id(String t_traffic_id) {
            this.t_traffic_id = t_traffic_id;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public int getTotal_price_child() {
            return total_price_child;
        }

        public void setTotal_price_child(int total_price_child) {
            this.total_price_child = total_price_child;
        }

        public String getTravel_title() {
            return travel_title;
        }

        public void setTravel_title(String travel_title) {
            this.travel_title = travel_title;
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

    public static class TicketHeadBean {
        /**
         * companyName :
         * curPage :
         * files : []
         * final_boat : 0
         * final_car : 0
         * final_price : 70
         * final_price_child : 0
         * final_price_evening : 0
         * final_price_family : 0
         * final_price_parent_child : 0
         * final_price_total : 0
         * headUrl :
         * id : 7
         * issue_count : 1
         * issue_time : 2019-02-15 14:30:25
         * keyWord :
         * line_or_throw : 1
         * max_price : 0
         * minPri_maxPri :
         * min_price : 0
         * open_time : 10:30-10:45
         * original_boat : 0
         * original_car : 0
         * original_price : 90
         * original_price_child : 0
         * original_price_evening : 0
         * original_price_family : 0
         * original_price_parent_child : 0
         * original_price_total : 0
         * photo_url :
         * sort_type : 0
         * stick : 1
         * stick_better : 0
         * stick_hot : 0
         * stick_low : 1
         * stick_name :
         * stick_new : 0
         * stick_rate : 0
         * stick_return : 0
         * stick_throw : 0
         * stick_time : 1550212224556
         * stick_zeroC : 0
         * tagName :
         * ticket_activity_id :
         * ticket_addr : 北京北京市
         * ticket_city_name : 北京市
         * ticket_name : 哈哈
         * ticket_other_id :
         * ticket_pro_code : 110000
         * ticket_pro_name : 北京
         * ticket_theme_id :
         * ticket_type : 1
         * uid : 74
         * userCity :
         * userPhone :
         * username :
         * view_count : 0
         */

        private String companyName;
        private String curPage;
        private int final_boat;
        private int final_car;
        private int final_price;
        private int final_price_child;
        private int final_price_evening;
        private int final_price_family;
        private int final_price_parent_child;
        private int final_price_total;
        private String headUrl;
        private int id;
        private int issue_count;
        private String issue_time;
        private String keyWord;
        private int line_or_throw;
        private int max_price;
        private String minPri_maxPri;
        private int min_price;
        private String open_time;
        private int original_boat;
        private int original_car;
        private int original_price;
        private int original_price_child;
        private int original_price_evening;
        private int original_price_family;
        private int original_price_parent_child;
        private int original_price_total;
        private String photo_url;
        private int sort_type;
        private int stick;
        private int stick_better;
        private int stick_hot;
        private int stick_low;
        private String stick_name;
        private int stick_new;
        private int stick_rate;
        private int stick_return;
        private int stick_throw;
        private long stick_time;
        private int stick_zeroC;
        private String tagName;
        private String ticket_activity_id;
        private String ticket_addr;
        private String ticket_city_name;
        private String ticket_name;
        private String ticket_other_id;
        private String ticket_pro_code;
        private String ticket_pro_name;
        private String ticket_theme_id;
        private int ticket_type;
        private int uid;
        private String userCity;
        private String userPhone;
        private String username;
        private int view_count;
        private List<?> files;

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

        public int getFinal_boat() {
            return final_boat;
        }

        public void setFinal_boat(int final_boat) {
            this.final_boat = final_boat;
        }

        public int getFinal_car() {
            return final_car;
        }

        public void setFinal_car(int final_car) {
            this.final_car = final_car;
        }

        public int getFinal_price() {
            return final_price;
        }

        public void setFinal_price(int final_price) {
            this.final_price = final_price;
        }

        public int getFinal_price_child() {
            return final_price_child;
        }

        public void setFinal_price_child(int final_price_child) {
            this.final_price_child = final_price_child;
        }

        public int getFinal_price_evening() {
            return final_price_evening;
        }

        public void setFinal_price_evening(int final_price_evening) {
            this.final_price_evening = final_price_evening;
        }

        public int getFinal_price_family() {
            return final_price_family;
        }

        public void setFinal_price_family(int final_price_family) {
            this.final_price_family = final_price_family;
        }

        public int getFinal_price_parent_child() {
            return final_price_parent_child;
        }

        public void setFinal_price_parent_child(int final_price_parent_child) {
            this.final_price_parent_child = final_price_parent_child;
        }

        public int getFinal_price_total() {
            return final_price_total;
        }

        public void setFinal_price_total(int final_price_total) {
            this.final_price_total = final_price_total;
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

        public int getLine_or_throw() {
            return line_or_throw;
        }

        public void setLine_or_throw(int line_or_throw) {
            this.line_or_throw = line_or_throw;
        }

        public int getMax_price() {
            return max_price;
        }

        public void setMax_price(int max_price) {
            this.max_price = max_price;
        }

        public String getMinPri_maxPri() {
            return minPri_maxPri;
        }

        public void setMinPri_maxPri(String minPri_maxPri) {
            this.minPri_maxPri = minPri_maxPri;
        }

        public int getMin_price() {
            return min_price;
        }

        public void setMin_price(int min_price) {
            this.min_price = min_price;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public int getOriginal_boat() {
            return original_boat;
        }

        public void setOriginal_boat(int original_boat) {
            this.original_boat = original_boat;
        }

        public int getOriginal_car() {
            return original_car;
        }

        public void setOriginal_car(int original_car) {
            this.original_car = original_car;
        }

        public int getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(int original_price) {
            this.original_price = original_price;
        }

        public int getOriginal_price_child() {
            return original_price_child;
        }

        public void setOriginal_price_child(int original_price_child) {
            this.original_price_child = original_price_child;
        }

        public int getOriginal_price_evening() {
            return original_price_evening;
        }

        public void setOriginal_price_evening(int original_price_evening) {
            this.original_price_evening = original_price_evening;
        }

        public int getOriginal_price_family() {
            return original_price_family;
        }

        public void setOriginal_price_family(int original_price_family) {
            this.original_price_family = original_price_family;
        }

        public int getOriginal_price_parent_child() {
            return original_price_parent_child;
        }

        public void setOriginal_price_parent_child(int original_price_parent_child) {
            this.original_price_parent_child = original_price_parent_child;
        }

        public int getOriginal_price_total() {
            return original_price_total;
        }

        public void setOriginal_price_total(int original_price_total) {
            this.original_price_total = original_price_total;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public int getSort_type() {
            return sort_type;
        }

        public void setSort_type(int sort_type) {
            this.sort_type = sort_type;
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

        public long getStick_time() {
            return stick_time;
        }

        public void setStick_time(long stick_time) {
            this.stick_time = stick_time;
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

        public String getTicket_activity_id() {
            return ticket_activity_id;
        }

        public void setTicket_activity_id(String ticket_activity_id) {
            this.ticket_activity_id = ticket_activity_id;
        }

        public String getTicket_addr() {
            return ticket_addr;
        }

        public void setTicket_addr(String ticket_addr) {
            this.ticket_addr = ticket_addr;
        }

        public String getTicket_city_name() {
            return ticket_city_name;
        }

        public void setTicket_city_name(String ticket_city_name) {
            this.ticket_city_name = ticket_city_name;
        }

        public String getTicket_name() {
            return ticket_name;
        }

        public void setTicket_name(String ticket_name) {
            this.ticket_name = ticket_name;
        }

        public String getTicket_other_id() {
            return ticket_other_id;
        }

        public void setTicket_other_id(String ticket_other_id) {
            this.ticket_other_id = ticket_other_id;
        }

        public String getTicket_pro_code() {
            return ticket_pro_code;
        }

        public void setTicket_pro_code(String ticket_pro_code) {
            this.ticket_pro_code = ticket_pro_code;
        }

        public String getTicket_pro_name() {
            return ticket_pro_name;
        }

        public void setTicket_pro_name(String ticket_pro_name) {
            this.ticket_pro_name = ticket_pro_name;
        }

        public String getTicket_theme_id() {
            return ticket_theme_id;
        }

        public void setTicket_theme_id(String ticket_theme_id) {
            this.ticket_theme_id = ticket_theme_id;
        }

        public int getTicket_type() {
            return ticket_type;
        }

        public void setTicket_type(int ticket_type) {
            this.ticket_type = ticket_type;
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

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
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
