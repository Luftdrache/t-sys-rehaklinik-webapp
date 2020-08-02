package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.TreatmentType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "medicines_and_procedures", schema = "rehaklinik",
        uniqueConstraints = @UniqueConstraint(columnNames = "medicine_procedure_name",
                name = "UNQ_MEDICINE_PROCEDURE_NAME"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineAndProcedure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_procedure_id", nullable = false, length = 11)
    private int medicineProcedureId;


    @Column(name = "medicine_procedure_name", nullable = false, length = 100)
    private String medicineProcedureName;


    @Enumerated(EnumType.STRING)
    @Column(name = "treatment_type", columnDefinition ="ENUM ('MEDICINE', 'PROCEDURE')", nullable = false, length = 9)
    private TreatmentType treatmentType;
}
