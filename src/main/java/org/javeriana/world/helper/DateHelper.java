package org.javeriana.world.helper;

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
}
