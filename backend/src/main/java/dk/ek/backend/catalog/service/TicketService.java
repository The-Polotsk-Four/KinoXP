package dk.ek.backend.catalog.service;


import dk.ek.backend.catalog.model.Ticket;
import dk.ek.backend.catalog.repository.TicketRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public void deleteTicket(long id){
        ticketRepository.deleteById(id);
    }


    public Ticket addTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }
}
