package com.tsystems.rehaklinik.dao;


import com.tsystems.rehaklinik.entities.ClinicalDiagnose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ClinicalDiagnosisDAOImpl implements ClinicalDiagnosisDAO {

    private static Logger logger = LoggerFactory.getLogger(ClinicalDiagnosisDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClinicalDiagnose createClinicalDiagnosis(ClinicalDiagnose clinicalDiagnosis) {
        logger.info("MedHelper_LOGS: ClinicalDiagnosisDAO: Add new diagnosis");
        entityManager.persist(clinicalDiagnosis);
        logger.info("MedHelper_LOGS:  ClinicalDiagnosisDAO: Added new diagnosis");
        return clinicalDiagnosis;
    }
}
