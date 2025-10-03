package dk.ek.backend.catalog.repository;

import dk.ek.backend.catalog.model.Snack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Long> {
    List<Snack> findByNameContaining(String name);
}
