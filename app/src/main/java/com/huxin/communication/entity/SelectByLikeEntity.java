package com.huxin.communication.entity;

public class SelectByLikeEntity {


    /**
     * id : 1047
     * areaId : 2
     * areaSecondId : 25
     * villageName : 金顶宝座
     * selectNumber :
     */

    private int id;
    private int areaId;
    private int areaSecondId;
    private String villageName;
    private String selectNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getAreaSecondId() {
        return areaSecondId;
    }

    public void setAreaSecondId(int areaSecondId) {
        this.areaSecondId = areaSecondId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getSelectNumber() {
        return selectNumber;
    }

    public void setSelectNumber(String selectNumber) {
        this.selectNumber = selectNumber;
    }
}
