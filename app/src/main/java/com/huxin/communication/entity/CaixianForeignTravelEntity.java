package com.huxin.communication.entity;

import java.util.List;

public class CaixianForeignTravelEntity {


    /**
     * curPage : 1
     * pageSize : 2
     * list : [{"companyName":"","curPage":"","depart_name":"北京市","depart_pro_name":"北京","files":[],"final_price":4800,"final_price_child":0,"generalize":"","goals_city":"","goals_name":"伦敦","goals_nat_name":"英国","headUrl":"","id":1,"issue_count":2,"issue_time":"2019-01-17 13:17:28","keyWord":"","line_or_throw":1,"max_days":30,"max_price":10000,"minPri_maxPri":"","min_days":0,"min_price":1000,"number_days":20,"photo_url":"","pickup_price":0,"return_price":200,"return_price_child":0,"sort_type":0,"spot_name":"巴黎圣母院","stick":1,"stick_better":0,"stick_hot":0,"stick_low":0,"stick_name":"上新","stick_new":1,"stick_rate":0,"stick_return":0,"stick_throw":0,"stick_zeroC":0,"t_activity_id":"萨达啥","t_address_id":"女神,哈哈","t_consume_id":"萨达","t_other_id":"大达到","t_overseas_id":"黑寡妇","t_stay_id":"大大神","t_traffic_id":"大sad","tagName":"女神,哈哈,萨达啥,萨达,黑寡妇,大大神,大达到","total_price":5000,"total_price_child":0,"travel_title":"粉底霜房间看电视","uid":1,"userCity":"长春市","username":"zail","view_count":1},{"companyName":"","curPage":"","depart_name":"北京市","depart_pro_name":"北京","files":[],"final_price":5000,"final_price_child":0,"generalize":"","goals_city":"","goals_name":"伦敦","goals_nat_name":"英国","headUrl":"D:\\upload/1547350911324/1547350911325_582.jpg","id":2,"issue_count":1,"issue_time":"2019-01-16 10:36:04","keyWord":"","line_or_throw":1,"max_days":30,"max_price":10000,"minPri_maxPri":"","min_days":0,"min_price":1000,"number_days":20,"photo_url":"","pickup_price":0,"return_price":300,"return_price_child":0,"sort_type":0,"spot_name":"巴黎圣母院","stick":2,"stick_better":0,"stick_hot":0,"stick_low":0,"stick_name":"","stick_new":0,"stick_rate":0,"stick_return":0,"stick_throw":0,"stick_zeroC":0,"t_activity_id":"萨达","t_address_id":"美国,乡村,女神","t_consume_id":"撒大声地","t_other_id":"达到","t_overseas_id":"黑寡妇","t_stay_id":"萨达阿萨","t_traffic_id":"大","tagName":"美国,乡村,女神,萨达,撒大声地,黑寡妇,萨达阿萨,达到","total_price":6000,"total_price_child":0,"travel_title":"范德萨范德萨","uid":2,"userCity":"石家庄","username":"dsa","view_count":1}]
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
}
