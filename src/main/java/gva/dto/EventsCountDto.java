package gva.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventsCountDto {
    private LocalDateTime requestedTime;
    private long count;
}
