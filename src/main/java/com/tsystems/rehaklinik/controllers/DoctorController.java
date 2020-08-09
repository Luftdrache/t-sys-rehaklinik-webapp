package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.*;
import com.tsystems.rehaklinik.util.BindingCheck;
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
    private static final String SHOW_SELECTED_PRESCRIPTION = "doctor_selected_prescription";
    private static final String EDIT_SELECTED_PRESCRIPTION = "doctor_edit_selected_prescription";


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




    @PostMapping("/cancel-prescription")
    public String cancelSelectedPrescription(@RequestParam("prescriptionIdToCancel") int prescriptionIdToCancel, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method cancelSelectedPrescription(), POST");
        boolean opCancellingResult = doctorService.cancelPrescription(prescriptionIdToCancel);
        return null; //Вернуть страницу с событиями пациента (создать)
    }


//    @PostMapping("/delete-prescription")
//    public String deletePrescriptionById(@RequestParam("prescriptionIdToDelete") int prescriptionIdToDelete,
//                                         @RequestParam("patient") int patientId,
//                                         ModelMap modelMap) {
//        logger.info("MedHelper_LOGS: In DoctorController - handler method deletePrescriptionById()");
//        boolean deletingResult = doctorService.deletePrescription(prescriptionIdToDelete);
//        if (!deletingResult) {
//            logger.info("MedHelper_LOGS:  DoctorController: failed to delete prescription wint id = " + prescriptionIdToDelete);
//            modelMap.addAttribute("message", "Failed to delete prescription");
//        } else {
//            logger.info("MedHelper_LOGS: deletePrescriptionById() action was completed successfully");
//        }
//        return "redirect:/doctor/show-prescription/" + patientId;
//    }


//**********************************************************************************************************************
//    @GetMapping("/medical-record/diagnosis/{id}")
//    public String fillOutDiagnosis(@PathVariable("id") int id, ModelMap modelMap) {
//        logger.info("MedHelper_LOGS: In DoctorController - handler method fillOutDiagnosis(), GET");
//        MedicalRecord medicalRecord = doctorService.getMedicalRecord(id);
//        System.out.println(medicalRecord);
//        modelMap.addAttribute("medrec", id);
//        modelMap.addAttribute("diagnosisToEdit", medicalRecord);
//        return DIAGNOSIS_JSP;
//    }

//
//    @PostMapping("/medical-record/diagnosis")
//    public String fillOutDiagnosis(@Valid @ModelAttribute("editHospitalisation") MedicalRecord clinicalDiagnosis,
//                                   BindingResult bindingResult, ModelMap modelMap) {
//        logger.info("MedHelper_LOGS: In DoctorController - handler method fillOutDiagnosis(), POST");
//        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
//            return ERROR_PAGE_JSP;
//        }
//        MedicalRecord medicalRecord = doctorService.updateMedicalRecord(clinicalDiagnosis);
//        logger.info("MedHelper_LOGS: In DoctorController: new diagnosis set or changed the old one");
//        modelMap.addAttribute("medrec", medicalRecord.getMedicalRecordId());
//        modelMap.addAttribute("medicalRecord", medicalRecord);
//        System.out.println(medicalRecord);
//        return MEDICAL_RECORD_JSP;
//    }


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

//**********************************************************************************************************************

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
        return EDIT_SELECTED_PRESCRIPTION;
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
            logger.info("MedHelper_LOGS:  DoctorController: failed to delete prescription wint id = " + prescriptionIdToDelete);
            modelMap.addAttribute("message", "Failed to delete prescription");
        } else {
            logger.info("MedHelper_LOGS: deletePrescriptionById() action was completed successfully");
        }
        return "redirect:/doctor/show-prescription/" + patientId;
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
     * Shows page to edit patient medical record
     *
     * @param id       Medical Record id
     * @param modelMap ModelMap with found medical record
     * @return form to edit current medical record
     */
    @GetMapping("/medical-record/edit/{id}")
    public String editMedicalRecord(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editMedicalRecord(), GET");
        MedicalRecordDTO medicalRecord = doctorService.getMedicalRecord(id);
        modelMap.addAttribute("medicalRecordToEdit", medicalRecord);
        return EDIT_MEDICAL_RECORD_JSP;
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
    public String editHospitalisation(@Valid @ModelAttribute("editHospitalisation") MedicalRecord hospitalisation,
                                      BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editHospitalisation(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        MedicalRecord medicalRecord = doctorService.setHospitalisation(hospitalisation);
        logger.info("MedHelper_LOGS: In DoctorController: set new data about hospitalisation");
        modelMap.addAttribute("medrec", hospitalisation.getMedicalRecordId());
        modelMap.addAttribute("medicalRecord", medicalRecord);
        return MEDICAL_RECORD_JSP;
    }


//***********************************************************************************************
//********************** DONE *******************************************************************
//***********************************************************************************************
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
        logger.info("MedHelper_LOGS: DoctorController: addPrescription(POST): new prescription added: " + prescriptionDTO);
        modelMap.addAttribute("prescription", newPrescription);
        return SHOW_SELECTED_PRESCRIPTION;
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

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
}
