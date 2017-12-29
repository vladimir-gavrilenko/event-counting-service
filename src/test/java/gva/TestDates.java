package gva;

import java.time.LocalDateTime;
import java.time.Month;

public interface TestDates {
    LocalDateTime DEC_29_11AM =
            LocalDateTime.of(2017, Month.DECEMBER, 29, 11, 0);
    LocalDateTime DEC_29_NOON =
            LocalDateTime.of(2017, Month.DECEMBER, 29, 12, 0);
    LocalDateTime DEC_29_1PM =
            LocalDateTime.of(2017, Month.DECEMBER, 29, 13, 0);
}
