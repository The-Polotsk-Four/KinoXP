package dk.ek.backend.catalog.dto;

import java.util.Set;

public record SeatDto(Long id, int row, int seatNumber, Set<TicketDto> ticket, HallDto hall) {
}
