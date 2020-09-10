package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.ClinicalDiagnosis;

import java.util.Set;


/**
 * DAO for {@link ClinicalDiagnosis} objects
 *
 * @author Julia Dalskaya
 */
public interface ClinicalDiagnosisDAO {

    /**
     * creates new clinical diagnosis
     *
     * @param clinicalDiagnosis clinical diagnosis
     * @return created clinical diagnosis
     */
    ClinicalDiagnosis createClinicalDiagnosis(ClinicalDiagnosis clinicalDiagnosis);


    /**
     * Updates clinical diagnosis
     *
     * @param clinicalDiagnose clinical diagnosis
     * @return updated clinical diagnosis
     */
    ClinicalDiagnosis updateClinicalDiagnosis(ClinicalDiagnosis clinicalDiagnose);


    /**
     * Deletes clinical diagnosis
     *
     * @param clinicalDiagnose clinical diagnosis to delete
     * @return boolean result of deleting operation
     */
    boolean deleteClinicalDiagnosis(ClinicalDiagnosis clinicalDiagnose);


    /**
     * Searches for a clinical diagnosis by it's id
     *
     * @param id clinical diagnosis id
     * @return clinical diagnosis found by id
     */
    ClinicalDiagnosis getClinicalDiagnosisById(int id);


    /**
     * Searches for all the patient's clinical diagnoses using his medical record's id
     *
     * @param medicalRecordId medical record id
     * @return Finds clinical diagnosis by its id
     */
    Set<ClinicalDiagnosis> getAllPatientClinicalDiagnosis(int medicalRecordId);
}
