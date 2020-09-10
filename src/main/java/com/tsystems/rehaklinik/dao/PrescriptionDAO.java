package com.tsystems.rehaklinik.dao;


import com.tsystems.rehaklinik.entities.Prescription;

import java.time.LocalDate;
import java.time.LocalTime;
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


    /**
     * Checks whether such a prescription is already in the database
     *
     * @param medicineOrProcedureName name of a medicine or a procedure
     * @param startTreatment          date of a start  treatment period
     * @param endTreatment            date of an end  treatment period
     * @return boolean result of operation
     */
    boolean checkTheDuplicatePrescriptionAssignment(
            int patientId, String medicineOrProcedureName, LocalDate startTreatment, LocalDate endTreatment);


    List<Prescription> checkOtherPrescriptionOnSameDateAndTime(
            int patientId, LocalDate startTreatment, LocalDate endTreatment, LocalTime time);


}
