package com.tsystems.rehaklinik.converters.stringToEnumConverters;

import com.tsystems.rehaklinik.types.HospitalStayStatus;
import org.springframework.core.convert.converter.Converter;

public class HospitalStayStatusStringToEnumConverter implements Converter<String, HospitalStayStatus> {
    @Override
    public HospitalStayStatus convert(String source) {
       source = source.equals("Being Treated") ? source = "BEING_TREATED" : source;
       return HospitalStayStatus.valueOf(source.toUpperCase());
    }
}