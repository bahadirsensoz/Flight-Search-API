package com.alibahadirsensoz.flightsearchapi.model;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document
@Data
public class Airport {

    @Id
    private String airportId;

    @NonNull
    private String city;

    public Airport() {

    }
}
