package com.neu.his.vojo;

import lombok.Data;

@Data
public class DrugPreDetailInfo {
    private int drugPreDetailID;
    private String drugName;
    private double drugUnitPrice;
    private int amount;
    private String createTime;
    private String state;
}
