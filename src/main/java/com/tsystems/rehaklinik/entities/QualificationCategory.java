package com.tsystems.rehaklinik.entities;

import com.tsystems.rehaklinik.types.QualificationCategories;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "qualification_categories", schema = "rehaklinik",
        uniqueConstraints = @UniqueConstraint(columnNames = "qualification_category",
                name = "UNQ_QUALIFICATION_CATEGORY"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualificationCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_category_id", nullable = false, length = 11)
    private int qualificationCategoryId;

    @NotBlank(message = "Employee's qualification category mustn't be blank or null")
    @Enumerated(EnumType.STRING)
//    @Size(max=50, message = "Qualification category length must be no more than 25 characters")
    @Column(name = "qualification_category", columnDefinition = "ENUM('SECOND', 'FIRST', 'HIGHER')",  nullable = false)
    private QualificationCategories qualificationCategoryName;

    @OneToMany(mappedBy = "qualificationCategory", fetch = FetchType.LAZY)
    private List<Employee> employees;

}

