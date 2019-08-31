package com.neu.his.web;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.DoctorIDDTO;
import com.neu.his.dto.DrugPrescriptionDTO;
import com.neu.his.dto.MedicalRecordDTO;
import com.neu.his.serviceInterface.ClinicManagement;
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

    /**
     * 填写病历首页
     *
     * @param medicalRecordDTO
     * @return
     */
    @RequestMapping(value = "/writeMedical", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject writeMedical(@RequestBody MedicalRecordDTO medicalRecordDTO) {
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
        return clinicManagement.getAllPatientNotDiagnose(doctorIDDTO);
    }
}
