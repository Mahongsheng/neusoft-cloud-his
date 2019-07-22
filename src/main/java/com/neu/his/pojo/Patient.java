package com.neu.his.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表patient对应的实体类Patient
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日11:53:24
 */
public class Patient implements Serializable {
    private Integer patientRecordID;
    private String patientName;
    private Integer patientGender;
    private Integer patientAge;
    private String patientIDNum;
    private String patientAddress;
    private String patientAgeType;
    private Date patientBirthday;

    public Integer getPatientRecordID() {
        return patientRecordID;
    }

    public void setPatientRecordID(Integer patientRecordID) {
        this.patientRecordID = patientRecordID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(Integer patientGender) {
        this.patientGender = patientGender;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientIDNum() {
        return patientIDNum;
    }

    public void setPatientIDNum(String patientIDNum) {
        this.patientIDNum = patientIDNum;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientAgeType() {
        return patientAgeType;
    }

    public void setPatientAgeType(String patientAgeType) {
        this.patientAgeType = patientAgeType;
    }

    public Date getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(Date patientBirthday) {
        this.patientBirthday = patientBirthday;
    }
}
