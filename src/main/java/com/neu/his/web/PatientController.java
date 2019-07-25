package com.neu.his.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.*;
import com.neu.his.serviceInterface.PatientManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientController {
    @Autowired
    private PatientManagement patientManagement;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestBody RegisterDTO registerDTO) {
        return patientManagement.register(registerDTO);
    }

    @RequestMapping(value = "/registerBack", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject registerBack(@RequestBody RegisterBackDTO registerBackDTO) {
        return patientManagement.registerBack(registerBackDTO);
    }

    @RequestMapping(value = "/writeMedical", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject writeMedical(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        return patientManagement.writeMedical(medicalRecordDTO);
    }

    @RequestMapping(value = "/makePrescription", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject makePrescription(@RequestBody DrugPrescriptionDTO drugPrescriptionDTO) {
        return patientManagement.makePrescription(drugPrescriptionDTO);
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject makePrescription(@RequestBody ChargeInfoDTO chargeInfoDTO) {
        return patientManagement.charge(chargeInfoDTO);
    }

    @RequestMapping(value = "/prescribe", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject prescribe(@RequestBody PrescribeDTO prescribeDTO) {
        return patientManagement.prescribe(prescribeDTO);
    }
}
