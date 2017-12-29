package gva.service.config;

import gva.repository.EventRepository;
import gva.service.DatabaseEventService;
import gva.service.DateTimeService;
import gva.service.EventService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(DateTimeServiceConfiguration.class)
public class EventServiceTestConfiguration {
    @Bean
    @Autowired
    @Qualifier("test")
    public EventService eventService(DateTimeService dateTimeService, EventRepository eventRepository) {
        return new DatabaseEventService(dateTimeService, eventRepository);
    }

    @Bean
    @Qualifier("test")
    public EventRepository eventRepository() {
        return Mockito.mock(EventRepository.class);
    }
}
