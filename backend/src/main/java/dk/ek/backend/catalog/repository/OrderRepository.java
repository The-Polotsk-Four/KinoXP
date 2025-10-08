package dk.ek.backend.catalog.repository;

import dk.ek.backend.catalog.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerEmail(String customerEmail);

    List<Order> findByCustomerPhoneNumber(int customerPhoneNumber);

}
