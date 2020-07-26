package com.tsystems.rehaklinik.dao;


import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class PatientDAOImpl implements PatientDAO {

    private static Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Patient createPatient(Patient patient) {
        logger.info("MedHelper_LOGS: PatientDAO: Add new patient");
        entityManager.persist(patient);
        logger.info("MedHelper_LOGS: PatientDAO: Add new patient");
        return patient;
    }


    @Override
    public String deletePatient(int patientId) {
        logger.info("MedHelper_LOGS: PatientDAO: Delete an employee by id");
        Employee employee = entityManager.find(Employee.class, patientId);
        if (employee != null) {
            entityManager.remove(employee);
            logger.info("MedHelper_LOGS: PatientDAO: patient with id = " + patientId + " deleted");
            return "Patient deleted successfully";
        }
        logger.info("MedHelper_LOGS: PatientDAO: Patient with id = " + patientId + " does not exist");
        return "The specified patient with id= " + patientId + " does not exist";
    }



    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }

    @Override
    public Patient findPatientById(int patientId) {
        return null;
    }

    @Override
    public List<Employee> findPatientBySurname(String patientSurname) {
        return null;
    }

    @Override
    public List<Patient> findAll() {
        logger.info("MedHelper_LOGS: PatientDAO: Find all patients");
        return entityManager.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    }
}
