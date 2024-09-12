package com.recharging.booking_api.serviceImpls;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import com.recharging.booking_api.entities.Event;
import com.recharging.booking_api.entities.Showtime;
import com.recharging.booking_api.entities.User;
import com.recharging.booking_api.repositories.BookingRepository;
import com.recharging.booking_api.repositories.EventRepository;
import com.recharging.booking_api.repositories.ShowtimeRepository;
import com.recharging.booking_api.repositories.UserRepository;
import com.recharging.booking_api.services.BookingService;
import com.recharging.booking_api.services.EventService;
import com.recharging.booking_api.services.ShowtimeService;
import com.recharging.booking_api.services.UserService;

@Component
public class DataLoader {

  private UserRepository userRepository;
  private EventRepository eventRepository;
  private ShowtimeRepository showtimeRepository;
  private BookingRepository bookingRepository;
  private EventService eventService;
  private BookingService bookingService;
  private ShowtimeService showtimeService;
  private UserService userService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  // @Autowired
  public DataLoader(UserRepository userRepository, EventRepository eventRepository,
      ShowtimeRepository showtimeRepository, BookingRepository bookingRepository, EventService eventService,
      BookingService bookingService, ShowtimeService showtimeService, UserService userService) {
    this.userRepository = userRepository;
    this.eventRepository = eventRepository;
    this.showtimeRepository = showtimeRepository;
    this.bookingRepository = bookingRepository;
    this.eventService = eventService;
    this.bookingService = bookingService;
    this.showtimeService = showtimeService;
    this.userService = userService;
  }

  @PostConstruct
  public void loadData() {
    // clear the database first
    userRepository.deleteAll();
    eventRepository.deleteAll();
    showtimeRepository.deleteAll();
    bookingRepository.deleteAll();

    // load data here
    // [Activity 2 - validation]
    userRepository.save(new User("Abigail", "abigail@gmail.com", passwordEncoder.encode("password123")));
    userRepository.save(new User("Bertrand", "bertrand@gmail.com", passwordEncoder.encode("password456")));
    userRepository.save(new User("Charlie", "charlie@gmail.com", passwordEncoder.encode("password789")));
    userRepository.save(new User("Daniel", "daniel@gmail.com", passwordEncoder.encode("password321")));

    eventRepository.save(new Event("Taylor Swift Concert",
        "https://www.studentuniverse.com/blog/wp-content/uploads/2014/04/Most-Beautiful-Places-to-Travel-Featured-Image.jpg"));
    eventService.addShowtimeToEvent(eventService.findByDescription("Taylor Swift Concert"),
        new Showtime(LocalDate.of(2024, Month.MARCH, 2), 2000, 2000), showtimeRepository);
    bookingService.createBooking(showtimeService.findShowtimeBySid(1), userService.findOneUser("Abigail"), 1);
    eventService.addShowtimeToEvent(eventService.findByDescription("Taylor Swift Concert"),
        new Showtime(LocalDate.of(2024, Month.MARCH, 3), 2000, 2000), showtimeRepository);
    bookingService.createBooking(showtimeService.findShowtimeBySid(2), userService.findOneUser("Abigail"), 1);
    eventService.addShowtimeToEvent(eventService.findByDescription("Taylor Swift Concert"),
        new Showtime(LocalDate.of(2024, Month.MARCH, 4), 2000, 2000), showtimeRepository);
    bookingService.createBooking(showtimeService.findShowtimeBySid(3), userService.findOneUser("Abigail"), 1);
    eventService.addShowtimeToEvent(eventService.findByDescription("Taylor Swift Concert"),
        new Showtime(LocalDate.of(2024, Month.MARCH, 7), 2000, 2000), showtimeRepository);
    eventService.addShowtimeToEvent(eventService.findByDescription("Taylor Swift Concert"),
        new Showtime(LocalDate.of(2024, Month.MARCH, 8), 2000, 2000), showtimeRepository);
    eventService.addShowtimeToEvent(eventService.findByDescription("Taylor Swift Concert"),
        new Showtime(LocalDate.of(2024, Month.MARCH, 9), 2000, 2000), showtimeRepository);

    eventRepository.save(new Event("Singapore vs South Korea World Cup Qualifier",
        "https://www.planetware.com/wpimages/2019/10/asia-best-places-to-visit-mount-fuji-japan.jpg"));
    eventService.addShowtimeToEvent(eventService.findByDescription("Singapore vs South Korea World Cup Qualifier"),
        new Showtime(LocalDate.of(2024, Month.JUNE, 6), 2000, 2000), showtimeRepository);
    bookingService.createBooking(showtimeService.findShowtimeBySid(7), userService.findOneUser("Bertrand"), 6);

    eventRepository.save(new Event("Formula 1 Race",
        "https://img.veenaworld.com/wp-content/uploads/2023/09/Famous-Places-in-the-World-Checking-off-the-Ultimate-Bucket-List.jpg"));
    eventService.addShowtimeToEvent(eventService.findByDescription("Formula 1 Race"),
        new Showtime(LocalDate.of(2024, Month.SEPTEMBER, 1), 2000, 2000), showtimeRepository);
    eventService.addShowtimeToEvent(eventService.findByDescription("Formula 1 Race"),
        new Showtime(LocalDate.of(2024, Month.SEPTEMBER, 2), 2000, 2000), showtimeRepository);
    bookingService.createBooking(showtimeService.findShowtimeBySid(9), userService.findOneUser("Charlie"), 2);
    bookingService.createBooking(showtimeService.findShowtimeBySid(9), userService.findOneUser("Daniel"), 6);
    eventService.addShowtimeToEvent(eventService.findByDescription("Formula 1 Race"),
        new Showtime(LocalDate.of(2024, Month.SEPTEMBER, 3), 2000, 2000), showtimeRepository);

  }
}
