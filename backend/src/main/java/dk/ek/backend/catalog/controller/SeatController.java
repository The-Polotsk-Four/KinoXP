package dk.ek.backend.catalog.controller;


import dk.ek.backend.catalog.dto.HallDto;
import dk.ek.backend.catalog.dto.SeatDto;
import dk.ek.backend.catalog.repository.SeatRepository;
import dk.ek.backend.catalog.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public ResponseEntity<List<SeatDto>> getAllSeats(){
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @GetMapping("/{id}")
    public ResponseEntity getSeatById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(seatService.getHallById(id));
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
