package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.*;
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
public class PrescriptionDAOImpl implements PrescriptionDAO {

    private static Logger logger = LoggerFactory.getLogger(PrescriptionDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Prescription createPrescription(Prescription prescription) {
        logger.info("MedHelper_LOGS: PrescriptionDAO: Add new prescription");
        entityManager.persist(prescription);
        logger.info("MedHelper_LOGS:  PrescriptionDAO: Added new prescription for patient: {}",
                prescription.getPatient().getFirstName() + " "
                        + prescription.getPatient().getMiddleName() + " "
                        + prescription.getPatient().getSurname());
        return prescription;
    }


    @Override
    public Prescription updatePrescription(Prescription editedPrescription) {
        int id = editedPrescription.getPrescriptionId();
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: received prescription with id = {} to update", id);
        if (id != 0 && entityManager.find(Prescription.class, id) != null) {
            try {
                Prescription prescription = entityManager.merge(editedPrescription);
                logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Successful attempt to edit prescription with an id = {}", id);
                return prescription;
            } catch (PersistenceException exception) {
                logger.error(exception.getMessage());
            }
        }
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Failed attempt to edit prescription with an id = {}", id);
        return null;
    }


    @Override
    public boolean deletePrescriptionById(int id) {
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Deleting prescription by id");
        Prescription prescription = findPrescriptionById(id);
        if (prescription != null) {
            entityManager.remove(prescription);
            logger.info("MedHelper_LOGS: PrescriptionDAOImpl: prescription with id = {} deleted", id);
            return true;
        }
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl:  prescription with id = {} does not exist", id);
        return false;
    }


    @Override
    public Prescription findPrescriptionById(int id) {
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Finding prescription by id");
        Prescription prescription = entityManager.find(Prescription.class, id);
        if (prescription != null) {
            logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Prescription with id = {} found successfully", id);
            return prescription;
        }
        logger.info("MedHelper_LOGS:  PrescriptionDAOImpl: Prescription  with id = {} not found", id);
        return null;
    }


    @Override
    public List<Prescription> fidAllPrescriptionsByPatientId(int id) {
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Finds all patient's prescriptions (by patient's id)");
        return entityManager.createQuery(
                "SELECT p FROM Prescription p WHERE p.patient.patientId = :patientId " +
                        "ORDER BY p.prescriptionStatus asc, p.startTreatment",
                Prescription.class).setParameter("patientId", id).getResultList();
    }
}
