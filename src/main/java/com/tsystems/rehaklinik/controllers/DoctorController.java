package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.Util.BindingCheck;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import com.tsystems.rehaklinik.services.DoctorService;
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
@RequestMapping("/doctor")
public class DoctorController {

    private Logger logger = LoggerFactory.getLogger(DoctorController.class);
    private final DoctorService doctorService;

    private static final String MAIN_DOCTOR_JSP = "doctor_main_page";
    private static final String MEDICAL_RECORD_JSP = "doctor_medical_record";
    private static final String EDIT_MEDICAL_RECORD_JSP = "doctor_edit_medical_record";
    private static final String ERROR_PAGE_JSP = "input_data_error_page";
    private static final String HOSPITALISATION_JSP = "doctor_hospitalisation";

    /**
     * Returns main doctor's page with his patient list on it
     *
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
     *
     * @param id       patient's id
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

    /**
     * Shows page to edit patient medical record
     *
     * @param id       Medical Record id
     * @param modelMap ModelMap with found medical record
     * @return form to edit current medical record
     */
    @GetMapping("/medical-record/edit/{id}")
    public String editMedicalRecordForm(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editMedicalRecordForm(), GET");
        MedicalRecord medicalRecord = doctorService.getMedicalRecord(id);
        modelMap.addAttribute("medicalRecordToEdit", medicalRecord);
        return EDIT_MEDICAL_RECORD_JSP;
    }

//
//    @PostMapping("/edit")
//    public String fillOutMedicalRecord(@Valid @ModelAttribute("editedMedRecord") MedicalRecord medRecord,
//                                    BindingResult bindingResult, ModelMap modelMap) {
//        logger.info("MedHelper_LOGS: In DoctorController - handler method editMedicalRecord(), POST");
//        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
//            return ERROR_PAGE_JSP;
//        }
//        Employee editedEmployee = adminService.editEmployee(employee);
//        if (editedEmployee == null) {
//            logger.info("MedHelper_LOGS: Error in the editing process of the employee" + editedEmployee.toString() + ")");
//            return EDIT_EMPLOYEE_JSP;
//        }
//        logger.info("MedHelper_LOGS: Employee edited successfully(" + editedEmployee.toString() + ")");
//        modelMap.addAttribute("message", "Employee edited successfully: ");
//        modelMap.addAttribute("employee", editedEmployee);
//        return EMPLOYEE_DETAILS_JSP;
//    }

    /**
     * Gives form to change hospitalisation data
     * @param id Medical record id
     * @param modelMap
     * @return form page to change hospitalisation data
     */
    @GetMapping("/medical-record/hospitalisation/{id}")
    public String editHospitalisationForm(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editHospitalisationForm(), GET");
        MedicalRecord medicalRecord = doctorService.getMedicalRecord(id);
        modelMap.addAttribute("hospitalisationToEdit", medicalRecord);
        modelMap.addAttribute("medrec", id);
        return HOSPITALISATION_JSP;
    }


    @PostMapping("/medical-record/hospitalisation/")
    public String editHospitalisation(@Valid @ModelAttribute("editHospitalisation") MedicalRecord hospitalisation,
                                      BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editHospitalisation(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        MedicalRecord medicalRecord = doctorService.setHospitalisation(hospitalisation);
        logger.info("MedHelper_LOGS: In DoctorController: set new data about hospitalisation");
        modelMap.addAttribute("medicalRecord", medicalRecord);
        return MEDICAL_RECORD_JSP;
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
