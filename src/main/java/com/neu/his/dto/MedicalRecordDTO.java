package com.neu.his.dto;

import lombok.Data;

@Data
public class MedicalRecordDTO {
    private Integer medicalRegistId;
    private String medicalRecordSaying;
    private String medicalRecordCurtIllness;
    private String medicalRecordTreat;
    private String medicalRecordPreIllness;
    private String medicalRecordAllergy;
    private String medicalRecordCheck;
    private String medicalRecordCheckAdvice;
    private String medicalRecordWarn;
    private String medicalRecordCheckResult;
    private String medicalRecordDealAdvice;
    private String medicalRecordState;
}
