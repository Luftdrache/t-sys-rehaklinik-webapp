package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Employee;
import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.types.EventStatus;
import com.tsystems.rehaklinik.types.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TreatmentEventDAOImpl implements TreatmentEventDAO {

    private static Logger logger = LoggerFactory.getLogger(TreatmentEventDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public boolean deleteAllPatientTreatmentEvents(int patientId) {
        return false;
    }



//********* done *************

    @Override
    public List<TreatmentEvent> findAllCompletedTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all completed treatment events");
        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventStatus = :status " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class).setParameter("status", EventStatus.COMPLETED).getResultList();
    }


    @Override
    public List<TreatmentEvent> findAllPlannedTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all treatment events except cancelled");
        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventStatus <> :statusCanceled " +
                        "AND t.treatmentEventStatus <> :statusCompleted " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class).
                setParameter("statusCanceled", EventStatus.CANCELLED)
                .setParameter("statusCompleted", EventStatus.COMPLETED )
                .getResultList();
    }


    @Override
    public TreatmentEvent findTreatmentEventById(int treatmentEventId) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding treatment event by id");
        TreatmentEvent foundTreatmentEvent = entityManager.find(TreatmentEvent.class, treatmentEventId);
        if(foundTreatmentEvent != null) {
            logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: treatment event with id = "
                    +  treatmentEventId + " found successfully");
            return foundTreatmentEvent;
        }
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl:  treatment event with id = " + treatmentEventId + " not found");
        return null;
    }


    @Override
    public List<TreatmentEvent> findAllTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all treatment events");
        return entityManager.createQuery("SELECT t FROM TreatmentEvent t ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class).getResultList();
    }

    @Override
    public TreatmentEvent cancelTreatmentEvent(TreatmentEvent treatmentEvent) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: cancelling treatment event");
        return entityManager.merge(treatmentEvent);
    }


    @Override
    public TreatmentEvent createTreatmentEvent(TreatmentEvent treatmentEvent) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: adding new treatment event");
        entityManager.persist(treatmentEvent);
        return treatmentEvent;
    }

}
