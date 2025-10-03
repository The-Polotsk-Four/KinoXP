//package dk.ek.backend.catalog.controller;
//
//import dk.ek.backend.catalog.dto.SnackDto;
//import dk.ek.backend.catalog.model.Snack;
//import dk.ek.backend.catalog.service.SnackService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/snacks")
//public class SnackController {
//
//    private final SnackService snackService;
//
//    public SnackController(SnackService snackService) {
//        this.snackService = snackService;
//    }
//
//    // Get snacks
//    @GetMapping
//    public ResponseEntity<List<SnackDto>> getAllSnacks(@RequestParam(required = false) String snack) {
//        if (snack != null && !snack.isEmpty()) {
//            return ResponseEntity.ok(snackService.getByName(snack));
//        }
//        return ResponseEntity.ok(snackService.getAll());
//    }
//
//    // Get snack by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<SnackDto> getSnackById(@PathVariable Long id) {
//        try {
//            return ResponseEntity.ok(snackService.getById(id));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    // Create snack
//    @PostMapping
//    public ResponseEntity<SnackDto> createSnack(@RequestBody SnackDto snackDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(snackService.createSnack(snackDto));
//    }
//
//    // Update snack
//    @PutMapping("/{id}")
//    public ResponseEntity<SnackDto> updateSnack(@PathVariable Long id, @RequestBody SnackDto snackDto) {
//        try {
//            return ResponseEntity.ok(snackService.updateSnack(id, snackDto));
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    // Delete snack
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSnack(@PathVariable Long id) {
//        try {
//            snackService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//}
