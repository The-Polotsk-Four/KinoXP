package dk.ek.backend.catalog.dto;

public record UserDto(
        Long id,
        String name,
        String role,
        String email,
        int phoneNumber,
        int age
)
{}
