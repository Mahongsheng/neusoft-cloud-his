package com.neu.his.service;

import com.neu.his.dao.*;
import com.neu.his.dto.RegisterDTO;
import com.neu.his.pojo.*;
import com.neu.his.serviceInterface.PatientManagement;
import org.apache.ibatis.jdbc.Null;
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

    /**
     * 患者挂号
     *
     * @param registerDTO
     * @return
     */
    @Override
    public boolean register(RegisterDTO registerDTO) {
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
            System.out.println(departmentID);

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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void addPatient(RegisterDTO registerDTO) {

    }

    private void addInvoice(RegisterDTO registerDTO) {
    }
}
