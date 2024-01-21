package com.alibahadirsensoz.flightsearchapi.controller;


import com.alibahadirsensoz.flightsearchapi.model.Flight;
import com.alibahadirsensoz.flightsearchapi.repository.SearchRepository;
import com.alibahadirsensoz.flightsearchapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/flights")
public class FlightController {


    @Autowired
    private FlightService flightService;

    @Autowired
    private SearchRepository searchRepository;

    @GetMapping("/")
    public List<Flight> getFlights(){
        return flightService.getFlights();
    }

    @GetMapping("/{flightId}")
    public Optional<Flight> getFlightByFlightId(@PathVariable String flightId){
        return flightService.getFlightByFlightId(flightId);

    }

    @PostMapping("/addflight")
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @PutMapping("/{flightId}")
    public Flight updateFlight(@PathVariable String flightId, @RequestBody Flight flight){
        return  flightService.updateFlight(flightId, flight);
    }

    @DeleteMapping("/{flightId}")
    public void deleteFlight(@PathVariable String flightId){
        flightService.deleteFlight(flightId);
    }

//    @GetMapping("/search")
//    public List<Flight> searchFlights(
//            @RequestParam(value = "origin", required = true) String origin,
//            @RequestParam(value = "destination", required = true) String destination,
//            @RequestParam(value = "departureDate", required = true) String departureDate,
//            @RequestParam(value = "returnDate", required = false) String returnDate) {
//
//        return flightService.searchFlights(origin, destination, departureDate, returnDate);
//    }

    @GetMapping("/searchByOrigin")
    public List<Flight> searchByOrigin(@RequestParam(value = "origin", required = true) String origin){
        return flightService.searchByOrigin(origin);
    }

    @GetMapping("/searchByAfterDepartureTime")
    public List<Flight> searchByAfterDepartureTime(@RequestParam(value = "departureDate", required = true) String departureTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse(departureTime, DateTimeFormatter.ISO_DATE_TIME);
        return flightService.searchByAfterDepartureTime(localDateTime);
    }

    @GetMapping("/searchByBeforeDepartureTime")
    public List<Flight> searchByBeforeDepartureTime(@RequestParam(value = "departureDate", required = true) String departureTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse(departureTime, DateTimeFormatter.ISO_DATE_TIME);
        return flightService.searchByBeforeDepartureTime(localDateTime);
    }

    @GetMapping("/search")
    public List<Flight> searchByBetweenTimesAndDepartureLanding(
            @RequestParam(value = "departureDate", required = true) String departureDate,
            @RequestParam(value = "returnDate", required = false) String returnDate) {
        LocalDateTime requestTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime localDateTime2 = LocalDateTime.parse(departureDate, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.from(localDateTime2), LocalTime.MAX);
        return flightService.searchByBetweenTimesAndDepartureLanding(requestTime, endOfDay);
    }

    @GetMapping("/searchByDestination")
    public List<Flight> searchByDestination(@RequestParam(value = "destination", required = true) String destination){
        return flightService.searchByDestination(destination);
    }


}
