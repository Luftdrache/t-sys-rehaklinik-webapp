package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.MedicalRecordDTO;
import com.tsystems.rehaklinik.dto.TreatmentEventDTO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import com.tsystems.rehaklinik.services.interfaces.DoctorService;
import com.tsystems.rehaklinik.services.interfaces.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Processes requests from a patient
 *
 * @author Julia Dalskaya
 */
@Controller
@RequestMapping("/patient")
public class PatientController {

    private Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final DoctorService doctorService;
    private final EmailService emailService;

    private static final String PATIENT_PAGE_JSP = "patient_page";

    /**
     * Returns to a patient information about his diagnosis and treatment events
     *
     * @param modelMap ModelMap
     * @return patient's information
     */
    @GetMapping("/personal-account")
    public String showPatientTreatmentDetails(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In PatientController - handler method showPatientTreatmentDetails()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int patientId = ((AuthenticationData) (authentication.getPrincipal())).getPatient().getPatientId();
        MedicalRecordDTO medicalRecordDTO = doctorService.getMedicalRecord(patientId);
        modelMap.addAttribute("medicalRecord", medicalRecordDTO);
        List<TreatmentEventDTO> treatmentEventDTOS = doctorService.findTreatmentEventsByPatientId(patientId);
        modelMap.addAttribute("treatmentEventList", treatmentEventDTOS);
        return PATIENT_PAGE_JSP;
    }

    @GetMapping("/send-email")
    public String sendInfoToPatientsEmail(ModelMap modelMap) {
        emailService.sendEmail();
        modelMap.addAttribute("message", "Information was send. Check your email.");
        return PATIENT_PAGE_JSP;
    }


    @Autowired
    public PatientController(DoctorService doctorService, EmailService emailService) {
        this.doctorService = doctorService;
        this.emailService = emailService;
    }
}
