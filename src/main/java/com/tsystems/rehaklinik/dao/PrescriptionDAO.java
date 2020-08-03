package com.tsystems.rehaklinik.dao;

import com.tsystems.rehaklinik.entities.Prescription;

import java.util.List;

public interface PrescriptionDAO {
    Prescription createPrescription(Prescription prescription);

    List<Prescription> fidAllPrescriptionsByPatientId(int id);

    boolean deletePrescriptionById(int id);

    Prescription updatePrescription(Prescription editedPrescription);

    Prescription findPrescriptionById(int id);
}
