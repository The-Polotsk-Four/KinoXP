package dk.ek.backend.catalog.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ShowDto(Long id, HallDto movie, LocalDateTime timeOfShowing, HallDto hall, List<TicketDto> tickets) {
}
