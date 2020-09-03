package com.tsystems.rehaklinik.fillers;

import com.tsystems.rehaklinik.dto.MedicalRecordDTO;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import com.tsystems.rehaklinik.types.HospitalStayStatus;

public class MedicalRecordFiller {
    private static final int ID = 1;
    private static final HospitalStayStatus STATUS = HospitalStayStatus.NEW;

    public MedicalRecordFiller() {
    }

    public static MedicalRecordDTO getMedicalRecordDTO() {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setMedicalRecordId(ID);
        medicalRecordDTO.setHospitalStayStatus(STATUS);
        return medicalRecordDTO;
    }

    public static MedicalRecord getMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setMedicalRecordId(ID);
        medicalRecord.setHospitalStayStatus(STATUS);
        return medicalRecord;
    }


}
