package dk.ek.backend.catalog.repository;

import dk.ek.backend.catalog.model.Seat;
import dk.ek.backend.catalog.model.Show;
import dk.ek.backend.catalog.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
