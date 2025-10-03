package dk.ek.backend.catalog.controller;

import dk.ek.backend.catalog.dto.TMdbDto;
import dk.ek.backend.catalog.dto.UserDto;
import dk.ek.backend.catalog.service.TMdbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TMdbController {

    private final TMdbService tMdbService;

    public TMdbController(TMdbService tMdbService){
        this.tMdbService = tMdbService;
    }


    @GetMapping("{/id}")
    public ResponseEntity<TMdbDto> getMovieById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(tMdbService.getMovieById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
