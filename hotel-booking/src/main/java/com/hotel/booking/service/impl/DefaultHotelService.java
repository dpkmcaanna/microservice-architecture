package com.hotel.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.booking.dto.CityDto;
import com.hotel.booking.dto.HotelResponseDto;
import com.hotel.booking.exception.BadRequestException;
import com.hotel.booking.exception.ElementNotFoundException;
import com.hotel.booking.model.City;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.repository.HotelRepository;
import com.hotel.booking.service.CityService;
import com.hotel.booking.service.HotelService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
class DefaultHotelService implements HotelService {
  private final HotelRepository hotelRepository;
  private final CityService cityService;

  @Autowired
  DefaultHotelService(HotelRepository hotelRepository, CityService cityService) {
    this.hotelRepository = hotelRepository;
    this.cityService = cityService;
  }

  @Override
  public List<Hotel> getAllHotels() {
    return hotelRepository.findAll();
  }

  @Override
  public List<Hotel> getHotelsByCity(Long cityId) {
    return hotelRepository.findAll().stream()
        .filter((hotel) -> cityId.equals(hotel.getCity().getId()))
        .collect(Collectors.toList());
  }

  @Override
  public Hotel createNewHotel(Hotel hotel) {
    if (hotel.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Hotel");
    }

    return hotelRepository.save(hotel);
  }

  @Override
  public Hotel getHotelById(Long id) {
    Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new ElementNotFoundException("Could not find hotel with ID provided"));
    return hotel.isDeleted() ? null : hotel;
  }

  @Override
  public void deleteHotel(Long id) {
    Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new ElementNotFoundException("Could not find hotel with ID provided"));

    if (hotel.isDeleted()) {
      throw new BadRequestException("The hotel with ID " + id + " is already deleted");
    }
    hotel.setDeleted(true);
    hotelRepository.save(hotel);
  }

  @Override
  public List<HotelResponseDto> getClosestHotels(Long cityId) {
    City city = cityService.getCityById(cityId);
    List<Hotel> hotels = hotelRepository.findAll();

    return hotels.stream()
            .filter(hotel -> !hotel.isDeleted())
            .sorted(Comparator.comparingDouble(hotel -> calculateDistance(
                    hotel.getLatitude(), hotel.getLongitude(),
                    city.getCityCentreLatitude(), city.getCityCentreLongitude())))
            .limit(3)
            .map(hotel -> {
              HotelResponseDto dto = new HotelResponseDto();
              dto.setId(hotel.getId());
              dto.setName(hotel.getName());
              dto.setRating(hotel.getRating());
              dto.setLatitude(hotel.getLatitude());
              dto.setLongitude(hotel.getLongitude());
              dto.setAddress(hotel.getAddress());
              // Populate CityDto
              CityDto cityDto = new CityDto();
              cityDto.setId(city.getId());
              cityDto.setName(city.getName());
              cityDto.setCityCentreLatitude(city.getCityCentreLatitude());
              cityDto.setCityCentreLongitude(city.getCityCentreLongitude());
              dto.setCity(cityDto);

              return dto;
            })
            .collect(Collectors.toList());
  }

  private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    final int R = 6371; // Radius of the Earth in km
    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 - lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
            Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                    Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c; // Convert to kilometers
  }
}
