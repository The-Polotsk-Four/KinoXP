package dk.ek.backend.catalog.dto;

import java.time.LocalDateTime;

public record OrderDto (Long id, double price, String customerEmail, int customerPhoneNumber, LocalDateTime timeOfPurchase) {

}
