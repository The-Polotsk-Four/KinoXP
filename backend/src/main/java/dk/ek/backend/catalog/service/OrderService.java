package dk.ek.backend.catalog.service;


import dk.ek.backend.catalog.dto.OrderDto;
import dk.ek.backend.catalog.dto.TicketDto;
import dk.ek.backend.catalog.mapper.Mapper;
import dk.ek.backend.catalog.model.Order;
import dk.ek.backend.catalog.model.Ticket;
import dk.ek.backend.catalog.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TicketService ticketService;

    public OrderService(OrderRepository orderRepository, TicketService ticketService) {
        this.orderRepository = orderRepository;
        this.ticketService = ticketService;
    }

    public List<OrderDto> getAllOrders(){
        List<OrderDto> orderDtos= new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders){
            orderDtos.add(Mapper.toDto(order));
        }
        return orderDtos;
    }

    public OrderDto getOrderById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            return Mapper.toDto(order.get());
        }
        throw new RuntimeException("Cant find order with id: "+id);
    }

    public List<OrderDto> getByCustomerEmail(String customerEmail){
        List<Order> orders = orderRepository.findByCustomerEmail(customerEmail);
        if (orders.isEmpty()){
            throw new RuntimeException("Cant find order with id: "+customerEmail);
        }
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders){
            orderDtos.add(Mapper.toDto(order));
        }
        return orderDtos;
    }

    public List<OrderDto> getByCustomerPhoneNumber(int CustomerPhoneNumber){
        List<Order> orders = orderRepository.findByCustomerPhoneNumber(CustomerPhoneNumber);
        if (orders.isEmpty()){
            throw new RuntimeException("Cant find order with id: "+CustomerPhoneNumber);
        }
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders){
            orderDtos.add(Mapper.toDto(order));
        }
        return orderDtos;
    }


    public OrderDto createNewOrder(int quantity, OrderDto orderDto, Long showId, Long seatId) {
        double totalPrice = 0;
        Set<Ticket> tickets = new HashSet<>();

        for (int i = 0; i < quantity; i++) {
            TicketDto ticketDto = ticketService.createTicket(showId, seatId);
            Ticket ticket = Mapper.toEntity(ticketDto);
            tickets.add(ticket);
            totalPrice += 110;
        }

        Order order = new Order();
        order.setCustomerEmail(orderDto.customerEmail());
        order.setCustomerPhoneNumber(orderDto.customerPhoneNumber());
        order.setTimeOfPurchase(LocalDateTime.now());
        order.setPrice(totalPrice);
        order.setTickets(tickets);

        Order savedOrder = orderRepository.save(order);
        return Mapper.toDto(savedOrder);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto){
        Optional<Order> findOrder = orderRepository.findById(id);

        if (findOrder.isEmpty()){
            throw new RuntimeException("Cant find order with id: "+id);
        }
        Order orderToUpdate = findOrder.get();

        orderToUpdate.setCustomerEmail(orderDto.customerEmail());
        orderToUpdate.setCustomerPhoneNumber(orderDto.customerPhoneNumber());
        orderToUpdate.setPrice(orderDto.price());
        orderToUpdate.setTimeOfPurchase(orderDto.timeOfPurchase());

        if (orderDto.tickets() != null) {
            Set<Ticket> tickets = new HashSet<>();
            for (TicketDto ticketDto : orderDto.tickets()) {
                tickets.add(Mapper.toEntity(ticketDto));
            }
            orderToUpdate.setTickets(tickets);
        }
        Order updatedOrder = orderRepository.save(orderToUpdate);
        return Mapper.toDto(updatedOrder);

    }
}
