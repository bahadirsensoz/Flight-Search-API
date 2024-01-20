package com.alibahadirsensoz.flightsearchapi.api.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Airport {

    @Id
    private String airportId;
    private String city;
}
