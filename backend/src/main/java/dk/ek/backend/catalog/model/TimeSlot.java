package dk.ek.backend.catalog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    @OneToOne
    private User employee;

    public TimeSlot() {
    }

    public TimeSlot(Long id, LocalDateTime startTime, LocalDateTime endTime, EmployeeRole role, User employee) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.role = role;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }
}
