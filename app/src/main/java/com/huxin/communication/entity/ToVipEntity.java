package com.huxin.communication.entity;

import java.util.List;

public class ToVipEntity {


    /**
     * vipModel : {"id":1,"matchingVip":50,"stickNumber":10}
     * combo : [{"id":1,"comboType":1,"comboOriginal":200,"comboPreference":180,"registerType":1,"state":1}]
     */

    private VipModelBean vipModel;
    private List<ComboBean> combo;

    public VipModelBean getVipModel() {
        return vipModel;
    }

    public void setVipModel(VipModelBean vipModel) {
        this.vipModel = vipModel;
    }

    public List<ComboBean> getCombo() {
        return combo;
    }

    public void setCombo(List<ComboBean> combo) {
        this.combo = combo;
    }

    public static class VipModelBean {
        /**
         * id : 1
         * matchingVip : 50
         * stickNumber : 10
         */

        private int id;
        private int matchingVip;
        private int stickNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMatchingVip() {
            return matchingVip;
        }

        public void setMatchingVip(int matchingVip) {
            this.matchingVip = matchingVip;
        }

        public int getStickNumber() {
            return stickNumber;
        }

        public void setStickNumber(int stickNumber) {
            this.stickNumber = stickNumber;
        }
    }

    public static class ComboBean {
        /**
         * id : 1
         * comboType : 1
         * comboOriginal : 200
         * comboPreference : 180
         * registerType : 1
         * state : 1
         */

        private int id;
        private int comboType;
        private int comboOriginal;
        private int comboPreference;
        private int registerType;
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getComboType() {
            return comboType;
        }

        public void setComboType(int comboType) {
            this.comboType = comboType;
        }

        public int getComboOriginal() {
            return comboOriginal;
        }

        public void setComboOriginal(int comboOriginal) {
            this.comboOriginal = comboOriginal;
        }

        public int getComboPreference() {
            return comboPreference;
        }

        public void setComboPreference(int comboPreference) {
            this.comboPreference = comboPreference;
        }

        public int getRegisterType() {
            return registerType;
        }

        public void setRegisterType(int registerType) {
            this.registerType = registerType;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
