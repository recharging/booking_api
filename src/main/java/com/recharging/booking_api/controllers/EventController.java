package com.recharging.booking_api.controllers;

import java.util.ArrayList;

import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.recharging.booking_api.entities.Event;
import com.recharging.booking_api.services.EventService;

@RestController
@Transactional
public class EventController {

  private EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  // READ (GET ALL)
  @GetMapping("/events")

  public ResponseEntity<ArrayList<Event>> getAllEvents() {
    ArrayList<Event> allEvents = eventService.getAllEvents();
    return new ResponseEntity<>(allEvents, HttpStatus.OK);
  }

  // READ (GET ONE)
  @GetMapping("/event/{id}")
  public ResponseEntity<Event> getEventByEid(@PathVariable(name = "id") Integer eid) {
    Event foundEvent = eventService.findEventByEid(eid);
    return new ResponseEntity<>(foundEvent, HttpStatus.OK);
  }

  // DELETE (DELETE ALL)
  @DeleteMapping("/events")
  public ResponseEntity<Event> deleteAllEvents() {
    eventService.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // DELETE (DELETE ONE)
  @DeleteMapping("/event/{id}")
  public ResponseEntity<Event> deleteEvent(@PathVariable(name = "id") Integer eid) {
    eventService.deleteEventbyEid(eid);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
