package dk.ek.backend.catalog.service;


import dk.ek.backend.catalog.model.Hall;
import dk.ek.backend.catalog.model.Seat;
import dk.ek.backend.catalog.repository.HallRepository;
import dk.ek.backend.catalog.repository.SeatRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateHallService{

    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;

    public CreateHallService(HallRepository hallRepository, SeatRepository seatRepository) {
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
    }

    public void createTheaterHalls() {

        Hall smallHall = new Hall();
        smallHall.setId(1L);
        hallRepository.save(smallHall);

        Hall bigHall = new Hall();
        bigHall.setId(2L);
        hallRepository.save(bigHall);


        String hallname = "Small hall";
        for (int rows=1; rows<21; rows++) {
            for (int seat=1; seat<13; seat++) {
                Seat seatObj = new Seat();
                seatObj.setId(hallname + " R" + rows + " S" + seat);
                seatObj.setSeatNumber(seat);
                seatObj.setRowNumber(rows);
                seatObj.setHall(smallHall);
                seatRepository.save(seatObj);
            }
        }

        String hallname2 = "Big hall";
        for (int rows=1; rows<26; rows++) {
            for (int seat=1; seat<17; seat++) {
                Seat seatObj = new Seat();
                seatObj.setId(hallname2 + " R" + rows + " S" + seat);
                seatObj.setSeatNumber(seat);
                seatObj.setRowNumber(rows);
                seatObj.setHall(bigHall);
                seatRepository.save(seatObj);
            }
        }


    }
}
