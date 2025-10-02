package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.MovieStatus;
import dk.ek.backend.catalog.model.Show;

import java.util.List;

public record MovieDto(Long id, String title, String description, String image, List<String> actors, MovieStatus status, List<ShowDto> show) {
}
