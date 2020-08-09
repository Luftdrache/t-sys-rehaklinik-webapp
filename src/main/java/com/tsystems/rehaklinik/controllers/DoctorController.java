package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.*;
import com.tsystems.rehaklinik.util.BindingCheck;
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


/**
 * Processes requests from a doctor
 *
 * @author Julia Dalskaya
 */
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
    private static final String DIAGNOSIS_JSP = "doctor_add_diagnosis";
    private static final String PATIENT_PRESCRIPTIONS_JSP = "doctor_patient_prescriptions";
    private static final String ADD_PRESCRIPTION_JSP = "doctor_add_prescription";
    private static final String SHOW_SELECTED_PRESCRIPTION_JSP = "doctor_selected_prescription";
    private static final String EDIT_SELECTED_PRESCRIPTION_JSP = "doctor_edit_selected_prescription";
    private static final String EDIT_CLINICAL_DIAGNOSIS_JSP = "doctor_edit_clinical_diagnosis";
    private static final String SHOW_PATIENT_TREATMENT_EVENTS_JSP = "doctor_patient_treatment_events";

    private static final String MESSAGE = "message";


    @PostMapping("/edit-clinical-diagnosis")
    public String editSelectedDiagnosis(@Valid @ModelAttribute("editClinicalDiagnosis") ClinicalDiagnosisDTO clinicalDiagnosisDTO,
                                        BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editSelectedDiagnosis(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        doctorService.editClinicalDiagnosis(clinicalDiagnosisDTO);
        int medicalRecordId = clinicalDiagnosisDTO.getMedicalRecord().getMedicalRecordId();
        return "redirect:/doctor/medical-record/" + medicalRecordId;
    }



    //--------------------------------------------------------------------

    /**
     * Returns main doctor's page with his patient list on it. Start page.
     *
     * @param modelMap modelMap to add a list of doctor's patients into it
     * @return main doctor's page
     */
    @GetMapping("/start-page")
    public String showDoctorsPatients(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showDoctorsPatients(), GET");
        List<PatientShortViewDTO> doctorsPatients = doctorService.patients();
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

//--------------- Prescription ------------------------------

    /**
     * Adds new prescription
     *
     * @param prescriptionDTO PrescriptionDTO
     * @param bindingResult   binding result
     * @param modelMap        ModelMap with data for prescription details page
     * @return page with prescription details
     */
    @PostMapping("/add-prescription")
    public String addPrescription(@Valid @ModelAttribute("newPrescription") PrescriptionDTO prescriptionDTO,
                                  BindingResult bindingResult, ModelMap modelMap) {

        logger.info("MedHelper_LOGS: In DoctorController - handler method addPrescription(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        PrescriptionDTO newPrescription = doctorService.addPrescription(prescriptionDTO);
        logger.info("MedHelper_LOGS: DoctorController: addPrescription(POST): new prescription added: {} ", prescriptionDTO);
        modelMap.addAttribute("prescription", newPrescription);
        return SHOW_SELECTED_PRESCRIPTION_JSP;
    }


    /**
     * Shows form for new prescription adding
     *
     * @param id       patient's id
     * @param modelMap Model Map with patient's id
     * @return form for filling in information about a new prescription
     */
    @GetMapping("/add-prescription/{id}")
    public String addPrescription(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: InDoctorController - handler method addPrescriptionForm(), GET");
        modelMap.addAttribute("patientId", id);
        return ADD_PRESCRIPTION_JSP;
    }

    /**
     * Shows page with all patient's prescriptions
     *
     * @param id       patient's id
     * @param modelMap ModelMap with prescriptions list or a message about no result
     * @return page with all patient's prescriptions
     */
    @GetMapping("/show-prescription/{id}")
    public String showAllPrescriptionByPatientId(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showPrescriptionById(), GET");
        List<PrescriptionShortViewDTO> prescriptionDTOS = doctorService.findAllPatientsPrescription(id);
        if (!prescriptionDTOS.isEmpty()) {
            modelMap.addAttribute("patientPrescriptionsList", prescriptionDTOS);
            modelMap.addAttribute("patientId", id);
            logger.info("MedHelper_LOGS: The action showPrescriptionById() completed successfully");
        } else {
            modelMap.addAttribute("patientPrescriptionsMessage",
                    "INFO: Patient has no any prescriptions yet");
            logger.info("MedHelper_LOGS: The action showPrescriptionById() returned null");
        }
        return PATIENT_PRESCRIPTIONS_JSP;
    }


    /**
     * Deleting prescription by id
     *
     * @param prescriptionIdToDelete prescription id to delete
     * @param patientId              patient id to redirect back after deleting a prescription
     * @param modelMap               ModelMap
     * @return page with all patient's prescriptions
     */
    @PostMapping("/delete-prescription")
    public String deletePrescriptionById(@RequestParam("prescriptionIdToDelete") int prescriptionIdToDelete,
                                         @RequestParam("patient") int patientId,
                                         ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method deletePrescriptionById()");
        boolean deletingResult = doctorService.deletePrescription(prescriptionIdToDelete);
        if (!deletingResult) {
            logger.info("MedHelper_LOGS:  DoctorController: failed to delete prescription wint id = {}", prescriptionIdToDelete);
            modelMap.addAttribute("message", "Failed to delete prescription");
        } else {
            logger.info("MedHelper_LOGS: deletePrescriptionById() action was completed successfully");
        }
        return "redirect:/doctor/show-prescription/" + patientId;
    }


    /**
     * Returns form to edit selected prescription
     *
     * @param id       prescription id to edit a prescription
     * @param modelMap ModelMap with selected prescription data
     * @return form to edit selected prescription
     */
    @GetMapping("/edit-prescription/{id}")
    public String editSelectedPrescription(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editSelectedPrescription(), GET");
        PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO = doctorService.findPrescriptionById(id);
        if (prescriptionTreatmentPatternDTO == null) {
            logger.info("MedHelper_LOGS: In DoctorController: prescription with id = " + id + " not found");
        } else {
            modelMap.addAttribute("prescriptionToEdit", prescriptionTreatmentPatternDTO);
            logger.info("MedHelper_LOGS: prescription with id = " + id + " was found successfully");
        }
        return EDIT_SELECTED_PRESCRIPTION_JSP;
    }

    /**
     * Updates selected patient's prescriptions
     *
     * @param prescriptionTreatmentPatternDTO edited prescription data to update selected prescription
     * @param bindingResult                   Binding result
     * @param modelMap                        ModelMap
     * @return page with all patient's prescriptions
     */
    @PostMapping("/edit-prescription")
    public String editSelectedPrescription(@Valid @ModelAttribute("editedPrescription")
                                                   PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO,
                                           BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editSelectedPrescription(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        System.out.println(prescriptionTreatmentPatternDTO);
        int patientId = prescriptionTreatmentPatternDTO.getPatientId();
        doctorService.editPrescription(prescriptionTreatmentPatternDTO);

        return "redirect:/doctor/show-prescription/" + patientId;
    }


    /**
     * Cancels selected prescription. All treatment events become cancelled
     *
     * @param prescriptionIdToCancel prescription id
     * @param modelMap ModelMap
     * @return page with all patient's treatment events
     */
    @PostMapping("/cancel-prescription")
    public String cancelSelectedPrescription(@RequestParam("prescriptionIdToCancel") int prescriptionIdToCancel, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method cancelSelectedPrescription(), POST");
        boolean opCancellingResult = doctorService.cancelPrescription(prescriptionIdToCancel);
        return SHOW_PATIENT_TREATMENT_EVENTS_JSP;
    }


    //--------------- Medical Record -------------------------------

    /**
     * Shows doctor a patient medical record
     *
     * @param id       patient's id
     * @param modelMap modelMap to add a medical record info
     * @return page with a patient medical record
     */
    @GetMapping("/medical-record/{id}")
    public String showMedicalRecord(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showMedicalRecord(), GET. id = {}", id);

        MedicalRecordDTO medicalRecord = doctorService.getMedicalRecord(id);
        modelMap.addAttribute("medicalRecord", medicalRecord);
        return MEDICAL_RECORD_JSP;
    }


    /**
     * Return form to add new clinical diagnosis
     *
     * @param id       current medical record id
     * @param modelMap ModelMap with current medical record id
     * @return form to add new clinical diagnosis
     */
    @GetMapping("/medical-record/add-diagnosis/{id}")
    public String addDiagnosis(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method addDiagnosis(), GET");
        modelMap.addAttribute("medrec", id);
        return DIAGNOSIS_JSP;
    }


    /**
     * Adds new diagnosis to current medical record
     *
     * @param medRecordId       current medical record id
     * @param clinicalDiagnosis clinical diagnosis to add
     * @param bindingResult     binding result
     * @param modelMap          ModelMap with updated medical record
     * @return page with medical record with added clinical diagnosis
     */
    @PostMapping("/medical-record/add-diagnosis/{id}")
    public String addDiagnosis(@PathVariable("id") int medRecordId,
                               @Valid @ModelAttribute("addClinicalDiagnosis") ClinicalDiagnosisDTO clinicalDiagnosis,
                               BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method addDiagnosis(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        MedicalRecordDTO medicalRecord = doctorService.setNewDiagnosis(clinicalDiagnosis, medRecordId);
        modelMap.addAttribute("medicalRecord", medicalRecord);
        return MEDICAL_RECORD_JSP;
    }

    /**
     * Provides a form to edit selected clinical diagnosis
     *
     * @param id       clinical diagnosis id
     * @param modelMap ModelMap
     * @return page to edit selected clinical diagnosis
     */
    @GetMapping("/edit-clinical-diagnosis/{id}")
    public String editSelectedDiagnosis(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editSelectedDiagnosi(), GET");
        ClinicalDiagnosisDTO clinicalDiagnosisDTO = doctorService.getClinicalDiagnosisDTO(id);
        if (clinicalDiagnosisDTO == null) {
            modelMap.addAttribute(MESSAGE, "Sorry, you tried to edit a nonexistent clinical diagnosis");
            logger.info("MedHelper_LOGS: In DoctorController: clinical diagnosis with id = {} not found", id);
        } else {
            modelMap.addAttribute("CDToEdit", clinicalDiagnosisDTO);
            logger.info("MedHelper_LOGS: prescription with id = {} was found successfully", id);
        }
        return EDIT_CLINICAL_DIAGNOSIS_JSP;
    }


    /**
     * Deletes selected clinical diagnosis
     *
     * @param cDiagnosisIdToDelete clinical diagnosis id
     * @param medRecId             current medical record id
     * @param modelMap             Model Map
     * @return redirects to page with medical record data
     */
    @PostMapping("/delete-diagnosis")
    public String deleteClinicalDiagnosisById(@RequestParam("cDiagnosisIdToDelete") int cDiagnosisIdToDelete,
                                              @RequestParam("medRecId") int medRecId,
                                              ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method deleteClinicalDiagnosisById(), POST");
        boolean deletingResult = doctorService.deleteClinicalDiagnosisById(cDiagnosisIdToDelete);
        return "redirect:/doctor/medical-record/" + medRecId;
    }


    /**
     * Gives form to change hospitalisation data
     *
     * @param id       Medical record id
     * @param modelMap ModelMap with found hospitalisation data
     * @return form page to change hospitalisation data
     */
    @GetMapping("/medical-record/hospitalisation/{id}")
    public String editHospitalisation(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editHospitalisation(), GET");
        MedicalRecordDTO medicalRecord = doctorService.getMedicalRecord(id);
        modelMap.addAttribute("hospitalisationToEdit", medicalRecord);
        modelMap.addAttribute("medrec", id);
        return HOSPITALISATION_JSP;
    }


    /**
     * Changes data about patient hospitalisation
     *
     * @param hospitalisation changed data about patient hospitalisation
     * @param bindingResult   binding result
     * @param modelMap        updated medical record
     * @return page with updated medical record
     */
    @PostMapping("/medical-record/hospitalisation/")
    public String editHospitalisation(@Valid @ModelAttribute("editHospitalisation") MedicalRecordDTO hospitalisation,
                                      BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editHospitalisation(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        MedicalRecordDTO medicalRecord = doctorService.setHospitalisation(hospitalisation);
        logger.info("MedHelper_LOGS: In DoctorController: set new data about hospitalisation");
        modelMap.addAttribute("medrec", hospitalisation.getMedicalRecordId());
        modelMap.addAttribute("medicalRecord", medicalRecord);
        return MEDICAL_RECORD_JSP;
    }


    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
}
