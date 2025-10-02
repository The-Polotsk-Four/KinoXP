package dk.ek.backend.catalog.repository;

import dk.ek.backend.catalog.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>{
}
