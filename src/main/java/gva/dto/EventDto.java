package gva.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EventDto {
    @NotNull
    private String description;
}
