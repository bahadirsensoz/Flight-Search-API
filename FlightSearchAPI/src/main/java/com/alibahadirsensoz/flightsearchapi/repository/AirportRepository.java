package com.alibahadirsensoz.flightsearchapi.repository;

import com.alibahadirsensoz.flightsearchapi.api.model.Airport;
import com.alibahadirsensoz.flightsearchapi.api.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AirportRepository extends MongoRepository<Airport, String> {
}
