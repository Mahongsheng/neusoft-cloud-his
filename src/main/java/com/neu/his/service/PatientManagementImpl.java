package com.neu.his.service;

import com.neu.his.dao.DepartmentMapper;
import com.neu.his.dao.PatientMapper;
import com.neu.his.dao.RegistLevelMapper;
import com.neu.his.dao.RegistrationRecordMapper;
import com.neu.his.dto.RegisterDTO;
import com.neu.his.serviceInterface.PatientManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean register(RegisterDTO registerDTO) {




        return false;
    }
}
