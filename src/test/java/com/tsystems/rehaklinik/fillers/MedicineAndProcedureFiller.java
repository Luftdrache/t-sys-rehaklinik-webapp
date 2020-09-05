package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.dto.MedicineAndProcedureDTO;
import com.tsystems.rehaklinik.entities.MedicineAndProcedure;
import com.tsystems.rehaklinik.entities.TreatmentEvent;
import com.tsystems.rehaklinik.types.TreatmentType;

public class MedicineAndProcedureFiller {
    private static final int ID = 1;
    private static final String NAME = "Aspirin";
    private static final TreatmentType TYPE = TreatmentType.MEDICINE;

    public static MedicineAndProcedure getMedicineAndProcedure() {
        MedicineAndProcedure medicineAndProcedure = new MedicineAndProcedure();
        medicineAndProcedure.setMedicineProcedureId(ID);
        medicineAndProcedure.setMedicineProcedureName(NAME);
        medicineAndProcedure.setTreatmentType(TYPE);
        return medicineAndProcedure;
    }

    public static MedicineAndProcedureDTO getMedicineAndProcedureDTO() {
        MedicineAndProcedureDTO medicineAndProcedureDTO = new MedicineAndProcedureDTO();
        medicineAndProcedureDTO.setMedicineProcedureId(ID);
        medicineAndProcedureDTO.setMedicineProcedureName(NAME);
        medicineAndProcedureDTO.setTreatmentType(TYPE);
        return medicineAndProcedureDTO;
    }

}
