package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.Hall;
import dk.ek.backend.catalog.model.Ticket;

public record SeatDto(String id, int row, int seatNumber, Ticket ticket, Hall hall) {
}
