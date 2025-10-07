package dk.ek.backend.catalog.controller;


import dk.ek.backend.catalog.dto.ShowDto;
import dk.ek.backend.catalog.dto.TicketDto;
import dk.ek.backend.catalog.model.Ticket;
import dk.ek.backend.catalog.repository.TicketRepository;
import dk.ek.backend.catalog.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(ticketService.getTicketById(id));
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<TicketDto> createShow(@PathVariable Long showId, Long seatId){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(showId, seatId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @RequestBody TicketDto ticketDto){
        try{
            return ResponseEntity.ok(ticketService.updateTicket(id, ticketDto));
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id){
        try {
            ticketService.deleteTicket(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

}
