package dk.ek.backend.catalog.dto;

import java.util.List;

public record TMdbDto (
        Long id,
        String title,
        String description,
        String image,
        List<String> actors,
        int runtime,
        String trailer
)
{}
