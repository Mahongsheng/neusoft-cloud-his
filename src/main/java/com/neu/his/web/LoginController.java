package com.neu.his.web;

import com.neu.his.dto.UserLoginDTO;
import com.neu.his.serviceInterface.LoginManagement;
import com.neu.his.vojo.LoginReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户管理控制类
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月23日08:53:59
 */
@Controller
public class LoginController {
    @Autowired
    private LoginManagement loginManagement;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public String loginPost(@RequestParam("userName") String userName,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Map<String,Object> map) {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUserLoginName(userName);
        userLoginDTO.setUserPsw(password);
        LoginReturn loginReturn = loginManagement.login(userLoginDTO);
        if (loginReturn.isIfNameRight() && loginReturn.isIfPswRight()) {
            //登录成功
            session.setAttribute("userName",loginReturn.getUserName());
            session.setAttribute("userType",loginReturn.getUserType());
            session.setAttribute("userID",loginReturn.getUserID());
            return "redirect:/index";
        } else {
            //登录失败
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
