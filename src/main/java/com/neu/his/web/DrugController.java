package com.neu.his.web;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.*;
import com.neu.his.serviceInterface.DrugManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DrugController {
    @Autowired
    private DrugManagement drugManagement;

    /**
     * 医生发药
     *
     * @param prescribeDTO
     * @return
     */
    @RequestMapping(value = "/prescribe", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject prescribe(@RequestBody PrescribeDTO prescribeDTO) {
        return drugManagement.prescribe(prescribeDTO);
    }

    /**
     * 得到所有已缴费未开药的处方明细
     *
     * @param prescribeSearchDTO
     * @return
     */
    @RequestMapping(value = "/getPrescribeInfo", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getPrescribeInfo(@RequestBody PrescribeSearchDTO prescribeSearchDTO) {
        return drugManagement.getPrescribeInfo(prescribeSearchDTO);
    }
}
