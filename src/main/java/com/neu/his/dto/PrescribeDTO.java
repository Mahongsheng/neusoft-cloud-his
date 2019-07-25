package com.neu.his.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrescribeDTO {
    List<Integer> drugPreIDs;//待开药的处方明细表
}
