package com.neu.his.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.neu.his.dao.*;
import com.neu.his.dto.*;
import com.neu.his.pojo.*;
import com.neu.his.serviceInterface.RegistrationManagement;
import com.neu.his.util.ReturnState;
import com.neu.his.vojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 挂号收费部分实现层
 */
@Service
public class RegistrationManagementImpl implements RegistrationManagement {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private RegistrationRecordMapper registrationRecordMapper;

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

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private RegistLevelMapper registLevelMapper;

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
            int maxMedicalID = patientMapper.findMaxID();
            //若为新病人，则插入病人数据库表。否则只做病人信息的修改
            if (registerDTO.getMedicalRecordID() == (maxMedicalID + 1)) {
                Patient patient = new Patient();
                patient.setPatientRecordId(registerDTO.getMedicalRecordID());
                patient.setPatientName(registerDTO.getName());

                byte gender;
                if (registerDTO.getGender().equals("男")) {
                    gender = 71;
                } else {
                    gender = 72;
                }

                patient.setPatientGender(gender);
                patient.setPatientIdNum(registerDTO.getNumID());
                patient.setPatientAge(registerDTO.getAge());
                patient.setPatientAgeType(registerDTO.getAgeType());
                patient.setPatientAddress(registerDTO.getAddress());
                patient.setPatientBirthday(registerDTO.getBirthday());
                patientMapper.insert(patient);
            } else {
                PatientExample patientExample = new PatientExample();
                PatientExample.Criteria criteria = patientExample.createCriteria();
                criteria.andPatientRecordIdEqualTo(registerDTO.getMedicalRecordID());

                Patient patient = new Patient();
                patient.setPatientName(registerDTO.getName());
                patient.setPatientAge(registerDTO.getAge());
                patient.setPatientAgeType(registerDTO.getAgeType());
                patient.setPatientAddress(registerDTO.getAddress());
                patientMapper.updateByExampleSelective(patient, patientExample);
            }
            //插入数据库挂号信息表中
            RegistrationRecord newRecord = new RegistrationRecord();

            int departmentID;
            int doctorID;
            int registerLevel = 0;
            String registerState;
            boolean ifNeedMedicalBook;

            //科室ID需要转换
            DepartmentExample departmentExample = new DepartmentExample();
            DepartmentExample.Criteria departmentCriteria = departmentExample.createCriteria();
            departmentCriteria.andDeptNameEqualTo(registerDTO.getDepartment());
            List<Department> departments = departmentMapper.selectByExample(departmentExample);
            departmentID = departments.get(0).getDeptId();

            doctorID = registerDTO.getDoctorID();

            //挂号等级需要转换
            if (registerDTO.getRegisterLevel().equals("专家号")) {
                registerLevel = 1;
            } else if (registerDTO.getRegisterLevel().equals("普通号")) {
                registerLevel = 2;
            }

            if (registerDTO.getMedicalBook().equals("是")) {
                ifNeedMedicalBook = true;
            } else {
                ifNeedMedicalBook = false;
            }

            //看诊状态需要自己添加
            registerState = "待诊";

            //将新的挂号信息进行存储
            newRecord.setRegistId(null);
            newRecord.setPatientRecordId(registerDTO.getMedicalRecordID());
            newRecord.setRegistChargeCategory(registerDTO.getChargeType());
            newRecord.setRegistLevel((byte) registerLevel);
            newRecord.setDoctorId((short) doctorID);
            newRecord.setRegistBook(ifNeedMedicalBook);
            newRecord.setRegistState(registerState);
            newRecord.setRegistTime(new Date());
            newRecord.setRegistUserId((short) registerDTO.getRegisterUserID());
            newRecord.setDeptId((short) departmentID);
            newRecord.setRegistNoon(registerDTO.getRegisterNoon());
            newRecord.setRegistDate(registerDTO.getRegisterDate());

            registrationRecordMapper.insert(newRecord);

            int registerID = registrationRecordMapper.findMaxID();

            addChargeInfoAndInvoice(registerDTO, registerID);

