package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.AuthenticationDataDTO;
import com.tsystems.rehaklinik.entities.AuthenticationData;
import org.mapstruct.factory.Mappers;

public interface AuthenticationDataMapper {

   AuthenticationDataMapper INSTANCE = Mappers.getMapper(AuthenticationDataMapper.class);

   AuthenticationDataDTO toDTO( AuthenticationData clinicalDiagnose);

   AuthenticationData fromDTO(AuthenticationDataDTO clinicalDiagnosisDTO);
}
