package gva.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SystemDateTimeService implements DateTimeService {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
