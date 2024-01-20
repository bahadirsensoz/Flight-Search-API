package com.alibahadirsensoz.flightsearchapi.service;


import com.alibahadirsensoz.flightsearchapi.exception.ResourceNotFoundException;
import com.alibahadirsensoz.flightsearchapi.model.Airport;
import com.alibahadirsensoz.flightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    public Airport addAirport(Airport airport){
//        airport.setAirportId(UUID.randomUUID().toString());
        return airportRepository.save(airport);
    }

    public Optional<Airport> getAirportByAirportId(String airportId) {
        Optional<Airport> airport = airportRepository.findById(airportId);

        if (airport.isPresent()){
            return Optional.of(airport.get());
        } else {
            throw new ResourceNotFoundException("Record with id = " + airportId + "was not found.");
        }
    }

    public Airport updateAirport(String airportId, Airport updatedAirport){
        Optional<Airport> airportOptional = airportRepository.findById(airportId);

        if(airportOptional.isPresent()) {
            Airport existingAirport = airportOptional.get();
            existingAirport.setCity(updatedAirport.getCity());
            return airportRepository.save(existingAirport);
        } else {
            throw new ResourceNotFoundException("Record with id = " + airportId + "was not found.");
        }
    }

    public void deleteAirport(String airportId) {
        Optional<Airport> airport = airportRepository.findById(airportId);

        if (airport.isPresent()) {
            airportRepository.deleteById(airportId);
        } else {
            throw new ResourceNotFoundException("Record with id = " + airportId + "was not found.");
        }
    }

}
