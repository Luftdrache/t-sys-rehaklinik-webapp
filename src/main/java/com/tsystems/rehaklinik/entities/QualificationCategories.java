package com.tsystems.rehaklinik.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "qualification_categories", schema = "rehaklinik")
public class QualificationCategories implements Serializable {
    private int qualificationCategoryId;
    private String qualificationCategory;
    private List<Employees> employees;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_category_id", nullable = false, length = 11)
    public int getQualificationCategoryId() {
        return qualificationCategoryId;
    }

    @Column(name = "qualification_category", nullable = false, unique = true, length = 25)
    public String getQualificationCategory() {
        return qualificationCategory;
    }

    @OneToMany(mappedBy = "qualificationCategory")
    public List<Employees> getEmployees() {
        return employees;
    }

    public void setQualificationCategoryId(int qualificationCategoryId) {
        this.qualificationCategoryId = qualificationCategoryId;
    }

    public void setQualificationCategory(String qualificationCategory) {
        this.qualificationCategory = qualificationCategory;
    }

    public void setEmployees(List<Employees> employeesByQualificationCategoryId) {
        this.employees = employeesByQualificationCategoryId;
    }
}
