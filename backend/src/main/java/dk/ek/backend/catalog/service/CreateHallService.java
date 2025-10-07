package dk.ek.backend.catalog.service;


import dk.ek.backend.catalog.model.Hall;
import dk.ek.backend.catalog.model.Seat;
import dk.ek.backend.catalog.repository.HallRepository;
import dk.ek.backend.catalog.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class CreateHallService{

    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;

    public CreateHallService(HallRepository hallRepository, SeatRepository seatRepository) {
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
    }

    @PostConstruct
    public void createNewHallsAndSeats(){
        if (hallRepository.count() >0){
            System.out.println("Halls already exist");
            return;
        }
        createTheaterHalls();
    }

    public void createTheaterHalls() {

        Hall smallHall = new Hall();
        hallRepository.save(smallHall);

        Hall bigHall = new Hall();
        hallRepository.save(bigHall);


        for (int rows=1; rows<21; rows++) {
            for (int seat=1; seat<13; seat++) {
                Seat seatObj = new Seat();
                seatObj.setSeatNumber(seat);
                seatObj.setRowNumber(rows);
                seatObj.setHall(smallHall);
                seatRepository.save(seatObj);
            }
        }

        for (int rows=1; rows<26; rows++) {
            for (int seat=1; seat<17; seat++) {
                Seat seatObj = new Seat();
                seatObj.setSeatNumber(seat);
                seatObj.setRowNumber(rows);
                seatObj.setHall(bigHall);
                seatRepository.save(seatObj);
            }
        }


    }
}
