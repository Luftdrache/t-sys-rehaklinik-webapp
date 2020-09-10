package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.PrescriptionTreatmentPatternDTO;
import com.tsystems.rehaklinik.entities.Prescription;

public class PrescriptionTreatmentPatternDTOConverter {

    public static Prescription convertFromDTO(
            Prescription prescriptionToEdit, PrescriptionTreatmentPatternDTO prescriptionTreatmentPatternDTO) {
        prescriptionToEdit.getMedicineAndProcedure().
                setMedicineProcedureId(prescriptionTreatmentPatternDTO.getMedicineProcedureId());
        prescriptionToEdit.getMedicineAndProcedure().
                setMedicineProcedureName(prescriptionTreatmentPatternDTO.getMedicineProcedureName());
        prescriptionToEdit.getMedicineAndProcedure().
                setTreatmentType(prescriptionTreatmentPatternDTO.getTreatmentType());
        prescriptionToEdit.setDose(prescriptionTreatmentPatternDTO.getDose());
        prescriptionToEdit.setAdministeringMedicationMethod(prescriptionTreatmentPatternDTO.
                getAdministeringMedicationMethod());
        prescriptionToEdit.setStartTreatment(prescriptionTreatmentPatternDTO.getStartTreatment());
        prescriptionToEdit.setEndTreatment(prescriptionTreatmentPatternDTO.getEndTreatment());
        prescriptionToEdit.getTreatmentTimePattern().
                setTreatmentTimePatternId(prescriptionTreatmentPatternDTO.getTreatmentTimePatternId());
        prescriptionToEdit.getTreatmentTimePattern().
                setIntervalInHours(prescriptionTreatmentPatternDTO.getIntervalInHours());
        prescriptionToEdit.getTreatmentTimePattern().
                setSunday(prescriptionTreatmentPatternDTO.isSunday());
        prescriptionToEdit.getTreatmentTimePattern().
                setMonday(prescriptionTreatmentPatternDTO.isMonday());
        prescriptionToEdit.getTreatmentTimePattern().
                setTuesday(prescriptionTreatmentPatternDTO.isTuesday());
        prescriptionToEdit.getTreatmentTimePattern().
                setWednesday(prescriptionTreatmentPatternDTO.isWednesday());
        prescriptionToEdit.getTreatmentTimePattern().
                setThursday(prescriptionTreatmentPatternDTO.isThursday());
        prescriptionToEdit.getTreatmentTimePattern().
                setFriday(prescriptionTreatmentPatternDTO.isFriday());
        prescriptionToEdit.getTreatmentTimePattern().
                setSaturday(prescriptionTreatmentPatternDTO.isSaturday());
        prescriptionToEdit.getTreatmentTimePattern().
                setBeforeMeals(prescriptionTreatmentPatternDTO.isBeforeMeals());
        prescriptionToEdit.getTreatmentTimePattern().
                setAfterMeals(prescriptionTreatmentPatternDTO.isAfterMeals());
        prescriptionToEdit.getTreatmentTimePattern().
                setAtMeals(prescriptionTreatmentPatternDTO.isAtMeals());
        prescriptionToEdit.getTreatmentTimePattern().
                setPrecisionTime(prescriptionTreatmentPatternDTO.getPrecisionTime());
        return prescriptionToEdit;
    }


}
