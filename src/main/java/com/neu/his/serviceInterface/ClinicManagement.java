package com.neu.his.serviceInterface;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.DoctorIDDTO;
import com.neu.his.dto.DrugPrescriptionDTO;
import com.neu.his.dto.MedicalRecordDTO;

import java.util.List;

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

    /**
     * 根据医生ID找到挂号到该医生的未诊断患者信息
     *
     * @return
     */
    List<JSONObject> getAllPatientNotDiagnose(DoctorIDDTO doctorIDDTO);

    /**
     * 根据医生ID找到挂号到该医生的已诊断患者信息
     *
     * @param doctorIDDTO
     * @return
     */
    List<JSONObject> getAllPatientDiagnose(DoctorIDDTO doctorIDDTO);

}
