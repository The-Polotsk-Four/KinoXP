package dk.ek.backend.catalog.controller;

import dk.ek.backend.catalog.dto.TimeSlotDto;
import dk.ek.backend.catalog.model.TimeSlot;
import dk.ek.backend.catalog.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/roster")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping
    public ResponseEntity<List<TimeSlotDto>> getTimeslots(@RequestParam(required = false)LocalDate date) {
        if (date != null) {
            return ResponseEntity.ok(timeSlotService.getByDate(date));
        }
        return ResponseEntity.ok(timeSlotService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeSlotDto> getTimeSlot(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(timeSlotService.getById(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<TimeSlotDto> createTimeSlot(@RequestBody TimeSlotDto timeSlotDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(timeSlotService.createTimeSlot(timeSlotDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeSlotDto> updateTimeSLot(@PathVariable Long id, @RequestBody TimeSlotDto timeSlotDto) {
        try {
            return ResponseEntity.ok(timeSlotService.updateTimeSlot(id, timeSlotDto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
