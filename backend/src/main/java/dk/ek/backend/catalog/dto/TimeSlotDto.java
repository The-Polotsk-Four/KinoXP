package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.EmployeeRole;

import java.time.LocalDate;
import java.time.LocalTime;

public record TimeSlotDto(Long id,
                          LocalDate date,
                          LocalTime startTime,
                          LocalTime endTime,
                          EmployeeRole role) {
}
