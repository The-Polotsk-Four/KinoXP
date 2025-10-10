package dk.ek.backend.catalog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Movie movie;
    private LocalDateTime timeOfShowing;

    @ManyToOne
    private Hall hall;

    private boolean cancelled = false;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

//    public Show(Long id, Movie movie, LocalDateTime timeOfShowing, Hall hall, List<Ticket> tickets) {
//        this.id = id;
//        this.movie = movie;
//        this.timeOfShowing = timeOfShowing;
//        this.hall = hall;
//        this.tickets = tickets;
//    }

    //for testing purposes
    public Show(Long id, Movie movie, LocalDateTime timeOfShowing, List<Ticket> tickets) {
        this.id = id;
        this.movie = movie;
        this.timeOfShowing = timeOfShowing;
        this.tickets = tickets;
    }

    public Show() {
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
        ticket.setShow(this);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
        ticket.setShow(null);
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
