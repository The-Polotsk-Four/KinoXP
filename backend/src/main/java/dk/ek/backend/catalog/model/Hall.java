package dk.ek.backend.catalog.model;

import dk.ek.backend.catalog.dto.ShowDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hall extends Movie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy ="hall")
    private List<Seat> seat;

    @OneToMany(mappedBy = "hall")
    private List<Show> show;

    public Hall(Long id, List<Seat> seat, List<Show> show) {
        this.id = id;
        this.seat = seat;
        this.show = show;
    }

    public Hall() {
    }

    public void addSeat(Seat seat){
        this.seat.add(seat);
        seat.setHall(this);
    }

    public void removeSeat(Seat seat) {
        this.seat.remove(seat);
        seat.setHall(null);
    }

    public void addShow(Show show){
        this.show.add(show);
        show.setHall(this);
    }

    public void removeShow(Show show) {
        this.show.remove(show);
        show.setHall(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Seat> getSeat() {
        return seat;
    }

    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }

    public List<Show> getShow() {
        return show;
    }

    public void setShow(List<Show> show) {
        this.show = show;
    }
}
