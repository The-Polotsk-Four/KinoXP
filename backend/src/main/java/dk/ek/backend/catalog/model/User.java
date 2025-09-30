package dk.ek.backend.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String test;

    private String name;
    private String status;
    private String email;
    private int telefonNumber;
    private int age;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefonNumber() {
        return telefonNumber;
    }

    public int getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefonNumber(int telefonNumber) {
        this.telefonNumber = telefonNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }
}