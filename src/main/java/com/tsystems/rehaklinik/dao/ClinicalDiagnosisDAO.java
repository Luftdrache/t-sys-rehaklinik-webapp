package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.ClinicalDiagnose;

import java.util.Set;

public interface ClinicalDiagnosisDAO {

    ClinicalDiagnose createClinicalDiagnosis(ClinicalDiagnose clinicalDiagnosis);

    ClinicalDiagnose updateClinicalDiagnosis(ClinicalDiagnose clinicalDiagnose);

    boolean deleteClinicalDiagnosis(int id);

    ClinicalDiagnose getClinicalDiagnosisById(int id);

    Set<ClinicalDiagnose> getAllPatientClinicalDiagnosis(int medicalRecordId);
}
