package com.grupo13.elasticSearch.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class TranslateDate {
    public static Date FromLocalDateToDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(value, formatter);
        ZoneId defaultZoneId = ZoneId.systemDefault();

        return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
    }
}
