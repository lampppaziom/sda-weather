package com.sda.weather.location;

import lombok.Data;

@Data
public class LocationDTO {
    private Long id;
    private String city;
    private double longitude;
    private double Latitude;
    private String region;
    private String country;
}
