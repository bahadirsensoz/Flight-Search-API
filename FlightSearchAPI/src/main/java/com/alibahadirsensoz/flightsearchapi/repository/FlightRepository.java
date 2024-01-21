package com.alibahadirsensoz.flightsearchapi.repository;

import com.alibahadirsensoz.flightsearchapi.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends MongoRepository<Flight, String> {



}
