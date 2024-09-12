package com.recharging.booking_api.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "booking")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bid")
  private Integer bid;

  @Min(value = 1, message = "Booked seats must be at least 1")
  @Column(name = "seats")
  private int bookedSeats;

  // Constructor
  public Booking(Showtime showtime, User user, int bookedSeats) {
    this.showtime = showtime;
    this.user = user;
    this.bookedSeats = bookedSeats;
  }

  @JsonBackReference(value = "showtime-booking")
  @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.MERGE)
  @JoinColumn(name = "showtime", referencedColumnName = "sid")
  private Showtime showtime;

  @JsonBackReference(value = "user-booking")
  @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.MERGE)
  @JoinColumn(name = "username", referencedColumnName = "uid")
  private User user;

  @JsonProperty
  private LocalDate getShowtimeDate() {
    return showtime == null ? null : showtime.getDate();
  }

  @JsonProperty
  private String getShowtimeEventDescription() {
    return showtime == null ? null : showtime.getEvent().getDescription();
  }


}