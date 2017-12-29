package gva.controler;

import gva.domain.Event;
import gva.dto.EventDto;
import gva.service.DateTimeService;
import gva.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final DateTimeService dateTimeService;

    @GetMapping("/all")
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> count(@Valid @RequestBody EventDto eventDto) {
        Event event = new Event();
        event.setTimeStamp(dateTimeService.now());
        event.setDescription(eventDto.getDescription());
        event = eventService.add(event);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }
}
