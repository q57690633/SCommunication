package com.huxin.communication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AroundTravelEntity implements Parcelable {


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

    public static class ListBean implements Parcelable {


        /**
         * TActivityId : 摄影,亲子,纯玩,休闲
         * TAddressId : 登山
         * TConsumeId : 零购物,零自费
         * TOtherId : 定制游,自由行,半自动
         * TOverseasId :
         * TStayId : 农家乐,庄园酒店
         * TTrafficId : 高铁,含接送,含小交通
         * companyName :
         * curPage :
         * depart_code : 300000
         * depart_name : 天津市
         * depart_pro_code : 120000
         * files : []
         * finalPrice : 400.0
         * finalPriceChild : 180.0
         * generalize : 周边游第二贴
         * goalsId : 5589
         * goals_city : 北京市
         * goals_city_code : 100000
         * goals_pro : 北京
         * headUrl :
         * id : 62
         * ifSevenInfo : 0
         * isCollect : 1
         * issue_count : 4
         * issue_time : 2019-02-21 17:29:59
         * keyWord :
         * lineOrThrow : 1
         * maxDay : 0
         * maxPrice : 0.0
         * minDay : 0
         * minPri_maxPri :
         * minPrice : 0.0
         * numberDays : 1
         * photo_url : http://39.105.203.33/upload/1550741398737/1550741398737_483.jpg
         * pickupPrice : 0
         * qrCode_url :
         * returnPrice : 100.0
         * returnPriceChild : 20.0
         * sevenDays_ago :
         * sort_type : 0
         * specilDays : 0
         * spotName : 八达岭长城
         * stick : 2
         * stick_better : 0
         * stick_hot : 0
         * stick_low : 0
         * stick_name :
         * stick_new : 0
         * stick_rate : 0
         * stick_return : 0
         * stick_throw : 0
         * stick_time : 0
         * stick_zeroC : 0
         * tagName : 登山,摄影,亲子,纯玩,休闲,零购物,零自费,农家乐,庄园酒店,定制游,自由行,半自动
         * totalPrice : 500.0
         * totalPriceChild : 200.0
         * travelTitle : 周边游第二贴
         * travel_kind : 1
         * uid : 99
         * userCity :
         * userPhone :
         * user_idForCol : 70
         * username :
         * view_count : 45
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
        private double finalPrice;
        private double finalPriceChild;
        private String generalize;
        private String goalsId;
        private String goals_city;
        private String goals_city_code;
        private String goals_pro;
        private String headUrl;
        private int id;
        private int ifSevenInfo;
        private int isCollect;
        private int issue_count;
        private String issue_time;
        private String keyWord;
        private int lineOrThrow;
        private int maxDay;
        private double maxPrice;
        private int minDay;
        private String minPri_maxPri;
        private double minPrice;
        private int numberDays;
        private String photo_url;
        private int pickupPrice;
        private String qrCode_url;
        private double returnPrice;
        private double returnPriceChild;
        private String sevenDays_ago;
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
        private int stick_time;
        private int stick_zeroC;
        private String tagName;
        private double totalPrice;
        private double totalPriceChild;
        private String travelTitle;
        private int travel_kind;
        private int uid;
        private String userCity;
        private String userPhone;
        private int user_idForCol;
        private String username;
        private int view_count;
        private List<String> files;

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

        public double getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(double finalPrice) {
            this.finalPrice = finalPrice;
        }

        public double getFinalPriceChild() {
            return finalPriceChild;
        }

        public void setFinalPriceChild(double finalPriceChild) {
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

        public double getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(double maxPrice) {
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

        public double getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(double minPrice) {
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

        public String getQrCode_url() {
            return qrCode_url;
        }

        public void setQrCode_url(String qrCode_url) {
            this.qrCode_url = qrCode_url;
        }

        public double getReturnPrice() {
            return returnPrice;
        }

        public void setReturnPrice(double returnPrice) {
            this.returnPrice = returnPrice;
        }

        public double getReturnPriceChild() {
            return returnPriceChild;
        }

        public void setReturnPriceChild(double returnPriceChild) {
            this.returnPriceChild = returnPriceChild;
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

        public int getStick_time() {
            return stick_time;
        }

        public void setStick_time(int stick_time) {
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

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public double getTotalPriceChild() {
            return totalPriceChild;
        }

        public void setTotalPriceChild(double totalPriceChild) {
            this.totalPriceChild = totalPriceChild;
        }

        public String getTravelTitle() {
            return travelTitle;
        }

        public void setTravelTitle(String travelTitle) {
            this.travelTitle = travelTitle;
        }

        public int getTravel_kind() {
            return travel_kind;
        }

        public void setTravel_kind(int travel_kind) {
            this.travel_kind = travel_kind;
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
            dest.writeString(this.TActivityId);
            dest.writeString(this.TAddressId);
            dest.writeString(this.TConsumeId);
            dest.writeString(this.TOtherId);
            dest.writeString(this.TOverseasId);
            dest.writeString(this.TStayId);
            dest.writeString(this.TTrafficId);
            dest.writeString(this.companyName);
            dest.writeString(this.curPage);
            dest.writeString(this.depart_code);
            dest.writeString(this.depart_name);
            dest.writeString(this.depart_pro_code);
            dest.writeDouble(this.finalPrice);
            dest.writeDouble(this.finalPriceChild);
            dest.writeString(this.generalize);
            dest.writeString(this.goalsId);
            dest.writeString(this.goals_city);
            dest.writeString(this.goals_city_code);
            dest.writeString(this.goals_pro);
            dest.writeString(this.headUrl);
            dest.writeInt(this.id);
            dest.writeInt(this.ifSevenInfo);
            dest.writeInt(this.isCollect);
            dest.writeInt(this.issue_count);
            dest.writeString(this.issue_time);
            dest.writeString(this.keyWord);
            dest.writeInt(this.lineOrThrow);
            dest.writeInt(this.maxDay);
            dest.writeDouble(this.maxPrice);
            dest.writeInt(this.minDay);
            dest.writeString(this.minPri_maxPri);
            dest.writeDouble(this.minPrice);
            dest.writeInt(this.numberDays);
            dest.writeString(this.photo_url);
            dest.writeInt(this.pickupPrice);
            dest.writeString(this.qrCode_url);
            dest.writeDouble(this.returnPrice);
            dest.writeDouble(this.returnPriceChild);
            dest.writeString(this.sevenDays_ago);
            dest.writeInt(this.sort_type);
            dest.writeInt(this.specilDays);
            dest.writeString(this.spotName);
            dest.writeInt(this.stick);
            dest.writeInt(this.stick_better);
            dest.writeInt(this.stick_hot);
            dest.writeInt(this.stick_low);
            dest.writeString(this.stick_name);
            dest.writeInt(this.stick_new);
            dest.writeInt(this.stick_rate);
            dest.writeInt(this.stick_return);
            dest.writeInt(this.stick_throw);
            dest.writeInt(this.stick_time);
            dest.writeInt(this.stick_zeroC);
            dest.writeString(this.tagName);
            dest.writeDouble(this.totalPrice);
            dest.writeDouble(this.totalPriceChild);
            dest.writeString(this.travelTitle);
            dest.writeInt(this.travel_kind);
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
            this.TActivityId = in.readString();
            this.TAddressId = in.readString();
            this.TConsumeId = in.readString();
            this.TOtherId = in.readString();
            this.TOverseasId = in.readString();
            this.TStayId = in.readString();
            this.TTrafficId = in.readString();
            this.companyName = in.readString();
            this.curPage = in.readString();
            this.depart_code = in.readString();
            this.depart_name = in.readString();
            this.depart_pro_code = in.readString();
            this.finalPrice = in.readDouble();
            this.finalPriceChild = in.readDouble();
            this.generalize = in.readString();
            this.goalsId = in.readString();
            this.goals_city = in.readString();
            this.goals_city_code = in.readString();
            this.goals_pro = in.readString();
            this.headUrl = in.readString();
            this.id = in.readInt();
            this.ifSevenInfo = in.readInt();
            this.isCollect = in.readInt();
            this.issue_count = in.readInt();
            this.issue_time = in.readString();
            this.keyWord = in.readString();
            this.lineOrThrow = in.readInt();
            this.maxDay = in.readInt();
            this.maxPrice = in.readDouble();
            this.minDay = in.readInt();
            this.minPri_maxPri = in.readString();
            this.minPrice = in.readDouble();
            this.numberDays = in.readInt();
            this.photo_url = in.readString();
            this.pickupPrice = in.readInt();
            this.qrCode_url = in.readString();
            this.returnPrice = in.readDouble();
            this.returnPriceChild = in.readDouble();
            this.sevenDays_ago = in.readString();
            this.sort_type = in.readInt();
            this.specilDays = in.readInt();
            this.spotName = in.readString();
            this.stick = in.readInt();
            this.stick_better = in.readInt();
            this.stick_hot = in.readInt();
            this.stick_low = in.readInt();
            this.stick_name = in.readString();
            this.stick_new = in.readInt();
            this.stick_rate = in.readInt();
            this.stick_return = in.readInt();
            this.stick_throw = in.readInt();
            this.stick_time = in.readInt();
            this.stick_zeroC = in.readInt();
            this.tagName = in.readString();
            this.totalPrice = in.readDouble();
            this.totalPriceChild = in.readDouble();
            this.travelTitle = in.readString();
            this.travel_kind = in.readInt();
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

    public AroundTravelEntity() {
    }

    protected AroundTravelEntity(Parcel in) {
        this.curPage = in.readString();
        this.pageSize = in.readInt();
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Parcelable.Creator<AroundTravelEntity> CREATOR = new Parcelable.Creator<AroundTravelEntity>() {
        @Override
        public AroundTravelEntity createFromParcel(Parcel source) {
            return new AroundTravelEntity(source);
        }

        @Override
        public AroundTravelEntity[] newArray(int size) {
            return new AroundTravelEntity[size];
        }
    };
}
