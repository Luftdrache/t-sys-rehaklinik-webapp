package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminService.class);
    private AdminService adminService;

    @GetMapping("/admin-page")
    public String showAllEmployees(ModelMap model){
        //Test
        model.addAttribute("message", "Test message!");
        return "admin_page";
    }


    @Autowired
    public AdminController(AdminService adminService) {this.adminService = adminService;}
}
