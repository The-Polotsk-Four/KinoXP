package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.Seat;
import dk.ek.backend.catalog.model.Show;

import java.util.List;

public record HallDto(Long id, List<Seat> seat, List<Show> show) {
}
