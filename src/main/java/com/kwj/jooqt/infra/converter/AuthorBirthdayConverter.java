package com.kwj.jooqt.infra.converter;

import org.jooq.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AuthorBirthdayConverter implements Converter<String, String> {

    private static final DateTimeFormatter CONTINUED_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter SPLIT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String from(String databaseObject) {
        LocalDate dateTime = LocalDate.parse(databaseObject, CONTINUED_FORMATTER);
        return SPLIT_FORMATTER.format(dateTime);
    }

    @Override
    public String to(String userObject) {
        LocalDate dateTime = LocalDate.parse(userObject, SPLIT_FORMATTER);
        return CONTINUED_FORMATTER.format(dateTime);
    }

    @Override
    public Class<String> fromType() {
        return String.class;
    }

    @Override
    public Class<String> toType() {
        return String.class;
    }

}