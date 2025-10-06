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

    public static Movie toEntity(MovieDto dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.title());
        movie.setYear(dto.year());
        movie.setRuntime(dto.runtime());
        movie.setPoster(dto.poster());
        movie.setTrailer(dto.trailer());
        movie.setActors(dto.actors());
        return movie;
    }

    public MovieDto toDto(Movie movie) {
        return new MovieDto(
                movie.getTitle(),
                movie.getYear(),
                movie.getRuntime(),
                movie.getPoster(),
                movie.getTrailer(),
                movie.getActors()
        );
    }
}

