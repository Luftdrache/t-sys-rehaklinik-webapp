package com.tsystems.rehaklinik.converters.stringToEnumConverters;

import com.tsystems.rehaklinik.types.PrescriptionStatus;
import org.springframework.core.convert.converter.Converter;

public class PrescriptionStatusStringToEnumConverter implements Converter<String, PrescriptionStatus> {
    @Override
    public PrescriptionStatus convert(String source) {
        return PrescriptionStatus.valueOf(source.toUpperCase());
    }
}
