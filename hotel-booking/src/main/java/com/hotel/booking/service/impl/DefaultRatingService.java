package com.hotel.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.booking.dto.RatingReportDto;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.HotelService;
import com.hotel.booking.service.RatingService;

import java.util.List;

@Service
public class DefaultRatingService implements RatingService {
  private final HotelService hotelService;

  @Autowired
  DefaultRatingService(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @Override
  public RatingReportDto getRatingAverage(Long cityId) {
    return getRatingAverage(hotelService.getHotelsByCity(cityId));
  }

  @Override
  public RatingReportDto getRatingAverage(List<Hotel> hotels) {
    double ratingSum = 0;
    int ratingCount = 0;

    for (Hotel hotel : hotels) {
      ratingSum += hotel.getRating();
      ratingCount++;
    }

    return new RatingReportDto(ratingCount, ratingSum / ratingCount);
  }
}
