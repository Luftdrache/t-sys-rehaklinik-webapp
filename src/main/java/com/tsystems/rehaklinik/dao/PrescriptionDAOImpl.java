package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import com.tsystems.rehaklinik.entities.Patient;
import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.types.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.method.P;
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
    public Prescription updatePrescription(Prescription editedPrescription) {
        int id = editedPrescription.getPrescriptionId();
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: received prescription with id = "
                + id + " to update");
        if (id != 0 && entityManager.find(Prescription.class, id) != null) {
            try {
                Prescription prescription = entityManager.merge(editedPrescription);
                logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Successful attempt to edit prescription with an id = " + id);
                return prescription;
            } catch (PersistenceException exception) {
                logger.info(exception.getMessage());
            }
        }
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Failed attempt to edit prescription with an id = " + id);
        return null;
    }


    @Override
    public Prescription createPrescription(Prescription prescription) {
        logger.info("MedHelper_LOGS: PrescriptionDAO: Add new prescription");
        entityManager.persist(prescription);
        logger.info("MedHelper_LOGS:  PrescriptionDAO: Added new prescription for patient: "
                + prescription.getPatient().getFirstName()
                + prescription.getPatient().getMiddleName()
                + prescription.getPatient().getSurname());
        return prescription;
    }


    @Override
    public boolean deletePrescriptionById(int id) {
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Deleting prescription by id");
        Prescription prescription = findPrescriptionById(id);
        if (prescription != null) {
            entityManager.remove(prescription);
            logger.info("MedHelper_LOGS: PrescriptionDAOImpl: prescription with id = " + id + " deleted");
            return true;
        }
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl:  prescription with id = " + id + " does not exist");
        return false;
    }


    @Override
    public Prescription findPrescriptionById(int id) {
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Finding prescription by id");
        Prescription prescription = entityManager.find(Prescription.class, id);
        if (prescription != null) {
            logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Prescription with id = " + id + " found successfully");
            return prescription;
        }
        logger.info("MedHelper_LOGS:  PrescriptionDAOImpl: Prescription  with id = " + id + " not found");
        return null;
    }


    @Override
    public List<Prescription> fidAllPrescriptionsByPatientId(int id) {
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Finds all patient's prescriptions (by patient's id)");
        return entityManager.createQuery(
                "SELECT p FROM Prescription p WHERE p.patient.patientId = :patientId ORDER BY p.startTreatment",
                Prescription.class).setParameter("patientId", id).getResultList();
    }
}
