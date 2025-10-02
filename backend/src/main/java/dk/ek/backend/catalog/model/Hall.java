package dk.ek.backend.catalog.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hall {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Seat> seat;

    @OneToMany
    private List<Show> show;

    public Hall(Long id, List<Seat> seat, List<Show> show) {
        this.id = id;
        this.seat = seat;
        this.show = show;
    }

    public Hall() {
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
