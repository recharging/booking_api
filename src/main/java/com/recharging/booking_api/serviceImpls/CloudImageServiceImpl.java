package com.recharging.booking_api.serviceImpls;

import org.springframework.stereotype.Service;

import com.recharging.booking_api.entities.CloudImage;
import com.recharging.booking_api.entities.Event;
import com.recharging.booking_api.repositories.CloudImageRepository;
import com.recharging.booking_api.repositories.EventRepository;
import com.recharging.booking_api.services.CloudImageService;

@Service
public class CloudImageServiceImpl implements CloudImageService {

  private final CloudImageRepository cloudImageRepository;
  private final EventRepository eventRepository;

  public CloudImageServiceImpl(CloudImageRepository cloudImageRepository, EventRepository eventRepository) {
    this.cloudImageRepository = cloudImageRepository;
    this.eventRepository = eventRepository;
  }

  @Override
  public CloudImage addCloudImage(int eid, CloudImage cloudImage) {
    Event event = eventRepository.findById(eid).orElseThrow(() -> new RuntimeException("Event not found"));
    cloudImage.setEvent(event);
    return cloudImageRepository.save(cloudImage);
  }

}
