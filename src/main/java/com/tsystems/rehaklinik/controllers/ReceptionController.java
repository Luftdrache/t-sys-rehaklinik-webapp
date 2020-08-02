package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.util.BindingCheck;
import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.dto.PatientShortViewDTO;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.services.AdminService;
import com.tsystems.rehaklinik.services.ReceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Processes requests from hospital reception
 *
 * @author Julia Dalskaya
 */
@Controller
@RequestMapping("/reception")
public class ReceptionController {

    private Logger logger = LoggerFactory.getLogger(ReceptionController.class);
    private final ReceptionService receptionService;
    private final AdminService adminService;


    private static final String RECEPTION_START_PAGE_JSP = "reception_main_page";
    private static final String ADD_NEW_PATIENT_JSP = "reception_add_new_patient";
    private static final String PATIENT_DETAILS_JSP = "reception_patient_details";
    private static final String RECEPTION_ERROR_PAGE = "input_data_error_page";
    private static final String EDIT_PATIENT_JSP = "reception_edit_patient";
    private static final String DOCTORS_LIST_JSP = "reception_doctors";




    //IN PROGRESS
    @PostMapping("/appoint-doctor")
    public String setAttendingDoctor(@RequestParam("doctorId") int doctorId,
                                     @RequestParam("patientId") int patientId, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In ReceptionController - handler method setAttendingDoctor(), POST");
        Patient patient = receptionService.getPatientById(patientId);
        patient.setAttendingDoctorId(adminService.getEmployee(doctorId));
        Patient patientWithDoctor = receptionService.editPatient(patient);
        modelMap.addAttribute("patientInfo", patientWithDoctor);
        modelMap.addAttribute("message", "Attending doctor is appointed");
        return PATIENT_DETAILS_JSP;
    }


    @GetMapping("/appoint-doctor")
    public String chooseAttendingDoctor(@RequestParam("patientId") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In ReceptionController - handler method chooseAttendingDoctor(), GET");
        List<EmployeeShortViewDTO> doctors = receptionService.getAllDoctors();
        modelMap.addAttribute("doctors", doctors);
        modelMap.addAttribute("patient", id);
        return DOCTORS_LIST_JSP;
    }


    @GetMapping("/edit-patient-data/{id}")
    public String editPatientData(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In ReceptionController - handler method editPatientDataForm(), GET");
        Patient patientToEdit = receptionService.getPatientById(id);
        modelMap.addAttribute("patientToEdit", patientToEdit);
        return EDIT_PATIENT_JSP;
    }


    @PostMapping("/edit")
    public String editPatientData(@Valid @ModelAttribute("editPatient") Patient patient, BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In ReceptionController  - handler method editPatientData(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return RECEPTION_ERROR_PAGE;
        }
        Patient patientInfo = receptionService.editPatient(patient);
        if (patientInfo == null) {
            logger.info("MedHelper_LOGS: Error in the editing process of the patient");
            return EDIT_PATIENT_JSP;
        }
        logger.info("MedHelper_LOGS: Patient edited successfully");
        modelMap.addAttribute("message", "Patient edited successfully: ");
        modelMap.addAttribute("patientInfo", patientInfo);
        return PATIENT_DETAILS_JSP;
    }


    @GetMapping("/patient-details/{id}")
    public String seePatientDetails(@PathVariable("id") int id, ModelMap modelMap) {
        if (modelMap.isEmpty()) {
            modelMap.addAttribute("patientInfo", receptionService.getPatientById(id));
        }
        return PATIENT_DETAILS_JSP;
    }

    /**
     * Returns list of all patients found in database
     *
     * @param modelMap
     * @return list of all patients found in database
     */
    @GetMapping("/start-page")
    public String showAllPatients(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In HospitalReceptionController:  handler method showAllPatients(), GET");
        List<PatientShortViewDTO> allPatients = receptionService.showAllPatients();
        if (allPatients != null) {
            modelMap.addAttribute("allPatients", allPatients);
            logger.info("MedHelper_LOGS: The action showAllPatients() completed successfully");
        } else {
            modelMap.addAttribute("message", "INFO: There is no information about patients in the database");
            logger.info("MedHelper_LOGS: The action showAllPatients() returned nothing");
        }
        return RECEPTION_START_PAGE_JSP;
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
     * @param patient       the new patient
     * @param bindingResult the binding results
     * @param model         ModelMap model
     * @return the next view name to display or an error page in case of error occurred
     */
    @PostMapping("/add-patient")
    public String addPatient(@Valid @ModelAttribute("addPatient") Patient patient, BindingResult bindingResult, ModelMap model) {
        logger.info("MedHelper_LOGS: In HospitalReceptionController:  handler method addPatient()");
        logger.info("MedHelper_LOGS: New patient from JSP = " + patient.toString());

        if (BindingCheck.bindingResultCheck(bindingResult, model)) {
            return RECEPTION_ERROR_PAGE;
        }

        Patient newPatient = receptionService.addNewPatient(patient);
        logger.info("MedHelper_LOGS: the new patient added successfully(" + patient.toString() + ")");
        model.addAttribute("message", "The new patient added successfully: ");
        model.addAttribute("newPatient", newPatient);
        return ADD_NEW_PATIENT_JSP;
    }


    /**
     * Deletes patient with specified id
     *
     * @param patientIdToDelete Patient id to delete
     * @param modelMap          modelMap
     * @return redirects to main hospital reception page
     */
    @PostMapping("/delete-patient")
    public String deletePatientById(@RequestParam("patientIdToDelete") int patientIdToDelete, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In AdminController - handler method deletePatientById()");
        String deletePatientByIdMessage = receptionService.deletePatientById(patientIdToDelete);
        modelMap.addAttribute("message", deletePatientByIdMessage);
        logger.info("MedHelper_LOGS: Result of deletePatientById action is " + deletePatientByIdMessage);
        return "redirect:/reception/start-page";
    }

    @Autowired
    public ReceptionController(ReceptionService receptionService, AdminService adminService) {
        this.receptionService = receptionService;
        this.adminService = adminService;
    }
}
