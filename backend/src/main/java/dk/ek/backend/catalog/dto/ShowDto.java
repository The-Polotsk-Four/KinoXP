package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.Hall;
import dk.ek.backend.catalog.model.Movie;
import dk.ek.backend.catalog.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public record ShowDto(Long id, Movie movie, LocalDateTime timeOfShowing, Hall hall, List<Ticket> ticket) {
}
