package com.tsystems.rehaklinik.dao;


import com.tsystems.rehaklinik.entities.Prescription;

import java.util.List;


/**
 * DAO for {@link Prescription} objects
 *
 * @author Julia Dalskaya
 */
public interface PrescriptionDAO {

    /**
     * Creates new prescription
     *
     * @param prescription new prescription
     * @return created prescription
     */
    Prescription createPrescription(Prescription prescription);


    /**
     * Searches for all the patient's prescriptions by patient's id
     *
     * @param id patient's id
     * @return found prescriptions list
     */
    List<Prescription> fidAllPrescriptionsByPatientId(int id);


    /**
     * Deletes prescription by it's id
     *
     * @param id prescription's id
     * @return boolean result of deleting operation
     */
    boolean deletePrescriptionById(int id);


    /**
     * Updates prescription
     *
     * @param editedPrescription edited prescription
     * @return updated prescription
     */
    Prescription updatePrescription(Prescription editedPrescription);


    /**
     * Searches for prescription by it's id
     *
     * @param id prescription's id
     * @return found prescription
     */
    Prescription findPrescriptionById(int id);
}
