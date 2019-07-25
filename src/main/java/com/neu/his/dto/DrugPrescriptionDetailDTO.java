package com.neu.his.dto;

import lombok.Data;

@Data
public class DrugPrescriptionDetailDTO {
    private Short drugId;//药品ID
    private String drugPreDetailUsage;//药品用法
    private String drugPreDetailAmount;//药品用量
    private String drugPreDetailFreq;//药品频次
    private Byte drugPreDetailNum;//药品数量
}
