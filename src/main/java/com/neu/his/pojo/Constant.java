package com.neu.his.pojo;

import java.io.Serializable;

/**
 * 数据库表constant对应的实体类Constant
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日13:22:08
 */
public class Constant implements Serializable {
    private Integer constantID;
    private Integer constantTypeID;
    private String ConstantCode;
    private String constantName;

    public Integer getConstantID() {
        return constantID;
    }

    public void setConstantID(Integer constantID) {
        this.constantID = constantID;
    }

    public Integer getConstantTypeID() {
        return constantTypeID;
    }

    public void setConstantTypeID(Integer constantTypeID) {
        this.constantTypeID = constantTypeID;
    }

    public String getConstantCode() {
        return ConstantCode;
    }

    public void setConstantCode(String constantCode) {
        ConstantCode = constantCode;
    }

    public String getConstantName() {
        return constantName;
    }

    public void setConstantName(String constantName) {
        this.constantName = constantName;
    }
}
