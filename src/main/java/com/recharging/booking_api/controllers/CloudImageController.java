package com.recharging.booking_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recharging.booking_api.entities.CloudImage;
import com.recharging.booking_api.services.CloudImageService;
import com.recharging.booking_api.services.EventService;

@RestController
@RequestMapping("/api")
public class CloudImageController {

  private final CloudImageService cloudImageService;
  private final EventService eventService;

  public CloudImageController(CloudImageService cloudImageService, EventService eventService) {
    this.cloudImageService = cloudImageService;
    this.eventService = eventService;
  }

  // Add a new CloudImage to an Event
  @PostMapping("/event/{eventId}/cloudimage")
  public ResponseEntity<CloudImage> addCloudImageToEvent(@PathVariable int eventId,
      @RequestBody CloudImage cloudImage) {
    try {
      CloudImage newCloudImage = cloudImageService.addCloudImage(eventId, cloudImage);
      return new ResponseEntity<>(newCloudImage, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If event not found
    }
  }

}
