package dk.ek.backend.catalog.mapper;

import dk.ek.backend.catalog.dto.*;
import dk.ek.backend.catalog.model.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

        public static SnackDto toDto(Snack snack) {
            return new SnackDto(
                    snack.getId(),

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

        public static TMdbDto toDto(Movie movie){
            return new TMdbDto(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getDescription(),
                    movie.getImage(),

                    movie.getActors());
        }

}
