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

import java.util.*;

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

    @Autowired
    private DrugMapper drugMapper;

    @Autowired
    private ChargeInfoMapper chargeInfoMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

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

    /**
     * 患者退号
     *
     * @param registerBackDTO
     * @return
     */
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

    /**
     * 填写病历首页
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

    /**
     * 收费
     *
     * @param chargeInfoDTO
     * @return
     */
    @Override
    public JSONObject charge(ChargeInfoDTO chargeInfoDTO) {
        JSONObject returnJson;
        try {
            //处方明细对象
            List<DrugPrescriptionDetail> drugPrescriptionDetails = new ArrayList<>();
            //药品处方明细属性
            int drug_pre_id;
            int drug_id;
            byte charge_type = 0;//已有
            //药品处方属性
            short doctor_id;
            Date drug_pre_time;
            int register_id = 0;
            //发票属性
            int invoice_id;//已有
            double invoice_price;//已有
            //收费信息属性
            short charge_refund_user_id;//已有
            String drug_name;
            double price;
            double unitPrice;
            short amount;
            short deptID;

            //转换收费类型
            if (chargeInfoDTO.getChargeType().equals("现金")) {
                charge_type = 51;
            } else if (chargeInfoDTO.getChargeType().equals("医保卡")) {
                charge_type = 52;
            } else if (chargeInfoDTO.getChargeType().equals("银行卡")) {
                charge_type = 53;
            } else if (chargeInfoDTO.getChargeType().equals("信用卡")) {
                charge_type = 54;
            } else if (chargeInfoDTO.getChargeType().equals("微信")) {
                charge_type = 55;
            } else if (chargeInfoDTO.getChargeType().equals("支付宝")) {
                charge_type = 56;
            } else if (chargeInfoDTO.getChargeType().equals("云闪付")) {
                charge_type = 57;
            } else if (chargeInfoDTO.getChargeType().equals("其他")) {
                charge_type = 58;
            }
            //得到发票号、发票总价、收费人员ID
            invoice_id = chargeInfoDTO.getInvoiceID();
            invoice_price = chargeInfoDTO.getChargeWholePrice();
            charge_refund_user_id = chargeInfoDTO.getChargeUserID();

            //首先将处方明细表中的处方明细状态处设置为已缴费，并取出所有处方明细属性封装为处方明细对象
            for (int drugPreDetailID : chargeInfoDTO.getDrugPreIDs()) {
                drugPrescriptionDetailMapper.changeStateChargedByPrimaryKey(drugPreDetailID);
                drugPrescriptionDetails.add(drugPrescriptionDetailMapper.selectByPrimaryKey(drugPreDetailID));
            }

            //将缴费信息插入表中
            for (DrugPrescriptionDetail drugPrescriptionDetail : drugPrescriptionDetails) {
                drug_pre_id = drugPrescriptionDetail.getDrugPreId();

                //找到该药品
                drug_id = drugPrescriptionDetail.getDrugId();
                Drug drug = drugMapper.selectByPrimaryKey(drug_id);
                drug_name = drug.getDrugName();
                unitPrice = drug.getDrugUnitPrice();
                amount = drugPrescriptionDetail.getDrugPreDetailNum();
                //计算出价格
                price = unitPrice * amount;

                //根据药品处方ID找到处方
                DrugPrescription drugPrescription = drugPrescriptionMapper.selectByPrimaryKey(drug_pre_id);
                //找到处方中的医生ID、开立时间、挂号ID
                doctor_id = drugPrescription.getDoctorId();
                drug_pre_time = drugPrescription.getDrugPreTime();
                register_id = drugPrescription.getRegistId();

                //找到执行科室ID
                Doctor doctor = doctorMapper.selectByPrimaryKey(doctor_id);
                deptID = doctor.getDeptId();

                //插入到消费信息表中
                ChargeInfo chargeInfo = new ChargeInfo();
                chargeInfo.setRegistId(register_id);
                chargeInfo.setInvoiceId(invoice_id);
                chargeInfo.setItemId((short) drug_id);
                chargeInfo.setItemType((byte) 2);
                chargeInfo.setItemName(drug_name);
                chargeInfo.setChargeInfoUnitPrice(unitPrice);
                chargeInfo.setChargeInfoAmount(amount);
                chargeInfo.setDeptId(deptID);
                chargeInfo.setChargeRefundTime(new Date());
                chargeInfo.setChargeRefundUserId(charge_refund_user_id);
                chargeInfo.setChargeBeginTime(drug_pre_time);
                chargeInfo.setChargeBeginUserid(doctor_id);
                chargeInfo.setChargeType(charge_type);
                chargeInfo.setChargeWholePrice(price);
                chargeInfoMapper.insert(chargeInfo);
            }
            //插入到发票表中
            Invoice invoice = new Invoice();
            invoice.setInvoicePrice((float) invoice_price);
            invoice.setInvoiceState((byte) 1);
            invoice.setInvoiceOperaTime(new Date());
            invoice.setChargeUserId(charge_refund_user_id);
            invoice.setRegistId(register_id);
            invoice.setChargeType(charge_type);
            invoiceMapper.insert(invoice);
            //包装返回Json
            ReturnState returnState = new ReturnState();
            returnState.setState(511);
            returnState.setDetail("缴费成功");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            //包装返回Json
            ReturnState returnState = new ReturnState();
            returnState.setState(512);
            returnState.setDetail("缴费失败");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        }
    }

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


    private void addPatient(RegisterDTO registerDTO) {

    }

    private void addInvoice(RegisterDTO registerDTO) {
    }
}
