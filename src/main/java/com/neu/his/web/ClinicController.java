package com.neu.his.web;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.*;
import com.neu.his.serviceInterface.ClinicManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClinicController {

    @Autowired
    private ClinicManagement clinicManagement;

    Logger logger = LoggerFactory.getLogger(ClinicController.class);

    /**
     * 填写病历首页
     *
     * @param medicalRecordDTO
     * @return
     */
    @RequestMapping(value = "/writeMedical", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject writeMedical(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        logger.info("填写病历首页");
        return clinicManagement.writeMedical(medicalRecordDTO);
    }

    /**
     * 医生开立处方
     *
     * @param drugPrescriptionDTO
     * @return
     */
    @RequestMapping(value = "/makePrescription", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject makePrescription(@RequestBody DrugPrescriptionDTO drugPrescriptionDTO) {
        logger.info("医生开立处方");
        return clinicManagement.makePrescription(drugPrescriptionDTO);
    }

    /**
     * 根据医生ID找到挂号到该医生的未诊断患者信息
     *
     * @param doctorIDDTO
     * @return
     */
    @RequestMapping(value = "/getAllPatientNotDiagnose", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getAllPatientNotDiagnose(@RequestBody DoctorIDDTO doctorIDDTO) {
        logger.info("根据医生ID找到挂号到该医生的未诊断患者信息");
        return clinicManagement.getAllPatientNotDiagnose(doctorIDDTO);
    }

    /**
     * 根据医生ID找到挂号到该医生的已诊断患者信息
     *
     * @param doctorIDDTO
     * @return
     */
    @RequestMapping(value = "/getAllPatientDiagnose", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getAllPatientDiagnose(@RequestBody DoctorIDDTO doctorIDDTO) {
        logger.info("根据医生ID找到挂号到该医生的已诊断患者信息");
        return clinicManagement.getAllPatientDiagnose(doctorIDDTO);
    }

    /**
     * 根据病历号得到该患者的一些信息
     *
     * @param registrationIDDTO
     * @return
     */
    @RequestMapping(value = "/getSpecificPatientInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getSpecificPatientInfo(@RequestBody RegistrationIDDTO registrationIDDTO) {
        logger.info("根据病历号得到该患者的一些信息");
        return clinicManagement.getSpecificPatientInfo(registrationIDDTO);
    }

    /**
     * 模糊匹配疾病类型
     *
     * @param findDiseaseCategoryDTO
     * @return
     */
    @RequestMapping(value = "/findDiseaseCategory", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> findDiseaseCategory(@RequestBody FindDiseaseCategoryDTO findDiseaseCategoryDTO) {
        logger.info("模糊匹配疾病类型");
        return clinicManagement.findDiseaseCategory(findDiseaseCategoryDTO);
    }

    /**
     * 模糊匹配疾病
     *
     * @param findDiseaseDTO
     * @return
     */
    @RequestMapping(value = "/findDisease", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> findDisease(@RequestBody FindDiseaseDTO findDiseaseDTO) {
        logger.info("模糊匹配疾病");
        return clinicManagement.findDisease(findDiseaseDTO);
    }

    /**
     * 模糊匹配药品
     *
     * @param findDrugDTO
     * @return
     */
    @RequestMapping(value = "/findDrug", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> findDrug(@RequestBody FindDrugDTO findDrugDTO) {
        logger.info("模糊匹配药品");
        return clinicManagement.findDrug(findDrugDTO);
    }

    /**
     * 找特定的药品
     *
     * @param drugIDDTO
     * @return
     */
    @RequestMapping(value = "/findSpecifDrug", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findSpecifDrug(@RequestBody DrugIDDTO drugIDDTO) {
        logger.info("找特定的药品");
        return clinicManagement.findSpecifDrug(drugIDDTO);
    }
}
