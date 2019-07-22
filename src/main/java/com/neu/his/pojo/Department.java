package com.neu.his.pojo;

import java.io.Serializable;

/**
 * 数据库表department对应的实体类Department
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日12:12:14
 */
public class Department implements Serializable {
    private Integer deptID;
    private String deptCode;
    private String deptName;
    private Integer deptCategory;
    private Integer deptType;

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
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

    public Integer getDeptCategory() {
        return deptCategory;
    }

    public void setDeptCategory(Integer deptCategory) {
        this.deptCategory = deptCategory;
    }

    public Integer getDeptType() {
        return deptType;
    }

    public void setDeptType(Integer deptType) {
        this.deptType = deptType;
    }
}
