package com.tsystems.rehaklinik.controllers.rest;

import com.tsystems.rehaklinik.dto.TreatmentEventDTO;
import com.tsystems.rehaklinik.services.interfaces.NurseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller for an external web application
 *
 * @author Julia Dalskaya
 */
@RestController
@RequestMapping("/rehaklinik/api")
public class NurseBoardRestController {

    private Logger logger = LoggerFactory.getLogger(NurseBoardRestController.class);

    NurseService nurseService;

    /**
     * Returns all today treatment events to external web application
     *
     * @return List of TreatmentEventDTOs
     */
    @GetMapping(value = "/today-treatment-events")
    public List<TreatmentEventDTO> getTodayTreatmentEvents() {
        logger.info("MedHelper_LOGS: In NurseBoardRestController - handler method getTodayTreatmentEvents()");
        return nurseService.getTodayTreatmentEvents();
    }

    @Autowired
    public NurseBoardRestController(NurseService nurseService) {
        this.nurseService = nurseService;
    }
}
