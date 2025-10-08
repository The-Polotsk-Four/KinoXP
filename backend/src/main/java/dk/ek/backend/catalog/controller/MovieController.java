package dk.ek.backend.catalog.controller;

import dk.ek.backend.catalog.dto.MovieDto;
import dk.ek.backend.catalog.dto.ShowDto;
import dk.ek.backend.catalog.model.Movie;
import dk.ek.backend.catalog.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllShows(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }


    //Seach for movie
    @GetMapping("/search/details")
    public MovieDto searchMovieDetails(@RequestParam String title, @RequestParam String year) {
        return movieService.searchMovieByTitleAndYear(title, year);
    }

    // Save  movie
    @PostMapping
    public Movie saveMovie(@RequestBody MovieDto movieDto) {
        return movieService.saveMovie(movieDto);
    }

    // Get give by id
    @GetMapping("/{tmdbId}")
    public MovieDto getMovieDetails(@PathVariable Long tmdbId) {
        return movieService.getMovieDetails(tmdbId);
    }

    // Save movie with full detail
    @PostMapping("/save/{tmdbId}")
    public Movie saveMovie(@PathVariable Long tmdbId) {
        MovieDto dto = movieService.getMovieDetails(tmdbId);
        return movieService.saveMovie(dto);
    }
}
