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
    public List<TreatmentEvent> findTreatmentEventByName(String tEventName) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding treatment events by name");
        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE lower(t.prescription.medicineAndProcedure.medicineProcedureName) LIKE lower(:tEventName) " +
                        "ORDER BY  t.treatmentEventStatus ASC, t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class)
                .setParameter("tEventName", "%" + tEventName + "%")
                .getResultList();
    }


    @Override
    public List<TreatmentEvent> findTreatmentEventByPatientId(int id) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding treatment events by selected patient");
        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.patient.patientId = :id " +
                        "ORDER BY  t.treatmentEventStatus ASC, t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class)
                .setParameter("id", id)
                .getResultList();
    }


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
    public boolean deletePrescriptionPlannedTreatmentEvents(List<TreatmentEvent> treatmentEvents) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: deleting planned treatment events by selected prescription");
        if (treatmentEvents != null) {
            for (TreatmentEvent tEvent : treatmentEvents) {
                entityManager.remove(tEvent);
            }
            return true;
        }
        return false;
    }


    @Override
    public List<TreatmentEvent> findTodayTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all today treatment events");
        LocalDate today = LocalDate.now();
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: current date is {}", today);
        checkOverdueTreatmentEvents();
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
        checkOverdueTreatmentEvents();
        LocalTime startTimePeriod = LocalTime.now();
        LocalTime endTimePeriod = LocalTime.now().plusHours(1);
        LocalDate today = LocalDate.now();
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: current time is {}", startTimePeriod);
        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventTime >= :startTimePeriod " +
                        "AND t.treatmentEventTime <= :endTimePeriod " +
                        "AND t.treatmentEventDate = :today " +
                        "AND t.treatmentEventStatus = :status " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class)
                .setParameter("startTimePeriod", startTimePeriod)
                .setParameter("endTimePeriod", endTimePeriod)
                .setParameter("today", today)
                .setParameter("status", EventStatus.PLANNED)
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
        checkOverdueTreatmentEvents();
        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventStatus = :statusPlanned " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class).
                setParameter("statusPlanned", EventStatus.PLANNED)
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


    private void checkOverdueTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: checking overdue treatment events");
        LocalTime overdueTime = LocalTime.now().plusHours(1);
        LocalDate today = LocalDate.now();

        List<TreatmentEvent> overdue = entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventStatus = :status " +
                        "AND t.treatmentEventTime <= :overdueTime " +
                        "AND t.treatmentEventDate = :today " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class)
                .setParameter("status", EventStatus.PLANNED)
                .setParameter("overdueTime", overdueTime)
                .setParameter("today", today)
                .getResultList();
        if (overdue != null) {
            for (TreatmentEvent tEvent : overdue) {
                tEvent.setTreatmentEventStatus(EventStatus.OVERDUE);
                entityManager.merge(tEvent);
            }
        }
    }


    @Override
    public List<TreatmentEvent> findOverdueTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding overdue treatment events");
        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventStatus = :statusOverdue " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class).
                setParameter("statusOverdue", EventStatus.OVERDUE)
                .getResultList();
    }


    @Override
    public List<TreatmentEvent> findPlannedTreatmentEventsByPrescriptionId(int prescriptionId) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all planned treatment events by prescription id");
        return entityManager.createQuery("SELECT t FROM TreatmentEvent t " +
                "WHERE t.treatmentEventStatus = :status " +
                "AND t.prescription.prescriptionId = :prescriptionId " +
                "ORDER BY t.treatmentEventDate, t.treatmentEventTime", TreatmentEvent.class)
                .setParameter("status", EventStatus.PLANNED)
                .setParameter("prescriptionId", prescriptionId)
                .getResultList();
    }
}
