package dk.ek.backend.catalog.controller;

import dk.ek.backend.catalog.dto.TimeSlotDto;
import dk.ek.backend.catalog.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/roster")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping
    public ResponseEntity<List<TimeSlotDto>> getTimeslots(@RequestParam(required = false) String date) {
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

    @GetMapping("/search")
    public ResponseEntity<List<TimeSlotDto>> searchAuthorsByName(@RequestParam String date) {
        return ResponseEntity.ok(timeSlotService.getByDate(date));
    }

//    @PostMapping("/{id}")
//    public ResponseEntity<TimeSlotDto> createTimeSlot(@RequestBody TimeSlotDto timeSlotDto) {
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(timeSlotService.createTimeSlot(timeSlotDto));
//    }

    @PostMapping
    public ResponseEntity<List<TimeSlotDto>> createMultipleTimeSlot(@RequestBody List<TimeSlotDto> timeSlotDtos) {
        List<TimeSlotDto> updatedList = new ArrayList<>();
        for (var timeSlotDto : timeSlotDtos) {
            updatedList.add(timeSlotService.createTimeSlot(timeSlotDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(updatedList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeSlotDto> updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlotDto timeSlotDto) {
        try {
            return ResponseEntity.ok(timeSlotService.updateTimeSlot(id, timeSlotDto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delteTimeSlot(@PathVariable Long id) {
        try {
            timeSlotService.deleteTimeSlot(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
