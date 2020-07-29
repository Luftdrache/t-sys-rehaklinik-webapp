package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.MedicalRecord;

public interface MedicalRecordDAO {
    MedicalRecord findMedicalRecordById(int id);
}
