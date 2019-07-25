package com.neu.his.serviceInterface;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.*;

public interface PatientManagement {
    /**
     * 挂号方法
     *
     * @param registerDTO
     * @return
     */
    JSONObject register(RegisterDTO registerDTO);

    /**
     * 退号方法
     *
     * @param registerBackDTO
     * @return
     */
    JSONObject registerBack(RegisterBackDTO registerBackDTO);

    /**
     * 填写病历首页
     *
     * @param medicalRecordDTO
     * @return
     */
    JSONObject writeMedical(MedicalRecordDTO medicalRecordDTO);

    /**
     * 医生开立处方（开药）
     *
     * @param drugPrescriptionDTO
     * @return
     */
    JSONObject makePrescription(DrugPrescriptionDTO drugPrescriptionDTO);

    /**
     * 收费员收费
     *
     * @param chargeInfoDTO
     * @return
     */
    JSONObject charge(ChargeInfoDTO chargeInfoDTO);

    /**
     * 医生开药
     *
     * @param prescribeDTO
     * @return
     */
    JSONObject prescribe(PrescribeDTO prescribeDTO);
}
