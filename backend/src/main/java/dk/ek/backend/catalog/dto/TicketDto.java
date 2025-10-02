package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.Seat;
import dk.ek.backend.catalog.model.Show;

import java.time.LocalDateTime;

public record TicketDto(Long id, double price, boolean status, LocalDateTime timeOfShowing, Show show, Seat seat) {
}
