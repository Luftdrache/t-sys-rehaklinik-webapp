package com.tsystems.rehaklinik.converters.stringToEnumConverters;

import com.tsystems.rehaklinik.types.Roles;
import org.springframework.core.convert.converter.Converter;

public class RolesStringToEnumConverter implements Converter<String, Roles> {
    @Override
    public Roles convert(String source) {
        return Roles.valueOf(source.toUpperCase());
    }
}
