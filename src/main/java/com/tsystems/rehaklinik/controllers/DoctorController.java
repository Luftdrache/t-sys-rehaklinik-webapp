package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import com.tsystems.rehaklinik.services.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private Logger logger = LoggerFactory.getLogger(DoctorController.class);
    private final DoctorService doctorService;

    private static final String MAIN_DOCTOR_JSP = "doctor_main_page";
    private static final String MEDICAL_RECORD_JSP = "doctor_medical_record";
    private static final String EDIT_MEDICAL_RECORD_JSP = "doctor_edit_medical_record";


    /**
     * Returns main doctor's page with his patient list on it
     * @param modelMap modelMap to add a list of doctor's patients into it
     * @return main doctor's page
     */
    @GetMapping("/start-page")
    public String showDoctorsPatients(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showDoctorsPatients(), GET");
        List<PatientDTO> doctorsPatients = doctorService.patients();
        if (doctorsPatients != null) {
            logger.info("MedHelper_LOGS: The action showDoctorsPatients() completed successfully");
            modelMap.addAttribute("doctorsPatients", doctorsPatients);
        } else {
            modelMap.addAttribute("messageToDoctor",
                    "INFO: You don't have any patients yet");
            logger.info("MedHelper_LOGS: The action showDoctorsPatients() returned null");
        }
        return MAIN_DOCTOR_JSP;
    }


    /**
     * Shows doctor a patient medical record
     * @param id patient's id
     * @param modelMap modelMap to add a medical record info
     * @return page with a patient medical record
     */
    @GetMapping("/medical-record/{id}")
    public String showMedicalRecord(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showMedicalRecord(), GET");
        MedicalRecord medicalRecord = doctorService.getMedicalRecord(id);
        modelMap.addAttribute("medicalRecord", medicalRecord);
        return MEDICAL_RECORD_JSP;
    }


    @GetMapping("/medical-record/edit/{id}")
    public String editMedicalRecordForm(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editMedicalRecordForm(), GET");
        MedicalRecord medicalRecord = doctorService.getMedicalRecord(id);
        modelMap.addAttribute("medicalRecordToEdit", medicalRecord);
        return EDIT_MEDICAL_RECORD_JSP;
    }



    @PostMapping("/medical-record")
    public String fillOutMedicalRecord(ModelMap modelMap) {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @GetMapping("/add-prescription")
    public String addPrescriptionForm(ModelMap modelMap) {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @PostMapping("/add-prescription")
    public String addPrescription(ModelMap modelMap) {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @GetMapping("/show-prescription")
    public String showPrescriptionById(ModelMap modelMap) {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @PostMapping("/discharge-patient")
    public String dischargePatient() {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
}
