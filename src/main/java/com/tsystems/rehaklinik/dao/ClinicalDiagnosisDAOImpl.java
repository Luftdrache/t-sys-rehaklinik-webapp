package com.tsystems.rehaklinik.dao;


import com.tsystems.rehaklinik.entities.ClinicalDiagnose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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


    @Override
    public ClinicalDiagnose updateClinicalDiagnosis(ClinicalDiagnose clinicalDiagnose) {
        logger.info("MedHelper_LOGS: ClinicalDiagnosisDAO: updating clinical diagnosis");
        if (clinicalDiagnose.getClinicalDiagnosisId() != 0
                && entityManager.find(ClinicalDiagnose.class, clinicalDiagnose.getClinicalDiagnosisId()) != null) {
            try {
                ClinicalDiagnose updClinicalDiagnosis = entityManager.merge(clinicalDiagnose);
                logger.info("MedHelper_LOGS: ClinicalDiagnosisDAO: " +
                        "Successful attempt to edit a clinical diagnosis with an id = %d", clinicalDiagnose.getClinicalDiagnosisId());
                return updClinicalDiagnosis;
            } catch (PersistenceException exception) {
                logger.info(exception.getMessage());
            }
        }
        logger.info("MedHelper_LOGS:ClinicalDiagnosisDAO:" +
                " Failed attempt to edit a clinical diagnosis with an id = %d", clinicalDiagnose.getClinicalDiagnosisId());
        return null;
    }
}
