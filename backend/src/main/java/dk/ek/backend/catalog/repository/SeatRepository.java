package dk.ek.backend.catalog.repository;

import dk.ek.backend.catalog.model.Movie;
import dk.ek.backend.catalog.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
