package com.neu.his.serviceInterface;

import com.neu.his.dto.UserLoginDTO;
import com.neu.his.vojo.LoginReturn;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户管理接口
 */
public interface LoginManagement {
    /**
     * 登录
     *
     * @param userLoginDTO
     * @return
     */
    LoginReturn login(UserLoginDTO userLoginDTO);
}
