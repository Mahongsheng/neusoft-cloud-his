package com.neu.his.serviceInterface;

import com.alibaba.fastjson.JSONObject;
import com.neu.his.dto.*;

import java.util.List;

public interface DrugManagement {

    /**
     * 医生开药
     *
     * @param prescribeDTO
     * @return
     */
    JSONObject prescribe(PrescribeDTO prescribeDTO);

    /**
     * 得到所有的待发药处方明细
     *
     * @param prescribeSearchDTO
     * @return
     */
    List<JSONObject> getPrescribeInfo(PrescribeSearchDTO prescribeSearchDTO);
}
