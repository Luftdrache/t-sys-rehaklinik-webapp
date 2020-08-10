package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.converters.LocalTimeAttributeConverter;
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
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
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
    public TreatmentEvent createTreatmentEvent(TreatmentEvent treatmentEvent) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: adding new treatment event");
        entityManager.persist(treatmentEvent);
        return treatmentEvent;
    }

    @Override
    public TreatmentEvent cancelTreatmentEvent(TreatmentEvent treatmentEvent) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: cancelling treatment event");
        return entityManager.merge(treatmentEvent);
    }

    @Override
    public TreatmentEvent setCompleted(TreatmentEvent treatmentEvent) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: Change treatment event  status to 'Completed'");
        return entityManager.merge(treatmentEvent);
    }

    @Override
    public List<TreatmentEvent> findTodayTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all today treatment events");
        LocalDate today = LocalDate.now();
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: current date is {}", today);

        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventDate = :today " +
                        "ORDER BY  t.treatmentEventStatus asc, t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class)
                .setParameter("today", today)
                .getResultList();
    }


    @Override
    public List<TreatmentEvent> findUrgentTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all urgent treatment events");
        LocalTime startTimePeriod = LocalTime.now();
        LocalTime endTimePeriod = LocalTime.now().plusHours(1);
        LocalDate today = LocalDate.now();
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: current time is {}", startTimePeriod);
        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventTime >= :startTimePeriod " +
                        "AND t.treatmentEventTime <= :endTimePeriod " +
                        "AND t.treatmentEventDate = :today " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class)
                .setParameter("startTimePeriod", startTimePeriod)
                .setParameter("endTimePeriod", endTimePeriod)
                .setParameter("today", today)
                .getResultList();
    }


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
                .setParameter("statusCompleted", EventStatus.COMPLETED)
                .getResultList();
    }


    @Override
    public TreatmentEvent findTreatmentEventById(int treatmentEventId) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding treatment event by id");
        TreatmentEvent foundTreatmentEvent = entityManager.find(TreatmentEvent.class, treatmentEventId);
        if (foundTreatmentEvent != null) {
            logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: treatment event with id = {} found successfully", treatmentEventId);
            return foundTreatmentEvent;
        }
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl:  treatment event with id = {} not found", treatmentEventId);
        return null;
    }


    @Override
    public List<TreatmentEvent> findAllTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all treatment events");
        return entityManager.createQuery("SELECT t FROM TreatmentEvent t ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class).getResultList();
    }


    @Override
    public List<TreatmentEvent> findTreatmentEventsByPatientsSurname(String surname) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding treatment events by patient's surname");
        return entityManager.createQuery(
                "select t from TreatmentEvent t where lower(t.patient.surname) LIKE lower(:surname) " +
                        "ORDER BY t.treatmentEventStatus asc, t.treatmentEventDate, t.treatmentEventTime", TreatmentEvent.class)
                .setParameter("surname", surname)
                .getResultList();
    }

}
