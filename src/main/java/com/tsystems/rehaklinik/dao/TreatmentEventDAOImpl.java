package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class TreatmentEventDAOImpl implements TreatmentEventDAO {

    private static Logger logger = LoggerFactory.getLogger(TreatmentEventDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TreatmentEvent createTreatmentEvent(TreatmentEvent treatmentEvent) {
        logger.info("MedHelper_LOGS: TreatmentEventDAOImpl: adding new treatment event");
        entityManager.persist(treatmentEvent);
        return treatmentEvent;
    }

}
