package com.neu.his.pojo;

import java.io.Serializable;

/**
 * 数据库表drug_prescription_detail对应的类DrugPrescriptionDetail
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日13:33:43
 */
public class DrugPrescriptionDetail implements Serializable {
    private Integer drugPreDetailID;
    private Integer drugPreID;
    private Integer drugID;
    private String drugPreDetailUsage;
    private String drugPreDetailAmount;
    private String drugPreDetailFreq;
    private Integer drugPreDetailNum;
    private String drugPreDetailState;

    public Integer getDrugPreDetailID() {
        return drugPreDetailID;
    }

    public void setDrugPreDetailID(Integer drugPreDetailID) {
        this.drugPreDetailID = drugPreDetailID;
    }

    public Integer getDrugPreID() {
        return drugPreID;
    }

    public void setDrugPreID(Integer drugPreID) {
        this.drugPreID = drugPreID;
    }

    public Integer getDrugID() {
        return drugID;
    }

    public void setDrugID(Integer drugID) {
        this.drugID = drugID;
    }

    public String getDrugPreDetailUsage() {
        return drugPreDetailUsage;
    }

    public void setDrugPreDetailUsage(String drugPreDetailUsage) {
        this.drugPreDetailUsage = drugPreDetailUsage;
    }

    public String getDrugPreDetailAmount() {
        return drugPreDetailAmount;
    }

    public void setDrugPreDetailAmount(String drugPreDetailAmount) {
        this.drugPreDetailAmount = drugPreDetailAmount;
    }

    public String getDrugPreDetailFreq() {
        return drugPreDetailFreq;
    }

    public void setDrugPreDetailFreq(String drugPreDetailFreq) {
        this.drugPreDetailFreq = drugPreDetailFreq;
    }

    public Integer getDrugPreDetailNum() {
        return drugPreDetailNum;
    }

    public void setDrugPreDetailNum(Integer drugPreDetailNum) {
        this.drugPreDetailNum = drugPreDetailNum;
    }

    public String getDrugPreDetailState() {
        return drugPreDetailState;
    }

    public void setDrugPreDetailState(String drugPreDetailState) {
        this.drugPreDetailState = drugPreDetailState;
    }
}
