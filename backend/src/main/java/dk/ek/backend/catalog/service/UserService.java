package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.dto.UserDto;
import dk.ek.backend.catalog.model.User;
import dk.ek.backend.catalog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(Mapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getRole(), user.getEmail(), user.getPhoneNumber(), user.getAge());
    }

    private User toEntity(UserDto userDto) {
        User user = new User();
        if (userDto.id() != null) {
            user.setId(userDto.id());
        }
        user.setId(userDto.id());
        user.setName(userDto.name());
        user.setRole(userDto.role());
        user.setEmail(userDto.email());
        user.setPhoneNumber(userDto.phoneNumber());
        user.setAge(userDto.age());
        return user;
    }


    public UserDto createUser(UserDto userDto){
        User user = toEntity(userDto);
        User saved = userRepository.save(user);
        return toDto(saved);
    }

    public UserDto updateUser (Long id, UserDto userDto){
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bruger blev ikke fundet med det id " + id));
        existing.setName(userDto.name());
        existing.setRole(userDto.role());
        existing.setEmail(userDto.email());
        existing.setPhoneNumber(userDto.phoneNumber());
        existing.setAge(userDto.age());

        User updated = userRepository.save(existing);
        return toDto(updated);
    }

    public void deleteUser (Long id){
        if (!userRepository.existsById(id)){
            throw new RuntimeException("Bruger blev ikke fundet med det id " + id);
        }
        userRepository.deleteById(id);
    }

}
