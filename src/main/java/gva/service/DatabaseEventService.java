package gva.service;

import gva.domain.Event;
import gva.repository.EventRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseEventService implements EventService {
    private final EventRepository eventRepository;

    @Override
    public Event add(@NonNull Event event) {
        if (event.getTimeStamp() == null) {
            throw new RuntimeException("Timestamp for the event is null: " + event);
        }
        return eventRepository.save(event);
    }

    @Override
    public long countForPeriod(LocalDateTime from, LocalDateTime to) {
        return eventRepository.countByTimeStampIsBetween(from, to);
    }


    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}
