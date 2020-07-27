package com.tsystems.rehaklinik.controllers;

import Util.BindingCheck;
import com.tsystems.rehaklinik.dto.PatientReceptionViewDTO;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.services.HospitalReceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/reception")
public class HospitalReceptionController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final HospitalReceptionService receptionService;

    private static final String RECEPTION_MAIN_JSP = "hospital_reception_page";
    private static final String ADD_NEW_PATIENT_JSP = "add_new_patient";
    private  static final String PATIENT_DETAILS_JSP = "patient_details.jsp";


    /**
     * Returns list of all patients found in database
     * @param modelMap
     * @return list of all patients found in database
     */
    @GetMapping("/start-page")
    public String showAllPatients(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In HospitalReceptionController:  handler method showAllPatients(), GET");
        List<PatientReceptionViewDTO> allPatients = receptionService.showAllPatients();
        if (allPatients != null) {
            modelMap.addAttribute("allPatients",allPatients);
            logger.info("MedHelper_LOGS: The action showAllPatients() completed successfully");
        } else {
            modelMap.addAttribute("message", "INFO: There is no information about patients in the database");
            logger.info("MedHelper_LOGS: The action showAllPatients() returned nothing");
        }
        return RECEPTION_MAIN_JSP;
    }


    /**
     * Returns page for new patient adding
     *
     * @return form page for new patient adding
     */
    @GetMapping("/add-patient")
    public String addPatientGetForm() {
        logger.info("MedHelper_LOGS: In HospitalReceptionController - handler method addPatientGetForm(), GET");
        return ADD_NEW_PATIENT_JSP;
    }


    /**
     * Is used to add a new patient to the database
     *
     * @param patient     the new patient
     * @param bindingResult the binding results
     * @param model         ModelMap model
     * @return the next view name to display or an error page in case of error occurred
     */
    @PostMapping("/add-patient")
    public String addPatient(@Valid @ModelAttribute("addPatient") Patient patient, BindingResult bindingResult, ModelMap model) {
        logger.info("MedHelper_LOGS: In HospitalReceptionController:  handler method addPatient()");
        logger.info("MedHelper_LOGS: New patient from JSP = " + patient.toString());

        if (BindingCheck.bindingResultCheck(bindingResult, model)) {
            return "error_page";
        }

        Patient newPatient = receptionService.addNewPatient(patient);
        logger.info("MedHelper_LOGS: the new patient added successfully(" + patient.toString() + ")");
        model.addAttribute("message", "The new patient added successfully: ");
        model.addAttribute("newPatient", newPatient);
        return ADD_NEW_PATIENT_JSP;
    }


    @GetMapping("/patient-details/{id}")
    public String seePatientDetails(@PathVariable("id") int id, ModelMap modelMap) {
        if (modelMap.isEmpty()) {
            modelMap.addAttribute("patient", receptionService.getPatientById(id));
        }
        return PATIENT_DETAILS_JSP;
    }








    @Autowired
    public HospitalReceptionController(HospitalReceptionService receptionService) {
        this.receptionService = receptionService;
    }
}
