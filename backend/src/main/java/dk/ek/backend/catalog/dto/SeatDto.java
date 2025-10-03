package dk.ek.backend.catalog.dto;

import java.util.Set;

public record SeatDto(String id, int row, int seatNumber, Set<TicketDto> ticket, HallDto hall) {
}
