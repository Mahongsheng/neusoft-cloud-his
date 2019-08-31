package com.neu.his.vojo;

import lombok.Data;

@Data
public class PrescribeInfo {
    private int drugPreDetailID;
    private String drugName;
    private double drugUnitPrice;
    private int amount;
    private String doctorName;
    private String preName;
    private String createTime;
}
