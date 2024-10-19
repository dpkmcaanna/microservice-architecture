package com.hotel.booking.service;

import java.util.List;

import com.hotel.booking.model.City;

public interface CityService {
  List<City> getAllCities();

  City getCityById(Long id);

  City createCity(City city);
}
