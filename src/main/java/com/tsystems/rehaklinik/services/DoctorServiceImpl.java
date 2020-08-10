package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.converters.DTOconverters.*;
import com.tsystems.rehaklinik.dao.*;
import com.tsystems.rehaklinik.dto.*;
import com.tsystems.rehaklinik.entities.*;
import com.tsystems.rehaklinik.types.EventStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public boolean cancelPrescription(int prescriptionId) {
        Prescription prescription = prescriptionDAO.findPrescriptionById(prescriptionId);
        List<TreatmentEvent> treatmentEventList = prescription.getTreatmentEvents();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                tEvent.setTreatmentEventStatus(EventStatus.CANCELLED);
                tEvent.setCancelReason("Cancelled by doctor");
                treatmentEventDAO.cancelTreatmentEvent(tEvent);
            }
            return true;
        }
        return false;
    }

    @Override
    public ClinicalDiagnosisDTO editClinicalDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO) {
        ClinicalDiagnose clinicalDiagnoseToEdit = ClinicalDiagnoseDTOConverter.fromDTO(clinicalDiagnosisDTO);
        MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(clinicalDiagnosisDTO.getMedicalRecord().getMedicalRecordId());
        clinicalDiagnoseToEdit.setMedicalRecord(medicalRecord);
        ClinicalDiagnosisDTO editedClinicalDiagnose = ClinicalDiagnoseDTOConverter.toDTO(
                clinicalDiagnosisDAO.updateClinicalDiagnosis(clinicalDiagnoseToEdit));
        return editedClinicalDiagnose;
    }

    //--------------------------------------------------------------------------------------
    @Override
    public PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = PrescriptionMapper.INSTANCE.fromDTO(prescriptionDTO);
        Prescription newPrescription = prescriptionDAO.createPrescription(prescription);
        PrescriptionDTO savedPrescriptionDTO = PrescriptionMapper.INSTANCE.toDTO(newPrescription);
        List<TreatmentEvent> treatmentEventList = treatmentEventGenerationService.generateTreatmentEvents(newPrescription);
        for (TreatmentEvent tEvent : treatmentEventList) {
            treatmentEventDAO.createTreatmentEvent(tEvent);
        }
        return savedPrescriptionDTO;
    }


    @Override
    public PrescriptionShortViewDTO editPrescription(PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO) {
        Prescription prescriptionToEdit = prescriptionDAO.findPrescriptionById(prescriptionTreatmentPatternDTO.getPrescriptionId());
        prescriptionToEdit = PrescriptionTreatmentPatternDTOConverter.convertFromDTO(prescriptionToEdit, prescriptionTreatmentPatternDTO);
        Prescription edited = prescriptionDAO.updatePrescription(prescriptionToEdit);

        return new PrescriptionShortViewDTO(edited);
    }


    @Override
    public PrescriptionTreatmentPatternDTO findPrescriptionById(int prescriptionId) {
        Prescription prescription = prescriptionDAO.findPrescriptionById(prescriptionId);
        return new PrescriptionTreatmentPatternDTO(prescription);
    }


    @Override
    public boolean deletePrescription(int prescriptionId) {
        boolean result = prescriptionDAO.deletePrescriptionById(prescriptionId);
        logger.info("MedHelper_LOGS: DoctorServiceImpl: result of deleting is {}", result);
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
    public MedicalRecordDTO setHospitalisation(MedicalRecordDTO medicalRecordDTO) {
        int id = medicalRecordDTO.getMedicalRecordId();
        Patient patient = patientDAO.findPatientById(id);
        medicalRecordDTO.setPatient(PatientDTOConverter.toDTO(patient));
        Set<ClinicalDiagnosisDTO> clinicalDiagnosisDTOSet = new HashSet<>();
        Set<ClinicalDiagnose> clinicalDiagnoseSet = clinicalDiagnosisDAO.getAllPatientClinicalDiagnosis(id);
        for (ClinicalDiagnose cd : clinicalDiagnoseSet) {
            clinicalDiagnosisDTOSet.add(ClinicalDiagnoseMapper.INSTANCE.toDTO(cd));
        }
        medicalRecordDTO.setClinicalDiagnosis(clinicalDiagnosisDTOSet);
        MedicalRecord medicalRecord = MedicalRecordMapper.INSTANCE.fromDTO(medicalRecordDTO);

        return medicalRecordDTO;
    }


    @Override
    public ClinicalDiagnosisDTO getClinicalDiagnosisDTO(int clinicalDiagnoseId) {
        ClinicalDiagnose clinicalDiagnose = clinicalDiagnosisDAO.getClinicalDiagnosisById(clinicalDiagnoseId);
        if (clinicalDiagnose != null) {
            return ClinicalDiagnoseDTOConverter.toDTO(clinicalDiagnose);
        }
        return null;
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
        MedicalRecordDTO medicalRecordDTO = MedicalRecordConverter.toDTO(medicalRecordDAO.updateMedicalRecord(medicalRecord));
        medicalRecordDTO.setPatient(PatientDTOConverter.toDTO(patientDAO.findPatientById(medRecordId)));
        Set<ClinicalDiagnosisDTO> clinicalDiagnosisDTOSet = new HashSet<>();
        for (ClinicalDiagnose cd : diagnosisSet) {
            clinicalDiagnosisDTOSet.add(ClinicalDiagnoseMapper.INSTANCE.toDTO(cd));
        }
        medicalRecordDTO.setClinicalDiagnosis(clinicalDiagnosisDTOSet);

        return medicalRecordDTO;
    }


    @Override
    public boolean deleteClinicalDiagnosisById(int cdId) {
        return clinicalDiagnosisDAO.deleteClinicalDiagnosis(cdId);
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


    @Override
    public MedicalRecordDTO getMedicalRecord(int patientId) {
        MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(patientId);
        if (medicalRecord != null) {
            MedicalRecordDTO medicalRecordDTO = MedicalRecordConverter.toDTO(medicalRecord);
            medicalRecordDTO.setPatient(PatientDTOConverter.toDTO(patientDAO.findPatientById(patientId)));
            Set<ClinicalDiagnose> diagnosisSet = medicalRecord.getClinicalDiagnosis();
            Set<ClinicalDiagnosisDTO> clinicalDiagnosisDTOSet = new HashSet<>();
            for (ClinicalDiagnose cd : diagnosisSet) {
                clinicalDiagnosisDTOSet.add(ClinicalDiagnoseMapper.INSTANCE.toDTO(cd));
            }
            medicalRecordDTO.setClinicalDiagnosis(clinicalDiagnosisDTOSet);
            return medicalRecordDTO;
        }
        return null;
    }


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
