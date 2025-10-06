package dk.ek.backend.common;

import dk.ek.backend.catalog.model.EmployeeRole;
import dk.ek.backend.catalog.model.TimeSlot;
import dk.ek.backend.catalog.model.User;
import dk.ek.backend.catalog.model.UserRole;
import dk.ek.backend.catalog.repository.TimeSlotRepository;
import dk.ek.backend.catalog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class RosterInitData implements CommandLineRunner {

    private final TimeSlotRepository timeSlotRepository;
    private final UserRepository userRepository;

    public RosterInitData(TimeSlotRepository timeSlotRepository, UserRepository userRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User christoffer = new User();
        christoffer.setName("Christoffer");
        christoffer.setPhoneNumber(44886622);
        christoffer.setUserRole(UserRole.USER);

        User gustav = new User();
        gustav.setName("Gustav");
        gustav.setAge(LocalDate.of(2001, 10, 8));
        gustav.setUserRole(UserRole.USER);

        User sofus = new User();
        sofus.setName("Sofus");
        sofus.setAge(LocalDate.of(1997, 1, 1));
        sofus.setUserRole(UserRole.USER);

        User tobias = new User();
        tobias.setName("Tobias");
        tobias.setAge(LocalDate.of(1996 ,2 ,14));
        tobias.setEmail("tobias@email.dk");
        tobias.setPhoneNumber(33778855);
        tobias.setUserRole(UserRole.ADMIN);

        userRepository.saveAll(List.of(
                christoffer,
                gustav,
                sofus,
                tobias));

        TimeSlot timeSlot01 = new TimeSlot();
        timeSlot01.setDate(LocalDate.of(2025, 10, 6));
        timeSlot01.setStartTime(LocalTime.of(12, 0));
        timeSlot01.setEndTime(LocalTime.of(20, 59));
        timeSlot01.setRole(EmployeeRole.SALE);

        TimeSlot timeSlot02 = new TimeSlot();
        timeSlot02.setDate(LocalDate.of(2025, 10, 6));
        timeSlot02.setStartTime(LocalTime.of(12, 0));
        timeSlot02.setEndTime(LocalTime.of(16, 59));
        timeSlot02.setRole(EmployeeRole.SALE);

        TimeSlot timeSlot03 = new TimeSlot();
        timeSlot03.setDate(LocalDate.of(2025, 10, 6));
        timeSlot03.setStartTime(LocalTime.of(12, 0));
        timeSlot03.setEndTime(LocalTime.of(18, 59));
        timeSlot03.setRole(EmployeeRole.OPERATOR);

        TimeSlot timeSlot04 = new TimeSlot();
        timeSlot04.setDate(LocalDate.of(2025, 10, 6));
        timeSlot04.setStartTime(LocalTime.of(12, 0));
        timeSlot04.setEndTime(LocalTime.of(16, 59));
        timeSlot04.setRole(EmployeeRole.FLOOR);

        TimeSlot timeSlot05 = new TimeSlot();
        timeSlot05.setDate(LocalDate.of(2025, 10, 6));
        timeSlot05.setStartTime(LocalTime.of(21, 0));
        timeSlot05.setEndTime(LocalTime.of(23, 59));
        timeSlot05.setRole(EmployeeRole.SALE);

        TimeSlot timeSlot06 = new TimeSlot();
        timeSlot06.setDate(LocalDate.of(2025, 10, 6));
        timeSlot06.setStartTime(LocalTime.of(19, 0));
        timeSlot06.setEndTime(LocalTime.of(23, 59));
        timeSlot06.setRole(EmployeeRole.OPERATOR);

        christoffer.addTimeslot(timeSlot01);
        gustav.addTimeslot(timeSlot02);
        sofus.addTimeslot(timeSlot03);
        tobias.addTimeslot(timeSlot04);
        tobias.addTimeslot(timeSlot05);

        timeSlotRepository.saveAll(List.of(
                timeSlot01,
                timeSlot02,
                timeSlot03,
                timeSlot04,
                timeSlot05,
                timeSlot06
        ));
    }
}
