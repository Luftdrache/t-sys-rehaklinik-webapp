package com.tsystems.rehaklinik.dao.implementations;

import com.tsystems.rehaklinik.dao.interfaces.TreatmentEventDAO;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.types.EventStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional
public class TreatmentEventDAOImpl implements TreatmentEventDAO {

    private static Logger logger = LoggerFactory.getLogger(TreatmentEventDAOImpl.class);

    private static final String STATUS = "status";
    private static final String TODAY = "today";
    private static final int NOT_AN_OVERDUE_PERIOD_IN_MINUTES = 59;

    @PersistenceContext
    private EntityManager entityManager;


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
    public boolean deleteTreatmentEvents(TreatmentEvent treatmentEvent) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: deleting  treatment events by id");
        if (treatmentEvent != null) {
            entityManager.remove(treatmentEvent);
            return true;
        }
        return false;
    }


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
    public List<TreatmentEvent> findAllTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all treatment events");
        return entityManager.createQuery("SELECT t FROM TreatmentEvent t ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class).getResultList();
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
                .setParameter(TODAY, today)
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
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: end period time is {}", endTimePeriod);
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: today {}", today);

        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventDate = :today " +
                        "AND t.treatmentEventTime >= :startTimePeriod " +
                        "AND t.treatmentEventTime <= :endTimePeriod " +
                        "AND t.treatmentEventStatus = :status " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class)
                .setParameter("startTimePeriod", startTimePeriod)
                .setParameter("endTimePeriod", endTimePeriod)
                .setParameter(TODAY, today)
                .setParameter(STATUS, EventStatus.PLANNED)
                .getResultList();
    }


    @Override
    public List<TreatmentEvent> findAllCompletedTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all completed treatment events");
        return entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventStatus = :status " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class).setParameter(STATUS, EventStatus.COMPLETED).getResultList();
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
    public List<TreatmentEvent> findPlannedTreatmentEventsByPrescriptionId(int prescriptionId) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all planned treatment events by prescription id");
        return entityManager.createQuery("SELECT t FROM TreatmentEvent t " +
                "WHERE t.treatmentEventStatus = :status " +
                "AND t.prescription.prescriptionId = :prescriptionId " +
                "ORDER BY t.treatmentEventDate, t.treatmentEventTime", TreatmentEvent.class)
                .setParameter(STATUS, EventStatus.PLANNED)
                .setParameter("prescriptionId", prescriptionId)
                .getResultList();
    }


    @Override
    public List<TreatmentEvent> findAllTreatmentEventsByPrescriptionId(int prescriptionId) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding all treatment events by prescription id");
        return entityManager.createQuery("SELECT t FROM TreatmentEvent t " +
                "WHERE t.prescription.prescriptionId = :prescriptionId", TreatmentEvent.class)
                .setParameter("prescriptionId", prescriptionId)
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
    public List<TreatmentEvent> findTreatmentEventsByPatientsSurname(String surname) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: finding treatment events by patient's surname");
        return entityManager.createQuery(
                "select t from TreatmentEvent t where lower(t.patient.surname) LIKE lower(:surname) " +
                        "ORDER BY t.treatmentEventStatus asc, t.treatmentEventDate, t.treatmentEventTime", TreatmentEvent.class)
                .setParameter("surname", "%" + surname + "%")
                .getResultList();
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


    /**
     * Checks if treatment event is overdue
     */
    private void checkOverdueTreatmentEvents() {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: checking overdue treatment events");
        LocalTime overdueTime = LocalTime.now().minusMinutes(NOT_AN_OVERDUE_PERIOD_IN_MINUTES);
        LocalDate today = LocalDate.now();

        List<TreatmentEvent> overdue = entityManager.createQuery(
                "SELECT t FROM TreatmentEvent t " +
                        "WHERE t.treatmentEventDate = :today AND t.treatmentEventStatus = :status " +
                        "AND t.treatmentEventTime  < :overdueTime " +
                        "ORDER BY t.treatmentEventDate, t.treatmentEventTime",
                TreatmentEvent.class)
                .setParameter(STATUS, EventStatus.PLANNED)
                .setParameter("overdueTime", overdueTime)
                .setParameter(TODAY, today)
                .getResultList();
        if (overdue != null) {
            for (TreatmentEvent tEvent : overdue) {
                tEvent.setTreatmentEventStatus(EventStatus.OVERDUE);
                entityManager.merge(tEvent);
            }
        }
    }
}
