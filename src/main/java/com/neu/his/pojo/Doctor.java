package com.neu.his.pojo;

import java.io.Serializable;

/**
 * 数据库表doctor对应的实体类Doctor
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日11:50:24
 */
public class Doctor implements Serializable {
    private Integer doctorID;
    private String doctorLoginName;
    private String doctorPSW;
    private String doctorName;
    private Integer deptID;
    private Integer doctorType;
    private Integer doctorTitle;
    private Integer doctorSche;
    private Integer doctorRegistLevel;

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorLoginName() {
        return doctorLoginName;
    }

    public void setDoctorLoginName(String doctorLoginName) {
        this.doctorLoginName = doctorLoginName;
    }

    public String getDoctorPSW() {
        return doctorPSW;
    }

    public void setDoctorPSW(String doctorPSW) {
        this.doctorPSW = doctorPSW;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Integer getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(Integer doctorType) {
        this.doctorType = doctorType;
    }

    public Integer getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(Integer doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public Integer getDoctorSche() {
        return doctorSche;
    }

    public void setDoctorSche(Integer doctorSche) {
        this.doctorSche = doctorSche;
    }

    public Integer getDoctorRegistLevel() {
        return doctorRegistLevel;
    }

    public void setDoctorRegistLevel(Integer doctorRegistLevel) {
        this.doctorRegistLevel = doctorRegistLevel;
    }
}
