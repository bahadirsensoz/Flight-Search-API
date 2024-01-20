package com.alibahadirsensoz.flightsearchapi.api.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import lombok.Data;


@Document
@Data
public class Flight {

    @Id
    private String flightId;
    private String departureAirport;
    private String landingAirport;
    private int price;
    private LocalDateTime departureTime;
    private LocalDateTime landingTime;

}
