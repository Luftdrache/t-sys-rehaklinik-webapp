package com.tsystems.rehaklinik.types;

public enum QualificationCategories {
    SECOND("Second"),
    FIRST("First"),
    HIGHER("Higher"),
    NONE("None");

    private String qualificationCategory;

    QualificationCategories(String qualificationCategory) {
        this.qualificationCategory = qualificationCategory;
    }

    public String getQualificationCategory() {
        return qualificationCategory;
    }


    public void setQualificationCategory(String qualificationCategory) {
        this.qualificationCategory = qualificationCategory;
    }

    @Override
    public String toString() {
        return qualificationCategory;
    }
}
