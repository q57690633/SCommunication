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
         * companyName : 北京极联互动科技有限公司
         * curPage :
         * files : []
         * final_boat : 0.0
         * final_car : 0.0
         * final_price : 100.0
         * final_price_child : 0.0
         * final_price_evening : 0.0
         * final_price_family : 0.0
         * final_price_parent_child : 0.0
         * final_price_total : 0.0
         * headUrl : http://39.105.203.33/upload/1552671345862/1552671345862_72.png
         * id : 55
         * ifSevenInfo : 0
         * isCollect : 0
         * issue_count : 1
         * issue_time : 2019-03-19 03:42:43
         * keyWord :
         * line_or_throw : 0
         * max_price : 0.0
         * minPri_maxPri :
         * min_price : 0.0
         * open_time : 03:42~10:42
         * original_boat : 0.0
         * original_car : 0.0
         * original_price : 200.0
         * original_price_child : 0.0
         * original_price_evening : 0.0
         * original_price_family : 0.0
         * original_price_parent_child : 0.0
         * original_price_total : 0.0
         * photo_url :
         * sevenDays_ago :
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
         * stick_time : 1552938163064
         * stick_zeroC : 0
         * tagName : 美食,购物,大巴游
         * ticket_activity_id : 美食,购物
         * ticket_addr : 北京北京市
         * ticket_city_name : 北京市
         * ticket_name : 故宫
         * ticket_other_id : 大巴游
         * ticket_pro_code : 110000
         * ticket_pro_name : 北京
         * ticket_theme_id :
         * ticket_type : 1
         * uid : 70
         * userCity : 保定市
         * userPhone : 19937057195
         * user_idForCol : 70
         * username : 同业用户
         * view_count : 0
         */

        private String companyName;
        private String curPage;
        private double final_boat;
        private double final_car;
        private double final_price;
        private double final_price_child;
        private double final_price_evening;
        private double final_price_family;
        private double final_price_parent_child;
        private double final_price_total;
        private String headUrl;
        private int id;
        private int ifSevenInfo;
        private int isCollect;
        private int issue_count;
        private String issue_time;
        private String keyWord;
        private int line_or_throw;
        private double max_price;
        private String minPri_maxPri;
        private double min_price;
        private String open_time;
        private double original_boat;
        private double original_car;
        private double original_price;
        private double original_price_child;
        private double original_price_evening;
        private double original_price_family;
        private double original_price_parent_child;
        private double original_price_total;
        private String photo_url;
        private String sevenDays_ago;
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
        private int user_idForCol;
        private String username;
        private int view_count;
        private List<String> files;
        private String generalize;

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

        public double getFinal_boat() {
            return final_boat;
        }

        public void setFinal_boat(double final_boat) {
            this.final_boat = final_boat;
        }

        public double getFinal_car() {
            return final_car;
        }

        public void setFinal_car(double final_car) {
            this.final_car = final_car;
        }

        public double getFinal_price() {
            return final_price;
        }

        public void setFinal_price(double final_price) {
            this.final_price = final_price;
        }

        public double getFinal_price_child() {
            return final_price_child;
        }

        public void setFinal_price_child(double final_price_child) {
            this.final_price_child = final_price_child;
        }

        public double getFinal_price_evening() {
            return final_price_evening;
        }

        public void setFinal_price_evening(double final_price_evening) {
            this.final_price_evening = final_price_evening;
        }

        public double getFinal_price_family() {
            return final_price_family;
        }

        public void setFinal_price_family(double final_price_family) {
            this.final_price_family = final_price_family;
        }

        public double getFinal_price_parent_child() {
            return final_price_parent_child;
        }

        public void setFinal_price_parent_child(double final_price_parent_child) {
            this.final_price_parent_child = final_price_parent_child;
        }

        public double getFinal_price_total() {
            return final_price_total;
        }

        public void setFinal_price_total(double final_price_total) {
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

        public int getIfSevenInfo() {
            return ifSevenInfo;
        }

        public void setIfSevenInfo(int ifSevenInfo) {
            this.ifSevenInfo = ifSevenInfo;
        }

        public int getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(int isCollect) {
            this.isCollect = isCollect;
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

        public double getMax_price() {
            return max_price;
        }

        public void setMax_price(double max_price) {
            this.max_price = max_price;
        }

        public String getMinPri_maxPri() {
            return minPri_maxPri;
        }

        public void setMinPri_maxPri(String minPri_maxPri) {
            this.minPri_maxPri = minPri_maxPri;
        }

        public double getMin_price() {
            return min_price;
        }

        public void setMin_price(double min_price) {
            this.min_price = min_price;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public double getOriginal_boat() {
            return original_boat;
        }

        public void setOriginal_boat(double original_boat) {
            this.original_boat = original_boat;
        }

        public double getOriginal_car() {
            return original_car;
        }

        public void setOriginal_car(double original_car) {
            this.original_car = original_car;
        }

        public double getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(double original_price) {
            this.original_price = original_price;
        }

        public double getOriginal_price_child() {
            return original_price_child;
        }

        public void setOriginal_price_child(double original_price_child) {
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

        public double getOriginal_price_total() {
            return original_price_total;
        }

        public void setOriginal_price_total(double original_price_total) {
            this.original_price_total = original_price_total;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public String getSevenDays_ago() {
            return sevenDays_ago;
        }

        public void setSevenDays_ago(String sevenDays_ago) {
            this.sevenDays_ago = sevenDays_ago;
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

        public int getUser_idForCol() {
            return user_idForCol;
        }

        public void setUser_idForCol(int user_idForCol) {
            this.user_idForCol = user_idForCol;
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
            dest.writeDouble(this.final_boat);
            dest.writeDouble(this.final_car);
            dest.writeDouble(this.final_price);
            dest.writeDouble(this.final_price_child);
            dest.writeDouble(this.final_price_evening);
            dest.writeDouble(this.final_price_family);
            dest.writeDouble(this.final_price_parent_child);
            dest.writeDouble(this.final_price_total);
            dest.writeString(this.headUrl);
            dest.writeInt(this.id);
            dest.writeInt(this.ifSevenInfo);
            dest.writeInt(this.isCollect);
            dest.writeInt(this.issue_count);
            dest.writeString(this.issue_time);
            dest.writeString(this.keyWord);
            dest.writeInt(this.line_or_throw);
            dest.writeDouble(this.max_price);
            dest.writeString(this.minPri_maxPri);
            dest.writeDouble(this.min_price);
            dest.writeString(this.open_time);
            dest.writeDouble(this.original_boat);
            dest.writeDouble(this.original_car);
            dest.writeDouble(this.original_price);
            dest.writeDouble(this.original_price_child);
            dest.writeDouble(this.original_price_evening);
            dest.writeDouble(this.original_price_family);
            dest.writeDouble(this.original_price_parent_child);
            dest.writeDouble(this.original_price_total);
            dest.writeString(this.photo_url);
            dest.writeString(this.sevenDays_ago);
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
            dest.writeLong(this.stick_time);
            dest.writeInt(this.stick_zeroC);
            dest.writeString(this.tagName);
            dest.writeString(this.ticket_activity_id);
            dest.writeString(this.ticket_addr);
            dest.writeString(this.ticket_city_name);
            dest.writeString(this.ticket_name);
            dest.writeString(this.ticket_other_id);
            dest.writeString(this.ticket_pro_code);
            dest.writeString(this.ticket_pro_name);
            dest.writeString(this.ticket_theme_id);
            dest.writeInt(this.ticket_type);
            dest.writeInt(this.uid);
            dest.writeString(this.userCity);
            dest.writeString(this.userPhone);
            dest.writeInt(this.user_idForCol);
            dest.writeString(this.username);
            dest.writeInt(this.view_count);
            dest.writeStringList(this.files);
        }

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            this.companyName = in.readString();
            this.curPage = in.readString();
            this.final_boat = in.readDouble();
            this.final_car = in.readDouble();
            this.final_price = in.readDouble();
            this.final_price_child = in.readDouble();
            this.final_price_evening = in.readDouble();
            this.final_price_family = in.readDouble();
            this.final_price_parent_child = in.readDouble();
            this.final_price_total = in.readDouble();
            this.headUrl = in.readString();
            this.id = in.readInt();
            this.ifSevenInfo = in.readInt();
            this.isCollect = in.readInt();
            this.issue_count = in.readInt();
            this.issue_time = in.readString();
            this.keyWord = in.readString();
            this.line_or_throw = in.readInt();
            this.max_price = in.readDouble();
            this.minPri_maxPri = in.readString();
            this.min_price = in.readDouble();
            this.open_time = in.readString();
            this.original_boat = in.readDouble();
            this.original_car = in.readDouble();
            this.original_price = in.readDouble();
            this.original_price_child = in.readDouble();
            this.original_price_evening = in.readDouble();
            this.original_price_family = in.readDouble();
            this.original_price_parent_child = in.readDouble();
            this.original_price_total = in.readDouble();
            this.photo_url = in.readString();
            this.sevenDays_ago = in.readString();
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
            this.stick_time = in.readLong();
            this.stick_zeroC = in.readInt();
            this.tagName = in.readString();
            this.ticket_activity_id = in.readString();
            this.ticket_addr = in.readString();
            this.ticket_city_name = in.readString();
            this.ticket_name = in.readString();
            this.ticket_other_id = in.readString();
            this.ticket_pro_code = in.readString();
            this.ticket_pro_name = in.readString();
            this.ticket_theme_id = in.readString();
            this.ticket_type = in.readInt();
            this.uid = in.readInt();
            this.userCity = in.readString();
            this.userPhone = in.readString();
            this.user_idForCol = in.readInt();
            this.username = in.readString();
            this.view_count = in.readInt();
            this.files = in.createStringArrayList();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
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
