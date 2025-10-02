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

    @OneToOne
    private Ticket ticket;

    @ManyToOne
    private Order order;

    public Ticket(Long id, double price, boolean status, LocalDateTime timeOfShowing, Show show, Ticket ticket, Order order) {
        this.id = id;
        this.price = price;
        this.status = status;
        this.timeOfShowing = timeOfShowing;
        this.show = show;
        this.ticket = ticket;
        this.order = order;
    }

    public Ticket() {
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
