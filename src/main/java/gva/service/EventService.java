package gva.service;

import gva.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    Event add(Event event);

    long countForPeriod(LocalDateTime from, LocalDateTime to);

    List<Event> findAll();
}
