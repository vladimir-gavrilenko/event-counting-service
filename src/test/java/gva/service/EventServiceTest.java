package gva.service;

import gva.domain.Event;
import gva.repository.EventRepository;
import gva.service.config.DateTimeServiceConfiguration;
import gva.service.config.EventServiceTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static gva.TestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest
@Import({ EventServiceTestConfiguration.class, DateTimeServiceConfiguration.class })
public class EventServiceTest {
    @Autowired
    private EventService eventService;

    @Autowired
    private DateTimeService dateTimeService;

    @MockBean
    private EventRepository eventRepository;

    @Before
    public void setUp() {
        Event event = new Event();
        event.setId(1_000_000_000L);
        event.setTimeStamp(dateTimeService.now());
        event.setDescription("test");

        given(eventRepository.countByTimeStampIsBetween(DEC_29_11AM, DEC_29_NOON))
                .willReturn(10L);
        given(eventRepository.countByTimeStampIsBetween(DEC_29_NOON, DEC_29_1PM))
                .willReturn(20L);
        given(eventRepository.countByTimeStampIsBetween(DEC_29_11AM, DEC_29_1PM))
                .willReturn(30L);
        given(eventRepository.save(any(Event.class))).willReturn(event);
    }

    @Test
    public void gettingEventCount() {
        long count;

        count = eventService.countForPeriod(DEC_29_11AM, DEC_29_NOON);
        assertEquals(10L, count);

        count = eventService.countForPeriod(DEC_29_NOON, DEC_29_1PM);
        assertEquals(20L, count);

        count = eventService.countForPeriod(DEC_29_11AM, DEC_29_1PM);
        assertEquals(30L, count);
    }

    @Test
    public void addingValidEvent() {
        Event localEvent = new Event();
        localEvent.setTimeStamp(dateTimeService.now());
        localEvent = eventService.add(localEvent);
        assertEquals(localEvent.getId().longValue(), 1_000_000_000L);
    }

    @Test(expected = RuntimeException.class)
    public void addingInvalidEvent() {
        Event localEvent = new Event();
        localEvent.setTimeStamp(null);
        eventService.add(localEvent);
    }
}
