package gva.service;

import gva.domain.Event;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service
public interface EventService {
    List<Event> findAll();
}
