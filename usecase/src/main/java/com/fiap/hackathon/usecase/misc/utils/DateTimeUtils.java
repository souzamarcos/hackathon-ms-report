package com.fiap.hackathon.usecase.misc.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static String toDateFormat(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDateTime.format(formatter);
    }

    public static String toHourMinuteFormat(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localDateTime.format(formatter);
    }

    public static String toHourMinuteFormatByTotalMinutes(Long minutes) {
        int exceededMinutes = Math.toIntExact(minutes % 60);
        int hours = Math.toIntExact((minutes - exceededMinutes) / 60);
        return String.format("%s hour(s) and %s minute(s)", hours, exceededMinutes);
    }

}
