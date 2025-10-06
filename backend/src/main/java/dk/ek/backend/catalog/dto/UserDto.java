package dk.ek.backend.catalog.dto;

import dk.ek.backend.catalog.model.UserRole;

import java.util.List;

public record UserDto(
        Long id,
        String name,
        UserRole role,
        String email,
        int phoneNumber,
        int age,
        List<TimeSlotDto> timeSlots
)
{}
