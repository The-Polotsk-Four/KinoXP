package dk.ek.backend.catalog.mapper;

import dk.ek.backend.catalog.dto.SnackDto;
import dk.ek.backend.catalog.dto.TicketDto;
import dk.ek.backend.catalog.dto.TimeSlotDto;
import dk.ek.backend.catalog.dto.UserDto;
import dk.ek.backend.catalog.model.Snack;
import dk.ek.backend.catalog.model.Ticket;
import dk.ek.backend.catalog.model.TimeSlot;
import dk.ek.backend.catalog.model.User;
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

}
