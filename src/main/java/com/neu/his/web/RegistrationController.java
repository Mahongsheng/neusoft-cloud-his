package com.neu.his.web;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.*;
import com.neu.his.serviceInterface.RegistrationManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationManagement registrationManagement;

    /**
     * 挂号方法
     *
     * @param registerDTO
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestBody RegisterDTO registerDTO) {
        return registrationManagement.register(registerDTO);
    }

    /**
     * 退号方法
     *
     * @param registerBackDTO
     * @return
     */
    @RequestMapping(value = "/registerBack", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject registerBack(@RequestBody RegisterBackDTO registerBackDTO) {
        return registrationManagement.registerBack(registerBackDTO);
    }

    /**
     * 收费方法
     *
     * @param chargeInfoDTO
     * @return
     */
    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject charge(@RequestBody ChargeInfoDTO chargeInfoDTO) {
        return registrationManagement.charge(chargeInfoDTO);
    }

    /**
     * 挂号时根据病历号得到一些信息
     *
     * @param medicalRecordID
     * @return
     */
    @RequestMapping(value = "/getRegistrationInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getRegistrationInfo(@RequestBody MedicalRecordIDDTO medicalRecordID) {
        return registrationManagement.getRegistrationInfo(medicalRecordID);
    }

    /**
     * 得到所有科室的名称
     *
     * @return
     */
    @RequestMapping(value = "/getAllDepartmentName", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getAllDepartmentName() {
        return registrationManagement.getAllDepartmentName();
    }

    /**
     * 根据科室名称得到所有医生姓名
     *
     * @return
     */
    @RequestMapping(value = "/getDoctorNameByDept", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getDoctorNameByDept(@RequestBody DepartmentNameDTO departmentNameDTO) {
        return registrationManagement.getDoctorNameByDept(departmentNameDTO);
    }

    /**
     * 根据医生ID得到该医生初始挂号额和已挂号额
     *
     * @param doctorIDDTO
     * @return
     */
    @RequestMapping(value = "/getRegistrationNum", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getRegistrationNum(@RequestBody DoctorIDDTO doctorIDDTO) {
        return registrationManagement.getRegistrationNum(doctorIDDTO);
    }

    /**
     * 根据病历号得到该患者的所有挂号信息
     *
     * @param medicalRecordIDDTO
     * @return
     */
    @RequestMapping(value = "/getRegistrationRecord", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getRegistrationRecord(@RequestBody MedicalRecordIDDTO medicalRecordIDDTO) {
        return registrationManagement.getRegistrationRecord(medicalRecordIDDTO);
    }

    /**
     * 根据病历号得到所有处方明细
     *
     * @param medicalRecordIDDTO
     * @return
     */
    @RequestMapping(value = "/getChargeInfo", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getChargeInfo(@RequestBody MedicalRecordIDDTO medicalRecordIDDTO) {
        return registrationManagement.getChargeInfo(medicalRecordIDDTO);
    }

    /**
     * 根据处方明细ID得到应收金额
     *
     * @param drugPreDetailIDDTO
     * @return
     */
    @RequestMapping(value = "/getDrugPreDetailInfo", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getDrugPreDetailInfo(@RequestBody DrugPreDetailIDDTO drugPreDetailIDDTO) {
        return registrationManagement.getDrugPreDetailInfo(drugPreDetailIDDTO);
    }

    /**
     * 找到最大可用发票号
     *
     * @return
     */
    @RequestMapping(value = "/findAvailableInvoiceID", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAvailableInvoiceID() {
        return registrationManagement.findAvailableInvoiceID();
    }
}
