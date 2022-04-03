package com.sda.weather.location;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "location")
class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private Double longitude;
    private Double latitude;
    private String region;
    private String country;
    private Instant creationDate;

    public Location(String city, Double longitude,
                    Double latitude, String region, String country) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.region = region;
        this.country = country;
    }
}
