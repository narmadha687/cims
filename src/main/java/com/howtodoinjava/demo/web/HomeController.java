package com.howtodoinjava.demo.web;

import com.howtodoinjava.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    EmployeeService service;

    @RequestMapping
    public String home() {
        return "home";
    }
}
