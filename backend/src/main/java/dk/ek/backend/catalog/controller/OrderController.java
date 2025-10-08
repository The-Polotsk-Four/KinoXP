package dk.ek.backend.catalog.controller;


import dk.ek.backend.catalog.dto.OrderDto;
import dk.ek.backend.catalog.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(@RequestParam(required = false) String customerEmail,
                                                    @RequestParam(required = false) Integer customerPhoneNumber){
        if (customerEmail!=null){
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

    @PostMapping("/{id}")
    public ResponseEntity<OrderDto> createOrder(@RequestParam int quantity,
                                                @RequestBody OrderDto order,
                                                @RequestParam Long showId,
                                                @RequestParam Long seatId){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createNewOrder(quantity,order, showId,seatId));
    }





}
