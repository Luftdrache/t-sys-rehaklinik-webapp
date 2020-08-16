package com.tsystems.rehaklinik.controllers;

import com.tsystems.rehaklinik.dto.*;
import com.tsystems.rehaklinik.services.NurseService;
import com.tsystems.rehaklinik.util.BindingCheck;
import com.tsystems.rehaklinik.services.DoctorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
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
    private final NurseService nurseService;

    private static final String MAIN_DOCTOR_JSP = "doctor_main_page";
    private static final String MEDICAL_RECORD_JSP = "doctor_medical_record";
    private static final String ERROR_PAGE_JSP = "input_data_error_page";
    private static final String HOSPITALISATION_JSP = "doctor_hospitalisation";
    private static final String DIAGNOSIS_JSP = "doctor_add_diagnosis";
    private static final String PATIENT_PRESCRIPTIONS_JSP = "doctor_patient_prescriptions";
    private static final String ADD_PRESCRIPTION_JSP = "doctor_add_prescription";
    private static final String SHOW_SELECTED_PRESCRIPTION_JSP = "doctor_selected_prescription";
    private static final String EDIT_SELECTED_PRESCRIPTION_JSP = "doctor_edit_selected_prescription";
    private static final String EDIT_CLINICAL_DIAGNOSIS_JSP = "doctor_edit_clinical_diagnosis";
    private static final String SHOW_PATIENT_TREATMENT_EVENTS_JSP = "doctor_patient_treatment_events";
    private static final String TREATMENT_EVENT_DETAILS_JSP = "nurse_event_details";

    private static final String MESSAGE = "message";
    private static final String TREATMENT_EVENT_LIST = "treatmentEventList";
    private static final String MEDICAL_RECORD = "medicalRecord";
    private static final String MEDICAL_RECORD_ID = "medrec";
    private static final int BAD_ID = 0;

    /**
     * Returns main doctor's page with his patient list on it. Start page.
     *
     * @param modelMap modelMap to add a list of doctor's patients into it
     * @return main doctor's page
     */
    @GetMapping("/start-page")
    public String showDoctorsPatients(ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showDoctorsPatients(), GET");
        List<PatientShortViewDTO> doctorsPatients = doctorService.findPatients();
        if (modelMap.isEmpty()) {
            if (!doctorsPatients.isEmpty()) {
                logger.info("MedHelper_LOGS: The action showDoctorsPatients() completed successfully");
                modelMap.addAttribute("doctorsPatients", doctorsPatients);
            } else {
                modelMap.addAttribute(MESSAGE,
                        "INFO: You don't have any patients yet");
                logger.info("MedHelper_LOGS: The action showDoctorsPatients() returned null");
            }
        }
        return MAIN_DOCTOR_JSP;
    }


