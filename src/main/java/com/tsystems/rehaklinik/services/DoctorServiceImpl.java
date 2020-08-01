package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.converters.DTOconverters.ClinicalDiagnoseMapper;
import com.tsystems.rehaklinik.converters.DTOconverters.MedicalRecordMapper;
import com.tsystems.rehaklinik.dao.*;
import com.tsystems.rehaklinik.dto.ClinicalDiagnosisDTO;
import com.tsystems.rehaklinik.dto.MedicalRecordDTO;
import com.tsystems.rehaklinik.dto.PatientShortViewDTO;
import com.tsystems.rehaklinik.entities.ClinicalDiagnose;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.entities.Prescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


    @Override
    public Prescription addPrescription(Prescription prescription) {
        Prescription newPrescription = prescriptionDAO.createPrescription(prescription);
        generateEvents(newPrescription);
        return newPrescription;
    }


    private void generateEvents(Prescription prescription) {
        throw new UnsupportedOperationException("Not implemented yet");
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
//    @Override
//    public MedicalRecord setNewDiagnosis(ClinicalDiagnose clinicalDiagnose, int medRecordId) {
//        Set<ClinicalDiagnose> diagnosisSet;
//        MedicalRecord medicalRecord = medicalRecordDAO.findMedicalRecordById(medRecordId);
//        diagnosisSet = medicalRecord.getClinicalDiagnosis();
//        ClinicalDiagnose diagnosis = clinicalDiagnosisDAO.createClinicalDiagnosis(clinicalDiagnose);
//        diagnosis.setMedicalRecord(medicalRecord);
//        ClinicalDiagnose updatedClinicalDiagnosis = clinicalDiagnosisDAO.updateClinicalDiagnosis(diagnosis);
//        diagnosisSet.add(updatedClinicalDiagnosis);
//        medicalRecord.setClinicalDiagnosis(diagnosisSet);
//        return medicalRecordDAO.updateMedicalRecord(medicalRecord);
//    }


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

    //NIE
    @Override
    public MedicalRecord getMedicalRecordById(int medRecId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    //OK
    @Override
    public MedicalRecord getMedicalRecord(int patientId) {
        return medicalRecordDAO.findMedicalRecordById(patientId);
    }

    //OK
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


    @Autowired
    public DoctorServiceImpl(PatientDAO patientDAO, MedicalRecordDAO medicalRecordDAO, ClinicalDiagnosisDAO clinicalDiagnosisDAO, PrescriptionDAO prescriptionDAO) {
        this.patientDAO = patientDAO;
        this.medicalRecordDAO = medicalRecordDAO;
        this.clinicalDiagnosisDAO = clinicalDiagnosisDAO;
        this.prescriptionDAO = prescriptionDAO;
    }
}
