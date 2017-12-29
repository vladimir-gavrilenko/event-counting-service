package gva.repository;

import gva.domain.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EventRepository eventRepository;

    private List<Event> events;

    private static final LocalDateTime DEC_29_11AM =
            LocalDateTime.of(2017, Month.DECEMBER, 29, 11, 0);
    private static final LocalDateTime DEC_29_NOON =
            LocalDateTime.of(2017, Month.DECEMBER, 29, 12, 0);
    private static final LocalDateTime DEC_29_1PM =
            LocalDateTime.of(2017, Month.DECEMBER, 29, 13, 0);

    @Before
    public void setUp() {
        events = Collections.nCopies(3, new Event());
        events.get(0).setTimeStamp(DEC_29_11AM);
        events.get(1).setTimeStamp(DEC_29_NOON);
        events.get(2).setTimeStamp(DEC_29_1PM);
        events.forEach(event -> testEntityManager.persist(event));
        testEntityManager.flush();
    }

    @After
    public void tearDown() {
        events.forEach(event -> testEntityManager.clear());
        testEntityManager.flush();
    }

    @Test
    public void findAll() {
        List<Event> found = eventRepository.findAll();
        assertThat(found.size() == 3);
    }

    @Test
    public void findByTimeStamp() {
        List<Event> found = eventRepository.findByTimeStampIsBetween(DEC_29_NOON, DEC_29_1PM);
        List<LocalDateTime> timeStamps = found.stream().map(Event::getTimeStamp).collect(Collectors.toList());
        assertThat(timeStamps.contains(DEC_29_NOON));
        assertThat(timeStamps.contains(DEC_29_1PM));
        assertThat(!timeStamps.contains(DEC_29_11AM));
    }

    @Test
    public void countByTimeStamp() {
        Long count = eventRepository.countByTimeStampIsBetween(DEC_29_NOON.plusSeconds(1), DEC_29_1PM);
        assertThat(count == 1);

        count = eventRepository.countByTimeStampIsBetween(DEC_29_1PM, DEC_29_1PM.plusSeconds(1));
        assertThat(count == 0);
    }
}
