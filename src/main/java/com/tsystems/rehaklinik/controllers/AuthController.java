package com.tsystems.rehaklinik.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Processes requests for spring security logic
 *
 * @author Julia Dalskaya
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    private static final String LOGIN_PAGE_JSP = "login";
    private static final String ERROR_403_PAGE_JSP = "403_error_page";

    /**
     * Shows login page for user authentication
     *
     * @return login page for user authentication
     */
    @GetMapping("/login")
    public String getLoginPage() {
        logger.info("MedHelper_LOGS: In AuthController - handler method getLoginPage(), GET");
        return LOGIN_PAGE_JSP;
    }


    /**
     * Redirects users to a page with warning message about they do not have access rights to a requested resource
     * (error 403)
     *
     * @return access denied error page
     */
    @GetMapping("/403-error-page")
    public String sendAccessDeniedErrorPage() {
        logger.info("MedHelper_LOGS: In AuthController - handler method sendAccessDeniedErrorPage(), GET");
        return ERROR_403_PAGE_JSP;
    }
}
