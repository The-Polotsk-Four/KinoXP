package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.model.Snack;
import dk.ek.backend.catalog.repository.SnackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SnackService {

    private final SnackRepository snackRepository;

    public SnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    public List<Snack> findAll() {
        return snackRepository.findAll();
    }

    public Optional<Snack> findById(Long id) {
        return snackRepository.findById(id);
    }

    public Snack save(Snack snack) {
        return snackRepository.save(snack);
    }

    public void deleteById(Long id) {
        snackRepository.deleteById(id);
    }
}
