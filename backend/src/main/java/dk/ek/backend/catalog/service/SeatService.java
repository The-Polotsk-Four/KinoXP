package dk.ek.backend.catalog.service;


import dk.ek.backend.catalog.dto.HallDto;
import dk.ek.backend.catalog.dto.SeatDto;
import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.model.Hall;
import dk.ek.backend.catalog.model.Seat;
import dk.ek.backend.catalog.repository.HallRepository;
import dk.ek.backend.catalog.repository.SeatRepository;
import dk.ek.backend.catalog.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final HallRepository hallRepository;
    private final ShowRepository showRepository;
    private final ShowService showService;

    public SeatService(SeatRepository seatRepository, HallRepository hallRepository, ShowRepository showRepository, ShowService showService) {
        this.seatRepository = seatRepository;
        this.hallRepository = hallRepository;
        this.showRepository = showRepository;
        this.showService = showService;
    }

    public List<SeatDto> getAllSeats(){
        List<SeatDto> seatDtos = new ArrayList<>();
        List<Seat> seats = seatRepository.findAll();
        for (Seat seat : seats){
            seatDtos.add(Mapper.toDto(seat));
        }
        return seatDtos;
    }

    public SeatDto getHallById(Long id){
        Optional<Seat> seat = seatRepository.findById(id);
        if(seat.isPresent()) {
            return Mapper.toDto(seat.get());
        }
        throw new RuntimeException("Hall not found with id: "+id);
    }



}
