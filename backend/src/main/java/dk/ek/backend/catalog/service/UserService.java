package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.Mapper.Mapper;
import dk.ek.backend.catalog.dto.UserDto;
import dk.ek.backend.catalog.model.User;
import dk.ek.backend.catalog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(Mapper::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brugeren blev ikke fundet med dette id " + id));
        return toDto(user);
    }

    private UserDto toDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getRole(), user.getEmail(), user.getPhoneNumber(), user.getAge());
    }

}
