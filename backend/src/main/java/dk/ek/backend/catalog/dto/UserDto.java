package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.UserRole;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UserDto(
        Long id,
        String name,
        UserRole role,
        String email,
        int phoneNumber,
        LocalDate age,
        Set<TimeSlotDto> timeSlots
)
{}
