package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.converters.DTOconverters.ClinicalDiagnoseMapper;
import com.tsystems.rehaklinik.converters.DTOconverters.MedicalRecordMapper;
import com.tsystems.rehaklinik.converters.DTOconverters.PrescriptionMapper;
import com.tsystems.rehaklinik.converters.DTOconverters.PrescriptionTreatmentPatternDTOConverter;
import com.tsystems.rehaklinik.dao.*;
import com.tsystems.rehaklinik.dto.*;
import com.tsystems.rehaklinik.entities.*;
import com.tsystems.rehaklinik.types.EventStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service("DoctorService")
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private static Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private final PatientDAO patientDAO;
    private final MedicalRecordDAO medicalRecordDAO;
    private final ClinicalDiagnosisDAO clinicalDiagnosisDAO;
    private final PrescriptionDAO prescriptionDAO;
    private final TreatmentEventGenerationService treatmentEventGenerationService;
    private final TreatmentEventDAO treatmentEventDAO;

    @Override
    public MedicalRecordDTO getMedicalRecord(int patientId) {
        MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(patientId);
        return null;
    }





    @Override
    public boolean cancelPrescription(int prescriptionId) {
        Prescription prescription = prescriptionDAO.findPrescriptionById(prescriptionId);
        List<TreatmentEvent>treatmentEventList = prescription.getTreatmentEvents();
        for (TreatmentEvent tEvent: treatmentEventList) {
            tEvent.setTreatmentEventStatus(EventStatus.CANCELLED);
            tEvent.setCancelReason("Cancelled by doctor");
            treatmentEventDAO.cancelTreatmentEvent(tEvent);
        }
        return true; //доделать нормально
    }

    @Override
    public PrescriptionShortViewDTO editPrescription(PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO) {
        Prescription prescriptionToEdit = prescriptionDAO.findPrescriptionById(prescriptionTreatmentPatternDTO.getPrescriptionId());
        prescriptionToEdit = PrescriptionTreatmentPatternDTOConverter.convertFromDTO(prescriptionToEdit, prescriptionTreatmentPatternDTO);
        return new PrescriptionShortViewDTO(prescriptionDAO.updatePrescription(prescriptionToEdit));
    }



    @Override
    public PrescriptionTreatmentPatternDTO findPrescriptionById(int prescriptionId) {
        Prescription prescription = prescriptionDAO.findPrescriptionById(prescriptionId);
        return new PrescriptionTreatmentPatternDTO(prescription);
    }


    @Override
    public boolean deletePrescription(int prescriptionId) {
        boolean result = prescriptionDAO.deletePrescriptionById(prescriptionId);
        logger.info("MedHelper_LOGS: DoctorServiceImpl: result of deleting is " + result);
        return result;
    }

    @Override
    public List<PrescriptionShortViewDTO> findAllPatientsPrescription(int patientId) {
        List<Prescription> prescriptionsList = prescriptionDAO.fidAllPrescriptionsByPatientId(patientId);
        List<PrescriptionShortViewDTO> prescriptionDTOS = new ArrayList<>();
        if (prescriptionsList != null) {
            for (Prescription p : prescriptionsList) {
                prescriptionDTOS.add(new PrescriptionShortViewDTO(p));
            }
            return prescriptionDTOS;
        }
        return Collections.emptyList();
    }


    @Override
    public PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = PrescriptionMapper.INSTANCE.fromDTO(prescriptionDTO);
        Prescription newPrescription = prescriptionDAO.createPrescription(prescription);
        PrescriptionDTO savedPrescriptionDTO = PrescriptionMapper.INSTANCE.toDTO(newPrescription);
        List<TreatmentEvent> treatmentEventList = treatmentEventGenerationService.generateTreatmentEvents(newPrescription);
        for (TreatmentEvent tEvent: treatmentEventList) {
            treatmentEventDAO.createTreatmentEvent(tEvent);
        }

        return savedPrescriptionDTO;
    }



    @Override
    public MedicalRecordDTO setNewDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO, int medRecordId) {
        MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(medRecordId);
        Set<ClinicalDiagnose> diagnosisSet = medicalRecord.getClinicalDiagnosis();
        ClinicalDiagnose clinicalDiagnose = ClinicalDiagnoseMapper.INSTANCE.fromDTO(clinicalDiagnosisDTO);
        ClinicalDiagnose diagnosis = clinicalDiagnosisDAO.createClinicalDiagnosis(clinicalDiagnose);
        diagnosis.setMedicalRecord(medicalRecord);
        ClinicalDiagnose updatedClinicalDiagnosis = clinicalDiagnosisDAO.updateClinicalDiagnosis(diagnosis);
        diagnosisSet.add(updatedClinicalDiagnosis);
        medicalRecord.setClinicalDiagnosis(diagnosisSet);
        return MedicalRecordMapper.INSTANCE.toDTO(medicalRecordDAO.updateMedicalRecord(medicalRecord));
    }


    //OK
    @Override
    public MedicalRecord setHospitalisation(MedicalRecord medicalRecord) {
        Patient patient = patientDAO.findPatientById(medicalRecord.getMedicalRecordId());
        medicalRecord.setPatient(patient);
        return medicalRecordDAO.updateMedicalRecord(medicalRecord);
    }

    //IN PROCESS
    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord editedMedicalRecord) {
        return medicalRecordDAO.updateMedicalRecord(editedMedicalRecord);
    }


    @Override
    public List<PatientShortViewDTO> patients() {
        List<Patient> allPatients = patientDAO.findAll();
        List<PatientShortViewDTO> patientsDTO = new ArrayList<>();
        if (!allPatients.isEmpty()) {
            for (Patient patient : allPatients) {
                patientsDTO.add(new PatientShortViewDTO(patient));
            }
            return patientsDTO;
        }
        return null;
    }

    //************** DONE **********************


    @Autowired
    public DoctorServiceImpl(PatientDAO patientDAO, MedicalRecordDAO medicalRecordDAO, ClinicalDiagnosisDAO clinicalDiagnosisDAO, PrescriptionDAO prescriptionDAO, TreatmentEventGenerationService treatmentEventGenerationService, TreatmentEventDAO treatmentEventDAO) {
        this.patientDAO = patientDAO;
        this.medicalRecordDAO = medicalRecordDAO;
        this.clinicalDiagnosisDAO = clinicalDiagnosisDAO;
        this.prescriptionDAO = prescriptionDAO;
        this.treatmentEventGenerationService = treatmentEventGenerationService;
        this.treatmentEventDAO = treatmentEventDAO;
    }
}
