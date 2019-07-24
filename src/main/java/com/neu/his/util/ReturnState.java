package com.neu.his.util;

public enum StateEnum {

    State501("挂号失败"),
    State502("挂号成功"),
    State503("登录异常"),
    State504("病历首页填写成功"),
    State505("病历首页填写失败");

    private String detail;

    StateEnum(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
