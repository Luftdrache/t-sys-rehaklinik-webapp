package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.util.BindingCheck;
import com.tsystems.rehaklinik.dto.PatientDTO;
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
    private static final String DIAGNOSIS_JSP = "doctor_diagnosis";
    private static final String PATIENT_PRESCRIPTIONS_JSP = "doctor_patient_prescription";
    private static final String ADD_PRESCRIPTION_JSP = "doctor_add_prescription";

    //In process
    @GetMapping("/add-prescription/{id}")
    public String addPrescription(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: InDoctorController - handler method addPrescriptionForm(), GET");
        modelMap.addAttribute("patientId", id);
        return ADD_PRESCRIPTION_JSP;
    }

    //In process
    @PostMapping("/add-prescription")
    public String addPrescription(@Valid @ModelAttribute("editHospitalisation") MedicalRecord clinicalDiagnosis,
                                  BindingResult bindingResult, ModelMap modelMap) {

        logger.info("MedHelper_LOGS: In DoctorController - handler method addPrescription(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }

        return "";
    }


    /**
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/medical-record/diagnosis/{id}")
    public String fillOutDiagnosisForm(@PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method fillOutDiagnosisForm(), GET");
        MedicalRecord medicalRecord = doctorService.getMedicalRecord(id);
        System.out.println(medicalRecord);
        modelMap.addAttribute("medrec", id);
        modelMap.addAttribute("diagnosisToEdit", medicalRecord);
        return DIAGNOSIS_JSP;
    }


    @PostMapping("/medical-record/diagnosis")
    public String fillOutDiagnosis(@Valid @ModelAttribute("editHospitalisation") MedicalRecord clinicalDiagnosis,
                                   BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method fillOutDiagnosis(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        MedicalRecord medicalRecord = doctorService.updateMedicalRecord(clinicalDiagnosis);
        logger.info("MedHelper_LOGS: In DoctorController: new diagnosis set or changed the old one");
        modelMap.addAttribute("medrec", medicalRecord.getMedicalRecordId());
        modelMap.addAttribute("medicalRecord", medicalRecord);
        System.out.println(medicalRecord);
        return MEDICAL_RECORD_JSP;
    }


//    @PostMapping("/add-prescription")
//    public String addPrescription(ModelMap modelMap) {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }

//    @PostMapping("/discharge-patient")
//    public String dischargePatient() {
//        throw new UnsupportedOperationException("Not implemented yet");
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

    @GetMapping("/show-prescription/{id}")
    public String showPrescriptionById(@PathVariable("id") int id, ModelMap modelMap) {
        return PATIENT_PRESCRIPTIONS_JSP;
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
        System.out.println(medicalRecord);
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


    /**
     * Gives form to change hospitalisation data
     *
     * @param id       Medical record id
     * @param modelMap ModelMap with found hospitalisation data
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


    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
}
