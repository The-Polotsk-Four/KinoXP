package dk.ek.backend.catalog.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="row_num")
    private int rowNumber;
    private int seatNumber;

    @OneToMany(mappedBy = "seat")
    private Set<Ticket> tickets;

    @ManyToOne
    private Hall hall;

//    public Seat(String id, int row, int seatNumber, Ticket ticket, Hall hall) {
//        this.id = id;
//        this.row = row;
//        this.seatNumber = seatNumber;
//        this.ticket = ticket;
//        this.hall = hall;
//    }


    public Seat(Long id, int rowNumber, int seatNumber, Set<Ticket> tickets) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.tickets = tickets;
    }

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int row) {
        this.rowNumber = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> ticket) {
        this.tickets = ticket;
    }

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
        ticket.setSeat(this);
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
