package com.neu.his.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表drug_prescription对应的类DrugPrescription
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日13:27:53
 */
public class DrugPrescription implements Serializable {
    private Integer drugPreID;
    private Integer registID;
    private Integer doctorID;
    private Integer medicalRecordID;
    private String drugPreName;
    private Date drugPreTime;
    private String drugPreState;

    public Integer getDrugPreID() {
        return drugPreID;
    }

    public void setDrugPreID(Integer drugPreID) {
        this.drugPreID = drugPreID;
    }

    public Integer getRegistID() {
        return registID;
    }

    public void setRegistID(Integer registID) {
        this.registID = registID;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public Integer getMedicalRecordID() {
        return medicalRecordID;
    }

    public void setMedicalRecordID(Integer medicalRecordID) {
        this.medicalRecordID = medicalRecordID;
    }

    public String getDrugPreName() {
        return drugPreName;
    }

    public void setDrugPreName(String drugPreName) {
        this.drugPreName = drugPreName;
    }

    public Date getDrugPreTime() {
        return drugPreTime;
    }

    public void setDrugPreTime(Date drugPreTime) {
        this.drugPreTime = drugPreTime;
    }

    public String getDrugPreState() {
        return drugPreState;
    }

    public void setDrugPreState(String drugPreState) {
        this.drugPreState = drugPreState;
    }
}
