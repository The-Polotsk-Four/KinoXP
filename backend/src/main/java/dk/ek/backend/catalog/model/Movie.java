package dk.ek.backend.catalog.model;

import jakarta.persistence.*;
import org.hibernate.mapping.ToOne;

import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String image;

    @ElementCollection
    private List<String> actors;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    @OneToMany(mappedBy = "movie")
    private List<Show> show;

    public Movie(Long id, String title, String description, String image, List<String> actors, MovieStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.actors = actors;
        this.status = status;
    }

    public void addShow(Show show){
        this.show.add(show);
        show.setMovie(this);
    }

    public void removeShow(Show show) {
        this.show.remove(show);
        show.setMovie(null);
    }

    public List<Show> getShow() {
        return show;
    }

    public void setShow(List<Show> show) {
        this.show = show;
    }

    public Movie() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }
}
