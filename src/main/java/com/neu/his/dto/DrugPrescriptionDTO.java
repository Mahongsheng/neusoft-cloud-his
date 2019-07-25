package com.neu.his.dto;

import lombok.Data;

import java.util.List;

@Data
public class DrugPrescriptionDTO {
    private Integer registerId;//挂号ID
    private Short doctorId;//医生ID
    private Integer medicalRecordId;//病历ID
    private String drugPreName;//处方名称
    private List<DrugPrescriptionDetailDTO> drugPrescriptionDetails;//处方明细表
}
