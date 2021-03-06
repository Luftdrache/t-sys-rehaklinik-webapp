package com.tsystems.rehaklinik.services;

import com.tsystems.rehaklinik.dao.interfaces.*;
import com.tsystems.rehaklinik.dto.*;
import com.tsystems.rehaklinik.entities.*;
import com.tsystems.rehaklinik.fillers.*;
import com.tsystems.rehaklinik.jms.MessageSender;
import com.tsystems.rehaklinik.services.implementations.DoctorServiceImpl;
import com.tsystems.rehaklinik.services.interfaces.TreatmentEventGenerationService;
import com.tsystems.rehaklinik.types.EventStatus;
import com.tsystems.rehaklinik.types.HospitalStayStatus;
import com.tsystems.rehaklinik.types.PrescriptionStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    private final static int TEST_ID = 1;

    @Mock
    private PatientDAO patientDAO;
    @Mock
    private MedicalRecordDAO medicalRecordDAO;
    @Mock
    private ClinicalDiagnosisDAO clinicalDiagnosisDAO;
    @Mock
    private PrescriptionDAO prescriptionDAO;
    @Mock
    private TreatmentEventGenerationService treatmentEventGenerationService;
    @Mock
    private TreatmentEventDAO treatmentEventDAO;
    @Mock
    private MessageSender messageSender;
    @Mock
    private Logger mockLogger;
    @Mock
    private Authentication authentication;
    @Mock
    private SecurityContext securityContext;


    @InjectMocks
    private DoctorServiceImpl doctorService;


    @Test
    void setHospitalisation_should_change_hospitalisation_status() {
        Patient patient = PatientFiller.getPatient();
        given(patientDAO.findPatientById(TEST_ID)).willReturn(patient);
        Set<ClinicalDiagnosis> clinicalDiagnosisSet = new HashSet<>();
        clinicalDiagnosisSet.add(ClinicalDiagnosisFiller.getClinicalDiagnosis());
        given(clinicalDiagnosisDAO.getAllPatientClinicalDiagnosis(TEST_ID)).willReturn(clinicalDiagnosisSet);
        given(medicalRecordDAO.updateMedicalRecord(any(MedicalRecord.class)))
                .willAnswer((Answer<MedicalRecord>) invocation -> {
                    MedicalRecord medicalRecord = (MedicalRecord) invocation.getArguments()[0];
                    medicalRecord.setClinicalDiagnosis(clinicalDiagnosisSet);
                    medicalRecord.setHospitalStayStatus(HospitalStayStatus.DISCHARGED);
                    return medicalRecord;
                });
        MedicalRecordDTO medicalRecordDTO = MedicalRecordFiller.getMedicalRecordDTO();
        medicalRecordDTO.setHospitalStayStatus(HospitalStayStatus.DISCHARGED);
        MedicalRecordDTO updated = doctorService.setHospitalisation(medicalRecordDTO);
        assertEquals(HospitalStayStatus.DISCHARGED, updated.getHospitalStayStatus());
    }


    @Test
    void editPrescription_should_return_updated_prescription() {
        Prescription prescription = PrescriptionFiller.getPrescription();
        given(prescriptionDAO.findPrescriptionById(TEST_ID)).willReturn(prescription);
        given(prescriptionDAO.updatePrescription(prescription)).willAnswer((Answer<Prescription>) invocation -> {
            Prescription updated = (Prescription) invocation.getArguments()[0];
            updated.setDose("100 mg");
            return updated;
        });

        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        treatmentEventList.add(treatmentEvent);
        given(treatmentEventDAO.findPlannedTreatmentEventsByPrescriptionId(TEST_ID)).willReturn(treatmentEventList);
        given(treatmentEventDAO.deletePrescriptionPlannedTreatmentEvents(treatmentEventList)).willReturn(false);

        PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO = PrescriptionFiller.getPrescriptionTreatmentPatternDTO();
        assertEquals("50 mg", prescriptionTreatmentPatternDTO.getDose());
        PrescriptionShortViewDTO prescriptionShortViewDTO = doctorService.editPrescription(prescriptionTreatmentPatternDTO);
        assertEquals(TEST_ID, prescriptionShortViewDTO.getPrescriptionId());
        assertEquals("100 mg", prescriptionShortViewDTO.getDose());
    }


    @Test
    void addPrescription_should_return_created_prescription() {
        given(prescriptionDAO.createPrescription(any(Prescription.class))).willAnswer((Answer<Prescription>) invocation -> {
            Prescription prescription = (Prescription) invocation.getArguments()[0];
            prescription.setPrescriptionStatus(PrescriptionStatus.TBD);
            return prescription;
        });
        messageSender.send("Updated");
        PrescriptionDTO prescriptionDTO = PrescriptionFiller.getPrescriptionDTO();
        doctorService.addPrescription(prescriptionDTO);
        assertEquals(prescriptionDTO.getPrescriptionId(),
                doctorService.addPrescription(prescriptionDTO).getPrescriptionId());
        assertEquals(PrescriptionStatus.TBD,
                doctorService.addPrescription(prescriptionDTO).getPrescriptionStatus());
    }


    @Test
    void getPrescriptionDetails_should_return_details() {
        Prescription prescription = PrescriptionFiller.getPrescription();
        given(prescriptionDAO.findPrescriptionById(TEST_ID)).willReturn(prescription);
        assertNotNull(doctorService.getPrescriptionDetails(TEST_ID));
        assertEquals(prescription.getMedicineAndProcedure().getMedicineProcedureName(),
                doctorService.getPrescriptionDetails(TEST_ID).getMedicineProcedureName());
    }

    @Test
    void cancelPrescription_should_return_true() {
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        treatmentEventList.add(TreatmentEventFiller.getTreatmentEvent());
        Prescription prescription = PrescriptionFiller.getPrescription();
        prescription.setPrescriptionStatus(PrescriptionStatus.CANCELLED);
        prescription.setTreatmentEvents(treatmentEventList);
        given(prescriptionDAO.findPrescriptionById(TEST_ID)).willReturn(prescription);
        Prescription cancelled = PrescriptionFiller.getPrescription();
        cancelled.setPrescriptionStatus(PrescriptionStatus.CANCELLED);
        cancelled.setTreatmentEvents(treatmentEventList);
        assertFalse(cancelled.getTreatmentEvents().isEmpty());
        given(prescriptionDAO.updatePrescription(prescription)).willReturn(cancelled);
        assertTrue(doctorService.cancelPrescription(TEST_ID));
    }

    @Test
    void cancelPrescription_should_return_false() {
        Prescription prescription = PrescriptionFiller.getPrescription();
        given(prescriptionDAO.findPrescriptionById(TEST_ID)).willReturn(prescription);
        Prescription cancelled = PrescriptionFiller.getPrescription();
        given(prescriptionDAO.updatePrescription(prescription)).willReturn(cancelled);
        assertFalse(doctorService.cancelPrescription(TEST_ID));
    }

    @Test
    void deletePrescription_should_return_true_when_deleting_is_successful() {
        given(prescriptionDAO.deletePrescriptionById(TEST_ID)).willReturn(true);
        assertTrue(doctorService.deletePrescription(TEST_ID));
    }

    @Test
    void deleteTreatmentEvent_should_return_true_when_deleting_is_successful() {
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(treatmentEvent);
        given(treatmentEventDAO.deleteTreatmentEvents(treatmentEvent)).willReturn(true);
        assertTrue(doctorService.deleteTreatmentEvent(TEST_ID));
    }

    @Test
    void deleteTreatmentEvent_should_return_false_when_deleting_failed() {
        TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(treatmentEvent);
        given(treatmentEventDAO.deleteTreatmentEvents(treatmentEvent)).willReturn(false);
        assertFalse(doctorService.deleteTreatmentEvent(TEST_ID));
    }

    @Test
    void deleteTreatmentEvent_should_return_false_when_event_not_found() {
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(null);
        assertFalse(doctorService.deleteTreatmentEvent(TEST_ID));
    }


    @Test
    void cancelTreatmentEvent_should_return_true_if_cancelled() {
        TreatmentEvent tEvent = TreatmentEventFiller.getTreatmentEvent();
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(tEvent);
        TreatmentEvent cancelled = TreatmentEventFiller.getTreatmentEvent();
        cancelled.setTreatmentEventStatus(EventStatus.CANCELLED);
        given(treatmentEventDAO.cancelTreatmentEvent(tEvent)).willReturn(cancelled);
        assertTrue(doctorService.cancelTreatmentEvent(TEST_ID));
    }

    @Test
    void cancelTreatmentEvent_should_return_false_if_not_cancelled() {
        TreatmentEvent tEvent = TreatmentEventFiller.getTreatmentEvent();
        given(treatmentEventDAO.findTreatmentEventById(TEST_ID)).willReturn(tEvent);
        TreatmentEvent cancelled = TreatmentEventFiller.getTreatmentEvent();
        given(treatmentEventDAO.cancelTreatmentEvent(tEvent)).willReturn(cancelled);
        assertFalse(doctorService.cancelTreatmentEvent(TEST_ID));
    }


    @Test
    void findPrescriptionById_should_found_prescription() {
        Prescription prescription = PrescriptionFiller.getPrescription();
        given(prescriptionDAO.findPrescriptionById(TEST_ID)).willReturn(prescription);
        PrescriptionTreatmentPatternDTO found = doctorService.findPrescriptionById(TEST_ID);
        assertNotNull(found);
    }


    @Test
    void findAllPatientsPrescription_should_return_prescriptions_list() {
        List<Prescription> prescriptionsList = new ArrayList<>();
        prescriptionsList.add(PrescriptionFiller.getPrescription());
        prescriptionsList.add(PrescriptionFiller.getPrescription());
        given(prescriptionDAO.fidAllPrescriptionsByPatientId(TEST_ID)).willReturn(prescriptionsList);

        for (Prescription prescription : prescriptionsList) {
            List<TreatmentEvent> treatmentEventList = new ArrayList<>();
            TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
            treatmentEventList.add(treatmentEvent);
            treatmentEventList.add(treatmentEvent);
            given(treatmentEventDAO.findAllTreatmentEventsByPrescriptionId(prescription.getPrescriptionId()))
                    .willReturn(treatmentEventList);
        }

        List<PrescriptionShortViewDTO> prescriptionDTOS = doctorService.findAllPatientsPrescription(TEST_ID);
        assertFalse(prescriptionDTOS.isEmpty());
        assertEquals(2, prescriptionDTOS.size());
    }


    @Test
    void checkIsPrescriptionDone_should_return_done_prescription() {

        List<Prescription> prescriptionsList = new ArrayList<>();
        prescriptionsList.add(PrescriptionFiller.getPrescription());
        prescriptionsList.add(PrescriptionFiller.getPrescription());
        given(prescriptionDAO.fidAllPrescriptionsByPatientId(TEST_ID)).willReturn(prescriptionsList);

        for (Prescription prescription : prescriptionsList) {
            List<TreatmentEvent> treatmentEventList = new ArrayList<>();
            TreatmentEvent treatmentEvent = TreatmentEventFiller.getTreatmentEvent();
            treatmentEvent.setTreatmentEventStatus(EventStatus.COMPLETED);
            treatmentEventList.add(treatmentEvent);
            treatmentEventList.add(treatmentEvent);
            given(treatmentEventDAO.findAllTreatmentEventsByPrescriptionId(prescription.getPrescriptionId()))
                    .willReturn(treatmentEventList);
        }

        List<PrescriptionShortViewDTO> prescriptionDTOS = doctorService.findAllPatientsPrescription(TEST_ID);
        assertFalse(prescriptionDTOS.isEmpty());
        assertEquals(PrescriptionStatus.DONE, prescriptionDTOS.get(0).getPrescriptionStatus());
    }


    @Test
    void findAllPatientsPrescription_should_find_empty_list() {
        List<Prescription> prescriptionsList = new ArrayList<>();
        given(prescriptionDAO.fidAllPrescriptionsByPatientId(TEST_ID)).willReturn(prescriptionsList);
        List<PrescriptionShortViewDTO> prescriptionDTOS = doctorService.findAllPatientsPrescription(TEST_ID);
        assertTrue(prescriptionDTOS.isEmpty());
    }

    @Test
    void findPatients_should_return_doctor_all_his_patients() {
        mockLogger.info("Logging");
        given(securityContext.getAuthentication()).willReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        AuthenticationData authenticationData = AuthenticationDataFiller.getAuthenticationData();
        authenticationData.setEmployee(EmployeeFiller.getEmployee());
        given(authentication.getPrincipal()).willReturn(authenticationData);
        List<Patient> allDoctorsPatients = new ArrayList<>();
        allDoctorsPatients.add(PatientFiller.getPatient());
        given(patientDAO.findPatientByDoctorId(TEST_ID)).willReturn(allDoctorsPatients);
        List<PatientShortViewDTO> foundPatients = doctorService.findPatients();
        assertFalse(foundPatients.isEmpty());
    }


    @Test
    void findAllPatients_should_return_an_emptyList() {
        mockLogger.info("Logging");
        List<Patient> allPatientsInDatabase = new ArrayList<>();
        allPatientsInDatabase.add(PatientFiller.getPatient());
        given(patientDAO.findAll()).willReturn(allPatientsInDatabase);
        List<PatientShortViewDTO> foundPatients = doctorService.findAllPatients();
        assertFalse(foundPatients.isEmpty());

    }

    @Test
    void findPatientBySurname_should_return_an_emptyList() {
        mockLogger.info("Logging");
        List<Patient> testPatientList = new ArrayList<>();
        given(patientDAO.findPatientBySurname("Fletcher")).willReturn(testPatientList);
        List<PatientShortViewDTO> foundPatients = doctorService.findPatientBySurname("Fletcher");
        assertTrue(foundPatients.isEmpty());
    }

    @Test
    void findPatientBySurname_should_return_found_patient() {
        mockLogger.info("Logging");
        List<Patient> testPatientsList = new ArrayList<>();
        testPatientsList.add(PatientFiller.getPatient());
        given(patientDAO.findPatientBySurname("Fletcher")).willReturn(testPatientsList);
        List<PatientShortViewDTO> foundPatients = doctorService.findPatientBySurname("Fletcher");
        assertEquals(testPatientsList.size(), foundPatients.size());
        assertFalse(foundPatients.isEmpty());
        assertEquals(testPatientsList.get(0).getPatientId(), foundPatients.get(0).getPatientId());
    }


    @Test
    void getMedicalRecord_should_return_medical_record_by_id() {
        MedicalRecord medicalRecord = MedicalRecordFiller.getMedicalRecord();
        given(medicalRecordDAO.findMedicalRecordById(TEST_ID)).willReturn(medicalRecord);
        Patient patient = PatientFiller.getPatient();
        given(patientDAO.findPatientById(TEST_ID)).willReturn(patient);
        MedicalRecordDTO medicalRecordDTO = doctorService.getMedicalRecord(TEST_ID);
        assertNotNull(medicalRecordDTO);
        assertEquals(TEST_ID, medicalRecordDTO.getMedicalRecordId());
    }

    @Test
    void getClinicalDiagnosis_should_return_clinical_diagnosis_by_id() {
        ClinicalDiagnosis clinicalDiagnose = ClinicalDiagnosisFiller.getClinicalDiagnosis();
        given(clinicalDiagnosisDAO.getClinicalDiagnosisById(TEST_ID)).willReturn(clinicalDiagnose);
        ClinicalDiagnosisDTO cdFoundById = doctorService.getClinicalDiagnosis(TEST_ID);
        assertEquals(clinicalDiagnose.getClinicalDiagnosisId(), cdFoundById.getClinicalDiagnosisId());
        assertEquals(clinicalDiagnose.getMainDisease(), cdFoundById.getMainDisease());
        assertEquals(clinicalDiagnose.getIcd10Code(), cdFoundById.getIcd10Code());
    }


    @Test
    void setNewDiagnosis_should_return_medical_record_with_diagnosis() {
        MedicalRecord medicalRecord = MedicalRecordFiller.getMedicalRecord();
        given(medicalRecordDAO.findMedicalRecordById(TEST_ID)).willReturn(medicalRecord);
        when(clinicalDiagnosisDAO.createClinicalDiagnosis(any(ClinicalDiagnosis.class)))
                .thenAnswer((Answer<ClinicalDiagnosis>) invocation -> {
                    ClinicalDiagnosis clinicalDiagnosis = (ClinicalDiagnosis) invocation.getArguments()[0];
                    clinicalDiagnosis.setClinicalDiagnosisId(1);
                    clinicalDiagnosis.setMainDisease("Cardiomyopathy");
                    clinicalDiagnosis.setIcd10Code("I43.1");
                    return clinicalDiagnosis;
                });
        when(clinicalDiagnosisDAO.updateClinicalDiagnosis(any(ClinicalDiagnosis.class)))
                .thenAnswer((Answer<ClinicalDiagnosis>) invocation -> {
                    ClinicalDiagnosis clinicalDiagnose = (ClinicalDiagnosis) invocation.getArguments()[0];
                    clinicalDiagnose.setMainDisease("Disease");
                    return clinicalDiagnose;
                });
        MedicalRecord updated = MedicalRecordFiller.getMedicalRecord();
        given(medicalRecordDAO.updateMedicalRecord(medicalRecord)).willReturn(updated);
        Patient patient = PatientFiller.getPatient();
        given(patientDAO.findPatientById(1)).willReturn(patient);
        ClinicalDiagnosisDTO clinicalDiagnosisDTO = ClinicalDiagnosisFiller.ClinicalDiagnosisDTO();
        MedicalRecordDTO updatedMedicalRecord = doctorService.setNewDiagnosis(clinicalDiagnosisDTO, 1);
        System.out.println(updatedMedicalRecord.getClinicalDiagnosis().size());
        assertFalse(updatedMedicalRecord.getClinicalDiagnosis().isEmpty());
    }

    @Test
    void editClinicalDiagnosis_should_return_edited_clinical_diagnosis() {
        ClinicalDiagnosis clinicalDiagnoseToEdit = ClinicalDiagnosisFiller.getClinicalDiagnosis();
        MedicalRecord medicalRecord = MedicalRecordFiller.getMedicalRecord();
        given(medicalRecordDAO.findMedicalRecordById(1)).willReturn(medicalRecord);
        ClinicalDiagnosis editedTest = ClinicalDiagnosisFiller.getClinicalDiagnosis();
        given(clinicalDiagnosisDAO.updateClinicalDiagnosis(clinicalDiagnoseToEdit)).willReturn(editedTest);
        ClinicalDiagnosisDTO clinicalDiagnosisDTO = ClinicalDiagnosisFiller.ClinicalDiagnosisDTO();
        ClinicalDiagnosisDTO edited = doctorService.editClinicalDiagnosis(clinicalDiagnosisDTO);
        assertEquals(clinicalDiagnoseToEdit.getIcd10Code(), edited.getIcd10Code());
    }

    @Test
    void deleteClinicalDiagnosisById_should_return_true() {
        ClinicalDiagnosis clinicalDiagnose = ClinicalDiagnosisFiller.getClinicalDiagnosis();
        given(clinicalDiagnosisDAO.getClinicalDiagnosisById(TEST_ID)).willReturn(clinicalDiagnose);
        MedicalRecord medicalRecord = MedicalRecordFiller.getMedicalRecord();
        given(medicalRecordDAO.findMedicalRecordById(TEST_ID)).willReturn(medicalRecord);
        given(clinicalDiagnosisDAO.deleteClinicalDiagnosis(clinicalDiagnose)).willReturn(true);
        boolean deletingResult = doctorService.deleteClinicalDiagnosisById(TEST_ID);
        assertTrue(deletingResult);
    }

    @Test
    void deleteClinicalDiagnosisById_should_return_false() {
        given(clinicalDiagnosisDAO.getClinicalDiagnosisById(1)).willReturn(null);
        boolean deletingResult = doctorService.deleteClinicalDiagnosisById(TEST_ID);
        assertFalse(deletingResult);
    }

    @Test
    void findTreatmentEventByName_should_return_found_treatment_events() {
        mockLogger.info("Logging");
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        treatmentEventList.add(TreatmentEventFiller.getTreatmentEvent());
        given(treatmentEventDAO.findTreatmentEventByName("Aspirin")).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundEvents = doctorService.findTreatmentEventByName("Aspirin");
        assertFalse(foundEvents.isEmpty());
        assertEquals("Aspirin", foundEvents.get(0).getMedicineProcedureName());
    }

    @Test
    void findTreatmentEventByName_should_return_an_empty_list_if_any_event_found() {
        mockLogger.info("Logging");
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        given(treatmentEventDAO.findTreatmentEventByName("Aspirin")).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundEvents = doctorService.findTreatmentEventByName("Aspirin");
        assertTrue(foundEvents.isEmpty());
    }

    @Test
    void findTreatmentEventsByPatientId_should_return_nonempty_list_if_events_found() {
        mockLogger.info("Logging");
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        treatmentEventList.add(TreatmentEventFiller.getTreatmentEvent());
        given(treatmentEventDAO.findTreatmentEventByPatientId(TEST_ID)).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundEvents = doctorService.findTreatmentEventsByPatientId(TEST_ID);
        assertFalse(foundEvents.isEmpty());
        assertEquals(1, foundEvents.size());
        assertEquals("Aspirin", foundEvents.get(0).getMedicineProcedureName());
    }

    @Test
    void findTreatmentEventsByPatientId_should_return_empty_list() {
        mockLogger.info("Logging");
        List<TreatmentEvent> treatmentEventList = new ArrayList<>();
        given(treatmentEventDAO.findTreatmentEventByPatientId(TEST_ID)).willReturn(treatmentEventList);
        List<TreatmentEventDTO> foundEvents = doctorService.findTreatmentEventsByPatientId(TEST_ID);
        assertTrue(foundEvents.isEmpty());
    }

    @Test
    void checkOtherPrescriptionsOnSameDateAndTime_should_return_an_empty_list() {
        Prescription prescription = PrescriptionFiller.getPrescription();
        prescription.getTreatmentTimePattern().setPrecisionTime(null);
        given(prescriptionDAO.checkOtherPrescriptionOnSameDateAndTime(prescription.getPatient().getPatientId(),
                prescription.getStartTreatment(),
                prescription.getEndTreatment(), LocalTime.of(7, 0, 0))).willReturn(Collections.emptyList());
        PrescriptionDTO prescriptionDTO = PrescriptionFiller.getPrescriptionDTO();
        List<PrescriptionShortViewDTO> found = doctorService.checkOtherPrescriptionsOnSameDateAndTime(prescriptionDTO);
        assertTrue(found.isEmpty());
    }
}