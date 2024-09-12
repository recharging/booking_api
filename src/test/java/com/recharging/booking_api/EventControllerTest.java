package com.recharging.booking_api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.recharging.booking_api.controllers.EventController;
import com.recharging.booking_api.entities.Event;
import com.recharging.booking_api.services.EventService;

public class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEvents() {
        //SETUP
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event());
        //MOCK
        when(eventService.getAllEvents()).thenReturn(events);
        //EXECUTE
        ResponseEntity<ArrayList<Event>> response = eventController.getAllEvents();
        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetEventByEid() {
        //SETUP
        Event event = new Event();
        //MOCK
        when(eventService.findEventByEid(anyInt())).thenReturn(event);
        //EXECUTE
        ResponseEntity<Event> response = eventController.getEventByEid(1);
        //ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testDeleteAllEvents() {
        //EXECUTE
        ResponseEntity<Event> response = eventController.deleteAllEvents();
        //ASSERT
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        //VERIFY
        verify(eventService, times(1)).deleteAll();
    }

    @Test
    public void testDeleteEvent() {
        //EXECUTE
        ResponseEntity<Event> response = eventController.deleteEvent(1);
        //ASSERT
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        //VERIFY
        verify(eventService, times(1)).deleteEventbyEid(1);
    }
}