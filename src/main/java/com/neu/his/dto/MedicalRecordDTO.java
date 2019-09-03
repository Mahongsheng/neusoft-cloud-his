package com.neu.his.dto;

import lombok.Data;

import java.util.List;

@Data
public class MedicalRecordDTO {
    private Integer medicalRegisterId;//挂号号
    private String medicalRecordSaying;//主诉
    private String medicalRecordCurtIllness;//现病史
    private String medicalRecordTreat;//现病治疗情况
    private String medicalRecordPreIllness;//既往史
    private String medicalRecordAllergy;//过敏史
    private String medicalRecordCheck;//体格检查
    private String medicalRecordCheckAdvice;//检查建议
    private String medicalRecordWarn;//注意事项
    private List<ConfirmedDTO> medicalConfirms;//初步检查结果表
    private String medicalRecordState;//病历状态
}
