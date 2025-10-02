package dk.ek.backend.catalog.mapper;

import dk.ek.backend.catalog.dto.*;
import dk.ek.backend.catalog.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    public static MovieDto toDto(Movie movie) {

        List<ShowDto> shows = new ArrayList<>();
        for (Show show : movie.getShow()) {
            shows.add(toDto(show));
        }

        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getImage(),
                movie.getActors(),
                movie.getStatus(),
                shows
        );
    }

    public static OrderDto toDto(Order order) {
        List<TicketDto> tickets = new ArrayList<>();
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
        return new SeatDto(
                seat.getId(),
                seat.getRow(),
                seat.getSeatNumber(),
                seat.getTicket(),
                seat.getHall()
        );
    }

    public static ShowDto toDto(Show show) {
        List<TicketDto> tickets = new ArrayList<>();
        for (Ticket ticket : show.getTickets()) {
            tickets.add(toDto(ticket));
        }

        return new ShowDto(
                show.getId(),
                show.getMovie(),
                show.getTimeOfShowing(),
                show.getHall(),
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
                ticket.getShow(),
                ticket.getSeat()
        );
    }

    public static TimeSlotDto toDto(TimeSlot timeSlot) {
        return new TimeSlotDto(
                timeSlot.getId(),
                timeSlot.getStartTime(),
                timeSlot.getEndTime(),
                timeSlot.getRole(),
                timeSlot.getEmployee()
        );
    }

    public static UserDto toDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getRole(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAge()
        );
    }

    public static Hall toEntity(HallDto hallDto){
            Hall hall= new Hall();
            hall.setId(hallDto.id());

            for (SeatDto seatDto : hallDto.seat()){
                hall.addSeat(toEntity(seatDto));
            }
            for (ShowDto showDto : hallDto.show()){
                hall.addShow(toEntity(showDto));
            }
            return hall;
    }

    public static Movie toEntity(MovieDto movieDto){
            Movie movie = new Movie();
            movie.setId(movieDto.id());
            movie.setTitle(movieDto.title());
            movie.setDescription(movieDto.description());
            movie.setImage(movieDto.image());
            movie.setActors(movieDto.actors());
            movie.setStatus(movieDto.status());

        for (ShowDto showDto : movieDto.show()){
            movie.addShow(toEntity(showDto));
        }
        return movie;
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



}
