package by.ruslan.web.util;

import by.ruslan.web.exception.ServiceException;

import javax.servlet.jsp.tagext.TagSupport;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code DateParser} class is responsible for date parsing
 *
 * @author Ruslan Nedvedskiy
 */
public class DateParser {

    /**
     * Parse string to date and time
     *
     * @param dateAndTimeStr date and time in string format
     * @return Timestamp object
     * @throws DateTimeParseException the DateTimeParseException exception
     */
    public static Timestamp parseDate(String dateAndTimeStr) throws DateTimeParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(dateAndTimeStr, DateTimeFormatter.ISO_DATE_TIME);;
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }
}
