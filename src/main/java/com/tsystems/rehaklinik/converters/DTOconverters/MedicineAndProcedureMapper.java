package com.tsystems.rehaklinik.converters.DTOconverters;

import com.tsystems.rehaklinik.dto.MedicineAndProcedureDTO;
import com.tsystems.rehaklinik.entities.MedicineAndProcedure;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicineAndProcedureMapper {

    MedicineAndProcedureMapper INSTANCE = Mappers.getMapper(MedicineAndProcedureMapper.class);

    MedicineAndProcedureDTO toDTO(MedicineAndProcedure medicineAndProcedure);

    MedicineAndProcedure fromDTO(MedicineAndProcedureDTO medicineAndProcedureDTO);
}
