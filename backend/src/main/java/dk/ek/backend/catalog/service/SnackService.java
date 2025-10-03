package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.dto.SnackDto;
import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.model.Snack;
import dk.ek.backend.catalog.repository.SnackRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SnackService {

    private final SnackRepository snackRepository;

    public SnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    public List<SnackDto> getAll() {
        List<SnackDto> snackDtos = new ArrayList<>();
        List<Snack> snacks = snackRepository.findAll();
        for (Snack snack : snacks) {
            snackDtos.add(Mapper.toDto(snack));
        }
        return snackDtos;
    }

    public List<SnackDto> getByName(String name) {
        List<SnackDto> snackDtos = new ArrayList<>();
        List<Snack> snacks = snackRepository.findByNameContaining(name);
        for (Snack snack : snacks) {
            snackDtos.add(Mapper.toDto(snack));
        }
        return snackDtos;
    }

    public SnackDto getById(Long id) {
        Optional<Snack> snack = snackRepository.findById(id);
        if (snack.isPresent()) {
            return Mapper.toDto(snack.get());
        }
        throw new RuntimeException();
    }

    public SnackDto createSnack(SnackDto snackDto) {
        Snack snack = Mapper.toEntity(snackDto);

        snack.setId(null);

        return Mapper.toDto(snackRepository.save(snack));
    }

    public SnackDto updateSnack(Long id, SnackDto snackDto) {
        Optional<Snack> findSnack = snackRepository.findById(id);
        Snack snack = Mapper.toEntity(snackDto);
        if (findSnack.isPresent()) {
            Snack snackToUpdate = findSnack.get();

            snackToUpdate.setName(snack.getName());
            snackToUpdate.setPrice(snack.getPrice());

            return Mapper.toDto(snackRepository.save(snackToUpdate));
        }
        throw new RuntimeException();
    }

    public void deleteById(Long id) {
        snackRepository.deleteById(id);
    }
}
