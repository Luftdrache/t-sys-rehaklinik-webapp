package com.tsystems.rehaklinik.converters.DTOconverters;


import com.tsystems.rehaklinik.dto.MedicalRecordDTO;
import com.tsystems.rehaklinik.entities.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicalRecordMapper {
    MedicalRecordMapper INSTANCE = Mappers.getMapper(MedicalRecordMapper.class);

    MedicalRecordDTO toDTO(MedicalRecord medicalRecord);

    MedicalRecord fromDTO(MedicalRecordDTO medicalRecordDTO);

}
