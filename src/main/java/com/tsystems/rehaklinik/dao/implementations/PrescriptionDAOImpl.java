package com.tsystems.rehaklinik.dao.implementations;

import com.tsystems.rehaklinik.dao.interfaces.PrescriptionDAO;
import com.tsystems.rehaklinik.entities.*;
import com.tsystems.rehaklinik.exceptions.DuplicatePrescriptionException;
import com.tsystems.rehaklinik.exceptions.WrongIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.time.LocalTime;
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
        logger.info("MedHelper_LOGS:  PrescriptionDAO: Added new prescription: {}",
                prescription.getMedicineAndProcedure().getMedicineProcedureName());
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
        logger.info("MedHelper_LOGS:  PrescriptionDAOImpl: Prescription  with id = {} not found. An exception was thrown", id);
        throw new WrongIdException(id);
    }


    @Override
    public List<Prescription> fidAllPrescriptionsByPatientId(int id) {
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: Finds all patient's prescriptions (by patient's id)");
        return entityManager.createQuery(
                "SELECT p FROM Prescription p WHERE p.patient.patientId = :patId " +
                        "ORDER BY p.prescriptionStatus asc, p.startTreatment",
                Prescription.class).setParameter("patId", id).getResultList();
    }


    @Override
    public boolean checkTheDuplicatePrescriptionAssignment(
            int patientId,
            String medicineOrProcedureName,
            LocalDate startTreatment,
            LocalDate endTreatment) {

        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: in checkTheDuplicatePrescriptionAssignment()");
        List<Prescription> existedPrescription = entityManager.createQuery("SELECT p FROM Prescription p " +
                "WHERE p.patient.patientId =:patientId " +
                "AND lower(p.medicineAndProcedure.medicineProcedureName) LIKE lower(:name) " +
                "AND (( :startDate BETWEEN p.startTreatment AND p.endTreatment) " +
                "OR (:endDate BETWEEN p.startTreatment AND p.endTreatment))")
                .setParameter("patientId", patientId)
                .setParameter("name", medicineOrProcedureName)
                .setParameter("startDate", startTreatment)
                .setParameter("endDate", endTreatment)
                .getResultList();
        if (!existedPrescription.isEmpty()) {
            logger.error("MedHelper_LOGS: PrescriptionDAOImpl: DuplicatePrescriptionException thrown");
            StringBuilder sb = new StringBuilder();
            for (Prescription prescription : existedPrescription) {
                sb.append("'" + medicineOrProcedureName + "', start period: " + prescription.getStartTreatment() +
                        ", end period: " + prescription.getEndTreatment());
            }
            throw new DuplicatePrescriptionException(sb.toString());
        }
        return false;
    }


    @Override
    public List<Prescription> checkOtherPrescriptionOnSameDateAndTime(
            int patientId,
            LocalDate startTreatment,
            LocalDate endTreatment,
            LocalTime time) {
        logger.info("MedHelper_LOGS: PrescriptionDAOImpl: in checkOtherPrescriptionOnSameDateAndTime()");
        return entityManager.createQuery("SELECT p FROM Prescription p " +
                "WHERE p.patient.patientId =:pId " +
                "AND p.treatmentTimePattern.precisionTime = :time " +
                "AND (( :startDate BETWEEN p.startTreatment AND p.endTreatment) " +
                "OR (:endDate BETWEEN p.startTreatment AND p.endTreatment))")
                .setParameter("pId", patientId)
                .setParameter("time", time)
                .setParameter("startDate", startTreatment)
                .setParameter("endDate", endTreatment)
                .getResultList();
    }
}
