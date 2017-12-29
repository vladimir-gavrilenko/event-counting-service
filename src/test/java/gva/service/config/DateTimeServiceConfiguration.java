package gva.service.config;

import gva.service.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TestConfiguration
public class DateTimeServiceConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    public DateTimeService dateTimeService() {
        String currentTimeStr = environment.getProperty("current.time");
        String currentTimeFormatStr = environment.getProperty("current.time.format");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(currentTimeFormatStr);
        LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);
        return new TestDateTimeService(currentTime);
    }

    private static class TestDateTimeService implements DateTimeService {
        private final LocalDateTime now;

        private TestDateTimeService(LocalDateTime now) {
            this.now = now;
        }

        @Override
        public LocalDateTime now() {
            return now;
        }
    }
}
