package com.hotel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.booking.model.City;

public interface CityRepository extends JpaRepository<City, Long> {}
