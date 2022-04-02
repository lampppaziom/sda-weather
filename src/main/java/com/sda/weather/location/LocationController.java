package com.sda.weather.location;


public class LocationController {

    private final ObjectMapper objectMapper;
    private final LocationService locationService;

    public LocationController(ObjectMapper objectMapper, LocationService locationServiceService) {
        this.objectMapper = objectMapper;
        this.locationService = locationService;
    }

    public String createLocation(String json) {
        try {
            LocationDTO requestBody = objectMapper.readValue(json, LocationDTO.class);
            String city = requestBody.getCity();
            String region = requestBody.getRegion();
            String country = requestBody.getCountry();
            double longitude = requestBody.getLongitude();
            double latitude = requestBody.getLatitude();
            Location savedLocation = locationService.createLocation(city, region, country,
                    longitude, latitude);
            LocationDTO locationDTO = mapToLocation
        }
    }

}
