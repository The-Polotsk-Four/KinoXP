package dk.ek.backend.catalog.service;

import dk.ek.backend.catalog.dto.ShowDto;
import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.model.Show;
import dk.ek.backend.catalog.repository.HallRepository;
import dk.ek.backend.catalog.repository.MovieRepository;
import dk.ek.backend.catalog.repository.ShowRepository;
import dk.ek.backend.catalog.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    private final HallRepository hallRepository;
    private final HallService hallService;
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;

    public ShowService(HallRepository hallRepository, HallService hallService, MovieRepository movieRepository, TicketRepository ticketRepository,ShowRepository showRepository) {
        this.hallRepository = hallRepository;
        this.hallService = hallService;
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        this.showRepository= showRepository;
    }


    public List<ShowDto> getAllShows(){
        List<ShowDto> showDtos = new ArrayList<>();
        List<Show> shows = showRepository.findAll();
        for (Show show : shows){
            showDtos.add(Mapper.toDto(show));
        }
        return showDtos;
    }

    public ShowDto getShowById(Long id){
        Optional<Show> show = showRepository.findById(id);
        if (show.isPresent()){
            return Mapper.toDto(show.get());
        }
        throw new RuntimeException("Cant find show with id: "+id);
    }

    public List<ShowDto> getShowByMovieId(Long id){
        List<Show> shows = showRepository.findByMovieId(id);
        if(shows.isEmpty()){
            throw new RuntimeException("cant find movie");
        }
        List<ShowDto> showDtos = new ArrayList<>();
        for (Show show : shows){
            showDtos.add(Mapper.toDto(show));
        }
        return showDtos;
    }

    public ShowDto createShow(ShowDto showDto){
        Show show = new Show();
        show.setMovie(movieRepository.findById(showDto.movie().id())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + showDto.movie().id())));
        show.setTimeOfShowing(showDto.timeOfShowing());
        show.setHall(hallRepository.findById(showDto.hall().id())
                .orElseThrow(() -> new RuntimeException("Hall not found with id: " + showDto.hall().id())));
        Show newShow = showRepository.save(show);
        return Mapper.toDto(newShow);
    }



    public ShowDto updateShow(Long id, ShowDto showDto){
        Show show = showRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Show not found with id: "+id));

        show.setTimeOfShowing(showDto.timeOfShowing());
        show.setMovie(movieRepository.findById(showDto.movie().id())
                .orElseThrow(()-> new RuntimeException("Movie with id: "+id+" not found")));
        show.setHall(hallRepository.findById(showDto.hall().id())
                .orElseThrow(() -> new RuntimeException("Hall not found with id: " + showDto.hall().id())));

        Show updatedShow = showRepository.save(show);
        return Mapper.toDto(updatedShow);
    }

    public void deleteShow(Long id ){
        showRepository.deleteById(id);
    }
}
