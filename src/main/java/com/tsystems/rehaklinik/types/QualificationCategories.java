package com.tsystems.rehaklinik.types;

public enum QualificationCategories {
    NONE("None"),
    SECOND("Second"),
    FIRST("First"),
    HIGHER("Higher");

    private String qualificationCategory;

    QualificationCategories(String qualificationCategory) {
        this.qualificationCategory = qualificationCategory;
    }

    public String getQualificationCategory() {
        return qualificationCategory;
    }
}
