package gva.service.config;

import gva.repository.EventRepository;
import gva.service.DatabaseEventService;
import gva.service.EventService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class EventServiceTestConfiguration {
    @Bean
    @Qualifier("test")
    public EventService eventService(EventRepository eventRepository) {
        return new DatabaseEventService(eventRepository);
    }

    @Bean
    @Qualifier("test")
    public EventRepository eventRepository() {
        return Mockito.mock(EventRepository.class);
    }
}
