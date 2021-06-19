package by.ruslan.web.util;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateParser {
    public static Date parseDate(String stringDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date(dateFormat.parse(stringDate).getTime());
        return date;
    }
}
