package com.neu.his.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChargeInfoDTO {
    private short chargeUserID;//收费人员ID
    private String chargeType;//收费方式
    private Double chargeWholePrice;//总价
    private Integer invoiceID;//发票ID
    private List<Integer> drugPreIDs;//处方明细表ID
}
