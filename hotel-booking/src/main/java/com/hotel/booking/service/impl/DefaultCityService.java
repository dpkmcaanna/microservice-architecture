package com.hotel.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.booking.exception.BadRequestException;
import com.hotel.booking.exception.ElementNotFoundException;
import com.hotel.booking.model.City;
import com.hotel.booking.repository.CityRepository;
import com.hotel.booking.service.CityService;

import java.util.List;

@Service
class DefaultCityService implements CityService {
  private final CityRepository cityRepository;

  @Autowired
  DefaultCityService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @Override
  public City getCityById(Long id) {
    return cityRepository
        .findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Could not find city with ID provided"));
  }

  @Override
  public List<City> getAllCities() {
    return cityRepository.findAll();
  }

  @Override
  public City createCity(City city) {
    if (city.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new City");
    }

    return cityRepository.save(city);
  }
}
