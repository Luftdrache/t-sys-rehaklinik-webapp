package com.tsystems.rehaklinik.converters.stringToEnumConverters;

import com.tsystems.rehaklinik.types.Gender;
import org.springframework.core.convert.converter.Converter;

public class GenderStringToEnumConverter  implements Converter<String, Gender> {
    @Override
    public Gender convert(String source) {
        return Gender.valueOf(source.toUpperCase());
    }
}
