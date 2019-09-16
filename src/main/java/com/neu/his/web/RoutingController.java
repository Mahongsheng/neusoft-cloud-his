package com.neu.his.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {

    @GetMapping("/register.html")
    public String registerHtml() {
        return "register";
    }

    @GetMapping("/registerBack.html")
    public String registerBackHtml() {
        return "registerBack";
    }

    @GetMapping("/charge.html")
    public String chargeHtml() {
        return "charge";
    }

    @GetMapping("/prescribe.html")
    public String prescribeHtml() {
        return "prescribe";
    }

    @GetMapping("/medicalRecord.html")
    public String medicalRecord() {
        return "medicalRecord";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }
}