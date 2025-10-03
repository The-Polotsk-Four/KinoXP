package dk.ek.backend.catalog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private boolean status;
    private LocalDateTime timeOfShowing;

    @ManyToOne
    private Show show;

//    @ManyToOne
//    private Order order;

    @ManyToOne
    private Seat seat;

    public Ticket(Long id, double price, boolean status, LocalDateTime timeOfShowing, Show show, Seat seat) {
        this.id = id;
        this.price = price;
        this.status = status;
        this.timeOfShowing = timeOfShowing;
        this.show = show;
        this.seat = seat;
    }

    public Ticket() {
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public LocalDateTime getTimeOfShowing() {
        return timeOfShowing;
    }

    public void setTimeOfShowing(LocalDateTime timeOfShowing) {
        this.timeOfShowing = timeOfShowing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
