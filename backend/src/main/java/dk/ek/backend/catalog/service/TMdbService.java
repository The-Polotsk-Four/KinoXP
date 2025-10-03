package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.dto.TMdbDto;
import dk.ek.backend.catalog.dto.UserDto;
import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.model.Movie;
import dk.ek.backend.catalog.model.User;
import dk.ek.backend.catalog.repository.MovieRepository;
import dk.ek.backend.catalog.repository.TMdbRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hibernate.Hibernate.map;

@Service
public class TMdbService {
    private final TMdbRepository tMdbRepository;

    public TMdbService(TMdbRepository tMdbRepository){
        this.tMdbRepository = tMdbRepository;
    }

    public TMdbDto getMovieById(Long id) {
        return tMdbRepository.findById(id)
                .map(Mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    private TMdbDto toDto(Movie movie) {
        return new TMdbDto(movie.getId(), movie.getTitle(), movie.getDescription(),movie.getImage(),movie.getActors())
}

}
