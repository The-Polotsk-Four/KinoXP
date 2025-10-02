package dk.ek.backend.catalog.dto;

import java.time.LocalDateTime;

public record TicketDto(Long id, double price, boolean status, LocalDateTime timeOfShowing) {
}
