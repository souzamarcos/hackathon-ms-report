package com.fiap.hackathon.usecase.utils;

import com.fiap.hackathon.usecase.misc.utils.DateTimeUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeUtilsTest {

    @Test
    void toDateFormat() {
        var localDateTime = LocalDateTime.of(2024, 3, 20, 10, 14, 59, 6);
        var expected = "20/03/2024";

        var actual = DateTimeUtils.toDateFormat(localDateTime);

        assertEquals(expected, actual);
    }

    @Test
    void toDateFormatNull() {
        LocalDateTime localDateTime = null;
        var expected = "";

        var actual = DateTimeUtils.toDateFormat(localDateTime);

        assertEquals(expected, actual);
    }

    @Test
    void toHourMinuteFormatByLocalDateTime() {
        var localDateTime = LocalDateTime.of(2024, 3, 20, 22, 14, 59, 6);
        var expected = "19:14";

        var actual = DateTimeUtils.toHourMinuteFormat(localDateTime);

        assertEquals(expected, actual);
    }

    @Test
    void toHourMinuteFormatByLocalDateTimeNull() {
        LocalDateTime localDateTime = null;
        var expected = "";

        var actual = DateTimeUtils.toHourMinuteFormat(localDateTime);

        assertEquals(expected, actual);
    }

    @Test
    void toHourMinuteFormatByLongMinutes() {
        var minutes = 539L;
        var expected = "8 hour(s) and 59 minute(s)";

        var actual = DateTimeUtils.toHourMinuteFormatByTotalMinutes(minutes);

        assertEquals(expected, actual);
    }
}
