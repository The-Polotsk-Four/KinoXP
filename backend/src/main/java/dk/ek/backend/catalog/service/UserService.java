package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.Mapper.Mapper;
import dk.ek.backend.catalog.dto.UserDto;
import dk.ek.backend.catalog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }


}
