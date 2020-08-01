package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.ClinicalDiagnosisDTO;
import com.tsystems.rehaklinik.entities.ClinicalDiagnose;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClinicalDiagnoseMapper {

    ClinicalDiagnoseMapper INSTANCE = Mappers.getMapper(ClinicalDiagnoseMapper.class);

    ClinicalDiagnosisDTO toDTO(ClinicalDiagnose clinicalDiagnose);

    ClinicalDiagnose fromDTO(ClinicalDiagnosisDTO clinicalDiagnosisDTO);
}
