package com.alibahadirsensoz.flightsearchapi.model;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import lombok.Data;

@Document
@Data
public class Flight {

    @Id
    private String flightId;
    @NonNull
    private String departureAirport;
    @NonNull
    private String landingAirport;
    @Positive
    private int price;
    @NonNull

    private LocalDateTime departureTime;
    @NonNull
    private LocalDateTime landingTime;

    public Flight() {

    }
}
