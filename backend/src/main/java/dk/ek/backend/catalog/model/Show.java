package dk.ek.backend.catalog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Movie movie;
    private LocalDateTime timeOfShowing;

    @ManyToOne
    private Hall hall;

    @OneToMany
    private List<Ticket> tickets;

    public Show(Long id, Movie movie, LocalDateTime timeOfShowing, Hall hall, List<Ticket> tickets) {
        this.id = id;
        this.movie = movie;
        this.timeOfShowing = timeOfShowing;
        this.hall = hall;
        this.tickets = tickets;
    }

    public Show() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getTimeOfShowing() {
        return timeOfShowing;
    }

    public void setTimeOfShowing(LocalDateTime timeOfShowing) {
        this.timeOfShowing = timeOfShowing;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> ticket) {
        this.tickets = ticket;
    }
}
