package dk.ek.backend.common;

import dk.ek.backend.catalog.model.*;
import dk.ek.backend.catalog.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CinemaInitData implements CommandLineRunner {
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final OrderRepository orderRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;

    public CinemaInitData(HallRepository hallRepository, MovieRepository movieRepository, OrderRepository orderRepository, SeatRepository seatRepository, ShowRepository showRepository, TicketRepository ticketRepository) {
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
        this.orderRepository = orderRepository;
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running cinema initData");


        // 1) Create Halls and Seats
        Hall hall1 = new Hall();
        Hall hall2 = new Hall();
        hall1 = hallRepository.save(hall1);
        hall2 = hallRepository.save(hall2);

        // helper to create seats for a hall
        List<Seat> hall1Seats = createSeatsForHall(hall1, 21, 13);
        List<Seat> hall2Seats = createSeatsForHall(hall2, 26, 17);
        seatRepository.saveAll(hall1Seats);
        seatRepository.saveAll(hall2Seats);

        // 2) Create Movies
        Movie movie1 = new Movie();
        movie1.setTitle("The Matrix");
        movie1.setReleaseYear("1999");
        movie1.setRuntime("136m");
        movie1.setPoster("https://m.media-amazon.com/images/I/51ISve-1n1S._UF1000,1000_QL80_.jpg");
        movie1.setTrailer("https://youtu.be/m8e-FF8MsqU");
        movie1.setActors("Keanu Reeves, Carrie-Anne Moss");
        movie1.setStatus(MovieStatus.AIRING);

        Movie movie2 = new Movie();
        movie2.setTitle("Interstellar");
        movie2.setReleaseYear("2014");
        movie2.setRuntime("169m");
        movie2.setPoster("https://static.posters.cz/image/750/23157.jpg");
        movie2.setTrailer("https://youtu.be/zSWdZVtXT7E");
        movie2.setActors("Matthew McConaughey, Anne Hathaway");
        movie2.setStatus(MovieStatus.AIRING);


        movieRepository.saveAll(Arrays.asList(movie1, movie2));


        // 3) Create Shows linked to movies and halls
        LocalDateTime now = LocalDateTime.now();
        Show show1 = new Show();
        show1.setMovie(movie1);
        show1.setHall(hall1);
        show1.setTimeOfShowing(now.plusHours(2));

        Show show2 = new Show();
        show2.setMovie(movie1);
        show2.setHall(hall2);
        show2.setTimeOfShowing(now.plusDays(1).withHour(18).withMinute(30));

        Show show3 = new Show();
        show3.setMovie(movie2);
        show3.setHall(hall1);
        show3.setTimeOfShowing(now.plusDays(2).withHour(20).withMinute(0));

        showRepository.saveAll(Arrays.asList(show1, show2, show3));

        // 4) Create Tickets for some seats and shows
        // pick first few seats of each hall
        List<Seat> seatsForShow1 = hall1Seats.stream().filter(s -> s.getRowNumber() == 1 && s.getSeatNumber() <= 3).collect(Collectors.toList());
        List<Seat> seatsForShow2 = hall2Seats.stream().filter(s -> s.getRowNumber() == 1 && s.getSeatNumber() <= 2).collect(Collectors.toList());
        List<Seat> seatsForShow3 = hall1Seats.stream().filter(s -> s.getRowNumber() == 2 && s.getSeatNumber() <= 2).collect(Collectors.toList());

        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(createTicketsForShow(show1, seatsForShow1, 100.0));
        tickets.addAll(createTicketsForShow(show2, seatsForShow2, 95.0));
        tickets.addAll(createTicketsForShow(show3, seatsForShow3, 110.0));
        ticketRepository.saveAll(tickets);

        // 5) Create Orders that contain tickets
        // Order 1 buys 2 tickets from show1
        Order order1 = new Order();
        order1.setCustomerEmail("alice@example.com");
        order1.setCustomerPhoneNumber(12345678);
        order1.setTimeOfPurchase(now);
        Set<Ticket> order1Tickets = new HashSet<>(tickets.stream().filter(t -> Objects.equals(t.getShow().getId(), show1.getId())).limit(2).collect(Collectors.toSet()));
        order1.setTickets(order1Tickets);
        order1.setPrice(order1Tickets.stream().mapToDouble(Ticket::getPrice).sum());

        // Order 2 buys 1 ticket from show2 and 1 from show3
        Order order2 = new Order();
        order2.setCustomerEmail("bob@example.com");
        order2.setCustomerPhoneNumber(87654321);
        order2.setTimeOfPurchase(now.plusMinutes(5));
        Set<Ticket> order2Tickets = new HashSet<>();
        tickets.stream().filter(t -> Objects.equals(t.getShow().getId(), show2.getId())).findFirst().ifPresent(order2Tickets::add);
        tickets.stream().filter(t -> Objects.equals(t.getShow().getId(), show3.getId())).findFirst().ifPresent(order2Tickets::add);
        order2.setTickets(order2Tickets);
        order2.setPrice(order2Tickets.stream().mapToDouble(Ticket::getPrice).sum());

        orderRepository.saveAll(Arrays.asList(order1, order2));


    }


    private List<Seat> createSeatsForHall(Hall hall, int rows, int seatsPerRow) {
        List<Seat> seats = new ArrayList<>();
        for (int r = 1; r <= rows; r++) {
            for (int s = 1; s <= seatsPerRow; s++) {
                Seat seat = new Seat();
                seat.setRowNumber(r);
                seat.setSeatNumber(s);
                seat.setHall(hall);
                seats.add(seat);
            }
        }
        return seats;
    }

    private List<Ticket> createTicketsForShow(Show show, List<Seat> seats, double basePrice) {
        LocalDateTime showTime = show.getTimeOfShowing();
        List<Ticket> list = new ArrayList<>();
        for (Seat s : seats) {
            Ticket t = new Ticket();
            t.setShow(show);
            t.setSeat(s);
            t.setTimeOfShowing(showTime);
            t.setPrice(basePrice);
            t.setStatus(true);
            list.add(t);
        }
        return list;
    }
}
