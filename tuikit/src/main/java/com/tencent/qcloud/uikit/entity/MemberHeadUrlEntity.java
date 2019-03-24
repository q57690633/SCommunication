package com.tencent.qcloud.uikit.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MemberHeadUrlEntity implements Parcelable {
    /**
     * companyName : 北京极联互动科技有限公司
     * headUrl : ["http://39.105.203.33/upload/1552671345862/1552671345862_72.png","http://39.105.203.33/upload/1551080711471/1551080711471_954.jpg"]
     */

    private String companyName;
    private ArrayList<String> headUrl;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ArrayList<String> getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(ArrayList<String> headUrl) {
        this.headUrl = headUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.companyName);
        dest.writeStringList(this.headUrl);
    }

    public MemberHeadUrlEntity() {
    }

    protected MemberHeadUrlEntity(Parcel in) {
        this.companyName = in.readString();
        this.headUrl = in.createStringArrayList();
    }

    public static final Parcelable.Creator<MemberHeadUrlEntity> CREATOR = new Parcelable.Creator<MemberHeadUrlEntity>() {
        @Override
        public MemberHeadUrlEntity createFromParcel(Parcel source) {
            return new MemberHeadUrlEntity(source);
        }

        @Override
        public MemberHeadUrlEntity[] newArray(int size) {
            return new MemberHeadUrlEntity[size];
        }
    };
}
