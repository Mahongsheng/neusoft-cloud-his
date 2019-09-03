package com.neu.his.dto;

import lombok.Data;

@Data
public class FindDiseaseDTO {
    private String diseaseCategory;
    private String pattern;
    private Integer pageNum;
    private Integer pageSize;
}
