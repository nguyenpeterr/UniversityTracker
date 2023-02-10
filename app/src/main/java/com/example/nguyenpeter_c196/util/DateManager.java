package com.example.nguyenpeter_c196.util;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateManager {
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyy");

    public static Long toMillisec(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy");
        Date reminderDate = null;
        try {
            reminderDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (reminderDate != null) {
            return reminderDate.getTime();
        }
        return (long) -1;
    }

    public static String date(int year, int month, int day) {
        return dateFormat.format(LocalDate.of(year, month, day));
    }
}
