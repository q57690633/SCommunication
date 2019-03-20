package com.huxin.communication.entity;

import java.util.List;

public class TabTicketNameEntity {

    private List<ThemeListBean> themeList;
    private List<ActivityListBean> activityList;
    private List<OtherListBean> otherList;

    public List<ThemeListBean> getThemeList() {
        return themeList;
    }

    public void setThemeList(List<ThemeListBean> themeList) {
        this.themeList = themeList;
    }

    public List<ActivityListBean> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityListBean> activityList) {
        this.activityList = activityList;
    }

    public List<OtherListBean> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<OtherListBean> otherList) {
        this.otherList = otherList;
    }

    public static class ThemeListBean {
        /**
         * id : 112
         * tagKind : 2
         * tagName : 景点门票
         * tagType : 7
         */

        private int id;
        private int tagKind;
        private String tagName;
        private int tagType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTagKind() {
            return tagKind;
        }

        public void setTagKind(int tagKind) {
            this.tagKind = tagKind;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getTagType() {
            return tagType;
        }

        public void setTagType(int tagType) {
            this.tagType = tagType;
        }
    }

    public static class ActivityListBean {
        /**
         * id : 130
         * tagKind : 2
         * tagName : 飞机票
         * tagType : 8
         */

        private int id;
        private int tagKind;
        private String tagName;
        private int tagType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTagKind() {
            return tagKind;
        }

        public void setTagKind(int tagKind) {
            this.tagKind = tagKind;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getTagType() {
            return tagType;
        }

        public void setTagType(int tagType) {
            this.tagType = tagType;
        }
    }

    public static class OtherListBean {
        /**
         * id : 19
         * tagKind : 1
         * tagName : 萨达
         * tagType : 6
         */

        private int id;
        private int tagKind;
        private String tagName;
        private int tagType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTagKind() {
            return tagKind;
        }

        public void setTagKind(int tagKind) {
            this.tagKind = tagKind;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getTagType() {
            return tagType;
        }

        public void setTagType(int tagType) {
            this.tagType = tagType;
        }
    }
}
