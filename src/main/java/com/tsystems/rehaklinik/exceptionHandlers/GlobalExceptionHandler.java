package com.tsystems.rehaklinik.exceptionHandlers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * ControllerAdvice. Handles all type HTTP exceptions, except 403 Forbidden
 *
 * @author Julia Dalskaya
 */
@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String COMMON_ERROR_PAGE_JSP = "404_405_500_etc_error_page";

    @ExceptionHandler(Exception.class)
    public String handleException(HttpSession session, HttpServletRequest req, Exception ex) {
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: Error occurred with {} ----> {}",
                req.getRequestURL(), ex.getMessage());
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS ----> {}", ex.fillInStackTrace());
        return COMMON_ERROR_PAGE_JSP;
    }
}
