package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.dao.EmployeeDAO;
import com.tsystems.rehaklinik.dao.EmployeeDAOImpl;
import com.tsystems.rehaklinik.dao.PatientDAO;
import com.tsystems.rehaklinik.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service ("HospitalReceptionService")
@Transactional
public class HospitalReceptionServiceImpl implements HospitalReceptionService {

    private static Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    private final PatientDAO patientDAO;


    @Override
    public Patient addNewPatient(Patient patient) {
        return patientDAO.createPatient(patient);
    }

    @Override
    public String deletePatientById(int id) {
        return patientDAO.deletePatient(id);
    }

    @Override
    public List<Patient> showAllPatients() {
        return patientDAO.findAll();
    }

    @Override
    public Patient editPatient(Patient editedPatient) {
        return null;
    }

    @Override
    public Patient getPatientById(int id) {
        return null;
    }

    @Override
    public List<Patient> findPatientBySurname(String surname) {
        return null;
    }




    @Autowired
    public HospitalReceptionServiceImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }
}
