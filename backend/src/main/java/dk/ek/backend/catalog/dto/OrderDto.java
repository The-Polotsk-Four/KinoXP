package dk.ek.backend.catalog.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto (Long id, double price, String customerEmail, int customerPhoneNumber, LocalDateTime timeOfPurchase, List<TicketDto> tickets) {

}
