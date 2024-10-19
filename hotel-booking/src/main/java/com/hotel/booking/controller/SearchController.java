package com.hotel.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hotel.booking.dto.HotelResponseDto;
import com.hotel.booking.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final HotelService hotelService;

    @Autowired
    public SearchController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/{cityId}")
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponseDto> findClosestHotels(@PathVariable Long cityId) {
        return hotelService.getClosestHotels(cityId);
    }
}
