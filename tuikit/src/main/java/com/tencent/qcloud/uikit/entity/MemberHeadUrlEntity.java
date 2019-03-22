package com.tencent.qcloud.uikit.entity;

import java.util.List;

public class MemberHeadUrlEntity {
    /**
     * companyName : 北京极联互动科技有限公司
     * headUrl : ["http://39.105.203.33/upload/1552671345862/1552671345862_72.png","http://39.105.203.33/upload/1551080711471/1551080711471_954.jpg"]
     */

    private String companyName;
    private List<String> headUrl;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(List<String> headUrl) {
        this.headUrl = headUrl;
    }
}
