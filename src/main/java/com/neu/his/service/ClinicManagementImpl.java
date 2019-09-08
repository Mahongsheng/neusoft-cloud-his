package com.neu.his.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.his.dao.*;
import com.neu.his.dto.*;
import com.neu.his.pojo.*;
import com.neu.his.serviceInterface.ClinicManagement;
import com.neu.his.util.ReturnState;
import com.neu.his.vojo.DiseaseBack;
import com.neu.his.vojo.DrugBack;
import com.neu.his.vojo.PatientInfo;
import com.neu.his.vojo.RegistrationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private RegistrationRecordMapper registrationRecordMapper;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DiseaseCategoryMapper diseaseCategoryMapper;

    @Autowired
    private DiseaseCatalogMapper diseaseCatalogMapper;

    @Autowired
    private DrugMapper drugMapper;

    @Autowired
    private ConstantMapper constantMapper;

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
                confirmed.setDiseaseId(confirmedDTO.getDiseaseID());
                confirmedMapper.insert(confirmed);
            }

            RegistrationRecord registrationRecord = new RegistrationRecord();
            registrationRecord.setRegistId(medicalRecordDTO.getMedicalRegisterId());
            registrationRecord.setRegistState("诊中");

            registrationRecordMapper.updateByPrimaryKeySelective(registrationRecord);

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
            RegistrationRecord registrationRecord = registrationRecordMapper.selectByPrimaryKey(drugPrescriptionDTO.getRegisterId());
            int medicalRecordID = registrationRecord.getPatientRecordId();
            //转换对象类型并进行插入
            DrugPrescription drugPrescription = new DrugPrescription();
            drugPrescription.setDrugPreId(null);
            drugPrescription.setDoctorId(drugPrescriptionDTO.getDoctorId());
            drugPrescription.setDrugPreName(drugPrescriptionDTO.getDrugPreName());
            drugPrescription.setDrugPreTime(new Date());
            drugPrescription.setMedicalRecordId(medicalRecordID);
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

    /**
     * 根据医生ID找到挂号到该医生的所有未诊断患者
     *
     * @param doctorIDDTO
     * @return
     */
    @Override
    public List<JSONObject> getAllPatientNotDiagnose(DoctorIDDTO doctorIDDTO) {
        List<JSONObject> returnJsons = new ArrayList<>();
        try {

            List<RegistrationRecord> registrationRecords = registrationRecordMapper.getAllPatientNotDiagnose(doctorIDDTO.getDoctorID());

            for (RegistrationRecord r : registrationRecords) {
                Patient patient = patientMapper.selectByPrimaryKey(r.getPatientRecordId());

                PatientInfo patientInfo = new PatientInfo();
                patientInfo.setMedicalRecordID(r.getPatientRecordId());
                patientInfo.setRegistrationID(r.getRegistId());
                patientInfo.setPatientName(patient.getPatientName());
                patientInfo.setAge(patient.getPatientAge());

                JSONObject returnJson = (JSONObject) JSON.toJSON(patientInfo);
                returnJsons.add(returnJson);
            }
            return returnJsons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据医生ID找到挂号到该医生的已诊断患者信息
     *
     * @param doctorIDDTO
     * @return
     */
    @Override
    public List<JSONObject> getAllPatientDiagnose(DoctorIDDTO doctorIDDTO) {
        List<JSONObject> returnJsons = new ArrayList<>();
        try {

            List<RegistrationRecord> registrationRecords = registrationRecordMapper.getAllPatientDiagnose(doctorIDDTO.getDoctorID());

            for (RegistrationRecord r : registrationRecords) {
                Patient patient = patientMapper.selectByPrimaryKey(r.getPatientRecordId());

                PatientInfo patientInfo = new PatientInfo();
                patientInfo.setMedicalRecordID(r.getPatientRecordId());
                patientInfo.setRegistrationID(r.getRegistId());
                patientInfo.setPatientName(patient.getPatientName());
                patientInfo.setAge(patient.getPatientAge());

                JSONObject returnJson = (JSONObject) JSON.toJSON(patientInfo);
                returnJsons.add(returnJson);
            }
            return returnJsons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据病历号得到该患者的一些信息
     *
     * @param registrationIDDTO
     * @return
     */
    @Override
    public JSONObject getSpecificPatientInfo(RegistrationIDDTO registrationIDDTO) {
        JSONObject returnJson;
        try {
            String gender = new String();
            RegistrationRecord registrationRecord = registrationRecordMapper.selectByPrimaryKey(registrationIDDTO.getRegistrationID());

            Patient patient = patientMapper.selectByPrimaryKey(registrationRecord.getPatientRecordId());

            PatientInfo patientInfo = new PatientInfo();
            patientInfo.setMedicalRecordID(patient.getPatientRecordId());
            patientInfo.setPatientName(patient.getPatientName());
            patientInfo.setAge(patient.getPatientAge());
            if (patient.getPatientGender() == 71) {
                gender = "男";
            } else if (patient.getPatientGender() == 72) {
                gender = "女";
            }
            patientInfo.setGender(gender);

            returnJson = (JSONObject) JSON.toJSON(patientInfo);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 模糊匹配疾病类型
     *
     * @param findDiseaseCategoryDTO
     * @return
     */
    @Override
    public List<JSONObject> findDiseaseCategory(FindDiseaseCategoryDTO findDiseaseCategoryDTO) {
        List<JSONObject> returnJsons = new ArrayList<>();
        try {
            String pattern = "%" + findDiseaseCategoryDTO.getPattern() + "%";
            List<DiseaseCategory> diseaseCategories = diseaseCategoryMapper.findDiseaseCategory(pattern);

            for (DiseaseCategory d : diseaseCategories) {
                JSONObject returnJson = (JSONObject) JSON.toJSON(d);
                returnJsons.add(returnJson);
            }
            return returnJsons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 模糊匹配疾病
     *
     * @param findDiseaseDTO
     * @return
     */
    @Override
    public List<JSONObject> findDisease(FindDiseaseDTO findDiseaseDTO) {
        List<JSONObject> returnJsons = new ArrayList<>();
        try {
            if (findDiseaseDTO.getDiseaseCategory().equals("")) {
                String pattern = "%" + findDiseaseDTO.getPattern() + "%";
                Page page = PageHelper.startPage(findDiseaseDTO.getPageNum(), findDiseaseDTO.getPageSize());
                List<DiseaseCatalog> diseaseCatalogs = diseaseCatalogMapper.findDiseaseCatalog(pattern);

                for (DiseaseCatalog d : diseaseCatalogs) {
                    DiseaseBack diseaseBack = new DiseaseBack();
                    diseaseBack.setDiseaseIcd(d.getDiseaseIcd());
                    diseaseBack.setDiseaseId(d.getDiseaseId());
                    diseaseBack.setDiseaseName(d.getDiseaseName());
                    diseaseBack.setWholePage(page.getPages());
                    JSONObject returnJson = (JSONObject) JSON.toJSON(diseaseBack);
                    returnJsons.add(returnJson);
                }
            } else {
                String pattern = "%" + findDiseaseDTO.getPattern() + "%";
                DiseaseCategoryExample diseaseCategoryExample = new DiseaseCategoryExample();
                DiseaseCategoryExample.Criteria criteria = diseaseCategoryExample.createCriteria();
                criteria.andDiseaseCateNameEqualTo(findDiseaseDTO.getDiseaseCategory());
                List<DiseaseCategory> diseaseCategories = diseaseCategoryMapper.selectByExample(diseaseCategoryExample);

                FindDiseaseDBDTO findDiseaseDBDTO = new FindDiseaseDBDTO();
                findDiseaseDBDTO.setDiseaseCategoryID(diseaseCategories.get(0).getDiseaseCateId());
                findDiseaseDBDTO.setPattern(pattern);

                Page page = PageHelper.startPage(findDiseaseDTO.getPageNum(), findDiseaseDTO.getPageSize());
                List<DiseaseCatalog> diseaseCatalogs = diseaseCatalogMapper.findDiseaseCatalogByCategory(findDiseaseDBDTO);

                for (DiseaseCatalog d : diseaseCatalogs) {
                    DiseaseBack diseaseBack = new DiseaseBack();
                    diseaseBack.setDiseaseIcd(d.getDiseaseIcd());
                    diseaseBack.setDiseaseId(d.getDiseaseId());
                    diseaseBack.setDiseaseName(d.getDiseaseName());
                    diseaseBack.setWholePage(page.getPages());
                    JSONObject returnJson = (JSONObject) JSON.toJSON(diseaseBack);
                    returnJsons.add(returnJson);
                }
            }
            return returnJsons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 模糊匹配药品
     *
     * @param findDrugDTO
     * @return
     */
    @Override
    public List<JSONObject> findDrug(FindDrugDTO findDrugDTO) {
        List<JSONObject> returnJsons = new ArrayList<>();
        try {
            String pattern = "%" + findDrugDTO.getPattern() + "%";
            Page page = PageHelper.startPage(findDrugDTO.getPageNum(), findDrugDTO.getPageSize());
            List<Drug> drugs = drugMapper.findDrug(pattern);

            for (Drug d : drugs) {
                String drugType;
                String drugDosage;

                Constant drugTypeConstant = constantMapper.selectByPrimaryKey(d.getDrugsTypeId());
                Constant drugDosageConstant = constantMapper.selectByPrimaryKey(d.getDrugsDosageId());

                drugType = drugTypeConstant.getConstantName();
                drugDosage = drugDosageConstant.getConstantName();

                DrugBack drugBack = new DrugBack();

                drugBack.setDrugId(d.getDrugId());
                drugBack.setDrugName(d.getDrugName());
                drugBack.setDrugSpecif(d.getDrugSpecif());
                drugBack.setDrugUnit(d.getDrugUnit());
                drugBack.setDrugManufacturer(d.getDrugManufacturer());
                drugBack.setDrugsDosage(drugDosage);
                drugBack.setDrugsType(drugType);
                drugBack.setDrugUnitPrice(d.getDrugUnitPrice());
                drugBack.setWholePage(page.getPages());

                JSONObject returnJson = (JSONObject) JSON.toJSON(drugBack);
                returnJsons.add(returnJson);
            }
            return returnJsons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 找特定的药品
     *
     * @param drugIDDTO
     * @return
     */
    @Override
    public JSONObject findSpecifDrug(DrugIDDTO drugIDDTO) {
        JSONObject returnJson;
        try {
            Drug d = drugMapper.selectByPrimaryKey(drugIDDTO.getDrugID());
            String drugType;
            String drugDosage;

            Constant drugTypeConstant = constantMapper.selectByPrimaryKey(d.getDrugsTypeId());
            Constant drugDosageConstant = constantMapper.selectByPrimaryKey(d.getDrugsDosageId());

            drugType = drugTypeConstant.getConstantName();
            drugDosage = drugDosageConstant.getConstantName();

            DrugBack drugBack = new DrugBack();

            drugBack.setDrugId(d.getDrugId());
            drugBack.setDrugName(d.getDrugName());
            drugBack.setDrugSpecif(d.getDrugSpecif());
            drugBack.setDrugUnit(d.getDrugUnit());
            drugBack.setDrugManufacturer(d.getDrugManufacturer());
            drugBack.setDrugsDosage(drugDosage);
            drugBack.setDrugsType(drugType);
            drugBack.setDrugUnitPrice(d.getDrugUnitPrice());
            returnJson = (JSONObject) JSON.toJSON(drugBack);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
