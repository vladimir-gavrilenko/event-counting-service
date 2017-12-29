package gva.service;

import gva.domain.Event;
import gva.repository.EventRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseEventService implements EventService {
    private final DateTimeService dateTimeService;
    private final EventRepository eventRepository;

    @Override
    public Event add(@NonNull Event event) {
        if (event.getTimeStamp() == null) {
            throw new IllegalArgumentException("Timestamp for the event is null: " + event);
        }
        return eventRepository.save(event);
    }

    // TODO
    // provide startTime? Find in repo using 'between'?
    @Override
    public List<Event> findEventsForLast(@NonNull Duration duration) {
        LocalDateTime ts = dateTimeService.now().minus(duration);
        return null;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}
