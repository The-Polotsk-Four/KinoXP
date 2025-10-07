package dk.ek.backend.catalog.service;


import dk.ek.backend.catalog.dto.TicketDto;
import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.model.Seat;
import dk.ek.backend.catalog.model.Show;
import dk.ek.backend.catalog.model.Ticket;
import dk.ek.backend.catalog.repository.SeatRepository;
import dk.ek.backend.catalog.repository.ShowRepository;
import dk.ek.backend.catalog.repository.TicketRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
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

    public TicketDto createTicket(Long showId, Long seatId){
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
        // hardcoded ticket price 110 kr
        ticket.setPrice(110);

        Ticket newTicket= ticketRepository.save(ticket);


        show.addTicket(newTicket);
        showRepository.save(show);

        return newTicket;
    }

    public boolean isSeatAvailable(Long showId, Long seatId){
        return !ticketRepository.existsByShowIdAndSeatId(showId, seatId);
    }

    public List<TicketDto> getAllTickets() {
        List<TicketDto> ticketDtos = new ArrayList<>();
        List<Ticket> tickets = ticketRepository.findAll();
        for (Ticket ticket : tickets){
            ticketDtos.add(Mapper.toDto(ticket));
        }
        return ticketDtos;
    }

    public TicketDto getTicketById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()){
            return Mapper.toDto(ticket.get());
        }
        throw new RuntimeException("Cant find show with id: "+id);
    }


    public void deleteTicket(long id){
        ticketRepository.deleteById(id);
    }

    public TicketDto updateTicket(Long id, TicketDto ticketDto){
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Show not found with id: "+id));
        ticket.setStatus(ticketDto.status());
        ticket.setTimeOfShowing(ticketDto.timeOfShowing());
        ticket.setSeat(seatRepository.findById(ticketDto.seat().id())
                .orElseThrow(()-> new RuntimeException("Cant find seat number")));

        Ticket updatedTicket = ticketRepository.save(ticket);
        return Mapper.toDto(updatedTicket);


    }


    public Ticket addTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }
}
