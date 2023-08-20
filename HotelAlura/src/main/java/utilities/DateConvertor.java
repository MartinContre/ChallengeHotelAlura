package utilities;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateConvertor {
    private DateConvertor() {
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
