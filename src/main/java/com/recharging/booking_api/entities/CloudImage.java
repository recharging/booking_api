package com.recharging.booking_api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cloud_image")
public class CloudImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "url", nullable = false)
  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id", nullable = false)
  @JsonBackReference // Prevents infinite loop by marking the back reference
  private Event event;

  // Default constructor
  public CloudImage() {
  }

  // Constructor with parameters
  public CloudImage(String url, Event event) {
    this.url = url;
    this.event = event;
  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }
}
