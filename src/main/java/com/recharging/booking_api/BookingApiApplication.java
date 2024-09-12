package com.recharging.booking_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookingApiApplication {

  private static final Logger logger = LoggerFactory.getLogger(BookingApiApplication.class);

  public static void main(String[] args) {

    SpringApplication.run(BookingApiApplication.class, args);
  }

}
