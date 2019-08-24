package com.neu.his.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neu.his.dao.*;
import com.neu.his.dto.ConfirmedDTO;
import com.neu.his.dto.DrugPrescriptionDTO;
import com.neu.his.dto.DrugPrescriptionDetailDTO;
import com.neu.his.dto.MedicalRecordDTO;
import com.neu.his.pojo.Confirmed;
import com.neu.his.pojo.DrugPrescription;
import com.neu.his.pojo.DrugPrescriptionDetail;
import com.neu.his.pojo.MedicalRecord;
import com.neu.his.serviceInterface.ClinicManagement;
import com.neu.his.util.ReturnState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ClinicManagementImpl implements ClinicManagement {

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Autowired
    private ConfirmedMapper confirmedMapper;

    @Autowired
    private DrugPrescriptionMapper drugPrescriptionMapper;

    @Autowired
    private DrugPrescriptionDetailMapper drugPrescriptionDetailMapper;

    /**
     * 填写病历首页
     *
     * @param medicalRecordDTO
     * @return
     */
    @Override
    public JSONObject writeMedical(MedicalRecordDTO medicalRecordDTO) {
        JSONObject returnJson;
        try {
            //转换对象类型并进行插入
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setMedicalRegistId(medicalRecordDTO.getMedicalRegisterId());
            medicalRecord.setMedicalRecordSaying(medicalRecordDTO.getMedicalRecordSaying());
            medicalRecord.setMedicalRecordCurtIllness(medicalRecordDTO.getMedicalRecordCurtIllness());
            medicalRecord.setMedicalRecordTreat(medicalRecordDTO.getMedicalRecordTreat());
            medicalRecord.setMedicalRecordPreIllness(medicalRecordDTO.getMedicalRecordPreIllness());
            medicalRecord.setMedicalRecordAllergy(medicalRecordDTO.getMedicalRecordAllergy());
            medicalRecord.setMedicalRecordCheck(medicalRecordDTO.getMedicalRecordCheck());
            medicalRecord.setMedicalRecordCheckAdvice(medicalRecordDTO.getMedicalRecordCheckAdvice());
            medicalRecord.setMedicalRecordWarn(medicalRecordDTO.getMedicalRecordWarn());
            medicalRecord.setMedicalRecordState(medicalRecordDTO.getMedicalRecordState());
            //插入
            medicalRecordMapper.insert(medicalRecord);

            for (ConfirmedDTO confirmedDTO : medicalRecordDTO.getMedicalConfirms()) {
                Confirmed confirmed = new Confirmed();
                confirmed.setConfirmedId(null);
                confirmed.setConfirmedCategory((byte) 1);
                confirmed.setDiseaseTime(confirmedDTO.getDiseaseTime());
                confirmed.setRegistId(medicalRecordDTO.getMedicalRegisterId());
                confirmed.setDiseaseIcd(confirmedDTO.getDiseaseICD());
                confirmedMapper.insert(confirmed);
            }
            ReturnState returnState = new ReturnState();
            returnState.setState(505);
            returnState.setDetail("病历首页填写成功");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;

        } catch (Exception e) {
            e.printStackTrace();
            ReturnState returnState = new ReturnState();
            returnState.setState(506);
            returnState.setDetail("病历首页填写失败");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        }
    }

    /**
     * 医生开立处方
     *
     * @param drugPrescriptionDTO
     * @return
     */
    @Override
    public JSONObject makePrescription(DrugPrescriptionDTO drugPrescriptionDTO) {
        JSONObject returnJson;
        try {
            //转换对象类型并进行插入
            DrugPrescription drugPrescription = new DrugPrescription();
            drugPrescription.setDrugPreId(null);
            drugPrescription.setDoctorId(drugPrescriptionDTO.getDoctorId());
            drugPrescription.setDrugPreName(drugPrescriptionDTO.getDrugPreName());
            drugPrescription.setDrugPreTime(new Date());
            drugPrescription.setMedicalRecordId(drugPrescriptionDTO.getMedicalRecordId());
            drugPrescription.setRegistId(drugPrescriptionDTO.getRegisterId());
            //插入
            drugPrescriptionMapper.insert(drugPrescription);
            int drugPreId = drugPrescriptionMapper.findMaxPreId();

            for (DrugPrescriptionDetailDTO drugPrescriptionDetailDTO : drugPrescriptionDTO.getDrugPrescriptionDetails()) {
                DrugPrescriptionDetail drugPrescriptionDetail = new DrugPrescriptionDetail();
                drugPrescriptionDetail.setDrugId(drugPrescriptionDetailDTO.getDrugId());
                drugPrescriptionDetail.setDrugPreDetailAmount(drugPrescriptionDetailDTO.getDrugPreDetailAmount());
                drugPrescriptionDetail.setDrugPreDetailFreq(drugPrescriptionDetailDTO.getDrugPreDetailFreq());
                drugPrescriptionDetail.setDrugPreDetailId(null);
                drugPrescriptionDetail.setDrugPreDetailNum(drugPrescriptionDetailDTO.getDrugPreDetailNum());
                drugPrescriptionDetail.setDrugPreDetailState("未缴费");
                drugPrescriptionDetail.setDrugPreId(drugPreId);
                drugPrescriptionDetail.setDrugPreDetailUsage(drugPrescriptionDetailDTO.getDrugPreDetailUsage());
                drugPrescriptionDetailMapper.insert(drugPrescriptionDetail);
            }
            ReturnState returnState = new ReturnState();
            returnState.setState(509);
            returnState.setDetail("开药成功");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;

        } catch (Exception e) {
            e.printStackTrace();
            ReturnState returnState = new ReturnState();
            returnState.setState(510);
            returnState.setDetail("开药失败");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        }
    }
}
