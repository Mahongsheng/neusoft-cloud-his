package com.neu.his.serviceInterface;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.DrugPrescriptionDTO;
import com.neu.his.dto.MedicalRecordDTO;

public interface ClinicManagement {
    /**
     * 填写病历首页
     *
     * @param medicalRecordDTO
     * @return
     */
    JSONObject writeMedical(MedicalRecordDTO medicalRecordDTO);

    /**
     * 医生开立处方（开药）
     *
     * @param drugPrescriptionDTO
     * @return
     */
    JSONObject makePrescription(DrugPrescriptionDTO drugPrescriptionDTO);
}
