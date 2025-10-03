//package dk.ek.backend.catalog.controller;
//
//import dk.ek.backend.catalog.dto.SnackDto;
//import dk.ek.backend.catalog.model.Snack;
//import dk.ek.backend.catalog.service.SnackService;
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
//    // Entity to DTO
//    private SnackDto toDto(Snack snack) {
//        return new SnackDto(
//                snack.getName(),
//                snack.getPrice(),
//                snack.getQuantity(),
//                snack.getDateOfPurchase()
//        );
//    }
//
//    //DTO to Entity
//    private Snack toEntity(SnackDto dto) {
//        Snack snack = new Snack();
//        snack.setName(dto.getName());
//        snack.setPrice(dto.getPrice());
//        snack.setQuantity(dto.getQuantity());
//        snack.setDateOfPurchase(dto.getDateOfPurchase());
//        return snack;
//    }
//
//    // Get snacks
//    @GetMapping
//    public List<SnackDto> getAllSnacks() {
//        return snackService.findAll()
//                .stream()
//                .map(this::toDto)
//                .collect(Collectors.toList());
//    }
//
//    // Get snack by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<SnackDto> getSnackById(@PathVariable Long id) {
//        return snackService.findById(id)
//                .map(snack -> ResponseEntity.ok(toDto(snack)))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // Create snack
//    @PostMapping
//    public SnackDto createSnack(@RequestBody SnackDto snackDto) {
//        Snack saved = snackService.save(toEntity(snackDto));
//        return toDto(saved);
//    }
//
//    // Update snack
//    @PutMapping("/{id}")
//    public ResponseEntity<SnackDto> updateSnack(@PathVariable Long id, @RequestBody SnackDto snackDto) {
//        return snackService.findById(id)
//                .map(existing -> {
//                    existing.setName(snackDto.getName());
//                    existing.setPrice(snackDto.getPrice());
//                    existing.setQuantity(snackDto.getQuantity());
//                    existing.setDateOfPurchase(snackDto.getDateOfPurchase());
//                    Snack updated = snackService.save(existing);
//                    return ResponseEntity.ok(toDto(updated));
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // Delete snack
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSnack(@PathVariable Long id) {
//        return snackService.findById(id)
//                .map(snack -> {
//                    snackService.deleteById(id);
//                    return ResponseEntity.noContent().build();
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//}
