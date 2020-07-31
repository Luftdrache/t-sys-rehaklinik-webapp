package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.ClinicalDiagnose;
import com.tsystems.rehaklinik.entities.Prescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@Transactional
public class PrescriptionDAOImpl implements PrescriptionDAO{

    private static Logger logger = LoggerFactory.getLogger(PrescriptionDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Prescription createPrescription(Prescription prescription) {
        logger.info("MedHelper_LOGS: PrescriptionDAO: Add new prescription");
        entityManager.persist(prescription);
        logger.info("MedHelper_LOGS:  PrescriptionDAO: Added new prescription: " + prescription);
        return prescription;
    }

}
