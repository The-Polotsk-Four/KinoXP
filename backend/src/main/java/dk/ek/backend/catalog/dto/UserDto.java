package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.UserRole;

import java.time.LocalDate;

public record UserDto(
        Long id,
        String name,
        UserRole role,
        String email,
        int phoneNumber,
        LocalDate age
//        , Set<TimeSlotDto> timeSlots
) {
}
