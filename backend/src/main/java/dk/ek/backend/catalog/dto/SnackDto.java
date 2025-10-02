package dk.ek.backend.catalog.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record SnackDto(Long id, String name, BigDecimal price, int quantity, LocalDateTime dateOfPurchase) {
}
