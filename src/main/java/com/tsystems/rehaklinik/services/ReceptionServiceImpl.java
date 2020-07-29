package com.tsystems.rehaklinik.services;


import com.tsystems.rehaklinik.dao.EmployeeDAO;
import com.tsystems.rehaklinik.dao.PatientDAO;
import com.tsystems.rehaklinik.dto.EmployeeDTO;
import com.tsystems.rehaklinik.dto.PatientDTO;
import com.tsystems.rehaklinik.entities.Employee;
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
    private final EmployeeDAO employeeDAO;


    @Override
    public List<Patient> findPatientBySurname(String surname) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<EmployeeDTO> getAllDoctors() {
       List<Employee> doctors = employeeDAO.findAllDoctors();
       List<EmployeeDTO> doctorsDTO = new ArrayList<>();
       if(!doctors.isEmpty()) {
           for (Employee doc : doctors) {
               doctorsDTO.add(new EmployeeDTO(doc));
               logger.info(doc.toString());
           }
       }
        return doctorsDTO;
    }

    @Override
    public String deletePatientById(int id) {
        return patientDAO.deletePatient(id);
    }

    @Override
    public Patient getPatientById(int id) {
        return patientDAO.findPatientById(id);
    }


    @Override
    public Patient editPatient(Patient editedPatient) {
        return patientDAO.updatePatient(editedPatient);
    }

    @Override
    public Patient addNewPatient(Patient patient) {
        return patientDAO.createPatient(patient);
    }


    @Override
    public List<PatientDTO> showAllPatients() {
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
    public ReceptionServiceImpl(PatientDAO patientDAO, EmployeeDAO employeeDAO) {
        this.patientDAO = patientDAO;
        this.employeeDAO = employeeDAO;
    }
}
