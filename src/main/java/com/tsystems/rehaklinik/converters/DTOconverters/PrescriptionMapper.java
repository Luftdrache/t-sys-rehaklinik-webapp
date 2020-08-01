package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.PrescriptionDTO;
import com.tsystems.rehaklinik.entities.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrescriptionMapper {

    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

    PrescriptionDTO toDTO(Prescription prescription);

    Prescription fromDTO(PrescriptionDTO prescriptionDTO);
}
