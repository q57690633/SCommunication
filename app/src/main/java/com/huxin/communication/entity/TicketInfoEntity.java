package com.huxin.communication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TicketInfoEntity implements Parcelable {

    /**
     * curPage : 1
     * pageSize : 4
     * list : [{"companyName":"","curPage":"","files":[],"final_boat":30,"final_car":20,"final_price":9,"final_price_child":10,"final_price_evening":30.2,"final_price_family":21,"final_price_parent_child":20,"goals_city":"","headUrl":"/upload/1547465839612/1547465839612_12.jpg","id":1,"issue_count":3,"issue_time":"2019-01-17 13:52:06","keyWord":"","line_or_throw":0,"max_price":0,"minPri_maxPri":"","min_price":0,"open_time":"9:00-18:00","original_boat":25,"original_car":22,"original_price":10,"original_price_child":15,"original_price_evening":20.2,"original_price_family":40.5,"original_price_parent_child":25.2,"photo_url":"","return_boat":2,"return_car":2,"return_price":1,"return_price_child":1,"return_price_evening":2,"return_price_family":20,"return_price_parent_child":22,"sort_type":0,"stick":1,"stick_better":0,"stick_hot":0,"stick_low":0,"stick_name":"上新","stick_new":1,"stick_rate":0,"stick_return":0,"stick_throw":0,"stick_zeroC":0,"tagName":"欢乐谷,海洋世界,滑雪","ticket_activity_id":"滑雪","ticket_addr":"坡子街1号","ticket_city_name":"长沙市","ticket_name":"博物馆","ticket_other_id":"","ticket_pro_name":"湖南省","ticket_theme_id":"欢乐谷,海洋世界","ticket_type":1,"uid":13,"userCity":"石家庄","username":"fds","view_count":0},{"companyName":"","curPage":"","files":[],"final_boat":1,"final_car":1,"final_price":1,"final_price_child":1,"final_price_evening":1,"final_price_family":1,"final_price_parent_child":1,"goals_city":"","headUrl":"","id":3,"issue_count":1,"issue_time":"2019-01-16 16:36:29","keyWord":"","line_or_throw":1,"max_price":0,"minPri_maxPri":"","min_price":0,"open_time":"9:00-18:00","original_boat":1,"original_car":1,"original_price":1,"original_price_child":1,"original_price_evening":1,"original_price_family":1,"original_price_parent_child":1,"photo_url":"","return_boat":1,"return_car":1,"return_price":1,"return_price_child":1,"return_price_evening":1,"return_price_family":1,"return_price_parent_child":1,"sort_type":0,"stick":1,"stick_better":0,"stick_hot":0,"stick_low":0,"stick_name":"上新","stick_new":1,"stick_rate":0,"stick_return":0,"stick_throw":0,"stick_zeroC":0,"tagName":"欢乐谷,臭豆腐,吃鸡,吃饭,","ticket_activity_id":"吃鸡,吃饭","ticket_addr":"太平街12号","ticket_city_name":"长沙市","ticket_name":"哈哈","ticket_other_id":"","ticket_pro_name":"湖南省","ticket_theme_id":"欢乐谷,臭豆腐","ticket_type":1,"uid":1,"userCity":"长春市","username":"zail","view_count":0},{"companyName":"","curPage":"","files":[],"final_boat":1,"final_car":1,"final_price":1,"final_price_child":1,"final_price_evening":1,"final_price_family":1,"final_price_parent_child":1,"goals_city":"","headUrl":"","id":4,"issue_count":1,"issue_time":"2019-01-16 16:38:09","keyWord":"","line_or_throw":1,"max_price":0,"minPri_maxPri":"","min_price":0,"open_time":"9:00-18:00","original_boat":1,"original_car":1,"original_price":1,"original_price_child":1,"original_price_evening":1,"original_price_family":1,"original_price_parent_child":1,"photo_url":"","return_boat":1,"return_car":1,"return_price":1,"return_price_child":1,"return_price_evening":1,"return_price_family":1,"return_price_parent_child":1,"sort_type":0,"stick":1,"stick_better":0,"stick_hot":0,"stick_low":0,"stick_name":"上新","stick_new":1,"stick_rate":0,"stick_return":0,"stick_throw":0,"stick_zeroC":0,"tagName":"充值,养生,充卡","ticket_activity_id":"","ticket_addr":"邮局路","ticket_city_name":"长沙市","ticket_name":"更新测试","ticket_other_id":"充卡","ticket_pro_name":"湖南省","ticket_theme_id":"充值,养生","ticket_type":2,"uid":1,"userCity":"长春市","username":"zail","view_count":0},{"companyName":"","curPage":"","files":[],"final_boat":2,"final_car":2,"final_price":9,"final_price_child":10,"final_price_evening":22,"final_price_family":22,"final_price_parent_child":22,"goals_city":"","headUrl":"","id":2,"issue_count":1,"issue_time":"2019-01-16 16:35:06","keyWord":"","line_or_throw":0,"max_price":0,"minPri_maxPri":"","min_price":0,"open_time":"9:00-18:00","original_boat":2,"original_car":2,"original_price":10,"original_price_child":15,"original_price_evening":20,"original_price_family":22,"original_price_parent_child":22,"photo_url":"","return_boat":2,"return_car":2,"return_price":1,"return_price_child":1,"return_price_evening":22,"return_price_family":22,"return_price_parent_child":22,"sort_type":0,"stick":2,"stick_better":0,"stick_hot":0,"stick_low":0,"stick_name":"","stick_new":0,"stick_rate":0,"stick_return":0,"stick_throw":0,"stick_zeroC":0,"tagName":"臭豆腐,欢乐谷,滑雪,吃鸡","ticket_activity_id":"滑雪,吃鸡","ticket_addr":"太平街1号","ticket_city_name":"长沙市","ticket_name":"呵呵","ticket_other_id":"","ticket_pro_name":"湖南省","ticket_theme_id":"臭豆腐,欢乐谷","ticket_type":1,"uid":1,"userCity":"长春市","username":"zail","view_count":0}]
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

    public static class ListBean implements Parcelable {


        /**
         * companyName :
         * curPage :
         * files : []
         * final_boat : 30
         * final_car : 20
         * final_price : 9
         * final_price_child : 10
         * final_price_evening : 30.2
         * final_price_family : 21
         * final_price_parent_child : 20
         * goals_city :
         * headUrl : /upload/1547465839612/1547465839612_12.jpg
         * id : 1
         * issue_count : 3
         * issue_time : 2019-01-17 13:52:06
         * keyWord :
         * line_or_throw : 0
         * max_price : 0
         * minPri_maxPri :
         * min_price : 0
         * open_time : 9:00-18:00
         * original_boat : 25
         * original_car : 22
         * original_price : 10
         * original_price_child : 15
         * original_price_evening : 20.2
         * original_price_family : 40.5
         * original_price_parent_child : 25.2
         * photo_url :
         * return_boat : 2
         * return_car : 2
         * return_price : 1
         * return_price_child : 1
         * return_price_evening : 2
         * return_price_family : 20
         * return_price_parent_child : 22
         * sort_type : 0
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
         * tagName : 欢乐谷,海洋世界,滑雪
         * ticket_activity_id : 滑雪
         * ticket_addr : 坡子街1号
         * ticket_city_name : 长沙市
         * ticket_name : 博物馆
         * ticket_other_id :
         * ticket_pro_name : 湖南省
         * ticket_theme_id : 欢乐谷,海洋世界
         * ticket_type : 1
         * uid : 13
         * userCity : 石家庄
         * username : fds
         * view_count : 0
         */

        private String companyName;
        private String curPage;
        private int final_boat;
        private int final_car;
        private int final_price;
        private int final_price_child;
        private double final_price_evening;
        private int final_price_family;
        private int final_price_parent_child;
        private String goals_city;
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
        private double original_price_evening;
        private double original_price_family;
        private double original_price_parent_child;
        private String photo_url;
        private int return_boat;
        private int return_car;
        private int return_price;
        private int return_price_child;
        private int return_price_evening;
        private int return_price_family;
        private int return_price_parent_child;
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
        private int stick_zeroC;
        private String tagName;
        private String ticket_activity_id;
        private String ticket_addr;
        private String ticket_city_name;
        private String ticket_name;
        private String ticket_other_id;
        private String ticket_pro_name;
        private String ticket_theme_id;
        private int ticket_type;
        private int uid;
        private String userCity;
        private String username;
        private int view_count;
        private List<String> files;
        private String generalize;
        private String isCollect;

        public String getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(String isCollect) {
            this.isCollect = isCollect;
        }

        public String getGeneralize() {
            return generalize;
        }

        public void setGeneralize(String generalize) {
            this.generalize = generalize;
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

        public double getFinal_price_evening() {
            return final_price_evening;
        }

        public void setFinal_price_evening(double final_price_evening) {
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

        public double getOriginal_price_evening() {
            return original_price_evening;
        }

        public void setOriginal_price_evening(double original_price_evening) {
            this.original_price_evening = original_price_evening;
        }

        public double getOriginal_price_family() {
            return original_price_family;
        }

        public void setOriginal_price_family(double original_price_family) {
            this.original_price_family = original_price_family;
        }

        public double getOriginal_price_parent_child() {
            return original_price_parent_child;
        }

        public void setOriginal_price_parent_child(double original_price_parent_child) {
            this.original_price_parent_child = original_price_parent_child;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public int getReturn_boat() {
            return return_boat;
        }

        public void setReturn_boat(int return_boat) {
            this.return_boat = return_boat;
        }

        public int getReturn_car() {
            return return_car;
        }

        public void setReturn_car(int return_car) {
            this.return_car = return_car;
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

        public int getReturn_price_evening() {
            return return_price_evening;
        }

        public void setReturn_price_evening(int return_price_evening) {
            this.return_price_evening = return_price_evening;
        }

        public int getReturn_price_family() {
            return return_price_family;
        }

        public void setReturn_price_family(int return_price_family) {
            this.return_price_family = return_price_family;
        }

        public int getReturn_price_parent_child() {
            return return_price_parent_child;
        }

        public void setReturn_price_parent_child(int return_price_parent_child) {
            this.return_price_parent_child = return_price_parent_child;
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

        public List<String> getFiles() {
            return files;
        }

        public void setFiles(List<String> files) {
            this.files = files;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.companyName);
            dest.writeString(this.curPage);
            dest.writeInt(this.final_boat);
            dest.writeInt(this.final_car);
            dest.writeInt(this.final_price);
            dest.writeInt(this.final_price_child);
            dest.writeDouble(this.final_price_evening);
            dest.writeInt(this.final_price_family);
            dest.writeInt(this.final_price_parent_child);
            dest.writeString(this.goals_city);
            dest.writeString(this.headUrl);
            dest.writeInt(this.id);
            dest.writeInt(this.issue_count);
            dest.writeString(this.issue_time);
            dest.writeString(this.keyWord);
            dest.writeInt(this.line_or_throw);
            dest.writeInt(this.max_price);
            dest.writeString(this.minPri_maxPri);
            dest.writeInt(this.min_price);
            dest.writeString(this.open_time);
            dest.writeInt(this.original_boat);
            dest.writeInt(this.original_car);
            dest.writeInt(this.original_price);
            dest.writeInt(this.original_price_child);
            dest.writeDouble(this.original_price_evening);
            dest.writeDouble(this.original_price_family);
            dest.writeDouble(this.original_price_parent_child);
            dest.writeString(this.photo_url);
            dest.writeInt(this.return_boat);
            dest.writeInt(this.return_car);
            dest.writeInt(this.return_price);
            dest.writeInt(this.return_price_child);
            dest.writeInt(this.return_price_evening);
            dest.writeInt(this.return_price_family);
            dest.writeInt(this.return_price_parent_child);
            dest.writeInt(this.sort_type);
            dest.writeInt(this.stick);
            dest.writeInt(this.stick_better);
            dest.writeInt(this.stick_hot);
            dest.writeInt(this.stick_low);
            dest.writeString(this.stick_name);
            dest.writeInt(this.stick_new);
            dest.writeInt(this.stick_rate);
            dest.writeInt(this.stick_return);
            dest.writeInt(this.stick_throw);
            dest.writeInt(this.stick_zeroC);
            dest.writeString(this.tagName);
            dest.writeString(this.ticket_activity_id);
            dest.writeString(this.ticket_addr);
            dest.writeString(this.ticket_city_name);
            dest.writeString(this.ticket_name);
            dest.writeString(this.ticket_other_id);
            dest.writeString(this.ticket_pro_name);
            dest.writeString(this.ticket_theme_id);
            dest.writeInt(this.ticket_type);
            dest.writeInt(this.uid);
            dest.writeString(this.userCity);
            dest.writeString(this.username);
            dest.writeInt(this.view_count);
            dest.writeStringList(this.files);
            dest.writeString(this.generalize);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.companyName = in.readString();
            this.curPage = in.readString();
            this.final_boat = in.readInt();
            this.final_car = in.readInt();
            this.final_price = in.readInt();
            this.final_price_child = in.readInt();
            this.final_price_evening = in.readDouble();
            this.final_price_family = in.readInt();
            this.final_price_parent_child = in.readInt();
            this.goals_city = in.readString();
            this.headUrl = in.readString();
            this.id = in.readInt();
            this.issue_count = in.readInt();
            this.issue_time = in.readString();
            this.keyWord = in.readString();
            this.line_or_throw = in.readInt();
            this.max_price = in.readInt();
            this.minPri_maxPri = in.readString();
            this.min_price = in.readInt();
            this.open_time = in.readString();
            this.original_boat = in.readInt();
            this.original_car = in.readInt();
            this.original_price = in.readInt();
            this.original_price_child = in.readInt();
            this.original_price_evening = in.readDouble();
            this.original_price_family = in.readDouble();
            this.original_price_parent_child = in.readDouble();
            this.photo_url = in.readString();
            this.return_boat = in.readInt();
            this.return_car = in.readInt();
            this.return_price = in.readInt();
            this.return_price_child = in.readInt();
            this.return_price_evening = in.readInt();
            this.return_price_family = in.readInt();
            this.return_price_parent_child = in.readInt();
            this.sort_type = in.readInt();
            this.stick = in.readInt();
            this.stick_better = in.readInt();
            this.stick_hot = in.readInt();
            this.stick_low = in.readInt();
            this.stick_name = in.readString();
            this.stick_new = in.readInt();
            this.stick_rate = in.readInt();
            this.stick_return = in.readInt();
            this.stick_throw = in.readInt();
            this.stick_zeroC = in.readInt();
            this.tagName = in.readString();
            this.ticket_activity_id = in.readString();
            this.ticket_addr = in.readString();
            this.ticket_city_name = in.readString();
            this.ticket_name = in.readString();
            this.ticket_other_id = in.readString();
            this.ticket_pro_name = in.readString();
            this.ticket_theme_id = in.readString();
            this.ticket_type = in.readInt();
            this.uid = in.readInt();
            this.userCity = in.readString();
            this.username = in.readString();
            this.view_count = in.readInt();
            this.files = in.createStringArrayList();
            this.generalize = in.readString();
        }

        public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
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
        dest.writeTypedList(this.list);
    }

    public TicketInfoEntity() {
    }

    protected TicketInfoEntity(Parcel in) {
        this.curPage = in.readString();
        this.pageSize = in.readInt();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Parcelable.Creator<TicketInfoEntity> CREATOR = new Parcelable.Creator<TicketInfoEntity>() {
        @Override
        public TicketInfoEntity createFromParcel(Parcel source) {
            return new TicketInfoEntity(source);
        }

        @Override
        public TicketInfoEntity[] newArray(int size) {
            return new TicketInfoEntity[size];
        }
    };
}
