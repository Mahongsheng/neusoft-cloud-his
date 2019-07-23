package com.neu.his.service;

import com.neu.his.dao.DoctorMapper;
import com.neu.his.dao.UserMapper;
import com.neu.his.dto.UserLoginDTO;
import com.neu.his.pojo.Doctor;
import com.neu.his.pojo.DoctorExample;
import com.neu.his.pojo.User;
import com.neu.his.pojo.UserExample;
import com.neu.his.serviceInterface.UserManagement;
import com.neu.his.vojo.LoginReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理服务层
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月23日08:51:37
 */
@Service
public class UserManagementImpl implements UserManagement {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    /**
     * 用户登录（待补充token、session、cookie）等
     * @param userLoginDTO
     * @return
     */
    @Override
    public LoginReturn login(UserLoginDTO userLoginDTO) {
        //实例化返回值
        LoginReturn loginReturn = new LoginReturn();
        if (userLoginDTO == null){
            return loginReturn;
        }
        loginReturn.setUserType(userLoginDTO.getUserType());
        //监测任何异常
        try {
            //判断医生或管理员
            if (userLoginDTO.getUserType().equals("医生")) {
                //使用Example进行查询
                DoctorExample doctorExample = new DoctorExample();
                DoctorExample.Criteria doctorCriteria = doctorExample.createCriteria();
                doctorCriteria.andDoctorLoginNameEqualTo(userLoginDTO.getUserLoginName());
                List<Doctor> doctorList = doctorMapper.selectByExample(doctorExample);

                //列表为空，则证明用户名不正确
                if (doctorList.isEmpty()) return loginReturn;

                if (doctorList.get(0).getDoctorPsw() == userLoginDTO.getUserPsw()){
                    loginReturn.setIfNameRight(true);
                    loginReturn.setIfPswRight(true);
                    loginReturn.setUserName(doctorList.get(0).getDoctorName());
                }
                return loginReturn;
            } else if (userLoginDTO.getUserType().equals("管理员")) {

                //使用Example进行查询
                UserExample userExample = new UserExample();
                UserExample.Criteria userCriteria = userExample.createCriteria();
                userCriteria.andUserLoginNameEqualTo(userLoginDTO.getUserLoginName());
                List<User> userList = userMapper.selectByExample(userExample);

                //列表为空，则证明用户名不正确
                if (userList.isEmpty()) return loginReturn;

                if (userList.get(0).getUserPsw() == userLoginDTO.getUserPsw()){
                    loginReturn.setIfNameRight(true);
                    loginReturn.setIfPswRight(true);
                    loginReturn.setUserName(userList.get(0).getUserName());
                }
                return loginReturn;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return loginReturn;
        }
        return loginReturn;
    }
}