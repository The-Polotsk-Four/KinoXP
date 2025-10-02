package dk.ek.backend.catalog.Mapper;

import dk.ek.backend.catalog.dto.UserDto;
import dk.ek.backend.catalog.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static UserDto toUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getRole(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAge()
        );
    }

}
