package com.neu.his.dto;

import lombok.Data;

@Data
public class FindDrugDTO {
    private String pattern;
    private Integer pageNum;
    private Integer pageSize;
}
