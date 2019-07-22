package com.neu.his.pojo;

import java.io.Serializable;

/**
 * 数据库表doctor对应的实体类Doctor
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日11:50:24
 */
public class Doctor implements Serializable {
    private int doctorID;
    private String doctorLoginName;
    private String doctorPSW;
    private String doctorName;
    private int deptID;
    private int doctorType;
    private int doctorTitle;
    private int doctorSche;
    private int doctorRegistLevel;

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
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

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public int getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(int doctorType) {
        this.doctorType = doctorType;
    }

    public int getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(int doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public int getDoctorSche() {
        return doctorSche;
    }

    public void setDoctorSche(int doctorSche) {
        this.doctorSche = doctorSche;
    }

    public int getDoctorRegistLevel() {
        return doctorRegistLevel;
    }

    public void setDoctorRegistLevel(int doctorRegistLevel) {
        this.doctorRegistLevel = doctorRegistLevel;
    }
}
