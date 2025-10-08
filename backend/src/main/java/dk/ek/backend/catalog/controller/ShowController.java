package dk.ek.backend.catalog.controller;

import dk.ek.backend.catalog.dto.HallDto;
import dk.ek.backend.catalog.dto.ShowDto;
import dk.ek.backend.catalog.service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public ResponseEntity<List<ShowDto>> getAllShows(){
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> getShowById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(showService.getShowById(id));
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<ShowDto> createShow(@RequestBody ShowDto showDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(showService.createShow(showDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowDto> updateShow(@PathVariable Long id, @RequestBody ShowDto showDto){
        try{
            return ResponseEntity.ok(showService.updateShow(id, showDto));
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id){
        try {
            showService.deleteShow(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("/movie/{movieId}")
    public List<ShowDto> getShowsByMovie(@PathVariable Long movieId) {
        return showService.getShowByMovieId(movieId);
    }



}
