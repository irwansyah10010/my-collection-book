package com.lawencon.readcollection.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DatetimeUtil {
    
    public static LocalDate epochMillisToLocalDate(Long epochMilis){
        return Instant.ofEpochMilli(epochMilis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Long localDateToEpochMilli(LocalDate date){
        return date.atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli();
    }


    public static long toMillis(int year, int month, int day) {
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, 0, 0, 0);
        return dateTime.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }
}
