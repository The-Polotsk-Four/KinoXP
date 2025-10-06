package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.MovieStatus;

import java.util.List;

public record MovieDto(
        String title,
        String year,
        String runtime,
        String poster,
        String trailer,
        String actors,
        MovieStatus status,
        List<ShowDto> show
) {}

