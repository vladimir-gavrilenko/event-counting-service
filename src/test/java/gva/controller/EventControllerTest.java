package gva.controller;

import gva.TestUtils;
import gva.controler.EventController;
import gva.domain.Event;
import gva.service.DateTimeService;
import gva.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static gva.TestUtils.EVENT_JSON;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EventController.class)
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @MockBean
    private DateTimeService dateTimeService;

    @Before
    public void setUp() {
        List<Event> events = TestUtils.testEvents();
        Event event = new Event();

        event.setId(1L);
        event.setTimeStamp(TestUtils.DEC_29_1PM.plusSeconds(1L));
        event.setDescription("new event");

        given(dateTimeService.now()).willReturn(TestUtils.DEC_29_1PM.plusSeconds(1L));
        given(eventService.add(any(Event.class))).willReturn(event);
        given(eventService.findAll()).willReturn(events);
        given(eventService.countForPeriod(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(2L);
    }

    @Test
    public void addingEvent() throws Exception {
        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(EVENT_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.description", is("new event")));
    }

    @Test
    public void addingEventWithWrongContentType() throws Exception {
        mockMvc.perform(post("/events")
                        .contentType(MediaType.ALL)
                        .content(EVENT_JSON))
                    .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void addingEventWithBadContent() throws Exception {
        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{}"))
                    .andExpect(status().isBadRequest());
    }

    @Test
    public void testCount() throws Exception {
        mockMvc.perform(get("/events/count")
                        .param("seconds", "60"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.requestedTime").exists())
                .andExpect(jsonPath("$.count", is(2)));
    }

    @Test
    public void testCountWithBadParams() throws Exception {
        mockMvc.perform(get("/events/count").param("s", "10"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/events/count"))
                .andExpect(status().isBadRequest());
    }
}
