package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.TreatmentEventDTO;
import com.tsystems.rehaklinik.services.NurseService;
import com.tsystems.rehaklinik.util.BindingCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nurse")
public class NurseController {

    private Logger logger = LoggerFactory.getLogger(NurseController.class);

    NurseService nurseService;

    private static final String MAIN_NURSE_JSP = "nurse_main_page";
    private static final String TREATMENT_EVENT_DETAILS_JSP = "nurse_event_details";
    private static final String SHOW_COMPLETED_TREATMENT_EVENTS_JSP = "nurse_all_completed_t_events";
    private static final String ERROR_PAGE_JSP = "input_data_error_page";
    private static final String URGENT_TREATMENT_EVENTS_JSP = "nurse_urgent_events";


    @PostMapping("/treatment-event-set-completed")
    public String setCompletedTreatmentEvent(ModelMap modelMap) {
        return MAIN_NURSE_JSP;
    }


    @GetMapping("/urgent-treatment-events")
    public String getUrgentTreatmentEvents() {

        return URGENT_TREATMENT_EVENTS_JSP;
    }


    //передать id!
    @PostMapping("/cancel-treatment-event")

    public String cancelTreatmentTEvent(@ModelAttribute("tEvent") int tEventId,
                                        @ModelAttribute("cancelReason") String cancelReason,
                                        BindingResult bindingResult, ModelMap modelMap) {

        logger.info("MedHelper_LOGS: In NurseController - handler method cancelTreatmentTEvent(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }

        boolean actionResult = nurseService.cancelTreatmentEvent(tEventId, cancelReason);
        if (actionResult == false) {
            modelMap.addAttribute("message", "Failed to change treatment event status");
        }
        return "redirect:/nurse/start-page";
    }


    //************ Done *************************

    /**
     * Shows all completed treatment events
     *
     * @param modelMap ModelMap
     * @return page with all completed treatment events
     */
    @GetMapping("/show-completed-treatment-events")
    public String showCompletedTreatmentEvents(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In NurseController: handler method showCompletedTreatmentEvents(), GET");
        List<TreatmentEventDTO> treatmentEventDTOS = nurseService.findAllCompletedTreatmentEvents();
        if (!treatmentEventDTOS.isEmpty()) {
            logger.info("MedHelper_LOGS: In NurseController: The action showCompletedTreatmentEvents() completed successfully");
            modelMap.addAttribute("treatmentEventList", treatmentEventDTOS);
        } else {
            logger.info("MedHelper_LOGS: The action showCompletedTreatmentEvents() returned empty list");
            modelMap.addAttribute("message",
                    "INFO: You don't have any completed treatment events yet. You should get to work!");
        }
        return SHOW_COMPLETED_TREATMENT_EVENTS_JSP;
    }

    /**
     * Returns main nurse's page with all her treatment events that need to be done on it. Start page.
     *
     * @param modelMap modelMap to add a list of treatment events sorted by date and time
     * @return main nurse's page
     */
    @GetMapping("/start-page")
    public String showNursePage(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In NurseController: handler method showNursePage(), GET");
        List<TreatmentEventDTO> treatmentEventDTOS = nurseService.findAllPlannedTreatmentEvents();
        if (!treatmentEventDTOS.isEmpty()) {
            logger.info("MedHelper_LOGS: In NurseController: The action showNursePage() completed successfully");
            modelMap.addAttribute("treatmentEventList", treatmentEventDTOS);
        } else {
            logger.info("MedHelper_LOGS: The action showNursePage() returned empty list");
            modelMap.addAttribute("message",
                    "INFO: You don't have any treatment events yet. Rest a little bit!");
        }
        return MAIN_NURSE_JSP;
    }


    /**
     * Shows selected treatment event details
     *
     * @param id       selected treatment event id
     * @param modelMap ModelMap with found data or message about bad request
     * @return page with treatment event details
     */
    @GetMapping("/treatment-event-details/{id}")
    public String showSelectedTreatmentDetails(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In NurseController: handler method showSelectedTreatmentDetails(), GET");
        TreatmentEventDTO treatmentEventDTO = nurseService.findTreatmentEventById(id);
        if (treatmentEventDTO != null) {
            logger.info("MedHelper_LOGS: In NurseController: handler method showSelectedTreatmentDetails() returns treatment event found by id");
            modelMap.addAttribute("treatmentEventDetails", treatmentEventDTO);
        } else {
            modelMap.addAttribute("message", "Treatment event with specified id was not found");
        }
        return TREATMENT_EVENT_DETAILS_JSP;
    }


    @Autowired
    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }
}
