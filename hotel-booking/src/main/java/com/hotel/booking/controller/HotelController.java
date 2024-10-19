package com.hotel.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.hotel.booking.dto.CityDto;
import com.hotel.booking.dto.HotelResponseDto;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
  private final HotelService hotelService;

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public HotelResponseDto getHotelById(@PathVariable("id")Long id) {
    Hotel hotel = hotelService.getHotelById(id);
    if (hotel == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
    }
    return convertToDto(hotel);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteHotel(@PathVariable Long id) {
    hotelService.deleteHotel(id);
  }

  private HotelResponseDto convertToDto(Hotel hotel) {
    HotelResponseDto response = new HotelResponseDto();
    response.setId(hotel.getId());
    response.setName(hotel.getName());
    response.setRating(hotel.getRating());
    response.setLatitude(hotel.getLatitude());
    response.setLongitude(hotel.getLongitude());
    response.setAddress(hotel.getAddress());

    CityDto cityDto = new CityDto();
    cityDto.setId(hotel.getCity().getId());
    cityDto.setName(hotel.getCity().getName());
    cityDto.setCityCentreLatitude(hotel.getCity().getCityCentreLatitude());
    cityDto.setCityCentreLongitude(hotel.getCity().getCityCentreLongitude());

    response.setCity(cityDto);
    return response;
  }
}
