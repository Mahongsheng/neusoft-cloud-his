package com.neu.his.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neu.his.dao.*;
import com.neu.his.dto.*;
import com.neu.his.pojo.*;
import com.neu.his.serviceInterface.DrugManagement;
import com.neu.his.util.ReturnState;
import com.neu.his.vojo.DrugPreDetailInfo;
import com.neu.his.vojo.PrescribeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DrugManagementImpl implements DrugManagement {

    @Autowired
    private DrugPrescriptionDetailMapper drugPrescriptionDetailMapper;

    @Autowired
    private DrugPrescriptionMapper drugPrescriptionMapper;

    @Autowired
    private DrugMapper drugMapper;

    @Autowired
    private DoctorMapper doctorMapper;

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

    /**
     * 得到所有的待发药处方明细
     *
     * @param prescribeSearchDTO
     * @return
     */
    @Override
    public List<JSONObject> getPrescribeInfo(PrescribeSearchDTO prescribeSearchDTO) {
        List<JSONObject> returnJsons = new ArrayList<>();
        try {

            DrugPrescriptionExample drugPrescriptionExample = new DrugPrescriptionExample();
            DrugPrescriptionExample.Criteria drugPrescriptionExampleCriteria = drugPrescriptionExample.createCriteria();
            drugPrescriptionExampleCriteria.andMedicalRecordIdEqualTo(prescribeSearchDTO.getMedicalRecordID());

            if (!prescribeSearchDTO.getDrugPreTime().equals("")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String timeSearch = prescribeSearchDTO.getDrugPreTime();
                Date dateSearch = simpleDateFormat.parse(timeSearch);

                Date begin;
                Date end;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateSearch);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.HOUR_OF_DAY, 0);

                begin = calendar.getTime();
                calendar.set(Calendar.HOUR_OF_DAY, 24);
                end = calendar.getTime();
                drugPrescriptionExampleCriteria.andDrugPreTimeBetween(begin, end);
            }

            List<DrugPrescription> drugPrescriptions = drugPrescriptionMapper.selectByExample(drugPrescriptionExample);

            for (DrugPrescription drugPre : drugPrescriptions) {
                DrugPrescriptionDetailExample drugPrescriptionDetailExample = new DrugPrescriptionDetailExample();
                DrugPrescriptionDetailExample.Criteria drugPrescriptionDetailExampleCriteria = drugPrescriptionDetailExample.createCriteria();
                drugPrescriptionDetailExampleCriteria.andDrugPreIdEqualTo(drugPre.getDrugPreId());
                drugPrescriptionDetailExampleCriteria.andDrugPreDetailStateEqualTo("已缴费");

                Doctor doctor = doctorMapper.selectByPrimaryKey(drugPre.getDoctorId());
                List<DrugPrescriptionDetail> findDrugPreDetails = drugPrescriptionDetailMapper.selectByExample(drugPrescriptionDetailExample);

                for (DrugPrescriptionDetail drugPreDetail : findDrugPreDetails) {
                    PrescribeInfo prescribeInfo = new PrescribeInfo();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Drug drug = drugMapper.selectByPrimaryKey(drugPreDetail.getDrugId());

                    prescribeInfo.setDrugPreDetailID(drugPreDetail.getDrugPreDetailId());
                    prescribeInfo.setAmount(drugPreDetail.getDrugPreDetailNum());
                    prescribeInfo.setCreateTime(simpleDateFormat.format(drugPre.getDrugPreTime()));
                    prescribeInfo.setDrugName(drug.getDrugName());
                    prescribeInfo.setDrugUnitPrice(drug.getDrugUnitPrice());
                    prescribeInfo.setDoctorName(doctor.getDoctorName());
                    prescribeInfo.setPreName(drugPre.getDrugPreName());

                    JSONObject jsonObject = (JSONObject) JSON.toJSON(prescribeInfo);
                    returnJsons.add(jsonObject);
                }
            }
            return returnJsons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
