package dk.ek.backend.catalog.repository;

import dk.ek.backend.catalog.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
