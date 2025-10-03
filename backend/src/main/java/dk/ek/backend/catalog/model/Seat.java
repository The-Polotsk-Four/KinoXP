package dk.ek.backend.catalog.model;

import jakarta.persistence.*;

@Entity
public class Seat {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    private int row;
    private int seatNumber;

    @OneToOne(mappedBy = "seat")
    private Ticket ticket;

    @ManyToOne
    private Hall hall;

    public Seat(String id, int row, int seatNumber, Ticket ticket, Hall hall) {
        this.id = id;
        this.row = row;
        this.seatNumber = seatNumber;
        this.ticket = ticket;
        this.hall = hall;
    }

    public Seat() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
