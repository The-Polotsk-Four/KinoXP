package dk.ek.backend.catalog.repository;

import dk.ek.backend.catalog.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
}
