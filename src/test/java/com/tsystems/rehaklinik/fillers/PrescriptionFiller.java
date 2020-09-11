package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.dto.PrescriptionDTO;
import com.tsystems.rehaklinik.dto.PrescriptionTreatmentPatternDTO;
import com.tsystems.rehaklinik.entities.Prescription;
import com.tsystems.rehaklinik.types.PrescriptionStatus;
import com.tsystems.rehaklinik.types.TreatmentType;

import java.time.LocalDate;

public class PrescriptionFiller {
    private static final int ID = 1;
    private static final LocalDate START = LocalDate.of(2020, 11, 20);
    private static final LocalDate END = LocalDate.of(2020, 12, 1);
    private static final String NAME = "Aspirin";
    private static final String DOSE = "50 mg";
    private static final String METHOD = "";
    private static final PrescriptionStatus STATUS = PrescriptionStatus.DONE;


    public static Prescription getPrescription() {
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(ID);
        prescription.setMedicineAndProcedure(MedicineAndProcedureFiller.getMedicineAndProcedure());
        prescription.setDose(DOSE);
        prescription.setAdministeringMedicationMethod(METHOD);
        prescription.setPatient(PatientFiller.getPatient());
        prescription.setStartTreatment(START);
        prescription.setEndTreatment(END);
        prescription.setTreatmentTimePattern(TreatmentTimePatternFiller.getPTreatmentTimePattern());
        prescription.setPrescriptionStatus(STATUS);
        return prescription;
    }

    public static PrescriptionDTO getPrescriptionDTO() {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setPrescriptionId(ID);
        prescriptionDTO.setMedicineAndProcedure(MedicineAndProcedureFiller.getMedicineAndProcedureDTO());
        prescriptionDTO.setDose(DOSE);
        prescriptionDTO.setAdministeringMedicationMethod(METHOD);
        prescriptionDTO.setPatient(PatientFiller.getPatientDTO());
        prescriptionDTO.setStartTreatment(START);
        prescriptionDTO.setEndTreatment(END);
        prescriptionDTO.setTreatmentTimePattern(TreatmentTimePatternFiller.getPTreatmentTimePatternDTO());
        return prescriptionDTO;
    }

    public static PrescriptionTreatmentPatternDTO getPrescriptionTreatmentPatternDTO() {
        PrescriptionTreatmentPatternDTO prescription = new PrescriptionTreatmentPatternDTO();
        prescription.setPrescriptionId(ID);
        prescription.setMedicineProcedureId(ID);
        prescription.setMedicineProcedureName(NAME);
        prescription.setTreatmentType(TreatmentType.MEDICINE);
        prescription.setDose(DOSE);
        prescription.setAdministeringMedicationMethod("");
        prescription.setStartTreatment(START);
        prescription.setEndTreatment(END);
        return prescription;
    }
}
