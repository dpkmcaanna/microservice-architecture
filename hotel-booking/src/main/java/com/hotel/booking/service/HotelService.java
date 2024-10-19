package com.hotel.booking.service;

import java.util.List;

import com.hotel.booking.dto.HotelResponseDto;
import com.hotel.booking.model.Hotel;

public interface HotelService {
  List<Hotel> getAllHotels();

  List<Hotel> getHotelsByCity(Long cityId);

  Hotel createNewHotel(Hotel hotel);

  Hotel getHotelById(Long Id);

  void deleteHotel(Long id);

  List<HotelResponseDto> getClosestHotels(Long cityId);
}
