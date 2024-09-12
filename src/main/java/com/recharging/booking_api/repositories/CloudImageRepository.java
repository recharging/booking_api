package com.recharging.booking_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.recharging.booking_api.entities.CloudImage;

public interface CloudImageRepository extends JpaRepository<CloudImage, Integer> {
}
