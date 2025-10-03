package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.dto.HallDto;
import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.model.Hall;
import dk.ek.backend.catalog.model.Seat;
import dk.ek.backend.catalog.model.Show;
import dk.ek.backend.catalog.repository.HallRepository;
import dk.ek.backend.catalog.repository.MovieRepository;
import dk.ek.backend.catalog.repository.SeatRepository;
import dk.ek.backend.catalog.repository.ShowRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HallService {

    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;

    public HallService(HallRepository hallRepository, MovieRepository movieRepository, SeatRepository seatRepository, ShowRepository showRepository) {
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
    }

    public List<HallDto> getAllHalls(){
        List<HallDto> hallDtos = new ArrayList<>();
        List<Hall> halls = hallRepository.findAll();
        for (Hall hall : halls){
            hallDtos.add(Mapper.toDto(hall));
        }
        return hallDtos;
    }

    public HallDto getHallById(Long id){
        Optional<Hall> hall = hallRepository.findById(id);
        if(hall.isPresent()) {
            return Mapper.toDto(hall.get());
        }
        throw new RuntimeException("Hall not found with id: "+id);
        }

//    public HallDto createHall(HallDto hallDto){
//        Hall hall = Mapper.toEntity(hallDto);
//
//        for(Seat seat : hall.getSeat()){
//            seatService.saveIfNotExists(seat);
//        }
//
//        for(Show show : hall.getShow()){
//            showService.saveIfNotExists(show);
//        }
//
//        hall.setId(null);
//
//        return Mapper.toDto(hallRepository.save(hall));
//    }


    }




