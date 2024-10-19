package com.hotel.booking.dto;

public class CityDto {
    private Long id;
    private String name;
    private double cityCentreLatitude;
    private double cityCentreLongitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCityCentreLatitude() {
        return cityCentreLatitude;
    }

    public void setCityCentreLatitude(double cityCentreLatitude) {
        this.cityCentreLatitude = cityCentreLatitude;
    }

    public double getCityCentreLongitude() {
        return cityCentreLongitude;
    }

    public void setCityCentreLongitude(double cityCentreLongitude) {
        this.cityCentreLongitude = cityCentreLongitude;
    }
}
