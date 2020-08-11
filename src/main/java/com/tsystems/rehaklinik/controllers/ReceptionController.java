package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.util.BindingCheck;
import com.tsystems.rehaklinik.dto.EmployeeShortViewDTO;
import com.tsystems.rehaklinik.dto.PatientShortViewDTO;
import com.tsystems.rehaklinik.services.AdminService;
import com.tsystems.rehaklinik.services.ReceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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


    private static final String RECEPTION_START_PAGE_JSP = "reception_main_page";
    private static final String ADD_NEW_PATIENT_JSP = "reception_add_new_patient";
    private static final String PATIENT_DETAILS_JSP = "reception_patient_details";
    private static final String RECEPTION_ERROR_PAGE = "input_data_error_page";
    private static final String EDIT_PATIENT_JSP = "reception_edit_patient";
    private static final String DOCTORS_LIST_JSP = "reception_doctors";

    private static final String MESSAGE = "message";


    /**
     * Returns page for new patient adding
     *
     * @return form page for new patient adding
     */
    @GetMapping("/add-patient")
    public String addPatient() {
        logger.info("MedHelper_LOGS: In HospitalReceptionController - handler method addPatientGetForm(), GET");
        return ADD_NEW_PATIENT_JSP;
    }

    /**
     * Is used to add a new patient to the database
     *
     * @param patientDTO    a new patient data
     * @param bindingResult binding result
     * @param model         ModelMap model
     * @return the next view name to display or an error page in case of error occurred
     */
    @PostMapping("/add-patient")
    public String addPatient(@Valid @ModelAttribute("addPatient") PatientDTO patientDTO, BindingResult bindingResult, ModelMap model) {
        logger.info("MedHelper_LOGS: In HospitalReceptionController:  handler method addPatient(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, model)) {
            return RECEPTION_ERROR_PAGE;
        }
        logger.info("MedHelper_LOGS: New patient from JSP");
        PatientDTO newPatient = receptionService.addNewPatient(patientDTO);
        logger.info(String.format("MedHelper_LOGS: the new patient added successfully(surname = %s)", patientDTO.getSurname()));
        model.addAttribute(MESSAGE, "The new patient added successfully: ");
        model.addAttribute("patientInfo", newPatient);
        return PATIENT_DETAILS_JSP;
    }

    /**
     * Shows page to edit patient's data
     *
     * @param id       patient's id
     * @param modelMap ModelMap
     * @return page to edit patient's data
     */
    @GetMapping("/edit-patient-data/{id}")
    public String editPatientData(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In ReceptionController - handler method editPatientDataForm(), GET");
        PatientDTO patientToEdit = receptionService.getPatientById(id);
        modelMap.addAttribute("patientToEdit", patientToEdit);
        return EDIT_PATIENT_JSP;
    }


    /**
     * Takes edited data of the patient to save them
     *
     * @param patientDTO    edited patient's data
     * @param bindingResult binding result
     * @param modelMap      ModelMap
     * @return page with edited patient's details or message about wrong attempt to edit a nonexistent record in database
     */
    @PostMapping("/edit")
    public String editPatientData(@Valid @ModelAttribute("editPatient") PatientDTO patientDTO, BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In ReceptionController  - handler method editPatientData(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return RECEPTION_ERROR_PAGE;
        }
        PatientDTO patientInfo = receptionService.editPatient(patientDTO);
        if (patientInfo == null) {
            logger.info("MedHelper_LOGS: Error in the editing process of the patient");
            modelMap.addAttribute(MESSAGE, "Attempt to edit a nonexistent patient");
            return EDIT_PATIENT_JSP;
        }
        logger.info("MedHelper_LOGS: Patient edited successfully");
        modelMap.addAttribute(MESSAGE, "Patient edited successfully: ");
        modelMap.addAttribute("patientInfo", patientInfo);
        return PATIENT_DETAILS_JSP;
    }


    /**
     * Deletes patient with specified id
     *
     * @param patientIdToDelete patient id to delete
     * @param redirectAttributes    redirect attributes
     * @return redirects to main hospital reception page
     */
    @PostMapping("/delete-patient")
    public RedirectView deletePatientById(@RequestParam("patientIdToDelete") int patientIdToDelete,  RedirectAttributes redirectAttributes) {
        logger.info("MedHelper_LOGS: In ReceptionController - handler method deletePatientById()");
        RedirectView redirectView = new RedirectView("/reception/start-page", true);
        String deletePatientByIdMessage = receptionService.deletePatientById(patientIdToDelete);
        List<PatientShortViewDTO> allPatients = receptionService.showAllPatients();
        redirectAttributes.addFlashAttribute(MESSAGE, deletePatientByIdMessage);
        redirectAttributes.addFlashAttribute("allPatients", allPatients);
        logger.info("MedHelper_LOGS: Result of deletePatientById action is {}", deletePatientByIdMessage);
        return redirectView;
    }


    /**
     * Shows page with patient details
     *
     * @param id       patient's id
     * @param modelMap ModelMap
     * @return page with patient details
     */
    @GetMapping("/patient-details/{id}")
    public String seePatientDetails(@PathVariable("id") int id, ModelMap modelMap) {

        PatientDTO patientDetails = receptionService.getPatientById(id);
        if (patientDetails != null) {
            logger.info("MedHelper_LOGS: In ReceptionController: seePatientDetails(), GET: patient found successfully");
            modelMap.addAttribute("patientInfo", patientDetails);
        } else {
            logger.info("MedHelper_LOGS: In ReceptionController: seePatientDetails(), GET: " +
                    "employee with id = {} not found", id);
            modelMap.addAttribute(MESSAGE, "There is no data about a patient with such id in the database");
        }
        return PATIENT_DETAILS_JSP;
    }


    /**
     * Provides a list of all doctors to assign a doctor to a patient
     *
     * @param id       patient's id
     * @param modelMap ModelMap
     * @return list with doctors to appoint
     */
    @GetMapping("/appoint-doctor")
    public String chooseAttendingDoctor(@RequestParam("patientId") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In ReceptionController - handler method chooseAttendingDoctor(), GET");
        List<EmployeeShortViewDTO> doctors = receptionService.getAllDoctors();
        modelMap.addAttribute("doctors", doctors);
        modelMap.addAttribute("patient", id);
        return DOCTORS_LIST_JSP;
    }


    /**
     * Returns list of all patients found in database
     *
     * @param modelMap ModelMap
     * @return list of all patients found in database
     */
    @GetMapping("/start-page")
    public String showAllPatients(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In HospitalReceptionController:  handler method showAllPatients(), GET");
        if (modelMap.isEmpty()) {
            List<PatientShortViewDTO> allPatients = receptionService.showAllPatients();
            if (!allPatients.isEmpty()) {
                modelMap.addAttribute("allPatients", allPatients);
                logger.info("MedHelper_LOGS: The action showAllPatients() completed successfully");
            } else {
                modelMap.addAttribute(MESSAGE, "INFO: There is no information about patients in the database");
                logger.info("MedHelper_LOGS: The action showAllPatients() returned nothing");
            }
        }
        return RECEPTION_START_PAGE_JSP;
    }

    /**
     * Appoints doctor to patient
     *
     * @param doctorId  doctor's id
     * @param patientId patient's id
     * @param modelMap  ModelMap
     * @return page with patient's details
     */
    @PostMapping("/appoint-doctor")
    public String setAttendingDoctor(@RequestParam("doctorId") int doctorId,
                                     @RequestParam("patientId") int patientId, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In ReceptionController - handler method setAttendingDoctor(), POST");

        PatientDTO patientWithDoctor = receptionService.setAttendingDoctor(doctorId, patientId);
        modelMap.addAttribute("patientInfo", patientWithDoctor);
        modelMap.addAttribute("message", "Attending doctor is appointed");
        return PATIENT_DETAILS_JSP;
    }


    /**
     * Returns a patient/patients with specified surname
     *
     * @param surname  Patient's surname
     * @param modelMap ModelMap
     * @return list with a patient/patients with specified surname
     */
    @GetMapping("/find-patient-by-surname")
    public RedirectView findPatientBySurname(@RequestParam("surname") String surname, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        logger.info("MedHelper_LOGS: In ReceptionController - handler method findPatientBySurname()");
        RedirectView redirectView = new RedirectView("/reception/start-page", true);
        List<PatientShortViewDTO> patientsFoundBySurname = receptionService.findPatientBySurname(surname);
        if (patientsFoundBySurname != null) {
            redirectAttributes.addFlashAttribute("allPatients", patientsFoundBySurname);
            logger.info("MedHelper_LOGS: The patient(-s) with surname = {} was(were) found successfully", surname);
            for (PatientShortViewDTO patient : patientsFoundBySurname) {
                logger.info(patient.toString());
            }
        } else {
            redirectAttributes.addFlashAttribute(MESSAGE, "There is no patient with surname = " + surname +
                    "  in database");
            logger.info("MedHelper_LOGS: There is no patient with surname = {} in database", surname);
        }
        return redirectView;
    }


    @Autowired
    public ReceptionController(ReceptionService receptionService, AdminService adminService) {
        this.receptionService = receptionService;
    }
}
