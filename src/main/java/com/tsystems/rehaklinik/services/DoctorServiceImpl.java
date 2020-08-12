package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.converters.DTOconverters.*;
import com.tsystems.rehaklinik.dao.*;
import com.tsystems.rehaklinik.dto.*;
import com.tsystems.rehaklinik.entities.*;
import com.tsystems.rehaklinik.types.EventStatus;
import com.tsystems.rehaklinik.types.HospitalStayStatus;
import com.tsystems.rehaklinik.types.PrescriptionStatus;
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
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in cancelPrescription() method");
        Prescription prescription = prescriptionDAO.findPrescriptionById(prescriptionId);
        prescription.setPrescriptionStatus(PrescriptionStatus.CANCELLED);
        Prescription cancelled = prescriptionDAO.updatePrescription(prescription);
        if (cancelled.getPrescriptionId() == prescription.getPrescriptionId()
                && cancelled.getPrescriptionStatus() == prescription.getPrescriptionStatus()) {
            logger.info("MedHelper_LOGS: In DoctorServiceImpl: cancelPrescription(): prescription cancelled");
            List<TreatmentEvent> treatmentEventList = prescription.getTreatmentEvents();
            if (treatmentEventList != null) {
                for (TreatmentEvent tEvent : treatmentEventList) {
                    tEvent.setTreatmentEventStatus(EventStatus.CANCELLED);
                    tEvent.setCancelReason("Cancelled by doctor");
                    treatmentEventDAO.cancelTreatmentEvent(tEvent);
                }
                return true;
            }
        }
        logger.info("MedHelper_LOGS: In DoctorServiceImpl: cancelPrescription(): Failed to change prescription status");
        return false;
    }

    @Override
    public ClinicalDiagnosisDTO editClinicalDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in editClinicalDiagnosis() method");
        ClinicalDiagnose clinicalDiagnoseToEdit = ClinicalDiagnoseDTOConverter.fromDTO(clinicalDiagnosisDTO);
        MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(clinicalDiagnosisDTO.getMedicalRecord().getMedicalRecordId());
        clinicalDiagnoseToEdit.setMedicalRecord(medicalRecord);
        ClinicalDiagnosisDTO editedClinicalDiagnose = ClinicalDiagnoseDTOConverter.toDTO(
                clinicalDiagnosisDAO.updateClinicalDiagnosis(clinicalDiagnoseToEdit));
        return editedClinicalDiagnose;
    }

    @Override
    public PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in addPrescription() method");
        Prescription prescription = PrescriptionMapper.INSTANCE.fromDTO(prescriptionDTO);
        prescription.setPrescriptionStatus(PrescriptionStatus.TBD);
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
        logger.info("MedHelper_LOGS: In DoctorServiceImpl --> in editPrescription() method");
        Prescription prescriptionToEdit = prescriptionDAO.findPrescriptionById(prescriptionTreatmentPatternDTO.getPrescriptionId());
        prescriptionToEdit = PrescriptionTreatmentPatternDTOConverter.convertFromDTO(prescriptionToEdit, prescriptionTreatmentPatternDTO);
        prescriptionToEdit.setPrescriptionStatus(PrescriptionStatus.TBD);
        Prescription edited = prescriptionDAO.updatePrescription(prescriptionToEdit);
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findPlannedTreatmentEventsByPrescriptionId(edited.getPrescriptionId());
        boolean result = treatmentEventDAO.deletePrescriptionPlannedTreatmentEvents(treatmentEventList);
        if (!result) {
            logger.info("MedHelper_LOGS: In DoctorServiceImpl: editPrescription(): failed attempt to delete planned events");
        }
        List<TreatmentEvent> newTreatmentEvents = treatmentEventGenerationService.generateTreatmentEvents(edited);
        for (TreatmentEvent tEvent : newTreatmentEvents) {
            treatmentEventDAO.createTreatmentEvent(tEvent);
        }
        return new PrescriptionShortViewDTO(edited);
    }


    @Override
    public PrescriptionTreatmentPatternDTO findPrescriptionById(int prescriptionId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in PrescriptionTreatmentPatternDTO() method");
        Prescription prescription = prescriptionDAO.findPrescriptionById(prescriptionId);
        return new PrescriptionTreatmentPatternDTO(prescription);
    }


    @Override
    public boolean deletePrescription(int prescriptionId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in deletePrescription() method");
        boolean result = prescriptionDAO.deletePrescriptionById(prescriptionId);
        logger.info("MedHelper_LOGS: DoctorServiceImpl: result of deleting is {}", result);
        return result;
    }


    @Override
    public List<PrescriptionShortViewDTO> findAllPatientsPrescription(int patientId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in findAllPatientsPrescription() method");
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
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in setHospitalisation() method");
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
        medicalRecordDAO.updateMedicalRecord(medicalRecord);

        //When discharged:
        if (medicalRecord.getHospitalStayStatus() == HospitalStayStatus.DISCHARGED) {
            List<Prescription> prescriptionList = prescriptionDAO.fidAllPrescriptionsByPatientId(
                    medicalRecord.getPatient().getPatientId());
            if (prescriptionList != null) {
                for (Prescription p : prescriptionList) {
                    cancelPrescription(p.getPrescriptionId());
                }
            }
        }
        return medicalRecordDTO;
    }


    @Override
    public ClinicalDiagnosisDTO getClinicalDiagnosisDTO(int clinicalDiagnoseId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in getClinicalDiagnosisDTO() method");
        ClinicalDiagnose clinicalDiagnose = clinicalDiagnosisDAO.getClinicalDiagnosisById(clinicalDiagnoseId);
        if (clinicalDiagnose != null) {
            return ClinicalDiagnoseDTOConverter.toDTO(clinicalDiagnose);
        }
        return null;
    }


    @Override
    public MedicalRecordDTO setNewDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO, int medRecordId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in setNewDiagnosis() method");
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
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in deleteClinicalDiagnosisById() method");
        return clinicalDiagnosisDAO.deleteClinicalDiagnosis(cdId);
    }


    @Override
    public List<PatientShortViewDTO> patients() {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in patients() method");
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
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in getMedicalRecord() method");
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

    @Override
    public List<PatientShortViewDTO> findPatientBySurname(String surname) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in findPatientBySurname() method");
        List<Patient> allPatientsFound = patientDAO.findPatientBySurname(surname);
        List<PatientShortViewDTO> patientShortViewDTOS = new ArrayList<>();
        if (!allPatientsFound.isEmpty()) {
            for (Patient patient : allPatientsFound) {
                patientShortViewDTOS.add(new PatientShortViewDTO(patient));
            }
            return patientShortViewDTOS;
        }
        return null;
    }


    @Override
    public List<TreatmentEventDTO> findTreatmentEventsByPatientId(int id) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in findTreatmentEventsByPatientId() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findTreatmentEventByPatientId(id);
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
            return treatmentEventDTOList;
        }
        return Collections.emptyList();
    }


    @Override
    public List<TreatmentEventDTO> findTreatmentEventByName(String tEventName) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in findTreatmentEventByName() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findTreatmentEventByName(tEventName);
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (treatmentEventList != null) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
            return treatmentEventDTOList;
        }
        return Collections.emptyList();
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
