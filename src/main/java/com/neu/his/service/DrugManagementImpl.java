package com.neu.his.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neu.his.dao.*;
import com.neu.his.dto.*;
import com.neu.his.serviceInterface.DrugManagement;
import com.neu.his.util.ReturnState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugManagementImpl implements DrugManagement {

    @Autowired
    private DrugPrescriptionDetailMapper drugPrescriptionDetailMapper;

    /**
     * 医生发药
     *
     * @param prescribeDTO
     * @return
     */
    @Override
    public JSONObject prescribe(PrescribeDTO prescribeDTO) {
        JSONObject returnJson;
        try {
            for (int drugPreDetailID : prescribeDTO.getDrugPreIDs()) {
                drugPrescriptionDetailMapper.changeStatePrescribedByPrimaryKey(drugPreDetailID);
            }
            //包装返回Json
            ReturnState returnState = new ReturnState();
            returnState.setState(513);
            returnState.setDetail("开药成功");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            //包装返回Json
            ReturnState returnState = new ReturnState();
            returnState.setState(514);
            returnState.setDetail("开药失败");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        }
    }
}
