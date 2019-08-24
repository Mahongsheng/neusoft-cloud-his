package com.neu.his.web;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.ChargeInfoDTO;
import com.neu.his.dto.RegisterBackDTO;
import com.neu.his.dto.RegisterDTO;
import com.neu.his.serviceInterface.RegistrationManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public JSONObject getRegistrationInfo(Integer medicalRecordID) {
        return registrationManagement.getRegistrationInfo(medicalRecordID);
    }
}
