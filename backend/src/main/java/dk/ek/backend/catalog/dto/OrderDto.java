package dk.ek.backend.catalog.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record OrderDto (Long id, double price, String customerEmail, int customerPhoneNumber, LocalDateTime timeOfPurchase, Set<TicketDto> tickets) {

}
