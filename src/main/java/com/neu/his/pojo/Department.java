package com.neu.his.pojo;

import java.io.Serializable;

/**
 * 数据库表department对应的实体类Department
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日12:12:14
 */
public class Department implements Serializable {
    private int deptID;
    private String deptCode;
    private String deptName;
    private int deptCategory;
    private int deptType;

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptCategory() {
        return deptCategory;
    }

    public void setDeptCategory(int deptCategory) {
        this.deptCategory = deptCategory;
    }

    public int getDeptType() {
        return deptType;
    }

    public void setDeptType(int deptType) {
        this.deptType = deptType;
    }
}
