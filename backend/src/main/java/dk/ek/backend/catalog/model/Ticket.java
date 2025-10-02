package dk.ek.backend.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String customerEmail;
    private int customerPhoneNumber;
    private boolean status;
    private LocalDateTime timeOfPurchase;
    private LocalDateTime timeOfShowing;

    public Ticket(int id, double price, String customerEmail, int customerPhoneNumber, boolean status, LocalDateTime timeOfPurchase, LocalDateTime timeOfShowing) {
        this.id = id;
        this.price = price;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.status = status;
        this.timeOfPurchase = timeOfPurchase;
        this.timeOfShowing = timeOfShowing;
    }


    public Ticket() {
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public LocalDateTime getTimeOfShowing() {
        return timeOfShowing;
    }

    public void setTimeOfShowing(LocalDateTime timeOfShowing) {
        this.timeOfShowing = timeOfShowing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String email) {
        this.customerEmail = email;
    }

    public int getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(int phoneNumber) {
        this.customerPhoneNumber = phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
