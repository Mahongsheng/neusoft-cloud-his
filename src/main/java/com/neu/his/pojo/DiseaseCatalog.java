package com.neu.his.pojo;

import java.io.Serializable;

/**
 * 数据库表disease_catalog对应的实体类diseaseCatalog
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日11:59:00
 */
public class DiseaseCatalog implements Serializable {
    private int diseaseID;
    private String diseaseCode;
    private String diseaseName;
    private String diseaseICD;
    private int diseaseCateID;

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseICD() {
        return diseaseICD;
    }

    public void setDiseaseICD(String diseaseICD) {
        this.diseaseICD = diseaseICD;
    }

    public int getDiseaseCateID() {
        return diseaseCateID;
    }

    public void setDiseaseCateID(int diseaseCateID) {
        this.diseaseCateID = diseaseCateID;
    }
}
