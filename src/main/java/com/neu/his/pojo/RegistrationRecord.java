package com.neu.his.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表registration_record对应的类RegistrationRecord
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日13:36:18
 */
public class RegistrationRecord implements Serializable {
    private Integer registID;
    private Integer patientRecordID;
    private Integer registChargeCategory;
    private Integer registLevel;
    private Integer doctorID;
    private Integer registBook;
    private String registState;
    private Date registTime;
    private Integer registUserID;
    private Integer deptID;
    private String registNoon;
    private Date registDate;

    public Integer getRegistID() {
        return registID;
    }

    public void setRegistID(Integer registID) {
        this.registID = registID;
    }

    public Integer getPatientRecordID() {
        return patientRecordID;
    }

    public void setPatientRecordID(Integer patientRecordID) {
        this.patientRecordID = patientRecordID;
    }

    public Integer getRegistChargeCategory() {
        return registChargeCategory;
    }

    public void setRegistChargeCategory(Integer registChargeCategory) {
        this.registChargeCategory = registChargeCategory;
    }

    public Integer getRegistLevel() {
        return registLevel;
    }

    public void setRegistLevel(Integer registLevel) {
        this.registLevel = registLevel;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public Integer getRegistBook() {
        return registBook;
    }

    public void setRegistBook(Integer registBook) {
        this.registBook = registBook;
    }

    public String getRegistState() {
        return registState;
    }

    public void setRegistState(String registState) {
        this.registState = registState;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Integer getRegistUserID() {
        return registUserID;
    }

    public void setRegistUserID(Integer registUserID) {
        this.registUserID = registUserID;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public String getRegistNoon() {
        return registNoon;
    }

    public void setRegistNoon(String registNoon) {
        this.registNoon = registNoon;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }
}
