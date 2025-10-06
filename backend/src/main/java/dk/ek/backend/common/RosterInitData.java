package dk.ek.backend.common;

import dk.ek.backend.catalog.repository.TimeSlotRepository;
import dk.ek.backend.catalog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;

public class RosterInitData implements CommandLineRunner {

    private final TimeSlotRepository timeSlotRepository;
    private final UserRepository userRepository;

    public RosterInitData(TimeSlotRepository timeSlotRepository, UserRepository userRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO: make users
        // TODO: make timeslots
    }
}
