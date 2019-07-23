package com.neu.his.serviceInterface;

import com.neu.his.dto.RegisterDTO;

public interface PatientManagement {
    /**
     * 挂号方法
     * @param registerDTO
     * @return
     */
    boolean register(RegisterDTO registerDTO);
}
