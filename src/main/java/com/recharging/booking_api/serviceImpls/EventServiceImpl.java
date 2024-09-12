package com.recharging.booking_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.recharging.booking_api.entities.Event;
import com.recharging.booking_api.entities.Showtime;
import com.recharging.booking_api.exceptions.EventNotFoundException;
import com.recharging.booking_api.repositories.EventRepository;
import com.recharging.booking_api.repositories.ShowtimeRepository;
import com.recharging.booking_api.services.EventService;

@Service
public class EventServiceImpl implements EventService {

  private EventRepository eventRepository;
  private ShowtimeRepository showtimeRepository;

  private EventServiceImpl(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Event findByDescription(String description) {
    Event event = eventRepository.findByDescription(description);
    return event;
  };

  @Override
  public Event findEventByEid(Integer eid) {
    return eventRepository.findById(eid).orElseThrow(() -> new EventNotFoundException(eid));
  }

  @Override
  public ArrayList<Event> getAllEvents() {
    List<Event> allEvents = eventRepository.findAll();
    return (ArrayList<Event>) allEvents;
  }

  @Override
  public Showtime addShowtimeToEvent(Event event, Showtime showtime, ShowtimeRepository showtimeRepository) {
    // add the Event to the Showtime
    showtime.setEvent(event);
    // save the Showtime to the database
    return showtimeRepository.save(showtime);
  }

  @Override
  public void deleteAll() {
    eventRepository.deleteAll();
  }

  @Override
  public void deleteEventbyEid(Integer eid) {
    eventRepository.findById(eid).orElseThrow(() -> new EventNotFoundException(eid));
    eventRepository.deleteById(eid);
  }
}
