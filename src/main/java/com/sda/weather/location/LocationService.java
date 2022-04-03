package com.sda.weather.location;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocationService {

    private final LocationRepository locationRepository;
    private final ObjectMapper objectMapper;

    public LocationService(LocationRepository locationRepository, ObjectMapper objectMapper) {
        this.locationRepository = locationRepository;
        this.objectMapper = objectMapper;
    }

    Location createLocation(String city, Double longitude, Double latitude, String region, String country) {
        if (latitude > 90 || latitude < -90)
            throw new IllegalArgumentException("Latitude should be in range between -90 and 90");
        if (longitude > 180 || longitude < -180)
            throw new IllegalArgumentException("Longitude should be in range between -180 and 180");
        if (city == null || city.isBlank())
            throw new IllegalArgumentException("City cannot be null");
        if (country == null || country.isBlank())
            throw new IllegalArgumentException("Country cannot be null");

        Location location = new Location(city, longitude, latitude, region, country);

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://worldclockapi.com/api/json/utc/now"))
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            TimeDTO timeDTO = objectMapper.readValue(responseBody, TimeDTO.class);

            String currentDateTime = timeDTO.getCurrentDateTime();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
            LocalDateTime localDateTime = LocalDateTime.parse(currentDateTime, dateTimeFormatter);
            Instant now = localDateTime.toInstant(ZoneOffset.UTC);

            location.setCreationDate(now);
        } catch (Exception e) {
            throw new RuntimeException("Time issue: " + e.getMessage());
        }

        Location savedLocation = locationRepository.save(location);
        return savedLocation;
    }
}
