package com.tsystems.rehaklinik.converters;

import com.tsystems.rehaklinik.types.HospitalStayStatus;
import org.springframework.core.convert.converter.Converter;

public class HospitalStayStatusStringToEnumConverter implements Converter<String, HospitalStayStatus> {
    @Override
    public HospitalStayStatus convert(String source) {
        return HospitalStayStatus.valueOf(source.toUpperCase());
    }
}