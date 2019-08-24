package com.neu.his.serviceInterface;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.ChargeInfoDTO;
import com.neu.his.dto.RegisterBackDTO;
import com.neu.his.dto.RegisterDTO;

public interface RegistrationManagement {
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
     * 收费员收费
     *
     * @param chargeInfoDTO
     * @return
     */
    JSONObject charge(ChargeInfoDTO chargeInfoDTO);

    /**
     * 挂号时根据病历号得到一些信息
     *
     * @param medicalRecordID
     * @return
     */
    JSONObject getRegistrationInfo(Integer medicalRecordID);
}
