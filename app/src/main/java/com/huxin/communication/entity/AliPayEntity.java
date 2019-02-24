package com.huxin.communication.entity;

public class AliPayEntity {

    /**
     * zfbOrder : alipay_sdk=alipay-sdk-java-3.3.87.ALL&app_id=2018121762587164&biz_content=%7B%22body%22%3A%22fa%22%2C%22out_trade_no%22%3A%22871550640541894%22%2C%22product_code%22%3A%22QUICK_WAP_WAY+%22%2C%22subject%22%3A%22%E7%99%BE%E8%A1%8C%E5%90%8C%E4%B8%9AVIP%E8%AE%A2%E5%8D%95%E4%BF%A1%E6%81%AF%22%2C%22timeout_express%22%3A%2260m%22%2C%22total_amount%22%3A%221%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F39.105.203.33%2Fjlkf%2Fweixin%2Fnotify_zfb.do&sign=dFqXfrDAKumfEs0VGhr57zWTFq3eFP0YZYp8pyARSm4z0RI51SOfR0rEo9J2Bb4XBf6cKshImsr5%2BEfKZbyDxjkzwSEsH5w4qDai5T63r1c5WLjUB4G%2FIgiXVnU3sijm4jd%2F%2F1PExT17xyPeXpECmJAD4tROZ1un6TUc0YH0TwWmRFSaPhoii2oyVu0SCvdf8QlzYSi97s5Vf2Ksl5cPfwVho4qTuGfUdNAaJAOY%2BtNEX6Yi52uqnlyg3GXQv6PtCFBQtKYaxkMtDwpyYU0BPV428ZkGh6UZYkExpZKvM7QEcmrr6g%2FXbrBtC2CibXFJokbtClYuz%2B2evbHvAprtnQ%3D%3D&sign_type=RSA2&timestamp=2019-02-20+13%3A29%3A01&version=1.0
     */

    private String zfbOrder;

    public String getZfbOrder() {
        return zfbOrder;
    }

    public void setZfbOrder(String zfbOrder) {
        this.zfbOrder = zfbOrder;
    }
}
