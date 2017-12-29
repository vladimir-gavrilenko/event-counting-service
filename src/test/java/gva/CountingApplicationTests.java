package gva;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static gva.TestUtils.EVENT_JSON;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CountingApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
public class CountingApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addEventAndCheckThatCounterIncreased() throws Exception {
        expectThatCounterIs(0);

        mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(EVENT_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("new event")));

        expectThatCounterIs(1);
    }

    private void expectThatCounterIs(long expectedValue) throws Exception {
        mockMvc.perform(get("/events/count")
                .param("seconds", "60"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.requestedTime").exists())
                .andExpect(jsonPath("$.count", is((int) expectedValue)));
    }
}
