package com.tsystems.rehaklinik.converters.stringToEnumConverters;

import com.tsystems.rehaklinik.types.EventStatus;
import org.springframework.core.convert.converter.Converter;

public class EventStatusStringToEnumConverter implements Converter<String, EventStatus> {

    @Override
    public EventStatus convert(String source) {
        return EventStatus.valueOf(source.toUpperCase());
    }
}