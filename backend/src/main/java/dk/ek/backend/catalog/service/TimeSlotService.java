package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.dto.TimeSlotDto;
import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.model.TimeSlot;
import dk.ek.backend.catalog.model.User;
import dk.ek.backend.catalog.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlotDto> getAll() {
        List<TimeSlotDto> timeSlotDtos = new ArrayList<>();
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();

        for (TimeSlot timeSlot : timeSlots) {
            timeSlotDtos.add(Mapper.toDto(timeSlot));
        }

        return timeSlotDtos;
    }

    public TimeSlotDto getById(Long id) {
        Optional<TimeSlot> timeSlot = timeSlotRepository.findById(id);
        if (timeSlot.isPresent()) {
            return Mapper.toDto(timeSlot.get());
        }

        throw new RuntimeException();
    }

    public TimeSlotDto createTimeSlot(TimeSlotDto timeSlotDto) {
        TimeSlot timeSlot = Mapper.toEntity(timeSlotDto);

        timeSlot.setId(null);

        return Mapper.toDto(timeSlotRepository.save(timeSlot));
    }

    public TimeSlotDto updateTimeSlot(Long id, TimeSlotDto timeSlotDto) {
        Optional<TimeSlot> findTimeSlot = timeSlotRepository.findById(id);
        TimeSlot timeSlot = Mapper.toEntity(timeSlotDto);

        if (findTimeSlot.isPresent()) {
            TimeSlot timeSlotToUpdate = findTimeSlot.get();

            timeSlotToUpdate.setDate(timeSlot.getDate());
            timeSlotToUpdate.setStartTime(timeSlot.getStartTime());
            timeSlotToUpdate.setEndTime(timeSlot.getEndTime());
            timeSlotToUpdate.setRole(timeSlot.getRole());
            timeSlotToUpdate.setUser(timeSlot.getUser());

            return Mapper.toDto(timeSlotRepository.save(timeSlotToUpdate));
        }
        throw new RuntimeException();
    }

    public void deleteTimeSlot(Long id) {
        timeSlotRepository.deleteById(id);
    }

    public List<TimeSlotDto> getByDate(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        List<TimeSlotDto> timeSlotDtos = new ArrayList<>();
        List<TimeSlot> timeSlots = timeSlotRepository.findByDateIs(parsedDate);

        for (TimeSlot timeSlot : timeSlots) {
            timeSlotDtos.add(Mapper.toDto(timeSlot));
        }

        return timeSlotDtos;
    }
}
