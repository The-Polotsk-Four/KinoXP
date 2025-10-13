package dk.ek.backend.catalog.controller;


import dk.ek.backend.catalog.dto.OrderDto;
import dk.ek.backend.catalog.dto.ShowDto;
import dk.ek.backend.catalog.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(
            @RequestParam(value = "customerEmail", required = false) String customerEmail,
            @RequestParam(value = "customerPhoneNumber", required = false) Integer customerPhoneNumber) {
        if (customerEmail != null) {
            return ResponseEntity.ok(orderService.getByCustomerEmail(customerEmail));
        } else if (customerPhoneNumber != null) {
            return ResponseEntity.ok(orderService.getByCustomerPhoneNumber(customerPhoneNumber));
        }
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(Long id){
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/email/{customerEmail}")
    public List<OrderDto> getOrderByEmail(@PathVariable String customerEmail) {
        return orderService.getByCustomerEmail(customerEmail);
    }

    @GetMapping("/phonenumber/{customerPhoneNumber}")
    public List<OrderDto> getOrderByPhoneNumber(@PathVariable int customerPhoneNumber) {
        return orderService.getByCustomerPhoneNumber(customerPhoneNumber);
    }


    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order,
                                                @RequestParam Long showId,
                                                @RequestParam Set<Long> seatId){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createNewOrder(order, showId,seatId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id,
                                                @RequestBody OrderDto orderDto){
        try{
            return ResponseEntity.ok(orderService.updateOrder(id, orderDto));
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
