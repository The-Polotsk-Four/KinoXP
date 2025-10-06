package dk.ek.backend.catalog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String name;

    private String email;
    private int phoneNumber;
    private LocalDate age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TimeSlot> timeSlots = new HashSet<>();

    public User(Long id, UserRole userRole, String name, String email, int phoneNumber, LocalDate age, Set<TimeSlot> timeSlots) {
        this.id = id;
        this.userRole = userRole;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.timeSlots = timeSlots;
    }

    public User() {

    }

    public Set<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(Set<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getAge() {
        return age;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(LocalDate age) {
        this.age = age;
    }

    public void addTimeslot(TimeSlot timeslot) {
        this.timeSlots.add(timeslot);
        timeslot.setUser(this);
    }

    public void removeTimeslot(TimeSlot timeSlot) {
        this.timeSlots.remove(timeSlot);
        timeSlot.setUser(null);
    }

    @Override
    public String toString() {
        return this.name;
    }
}