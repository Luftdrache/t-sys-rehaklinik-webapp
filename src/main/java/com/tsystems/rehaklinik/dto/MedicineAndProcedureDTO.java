package com.tsystems.rehaklinik.dto;

import com.tsystems.rehaklinik.types.TreatmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineAndProcedureDTO {

    private int medicineProcedureId;

    @NotNull(message = "Medicine or procedure name must be set")
    @NotBlank(message = "Medicine or procedure name mustn't be blank")
    private String medicineProcedureName;


    @NotNull(message = "Treatment type must be set")
    private TreatmentType treatmentType;
}
