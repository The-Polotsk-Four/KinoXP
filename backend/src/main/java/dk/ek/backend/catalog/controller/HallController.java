//package dk.ek.backend.catalog.controller;
//
//import dk.ek.backend.catalog.dto.HallDto;
//import dk.ek.backend.catalog.service.HallService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/halls")
//public class HallController {
//
//    private final HallService hallService;
//
//    public HallController(HallService hallService) {
//        this.hallService = hallService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<HallDto>> getAllHalls(){
//        return ResponseEntity.ok(hallService.getAllHalls());
//    }
//}
