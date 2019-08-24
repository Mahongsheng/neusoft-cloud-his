package com.neu.his.serviceInterface;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.*;

public interface DrugManagement {

    /**
     * 医生开药
     *
     * @param prescribeDTO
     * @return
     */
    JSONObject prescribe(PrescribeDTO prescribeDTO);
}
