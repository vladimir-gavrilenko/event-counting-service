package gva.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime timeStamp;
    private String description;
}
