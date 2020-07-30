package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.dao.MedicalRecordDAO;
import com.tsystems.rehaklinik.dao.PatientDAO;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import com.tsystems.rehaklinik.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("DoctorService")
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private static Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private final PatientDAO patientDAO;
    private final MedicalRecordDAO medicalRecordDAO;


    @Override
    public MedicalRecord setHospitalisation(MedicalRecord medicalRecord) {
        Patient patient = patientDAO.findPatientById(medicalRecord.getMedicalRecordId());
        medicalRecord.setPatient(patient);
        return medicalRecordDAO.updateMedicalRecord(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord editedMedicalRecord) {
        return medicalRecordDAO.updateMedicalRecord(editedMedicalRecord);
    }

    @Override
    public MedicalRecord getMedicalRecordById(int medRecId) {
        return null;
    }

    @Override
    public MedicalRecord getMedicalRecord(int patientId) {
        return medicalRecordDAO.findMedicalRecordById(patientId);
    }


    @Override
    public List<PatientDTO> patients() {
        List<Patient> allPatients = patientDAO.findAll();
        List<PatientDTO> patientsDTO = new ArrayList<>();
        if (!allPatients.isEmpty()) {
            for (Patient patient : allPatients) {
                patientsDTO.add(new PatientDTO(patient));
            }
            return patientsDTO;
        }
        return null;
    }


    @Autowired
    public DoctorServiceImpl(PatientDAO patientDAO, MedicalRecordDAO medicalRecordDAO) {
        this.patientDAO = patientDAO;
        this.medicalRecordDAO = medicalRecordDAO;
    }
}
