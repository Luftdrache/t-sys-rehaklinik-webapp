package com.tsystems.rehaklinik.exceptions;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


/**
 * ControllerAdvice. Handles all type HTTP exceptions, except 403 Forbidden, and some custom exceptions
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
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS -----> ", ex);
        ModelAndView model = new ModelAndView(COMMON_ERROR_PAGE_JSP);
        model.addObject(MESSAGE_FIRST_PART, "Something went wrong.");
        model.addObject(MESSAGE_SECOND_PART, "Don't panic, we'll fix it soon!");
        return model;
    }

    /**
     * Handles NoHandlerFoundException
     *
     * @param req HttpServletRequest
     * @param ex  Exception
     * @return common error page
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(HttpServletRequest req, Exception ex) {
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: Error occurred with {} -----> {}",
                req.getRequestURL(), ex.getMessage());
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS -----> ", ex);
        ModelAndView model = new ModelAndView(COMMON_ERROR_PAGE_JSP);
        model.addObject(MESSAGE_FIRST_PART, "Sorry, page not found!");
        model.addObject(MESSAGE_SECOND_PART, "Please check the URL and try again.");
        return model;
    }

    /**
     * Handles WrongIdException
     *
     * @param req HttpServletRequest
     * @param ex  Exception
     * @return common error page
     */
    @ExceptionHandler(WrongIdException.class)
    public ModelAndView handleWrongIdException(HttpServletRequest req, Exception ex) {
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: Error occurred with {} -----> {}",
                req.getRequestURL(), ex.getMessage());
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS ----->", ex);
        ModelAndView model = new ModelAndView(COMMON_ERROR_PAGE_JSP);
        model.addObject(MESSAGE_FIRST_PART, "Operation with an invalid parameter");
        model.addObject(MESSAGE_SECOND_PART, ex.getMessage());
        return model;
    }

    /**
     * Handles DuplicatePrescriptionException
     *
     * @param req HttpServletRequest
     * @param ex  Exception
     * @return common error page
     */
    @ExceptionHandler(DuplicatePrescriptionException.class)
    public ModelAndView handleDuplicatePrescriptionException(HttpServletRequest req, Exception ex) {
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: Error occurred with {} -----> {}",
                req.getRequestURL(), ex.getMessage());
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS ----->", ex);
        ModelAndView model = new ModelAndView(COMMON_ERROR_PAGE_JSP);
        model.addObject(MESSAGE_FIRST_PART,
                "Prescription similar to what you are trying to add is already in the database. " +
                        "Change dates or edit an existing one:\n\n");
        model.addObject(MESSAGE_SECOND_PART, ex.getMessage());
        return model;
    }

    /**
     * Handles DataIntegrityViolationException
     *
     * @param req HttpServletRequest
     * @param ex  Exception
     * @return common error page
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(HttpServletRequest req, Exception ex) {
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: Error occurred with {} -----> {}",
                req.getRequestURL(), ex.getMessage());
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS ----->", ex.getCause().getCause().getMessage());
        ModelAndView model = new ModelAndView(COMMON_ERROR_PAGE_JSP);
        model.addObject(MESSAGE_FIRST_PART,
                "An error occurred while entering data! Check your input: ");
        model.addObject(MESSAGE_SECOND_PART, ExceptionUtils.getRootCause(ex).getMessage());
        return model;
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ModelAndView handleConstraintViolationException(HttpServletRequest req, Exception ex) {
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: Error occurred with {} -----> {}",
                req.getRequestURL(), ex.getMessage());
        logger.error("MedHelper_LOGS: GlobalExceptionHandler: SEE DETAILS ----->", ex.getCause().getCause().getMessage());
        ModelAndView model = new ModelAndView(COMMON_ERROR_PAGE_JSP);
        model.addObject(MESSAGE_FIRST_PART,
                "An error occurred while entering data! Check your input: ");
        model.addObject(MESSAGE_SECOND_PART, ExceptionUtils.getRootCauseMessage(ex));
        return model;
    }

}
