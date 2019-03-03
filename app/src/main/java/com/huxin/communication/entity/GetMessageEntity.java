package com.huxin.communication.entity;

public class GetMessageEntity {


    /**
     * head_url: 头像
     * msg :  消息
     * type:  类型 1单聊 2群聊
     * id:    聊天的id
     * num:   消息的次数
     */

    private String head_url;

    private String msg;

    private int type;

    private int id;

    private int num;

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
