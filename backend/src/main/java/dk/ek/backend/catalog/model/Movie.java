package dk.ek.backend.catalog.model;

import dk.ek.backend.catalog.dto.ShowDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String year;
    private String runtime;
    private String poster;
    private String trailer;
    private String actors;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    @OneToMany
    private List<Show> show;


    public Movie(Long id, String title, String year, String runtime, String poster, String trailer, String actors, MovieStatus status, List<Show> show) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.poster = poster;
        this.trailer = trailer;
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

    public Movie() {}

    public List<Show> getShow() {
        return show;
    }

    public void setShow(List<Show> show) {this.show = show;}

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public MovieStatus getStatus() {
        return status;
    }
    public void setStatus(MovieStatus status) {
        this.status = status;}

}