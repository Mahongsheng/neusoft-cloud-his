package com.neu.his.web;

import com.neu.his.dto.UserLoginDTO;
import com.neu.his.serviceInterface.UserManagement;
import com.neu.his.vojo.LoginReturn;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    private UserManagement userManagement;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        LoginReturn loginReturn = userManagement.login(userLoginDTO, response);
        if (loginReturn.isIfNameRight() && loginReturn.isIfPswRight()) {
            return "redirect:/index";
        } else {
            return "redirect:/index";
        }
    }
}
