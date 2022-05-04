package org.javeriana.world.helper;

import org.javeriana.util.WorldConfiguration;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateHelper {

    public static int differenceDaysBetweenTwoDates(String date1, String date2) {
        WorldConfiguration config = WorldConfiguration.getPropsInstance();
        String dateFormat = config.getProperty("date.format");
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
        DateTime dateTime1 = formatter.parseDateTime(date1);
        DateTime dateTime2 = formatter.parseDateTime(date2);
        int daysDifference = Days.daysBetween(dateTime1,dateTime2).getDays();
        return Days.daysBetween(dateTime1,dateTime2).getDays();
    }

    public static DateTime getDateInJoda(String date) {
        WorldConfiguration config = WorldConfiguration.getPropsInstance();
        String dateFormat = config.getProperty("date.format");
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
        return formatter.parseDateTime(date);
    }

    public static int getMonthFromStringDate(String date) {
        return getDateInJoda(date).getMonthOfYear()-1;
    }
}
