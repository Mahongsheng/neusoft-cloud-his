package com.neu.his.web;

import com.neu.his.dto.UserLoginDTO;
import com.neu.his.serviceInterface.LoginManagement;
import com.neu.his.vojo.LoginReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    public String loginPost(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletResponse response) {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUserLoginName(userName);
        userLoginDTO.setUserPsw(password);
        LoginReturn loginReturn = loginManagement.login(userLoginDTO, response);
        if (loginReturn.isIfNameRight() && loginReturn.isIfPswRight()) {
            return "redirect:/";
        } else {
            return "redirect:/404";
        }
    }
}
