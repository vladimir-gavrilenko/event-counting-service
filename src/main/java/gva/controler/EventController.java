package gva.controler;

import gva.domain.Event;
import gva.dto.EventDto;
import gva.dto.EventsCountDto;
import gva.service.DateTimeService;
import gva.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/events", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final DateTimeService dateTimeService;

    @GetMapping("/all")
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/count")
    public ResponseEntity<EventsCountDto> findLast(@RequestParam("seconds") long seconds) {
        LocalDateTime requestedTime = dateTimeService.now();
        EventsCountDto eventsCountDto = new EventsCountDto();
        eventsCountDto.setRequestedTime(requestedTime);
        long count = eventService.countForPeriod(requestedTime.minusSeconds(seconds), requestedTime);
        eventsCountDto.setCount(count);
        return new ResponseEntity<>(eventsCountDto, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> addEvent(@Valid @RequestBody EventDto eventDto) {
        Event event = new Event();
        event.setTimeStamp(dateTimeService.now());
        event.setDescription(eventDto.getDescription());
        event = eventService.add(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }
}
