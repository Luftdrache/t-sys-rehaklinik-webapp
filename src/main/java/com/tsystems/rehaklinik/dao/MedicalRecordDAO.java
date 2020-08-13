package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.MedicalRecord;


/**
 * DAO for {@link MedicalRecord} objects
 *
 * @author Julia Dalskaya
 */
public interface MedicalRecordDAO {

    /**
     * Searches medical record by it's id
     *
     * @param id medical record id
     * @return found medical record
     */
    MedicalRecord findMedicalRecordById(int id);


    /**
     * Updates medical record's data
     *
     * @param editedMedicalRecord edited medical record
     * @return updated medical record
     */
    MedicalRecord updateMedicalRecord(MedicalRecord editedMedicalRecord);
}
