package dk.ek.backend.catalog.service;


import dk.ek.backend.catalog.model.Seat;
import dk.ek.backend.catalog.model.Show;
import dk.ek.backend.catalog.model.Ticket;
import dk.ek.backend.catalog.repository.SeatRepository;
import dk.ek.backend.catalog.repository.ShowRepository;
import dk.ek.backend.catalog.repository.TicketRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ShowService showService;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    public TicketService(TicketRepository ticketRepository,ShowRepository showRepository,ShowService showService,SeatRepository seatRepository) {
        this.showService=showService;
        this.showRepository=showRepository;
        this.ticketRepository = ticketRepository;
        this.seatRepository=seatRepository;
    }

    public Ticket createTicket(Long showId, Long seatId){
        Show show = showRepository.findById(showId)
                .orElseThrow(()-> new RuntimeException("Cant find a show with id: "+showId));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(()-> new RuntimeException("Cant find seat with id: "+seatId));

        if (!isSeatAvailable(showId, seatId)){
            throw new IllegalStateException("Seat is already booked");
        }

        Ticket ticket = new Ticket();
        ticket.setShow(show);
        ticket.setSeat(seat);
        ticket.setTimeOfShowing(show.getTimeOfShowing());
        ticket.setStatus(true);
//        Vi skal finde en måde at beregne prisen og hvor den skal være
//        ticket.setPrice();

        Ticket newTicket= ticketRepository.save(ticket);


        show.addTicket(newTicket);
        showRepository.save(show);

        return newTicket;
    }

    public boolean isSeatAvailable(Long showId, Long seatId){
        return !ticketRepository.existsByShowIdAndSeatId(showId, seatId);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found with id: " + id));
    }


    public void deleteTicket(long id){
        Ticket ticket = getTicketById(id);
        Show show = ticket.getShow();

        if (show!= null){
            show.removeTicket(ticket);
        }
        ticketRepository.deleteById(id);
    }


    public Ticket addTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }
}
