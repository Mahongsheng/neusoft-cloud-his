package com.neu.his.web;

import com.neu.his.dto.RegisterDTO;
import com.neu.his.serviceInterface.PatientManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
    @Autowired
    private PatientManagement patientManagement;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public boolean register(@RequestBody RegisterDTO registerDTO) {
        return patientManagement.register(registerDTO);
    }
}
