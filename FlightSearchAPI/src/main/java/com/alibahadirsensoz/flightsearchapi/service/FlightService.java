package com.alibahadirsensoz.flightsearchapi.service;


import java.time.LocalDateTime;
import com.alibahadirsensoz.flightsearchapi.exception.ResourceNotFoundException;
import com.alibahadirsensoz.flightsearchapi.model.Flight;
import com.alibahadirsensoz.flightsearchapi.repository.FlightRepository;
import com.alibahadirsensoz.flightsearchapi.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SearchRepository searchRepository;

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


    public List<Flight> searchByOrigin(String origin){
        return searchRepository.findFlightsByDepartureAirport(origin);
    }

    public List<Flight> searchByBeforeDepartureTime(LocalDateTime departureTime){
        return searchRepository.findFlightsByDepartureTimeAfter(departureTime);
    }

    public List<Flight> searchByAfterDepartureTime(LocalDateTime departureTime) {
        return searchRepository.findFlightsByDepartureTimeBefore(departureTime);
    }


    public List<Flight> searchByBetweenTimesAndDepartureLanding(LocalDateTime requestTime, LocalDateTime departureTime){
        requestTime = LocalDateTime.now();
        return searchRepository.findFlightsByDepartureTimeBetween(requestTime, departureTime);
    }
    public List<Flight> searchFlights(String origin, String destination, String departureDate, String returnDate) {
        DateTimeFormatter isoFormat = DateTimeFormatter.ISO_DATE_TIME;
        departureDate = departureDate.replace(" ", "+");

        if (returnDate != null) {
            returnDate = returnDate.replace(" ", "+");

            // Two-way flight search
            return searchRepository.findFlightsByDepartureAirportAndLandingAirportAndDepartureTimeAndLandingTime(
                    origin, destination, LocalDateTime.parse(departureDate, isoFormat), LocalDateTime.parse(returnDate, isoFormat));
        } else {
            // One-way flight search
            return searchRepository.findFlightsByDepartureAirportAndLandingAirportAndDepartureTime(
                    origin, destination, LocalDateTime.parse(departureDate, isoFormat));
        }
    }

    public List<Flight> searchByDestination(String destination) {
        return searchRepository.findFlightsByLandingAirport(destination);
    }

}





