package com.tencent.qcloud.uikit.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MemberHeadUrlEntity/* implements Parcelable*/ {


    /**
     * companyName : 北京极联互动科技有限公司
     * data : [{"headUrl":"http://39.105.203.33/upload/1552671345862/1552671345862_72.png","uid":70},{"headUrl":"http://39.105.203.33/upload/1551080711471/1551080711471_954.jpg","uid":114},{"headUrl":"http://39.105.203.33/upload/1553449868222/1553449868222_441.png","uid":182}]
     */

    private String companyName;
    private List<DataBean> data;

    public MemberHeadUrlEntity() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * headUrl : http://39.105.203.33/upload/1552671345862/1552671345862_72.png
         * uid : 70
         */

        private String headUrl;
        private int uid;

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
