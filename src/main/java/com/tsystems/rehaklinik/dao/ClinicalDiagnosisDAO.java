package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.ClinicalDiagnose;

public interface ClinicalDiagnosisDAO {

    ClinicalDiagnose createClinicalDiagnosis(ClinicalDiagnose clinicalDiagnosis);

    ClinicalDiagnose updateClinicalDiagnosis(ClinicalDiagnose clinicalDiagnose);
}
