package com.tsystems.rehaklinik.dao.implementations;


import com.tsystems.rehaklinik.dao.interfaces.PatientDAO;
import com.tsystems.rehaklinik.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;


@Repository
@Transactional
public class PatientDAOImpl implements PatientDAO {

    private static Logger logger = LoggerFactory.getLogger(PatientDAOImpl.class);

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
    public Patient updatePatient(Patient patient) {
        logger.info("MedHelper_LOGS: PatientDAO: Data about an patient with the id = {} is updated",
                patient.getPatientId());
        if (patient.getPatientId() != 0 && entityManager.find(Patient.class, patient.getPatientId()) != null) {
            try {
                Patient editedPatient = entityManager.merge(patient);
                logger.info("MedHelper_LOGS: PatientDAO: Successful attempt to edit an patient with an id = {} ",
                        patient.getPatientId());
                return editedPatient;
            } catch (PersistenceException exception) {
                logger.error(exception.getMessage());
            }
        }
        logger.info("MedHelper_LOGS: PatientDAO: Failed attempt to edit a patient with an id = {}", patient.getPatientId());
        return null;
    }


    @Override
    public boolean deletePatient(int patientId) {
        logger.info("MedHelper_LOGS: PatientDAO: Delete patient by id");
        Patient patient = entityManager.find(Patient.class, patientId);
        if (patient != null) {
            entityManager.remove(patient);
            logger.info("MedHelper_LOGS: PatientDAO: patient with id = {} deleted", patientId);
            return true;
        }
        logger.info("MedHelper_LOGS: PatientDAO: Patient with id = {} does not exist", +patientId);
        return false;
    }


    @Override
    public List<Patient> findAll() {
        logger.info("MedHelper_LOGS: PatientDAO: Find all patients");
        return entityManager.createQuery("SELECT p FROM Patient p ORDER BY p.surname", Patient.class).getResultList();
    }


    @Override
    public List<Patient> findPatientBySurname(String patientSurname) {
        logger.info("MedHelper_LOGS: EmployeeDAO: Find an employee by surname");
        return entityManager.createQuery(
                "select p from Patient p where lower(p.surname) LIKE lower(:patientSurname)", Patient.class)
                .setParameter("patientSurname", "%" + patientSurname + "%")
                .getResultList();
    }


    @Override
    public Patient findPatientById(int patientId) {
        logger.info("MedHelper_LOGS: PatientDAO: Find a patient by id");
        Patient patient = entityManager.find(Patient.class, patientId);
        if (patient != null) {
            logger.info("MedHelper_LOGS: PatientDAO: Patient with id = {} found successfully", patientId);
            return patient;
        }
        logger.info("MedHelper_LOGS: PatientDAO: Patient with id = {} not found", patientId);
        return null;
    }


    @Override
    public List<Patient> findPatientByDoctorId(int doctorId) {
        logger.info("MedHelper_LOGS: PatientDAO: Find all patients by doctor's id");
        return entityManager.createQuery(
                "SELECT p FROM Patient p " +
                        "WHERE p.attendingDoctorId.employeeId = :doctorId " +
                        "ORDER BY p.surname", Patient.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }
}
