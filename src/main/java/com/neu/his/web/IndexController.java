package com.neu.his.web;

import com.neu.his.dao.DoctorMapper;
import com.neu.his.pojo.Doctor;
import com.neu.his.pojo.DoctorExample;
import com.neu.his.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                DoctorExample doctorExample = new DoctorExample();
                DoctorExample.Criteria doctorCriteria = doctorExample.createCriteria();
                doctorCriteria.andDoctorIdEqualTo(Short.parseShort(token));
                List<Doctor> doctors = doctorMapper.selectByExample(doctorExample);
                if (!doctors.isEmpty()) {
                    request.getSession().setAttribute("doctor", doctors.get(0));
                }
                break;
            }
        }
        return "index";
    }
}
