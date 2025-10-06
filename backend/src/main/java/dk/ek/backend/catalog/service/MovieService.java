package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.dto.MovieDto;
import dk.ek.backend.catalog.dto.ShowDto;
import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.model.Movie;
import dk.ek.backend.catalog.model.MovieStatus;
import dk.ek.backend.catalog.model.Show;
import dk.ek.backend.catalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

import static dk.ek.backend.catalog.model.MovieStatus.COMING_SOON;


@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final Mapper mapper;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${tmdb.api.key}")
    private String apiKey;

    public MovieService(MovieRepository movieRepository, Mapper Mapper) {
        this.movieRepository = movieRepository;
        this.mapper = Mapper;
    }

    public MovieDto searchMovieByTitleAndYear(String title, String year) {
        try {
            // Search by title and year
            String searchUrl = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/search/movie")
                    .queryParam("api_key", apiKey)
                    .queryParam("query", title)
                    .queryParam("year", year)
                    .toUriString();

            Map<String, Object> searchResponse = restTemplate.getForObject(searchUrl, Map.class);
            List<Map<String, Object>> results = (List<Map<String, Object>>) searchResponse.get("results");

            if (results == null || results.isEmpty()) {
                throw new RuntimeException("No movies found for title: " + title + " (" + year + ")");
            }

            // select first movie
            Map<String, Object> firstResult = results.get(0);
            Long tmdbId = ((Number) firstResult.get("id")).longValue();


            return getMovieDetails(tmdbId);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to search for movie: " + title + " (" + year + ")");
        }
    }

    // Save movie
    public Movie saveMovie(MovieDto dto) {
        Movie movie = Mapper.toEntity(dto);
        return movieRepository.save(movie);
    }

    public MovieDto getMovieDetails(Long tmdbId) {
        // Get movie details
        String detailsUrl = "https://api.themoviedb.org/3/movie/" + tmdbId + "?api_key=" + apiKey;
        Map<String, Object> details = restTemplate.getForObject(detailsUrl, Map.class);

        // Get actors
        String creditsUrl = "https://api.themoviedb.org/3/movie/" + tmdbId + "/credits?api_key=" + apiKey;
        Map<String, Object> credits = restTemplate.getForObject(creditsUrl, Map.class);
        List<Map<String, Object>> cast = (List<Map<String, Object>>) credits.get("cast");

        String actors = cast.stream()
                .limit(5)
                .map(c -> (String) c.get("name"))
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        // Get trailer
        String videosUrl = "https://api.themoviedb.org/3/movie/" + tmdbId + "/videos?api_key=" + apiKey;
        Map<String, Object> videosResp = restTemplate.getForObject(videosUrl, Map.class);
        List<Map<String, Object>> videos = (List<Map<String, Object>>) videosResp.get("results");
        String trailer = videos.stream()
                .filter(v -> "Trailer".equals(v.get("type")) && "YouTube".equals(v.get("site")))
                .map(v -> "https://www.youtube.com/watch?v=" + v.get("key"))
                .findFirst()
                .orElse("");


        String title = (String) details.get("title");
        String year = String.valueOf(details.get("release_date")).split("-")[0];
        String runtime = details.get("runtime") != null ? details.get("runtime").toString() + " min" : "";
        String poster = "https://image.tmdb.org/t/p/w500" + details.get("poster_path");
        Enum status = COMING_SOON;
        List<ShowDto> show = null;

        return new MovieDto(
                title,
                year,
                runtime,
                poster,
                trailer,
                actors, status,show);
    }

}