package com.neu.his.service;

import com.neu.his.dao.DoctorMapper;
import com.neu.his.dao.UserMapper;
import com.neu.his.dto.UserLoginDTO;
import com.neu.his.pojo.Doctor;
import com.neu.his.pojo.DoctorExample;
import com.neu.his.pojo.User;
import com.neu.his.pojo.UserExample;
import com.neu.his.serviceInterface.LoginManagement;
import com.neu.his.vojo.LoginReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户管理服务层
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月23日08:51:37
 */
@Service
public class LoginManagementImpl implements LoginManagement {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    /**
     * 用户登录（待补充token、session、cookie）等
     *
     * @param userLoginDTO
     * @return
     */
    @Override
    public LoginReturn login(UserLoginDTO userLoginDTO) {
        //实例化返回值
        LoginReturn loginReturn = new LoginReturn();
        //监测任何异常
        try {
            //先后在医生和管理员表中进行检索
            //使用Example进行查询
            DoctorExample doctorExample = new DoctorExample();
            DoctorExample.Criteria doctorCriteria = doctorExample.createCriteria();
            doctorCriteria.andDoctorLoginNameEqualTo(userLoginDTO.getUserLoginName());
            List<Doctor> doctorList = doctorMapper.selectByExample(doctorExample);
            //列表为空，则证明医生中不存在该用户
            if (doctorList.isEmpty()) {
                //使用Example进行查询
                UserExample userExample = new UserExample();
                UserExample.Criteria userCriteria = userExample.createCriteria();
                userCriteria.andUserLoginNameEqualTo(userLoginDTO.getUserLoginName());
                List<User> userList = userMapper.selectByExample(userExample);
                if (userList.isEmpty()) {
                    //都没有检索到该用户，用户名不存在
                    loginReturn.setIfNameRight(false);
                } else if (!userList.get(0).getUserPsw().equals(userLoginDTO.getUserPsw())) {
                    loginReturn.setIfNameRight(true);
                    loginReturn.setIfPswRight(false);
                    return loginReturn;
                } else {
                    loginReturn.setIfNameRight(true);
                    loginReturn.setIfPswRight(true);
                    loginReturn.setUserID(userList.get(0).getUserId());
                    loginReturn.setUserName(userList.get(0).getUserName());
                    loginReturn.setUserType("管理员");
                    //列表不为空，写cookie
//                    response.addCookie(new Cookie("token", userList.get(0).getUserId().toString()));
                    return loginReturn;
                }
            } else if (!doctorList.get(0).getDoctorPsw().equals(userLoginDTO.getUserPsw())) {
                loginReturn.setIfNameRight(true);
                loginReturn.setIfPswRight(false);
                return loginReturn;
            } else {
                loginReturn.setIfNameRight(true);
                loginReturn.setIfPswRight(true);
                loginReturn.setUserID(doctorList.get(0).getDoctorId());
                loginReturn.setUserName(doctorList.get(0).getDoctorName());
                loginReturn.setUserType("医生");
                //列表不为空，写cookie
//                response.addCookie(new Cookie("token", doctorList.get(0).getDoctorId().toString()));
                return loginReturn;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return loginReturn;
        }
        return loginReturn;
    }
}
