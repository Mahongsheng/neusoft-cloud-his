package com.neu.his.serviceInterface;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.DrugPrescriptionDTO;
import com.neu.his.dto.MedicalRecordDTO;
import com.neu.his.dto.RegisterBackDTO;
import com.neu.his.dto.RegisterDTO;

public interface PatientManagement {
    /**
     * 挂号方法
     *
     * @param registerDTO
     * @return
     */
    JSONObject register(RegisterDTO registerDTO);

    /**
     * 退号方法
     *
     * @param registerBackDTO
     * @return
     */
    JSONObject registerBack(RegisterBackDTO registerBackDTO);

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
