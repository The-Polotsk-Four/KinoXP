package dk.ek.backend.catalog.repository;

import dk.ek.backend.catalog.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
