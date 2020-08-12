package com.tsystems.rehaklinik.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    private static final String LOGIN_PAGE_JSP = "login";
    private static final String ERROR_403_PAGE_JSP = "403_error_page";
    private static final String COMMON_ERROR_PAGE_JSP = "404_405_500_etc_error_page";

    @GetMapping("/login")
    public String getLoginPage() {
        return LOGIN_PAGE_JSP;
    }

    @GetMapping("/403-error-page")
    public String sendAccessDeniedErrorPage() {
        return ERROR_403_PAGE_JSP;
    }
}
