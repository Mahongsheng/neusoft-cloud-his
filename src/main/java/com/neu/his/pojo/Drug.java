package com.neu.his.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表drug对应的实体类Drug
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日11:55:25
 */
public class Drug implements Serializable {
    private Integer drugID;
    private String drugCode;
    private String drugName;
    private String drugSpecif;
    private String drugUnit;
    private String drugManufacturer;
    private Integer drugsDosageID;
    private Integer drugsTypeID;
    private float drugUnitPrice;
    private String drugMemonicCode;
    private Date drugCrtTime;

    public Integer getDrugID() {
        return drugID;
    }

    public void setDrugID(Integer drugID) {
        this.drugID = drugID;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugSpecif() {
        return drugSpecif;
    }

    public void setDrugSpecif(String drugSpecif) {
        this.drugSpecif = drugSpecif;
    }

    public String getDrugUnit() {
        return drugUnit;
    }

    public void setDrugUnit(String drugUnit) {
        this.drugUnit = drugUnit;
    }

    public String getDrugManufacturer() {
        return drugManufacturer;
    }

    public void setDrugManufacturer(String drugManufacturer) {
        this.drugManufacturer = drugManufacturer;
    }

    public Integer getDrugsDosageID() {
        return drugsDosageID;
    }

    public void setDrugsDosageID(Integer drugsDosageID) {
        this.drugsDosageID = drugsDosageID;
    }

    public Integer getDrugsTypeID() {
        return drugsTypeID;
    }

    public void setDrugsTypeID(Integer drugsTypeID) {
        this.drugsTypeID = drugsTypeID;
    }

    public float getDrugUnitPrice() {
        return drugUnitPrice;
    }

    public void setDrugUnitPrice(float drugUnitPrice) {
        this.drugUnitPrice = drugUnitPrice;
    }

    public String getDrugMemonicCode() {
        return drugMemonicCode;
    }

    public void setDrugMemonicCode(String drugMemonicCode) {
        this.drugMemonicCode = drugMemonicCode;
    }

    public Date getDrugCrtTime() {
        return drugCrtTime;
    }

    public void setDrugCrtTime(Date drugCrtTime) {
        this.drugCrtTime = drugCrtTime;
    }
}
