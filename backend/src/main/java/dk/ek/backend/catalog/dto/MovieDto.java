package dk.ek.backend.catalog.dto;

import java.util.List;

public record MovieDto(
        String title,
        String year,
        String runtime,
        String poster,
        String trailer,
        String actors
) {}
