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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    private static final String TODAY_TREATMENT_EVENTS_JSP = "nurse_today_events";

    private static final String MESSAGE = "message";
    protected static final String TREATMENT_EVENT_LIST = "treatmentEventList";



    /**
     * Changes treatment event status to "Completed"
     *
     * @param tEventId      treatment event status id
     * @param bindingResult binding result
     * @param modelMap      ModelMap
     * @return redirects to main page with all planned treatment events
     */
    @PostMapping("/treatment-event-set-completed")
    public String setCompletedTreatmentEvent(@ModelAttribute("treatmentEventId") int tEventId,
                                             BindingResult bindingResult,
                                             ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In NurseController - handler method setCompletedTreatmentEvent(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        boolean actionResult = nurseService.setTreatmentEventToCompleted(tEventId);
        if (!actionResult) {
            modelMap.addAttribute(MESSAGE, "Failed to change treatment event status");
        }
        modelMap.addAttribute(MESSAGE, " Treatment event status changed to 'COMPLETED'");
        return "redirect:/nurse/start-page";
    }


    /**
     * Assigns the completed status to the treatment event
     *
     * @param tEventId      treatment event id
     * @param cancelReason  reason why the treatment event was canceled
     * @param bindingResult binding result
     * @param modelMap      ModeMap
     * @return redirects to nurse's main page
     */
    @PostMapping("/cancel-treatment-event")
    public String cancelTreatmentTEvent(@ModelAttribute("tEvent") int tEventId,
                                        @ModelAttribute("cancelReason") String cancelReason,
                                        BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In NurseController - handler method cancelTreatmentTEvent(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        boolean actionResult = nurseService.cancelTreatmentEvent(tEventId, cancelReason);
        if (!actionResult) {
            modelMap.addAttribute(MESSAGE, "Failed to change treatment event status");
        }
        return "redirect:/nurse/start-page";
    }


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
            modelMap.addAttribute(TREATMENT_EVENT_LIST, treatmentEventDTOS);
        } else {
            logger.info("MedHelper_LOGS: The action showCompletedTreatmentEvents() returned empty list");
            modelMap.addAttribute(MESSAGE,
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
        if(modelMap.isEmpty()) {
            modelMap.addAttribute("tableHeader", "ALL PLANNED TREATMENT EVENTS");
            List<TreatmentEventDTO> treatmentEventDTOS = nurseService.findAllPlannedTreatmentEvents();
            if (!treatmentEventDTOS.isEmpty()) {
                logger.info("MedHelper_LOGS: In NurseController: The action showNursePage() completed successfully");
                modelMap.addAttribute(TREATMENT_EVENT_LIST, treatmentEventDTOS);
            } else {
                logger.info("MedHelper_LOGS: The action showNursePage() returned empty list");
                modelMap.addAttribute(MESSAGE,
                        "INFO: You don't have any treatment events yet. Rest a little bit!");
            }
        }
        return MAIN_NURSE_JSP;
    }


    /**
     * Shows urgent treatment events (on a nearest hour)
     *
     * @param modelMap ModelMap
     * @return page with treatment events on a nearest hour
     */
    @GetMapping("/urgent-treatment-events")
    public String getUrgentTreatmentEvents(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In NurseController - handler method  getUrgentTreatmentEvents(), GET");
        List<TreatmentEventDTO> treatmentEventDTOS = nurseService.getUrgentTreatmentEvents();
        if (!treatmentEventDTOS.isEmpty()) {
            logger.info("MedHelper_LOGS: In NurseController: The action getUrgentTreatmentEvents() completed successfully");
            modelMap.addAttribute(TREATMENT_EVENT_LIST, treatmentEventDTOS);
        } else {
            logger.info("MedHelper_LOGS: The action getUrgentTreatmentEvents() returned empty list");
            modelMap.addAttribute(MESSAGE,
                    "INFO: You don't have any urgent treatment events yet.");
        }
        return URGENT_TREATMENT_EVENTS_JSP;
    }


    /**
     * Shows treatment events that a nurse should perform today
     *
     * @param modelMap ModelMap
     * @return page with current treatment events for today
     */
    @GetMapping("/today-treatment-events")
    public String getTodayTreatmentEvents(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In NurseController - handler method getTodayTreatmentEvents(), GET");
        List<TreatmentEventDTO> treatmentEventDTOS = nurseService.getTodayTreatmentEvents();
        if (!treatmentEventDTOS.isEmpty()) {
            logger.info("MedHelper_LOGS: In NurseController: The action getTodayTreatmentEvents() completed successfully");
            modelMap.addAttribute(TREATMENT_EVENT_LIST, treatmentEventDTOS);
        } else {
            logger.info("MedHelper_LOGS: The action getTodayTreatmentEvents() returned empty list");
            modelMap.addAttribute(MESSAGE,
                    "INFO: You don't have any treatment events today.");
        }
        return TODAY_TREATMENT_EVENTS_JSP;
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
            modelMap.addAttribute(MESSAGE, "Treatment event with specified id was not found");
        }
        return TREATMENT_EVENT_DETAILS_JSP;
    }


    /**
     * Finds patient's treatment events by surname
     *
     * @param patientSurname patient's surname
     * @param redirectAttributes redirect attributes
     * @return search result
     */
    @GetMapping("/find-events-by-surname")
    public RedirectView findTreatmentEventsBySurname(@RequestParam("patientSurname") String patientSurname,
                                                     RedirectAttributes redirectAttributes) {
        logger.info("MedHelper_LOGS: In NurseController - handler method findTreatmentEventsBySurname(), GET");

        RedirectView redirectView = new RedirectView("/nurse/start-page", true);
        List<TreatmentEventDTO> treatmentEventDTOS = nurseService.findTreatmentEventsByPatientsSurname(patientSurname);
        redirectAttributes.addFlashAttribute("tableHeader", "SEARCH RESULT");
        if (!treatmentEventDTOS.isEmpty()) {
            logger.info("MedHelper_LOGS: In NurseController: The action findTreatmentEventsBySurname() completed successfully");
            redirectAttributes.addFlashAttribute(TREATMENT_EVENT_LIST, treatmentEventDTOS);
        } else {
            logger.info("MedHelper_LOGS: The action  findTreatmentEventsBySurname() returned empty list");
            redirectAttributes.addFlashAttribute(MESSAGE,
                    "INFO: The patient with the specified surname not found");
        }
        return redirectView;
    }

    @Autowired
    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }
}
