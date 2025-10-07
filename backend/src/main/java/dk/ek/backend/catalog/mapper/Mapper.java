package dk.ek.backend.catalog.mapper;

import dk.ek.backend.catalog.dto.*;
import dk.ek.backend.catalog.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Mapper {


    private static final Logger log = LoggerFactory.getLogger(Mapper.class);

    public static HallDto toDto(Hall hall){
        List<SeatDto> seats = new ArrayList<>();

        List<ShowDto> shows = new ArrayList<>();
        for (Show show : hall.getShow()){
            shows.add(toDto(show));
        }
        for (Seat seat : hall.getSeat()){
            seats.add(toDto(seat));
        }

        return new HallDto(
                hall.getId(),
                seats
        );
    }


    public static OrderDto toDto(Order order) {
        Set<TicketDto> tickets = new HashSet<>();
        for (Ticket ticket : order.getTickets()) {
            tickets.add(toDto(ticket));
        }

        return new OrderDto(
                order.getId(),
                order.getPrice(),
                order.getCustomerEmail(),
                order.getCustomerPhoneNumber(),
                order.getTimeOfPurchase(),
                tickets
        );
    }

    public static SeatDto toDto(Seat seat) {
        Set<TicketDto> tickets = new HashSet<>();
        for (Ticket ticket : seat.getTickets()) {
            tickets.add(toDto(ticket));
        }

        return new SeatDto(
                seat.getId(),
                seat.getRowNumber(),
                seat.getSeatNumber()
        );
    }

    public static ShowDto toDto(Show show) {
        List<TicketDto> tickets = new ArrayList<>();
        for (Ticket ticket : show.getTickets()) {
            tickets.add(toDto(ticket));
        }

        return new ShowDto(
                show.getId(),
                toDto(show.getMovie()),
//                toDto(show.getMovie()),
                show.getTimeOfShowing(),
                toDto(show.getHall()),
                tickets
        );
    }

    public static SnackDto toDto(Snack snack) {
        return new SnackDto(
                snack.getId(),
                snack.getName(),
                snack.getPrice(),
                snack.getQuantity(),
                snack.getDateOfPurchase()
        );
    }

    public static TicketDto toDto(Ticket ticket) {
        return new TicketDto(
                ticket.getId(),
                ticket.getPrice(),
                ticket.isStatus(),
                ticket.getTimeOfShowing(),
                toDto(ticket.getSeat())
        );
    }

    public static TimeSlotDto toDto(TimeSlot timeSlot) {
        System.out.println("timeSlotToDto: " + timeSlot);
        if (timeSlot.getUser() == null) {
            return new TimeSlotDto(
                    timeSlot.getId(),
                    timeSlot.getDate(),
                    timeSlot.getStartTime(),
                    timeSlot.getEndTime(),
                    timeSlot.getRole(),
                    null
            );
        }
        return new TimeSlotDto(
                timeSlot.getId(),
                timeSlot.getDate(),
                timeSlot.getStartTime(),
                timeSlot.getEndTime(),
                timeSlot.getRole(),
                toDto(timeSlot.getUser())
        );
    }

    public static UserDto toDto(User user){
//        Set<TimeSlotDto> timeSlots = new HashSet<>();
//        for (var timeSlot : user.getTimeSlots()) {
//            timeSlots.add(toDto(timeSlot));
//        }
        System.out.println("user toDto: " + user);

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getUserRole(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAge()
        );
    }
    public static MovieDto toDto(Movie movie) {
        List<ShowDto> shows = new ArrayList<>();
        for (Show show : movie.getShow()) {
            shows.add(toDto(show));
        }

        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getRuntime(),
                movie.getPoster(),
                movie.getTrailer(),
                movie.getActors(),
                movie.getStatus(),
                shows);
    }
    public static Hall toEntity(HallDto hallDto){
            Hall hall= new Hall();
            hall.setId(hallDto.id());
            return hall;
    }

    public static Order toEntity(OrderDto orderDto){
            Order order = new Order();
            order.setId(orderDto.id());
            order.setPrice(orderDto.price());
            order.setCustomerEmail(orderDto.customerEmail());
            order.setCustomerPhoneNumber(orderDto.customerPhoneNumber());
            order.setTimeOfPurchase(orderDto.timeOfPurchase());

            for (TicketDto ticketDto: orderDto.tickets()){
                order.addTicket(toEntity(ticketDto));
            }
            return order;
    }

    public static Seat toEntity(SeatDto seatDto){
            Seat seat = new Seat();
            seat.setId(seatDto.id());
            seat.setRowNumber(seatDto.row());
            seat.setSeatNumber(seatDto.seatNumber());

//            for (TicketDto ticketDto: seatDto.ticket()) {
//                seat.addTicket(toEntity(ticketDto));
//            }
            return seat;
    }

    public static Show toEntity(ShowDto showDto){
            Show show = new Show();
            show.setId(showDto.id());
            show.setMovie(toEntity(showDto.movie()));
            show.setTimeOfShowing(showDto.timeOfShowing());
            show.setHall(toEntity(showDto.hall()));

            for (TicketDto ticketDto : showDto.tickets()) {
                show.addTicket(toEntity(ticketDto));
            }
            return show;
    }

    public static Snack toEntity(SnackDto snackDto){
            Snack snack = new Snack();
            snack.setId(snackDto.id());
            snack.setName(snackDto.name());
            snack.setPrice(snackDto.price());
            return snack;
    }

    public static Ticket toEntity(TicketDto ticketDto){
            Ticket ticket = new Ticket();
            ticket.setId(ticketDto.id());
            ticket.setPrice(ticketDto.price());
            ticket.setStatus(ticketDto.status());
            ticket.setTimeOfShowing(ticketDto.timeOfShowing());
            ticket.setSeat(toEntity(ticketDto.seat()));
            return ticket;
    }

    public static TimeSlot toEntity(TimeSlotDto timeSlotDto){
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setId(timeSlotDto.id());
            timeSlot.setStartTime(timeSlotDto.startTime());
            timeSlot.setEndTime(timeSlotDto.endTime());
            timeSlot.setRole(timeSlotDto.role());
            timeSlot.setUser(toEntity(timeSlotDto.user()));
            return timeSlot;
    }

    public static User toEntity(UserDto userDto){
            User user = new User();
            user.setId(userDto.id());
            user.setUserRole(userDto.role());
            user.setEmail(userDto.email());
            user.setPhoneNumber(userDto.phoneNumber());
            user.setAge(userDto.age());

            return user;
    }


    public static Movie toEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.id());
        movie.setTitle(movieDto.title());
        movie.setReleaseYear(movieDto.year());
        movie.setRuntime(movieDto.runtime());
        movie.setPoster(movieDto.poster());
        movie.setTrailer(movieDto.trailer());
        movie.setActors(movieDto.actors());
        for (ShowDto showDto : movieDto.show()){
            movie.addShow(toEntity(showDto));
        }

        return movie;
    }
}

