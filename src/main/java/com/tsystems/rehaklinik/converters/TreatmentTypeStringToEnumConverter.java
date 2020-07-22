package com.tsystems.rehaklinik.converters;

import com.tsystems.rehaklinik.types.TreatmentType;
import org.springframework.core.convert.converter.Converter;

public class TreatmentTypeStringToEnumConverter implements Converter<String, TreatmentType> {

    @Override
    public TreatmentType convert(String source) {
        return TreatmentType.valueOf(source.toUpperCase());
    }
}