package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.dao.PatientDAO;
import com.tsystems.rehaklinik.dto.PatientReceptionViewDTO;
import com.tsystems.rehaklinik.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service ("ReceptionService")
@Transactional
public class ReceptionServiceImpl implements ReceptionService {

    private static Logger logger = LoggerFactory.getLogger(ReceptionServiceImpl.class);
    private final PatientDAO patientDAO;



    @Override
    public List<Patient> findPatientBySurname(String surname) {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @Override
    public String deletePatientById(int id) {
        return patientDAO.deletePatient(id);
    }




    @Override
    public Patient getPatientById(int id) {
        return patientDAO.findPatientById(id);
    }

    //OK
    @Override
    public Patient editPatient(Patient editedPatient) {
        return patientDAO.updatePatient(editedPatient);
    }
    //OK
    @Override
    public Patient addNewPatient(Patient patient) {
        return patientDAO.createPatient(patient);
    }
    //OK
    @Override
    public List<PatientReceptionViewDTO> showAllPatients() {
        List<Patient> allPatients = patientDAO.findAll();
        List<PatientReceptionViewDTO> patientsDTO = new ArrayList<>();
        if (!allPatients.isEmpty()) {
            for (Patient patient : allPatients) {
                patientsDTO.add(new PatientReceptionViewDTO(patient));
            }
            return patientsDTO;
        }
        return null;
    }


    @Autowired
    public ReceptionServiceImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }
}
