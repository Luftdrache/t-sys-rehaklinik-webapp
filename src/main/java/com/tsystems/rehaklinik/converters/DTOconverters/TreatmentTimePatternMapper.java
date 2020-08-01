package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.TreatmentTimePatternDTO;
import com.tsystems.rehaklinik.entities.TreatmentTimePattern;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TreatmentTimePatternMapper {

    TreatmentTimePatternMapper INSTANCE = Mappers.getMapper( TreatmentTimePatternMapper.class);

    TreatmentTimePatternDTO toDTO(TreatmentTimePattern treatmentTimePattern);

    TreatmentTimePattern fromDTO(TreatmentTimePatternDTO treatmentTimePatternDTO);
}
