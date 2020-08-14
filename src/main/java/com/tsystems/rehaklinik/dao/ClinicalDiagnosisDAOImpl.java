package com.tsystems.rehaklinik.dao;


import com.tsystems.rehaklinik.entities.ClinicalDiagnose;
import com.tsystems.rehaklinik.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Set;


@Repository
@Transactional
public class ClinicalDiagnosisDAOImpl implements ClinicalDiagnosisDAO {

    private static Logger logger = LoggerFactory.getLogger(ClinicalDiagnosisDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClinicalDiagnose createClinicalDiagnosis(ClinicalDiagnose clinicalDiagnosis) {
        logger.info("MedHelper_LOGS: ClinicalDiagnosisDAOImpl: Add new diagnosis");
        entityManager.persist(clinicalDiagnosis);
        logger.info("MedHelper_LOGS:  ClinicalDiagnosisDAOImpl: Added new diagnosis");
        return clinicalDiagnosis;
    }


    @Override
    public ClinicalDiagnose updateClinicalDiagnosis(ClinicalDiagnose clinicalDiagnose) {
        logger.info("MedHelper_LOGS: ClinicalDiagnosisDAOImpl: updating clinical diagnosis");
        if (clinicalDiagnose.getClinicalDiagnosisId() != 0
                && entityManager.find(ClinicalDiagnose.class, clinicalDiagnose.getClinicalDiagnosisId()) != null) {
            try {
                ClinicalDiagnose updClinicalDiagnosis = entityManager.merge(clinicalDiagnose);
                logger.info("MedHelper_LOGS: ClinicalDiagnosisDAOImpl: " +
                        "Successful attempt to edit a clinical diagnosis with an id = {} ", clinicalDiagnose.getClinicalDiagnosisId());
                return updClinicalDiagnosis;
            } catch (PersistenceException exception) {
                logger.info(exception.getMessage());
            }
        }
        logger.info("MedHelper_LOGS:ClinicalDiagnosisDAO:" +
                " Failed attempt to edit a clinical diagnosis with an id = {}", clinicalDiagnose.getClinicalDiagnosisId());
        return null;
    }


    @Override
    public boolean deleteClinicalDiagnosis(ClinicalDiagnose clinicalDiagnose) {
        logger.info("MedHelper_LOGS: ClinicalDiagnosisDAOImpl: Deleting diagnosis by id");
        int id =  clinicalDiagnose.getClinicalDiagnosisId();
        entityManager.remove(clinicalDiagnose);
        ClinicalDiagnose deleted = entityManager.find(ClinicalDiagnose.class, id);
        if (deleted == null) {
            logger.info("MedHelper_LOGS: ClinicalDiagnosisDAOImpl: Clinical Diagnosis with id = {} deleted", id);
            return true;
        }
        logger.info("MedHelper_LOGS: ClinicalDiagnosisDAOImpl: Failed attempt to delete " +
                "clinical diagnosis  with id = {}", id);
        return false;
    }


    @Override
    public ClinicalDiagnose getClinicalDiagnosisById(int id) {
        logger.info("MedHelper_LOGS: ClinicalDiagnosisDAOImpl: getting diagnosis by id");
        ClinicalDiagnose clinicalDiagnose = entityManager.find(ClinicalDiagnose.class, id);
        if (clinicalDiagnose != null) {
            logger.info("MedHelper_LOGS: ClinicalDiagnosisDAOImpl: Clinical diagnose with id = {} found successfully", id);
            return clinicalDiagnose;
        }
        logger.info("MedHelper_LOGS: ClinicalDiagnosisDAOImpl: Clinical diagnose with id = {} not found", id);
        return null;
    }


    @Override
    public Set<ClinicalDiagnose> getAllPatientClinicalDiagnosis(int medicalRecordId) {
        logger.info("MedHelper_LOGS: ClinicalDiagnosisDAO: finding clinical diagnosis by medical record id");
        return new HashSet<>(
                entityManager.createQuery(
                        "SELECT cd FROM ClinicalDiagnose cd WHERE cd.medicalRecord.medicalRecordId = :medicalRecordId",
                        ClinicalDiagnose.class)
                        .setParameter("medicalRecordId", medicalRecordId).getResultList());
    }
}
