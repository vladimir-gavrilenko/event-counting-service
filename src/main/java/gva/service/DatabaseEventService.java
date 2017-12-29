package gva.service;

import gva.domain.Event;
import gva.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseEventService implements EventService {
    private final EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}
