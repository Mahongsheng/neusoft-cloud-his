package com.neu.his.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotFoundController {
    @GetMapping("/404.html")
    public String notFound() {
        return "404";
    }
}
