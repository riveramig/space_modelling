package org.javeriana.world.helper;

import org.javeriana.util.WorldConfiguration;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    public static int getMonthFromStringDate(String date, String format) throws ParseException {
        Date dateObj = new SimpleDateFormat(format).parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateObj);
        return calendar.get(Calendar.MONTH);
    }

    public static int differenceDaysBetweenTwoDates(String date1, String date2) {
        WorldConfiguration config = WorldConfiguration.getPropsInstance();
        String dateFormat = config.getProperty("date.format");
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
        DateTime dateTime1 = formatter.parseDateTime(date1);
        DateTime dateTime2 = formatter.parseDateTime(date2);
        return Days.daysBetween(dateTime1,dateTime2).getDays();
    }

    public static DateTime getDateInJoda(String date) {
        WorldConfiguration config = WorldConfiguration.getPropsInstance();
        String dateFormat = config.getProperty("date.format");
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
        return formatter.parseDateTime(date);
    }
}
