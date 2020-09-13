package com.tsystems.rehaklinik.services.implementations;


import com.tsystems.rehaklinik.converters.DTOconverters.*;
import com.tsystems.rehaklinik.dao.interfaces.*;
import com.tsystems.rehaklinik.dto.*;
import com.tsystems.rehaklinik.entities.*;
import com.tsystems.rehaklinik.jms.MessageSender;
import com.tsystems.rehaklinik.services.interfaces.DoctorService;
import com.tsystems.rehaklinik.services.interfaces.TreatmentEventGenerationService;
import com.tsystems.rehaklinik.types.EventStatus;
import com.tsystems.rehaklinik.types.HospitalStayStatus;
import com.tsystems.rehaklinik.types.PrescriptionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.*;

@Service("DoctorService")
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private static Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private PatientDAO patientDAO;
    private MedicalRecordDAO medicalRecordDAO;
    private ClinicalDiagnosisDAO clinicalDiagnosisDAO;
    private PrescriptionDAO prescriptionDAO;
    private TreatmentEventGenerationService treatmentEventGenerationService;
    private TreatmentEventDAO treatmentEventDAO;
    private MessageSender messageSender;


    private static final int DEFAULT_HOUR = 7;
    private static final int DEFAULT_MINUTES = 0;
    private static final int DEFAULT_SECONDS = 0;


    @Override
    public List<PatientShortViewDTO> findPatients() {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in findPatients() method");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int doctorId = ((AuthenticationData) (authentication.getPrincipal())).getEmployee().getEmployeeId();
        logger.info("MedHelper_LOGS: In DoctorServiceImpl: doctor's id = {}", doctorId);
        List<Patient> patientList = patientDAO.findPatientByDoctorId(doctorId);
        List<PatientShortViewDTO> patientsDTO = new ArrayList<>();
        if (!patientList.isEmpty()) {
            for (Patient patient : patientList) {
                patientsDTO.add(new PatientShortViewDTO(patient));
            }
        }
        return patientsDTO;
    }

    @Override
    public List<PatientShortViewDTO> findAllPatients() {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in findAllPatients() method");
        List<Patient> allPatients = patientDAO.findAll();
        List<PatientShortViewDTO> patientsDTO = new ArrayList<>();
        if (!allPatients.isEmpty()) {
            for (Patient patient : allPatients) {
                patientsDTO.add(new PatientShortViewDTO(patient));
            }
        }
        return patientsDTO;
    }


    @Override
    public MedicalRecordDTO getMedicalRecord(int patientId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in getMedicalRecord() method");
        MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(patientId);
        MedicalRecordDTO medicalRecordDTO = MedicalRecordConverter.toDTO(medicalRecord);
        medicalRecordDTO.setPatient(PatientDTOConverter.toDTO(patientDAO.findPatientById(patientId)));
        Set<ClinicalDiagnosis> diagnosisSet = medicalRecord.getClinicalDiagnosis();
        Set<ClinicalDiagnosisDTO> clinicalDiagnosisDTOSet = new HashSet<>();
        for (ClinicalDiagnosis cd : diagnosisSet) {
            clinicalDiagnosisDTOSet.add(ClinicalDiagnoseMapper.INSTANCE.toDTO(cd));
        }
        medicalRecordDTO.setClinicalDiagnosis(clinicalDiagnosisDTOSet);
        return medicalRecordDTO;
    }


    @Override
    public PrescriptionDTO addPrescription(PrescriptionDTO prescriptionDTO) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in addPrescription() method");
        Prescription prescription = PrescriptionMapper.INSTANCE.fromDTO(prescriptionDTO);

        checkTheDuplicatePrescriptionAssignment(prescription);

        prescription.setPrescriptionStatus(PrescriptionStatus.TBD);
        if (prescription.getTreatmentTimePattern().getPrecisionTime() == null) {
            prescription.getTreatmentTimePattern()
                    .setPrecisionTime(LocalTime.of(DEFAULT_HOUR, DEFAULT_MINUTES, DEFAULT_SECONDS));
        }
        Prescription newPrescription = prescriptionDAO.createPrescription(prescription);
        PrescriptionDTO savedPrescriptionDTO = PrescriptionMapper.INSTANCE.toDTO(newPrescription);
        List<TreatmentEvent> treatmentEventList = treatmentEventGenerationService.generateTreatmentEvents(newPrescription);
        for (TreatmentEvent tEvent : treatmentEventList) {
            treatmentEventDAO.createTreatmentEvent(tEvent);
        }
        messageSender.send("Rehaklinik web-app: Changes in the treatment schedule (adding)");
        return savedPrescriptionDTO;
    }


    /**
     * Checks is there already same prescription in database on specified dates
     *
     * @param prescription prescription
     * @return boolean operation result
     */
    private boolean checkTheDuplicatePrescriptionAssignment(Prescription prescription) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in checkTheDuplicatePrescriptionAssignment() method");
        return prescriptionDAO.checkTheDuplicatePrescriptionAssignment(prescription.getPatient().getPatientId(),
                prescription.getMedicineAndProcedure().getMedicineProcedureName(),
                prescription.getStartTreatment(), prescription.getEndTreatment());
    }


    @Override
    public List<PrescriptionShortViewDTO> checkOtherPrescriptionsOnSameDateAndTime(PrescriptionDTO prescriptionDTO) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in checkOtherPrescriptionOnSameDateAndTime() method");
        Prescription prescription = PrescriptionMapper.INSTANCE.fromDTO(prescriptionDTO);
        LocalTime specifiedTime = prescription.getTreatmentTimePattern().getPrecisionTime() == null
                ? LocalTime.of(DEFAULT_HOUR, DEFAULT_MINUTES, DEFAULT_SECONDS)
                : prescription.getTreatmentTimePattern().getPrecisionTime();
        List<Prescription> otherPrescriptionOnSameDateAndTimeList = prescriptionDAO.checkOtherPrescriptionOnSameDateAndTime(
                prescription.getPatient().getPatientId(),
                prescription.getStartTreatment(),
                prescription.getEndTreatment(),
                specifiedTime
        );
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> checkOtherPrescriptionOnSameDateAndTime() returns {}",
                otherPrescriptionOnSameDateAndTimeList.isEmpty());
        if (otherPrescriptionOnSameDateAndTimeList.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<PrescriptionShortViewDTO> prescriptionDTOS = new ArrayList<>();
            for (Prescription p : otherPrescriptionOnSameDateAndTimeList) {
                prescriptionDTOS.add(new PrescriptionShortViewDTO(p));
            }
            return prescriptionDTOS;
        }
    }


    @Override
    public PrescriptionShortViewDTO editPrescription(PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl --> in editPrescription() method");
        Prescription prescriptionToEdit = prescriptionDAO.findPrescriptionById(
                prescriptionTreatmentPatternDTO.getPrescriptionId());
        prescriptionToEdit =
                PrescriptionTreatmentPatternDTOConverter.convertFromDTO(prescriptionToEdit, prescriptionTreatmentPatternDTO);
        logger.info("!!!!! {}", prescriptionToEdit.getTreatmentTimePattern().getPrecisionTime());
        prescriptionToEdit.setPrescriptionStatus(PrescriptionStatus.TBD);
        Prescription edited = prescriptionDAO.updatePrescription(prescriptionToEdit);
        List<TreatmentEvent> treatmentEventList =
                treatmentEventDAO.findPlannedTreatmentEventsByPrescriptionId(edited.getPrescriptionId());
        boolean result = treatmentEventDAO.deletePrescriptionPlannedTreatmentEvents(treatmentEventList);
        if (!result) {
            logger.info("MedHelper_LOGS: In DoctorServiceImpl: editPrescription(): failed attempt to delete planned events");
        }
        List<TreatmentEvent> newTreatmentEvents = treatmentEventGenerationService.generateTreatmentEvents(edited);
        for (TreatmentEvent tEvent : newTreatmentEvents) {
            treatmentEventDAO.createTreatmentEvent(tEvent);
        }
        messageSender.send("Rehaklinik web-app: Changes in the treatment schedule (editing)");
        return new PrescriptionShortViewDTO(edited);
    }


    @Override
    public PrescriptionDetailsDTO getPrescriptionDetails(int prescriptionId) {
        PrescriptionTreatmentPatternDTO prescription = findPrescriptionById(prescriptionId);
        return new PrescriptionDetailsDTO(prescription);
    }


    @Override
    public boolean deletePrescription(int prescriptionId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in deletePrescription() method");
        boolean result = prescriptionDAO.deletePrescriptionById(prescriptionId);
        logger.info("MedHelper_LOGS: DoctorServiceImpl: result of deleting is {}", result);
        messageSender.send("Rehaklinik web-app: Changes in the treatment schedule (deleting)");
        return result;
    }

    @Override
    public boolean cancelPrescription(int prescriptionId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in cancelPrescription() method");
        Prescription prescription = prescriptionDAO.findPrescriptionById(prescriptionId);
        if (!prescription.getPrescriptionStatus().equals(PrescriptionStatus.DONE)) {
            prescription.setPrescriptionStatus(PrescriptionStatus.CANCELLED);
        }
        Prescription cancelled = prescriptionDAO.updatePrescription(prescription);
        if (cancelled.getPrescriptionId() == prescription.getPrescriptionId()
                && cancelled.getPrescriptionStatus() == prescription.getPrescriptionStatus()) {
            logger.info("MedHelper_LOGS: In DoctorServiceImpl: cancelPrescription(): prescription cancelled");
            List<TreatmentEvent> treatmentEventList = prescription.getTreatmentEvents();
            if (treatmentEventList != null) {
                for (TreatmentEvent tEvent : treatmentEventList) {
                    if (tEvent.getTreatmentEventStatus() == EventStatus.PLANNED ||
                            tEvent.getTreatmentEventStatus() == EventStatus.OVERDUE) {
                        tEvent.setTreatmentEventStatus(EventStatus.CANCELLED);
                        tEvent.setCancelReason("Cancelled by doctor");
                        treatmentEventDAO.cancelTreatmentEvent(tEvent);
                    }
                }
                messageSender.send("Rehaklinik web-app: Changes in the treatment schedule (cancelling)");
                return true;
            }
        }
        logger.info("MedHelper_LOGS: In DoctorServiceImpl: cancelPrescription(): Failed to change prescription status");
        return false;
    }

    @Override
    public boolean cancelTreatmentEvent(int tEventId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in cancelTreatmentEvent() method");
        TreatmentEvent treatmentEvent = treatmentEventDAO.findTreatmentEventById(tEventId);
        treatmentEvent.setTreatmentEventStatus(EventStatus.CANCELLED);
        treatmentEvent.setCancelReason("Cancelled by doctor");
        TreatmentEvent cancelled = treatmentEventDAO.cancelTreatmentEvent(treatmentEvent);
        if (cancelled.getTreatmentEventId() == treatmentEvent.getTreatmentEventId() &&
                cancelled.getTreatmentEventStatus() == treatmentEvent.getTreatmentEventStatus()) {
            messageSender.send("Rehaklinik web-app: Changes in the treatment schedule (cancelling)");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTreatmentEvent(int tEventId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in deleteTreatmentEvent() method");
        TreatmentEvent treatmentEvent = treatmentEventDAO.findTreatmentEventById(tEventId);
        if (treatmentEvent != null) {
            treatmentEvent.setPrescription(null);
            messageSender.send("Rehaklinik web-app: Changes in the treatment schedule (deleting)");
            return treatmentEventDAO.deleteTreatmentEvents(treatmentEvent);
        }
        return false;
    }

    @Override
    public ClinicalDiagnosisDTO editClinicalDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in editClinicalDiagnosis() method");
        ClinicalDiagnosis clinicalDiagnoseToEdit = ClinicalDiagnoseDTOConverter.fromDTO(clinicalDiagnosisDTO);
        MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(
                clinicalDiagnosisDTO.getMedicalRecord().getMedicalRecordId());
        clinicalDiagnoseToEdit.setMedicalRecord(medicalRecord);
        return ClinicalDiagnoseDTOConverter.toDTO(clinicalDiagnosisDAO.updateClinicalDiagnosis(clinicalDiagnoseToEdit));
    }

    @Override
    public MedicalRecordDTO setHospitalisation(MedicalRecordDTO medicalRecordDTO) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in setHospitalisation() method");
        int id = medicalRecordDTO.getMedicalRecordId();
        Patient patient = patientDAO.findPatientById(id);
        medicalRecordDTO.setPatient(PatientDTOConverter.toDTO(patient));
        Set<ClinicalDiagnosisDTO> clinicalDiagnosisDTOSet = new HashSet<>();
        Set<ClinicalDiagnosis> clinicalDiagnoseSet = clinicalDiagnosisDAO.getAllPatientClinicalDiagnosis(id);
        for (ClinicalDiagnosis cd : clinicalDiagnoseSet) {
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
                    p.setPrescriptionStatus(PrescriptionStatus.DONE);
                    cancelPrescription(p.getPrescriptionId());
                }
            }
        }
        return medicalRecordDTO;
    }

    @Override
    public ClinicalDiagnosisDTO getClinicalDiagnosis(int clinicalDiagnoseId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in getClinicalDiagnosisDTO() method");
        ClinicalDiagnosis clinicalDiagnose = clinicalDiagnosisDAO.getClinicalDiagnosisById(clinicalDiagnoseId);
        return ClinicalDiagnoseDTOConverter.toDTO(clinicalDiagnose);
    }

    @Override
    public MedicalRecordDTO setNewDiagnosis(ClinicalDiagnosisDTO clinicalDiagnosisDTO, int medRecordId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in setNewDiagnosis() method");
        MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(medRecordId);
        Set<ClinicalDiagnosis> diagnosisSet = medicalRecord.getClinicalDiagnosis();
        ClinicalDiagnosis clinicalDiagnose = ClinicalDiagnoseMapper.INSTANCE.fromDTO(clinicalDiagnosisDTO);
        ClinicalDiagnosis diagnosis = clinicalDiagnosisDAO.createClinicalDiagnosis(clinicalDiagnose);
        diagnosis.setMedicalRecord(medicalRecord);
        ClinicalDiagnosis updatedClinicalDiagnosis = clinicalDiagnosisDAO.updateClinicalDiagnosis(diagnosis);
        diagnosisSet.add(updatedClinicalDiagnosis);
        medicalRecord.setClinicalDiagnosis(diagnosisSet);
        MedicalRecordDTO medicalRecordDTO = MedicalRecordConverter.toDTO(medicalRecordDAO.updateMedicalRecord(medicalRecord));
        medicalRecordDTO.setPatient(PatientDTOConverter.toDTO(patientDAO.findPatientById(medRecordId)));
        Set<ClinicalDiagnosisDTO> clinicalDiagnosisDTOSet = new HashSet<>();
        for (ClinicalDiagnosis cd : diagnosisSet) {
            clinicalDiagnosisDTOSet.add(ClinicalDiagnoseMapper.INSTANCE.toDTO(cd));
        }
        medicalRecordDTO.setClinicalDiagnosis(clinicalDiagnosisDTOSet);
        return medicalRecordDTO;
    }

    @Override
    public boolean deleteClinicalDiagnosisById(int clinicalDiagnosisId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in deleteClinicalDiagnosisById() method");
        ClinicalDiagnosis clinicalDiagnose = clinicalDiagnosisDAO.getClinicalDiagnosisById(clinicalDiagnosisId);
        if (clinicalDiagnose != null) {
            MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(
                    clinicalDiagnose.getMedicalRecord().getMedicalRecordId());
            medicalRecord.setClinicalDiagnosis(null);
            clinicalDiagnose.setMedicalRecord(null);
            return clinicalDiagnosisDAO.deleteClinicalDiagnosis(clinicalDiagnose);
        }
        return false;
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
        }
        return patientShortViewDTOS;
    }

    @Override
    public PrescriptionTreatmentPatternDTO findPrescriptionById(int prescriptionId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in PrescriptionTreatmentPatternDTO() method");
        Prescription prescription = prescriptionDAO.findPrescriptionById(prescriptionId);
        return new PrescriptionTreatmentPatternDTO(prescription);
    }

    @Override
    public List<PrescriptionShortViewDTO> findAllPatientsPrescription(int patientId) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in findAllPatientsPrescription() method");
        List<Prescription> prescriptionsList = prescriptionDAO.fidAllPrescriptionsByPatientId(patientId);
        for (Prescription prescription : prescriptionsList) {
            checkIsPrescriptionDone(prescription);
        }
        List<PrescriptionShortViewDTO> prescriptionDTOS = new ArrayList<>();
        if (!prescriptionsList.isEmpty()) {
            for (Prescription p : prescriptionsList) {
                prescriptionDTOS.add(new PrescriptionShortViewDTO(p));
            }
        }
        return prescriptionDTOS;
    }


    /**
     * Checks  that a prescription has already been completed
     *
     * @param prescription prescription to check
     * @return boolean result of operation
     */
    private boolean checkIsPrescriptionDone(Prescription prescription) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in checkPrescriptionStatus() method");
        if (prescription.getPrescriptionStatus().equals(PrescriptionStatus.CANCELLED)) {
            return false;
        }
        int prescriptionId = prescription.getPrescriptionId();
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findAllTreatmentEventsByPrescriptionId(prescriptionId);
        for (TreatmentEvent tEvent : treatmentEventList) {
            if (tEvent.getTreatmentEventStatus().equals(EventStatus.PLANNED) ||
                    (tEvent.getTreatmentEventStatus().equals(EventStatus.OVERDUE))) {
                return false;
            }
        }
        prescription.setPrescriptionStatus(PrescriptionStatus.DONE);
        return true;
    }


    @Override
    public List<TreatmentEventDTO> findTreatmentEventsByPatientId(int id) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in findTreatmentEventsByPatientId() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findTreatmentEventByPatientId(id);
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (!treatmentEventList.isEmpty()) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
        }
        return treatmentEventDTOList;
    }


    @Override
    public List<TreatmentEventDTO> findTreatmentEventByName(String tEventName) {
        logger.info("MedHelper_LOGS: In DoctorServiceImpl  --> in findTreatmentEventByName() method");
        List<TreatmentEvent> treatmentEventList = treatmentEventDAO.findTreatmentEventByName(tEventName);
        List<TreatmentEventDTO> treatmentEventDTOList = new ArrayList<>();
        if (!treatmentEventList.isEmpty()) {
            for (TreatmentEvent tEvent : treatmentEventList) {
                treatmentEventDTOList.add(new TreatmentEventDTO(tEvent));
            }
        }
        return treatmentEventDTOList;
    }


    @Autowired
    public DoctorServiceImpl(PatientDAO patientDAO,
                             MedicalRecordDAO medicalRecordDAO,
                             ClinicalDiagnosisDAO clinicalDiagnosisDAO,
                             PrescriptionDAO prescriptionDAO,
                             TreatmentEventGenerationService treatmentEventGenerationService,
                             TreatmentEventDAO treatmentEventDAO, MessageSender messageSender) {
        this.patientDAO = patientDAO;
        this.medicalRecordDAO = medicalRecordDAO;
        this.clinicalDiagnosisDAO = clinicalDiagnosisDAO;
        this.prescriptionDAO = prescriptionDAO;
        this.treatmentEventGenerationService = treatmentEventGenerationService;
        this.treatmentEventDAO = treatmentEventDAO;
        this.messageSender = messageSender;
    }
}
