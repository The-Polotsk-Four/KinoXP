package dk.ek.backend.catalog.model;

import jakarta.persistence.*;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String name;
    private String role;
    private String email;
    private int phoneNumber;
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private TimeSlot timeSlot;


    public UserRole getUserRole() {
        return userRole;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
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

    public void setRole(String role) {
        this.role = role;
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


}