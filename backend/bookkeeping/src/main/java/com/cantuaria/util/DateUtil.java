package com.cantuaria.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter BR_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter EDF_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, BR_DATE_FORMAT);
    }

    public static String toEDFFormat(LocalDate date) {
        return date.format(EDF_DATE_FORMAT);
    }
}
