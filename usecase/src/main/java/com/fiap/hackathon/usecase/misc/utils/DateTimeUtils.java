package com.fiap.hackathon.usecase.misc.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateTimeUtils {

    public static String toDateFormat(LocalDateTime localDateTime) {
        if(localDateTime == null) {
            return "";
        }

        var instant = localDateTime.toInstant(ZoneOffset.UTC);
        var localDateUTC = LocalDateTime.ofInstant(instant, ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return Optional.of(localDateUTC).map(l -> l.format(formatter)).orElse("");
    }

    public static String toHourMinuteFormat(LocalDateTime localDateTime) {
        if(localDateTime == null) {
            return "";
        }

        var instant = localDateTime.toInstant(ZoneOffset.UTC);
        var localDateUTC = LocalDateTime.ofInstant(instant, ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return Optional.of(localDateUTC).map(l -> l.format(formatter)).orElse("");
    }

    public static String toHourMinuteFormatByTotalMinutes(Long minutes) {
        int exceededMinutes = Math.toIntExact(minutes % 60);
        int hours = Math.toIntExact((minutes - exceededMinutes) / 60);
        return String.format("%s hour(s) and %s minute(s)", hours, exceededMinutes);
    }

}
