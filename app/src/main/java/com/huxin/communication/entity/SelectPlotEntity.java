package com.huxin.communication.entity;

public class SelectPlotEntity {

    /**
     * id : 3
     * areaId : 3
     * areaSecondId :
     * villageName : 慧时欣园
     * selectNumber : 25
     */

    private int id;
    private int areaId;
    private String areaSecondId;
    private String villageName;
    private int selectNumber;

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

    public String getAreaSecondId() {
        return areaSecondId;
    }

    public void setAreaSecondId(String areaSecondId) {
        this.areaSecondId = areaSecondId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public int getSelectNumber() {
        return selectNumber;
    }

    public void setSelectNumber(int selectNumber) {
        this.selectNumber = selectNumber;
    }
}
