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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineAndProcedure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_procedure_id", nullable = false, length = 11)
    private int medicineProcedureId;

    @NotNull(message = "Medicine or procedure name must be set")
    @NotBlank(message = "Medicine or procedure name mustn't be blank")
    @Column(name = "medicine_procedure_name", nullable = false, length = 100)
    private String medicineProcedureName;

    @NotNull(message = "Treatment type must be set")
    @Enumerated(EnumType.STRING)
    @Column(name = "treatment_type", columnDefinition ="ENUM ('MEDICINE', 'PROCEDURE')", nullable = false, length = 9)
    private TreatmentType treatmentType;

    @OneToMany(mappedBy = "medicineAndProcedure")
    private List<Prescription> prescriptions;
}
