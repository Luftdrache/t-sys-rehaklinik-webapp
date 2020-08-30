package com.tsystems.rehaklinik.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;


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
    private static final String MESSAGE_FIRST_PART = "message";
    private static final String MESSAGE_SECOND_PART = "message_part2";


    /**
     * Handles Exception
     *
     * @param req HttpServletRequest
     * @param ex  Exception
     * @return common error page
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest req, Exception ex) {
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: Error occurred with {} -----> {}",
                req.getRequestURL(), ex.getMessage());
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS -----> ", ex.fillInStackTrace());
        ModelAndView model = new ModelAndView(COMMON_ERROR_PAGE_JSP);
        model.addObject(MESSAGE_FIRST_PART, "Something went wrong.");
        model.addObject(MESSAGE_SECOND_PART, "Don't panic, we'll fix it soon!");
        return model;
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(HttpServletRequest req, Exception ex) {
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: Error occurred with {} -----> {}",
                req.getRequestURL(), ex.getMessage());
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS -----> ", ex.fillInStackTrace());
        ModelAndView model = new ModelAndView(COMMON_ERROR_PAGE_JSP);
        model.addObject(MESSAGE_FIRST_PART, "Sorry, page not found!");
        model.addObject(MESSAGE_SECOND_PART, "Please check the URL and try again.");
        return model;
    }


    @ExceptionHandler(WrongIdException.class)
    public ModelAndView handleWrongIdException(HttpServletRequest req, Exception ex) {
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: Error occurred with {} -----> {}",
                req.getRequestURL(), ex.getMessage());
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS ----->", ex.fillInStackTrace());
        ModelAndView model = new ModelAndView(COMMON_ERROR_PAGE_JSP);
        model.addObject(MESSAGE_FIRST_PART, "Operation with an invalid parameter");
        model.addObject(MESSAGE_SECOND_PART, ex.getMessage());
        return model;
    }
}
