package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.ClinicalDiagnose;
import com.tsystems.rehaklinik.exceptions.WrongIdException;

import java.util.Set;


/**
 * DAO for {@link ClinicalDiagnose} objects
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
    ClinicalDiagnose createClinicalDiagnosis(ClinicalDiagnose clinicalDiagnosis);


    /**
     * Updates clinical diagnosis
     *
     * @param clinicalDiagnose clinical diagnosis
     * @return updated clinical diagnosis
     */
    ClinicalDiagnose updateClinicalDiagnosis(ClinicalDiagnose clinicalDiagnose);


    /**
     * Deletes clinical diagnosis
     *
     * @param clinicalDiagnose clinical diagnosis to delete
     * @return boolean result of deleting operation
     */
    boolean deleteClinicalDiagnosis(ClinicalDiagnose clinicalDiagnose);


    /**
     * Searches for a clinical diagnosis by it's id
     *
     * @param id clinical diagnosis id
     * @return clinical diagnosis found by id
     */
    ClinicalDiagnose getClinicalDiagnosisById(int id) throws WrongIdException;


    /**
     * Searches for all the patient's clinical diagnoses using his medical record's id
     *
     * @param medicalRecordId medical record id
     * @return Finds clinical diagnosis by its id
     */
    Set<ClinicalDiagnose> getAllPatientClinicalDiagnosis(int medicalRecordId);
}
