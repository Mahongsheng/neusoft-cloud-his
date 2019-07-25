package com.neu.his.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neu.his.dao.*;
import com.neu.his.dto.*;
import com.neu.his.pojo.*;
import com.neu.his.serviceInterface.PatientManagement;
import com.neu.his.util.ReturnState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientManagementImpl implements PatientManagement {

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private RegistLevelMapper registLevelMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RegistrationRecordMapper registrationRecordMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Autowired
    private ConfirmedMapper confirmedMapper;

    @Autowired
    private DrugPrescriptionMapper drugPrescriptionMapper;

    @Autowired
    private DrugPrescriptionDetailMapper drugPrescriptionDetailMapper;

    /**
     * 患者挂号
     *
     * @param registerDTO
     * @return
     */
    @Override
    public JSONObject register(RegisterDTO registerDTO) {
        JSONObject returnJson;
        try {
            //插入数据库挂号信息表中
            RegistrationRecord newRecord = new RegistrationRecord();

            int departmentID;
            int doctorID;
            int registerLevel = 0;
            String registerState;

            //科室ID需要转换
            DepartmentExample departmentExample = new DepartmentExample();
            DepartmentExample.Criteria departmentCriteria = departmentExample.createCriteria();
            departmentCriteria.andDeptNameEqualTo(registerDTO.getDepartment());
            List<Department> departments = departmentMapper.selectByExample(departmentExample);
            departmentID = departments.get(0).getDeptId();

            //由医生姓名+科室名称转换为医生ID
            DoctorExample doctorExample = new DoctorExample();
            DoctorExample.Criteria doctorCriteria = doctorExample.createCriteria();
            doctorCriteria.andDoctorNameEqualTo(registerDTO.getDoctorName());
            doctorCriteria.andDeptIdEqualTo((short) departmentID);
            List<Doctor> doctors = doctorMapper.selectByExample(doctorExample);
            doctorID = doctors.get(0).getDoctorId();

            //挂号等级需要转换
            if (registerDTO.getRegisterLevel().equals("专家号")) {
                registerLevel = 1;
            } else if (registerDTO.getRegisterLevel().equals("普通号")) {
                registerLevel = 2;
            }
            //看诊状态需要自己添加
            registerState = "待诊";

            //将新的挂号信息进行存储
            newRecord.setRegistId(null);
            newRecord.setPatientRecordId(registerDTO.getMedicalRecordID());
            newRecord.setRegistChargeCategory(registerDTO.getChargeType());
            newRecord.setRegistLevel((byte) registerLevel);
            newRecord.setDoctorId((short) doctorID);
            newRecord.setRegistBook(registerDTO.getMedicalBook());
            newRecord.setRegistState(registerState);
            newRecord.setRegistTime(new Date());
            newRecord.setRegistUserId((short) registerDTO.getRegisterUserID());
            newRecord.setDeptId((short) departmentID);
            newRecord.setRegistNoon(registerDTO.getRegisterNoon());
            newRecord.setRegistDate(registerDTO.getRegisterDate());

            registrationRecordMapper.insert(newRecord);
//            addInvoice(registerDTO);
            //日后再去判断，挂号目前成功
//            addPatient(registerDTO);
            //Json转换出现一定问题
            ReturnState returnState = new ReturnState();
            returnState.setState(503);
            returnState.setDetail("挂号成功");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            ReturnState returnState = new ReturnState();
            returnState.setState(504);
            returnState.setDetail("挂号失败");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        }
    }

    @Override
    public JSONObject registerBack(RegisterBackDTO registerBackDTO) {
        JSONObject returnJson;
        try {
            //得到科室ID
            DepartmentExample departmentExample = new DepartmentExample();
            DepartmentExample.Criteria departmentCriteria = departmentExample.createCriteria();
            departmentCriteria.andDeptNameEqualTo(registerBackDTO.getDepartment());
            List<Department> departments = departmentMapper.selectByExample(departmentExample);
            int departmentID = departments.get(0).getDeptId();

            //得到该挂号信息
            RegistrationRecordExample registrationRecordExample = new RegistrationRecordExample();
            RegistrationRecordExample.Criteria registrationCriteria = registrationRecordExample.createCriteria();
            registrationCriteria.andPatientRecordIdEqualTo(registerBackDTO.getMedicalRecordID());
            registrationCriteria.andRegistDateEqualTo(registerBackDTO.getRegisterDate());
            registrationCriteria.andRegistNoonEqualTo(registerBackDTO.getRegisterNoon());
            registrationCriteria.andDeptIdEqualTo((short) departmentID);
            List<RegistrationRecord> registrationRecords = registrationRecordMapper.selectByExample(registrationRecordExample);

            int registerID = registrationRecords.get(0).getRegistId();
            registrationRecordMapper.updateStateByPrimaryKey(registerID);

            ReturnState returnState = new ReturnState();
            returnState.setState(507);
            returnState.setDetail("退号成功");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            ReturnState returnState = new ReturnState();
            returnState.setState(508);
            returnState.setDetail("退号失败");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        }
    }

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

    @Override
    public JSONObject makePrescription(DrugPrescriptionDTO drugPrescriptionDTO) {
        JSONObject returnJson;
        try {
            //转换对象类型并进行插入
            DrugPrescription drugPrescription = new DrugPrescription();
            drugPrescription.setDrugPreId(null);
            drugPrescription.setDoctorId(drugPrescriptionDTO.getDoctorId());
            drugPrescription.setDrugPreName(drugPrescriptionDTO.getDrugPreName());
            drugPrescription.setDrugPreState("未缴费");
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

    private void addPatient(RegisterDTO registerDTO) {

    }

    private void addInvoice(RegisterDTO registerDTO) {
    }
}
