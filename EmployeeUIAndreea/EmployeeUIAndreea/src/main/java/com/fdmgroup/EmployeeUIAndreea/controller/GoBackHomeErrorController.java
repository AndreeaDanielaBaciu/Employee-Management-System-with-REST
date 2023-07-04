package com.fdmgroup.EmployeeUIAndreea.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoBackHomeErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Custom logic to handle the error and display a custom error page
        return "error"; // Assuming you have an "error.html" Thymeleaf template
    }

    public String getErrorPath() {
        return "/error";
    }
}

