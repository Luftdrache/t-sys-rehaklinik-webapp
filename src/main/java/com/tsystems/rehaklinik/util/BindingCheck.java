package com.tsystems.rehaklinik.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;


public class BindingCheck {

    private static Logger logger = LoggerFactory.getLogger(BindingCheck.class);


    /**
     * Checks if there is any binding errors
     *
     * @param bindingResult the binding results
     * @param modelMap      ModelMap
     * @return boolean result
     */
    public static boolean bindingResultCheck(BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    modelMap.addAttribute("message", "<p>Details: </p>" + fieldError.getDefaultMessage());
                    logger.error("BindingResult Error: {}. Details: {} ", fieldError.getCode(), fieldError.getDefaultMessage());
                    return true;
                }
            }
        }
        return false;
    }

    private BindingCheck() {
    }
}
