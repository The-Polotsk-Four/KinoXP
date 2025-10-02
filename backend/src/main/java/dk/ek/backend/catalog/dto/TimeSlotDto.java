package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.EmployeeRole;

import java.time.LocalDateTime;

public record TimeSlotDto(Long id, LocalDateTime startTime, LocalDateTime end, EmployeeRole role) {
}
