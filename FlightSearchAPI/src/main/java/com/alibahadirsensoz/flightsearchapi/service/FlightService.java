package com.alibahadirsensoz.flightsearchapi.service;


import com.alibahadirsensoz.flightsearchapi.exception.ResourceNotFoundException;
import com.alibahadirsensoz.flightsearchapi.model.Flight;
import com.alibahadirsensoz.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public Flight addFlight(Flight flight){
//        flight.setFlightId(UUID.randomUUID().toString());
        return flightRepository.save(flight);
    }

    public Optional<Flight> getFlightByFlightId(String flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);

        if (flight.isPresent()){
            return Optional.of(flight.get());
        } else {
            throw new ResourceNotFoundException("Record with id = " + flightId + "was not found.");
        }
    }

    public Flight updateFlight(String flightId, Flight updatedFlight){
        Optional<Flight> flightOptional = flightRepository.findById(flightId);

        if(flightOptional.isPresent()) {
            Flight existingFlight = flightOptional.get();
            existingFlight.setPrice(updatedFlight.getPrice());
            existingFlight.setLandingTime(updatedFlight.getLandingTime());
            existingFlight.setDepartureTime(updatedFlight.getDepartureTime());
            existingFlight.setLandingAirport(updatedFlight.getLandingAirport());
            existingFlight.setDepartureAirport(updatedFlight.getDepartureAirport());
            return flightRepository.save(existingFlight);
        } else {
            throw new ResourceNotFoundException("Record with id = " + flightId + "was not found.");
        }
    }

    public void deleteFlight(String flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);

        if (flight.isPresent()) {
            flightRepository.deleteById(flightId);
        } else {
            throw new ResourceNotFoundException("Record with id = " + flightId + "was not found.");
        }
    }

}
