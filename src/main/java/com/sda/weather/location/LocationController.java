package com.sda.weather.location;


import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationController {

    private final ObjectMapper objectMapper;
    private final LocationService locationService;

    public LocationController(ObjectMapper objectMapper, LocationService locationService) {
        this.objectMapper = objectMapper;
        this.locationService = locationService;
    }

    public String addLocation(String json) {
        try {
            LocationDTO requestBody = objectMapper.readValue(json, LocationDTO.class);
            String city = requestBody.getCity();
            String region = requestBody.getRegion();
            String country = requestBody.getCountry();
            double longitude = requestBody.getLongitude();
            double latitude = requestBody.getLatitude();
            Location savedLocation = locationService.createLocation(city, longitude, latitude, region, country);
            LocationDTO locationDTO = mapToLocationDTO(savedLocation);
            String serializedLocationDTO = objectMapper.writeValueAsString(locationDTO);
            return serializedLocationDTO;
        } catch (Exception e) {
            return String.format("{\"errorMessage\":\"%s\"}", e.getMessage());
        }
    }

    private LocationDTO mapToLocationDTO(Location savedLocation) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(savedLocation.getId());
        locationDTO.setCity(savedLocation.getCity());
        locationDTO.setCountry(savedLocation.getCountry());
        locationDTO.setLatitude(savedLocation.getLatitude());
        locationDTO.setLongitude(savedLocation.getLongitude());
        return locationDTO;
    }

}
