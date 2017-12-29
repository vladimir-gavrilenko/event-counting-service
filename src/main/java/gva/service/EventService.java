package gva.service;

import gva.domain.Event;

import java.time.Duration;
import java.util.List;

public interface EventService {
    Event add(Event event);

    List<Event> findEventsForLast(Duration duration); // TODO count, startTime

    List<Event> findAll(); // for debugging
}
