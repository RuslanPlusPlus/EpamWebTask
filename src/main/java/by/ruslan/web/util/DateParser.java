package by.ruslan.web.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    public static Timestamp parseDate(String dateAndTimeStr) throws DateTimeParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(dateAndTimeStr, DateTimeFormatter.ISO_DATE_TIME);;
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }
}