            //返回挂号成功信息
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
     * 添加收费信息以及发票信息
     *
     * @param registerDTO
     * @param registerID
     */
    private void addChargeInfoAndInvoice(RegisterDTO registerDTO, int registerID) {
        //收费信息属性
        short charge_refund_user_id = registerDTO.getRegisterUserID();//已有
        String item_name = "挂号费";
        double wholeMoney = registerDTO.getMoney();
        double price = registerDTO.getMoney();
        double unitPrice = registerDTO.getMoney();
        short amount = 1;
        short deptID;
        int invoice_id = registerDTO.getInvoiceID();//已有
        int itemID = 1;
        byte itemType = 1;
        byte charge_type = 51;

        //转换收费类型
        if (registerDTO.getChargeType().equals("现金")) {
            charge_type = 51;
        } else if (registerDTO.getChargeType().equals("医保卡")) {
            charge_type = 52;
        } else if (registerDTO.getChargeType().equals("银行卡")) {
            charge_type = 53;
        } else if (registerDTO.getChargeType().equals("信用卡")) {
            charge_type = 54;
        } else if (registerDTO.getChargeType().equals("微信")) {
            charge_type = 55;
        } else if (registerDTO.getChargeType().equals("支付宝")) {
            charge_type = 56;
        } else if (registerDTO.getChargeType().equals("云闪付")) {
            charge_type = 57;
        } else if (registerDTO.getChargeType().equals("其他")) {
            charge_type = 58;
        }

        DepartmentExample departmentExample = new DepartmentExample();
        DepartmentExample.Criteria criteria = departmentExample.createCriteria();
        criteria.andDeptNameEqualTo(registerDTO.getDepartment());

        List<Department> departments = departmentMapper.selectByExample(departmentExample);

        deptID = departments.get(0).getDeptId();

        ChargeInfo chargeInfo = new ChargeInfo();

        chargeInfo.setRegistId(registerID);
        chargeInfo.setInvoiceId(invoice_id);
        chargeInfo.setItemId((short) itemID);
        chargeInfo.setItemType(itemType);
        chargeInfo.setItemName(item_name);
        chargeInfo.setChargeInfoUnitPrice(unitPrice);
        chargeInfo.setChargeInfoAmount(amount);
        chargeInfo.setDeptId(deptID);
        chargeInfo.setChargeRefundTime(new Date());
        chargeInfo.setChargeRefundUserId(charge_refund_user_id);
        chargeInfo.setChargeBeginTime(new Date());
        chargeInfo.setChargeBeginUserid(charge_refund_user_id);
        chargeInfo.setChargeType(charge_type);
        chargeInfo.setChargeWholePrice(price);
        chargeInfoMapper.insert(chargeInfo);


        Invoice invoice = new Invoice();
        invoice.setInvoicePrice((float) wholeMoney);
        invoice.setInvoiceState((byte) 1);
        invoice.setInvoiceOperaTime(new Date());
        invoice.setChargeUserId(charge_refund_user_id);
        invoice.setRegistId(registerID);
        invoice.setChargeType(charge_type);
        invoice.setInvoiceSettleState((byte) 0);
        invoiceMapper.insert(invoice);
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
            registrationRecordMapper.updateStateByPrimaryKey(registerBackDTO.getRegistrationID());

            //收费表加一条冲红
            ChargeInfoExample chargeInfoExample = new ChargeInfoExample();
            ChargeInfoExample.Criteria criteria = chargeInfoExample.createCriteria();
            criteria.andRegistIdEqualTo(registerBackDTO.getRegistrationID());
            criteria.andItemNameEqualTo("挂号费");


            ChargeInfo chargeInfo = chargeInfoMapper.selectByExample(chargeInfoExample).get(0);

            int beforeInvoiceID = chargeInfo.getInvoiceId();
            int afterInvoiceID = invoiceMapper.findMaxID() + 1;

            chargeInfo.setChargeInforId(null);
            chargeInfo.setInvoiceId(afterInvoiceID);
            chargeInfo.setChargeBeginTime(new Date());
            chargeInfo.setChargeRefundTime(new Date());
            chargeInfo.setChargeInfoUnitPrice(-chargeInfo.getChargeInfoUnitPrice());
            chargeInfo.setChargeWholePrice(-chargeInfo.getChargeWholePrice());

            chargeInfoMapper.insert(chargeInfo);


            //发票表加一条冲红
            Invoice invoice = invoiceMapper.selectByPrimaryKey(beforeInvoiceID);
            invoice.setInvoiceRedId(afterInvoiceID);
            invoiceMapper.updateByPrimaryKey(invoice);

            invoice.setInvoiceId(null);
            invoice.setInvoiceOperaTime(new Date());
            invoice.setInvoicePrice(-invoice.getInvoicePrice());
            invoice.setInvoiceRedId(null);
            invoiceMapper.insert(invoice);

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

    /**
     * 挂号时根据病历号得到一些信息
     *
     * @param medicalRecordIDDTO
     * @return
     */
    @Override
    public JSONObject getRegistrationInfo(MedicalRecordIDDTO medicalRecordIDDTO) {
        JSONObject returnJson;
        try {
            if (medicalRecordIDDTO.getMedicalRecordID() == null) {
                int maxMedicalRecordID = patientMapper.findMaxID() + 1;
                RegistrationInfo registrationInfo = new RegistrationInfo();
                registrationInfo.setMedicalRecordID(maxMedicalRecordID);
                returnJson = (JSONObject) JSON.toJSON(registrationInfo);
                return returnJson;
            } else {
                String gender = new String();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Patient patient = patientMapper.selectByPrimaryKey(medicalRecordIDDTO.getMedicalRecordID());
                RegistrationInfo registrationInfo = new RegistrationInfo();
                registrationInfo.setMedicalRecordID(medicalRecordIDDTO.getMedicalRecordID());
                registrationInfo.setPatientName(patient.getPatientName());
                if (patient.getPatientGender() == 71) {
                    gender = "男";
                } else if (patient.getPatientGender() == 72) {
                    gender = "女";
                }
                registrationInfo.setGender(gender);
                registrationInfo.setAge(patient.getPatientAge());
                registrationInfo.setBirthday(simpleDateFormat.format(patient.getPatientBirthday()));
                registrationInfo.setNumID(patient.getPatientIdNum());
                registrationInfo.setAddress(patient.getPatientAddress());
                returnJson = (JSONObject) JSON.toJSON(registrationInfo);
                return returnJson;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ReturnState returnState = new ReturnState();
            returnState.setState(515);
            returnState.setDetail("病人信息获取失败");
            returnJson = (JSONObject) JSON.toJSON(returnState);
            return returnJson;
        }
    }

    /**
     * 得到所有科室的名称
     *
     * @return
     */
    @Override
    public List<JSONObject> getAllDepartmentName() {
        List<JSONObject> jsonObjectList = new ArrayList<>();
        try {
            DepartmentExample departmentExample = new DepartmentExample();
            List<Department> departments = departmentMapper.selectByExample(departmentExample);
            for (Department d : departments) {
                DepartmentName departmentName = new DepartmentName();
                departmentName.setDepartmentName(d.getDeptName());
                JSONObject jsonObject = (JSONObject) JSON.toJSON(departmentName);
                jsonObjectList.add(jsonObject);
            }
            return jsonObjectList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据科室名称得到所有医生姓名
     *
     * @param departmentNameDTO
     * @return
     */
    @Override
    public List<JSONObject> getDoctorNameByDept(DepartmentNameDTO departmentNameDTO) {
        List<JSONObject> jsonObjectList = new ArrayList<>();
        try {
            DepartmentExample departmentExample = new DepartmentExample();
            DepartmentExample.Criteria deptCriteria = departmentExample.createCriteria();
            deptCriteria.andDeptNameEqualTo(departmentNameDTO.getDepartmentName());

            List<Department> departments = departmentMapper.selectByExample(departmentExample);

            byte registerLevel;
            if (departmentNameDTO.getDoctorLevel().equals("普通号")) {
                registerLevel = 2;
            } else {
                registerLevel = 1;
            }

            DoctorExample doctorExample = new DoctorExample();
            DoctorExample.Criteria docCriteria = doctorExample.createCriteria();
            docCriteria.andDeptIdEqualTo(departments.get(0).getDeptId());
            docCriteria.andDoctorRegistLevelEqualTo(registerLevel);

            List<Doctor> doctors = doctorMapper.selectByExample(doctorExample);

            for (Doctor d : doctors) {
                DoctorName doctorName = new DoctorName();
                doctorName.setDoctorName(d.getDoctorName());
                doctorName.setDoctorID(d.getDoctorId());
                JSONObject jsonObject = (JSONObject) JSON.toJSON(doctorName);
                jsonObjectList.add(jsonObject);
            }
            return jsonObjectList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据医生ID得到该医生的初始挂号额和已挂号额
     *
     * @param doctorIDDTO
     * @return
     */
    @Override
    public JSONObject getRegistrationNum(DoctorIDDTO doctorIDDTO) {
        JSONObject returnJson;
        try {
            DoctorRegistrationNum doctorRegistrationNum = new DoctorRegistrationNum();
            Doctor doctor = doctorMapper.selectByPrimaryKey((short) doctorIDDTO.getDoctorID());

            RegistLevel registLevel = registLevelMapper.selectByPrimaryKey(doctor.getDoctorRegistLevel());

            Date todayBegin;
            Date todayEnd;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            todayBegin = calendar.getTime();
            calendar.set(Calendar.HOUR_OF_DAY, 24);
            todayEnd = calendar.getTime();

            RegistrationRecordExample registrationRecordExample = new RegistrationRecordExample();
            RegistrationRecordExample.Criteria criteria = registrationRecordExample.createCriteria();
            criteria.andDoctorIdEqualTo((short) doctorIDDTO.getDoctorID());
            criteria.andRegistStateEqualTo("待诊");
            criteria.andRegistTimeGreaterThanOrEqualTo(todayBegin);
            criteria.andRegistTimeLessThan(todayEnd);

            int usedRegistrationNum = (int) registrationRecordMapper.countByExample(registrationRecordExample);

            doctorRegistrationNum.setDoctorRegistrationNum(registLevel.getRegistLevelLimit());
            doctorRegistrationNum.setDoctorUsedRegistrationNum(usedRegistrationNum);

            returnJson = (JSONObject) JSON.toJSON(doctorRegistrationNum);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据病历号获得该患者挂号信息
     *
     * @param medicalRecordIDDTO
     * @return
     */
    @Override
    public List<JSONObject> getRegistrationRecord(MedicalRecordIDDTO medicalRecordIDDTO) {
        List<JSONObject> returnJsons = new ArrayList<>();
        try {
            RegistrationRecordExample registrationRecordExample = new RegistrationRecordExample();
            RegistrationRecordExample.Criteria criteria = registrationRecordExample.createCriteria();
            criteria.andPatientRecordIdEqualTo(medicalRecordIDDTO.getMedicalRecordID());

            Page page = PageHelper.startPage(medicalRecordIDDTO.getPageNum(), medicalRecordIDDTO.getPageSize());
            List<RegistrationRecord> registrationRecords = registrationRecordMapper.selectByExample(registrationRecordExample);

            for (RegistrationRecord r : registrationRecords) {
                PatientRegistrationRecord patientRegistrationRecord = new PatientRegistrationRecord();

                Department department = departmentMapper.selectByPrimaryKey(r.getDeptId());

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                patientRegistrationRecord.setRegistrationID(r.getRegistId());
                patientRegistrationRecord.setDepartment(department.getDeptName());
                patientRegistrationRecord.setRegistrationDate(simpleDateFormat.format(r.getRegistDate()));
                patientRegistrationRecord.setRegistrationNoon(r.getRegistNoon());
                patientRegistrationRecord.setRegistrationState(r.getRegistState());
                patientRegistrationRecord.setWholePage(page.getPages());

                JSONObject jsonObject = (JSONObject) JSON.toJSON(patientRegistrationRecord);

                returnJsons.add(jsonObject);
            }
            return returnJsons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据病历号得到所有处方明细
     *
     * @param medicalRecordIDDTO
     * @return
     */
    @Override
    public List<JSONObject> getChargeInfo(MedicalRecordIDDTO medicalRecordIDDTO) {
        List<JSONObject> returnJsons = new ArrayList<>();
        try {
            DrugPrescriptionExample drugPrescriptionExample = new DrugPrescriptionExample();
            DrugPrescriptionExample.Criteria drugPrescriptionExampleCriteria = drugPrescriptionExample.createCriteria();
            drugPrescriptionExampleCriteria.andMedicalRecordIdEqualTo(medicalRecordIDDTO.getMedicalRecordID());

            List<DrugPrescription> drugPrescriptions = drugPrescriptionMapper.selectByExample(drugPrescriptionExample);

            for (DrugPrescription drugPre : drugPrescriptions) {
                DrugPrescriptionDetailExample drugPrescriptionDetailExample = new DrugPrescriptionDetailExample();
                DrugPrescriptionDetailExample.Criteria drugPrescriptionDetailExampleCriteria = drugPrescriptionDetailExample.createCriteria();
                drugPrescriptionDetailExampleCriteria.andDrugPreIdEqualTo(drugPre.getDrugPreId());
                drugPrescriptionDetailExampleCriteria.andDrugPreDetailStateEqualTo("未缴费");

                List<DrugPrescriptionDetail> findDrugPreDetails = drugPrescriptionDetailMapper.selectByExample(drugPrescriptionDetailExample);

                for (DrugPrescriptionDetail drugPreDetail : findDrugPreDetails) {
                    DrugPreDetailInfo drugPreDetailInfo = new DrugPreDetailInfo();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Drug drug = drugMapper.selectByPrimaryKey(drugPreDetail.getDrugId());

                    drugPreDetailInfo.setDrugPreDetailID(drugPreDetail.getDrugPreDetailId());
                    drugPreDetailInfo.setAmount(drugPreDetail.getDrugPreDetailNum());
                    drugPreDetailInfo.setCreateTime(simpleDateFormat.format(drugPre.getDrugPreTime()));
                    drugPreDetailInfo.setDrugName(drug.getDrugName());
                    drugPreDetailInfo.setDrugUnitPrice(drug.getDrugUnitPrice());
                    drugPreDetailInfo.setState(drugPreDetail.getDrugPreDetailState());

                    JSONObject jsonObject = (JSONObject) JSON.toJSON(drugPreDetailInfo);
                    returnJsons.add(jsonObject);
                }
            }
            return returnJsons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据处方明细ID得到应收金额
     *
     * @param drugPreDetailIDDTO
     * @return
     */
    @Override
    public JSONObject getDrugPreDetailInfo(DrugPreDetailIDDTO drugPreDetailIDDTO) {
        JSONObject returnJson;
        try {
            float wholeMoney = 0;
            List<Integer> drugPreDetailIDs = drugPreDetailIDDTO.getDrugPreDetailIDs();
            for (int i = 0; i < drugPreDetailIDs.size(); i++) {
                DrugPrescriptionDetail drugPrescriptionDetail = drugPrescriptionDetailMapper.selectByPrimaryKey(drugPreDetailIDs.get(i));
                int amount = drugPrescriptionDetail.getDrugPreDetailNum();
                Drug drug = drugMapper.selectByPrimaryKey(drugPrescriptionDetail.getDrugId());
                float unitPrice = drug.getDrugUnitPrice();

                float money = amount * unitPrice;
                wholeMoney += money;
            }

            ChargeMoneyBack chargeMoneyBack = new ChargeMoneyBack();
            chargeMoneyBack.setWholeMoney(wholeMoney);

            returnJson = (JSONObject) JSON.toJSON(chargeMoneyBack);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 找到可用发票号
     *
     * @return
     */
    @Override
    public JSONObject findAvailableInvoiceID() {
        JSONObject returnJson;
        try {
            int maxID = invoiceMapper.findMaxID();
            AvailableInvoiceID availableInvoiceID = new AvailableInvoiceID();
            availableInvoiceID.setInvoiceID(maxID);
            returnJson = (JSONObject) JSON.toJSON(availableInvoiceID);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到挂号金额
     *
     * @return
     */
    @Override
    public JSONObject getRegisterLevelMoney(RegisterLevelDTO registerLevelDTO) {
        JSONObject returnJson;
        try {
            RegistLevelExample example = new RegistLevelExample();
            RegistLevelExample.Criteria criteria = example.createCriteria();
            criteria.andRegistLevelNameEqualTo(registerLevelDTO.getRegisterLevel());

            List<RegistLevel> registLevels = registLevelMapper.selectByExample(example);

            AvailableInvoiceID availableInvoiceID = new AvailableInvoiceID();
            availableInvoiceID.setInvoiceID(registLevels.get(0).getRegistLevelPrice());
            returnJson = (JSONObject) JSON.toJSON(availableInvoiceID);
            return returnJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
