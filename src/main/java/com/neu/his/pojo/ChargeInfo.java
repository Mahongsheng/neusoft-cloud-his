package com.neu.his.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表charge_info对应的类ChargeInfo
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日13:23:23
 */
public class ChargeInfo implements Serializable {
    private Integer chargeInforID;
    private Integer registID;
    private Integer invoiceID;
    private Integer itemID;
    private Integer itemType;
    private String itemName;
    private double chargeInfoUnitPrice;
    private Integer chargeInfoAmount;
    private Integer deptID;
    private Date charge_refundTime;
    private Integer charge_refundUserID;
    private Date chargeBegIntegerime;
    private Integer chargeBeginUserID;
    private Integer chargeType;
    private double chargeWholePrice;

    public Integer getChargeInforID() {
        return chargeInforID;
    }

    public void setChargeInforID(Integer chargeInforID) {
        this.chargeInforID = chargeInforID;
    }

    public Integer getRegistID() {
        return registID;
    }

    public void setRegistID(Integer registID) {
        this.registID = registID;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getChargeInfoUnitPrice() {
        return chargeInfoUnitPrice;
    }

    public void setChargeInfoUnitPrice(double chargeInfoUnitPrice) {
        this.chargeInfoUnitPrice = chargeInfoUnitPrice;
    }

    public Integer getChargeInfoAmount() {
        return chargeInfoAmount;
    }

    public void setChargeInfoAmount(Integer chargeInfoAmount) {
        this.chargeInfoAmount = chargeInfoAmount;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Date getCharge_refundTime() {
        return charge_refundTime;
    }

    public void setCharge_refundTime(Date charge_refundTime) {
        this.charge_refundTime = charge_refundTime;
    }

    public Integer getCharge_refundUserID() {
        return charge_refundUserID;
    }

    public void setCharge_refundUserID(Integer charge_refundUserID) {
        this.charge_refundUserID = charge_refundUserID;
    }

    public Date getChargeBegIntegerime() {
        return chargeBegIntegerime;
    }

    public void setChargeBegIntegerime(Date chargeBegIntegerime) {
        this.chargeBegIntegerime = chargeBegIntegerime;
    }

    public Integer getChargeBeginUserID() {
        return chargeBeginUserID;
    }

    public void setChargeBeginUserID(Integer chargeBeginUserID) {
        this.chargeBeginUserID = chargeBeginUserID;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public double getChargeWholePrice() {
        return chargeWholePrice;
    }

    public void setChargeWholePrice(double chargeWholePrice) {
        this.chargeWholePrice = chargeWholePrice;
    }
}
