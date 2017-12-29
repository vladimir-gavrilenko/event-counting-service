package gva.controler;

import gva.domain.Event;
import gva.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/all")
    public List<Event> findAll() {
        return eventService.findAll();
    }
}
