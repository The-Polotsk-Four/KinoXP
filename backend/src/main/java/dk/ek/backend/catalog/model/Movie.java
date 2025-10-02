package dk.ek.backend.catalog.model;

import jakarta.persistence.*;
import org.hibernate.mapping.ToOne;

import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String image;
    private List<String> actors;
    private boolean status;

    @OneToMany
    private List<Show> show;

    public Movie(int id, String title, String description, String image, List<String> actors, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.actors = actors;
        this.status = status;
    }

    public List<Show> getShow() {
        return show;
    }

    public void setShow(List<Show> show) {
        this.show = show;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
