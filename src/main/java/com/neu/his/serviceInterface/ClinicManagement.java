package com.neu.his.serviceInterface;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.*;
import com.neu.his.pojo.DiseaseCategory;

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

    /**
     * 根据病历号得到该患者的一些信息
     *
     * @param registrationIDDTO
     * @return
     */
    JSONObject getSpecificPatientInfo(RegistrationIDDTO registrationIDDTO);

    /**
     * 模糊匹配疾病类型
     *
     * @param findDiseaseCategoryDTO
     * @return
     */
    List<JSONObject> findDiseaseCategory(FindDiseaseCategoryDTO findDiseaseCategoryDTO);

    /**
     * 模糊匹配疾病
     *
     * @param findDiseaseDTO
     * @return
     */
    List<JSONObject> findDisease(FindDiseaseDTO findDiseaseDTO);

}