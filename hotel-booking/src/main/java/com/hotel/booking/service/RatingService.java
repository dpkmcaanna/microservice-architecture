package com.hotel.booking.service;

import java.util.List;

import com.hotel.booking.dto.RatingReportDto;
import com.hotel.booking.model.Hotel;

public interface RatingService {
    RatingReportDto getRatingAverage(Long cityId);

    RatingReportDto getRatingAverage(List<Hotel> hotels);
}