//--------------- Prescription ------------------------------

    /**
     * Shows form for new prescription adding
     *
     * @param id       medical record / patient id
     * @param request  HttpServletRequest
     * @param modelMap Model Map with patient's id
     * @return form for filling in information about a new prescription
     */
    @GetMapping("/add-prescription/{id}")
    public String addPrescription(
            @PathVariable("id") int id, HttpServletRequest request, ModelMap modelMap) throws NoHandlerFoundException {
        logger.info("MedHelper_LOGS: InDoctorController - handler method addPrescription(), GET");
        MedicalRecordDTO medicalRecord = doctorService.getMedicalRecord(id);
        if (medicalRecord.getMedicalRecordId() == BAD_ID) {
            logger.info("MedHelper_LOGS: In DoctorController: medical record with the specified id not found");
            throw new NoHandlerFoundException(
                    request.getMethod(), request.getRequestURI(), new ServletServerHttpRequest(request).getHeaders());
        }
        modelMap.addAttribute("patientId", id);
        return ADD_PRESCRIPTION_JSP;
    }


    /**
     * Adds new prescription
     *
     * @param prescriptionDTO PrescriptionDTO
     * @param bindingResult   binding result
     * @param modelMap        ModelMap
     * @return page with all patient's prescriptions
     */
    @PostMapping("/add-prescription")
    public String addPrescription(@Valid @ModelAttribute("newPrescription") PrescriptionDTO prescriptionDTO,
                                  @ModelAttribute("patient.patientId") int id,
                                  BindingResult bindingResult, ModelMap modelMap) {

        logger.info("MedHelper_LOGS: In DoctorController - handler method addPrescription(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        PrescriptionDTO newPrescription = doctorService.addPrescription(prescriptionDTO);
        if (newPrescription != null) {
            logger.info("MedHelper_LOGS: DoctorController: addPrescription(POST): new prescription added: {} ",
                    prescriptionDTO);
        } else {
            logger.info("MedHelper_LOGS: DoctorController: addPrescription(POST): failed attempt to add " +
                    "new prescription");
        }
        return "redirect:/doctor/show-prescription/" + id;
    }


    /**
     * Show page with selected prescription details
     *
     * @param id       prescription id
     * @param request  HttpServletRequest
     * @param modelMap ModelMap
     * @return page with prescription details
     */
    @GetMapping("/prescription-details/{id}")
    public String showPrescriptionDetails(
            @PathVariable("id") int id, HttpServletRequest request, ModelMap modelMap) throws NoHandlerFoundException {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showPrescriptionDetails(), GET");
        PrescriptionDetailsDTO prescriptionDetails = doctorService.getPrescriptionDetails(id);
        if (prescriptionDetails.getPrescriptionId() == BAD_ID) {
            logger.info("MedHelper_LOGS: In DoctorController: medical record with the specified id not found");
            throw new NoHandlerFoundException(
                    request.getMethod(), request.getRequestURI(), new ServletServerHttpRequest(request).getHeaders());
        }
        modelMap.addAttribute(MEDICAL_RECORD_ID, prescriptionDetails.getMedicalRecordId());
        modelMap.addAttribute("prescription", prescriptionDetails);
        return SHOW_SELECTED_PRESCRIPTION_JSP;
    }


    /**
     * Shows page with all patient's prescriptions
     *
     * @param id       patient's id
     * @param request  HttpServletRequest
     * @param modelMap ModelMap with prescriptions list or a message about no result
     * @return page with all patient's prescriptions
     * @throws NoHandlerFoundException if requested is not exists
     */
    @GetMapping("/show-prescription/{id}")
    public String showAllPrescriptionByPatientId(
            @PathVariable("id") int id, HttpServletRequest request, ModelMap modelMap) throws NoHandlerFoundException {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showPrescriptionById(), GET");
        MedicalRecordDTO medicalRecord = doctorService.getMedicalRecord(id);
        if (medicalRecord.getMedicalRecordId() == BAD_ID) {
            logger.info("MedHelper_LOGS: In DoctorController: medical record with the specified id not found");
            throw new NoHandlerFoundException(
                    request.getMethod(), request.getRequestURI(), new ServletServerHttpRequest(request).getHeaders());
        }
        List<PrescriptionShortViewDTO> prescriptionDTOS = doctorService.findAllPatientsPrescription(id);
        if (!prescriptionDTOS.isEmpty()) {
            modelMap.addAttribute("patientPrescriptionsList", prescriptionDTOS);
            logger.info("MedHelper_LOGS: The action showPrescriptionById() completed successfully");
        } else {
            modelMap.addAttribute(MESSAGE,
                    "INFO: Patient has no any prescriptions yet");
            logger.info("MedHelper_LOGS: The action showPrescriptionById() returned null");
        }
        modelMap.addAttribute("patientId", id);
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
    public String deletePrescriptionById(@ModelAttribute("prescriptionIdToDelete") int prescriptionIdToDelete,
                                         @ModelAttribute("patient") int patientId,
                                         ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method deletePrescriptionById()");
        boolean deletingResult = doctorService.deletePrescription(prescriptionIdToDelete);
        if (!deletingResult) {
            logger.info("MedHelper_LOGS:  DoctorController: failed to delete prescription wint id = {}", prescriptionIdToDelete);
            modelMap.addAttribute(MESSAGE, "Failed to delete prescription");
        } else {
            logger.info("MedHelper_LOGS: deletePrescriptionById() action was completed successfully");
        }
        return "redirect:/doctor/show-prescription/" + patientId;
    }


    /**
     * Returns form to edit selected prescription
     *
     * @param id       prescription id to edit a prescription
     * @param request  HttpServletRequest
     * @param modelMap ModelMap with selected prescription data
     * @return form to edit selected prescription
     * @throws NoHandlerFoundException if request path is incorrect
     */
    @GetMapping("/edit-prescription/{id}")
    public String editSelectedPrescription(
            @PathVariable("id") int id, HttpServletRequest request, ModelMap modelMap) throws NoHandlerFoundException {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editSelectedPrescription(), GET");
        PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO = doctorService.findPrescriptionById(id);
        if (prescriptionTreatmentPatternDTO.getPrescriptionId() == BAD_ID) {
            logger.info("MedHelper_LOGS: In DoctorController: prescription with id = {} not found", id);
            throw new NoHandlerFoundException(
                    request.getMethod(), request.getRequestURI(), new ServletServerHttpRequest(request).getHeaders());
        } else {
            modelMap.addAttribute("prescriptionToEdit", prescriptionTreatmentPatternDTO);
            logger.info("MedHelper_LOGS: prescription with id = {}  was found successfully", id);
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
        int patientId = prescriptionTreatmentPatternDTO.getPatientId();
        doctorService.editPrescription(prescriptionTreatmentPatternDTO);
        return "redirect:/doctor/show-prescription/" + patientId;
    }


    /**
     * Cancels selected prescription. All treatment events become cancelled
     *
     * @param prescriptionIdToCancel prescription id
     * @param modelMap               ModelMap
     * @return page with all patient's treatment events
     */
    @PostMapping("/cancel-prescription")
    public String cancelSelectedPrescription(
            @RequestParam("prescriptionIdToCancel") int prescriptionIdToCancel,
            @RequestParam("patientId") int patientId, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method cancelSelectedPrescription(), POST");
        boolean opCancellingResult = doctorService.cancelPrescription(prescriptionIdToCancel);
        logger.info("Cancelling result: {}", opCancellingResult);
        return "redirect:/doctor/show-prescription/" + patientId;
    }


    //--------------- Medical Record -------------------------------

    /**
     * Shows doctor a patient medical record
     *
     * @param id       patient's id
     * @param request  HttpServletRequest
     * @param modelMap modelMap to add a medical record info
     * @return page with a patient medical record
     */
    @GetMapping("/medical-record/{id}")
    public String showMedicalRecord(@PathVariable("id") int id, ModelMap modelMap,
                                    HttpServletRequest request) throws NoHandlerFoundException {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showMedicalRecord(), GET. id = {}", id);
        MedicalRecordDTO medicalRecord = doctorService.getMedicalRecord(id);
        if (medicalRecord.getMedicalRecordId() == BAD_ID) {
            logger.info("MedHelper_LOGS: In DoctorController: medical record with the specified id not found");
            throw new NoHandlerFoundException(
                    request.getMethod(), request.getRequestURI(), new ServletServerHttpRequest(request).getHeaders());
        }
        modelMap.addAttribute(MEDICAL_RECORD, medicalRecord);
        return MEDICAL_RECORD_JSP;
    }


    /**
     * Return form to add new clinical diagnosis
     *
     * @param id       current medical record id
     * @param request  HttpServletRequest
     * @param modelMap ModelMap with current medical record id
     * @return form to add new clinical diagnosis
     */
    @GetMapping("/medical-record/add-diagnosis/{id}")
    public String addDiagnosis(
            @PathVariable("id") int id, HttpServletRequest request, ModelMap modelMap) throws NoHandlerFoundException {
        logger.info("MedHelper_LOGS: In DoctorController - handler method addDiagnosis(), GET");
        MedicalRecordDTO medicalRecord = doctorService.getMedicalRecord(id);
        if (medicalRecord.getMedicalRecordId() == BAD_ID) {
            logger.info("MedHelper_LOGS: In DoctorController: medical record with the specified id not found");
            throw new NoHandlerFoundException(
                    request.getMethod(), request.getRequestURI(), new ServletServerHttpRequest(request).getHeaders());
        }
        modelMap.addAttribute(MEDICAL_RECORD_ID, id);
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
        modelMap.addAttribute(MEDICAL_RECORD, medicalRecord);
        return "redirect:/doctor/medical-record/" + medRecordId;
    }

    /**
     * Provides a form to edit selected clinical diagnosis
     *
     * @param id       clinical diagnosis id
     * @param request  HttpServletRequest
     * @param modelMap ModelMap
     * @return page to edit selected clinical diagnosis
     */
    @GetMapping("/edit-clinical-diagnosis/{id}")
    public String editSelectedDiagnosis(
            @PathVariable("id") int id, HttpServletRequest request, ModelMap modelMap) throws NoHandlerFoundException {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editSelectedDiagnosis(), GET");
        ClinicalDiagnosisDTO clinicalDiagnosisDTO = doctorService.getClinicalDiagnosis(id);
        if (clinicalDiagnosisDTO.getClinicalDiagnosisId() == BAD_ID) {
            logger.info("MedHelper_LOGS: In DoctorController: bad request - clinical diagnosis with id = {} not found", id);
            throw new NoHandlerFoundException(
                    request.getMethod(), request.getRequestURI(), new ServletServerHttpRequest(request).getHeaders());
        } else {
            modelMap.addAttribute("CDToEdit", clinicalDiagnosisDTO);
            logger.info("MedHelper_LOGS: prescription with id = {} was found successfully", id);
        }
        return EDIT_CLINICAL_DIAGNOSIS_JSP;
    }


    /**
     * Changes selected clinical diagnosis
     *
     * @param clinicalDiagnosisDTO clinicalDiagnosisDTO
     * @param bindingResult        binding result
     * @param modelMap             ModelMap
     * @return redirects to page with medical record
     */
    @PostMapping("/edit-clinical-diagnosis")
    public String editSelectedDiagnosis(
            @Valid @ModelAttribute("editClinicalDiagnosis") ClinicalDiagnosisDTO clinicalDiagnosisDTO,
            BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editSelectedDiagnosis(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        doctorService.editClinicalDiagnosis(clinicalDiagnosisDTO);
        int medicalRecordId = clinicalDiagnosisDTO.getMedicalRecord().getMedicalRecordId();
        return "redirect:/doctor/medical-record/" + medicalRecordId;
    }


    /**
     * Deletes selected clinical diagnosis
     *
     * @param cDiagnosisIdToDelete clinical diagnosis id
     * @param medRecId             current medical record id
     * @return redirects to page with medical record data
     */
    @PostMapping("/delete-diagnosis")
    public String deleteClinicalDiagnosisById(@RequestParam("cDiagnosisIdToDelete") int cDiagnosisIdToDelete,
                                              @RequestParam("medRecId") int medRecId) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method deleteClinicalDiagnosisById(), POST");
        boolean deletingResult = doctorService.deleteClinicalDiagnosisById(cDiagnosisIdToDelete);
        logger.info("Deleting result: {}", deletingResult);
        return "redirect:/doctor/medical-record/" + medRecId;
    }


    /**
     * Gives form to change hospitalisation data
     *
     * @param id       Medical record id
     * @param request  HttpServletRequest
     * @param modelMap ModelMap with found hospitalisation data
     * @return form page to change hospitalisation data
     */
    @GetMapping("/medical-record/hospitalisation/{id}")
    public String editHospitalisation(
            @PathVariable("id") int id, HttpServletRequest request, ModelMap modelMap) throws NoHandlerFoundException {
        logger.info("MedHelper_LOGS: In DoctorController - handler method editHospitalisation(), GET");
        MedicalRecordDTO medicalRecord = doctorService.getMedicalRecord(id);
        if (medicalRecord.getMedicalRecordId() == BAD_ID) {
            logger.info("MedHelper_LOGS: In DoctorController: bad request - medical record with the specified id not found");
            throw new NoHandlerFoundException(
                    request.getMethod(), request.getRequestURI(), new ServletServerHttpRequest(request).getHeaders());
        }
        modelMap.addAttribute("hospitalisationToEdit", medicalRecord);
        modelMap.addAttribute(MEDICAL_RECORD_ID, id);
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
        int medRecordId = hospitalisation.getMedicalRecordId();
        logger.info("MedHelper_LOGS: In DoctorController: set new data about hospitalisation");
        modelMap.addAttribute(MEDICAL_RECORD_ID, medRecordId);
        modelMap.addAttribute(MEDICAL_RECORD, medicalRecord);
        return "redirect:/doctor/medical-record/" + medRecordId;
    }

    //-------------------- Other ----------------------------------------------------------

    /**
     * Finds a patient by surname
     *
     * @param surname            patient's surname
     * @param redirectAttributes RedirectAttributes
     * @return page with found result or message about unsuccessful search
     */
    @GetMapping("/find-patient-by-surname")
    public RedirectView findPatientBySurname(
            @RequestParam("surname") String surname, RedirectAttributes redirectAttributes) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method findPatientBySurname()");
        RedirectView redirectView = new RedirectView("/doctor/start-page", true);
        List<PatientShortViewDTO> patientShortViewDTOS = doctorService.findPatientBySurname(surname);
        if (!patientShortViewDTOS.isEmpty()) {
            redirectAttributes.addFlashAttribute("doctorsPatients", patientShortViewDTOS);
            logger.info("MedHelper_LOGS: The patient(-s) with surname = {} was(were) found successfully", surname);
        } else {
            redirectAttributes.addFlashAttribute(MESSAGE, "You don't have a patient with surname = " + surname +
                    "  in database");
            logger.info("MedHelper_LOGS: There is no patient with surname = {} in database", surname);
        }
        return redirectView;
    }


    /**
     * Shows all found patient's treatment events
     *
     * @param patientId patient id
     * @param request   HttpServletRequest
     * @param modelMap  ModelMap
     * @return page with all found patient's treatment events
     */
    @GetMapping("/show-patient-treatment-events/{id}")
    public String showPatientTreatmentEvents(
            @PathVariable("id") int patientId,
            HttpServletRequest request, ModelMap modelMap) throws NoHandlerFoundException {
        logger.info("MedHelper_LOGS: In DoctorController - handler method showPatientTreatmentEvents(), GET");
        if (modelMap.isEmpty()) {
            MedicalRecordDTO medicalRecord = doctorService.getMedicalRecord(patientId);
            if (medicalRecord.getMedicalRecordId() == BAD_ID) {
                logger.info("MedHelper_LOGS: In DoctorController: bad request - medical record with the specified id not found");
                throw new NoHandlerFoundException(
                        request.getMethod(), request.getRequestURI(), new ServletServerHttpRequest(request).getHeaders());
            }
            List<TreatmentEventDTO> treatmentEventDTOS = doctorService.findTreatmentEventsByPatientId(patientId);
            if (!treatmentEventDTOS.isEmpty()) {
                logger.info("MedHelper_LOGS: In DoctorController: " +
                        "The action showPatientTreatmentEvents() completed successfully");
                modelMap.addAttribute(TREATMENT_EVENT_LIST, treatmentEventDTOS);
            } else {
                logger.info("MedHelper_LOGS: The action showPatientTreatmentEvents() returned empty list");
                modelMap.addAttribute(MESSAGE,
                        "INFO: Patient doesn't have treatment events now.");
            }
        }
        modelMap.addAttribute("currentPatientId", patientId);
        return SHOW_PATIENT_TREATMENT_EVENTS_JSP;
    }


    /**
     * Search treatment events by name
     *
     * @param patientId          patient id
     * @param tEventName         name of treatment event (medicine or procedure name)
     * @param redirectAttributes RedirectAttributes
     * @return redirects to page with result of search
     */
    @GetMapping("/find-treatment-event-by-name/{id}")
    public RedirectView findTreatmentEventByName(@PathVariable("id") int patientId,
                                                 @RequestParam("tEventName") String tEventName,
                                                 RedirectAttributes redirectAttributes) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method  findTreatmentEventByName(), GET");
        List<TreatmentEventDTO> treatmentEventDTOS = doctorService.findTreatmentEventByName(tEventName);
        RedirectView redirectView = new RedirectView("/doctor/show-patient-treatment-events/" + patientId, true);

        if (!treatmentEventDTOS.isEmpty()) {
            logger.info("MedHelper_LOGS: In DoctorController: The action findTreatmentEventByName() completed successfully");
            redirectAttributes.addFlashAttribute(TREATMENT_EVENT_LIST, treatmentEventDTOS);
        } else {
            logger.info("MedHelper_LOGS: The action findTreatmentEventByName() returned empty list");
            redirectAttributes.addFlashAttribute(MESSAGE,
                    "INFO: The patient was not prescribed the specified medication or procedure.");
        }
        return redirectView;
    }


    /**
     * Shows selected treatment event's details
     *
     * @param id       treatment event id
     * @param modelMap ModelMap
     * @return page with treatment event's details
     */
    @GetMapping("/treatment-event-details/{id}")
    public String showSelectedTreatmentDetails(
            @PathVariable("id") int id, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController: handler method showSelectedTreatmentDetails(), GET");

        TreatmentEventDTO treatmentEventDTO = nurseService.findTreatmentEventById(id);
        if (treatmentEventDTO != null) {
            logger.info("MedHelper_LOGS: In DoctorController: handler method showSelectedTreatmentDetails() " +
                    "returns treatment event found by id");
            modelMap.addAttribute("treatmentEventDetails", treatmentEventDTO);
        } else {
            modelMap.addAttribute(MESSAGE, "Treatment event with specified id was not found");
        }
        return TREATMENT_EVENT_DETAILS_JSP;
    }


    /**
     * Change treatment event's status to 'COMPLETED'
     *
     * @param tEventId
     * @param patientId     patient's id
     * @param bindingResult binding result
     * @param modelMap      ModelMap
     * @return redirects to page with all found patient's treatment events
     */
    @PostMapping("/treatment-event-set-completed")
    public String setCompletedTreatmentEvent(@ModelAttribute("treatmentEventId") int tEventId,
                                             @ModelAttribute("patientId") int patientId,
                                             BindingResult bindingResult,
                                             ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method setCompletedTreatmentEvent(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        boolean actionResult = nurseService.setTreatmentEventToCompleted(tEventId);
        if (!actionResult) {
            logger.info("MedHelper_LOGS: In DoctorController - Failed to change treatment event status");
            modelMap.addAttribute(MESSAGE, "Failed to change treatment event status");
        }
        logger.info("MedHelper_LOGS: In DoctorController - Treatment event status changed to 'COMPLETED'");
        return "redirect:/doctor/show-patient-treatment-events/" + patientId;
    }


    /**
     * Deletes specified treatment event
     *
     * @param tEventIdToDelete treatment event's id
     * @param patientId        patient's id
     * @param modelMap         ModelMap
     * @return page with all found patient's treatment events
     */
    @PostMapping("/delete-treatment-event")
    public String deleteTreatmentEvent(
            @ModelAttribute("tEventIdToDelete") int tEventIdToDelete,
            @ModelAttribute("patient") int patientId,
            ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method deleteTreatmentEvent()");
        boolean deletingResult = doctorService.deleteTreatmentEvent(tEventIdToDelete);
        if (!deletingResult) {
            logger.info("MedHelper_LOGS:  DoctorController: " +
                    "failed to delete treatment event with id = {}", tEventIdToDelete);
            modelMap.addAttribute(MESSAGE, "Failed to delete treatment event");
        } else {
            logger.info("MedHelper_LOGS: deleteTreatmentEvent() action was completed successfully");
        }
        return "redirect:/doctor/show-patient-treatment-events/" + patientId;
    }


    /**
     * Cancels specified treatment event
     *
     * @param tEventId      treatment event's id
     * @param patientId     patient's id
     * @param bindingResult binding result
     * @param modelMap      ModelMap
     * @return page with all found patient's treatment events
     */
    @PostMapping("/cancel-treatment-event")
    public String cancelTreatmentTEvent(@ModelAttribute("tEvent") int tEventId,
                                        @ModelAttribute("patientId") int patientId,
                                        BindingResult bindingResult, ModelMap modelMap) {
        logger.info("MedHelper_LOGS: In DoctorController - handler method cancelTreatmentTEvent(), POST");
        if (BindingCheck.bindingResultCheck(bindingResult, modelMap)) {
            return ERROR_PAGE_JSP;
        }
        boolean actionResult = doctorService.cancelTreatmentEvent(tEventId);
        if (!actionResult) {
            logger.info("MedHelper_LOGS: In DoctorController - Failed to cancel treatment event");
            modelMap.addAttribute(MESSAGE, "Failed to cancel treatment event");
        }
        return "redirect:/doctor/show-patient-treatment-events/" + patientId;
    }


    @Autowired
    public DoctorController(DoctorService doctorService, NurseService nurseService) {
        this.doctorService = doctorService;
        this.nurseService = nurseService;
    }
}
