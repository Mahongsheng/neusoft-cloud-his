package com.neu.his.web;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.*;
import com.neu.his.serviceInterface.RegistrationManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationManagement registrationManagement;

    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    /**
     * 挂号方法
     *
     * @param registerDTO
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestBody RegisterDTO registerDTO) {
        logger.info("挂号");
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
        logger.info("退号");
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
        logger.info("收费");
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
        logger.info("挂号时根据病历号得到一些信息");
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
        logger.info("得到所有科室的名称");
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
        logger.info("根据科室名称得到所有医生姓名");
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
        logger.info("根据医生ID得到该医生初始挂号额和已挂号额");
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
        logger.info("根据病历号得到该患者的所有挂号信息");
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
        logger.info("根据病历号得到所有处方明细");
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
        logger.info("根据处方明细ID得到应收金额");
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
        logger.info("找到最大可用发票号");
        return registrationManagement.findAvailableInvoiceID();
    }

    /**
     * 得到该挂号级别对应的挂号金额
     *
     * @param registerLevelDTO
     */
    @PostMapping(value = "/getRegisterLevelMoney")
    @ResponseBody
    public JSONObject getRegisterLevelMoney(@RequestBody RegisterLevelDTO registerLevelDTO) {
        logger.info("得到该挂号级别对应的挂号金额");
        return registrationManagement.getRegisterLevelMoney(registerLevelDTO);
    }
}