package dk.ek.backend.catalog;

import dk.ek.backend.catalog.dto.TMdbDto;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TMdbClientAPI {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String apiKey = "0069ed7b49f1dd30ed4583c37a5b0a15";

    public List<TMdbDto> searchMovies (String query){
        String url = "https://api.themoviedb.org/3/search/movie?api_key= " + apiKey + "&query=" + query;
        return searchMovies(query);
    }
}
