package gva;

import gva.domain.Event;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

public interface TestUtils {
    LocalDateTime DEC_29_11AM =
            LocalDateTime.of(2017, Month.DECEMBER, 29, 11, 0);
    LocalDateTime DEC_29_NOON =
            LocalDateTime.of(2017, Month.DECEMBER, 29, 12, 0);
    LocalDateTime DEC_29_1PM =
            LocalDateTime.of(2017, Month.DECEMBER, 29, 13, 0);

    static List<Event> testEvents() {
        List<Event> events = Collections.nCopies(3, new Event());

        events.get(0).setTimeStamp(DEC_29_11AM);
        events.get(1).setTimeStamp(DEC_29_NOON);
        events.get(2).setTimeStamp(DEC_29_1PM);

        events.get(0).setDescription("one");
        events.get(1).setDescription("two");
        events.get(2).setDescription("three");

        return events;
    }
}
