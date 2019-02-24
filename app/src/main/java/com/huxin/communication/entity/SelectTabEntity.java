package com.huxin.communication.entity;

import java.util.List;

public class SelectTabEntity {


    /**
     * stickNumber : 2
     * tag : [{"id":1,"tabClassify":"","tabName":"学区房","tabType":1},{"id":2,"tabClassify":"","tabName":"近地铁","tabType":1}]
     */

    private int stickNumber;
    private List<TagBean> tag;

    public int getStickNumber() {
        return stickNumber;
    }

    public void setStickNumber(int stickNumber) {
        this.stickNumber = stickNumber;
    }

    public List<TagBean> getTag() {
        return tag;
    }

    public void setTag(List<TagBean> tag) {
        this.tag = tag;
    }

    public static class TagBean {
        /**
         * id : 1
         * tabClassify :
         * tabName : 学区房
         * tabType : 1
         */

        private int id;
        private String tabClassify;
        private String tabName;
        private int tabType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTabClassify() {
            return tabClassify;
        }

        public void setTabClassify(String tabClassify) {
            this.tabClassify = tabClassify;
        }

        public String getTabName() {
            return tabName;
        }

        public void setTabName(String tabName) {
            this.tabName = tabName;
        }

        public int getTabType() {
            return tabType;
        }

        public void setTabType(int tabType) {
            this.tabType = tabType;
        }
    }
}
