package com.sda.weather.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class LocationServiceTest {

    private LocationService locationService;

    @BeforeEach
    void setUp() {

        LocationRepository locationRepository = new LocationRepositoryMock();
        ObjectMapper objectMapper = new ObjectMapper();
        locationService = new LocationService(locationRepository, objectMapper);
    }

    @Test
    void createLocation_whenDataIsProper_returnsNewObject() {
        Location result = locationService.createLocation("poz", 11.1, 12.2, "wlkp", "pol");
        assertThat(result.getCity()).isNotNull();
        assertThat(result.getCountry()).isNotNull();
        assertThat(result.getLatitude()).isLessThanOrEqualTo(90);
        assertThat(result.getLatitude()).isGreaterThanOrEqualTo(-90);
        assertThat(result.getLongitude()).isLessThanOrEqualTo(180);
        assertThat(result.getLongitude()).isGreaterThanOrEqualTo(-180);
    }

    @Test
    void createLocation_whenCityIsEmpty_throwsAnException() {
        Throwable result = catchThrowable(() -> locationService.createLocation(" ", 11.1, 11.2, "wlkp", "pol"));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenCountryIsEmpty_throwsAnException() {
        Throwable result = catchThrowable(() -> locationService.createLocation("pzn", 11.1, 11.2, "wlkp", " "));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenLongitudeIsTooLarge_throwsAnException() {
        Throwable result = catchThrowable(() -> locationService.createLocation("pzn", 190.0, 11.2, "wlkp", "pol"));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenLongitudeIsTooSmall_throwsAnException() {
        Throwable result = catchThrowable(() -> locationService.createLocation("pzn", -190.0, 11.2, "wlkp", "pol"));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenLatitudeIsTooLarge_throwsAnException() {
        Throwable result = catchThrowable(() -> locationService.createLocation("pzn", 170.0, 100.0, "wlkp", "pol"));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createLocation_whenLatitudeIsTooSmall_throwsAnException() {
        Throwable result = catchThrowable(() -> locationService.createLocation("pzn", 190.0, -100.0, "wlkp", "pol"));
        assertThat(result).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
