package dk.ek.backend.catalog.dto;

import java.util.List;

public record HallDto(Long id, List<SeatDto> seats) {
}
