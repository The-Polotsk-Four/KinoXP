package dk.ek.backend.catalog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    @ManyToOne
    @JsonBackReference
    private User user;

    public TimeSlot() {
    }

    public TimeSlot(Long id, LocalDate date, LocalTime startTime, LocalTime endTime, EmployeeRole role, User user) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.role = role;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser(User user) {
        this.user = (user);
        user.addUserToTimeSlot(this);
    }

    public void removeUser(User user) {
        this.user = null;
        user.removeSpecificTimeSlot(null);
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", role=" + role +
                ", user=" + user +
                '}';
    }
}
