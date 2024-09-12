package com.recharging.booking_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "eid")
  private Integer eid;

  @NotBlank(message = "Description is mandatory")
  @Column(name = "description")
  private String description;

  @Column(name = "image_url")
  private String imageUrl;

  // One-to-Many relationship with CloudImage
  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JsonManagedReference // Marks the managed side of the relationship
  private List<CloudImage> cloudImages;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
  private List<Showtime> showtime;

  // Default constructor
  public Event() {
  }

  // Constructor with parameters
  public Event(String description, String imageUrl) {
    this.description = description;
    this.imageUrl = imageUrl;
  }

  // Additional constructors, getters, and setters can be added as needed
}
