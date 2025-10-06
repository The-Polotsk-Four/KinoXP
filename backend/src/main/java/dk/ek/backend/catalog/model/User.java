package dk.ek.backend.catalog.model;

import jakarta.persistence.*;

import java.util.List;

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
    private int age;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeSlot> timeSlot;

    public User(Long id, UserRole userRole, String name, String email, int phoneNumber, int age, List<TimeSlot> timeSlot) {
        this.id = id;
        this.userRole = userRole;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.timeSlot = timeSlot;
    }

    public User() {

    }

    public List<TimeSlot> getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(List<TimeSlot> timeSlot) {
        this.timeSlot = timeSlot;
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

    public int getAge() {
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

    public void setAge(int age) {
        this.age = age;
    }

    public void addTimeslot(TimeSlot timeslot){
        this.timeSlot.add(timeslot);
        timeslot.setUser(this);
    }

    public void removeTimeslot(TimeSlot timeSlot) {
        this.timeSlot.remove(timeSlot);
        timeSlot.setUser(null);
    }
}